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


@Autonomous(name = "Test #3", group = "Teste")
public class Autonoma_Demo_Remote extends MetodeAutonoma
{
    private HardwareM   fer       = new HardwareM();
    private ElapsedTime runtime   = new ElapsedTime();
    private ElapsedTime runtime2  = new ElapsedTime();
    private Orientation angles    = new Orientation();
    private double      currAngle = 0.0;
    private int         degrees90 = 30;

    @Override
    public void runOpMode() throws InterruptedException {

        fer.initialize();

        waitForStart();
        runtime.reset();

        //Prinde pre-loadul
        fer.clesteStanga.setPower(1);
        fer.clesteDreapta.setPower(.7);

        //stai o secunda
        sleep(1000);

        //intoarce-te la dreapta
        turn(degrees90-1.7);

        runtime2.reset();
        //Ridica bratul la stratul 3
        while(opModeIsActive() && runtime2.milliseconds()<HardwareM.T3+50){
            fer.brat.setPower(.27);
        }
        fer.brat.setPower(0);

        //mergi in fata
        goTo(.35, -1925, fer.wheelLeftBack, fer.wheelLeftFront, fer.wheelRightFront ,fer.wheelRightBack);
        while(opModeIsActive() && runtime.seconds() < 30 && (fer.wheelLeftBack.isBusy() || fer.wheelLeftBack.isBusy())){}
        fer.stopMotors(fer.wheelLeftBack, fer.wheelLeftFront, fer.wheelRightFront ,fer.wheelRightBack);
        fer.setEncoderMode(DcMotor.RunMode.RUN_USING_ENCODER, fer.wheelLeftBack, fer.wheelLeftFront, fer.wheelRightFront ,fer.wheelRightBack);

        //intoarce-te la stanga
        turn(-degrees90+9);

        runtime2.reset();
        //Ridica bratul la stratul 3
        while(opModeIsActive() && runtime2.milliseconds()<HardwareM.T3+100f){
            fer.brat.setPower(.34);
        }
        fer.brat.setPower(0);

        //Mergi in fata pana la s.h.
        goTo(.35, -1540, fer.wheelLeftBack, fer.wheelLeftFront, fer.wheelRightFront ,fer.wheelRightBack);
        while(opModeIsActive() && runtime.seconds() < 30 && (fer.wheelLeftBack.isBusy() || fer.wheelRightBack.isBusy()));
        fer.stopMotors(fer.wheelLeftBack, fer.wheelLeftFront, fer.wheelRightFront ,fer.wheelRightBack);
        fer.setEncoderMode(DcMotor.RunMode.RUN_USING_ENCODER, fer.wheelLeftBack, fer.wheelLeftFront, fer.wheelRightFront ,fer.wheelRightBack);

        //Reseteaza al 2-lea timer
        runtime2.reset();

        //lasa obiectul in s.h.
        fer.clesteStanga.setPower(-.7);
        fer.clesteDreapta.setPower(-.7);

        runtime2.reset();

        //Ridica bratul la stratul 3
        while(opModeIsActive() && runtime2.milliseconds()<150){
            fer.brat.setPower(.28);
        }
        fer.brat.setPower(0);

        //mergi in spate la pozitia initiala
        goTo(1, 1480, fer.wheelLeftBack, fer.wheelLeftFront, fer.wheelRightFront ,fer.wheelRightBack);
        while(opModeIsActive() && runtime.seconds() < 30 && (fer.wheelLeftBack.isBusy() || fer.wheelRightBack.isBusy()));
        fer.stopMotors(fer.wheelLeftBack, fer.wheelLeftFront, fer.wheelRightFront ,fer.wheelRightBack);
        fer.setEncoderMode(DcMotor.RunMode.RUN_USING_ENCODER, fer.wheelLeftBack, fer.wheelLeftFront, fer.wheelRightFront ,fer.wheelRightBack);

        //Reseteaza al 2-lea timer
        runtime2.reset();

        //Coboara bratul
        while(opModeIsActive() && runtime2.milliseconds()<HardwareM.T3-100){
            fer.brat.setPower(-.2);
        }
        fer.brat.setPower(0);

        //intoarce-te la dreapta
        turn(degrees90+12);

        //mergi in spate pana la carusel
        goTo(1, 6380, fer.wheelLeftBack, fer.wheelLeftFront, fer.wheelRightFront ,fer.wheelRightBack);
        while(opModeIsActive() && runtime.seconds() < 30 && (fer.wheelLeftBack.isBusy() || fer.wheelLeftBack.isBusy())){}
        fer.stopMotors(fer.wheelRightBack, fer.wheelLeftBack);
        fer.setEncoderMode(DcMotor.RunMode.RUN_USING_ENCODER, fer.wheelLeftBack, fer.wheelLeftFront, fer.wheelRightFront ,fer.wheelRightBack);

        //intoarce-te la stanga
        turn(-degrees90-9.5);

        //mergi in spate pana la carusel
        goTo(1, 1900, fer.wheelLeftBack, fer.wheelLeftFront, fer.wheelRightFront ,fer.wheelRightBack);
        while(opModeIsActive() && runtime.seconds() < 30 && (fer.wheelLeftBack.isBusy() || fer.wheelLeftBack.isBusy())){}
        fer.stopMotors(fer.wheelLeftBack, fer.wheelLeftFront, fer.wheelRightFront ,fer.wheelRightBack);
        fer.setEncoderMode(DcMotor.RunMode.RUN_USING_ENCODER, fer.wheelLeftBack, fer.wheelLeftFront, fer.wheelRightFront ,fer.wheelRightBack);


        runtime2.reset();
        //da o ratusca jos
           while(opModeIsActive() && runtime2.seconds()<3.5){
               fer.carusel.setPower(.8);

       }
        fer.carusel.setPower(0);

        //mergi in fata pana in s.u.
        goTo(1, -2025, fer.wheelLeftBack, fer.wheelLeftFront, fer.wheelRightFront ,fer.wheelRightBack);
        while(opModeIsActive() && runtime.seconds() < 30 && runtime.seconds()<30 && (fer.wheelLeftBack.isBusy() || fer.wheelLeftBack.isBusy())){}
        fer.stopMotors(fer.wheelLeftBack, fer.wheelLeftFront, fer.wheelRightFront ,fer.wheelRightBack);

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
