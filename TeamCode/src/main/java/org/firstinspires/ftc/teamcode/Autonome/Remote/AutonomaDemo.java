package org.firstinspires.ftc.teamcode.Autonome.Remote;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Autonome.MetodeAutonoma;
import org.firstinspires.ftc.teamcode.Hardware.HardwareM;

@Autonomous(name = "Autonoma Test", group = "Teste")
public class AutonomaDemo extends MetodeAutonoma {
private HardwareM   fer       = new HardwareM();
private ElapsedTime runtime   = new ElapsedTime();
private ElapsedTime runtime2  = new ElapsedTime();
private Orientation angles    = new Orientation();
private double      currAngle = 0.0;
private double      velocity = 50;
private int         degrees90 = 30;

    @Override
    public void runOpMode() throws InterruptedException{
        fer.initialize(hardwareMap);

        waitForStart();
        runtime.reset();

        //Prinde pre-loadul
        fer.clesteStanga.setPower(1);
        fer.clesteDreapta.setPower(.7);

        goToV(velocity, 500, fer.brat1);
        while (opModeIsActive() && runtime.seconds() < 30 && fer.brat1.isBusy()){}
        fer.stopMotors(fer.brat1);
        fer.setEncoderMode(DcMotor.RunMode.RUN_USING_ENCODER, fer.brat1);

        //stai o secunda
//        sleep(1000);

        //intoarce-te la dreapta
        turn(degrees90-1.7);

        goToVcm(velocity, 10, fer.wheelLeftBack, fer.wheelRightBack);
        while(opModeIsActive() && runtime.seconds() < 30 && (fer.wheelLeftBack.isBusy() || fer.wheelRightBack.isBusy())){ //isRunning(runtime, fer.wheelLeftBack, fer.wheelRightBack)
            fer.wheelLeftFront.setVelocity(velocity);
            fer.wheelRightFront.setVelocity(velocity);
        }
        fer.stopMotors(fer.wheelLeftBack, fer.wheelLeftFront, fer.wheelRightFront ,fer.wheelRightBack);
        fer.setEncoderMode(DcMotor.RunMode.RUN_USING_ENCODER, fer.wheelLeftBack, fer.wheelRightBack);
    }

    public void resetAngle() {
        angles = fer.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        currAngle = 0;
    }

    public double getCurrAngle() {
        Orientation orientation = fer.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double deltaAngle = -orientation.firstAngle + angles.firstAngle;

        if(deltaAngle > 180)
            deltaAngle -= 360;
        else if(deltaAngle <= -180)
            deltaAngle += 360;
        currAngle += deltaAngle;
        angles = orientation;
        return currAngle;
    }

    public void turn(double degrees) {
        resetAngle();
        double target = degrees;
        while(Math.abs(target) > 2){
            getCurrAngle();
            double p = (target < 0 ? -.3 : .3);
            fer.wheelLeftBack.setPower(-p);
            fer.wheelLeftFront.setPower(-p);
            fer.wheelRightBack.setPower(p);
            fer.wheelRightFront.setPower(p);
            target = target - currAngle;
            resetAngle();
            telemetry.addData("Target: ", "%7f / %7f", target, 2.0);
            telemetry.update();

        }
    }
}
