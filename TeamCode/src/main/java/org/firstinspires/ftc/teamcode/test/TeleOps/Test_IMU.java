package org.firstinspires.ftc.teamcode.test.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.hardware.robot.Robot;

@TeleOp(name = "Test_IMU", group = "Teste_TeleOp")
@Disabled
public class Test_IMU extends OpMode {
    private double      currAngle = 0.0;
    private Orientation angles    = new Orientation();
    private Robot fer       = new Robot();

    @Override
    public void init() {
        fer.init();
        telemetry.addData("Initializare: ", "Completa");
    }

    @Override
    public void loop() {
        telemetry.addData("Heading: ", angles.firstAngle);

    }

    public void resetAngle() {
        angles = fer.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        currAngle = 0;
    }
}
