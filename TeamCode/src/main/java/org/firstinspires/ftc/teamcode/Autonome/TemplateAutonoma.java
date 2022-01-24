package org.firstinspires.ftc.teamcode.Autonome;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Hardware.HardwareM;

public class TemplateAutonoma extends LinearOpMode {
    HardwareM fer = new HardwareM();

    @Override
    public void runOpMode() throws InterruptedException {
        fer.init(hardwareMap);
    }

    public void drive (double power, int rotatii) {
        fer.goToPosition(power, rotatii, fer.roataStanga, fer.roataDreapta);

        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataDreapta.isBusy())) {}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);
    }

    public void turn (double power, int rotatiiStanga, int rotatiiDreapta) {
        fer.goToPosition(power, rotatiiStanga, rotatiiDreapta, fer.roataStanga, fer.roataDreapta);

        while(opModeIsActive() && (fer.roataStanga.isBusy() || fer.roataDreapta.isBusy())) {}
        fer.stopMotors(fer.roataDreapta, fer.roataStanga);
    }

    public void rotationR (double power, int rotatii) {
        fer.goToPosition(power, rotatii, fer.roataStanga);

        while(opModeIsActive() && fer.roataStanga.isBusy()){};
        fer.stopMotors(fer.roataStanga);
    }

    public void rotationS (double power, int rotatii) {
        fer.goToPosition(power, rotatii, fer.roataDreapta);

        while(opModeIsActive() && fer.roataDreapta.isBusy()){};
        fer.stopMotors(fer.roataDreapta);
    }

    public void brat (double power, int rotatii) {
        fer.goToPosition(power, rotatii, fer.brat_S, fer.brat_D);

        while(opModeIsActive() && (fer.brat_S.isBusy() || fer.brat_D.isBusy())){}
        fer.stopMotors(fer.brat_S, fer.brat_D);
    }

    public void scripete (double power, int rotatii) {
        fer.goToPosition(power, rotatii, fer.brat_S, fer.brat_D);

        while(opModeIsActive() && (fer.brat_S.isBusy() || fer.brat_D.isBusy())){}
        fer.stopMotors(fer.brat_S, fer.brat_D);
    }

    public void peria (double power, int rotatii) {
        fer.goToPosition(power, rotatii, fer.peria);

        while(opModeIsActive() && fer.peria.isBusy()) {}
        fer.stopMotors(fer.peria);
    }
}
