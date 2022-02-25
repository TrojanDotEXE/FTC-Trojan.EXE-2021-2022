package org.firstinspires.ftc.teamcode.Autonome.Teste;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Autonome.MetodeAutonoma;
import org.firstinspires.ftc.teamcode.Hardware.HardwareM;

@Autonomous(name = "Test_Brat", group = "Teste")
public class Test_Brat extends MetodeAutonoma
{
    HardwareM fer = new HardwareM();
    private ElapsedTime runtime = new ElapsedTime();

    @Override public void runOpMode(){
        fer.init(hardwareMap);
        fer.leftClaw.setPower(1);
        fer.rightClaw.setPower(.7);
        sleep(2000);

        waitForStart();
        runtime.reset();

        while(opModeIsActive() && runtime.milliseconds()<600){
            fer.brat_D.setPower(.2);
            fer.brat_S.setPower(.2);
        }
        fer.brat_S.setPower(0);
        fer.brat_D.setPower(0);

        telemetry.addData("Timp: ", "%7d", (int)runtime.seconds());

        goTo(.4, -815, fer.brat_Scripete);
        while(opModeIsActive() && fer.brat_Scripete.isBusy()){}
        fer.stopMotors(fer.brat_Scripete);

        fer.leftClaw.setPower(-1);
        fer.rightClaw.setPower(-1);

        sleep(1000);

        fer.leftClaw.setPower(1);
        fer.rightClaw.setPower(.7);

        fer.resetEncoders(fer.brat_Scripete);

        goTo(.4, 815, fer.brat_Scripete);
        while(opModeIsActive() && fer.brat_Scripete.isBusy()){}
        fer.stopMotors(fer.brat_Scripete);

        while(opModeIsActive()){}


    }
}
