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
    private ElapsedTime runtime2 = new ElapsedTime();

    @Override public void runOpMode(){
        fer.init(hardwareMap);
        //PRinde pre-loadul
        fer.leftClaw.setPower(1);
        fer.rightClaw.setPower(.7);
        //Asteapta o secunda
        sleep(1000);

        waitForStart();
        runtime.reset();

        //Ridica bratul la stratul 3
        while(opModeIsActive() && runtime.milliseconds()<HardwareM.T3){
            fer.brat_D.setPower(.2);
            fer.brat_S.setPower(.2);
        }
        fer.brat_S.setPower(0);
        fer.brat_D.setPower(0);

        //Mergi in fata pana la s.h.
        goTo(.4, -1300, fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataDreapta.isBusy()));
        fer.stopMotors(fer.roataStanga, fer.roataDreapta);

        //lasa obiectul in s.h.
        fer.leftClaw.setPower(-1);
        fer.rightClaw.setPower(-1);

        //mergi in spate la pozitia initiala
        goTo(.4, 1300, fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataDreapta.isBusy()));
        fer.stopMotors(fer.roataStanga, fer.roataDreapta);

        //inchide clestele
        fer.leftClaw.setPower(1);
        fer.rightClaw.setPower(.7);

        //Reseteaza al 2-lea timer
        runtime2.reset();

        //Coboara bratul
        while(opModeIsActive() && runtime2.milliseconds()<HardwareM.T3-100){
            fer.brat_D.setPower(-.2);
            fer.brat_S.setPower(-.2);
        }
        fer.brat_S.setPower(0);
        fer.brat_D.setPower(0);

        //Asteapta pana se inchide op modul+
        while(opModeIsActive()){}


    }
}
