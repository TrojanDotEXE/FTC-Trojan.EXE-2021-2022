package org.firstinspires.ftc.teamcode.Hardware;

import androidx.annotation.NonNull;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HardwareM extends LinearOpMode {

    public DcMotor roataStanga  = null , roataDreapta = null; //Motoare fata
    public DcMotor bratStanga   = null , bratDreapta  = null, //Motoare brat
                   bratScripete = null;
    public DcMotor carusel      = null;

    public CRServo clesteStanga = null , clesteDreapta = null; //schimbati in Servo daca folositi celelalte variante

    public BNO055IMU imu;

    public static final int MAX_SCRIPETE            =  -821 ;
    public static final int MIN_SCRIPETE            =  -8 ;

    public static final int ROTIRE90    = 1980;

    public static final int T1 = 400;
    public static final int T2 = 600;
    public static final int T3 = 1000;

    public static final int HDHEX40_TICK_COUNTS     = 1120;         //TODO: fa un Enum pt tick counts, roti, etc.
    public static final int TETRIX_TICK_COUNTS      = 1440;
    public static final int REV_COREHEX_TICK_COUNTS = 288;

    public static final double circumferintaRoata    = 11.131308365;
    public static final double circumferintaScripete = 4.328829925;
    public static final double driveGearRatio        = 1.6;

    public static final int NEVEREST40_TICKS_PER_INCH  = (int)((HDHEX40_TICK_COUNTS * driveGearRatio)/circumferintaRoata); //TODO: coment cu motor
    public static final int TETRIX_TICKS_PER_INCH      = (int)((TETRIX_TICK_COUNTS)/circumferintaRoata);
    public static final int REV_COREHEX_TICKS_PER_INCH = (int)((REV_COREHEX_TICK_COUNTS)/circumferintaRoata);
    public static final int SCRIPETE_TICKS_PER_INCH    = (int)(TETRIX_TICK_COUNTS/circumferintaScripete);

    //TODO: fa autonoma sa se opreasca dupa perioada de timp permisa

    public void init (HardwareMap hardwaremap) {

        roataStanga  = hardwaremap.get(DcMotor.class, "motorStanga");   //Motoare
        roataDreapta = hardwaremap.get(DcMotor.class, "motorDreapta");
        bratStanga   = hardwaremap.get(DcMotor.class, "motorS");
        bratDreapta  = hardwaremap.get(DcMotor.class, "motorD");
        bratScripete = hardwaremap.get(DcMotor.class, "motorScripete");
        carusel      = hardwaremap.get(DcMotor.class, "motorCaruselS");

        clesteStanga  = hardwaremap.get(CRServo.class, "leftClaw");
        clesteDreapta = hardwaremap.get(CRServo.class, "rightClaw");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        setIMUParams(parameters);

        imu = hardwaremap.get(BNO055IMU.class, "IMU");
        imu.initialize(parameters);

        set0Behaviour(DcMotor.ZeroPowerBehavior.BRAKE, roataStanga, roataDreapta, bratStanga, bratDreapta, bratScripete, carusel);
        setDirections(DcMotor.Direction.FORWARD,  roataDreapta, bratStanga, bratScripete);
        setDirections(DcMotor.Direction.REVERSE, roataStanga, bratDreapta, carusel);
        resetEncoders(roataDreapta, roataStanga, bratStanga, bratScripete);
        stopMotors();
        clesteStanga.setDirection(DcMotorSimple.Direction.REVERSE);
        //leftClaw.setDirection();
        //stopServos();
    }

    private void setDirections(DcMotor.Direction d, @NonNull DcMotor ... motors) {
        for (DcMotor m:motors)
            m.setDirection(d);
    }

    private void set0Behaviour(DcMotor.ZeroPowerBehavior mode, @NonNull DcMotor ... motors) {
        for (DcMotor m:motors)
            m.setZeroPowerBehavior(mode);
    }

    private void setIMUParams(@NonNull BNO055IMU.Parameters param) {
        param.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        param.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        param.calibrationDataFile = "BNO055IMUCalibration.json";
        param.loggingEnabled = true;
        param.loggingTag = "IMU";
        param.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
    }

    public void setEncoderMode(DcMotor.RunMode mode, @NonNull DcMotor ... motors) {
        for (DcMotor m:motors)
            m.setMode(mode);
    }

    public void stopMotors() {
        roataStanga.setPower(0);
        roataDreapta.setPower(0);
        bratStanga.setPower(0);
        bratDreapta.setPower(0);
        bratScripete.setPower(0);
        carusel.setPower(0);
    }

    public void stopMotors(@NonNull DcMotor ... motors) {
        for (DcMotor m:motors)
            m.setPower(0);
    }

    public void setWheelPowers(double p1, double p2) {
        roataStanga.setPower(p1);
        roataDreapta.setPower(p2);
    }

    public void resetEncoders(@NonNull DcMotor ... motors) {
        for(DcMotor motor:motors)
        {
            setEncoderMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER, motor);
            setEncoderMode(DcMotor.RunMode.RUN_USING_ENCODER, motor);
        }
    }

    public void restartServos() {
        clesteStanga.setPower(1);
        clesteDreapta.setPower(.8);
    }

    @Override
    public void runOpMode(){}
    public HardwareM(){}    //Constructor
}

