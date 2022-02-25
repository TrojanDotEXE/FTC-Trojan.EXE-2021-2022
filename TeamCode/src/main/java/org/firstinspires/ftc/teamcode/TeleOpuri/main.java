package org.firstinspires.ftc.teamcode.TeleOpuri;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.Hardware.HardwareM;

@TeleOp(name = "TeleOP", group = "Teste")
public class main extends OpMode
{
    private ElapsedTime runtime = new ElapsedTime();
          HardwareM fer      = new HardwareM();
          double servoMAX = 1.0, servoMIN = 0.0, pozitie = 0.0;

    @Override
    public void init() {
        fer.init(hardwareMap);
        telemetry.addData("Status: " ,"Initialized");
    }
    @Override
    public void start(){runtime.reset();}

    /**
     * <h1>Gamepad 1</h1>
     * <ul><li> <b>fata spate</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - left_stick_y </li>
     *     <li> <b>stanga dreapta</b> - right_stick_x</li>
     *     <li> <b>carusel dreapta</b> - right_bumper </li>
     *     <li> <b>carusel stanga</b>&nbsp; - left_bumper </li>
     *     <li> <b>reset encoder *</b> - x </li></ul>
     * <h1>Gamepad 2</h1>
     * <ul><li> <b>brat sus-jos</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - left_stick_y (<b>1 = jos</b>, <b>-1 = sus</b>)</li>
     *     <li> <b>scripete fata-spate</b> - right_stick_y (<b>1 = fata</b>, <b>-1 = spate</b>)</li>
     *     <li> <b>cleste deschidere</b>&nbsp;&nbsp; - left_bumper</li>
     *     <li> <b>slowmode brat</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - b</li></ul></ul>
     */

    @Override
    public void loop() {
        double fata_spate     = gamepad1.left_stick_y;
        double stanga_dreapta = gamepad1.right_stick_x;
        double left, right;

        left  = Range.clip(fata_spate - stanga_dreapta,-1,1);
        right = Range.clip(fata_spate + stanga_dreapta,-1 ,1);

        fer.roataStanga.setPower(left);
        fer.roataDreapta.setPower(right);

        while(gamepad1.dpad_left) {
            fer.roataStanga.setPower(-.7);
            fer.roataDreapta.setPower(.7);
            }
        while(gamepad1.dpad_right) {
            fer.roataStanga.setPower(.7);
            fer.roataDreapta.setPower(-.7);
        }

        //Carusel
        while(gamepad1.right_bumper)
            fer.caruselDreapta.setPower(-.85);
        while (gamepad1.left_bumper)
            fer.caruselStanga.setPower(.85);

        fer.caruselDreapta.setPower(0);
        fer.caruselStanga.setPower(0);

//Gamepad 2------------------------------------------------------------------------------------------------------------------------------------------------------------
        //Brat
        while(gamepad2.b){
            fer.brat_D.setPower(Range.clip(gamepad2.left_stick_y, -.3, .3));
            fer.brat_S.setPower(Range.clip(gamepad2.left_stick_y, -.3, .3));

        }
        fer.brat_D.setPower(Range.clip(gamepad2.left_stick_y, -.5, .5));
        fer.brat_S.setPower(Range.clip(gamepad2.left_stick_y, -.5, .5));
        fer.brat_Scripete.setPower(Range.clip(gamepad2.right_stick_y, -1, 1));

        //Cleste
        if(gamepad2.right_bumper) {
            fer.leftClaw.setPower(-1);
            fer.rightClaw.setPower(-1 );
        }
        else {
            fer.leftClaw.setPower(1);
            fer.rightClaw.setPower(.8);
        }

        if(gamepad1.x)
            fer.resetEncoders(fer.roataDreapta, fer.roataStanga, fer.brat_S, fer.brat_Scripete);

        telemetry.addData("Left Position: ", "%7d", fer.roataStanga.getCurrentPosition());
        telemetry.addData("Right Position: ", "%7d", fer.roataDreapta.getCurrentPosition());
        telemetry.addData("Arm Position: ", "%7d", fer.brat_S.getCurrentPosition());
        telemetry.addData("Scripete Position: ", "%7d", fer.brat_Scripete.getCurrentPosition());

        telemetry.addData("Run Time: ", "%7d", (int)runtime.seconds());
    }
}
