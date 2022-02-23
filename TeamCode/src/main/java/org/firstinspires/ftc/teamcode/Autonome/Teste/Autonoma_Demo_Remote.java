package org.firstinspires.ftc.teamcode.Autonome.Teste;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
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
        fer.stopServos();
        sleep(2000);

        waitForStart();
        runtime.reset();

        //mergi in fata
        goTo(.5, -730, fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){
            fer.leftClaw.setPower(.8);
            fer.rightClaw.setPower(.8);}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);

        //intoarcete cu spatele catre carusel
        turn(.7, -2360, fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){
            fer.leftClaw.setPower(.8);
            fer.rightClaw.setPower(.8);
        }
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);

        //mergi in spate
        goTo(.5, 2850, fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){
        fer.leftClaw.setPower(.8);
        fer.rightClaw.setPower(.8);
        }
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);

        turn(.5, 2100, fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);

        goTo(.5, 2200, fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);

        //da o ratusca jos
        while(opModeIsActive() && runtime.seconds()<15)
            fer.caruselStanga.setPower(.7);
        fer.caruselStanga.setPower(0);

        goTo(.5, -2430, fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);

//        intoarcete in stanga
        turn(.8, NR, fer.roataStanga, fer.roataDreapta);

//        mergi in fata ca sa te parchezi
        goTo(.8, NR, fer.roataStanga, fer.roataDreapta);

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
