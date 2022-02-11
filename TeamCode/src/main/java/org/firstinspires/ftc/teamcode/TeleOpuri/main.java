package org.firstinspires.ftc.teamcode.TeleOpuri;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.Hardware.HardwareM;

@TeleOp(name = "TeleOP", group = "Teste")
public class  main extends OpMode
{
    //private ElapsedTime runtime = new ElapsedTime();
          HardwareM fer      = new HardwareM();

    @Override
    public void init() {
        fer.init(hardwareMap);
        telemetry.addData("Status: " ,"Initialized");
    }
//    @Override
//    public void start(){runtime.reset();}

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
        right = Range.clip(fata_spate + stanga_dreapta,-1,1);

        fer.roataStanga.setPower(left);
        fer.roataDreapta.setPower(right);

        //Carusel
        if(gamepad1.right_bumper)
            fer.caruselDreapta.setPower(1);
        if(gamepad1.left_bumper)
            fer.caruselStanga.setPower(1);

        fer.caruselDreapta.setPower(0);
        fer.caruselStanga.setPower(0);

        //Reset encoders
        //if(gamepad1.x)
            //fer.resetEncoders();

//Gamepad 2------------------------------------------------------------------------------------------------------------------------------------------------------------
        //Brat
        if(gamepad2.b)
        {
            fer.brat_D.setPower(Range.clip(gamepad2.left_stick_y, -.5, .5));
            fer.brat_S.setPower(Range.clip(gamepad2.left_stick_y, -.5, .5));

            telemetry.addData("Slowmode: ", "Activat");
        }
        fer.brat_D.setPower(Range.clip(gamepad2.left_stick_y, -.7, .7));
        fer.brat_S.setPower(Range.clip(gamepad2.left_stick_y, -.7, .7));
        fer.brat_Scripete.setPower(Range.clip(gamepad2.right_stick_y, -1, 1));

        if(gamepad2.left_bumper) {
            fer.leftClaw.setPower(.5);
            fer.rightClaw.setPower(.5);
        }
        else if(gamepad2.right_bumper) {
            fer.leftClaw.setPower(-.5);
            fer.rightClaw.setPower(-.5);
        }
        fer.leftClaw.setPower(0);
        fer.rightClaw.setPower(0);

        telemetry.addData("Slowmode: ", "Dezactivat");

        //telemetry.addData("Roata stanga", fer.roataStanga.getCurrentPosition());
        //telemetry.addData("Roata dreapta", fer.roataDreapta.getCurrentPosition());

        telemetry.addData("Run Time: ", getRuntime());
    }
}
