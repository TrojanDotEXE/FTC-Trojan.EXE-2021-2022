package org.firstinspires.ftc.teamcode.TeleOpuri.Altele;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.Hardware.HardwareM;

@TeleOp(name = "RipPeria", group = "Other")
@Disabled
public class RIP_Peria extends OpMode {
    HardwareM fer = new HardwareM();

    @Override
    public void init() {
        fer.init(hardwareMap);
        telemetry.addData("Robot" ,"Initializat");
    }

    @Override
    public void loop() {
                //Peria
                if(gamepad2.left_bumper) {
                    fer.peria.setPower(1);
                    telemetry.addData("Perie","Spate");
                }
                else if(gamepad2.right_bumper) {
                    fer.peria.setPower(-1);
                    telemetry.addData("Perie","Fata");
                }
                fer.peria.setPower(0);
                telemetry.addData("Perie","Oprita");
        }
}

