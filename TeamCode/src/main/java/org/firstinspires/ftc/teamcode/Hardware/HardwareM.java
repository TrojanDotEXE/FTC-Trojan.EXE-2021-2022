package org.firstinspires.ftc.teamcode.Hardware;

import androidx.annotation.NonNull;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

public class HardwareM extends LinearOpMode {

    public DcMotor roataStanga  = null , roataDreapta = null; //Motoare fata
    public DcMotor brat = null ;
    public DcMotor carusel      = null;

    private static final String TFOD_MODEL_ASSET = "FreightFrenzy_BCDM.tflite";
    private static final String[] LABELS = {
            "Ball",
            "Cube",
            "Duck",
            "Marker"
    };
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    private static final String VUFORIA_KEY = "AbcufIb/////AAABmZs6KcRHr0m2uxP9FdDNZHiJeC/zzCekip17+7u/oYc5AH4DhJyHL++2SqmTLlvYT+UismLHFociytOA1QJoXS00gFgdc0899BBB9MWVz6Jve58FlGOVtMU9PqrGP5XJDi9qz2b5HrBu3wSzRiYm/Mwkp2NEq8G1xszfl1be1WtsTGH6RC/tkTq/mxVTiHyzIu3ogXhRzRhnes9DKJlVb6kD4Tn0xxvQTF/14ipbBa0Z4BKrjEYld4U/wVT5dW95RJtMz+dExw68UNdPRLNgk5UtLSDvtgh0i2F4hyO5eyaiTZ+9ZeUUgChId0ow2Sfqox23GPIECsMf5d9oyD3fdCaDrXV/LqBFoI50j8H5svRP";


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
        brat = hardwaremap.get(DcMotor.class, "motorBratStanga");
        carusel      = hardwaremap.get(DcMotor.class, "motorCarusel");
        clesteStanga  = hardwaremap.get(CRServo.class, "leftClaw");
        clesteDreapta = hardwaremap.get(CRServo.class, "rightClaw");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        setIMUParams(parameters);

        imu = hardwaremap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        set0Behaviour(DcMotor.ZeroPowerBehavior.BRAKE, roataStanga, roataDreapta, brat, carusel);
        setDirections(DcMotor.Direction.FORWARD,  roataDreapta, brat);
        setDirections(DcMotor.Direction.REVERSE, roataStanga, carusel);
        resetEncoders(roataDreapta, roataStanga, brat);
        stopMotors();
        clesteStanga.setDirection(DcMotorSimple.Direction.REVERSE);
        //leftClaw.setDirection();
        //stopServos();
        initVuforia();
        initTfod();
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
        brat.setPower(0);
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

    private void initVuforia() {

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam");

        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    private void initTfod() {

        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.8f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 320;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
    }

}

