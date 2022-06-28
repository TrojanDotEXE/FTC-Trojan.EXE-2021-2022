package org.firstinspires.ftc.teamcode.Hardware;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.HardwareM;

public class MetodeAutonoma extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {}

    public void goTo(double power, int rotatii, @NonNull DcMotor... motors){
        for(DcMotor motor:motors)
        {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setTargetPosition(rotatii);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setPower(power);
        }
    }

    public void goTo(double power, int rotatii1, int rotatii2, DcMotor motor1, DcMotor motor2){
        goTo(power, rotatii1, motor1);
        goTo(power, rotatii2, motor2);
    }

    public void goToV(double velocity, int rotatii, @NonNull DcMotorEx... motors){
        for(DcMotorEx motor:motors)
        {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setTargetPosition(rotatii);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setVelocity(velocity);
        }
    }

    public void goToV(double velocity, int rotatii1, int rotatii2, DcMotorEx motor1, DcMotorEx motor2){
        goToV(velocity, rotatii1, motor1);
        goToV(velocity, rotatii2, motor2);
    }

    public void goToVcm(double velocity, int cm, @NonNull DcMotorEx... motors){
        for(DcMotorEx motor:motors)
        {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setTargetPosition((int)(cm * HardwareM.rotPerCM));
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setVelocity(velocity);
        }
    }

    public void goToVcm(double velocity, double cm1, int cm2, DcMotorEx motor1, DcMotorEx motor2){
        goToV(velocity, (int)(cm1 * HardwareM.rotPerCM), motor1);
        goToV(velocity, (int)(cm2 *HardwareM.rotPerCM), motor2);
    }

    //rotatii+ => rotire dreapta
    //rotatii- => rotire stanga
    public void turn (double power, int rotatii, @NonNull DcMotor roataStanga, @NonNull DcMotor roataDreapta) {
        roataStanga.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        roataStanga.setTargetPosition(rotatii);
        roataStanga.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        roataStanga.setPower(power);
        roataDreapta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        roataDreapta.setTargetPosition(-rotatii);
        roataDreapta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        roataDreapta.setPower(power);
    }

    public void turnV (double velocity, int rotatii, @NonNull DcMotorEx roataStanga, @NonNull DcMotorEx roataDreapta) {
        roataStanga.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        roataStanga.setTargetPosition(rotatii);
        roataStanga.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        roataStanga.setVelocity(velocity);
        roataDreapta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        roataDreapta.setTargetPosition(-rotatii);
        roataDreapta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        roataDreapta.setVelocity(velocity);
    }

    //nr rotatii encoder
    public void displayEncoder(@NonNull DcMotor motor){
        while(motor.isBusy())
        {
            telemetry.addData("Left Position / Target :", "%7d / %7d",
                    motor.getCurrentPosition(), motor.getTargetPosition());
            telemetry.update();
        }
    }

    //nr rotatii 2 encodere
    public void displayEncoders(@NonNull DcMotor motorStanga, DcMotor motorDreapta) {
        while (motorStanga.isBusy() || motorDreapta.isBusy()) {
            telemetry.addData("Left Position / Target :", "%7d / %7d",
                    motorStanga.getCurrentPosition(), motorStanga.getTargetPosition());
            telemetry.addData("Right Position / Target :", "%7d / %7d",
                    motorDreapta.getCurrentPosition(), motorDreapta.getTargetPosition());
            telemetry.update();
        }
    }

    public boolean isRunning(ElapsedTime runtime, @NonNull DcMotor... motors){
        boolean isMotorBusy = false;
        for (DcMotor motor: motors) {
            if (motor.isBusy()) {
                isMotorBusy = true;
                break;
            }
        }
        return opModeIsActive() && runtime.seconds() < 30 && isMotorBusy;
    }
}
