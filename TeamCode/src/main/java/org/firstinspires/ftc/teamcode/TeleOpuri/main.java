package org.firstinspires.ftc.teamcode.TeleOpuri;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorImpl;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Hardware.HardwareM;

@TeleOp(name = "TeleOP", group = "Teste")
@Disabled
public class main extends OpMode
{
    private ElapsedTime runtime = new ElapsedTime();
    private Orientation angles  = new Orientation();
            HardwareM   fer     = new HardwareM();
    private double      currAngle = 0.0;
    private DcMotorEx

    @Override
    public void init() {
        fer.init(hardwareMap);
        telemetry.addData("Status: " ,"Initialized");
    }
    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        double fata_spate     = gamepad1.left_stick_y;
        double stanga_dreapta = gamepad1.right_stick_x;
        double left, right;

        left  = Range.clip(fata_spate - stanga_dreapta,-1,1);
        right = Range.clip(fata_spate + stanga_dreapta,-1 ,1);

        fer.roataStanga.setPower(left);
        fer.roataDreapta.setPower(right);

        while(gamepad1.dpad_left) {
            fer.roataStanga.setPower(-.7);
            fer.roataDreapta.setPower(.7);
            }
        while(gamepad1.dpad_right) {
            fer.roataStanga.setPower(.7);
            fer.roataDreapta.setPower(-.7);
        }

        //Carusel
        while(gamepad1.right_bumper)
            fer.carusel.setPower(-.85);
        while (gamepad1.left_bumper)
            fer.carusel.setPower(.85);

        fer.carusel.setPower(0);

///Gamepad 2

        //Brat
        while(gamepad2.b){
            fer.bratDreapta.setPower(Range.clip(gamepad2.left_stick_y, -.3, .3));
            fer.bratStanga.setPower(Range.clip(gamepad2.left_stick_y, -.3, .3));

        }
        fer.bratDreapta.setPower(Range.clip(gamepad2.left_stick_y, -.5, .5));
        fer.bratStanga.setPower(Range.clip(gamepad2.left_stick_y, -.5, .5));
        fer.bratScripete.setPower(Range.clip(gamepad2.right_stick_y, -1, 1));

        //Cleste
        if(gamepad2.right_bumper) {
            fer.clesteStanga.setPower(-1);
            fer.clesteDreapta.setPower(-1 );
        }
        else {
            fer.clesteStanga.setPower(1);
            fer.clesteDreapta.setPower(.8);
        }
        if(gamepad1.y)
            turn(42);

        if (gamepad1.b)
            turn(-41);

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
        resetAngle();                      // asta ii robotu meu, sugeti
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
