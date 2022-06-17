package org.firstinspires.ftc.teamcode.Teste.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Hardware.HardwareM;

@TeleOp(name = "Test IMU", group = "Teste")
@Disabled
public class Test_IMU extends OpMode {
    private double      currAngle = 0.0;
    private Orientation angles    = new Orientation();
    private HardwareM   fer       = new HardwareM();

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
