package org.firstinspires.ftc.teamcode.TeleOpuri.Altele;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.Hardware.HardwareM;

@TeleOp(name = "Cleste", group = "Other")
@Disabled
public class TeleOp_Cleste extends OpMode {
    HardwareM fer = new HardwareM();

    @Override
    public void init() {
        fer.init(hardwareMap);
        telemetry.addData("Robot" ,"Initializat");
    }

    @Override
    public void loop() {

        telemetry.update();
    }
    }

