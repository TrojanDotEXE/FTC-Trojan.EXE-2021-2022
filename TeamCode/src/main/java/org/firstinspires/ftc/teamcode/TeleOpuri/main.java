package org.firstinspires.ftc.teamcode.TeleOpuri;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Hardware.HardwareM;

@TeleOp(name = "TeleOP", group = "Teste")
//@Disabled
public class main extends OpMode
{
    private final double ARM_MAX_LOAD_POWER = .35;
    private ElapsedTime runtime = new ElapsedTime();
    private Orientation angles  = new Orientation();
    private HardwareM   fer     = new HardwareM();
    private double      currAngle = 0.0;
    private double compensation = 0;

    @Override
    public void init() {
        fer.initialize(hardwareMap);
    }

    @Override
    public void start() {
        runtime.reset();
        fer.restartServos();
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


        if(gamepad1.y)
            turn(41);

        if (gamepad1.b)
            turn(-41);

        //Carusel
        if(gamepad1.right_bumper)
            fer.carusel.setPower(-.9);
        if(gamepad1.left_bumper)
            fer.carusel.setPower(.9);
        fer.carusel.setPower(0);

///Gamepad 2

        //Brat
        double leftStickY2 = gamepad2.left_stick_y * 5;


        if (gamepad2.b) {
            compensation = leftStickY2;
        }

        if (leftStickY2 > 0)
            fer.brat1.setPower(Range.clip(leftStickY2, -1, 1));

        else if (compensation > 0)
                fer.brat1.setPower(Range.clip(compensation, -1, 1));

        telemetry.addData("Putere Btat: ", "%.4f", leftStickY2);
        telemetry.addData("Putere Btat1: ", "%.4f", fer.brat1.getPower());

        if (gamepad2.left_trigger > 0)
            fer.brat1.setPower(.35);
        else if(gamepad2.right_trigger > 0)
            fer.brat1.setPower(-.35);

        //Cleste
        if(gamepad2.right_bumper) {
            fer.clesteStanga.setPower(-1);
            fer.clesteDreapta.setPower(-1);
        }
        else {
            fer.clesteStanga.setPower(1);
            fer.clesteDreapta.setPower(1);
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
