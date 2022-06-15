package org.firstinspires.ftc.teamcode.Hardware;

import androidx.annotation.NonNull;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

public class HardwareM extends LinearOpMode {

    public DcMotor wheelLeftFront  = null;
    public DcMotor wheelRightFront = null;
    public DcMotor wheelLeftBack   = null;
    public DcMotor wheelRightBack  = null;
    public DcMotor brat1           = null;
    public DcMotor brat2           = null;
    public DcMotor carusel         = null;
    public CRServo clesteStanga    = null;
    public CRServo clesteDreapta   = null;
    public BNO055IMU imu           = null;

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    private static final String VUFORIA_KEY = "AbcufIb/////AAABmZs6KcRHr0m2uxP9FdDNZHiJeC/zzCekip17+7u/oYc5AH4DhJyHL++2SqmTLlvYT+UismLHFociytOA1QJoXS00gFgdc0899BBB9MWVz6Jve58FlGOVtMU9PqrGP5XJDi9qz2b5HrBu3wSzRiYm/Mwkp2NEq8G1xszfl1be1WtsTGH6RC/tkTq/mxVTiHyzIu3ogXhRzRhnes9DKJlVb6kD4Tn0xxvQTF/14ipbBa0Z4BKrjEYld4U/wVT5dW95RJtMz+dExw68UNdPRLNgk5UtLSDvtgh0i2F4hyO5eyaiTZ+9ZeUUgChId0ow2Sfqox23GPIECsMf5d9oyD3fdCaDrXV/LqBFoI50j8H5svRP";

    private static final String TFOD_MODEL_ASSET = "FreightFrenzy_BCDM.tflite";
    private static final String[] LABELS = {
            "Ball",
            "Cube",
            "Duck",
            "Marker"
    };

    public static final int ROTIRE90    = 1980;

    public static final int T1 = 400;
    public static final int T2 = 600;
    public static final int T3 = 1000;

    public void initialize(HardwareMap hardwaremap) {
        wheelLeftFront  = hardwaremap.get(DcMotor.class  , "motorStangaFata");
        wheelRightFront = hardwaremap.get(DcMotor.class  , "motorDreaptaFata");
        wheelLeftBack   = hardwaremap.get(DcMotor.class  , "motorStangaSpate");
        wheelRightBack  = hardwaremap.get(DcMotor.class  , "motorDreaptaSpate");
        brat1           = hardwaremap.get(DcMotor.class  , "motorBrat1");
        brat2           = hardwaremap.get(DcMotor.class  , "motorBrat2");
        carusel         = hardwaremap.get(DcMotor.class  , "motorCarusel");
        clesteStanga    = hardwaremap.get(CRServo.class  , "leftClaw");
        clesteDreapta   = hardwaremap.get(CRServo.class  , "rightClaw");
        imu             = hardwaremap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        setIMUParams(parameters);
        imu.initialize(parameters);

        set0Behaviour(DcMotor.ZeroPowerBehavior.BRAKE,
                wheelLeftBack, wheelLeftFront,
                wheelRightBack, wheelRightFront, carusel
                );
        setDirections(DcMotor.Direction.FORWARD,
                wheelRightBack, wheelRightFront,
                brat2
                );
        setDirections(DcMotor.Direction.REVERSE,
                wheelLeftBack, wheelLeftFront,
                carusel,brat1
                );
        resetEncoders(wheelRightBack, wheelLeftBack,
                wheelRightFront, wheelLeftFront
                );  //,
                    //brat1, brat2
        stopMotors();
        clesteStanga.setDirection(DcMotorSimple.Direction.REVERSE);
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
        wheelLeftBack.setPower(0);
        wheelRightBack.setPower(0);
        wheelLeftFront.setPower(0);
        wheelRightFront.setPower(0);
        brat1.setPower(0);
        carusel.setPower(0);
    }

    public void stopMotors(@NonNull DcMotor ... motors) {
        for (DcMotor m:motors)
            m.setPower(0);
    }

    public void setWheelPowers(double p1, double p2) {
        wheelLeftBack.setPower(p1);
        wheelLeftFront.setPower(p1);
        wheelRightBack.setPower(p2);
        wheelRightFront.setPower(p2);
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
    public HardwareM(){}

//    private void initVuforia() {
//
//        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
//
//        parameters.vuforiaLicenseKey = VUFORIA_KEY;
//        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam");
//
//        vuforia = ClassFactory.getInstance().createVuforia(parameters);
//    }
//
//    private void initTfod() {
//
//        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
//                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//
//        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
//        tfodParameters.minResultConfidence = 0.8f;
//        tfodParameters.isModelTensorFlow2 = true;
//        tfodParameters.inputSize = 320;
//        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
//        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
//    }

}

