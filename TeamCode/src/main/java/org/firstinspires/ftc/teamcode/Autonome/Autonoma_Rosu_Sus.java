package org.firstinspires.ftc.teamcode.Autonome;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Hardware.HardwareM;

@Autonomous (name="Rosu_Sus",group="Autonome")
public class Autonoma_Rosu_Sus extends TemplateAutonoma
{
//    HardwareM fer = new HardwareM();

    @Override
    public void runOpMode() throws InterruptedException
    {
        fer.init(hardwareMap);
        telemetry.addData("Robot", "Iitializat");
        telemetry.update();

        waitForStart();
        while(opModeIsActive())
        {
            fer.roataDreapta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            fer.roataStanga.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            fer.roataDreapta.setTargetPosition(HardwareM.NEVEREST40_TICKS_PER_INCH*7);
            fer.roataStanga.setTargetPosition(HardwareM.NEVEREST40_TICKS_PER_INCH*7);

            fer.roataDreapta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fer.roataStanga.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            fer.roataDreapta.setPower(.3);
            fer.roataStanga.setPower(.3);

            while(fer.roataStanga.isBusy() || fer.roataDreapta.isBusy()) {}
            fer.roataStanga.setPower(0);
            fer.roataDreapta.setPower(0);

//            fata_spate(.5,HardwareM.NEVEREST40_TICKS_PER_INCH*15);
            telemetry.addData("Rotatii", fer.roataStanga.getCurrentPosition());
            telemetry.addData("Rotatii", fer.roataDreapta.getCurrentPosition());
            telemetry.update();
        }
    }
}
