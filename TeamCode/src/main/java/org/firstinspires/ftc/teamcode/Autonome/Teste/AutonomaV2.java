package org.firstinspires.ftc.teamcode.Autonome.Teste;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Autonome.MetodeAutonoma;
import org.firstinspires.ftc.teamcode.Hardware.HardwareM;

@Autonomous(name = "Auto V2", group = "Teste")
public class AutonomaV2 extends MetodeAutonoma {

    HardwareM fer = new HardwareM();
    private ElapsedTime runtime = new ElapsedTime();

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
        goTo(.5, -2000, fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);

        //intoarcete cu spatele catre carusel
        turn(.6, -3900,fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);

        //mergi in spate
        goTo(.5, 1810, fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);

        turn(.6,-1000,fer.roataStanga,fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);

        goTo(.5, 1500, fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);

        //da o ratusca jos
        while(opModeIsActive() && runtime.seconds()<10){
            fer.caruselDreapta.setPower(-.7);
        }
        fer.caruselDreapta.setPower(0);

        turn(.5, 3600, fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);

        goTo(.5, -2100, fer.roataStanga, fer.roataDreapta);
        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataStanga.isBusy())){}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);
    }
}
