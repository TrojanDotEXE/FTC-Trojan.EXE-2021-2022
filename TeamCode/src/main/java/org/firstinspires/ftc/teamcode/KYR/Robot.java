package org.firstinspires.ftc.teamcode.KYR;
import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.drives.FourWheelDrive;

public class Robot extends LinearOpMode {

    public ElapsedTime t1 = new ElapsedTime();
    public FourWheelDrive drivetrain;
    public CRServo cleste = null;

    public void initialize(@NonNull HardwareMap hardwaremap) {
        left = hardwaremap.get(DcMotor.class, "left");
        right = hardwaremap.get(DcMotor.class, "right");
        cleste = hardwaremap.get(CRServo.class, "cleste");

        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left.setDirection(DcMotorSimple.Direction.FORWARD);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setDirection(DcMotorSimple.Direction.REVERSE);
        left.setPower(0);
        right.setPower(0);
        cleste.setDirection(DcMotorSimple.Direction.FORWARD);
        cleste.setPower(0);
    }

    public void move( double power ) {
        drive( power, 0 );
    }

    public void turn( double power ) {
        drive( 0, power );
    }

    public void drive( double move, double turn ) {
        double leftPower = move + turn;
        double rightPower = move - turn;

        setMotorPower(leftPower, rightPower);
    }

    public void move(double power, int time) {
        t1.reset();
        while ((int)(t1.seconds()) > time) {
            left.setPower(power);
            right.setPower(power);
        }
        left.setPower(0);
        right.setPower(0);
    }

    public void turn(double power, int time) {
        t1.reset();
        while ((int)(t1.seconds()) > time) {
            left.setPower(power);
            right.setPower(-power);
        }
        left.setPower(0);
        right.setPower(0);
    }

    public void cleste(double power, int time) {
        t1.reset();
        while ((int)(t1.seconds()) > time) {
            cleste.setPower(power);
        }
        cleste.setPower(0);
    }

    public void setMotorPower(double leftPower, double rightPower) {
        left.setPower(leftPower);
        right.setPower(rightPower);
    }

    public void sendRandomNumber() {
        telemetry.addData("Mesaj: ", Math.random()*100);
    }

    Robot () {}
    @Override
    public void runOpMode() throws InterruptedException {}
}
