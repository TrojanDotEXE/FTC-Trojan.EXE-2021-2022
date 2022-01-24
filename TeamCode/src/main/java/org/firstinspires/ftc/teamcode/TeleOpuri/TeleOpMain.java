package org.firstinspires.ftc.teamcode.TeleOpuri;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.Hardware.HardwareM;

@TeleOp(name = "TeleOP", group = "Teste")
public class TeleOpMain extends OpMode
{   HardwareM fer = new HardwareM();    //Variabile
    private ElapsedTime runtime = new ElapsedTime();
                                        //TODO: adauga un timer pe telemetry
    @Override
    public void init() {
        fer.init(hardwareMap);
        telemetry.addData("Robot" ,"Initializat");
    }
    @Override
    public void start(){runtime.reset();}

    /**
     * <h3>Gamepad 1</h3>
     * <ul><li>left_stick_y - fata spate</li>
     *     <li>right_stick_x - stanga dreapta</li>
     *     <li>right_bumper - motor carusel</li></ul>
     * <h3>Gamepad 2</h3>
     * <ul><li></li>
     *     <li></li>
     *     <li></li></ul>
     */
    // TODO: controale
    @Override
    public void loop() {
        double fata_spate = gamepad1.left_stick_y;
        double stanga_dreapta = gamepad1.right_stick_x;
        double left;
        double right;
        double servo_off = .5;
        double servo_on = 0;


        left = Range.clip(fata_spate - stanga_dreapta,-1,1);
        right = Range.clip(fata_spate + stanga_dreapta,-1,1 );

        fer.roataStanga.setPower(left);
        fer.roataDreapta.setPower(right);

        //Carusel
        if(gamepad1.right_bumper) fer.carusel.setPower(1);
            fer.carusel.setPower(0);

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

        if(gamepad2.a) fer.servoBrat.setPosition(servo_on);
            fer.servoBrat.setPosition(servo_off);

        //Peria
        if(gamepad2.left_bumper) {
                fer.peria.setPower(1);
                telemetry.addData("Perie","Spate");
            }
        else if(gamepad2.right_bumper) {
                fer.peria.setPower(-1);
                telemetry.addData("Perie","Fata");
            }
        fer.peria.setPower(0);
        telemetry.addData("Perie","Oprita");

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }
}