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
            HardwareM fer = new HardwareM();

    @Override
    public void init() {
        fer.init(hardwareMap);
        telemetry.addData("Robot" ,"Initializat");
    }
    @Override
    public void start(){runtime.reset();}

    /**
     * <h1>Gamepad 1</h1>
     * <ul><li> <b>fata spate</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - left_stick_y </li>
     *     <li> <b>stanga dreapta</b> - right_stick_x</li>
     *     <li> <b>motor carusel</b>&nbsp;&nbsp; - right_bumper (<b>fata</b>), left_bumper (<b>spate</b>)</li></ul>
     * <h3>Gamepad 2</h3>
     * <ul><li></li>
     *     <li></li>
     *     <li></li></ul>
     */

    @Override
    public void loop() {
        double fata_spate     = gamepad1.left_stick_y;
        double stanga_dreapta = gamepad1.right_stick_x;
        double left, right;
        double servo_on  = 0;
        double servo_off = .5;

        left  = Range.clip(fata_spate - stanga_dreapta,-1,1);
        right = Range.clip(fata_spate + stanga_dreapta,-1,1);

        fer.roataStanga.setPower(left);
        fer.roataDreapta.setPower(right);

        //Carusel
        if(gamepad1.right_bumper)
            fer.carusel.setPower(1);
        else if(gamepad1.left_bumper)
            fer.carusel.setPower(-1);

        fer.carusel.setPower(0);

//Gamepad 2------------------------------------------------------------------------------------------------------------------------------------------------------------

        //Brat
        if(gamepad2.b)
        {
            fer.brat_D.setPower(Range.clip(gamepad2.left_stick_y, -.5, .5));
            fer.brat_S.setPower(Range.clip(gamepad2.left_stick_y, -.5, .5));

            telemetry.addData("Slowmode", "Activat");
        }
        fer.brat_D.setPower(Range.clip(gamepad2.left_stick_y, -.7, .7));
        fer.brat_S.setPower(Range.clip(gamepad2.left_stick_y, -.7, .7));
        fer.brat_Scripete.setPower(Range.clip(gamepad2.right_stick_y, -1, 1));

        telemetry.addData("Slowmode", "Dezactivat");

        if(gamepad2.a)
        {
            fer.leftClaw.setPosition(servo_on);
            fer.rightClaw.setPosition(servo_on);
        }
        fer.leftClaw.setPosition(servo_off);
        fer.rightClaw.setPosition(servo_off);

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }
}
