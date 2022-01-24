package org.firstinspires.ftc.teamcode.TeleOpuri.Altele;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.Hardware.HardwareM;

@TeleOp(name = "TeleOP", group = "Other")
@Disabled
public class TeleOp_Cleste extends OpMode{

        HardwareM fer = new HardwareM();    //Variabile
        //TODO: adauga un timer pe telemetry
        @Override
        public void init()
        {
            fer.init(hardwareMap);
            telemetry.addData("Robot" ,"Initializat");
        }

        @Override
        public void loop()
        {
            //Gamepad 1 //TODO: controale

            double fata_spate = Range.clip(gamepad1.left_stick_y, -1, 1);
            double stanga_dreapta = Range.clip(gamepad1.right_stick_x, -1, 1);
            double left;
            double right;

            left = fata_spate - stanga_dreapta;
            right = fata_spate + stanga_dreapta;

            fer.roataStanga.setPower(left);
            fer.roataDreapta.setPower(right);

            //Carusel
            if(gamepad1.right_bumper) fer.carusel.setPower(1);      //TODO: directie corespondenta cu bumperul
            else if(gamepad1.left_bumper) fer.carusel.setPower(-1);
            else fer.carusel.setPower(0);

            //Gamepad 2

            //Brat
            if(gamepad2.b) {
                fer.brat_D.setPower(Range.clip(gamepad2.left_stick_y, -.5, .5));
                fer.brat_S.setPower(Range.clip(gamepad2.left_stick_y, -.5, .5));
                telemetry.addData("Slowmode", "Activat");
            }
            fer.brat_D.setPower(Range.clip(gamepad2.left_stick_y, -.7, .7));
            fer.brat_S.setPower(Range.clip(gamepad2.left_stick_y, -.7, .7));
            fer.brat_Scripete.setPower(Range.clip(gamepad2.right_stick_y, -1, 1));
            telemetry.addData("Slowmode", "Dezactivat");

            if(gamepad2.a) fer.servoBrat.setPosition(.5);
            else fer.servoBrat.setPosition(0);

//        if(gamepad2.y) {
//            fer.goToPosition(fer.SCRIPETE_ROTATION, 1, fer.brat_A); //TODO: lungimea sforii
//
//            while(fer.brat_A.isBusy()) {}
//            fer.brat_A.setPower(0);
//        }

            //Peria
            if(gamepad2.left_bumper) {
                fer.peria.setPower(1);
                telemetry.addData("Perie","Spate");
            }
            else if(gamepad2.right_bumper) {
                fer.peria.setPower(-1);
                telemetry.addData("Perie","Fata");
            }
            else {
                fer.peria.setPower(0);
                telemetry.addData("Perie","Oprita");
            }

            telemetry.update();
        }
    }

