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
    int NR;

    @Override
    public void runOpMode() throws InterruptedException {

        fer.init(hardwareMap);
        fer.leftClaw.setPower(1);
        fer.rightClaw.setPower(.7);
        sleep(2000);

        waitForStart();
        runtime.reset();

        while(opModeIsActive() && runtime.milliseconds()<100){
            fer.brat_D.setPower(.8);
            fer.brat_S.setPower(.8);
        }
        fer.brat_S.setPower(0);
        fer.brat_D.setPower(0);

        //mergi in fata
        goTo(.5, -3040, fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);

        //intoarcete cu spatele catre carusel
        turn(.6, -4050,fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);

        //mergi in spate
        goTo(.5, 3200, fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);

        turn(.5, 2875, fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);

        goTo(.5, 2570, fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);

        //da o ratusca jos
        while(opModeIsActive() && runtime.seconds()<15){
            fer.caruselStanga.setPower(.7);
        }
        fer.caruselStanga.setPower(0);

        goTo(.5, -2250, fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);

//        intoarcete in stanga
//        turn(.8, NR, fer.roataStanga, fer.roataDreapta);

//        mergi in fata ca sa te parchezi
//        goTo(.8, NR, fer.roataStanga, fer.roataDreapta);

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
