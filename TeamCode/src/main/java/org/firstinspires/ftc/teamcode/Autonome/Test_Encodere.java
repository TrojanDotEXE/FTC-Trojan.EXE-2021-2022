package org.firstinspires.ftc.teamcode.Autonome;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Hardware.HardwareM;

@Autonomous(name = "Test Encodere")
public class Test_Encodere extends LinearOpMode
{
    HardwareM fer = new HardwareM();

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        while(opModeIsActive())
        {
            fer.init(hardwareMap);
            fer.roataStanga.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            fer.roataDreapta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            fer.roataStanga.setTargetPosition(1120);
            fer.roataDreapta.setTargetPosition(1120);

            fer.roataStanga.setPower(.3);
            fer.roataDreapta.setPower(.3);

            fer.roataStanga.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fer.roataDreapta.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            telemetry.addData("Rotatii Stanga", fer.roataStanga.getCurrentPosition());
            telemetry.addData("Rotatii Dreapta", fer.roataDreapta.getCurrentPosition());
            telemetry.update();

            while(fer.roataStanga.isBusy() || fer.roataDreapta.isBusy()){}
            fer.roataStanga.setPower(0);
            fer.roataDreapta.setPower(0);
        }
    }
}
