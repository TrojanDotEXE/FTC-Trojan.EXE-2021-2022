package org.firstinspires.ftc.teamcode.TeleOpuri.Altele;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Hardware.HardwareM;

@TeleOp(name = "Teleop_DriveTrains", group = "Other")
@Disabled
public class TeleOp_DriveTrains extends HardwareM
{
    @Override
    public void runOpMode()
    {
        double left_Y;
        double left_X;
        double right_Y;
        double right_X;
        double targetAngle;
        double mag1;
        double mag2;
        double rotationPower;
        double maxPower;
        double scaleDown;

        telemetry.addData("Status: ", "Hit [Init] to Initialize ze bot");    //
        telemetry.update();

        init(hardwareMap);

        telemetry.addData("Status: ", "Hit [PLAY] to start!");
        telemetry.update();

        waitForStart();
        while(opModeIsActive())
        {
            left_Y = gamepad1.left_stick_y;
            left_X = gamepad1.left_stick_x;
            right_Y = gamepad1.right_stick_y;
            right_X = gamepad1.right_stick_x;
            targetAngle = (Math.atan2(right_Y, right_X));

            rotationPower = -left_X;
            mag1 = Math.sqrt(Math.pow(right_X, 2) + Math.pow(right_Y, 2)) * (Math.sin(targetAngle + Math.PI / 4));
            mag2 = Math.sqrt(Math.pow(right_X, 2) + Math.pow(right_Y, 2)) * (Math.sin(targetAngle - Math.PI / 4));

            maxPower = Math.max(Math.abs(mag1) + Math.abs(rotationPower), Math.abs(mag2) + Math.abs(rotationPower)) + 0.15;
            scaleDown = 1.0;

            if (maxPower > 1)
                scaleDown = 1.0 / maxPower;

            roataStanga.setPower((mag2 - rotationPower) * scaleDown);
            roataDreapta.setPower((mag1 + rotationPower) * scaleDown);
        }
    }
}

