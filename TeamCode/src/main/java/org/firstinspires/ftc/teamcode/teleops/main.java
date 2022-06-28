package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.hardware.robot.Robot;

@TeleOp(name = "TeleOP", group = "Teste")
//@Disabled
public class main extends OpMode
{
    private final double ARM_MAX_LOAD_POWER = 0;
    private ElapsedTime runtime = new ElapsedTime();
    private Orientation angles  = new Orientation();
    private Robot fer     = new Robot();
    private double      currAngle = 0.0;

    @Override
    public void init() {
        fer.initialize(hardwareMap);
    }

    @Override
    public void start() {
        runtime.reset();
        fer.clesteStanga.setPower(1);
        fer.clesteDreapta.setPower(.8);
    }

    @Override
    public void loop() {
        double fata_spate = gamepad1.left_stick_y;
        double stanga_dreapta = gamepad1.right_stick_x;

        double left = Range.clip(fata_spate - stanga_dreapta, -1, 1);
        double right = Range.clip(fata_spate + stanga_dreapta, -1, 1);

        fer.setWheelPowers(left, right);

        while(gamepad1.dpad_left)
            fer.setWheelPowers(-.7, .7);

        while(gamepad1.dpad_right)
            fer.setWheelPowers(.7, -.7);


//        if(gamepad1.y)
//            turn(41);
//
//        if (gamepad1.b)
//            turn(-41);

        //Carusel
        if(gamepad1.right_bumper)
            fer.carusel.setPower(-.9);
        if(gamepad1.left_bumper)
            fer.carusel.setPower(.9);
        fer.carusel.setPower(0);

///Gamepad 2

        //Brat
        fer.brat1.setPower(Range.clip(gamepad2.left_stick_y*.5, -1, 1));

        telemetry.addData("Putere Btat: ", "%.4f", gamepad2.left_stick_y);
        telemetry.addData("Putere Btat1: ", "%.4f", fer.brat1.getPower());

        if(gamepad2.right_trigger > 0)
            fer.brat1.setPower(-.3);
        if(gamepad2.b) {
            fer.brat1.setPower(.3);
        }

        //Cleste
        if(gamepad2.right_bumper) {
            fer.clesteStanga.setPower(-1);
            fer.clesteDreapta.setPower(-1);
        }
        else {
            fer.clesteStanga.setPower(1);
            fer.clesteDreapta.setPower(.8);
        }

        telemetry.addData("Run Time: ", "%7d", (int)runtime.seconds());
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
            fer.setWheelPowers(-p, p);
            target = target - currAngle;
            resetAngle();
            telemetry.addData("Target: ", "%7f / %7f", target, 2.0);
            telemetry.update();

        }
    }
}
