package org.firstinspires.ftc.teamcode.Autonome.Teste;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonome.MetodeAutonoma;
import org.firstinspires.ftc.teamcode.Hardware.HardwareM;


@Autonomous(name = "Test #3", group = "Teste")
public class Autonoma_Demo_Remote extends MetodeAutonoma
{
    HardwareM fer = new HardwareM();

    @Override
    public void runOpMode() throws InterruptedException {

        fer.init(hardwareMap);

        waitForStart();

        goTo(.3, 1440, fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() & (fer.roataStanga.isBusy() || fer.roataDreapta.isBusy())){
            displayEncoders(fer.roataStanga, fer.roataDreapta);
        }

    }
}
