package org.firstinspires.ftc.teamcode.Autonome.Teste;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Autonome.MetodeAutonoma;
import org.firstinspires.ftc.teamcode.Hardware.HardwareM;

@Autonomous(name = "Test_Gyro", group = "Teste")
public class Test_Gyro extends LinearOpMode {
    private double      currAngle = 0.0;
    private Orientation angles    = new Orientation();
    private HardwareM   fer       = new HardwareM();

    @Override
    public void runOpMode() throws InterruptedException {
        fer.init(hardwareMap);

        turn(90);

        sleep(3000);

        turn(-180);

    }

    public void resetAngle() {
        angles = fer.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        currAngle = 0;
    }

    public double getCurrAngle() {
        Orientation orientation = fer.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double deltaAngle = orientation.firstAngle - angles.firstAngle;

        if(deltaAngle > 180)
            deltaAngle -= 360;
        else if(deltaAngle <= -180)
            deltaAngle += 360;

        currAngle += deltaAngle;
        angles = orientation;
        return currAngle;
    }

    public void turn(double deg) {
        resetAngle();

        double target = deg;

        while (opModeIsActive() && Math.abs(target) > 2) {
            double p = (target < 0 ? -.3 : .3);
            fer.setWheelPowers(-p, p);
            target = deg - getCurrAngle();
            telemetry.addData("Target: ", "%7f / %7f", target, deg);
        }
        fer.stopMotors();
    }
}
