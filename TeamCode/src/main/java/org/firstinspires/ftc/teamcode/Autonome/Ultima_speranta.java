package org.firstinspires.ftc.teamcode.Autonome;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Hardware.HardwareM;

@Autonomous(name="AutonomaX", group="Autonome")
@Disabled
public class Ultima_speranta extends LinearOpMode {
    HardwareM fer = new HardwareM();

        // called when init button is  pressed.

        @Override
        public void runOpMode() throws InterruptedException
        {
            telemetry.addData("Mode", "waiting");
            telemetry.update();

            // wait for start button.

            waitForStart();

            telemetry.addData("Mode", "running");
            telemetry.update();

            // set both motors to 25% power.

            fer.roataStanga.setPower(-.5);
            fer.roataDreapta.setPower(-.5);

            sleep(2000);        // wait for 2 seconds.


        }

}
