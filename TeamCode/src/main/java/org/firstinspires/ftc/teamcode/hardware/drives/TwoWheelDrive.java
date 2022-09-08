package org.firstinspires.ftc.teamcode.hardware.drives;
import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TwoWheelDrive implements Drivetrain{
    public DcMotorEx left;
    public DcMotorEx right;



    @Override
    public void move(double power) {

    }

    @Override
    public void turn(double power) {

    }

    @Override
    public void stop() {

    }

    @Override
    public void drive(double move, double turn) {

    }

    @Override
    public int convertDistTicks(double distanceToMove, double circumference) {
        return 0;
    }

    @Override
    public State getState() {
        return null;
    }

    public TwoWheelDrive( HardwareMap hardwareMap ) {
        this(hardwareMap, "backLeft", "backRight" );
    }

    public TwoWheelDrive(HardwareMap hardwareMap, String leftName, String rightName) {
        setUpMotors( hardwareMap, leftName, rightName);
    }

    private void setUpMotors(HardwareMap hardwareMap, String leftName, String rightName) {
        left = hardwareMap.get(DcMotorEx.class, leftName);
        right= hardwareMap.get(DcMotorEx.class, rightName);
        setMotorDirections(FORWARD, REVERSE);
        setZeroPowerBehavior(BRAKE, BRAKE);
    }

    public void setMotorDirections(Direction leftD, Direction rightD) {
        left.setDirection(leftD);
        right.setDirection(rightD);
    }

    public void setZeroPowerBehavior(ZeroPowerBehavior leftB, ZeroPowerBehavior rightB) {
        left.setZeroPowerBehavior(leftB);
        right.setZeroPowerBehavior(rightB);
    }
}
