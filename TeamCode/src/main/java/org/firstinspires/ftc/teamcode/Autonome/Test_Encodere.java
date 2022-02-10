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

            fer.roataStanga.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fer.roataDreapta.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            fer.roataStanga.setTargetPosition(1120);
            fer.roataDreapta.setTargetPosition(1120);

            fer.roataStanga.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fer.roataDreapta.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            fer.roataStanga.setPower(-.3);
            fer.roataDreapta.setPower(-.3);

            while(opModeIsActive() && (fer.roataStanga.isBusy() && fer.roataDreapta.isBusy())){
                telemetry.addData("Rotatii Stanga/Dreapta", "%7d / %7d",
                                    fer.roataStanga.getCurrentPosition(),
                                    fer.roataDreapta.getCurrentPosition());
                telemetry.update();
            }
            fer.roataStanga.setPower(0);
            fer.roataDreapta.setPower(0);

            fer.roataStanga.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fer.roataDreapta.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}
