package org.firstinspires.ftc.teamcode.Autonome;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

    @Autonomous(name="Zach Plays With Auton", group="Freight Frenzy")
    //@Disabled
public class Ultima_speranta extends LinearOpMode {

        DcMotor leftMotor;
        DcMotor rightMotor;

        // called when init button is  pressed.

        @Override
        public void runOpMode() throws InterruptedException
        {
            leftMotor = hardwareMap.dcMotor.get("motorStanga");
            rightMotor = hardwareMap.dcMotor.get("motorDreapta");

            leftMotor.setDirection(DcMotor.Direction.REVERSE);

            telemetry.addData("Mode", "waiting");
            telemetry.update();

            // wait for start button.

            waitForStart();

            telemetry.addData("Mode", "running");
            telemetry.update();

            // set both motors to 25% power.

            leftMotor.setPower(0.25);
            rightMotor.setPower(0.25);

            sleep(2000);        // wait for 2 seconds.

            // set both motors to opposite power, turning left

            leftMotor.setPower(0.25);
            rightMotor.setPower(-0.25);

            sleep(2000);        // wait for 2 seconds.

            // set both motors to 25% power.

            leftMotor.setPower(0.25);
            rightMotor.setPower(0.25);

            sleep(2000);        // wait for 2 seconds.

            // set motor power to zero to stop motors.

            leftMotor.setPower(0.0);
            rightMotor.setPower(0.0);
        }

}
