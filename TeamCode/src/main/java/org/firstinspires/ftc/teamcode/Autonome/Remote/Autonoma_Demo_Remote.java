package org.firstinspires.ftc.teamcode.Autonome.Remote;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Autonome.MetodeAutonoma;
import org.firstinspires.ftc.teamcode.Hardware.HardwareM;


@Autonomous(name = "Test #3", group = "Teste")
public class Autonoma_Demo_Remote extends MetodeAutonoma
{
    HardwareM fer = new HardwareM();
    private ElapsedTime runtime = new ElapsedTime();
    private ElapsedTime runtime2 = new ElapsedTime();
    int NR;

    @Override
    public void runOpMode() throws InterruptedException {

        fer.init(hardwareMap);
        //Prinde pre-loadul
        fer.leftClaw.setPower(1);
        fer.rightClaw.setPower(.7);
        //Asteapta o secunda
        sleep(1000);

        waitForStart();
        runtime.reset();

        //intoarce-te la dreapta
        turn(.6, -3000, fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);

        //mergi in fata
        goTo(.6, -3040, fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);

        //intoarce-te la stanga
        turn(.6, 3000,fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);

        //mergi in fata
        goTo(.6, -3040, fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);

        //Ridica bratul la nivelul 3
        runtime2.reset();
        while(opModeIsActive() && (runtime2.milliseconds())<HardwareM.T3){
            fer.brat_D.setPower(.2);
            fer.brat_S.setPower(.2);
        }
        fer.brat_S.setPower(0);
        fer.brat_D.setPower(0);

        //extinde bratul
        goTo(.4, -815, fer.brat_Scripete);
        while(opModeIsActive() && fer.brat_Scripete.isBusy()){}
        fer.stopMotors(fer.brat_Scripete);

        //lasa cubul
        fer.leftClaw.setPower(-1);
        fer.rightClaw.setPower(-1);

        //retrage bratul
        fer.resetEncoders(fer.brat_Scripete);

        goTo(.4, 815, fer.brat_Scripete);
        while(opModeIsActive() && fer.brat_Scripete.isBusy()){}
        fer.stopMotors(fer.brat_Scripete);

        //mergi in spate
        goTo(.5, 3040, fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);

        //intoarce-te la dreapta
        turn(.5, -2875, fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);

        //mergi in spate pana la carusel
        goTo(.5, 2570, fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);

        //da o ratusca jos
        while(opModeIsActive() && runtime.seconds()<15){
            fer.caruselStanga.setPower(.7);
        }
        fer.caruselStanga.setPower(0);

        //intoarce-te la stanga
        turn(.6, 3000,fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);

        //mergi pana in parcare
        goTo(.5, -2250, fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);

        //TODO: camera------------------------------------------------------------------------------------------------------------------------------------------
//        TODO: brat
//
//        ridica bratul la nivelul 2
//        goTo(.7, NR, fer.brat_S, fer.brat_D);
//
//        extinde bratul
//        goTo(.9, NR, fer.brat_Scripete);
//
//        deschide clestele
//
//re
//-------------------------------------------------------------------------------------------------------------------------------------------------------
    }
}
