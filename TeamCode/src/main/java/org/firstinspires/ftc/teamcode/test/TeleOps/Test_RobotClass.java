package org.firstinspires.ftc.teamcode.test.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.test.Hardware.TestChildRobot;

public class Test_RobotClass extends OpMode {

    TestChildRobot robot;

    @Override
    public void init() {
        robot = new TestChildRobot(this);

        telemetry.addData("Status: ", "Initializat");
        telemetry.update();
    }

    @Override
    public void loop() {
        double move = gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;

        robot.mecanumDrive.drive(move, turn);
    }
}
