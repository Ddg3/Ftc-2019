package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot
{
    public final double COUNTS_PER_REV = 1120; //AndyMark 40:1 motor
    public final double WHEEL_DIAMETER = 4; //Wheel diameter of 4 inches
    public final double DRIVE_GEAR_REDUCTION = 0.75; //No gear reduction applied
    public final double COUNTS_PER_INCH = (COUNTS_PER_REV * DRIVE_GEAR_REDUCTION)/(WHEEL_DIAMETER * Math.PI);

    private HardwareMap hardwareMap = null;
    private TeleOpMode teleOpMode = null;
    private AutoOpMode autoOpMode = null;
    private ElapsedTime runtime = null;
    public Telemetry telemetry = null;

    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    //private static Motor testMotor = null;

    private DriveMode driveMode = null;

    enum DriveMode
    {
        Arcade,
        Tank,
        Mecanum,
        Swerve
    }

    public Robot(HardwareMap hardwareMap, ElapsedTime runtime, Telemetry telemetry)
    {
        this.hardwareMap = hardwareMap;
        this.runtime = runtime;
        this.telemetry = telemetry;
        //this.autoOpMode = opMode;
        //this.teleOpMode = tOpMode;

        driveMode = DriveMode.Arcade;

        initHardware();
    }

    public void switchDrive()
    {
        switch (driveMode)
        {
            case Arcade:
                driveMode = DriveMode.Tank;
                break;
            case Tank:
                driveMode = DriveMode.Mecanum;
                break;
            case Mecanum:
                driveMode = DriveMode.Swerve;
                break;
            case Swerve:
                driveMode = DriveMode.Arcade;
                break;
        }
    }
    public void setDrive(DriveMode driveMode)
    {
        switch(driveMode)
            {
            case Arcade:
                //leftBackDrive.setMaster(leftFrontDrive);
                //rightBackDrive.setMaster(rightFrontDrive);
                break;
            case Tank:
                //leftBackDrive.setMaster(leftFrontDrive);
                //rightBackDrive.setMaster(rightFrontDrive);
                break;
            case Mecanum:

                break;
            case Swerve:

                break;
        }

    }

    public void initHardware()
    {
        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "leftFront");
        leftBackDrive = hardwareMap.get(DcMotor.class, "leftBack");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFront");
        rightBackDrive = hardwareMap.get(DcMotor.class, "rightBack");

        //testMotor = (Motor)hardwareMap.get(DcMotor.class, "TEST_MOTOR");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);


    }

    public void setDriveMode(DcMotor.RunMode mode)
    {
        rightFrontDrive.setMode(mode);
        rightBackDrive.setMode(mode);
        leftFrontDrive.setMode(mode);
        leftBackDrive.setMode(mode);
    }

    public void setDrivePower(double leftFrontPower, double leftBackPower, double rightFrontPower, double rightBackPower)
    {
        rightFrontDrive.setPower(rightFrontPower);
        rightBackDrive.setPower(rightBackPower);
        leftFrontDrive.setPower(leftFrontPower);
        leftBackDrive.setPower(leftBackPower);
    }

    /*public void setDrivePowerFor()
    {

    }*/

    /*public DcMotor setFollower(DcMotor master, DcMotor slave)
    {

    }*/

    public void testEncoder()
    {
        int targetRight = (int)(5 * COUNTS_PER_INCH);
        int targetLeft = (int)(5 * COUNTS_PER_INCH);

        double rightFrontDistance = rightFrontDrive.getCurrentPosition() + targetRight;
        double rightBackDistance = rightBackDrive.getCurrentPosition() + targetRight;
        double leftFrontDistance = leftFrontDrive.getCurrentPosition() + targetLeft;
        double leftBackDistance = leftBackDrive.getCurrentPosition() + targetLeft;

        rightFrontDrive.setTargetPosition((int)rightFrontDistance);
        rightBackDrive.setTargetPosition((int)rightBackDistance);
        leftFrontDrive.setTargetPosition((int)leftFrontDistance);
        leftBackDrive.setTargetPosition((int)leftBackDistance);

        telemetry.addData("Start Position: ", leftBackDrive.getCurrentPosition());
        telemetry.addData("Target Position: ", leftBackDrive.getTargetPosition());
        telemetry.update();

        setDriveMode(DcMotor.RunMode.RUN_TO_POSITION);

        setDrivePower(1, 1, 1, 1);


        /*while((rightFront.isBusy() && rightBack.isBusy() && leftFront.isBusy() && leftBack.isBusy()) &&
                timeOut > runtime.time() && opMode.opModeIsActive()){
            opMode.idle();
            telemetry.addData("State: ", "In Loop");
            telemetry.addData("Target Position: ", leftBack.getTargetPosition());
            telemetry.addData("Current Position: ",  leftBack.getCurrentPosition());
            telemetry.update();
        }*/

        //resetTargets();
        //setDrivePower(0, 0, 0, 0);
        setDriveMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public  DriveMode getDriveMode()
    {
        return driveMode;
    }

    public  void setDriveMode(DriveMode driveMode)
    { this.driveMode = driveMode;
    }
    public  ElapsedTime getRuntime() {
        return runtime;
    }

    public  void setRuntime(ElapsedTime runtime) {
        this.runtime = runtime;
    }

    public Telemetry getTelemetry() {
        return telemetry;
    }

    public void setTelemetry(Telemetry telemetry) {
        this.telemetry = telemetry;
    }
    public  DcMotor getLeftFrontDrive() {
        return leftFrontDrive;
    }

    public void setLeftFrontDrive(Motor leftFrontDrive) {
        this.leftFrontDrive = leftFrontDrive;
    }

    public  DcMotor getLeftBackDrive() {
        return leftBackDrive;
    }

    public  void setLeftBackDrive(Motor leftBackDrive) {
        this.leftBackDrive = leftBackDrive;
    }

    public  DcMotor getRightFrontDrive() {
        return rightFrontDrive;
    }

    public  void setRightFrontDrive(Motor rightFrontDrive) {
        this.rightFrontDrive = rightFrontDrive;
    }

    public  DcMotor getRightBackDrive() {
        return rightBackDrive;
    }

    public  void setRightBackDrive(Motor rightBackDrive) {
        this.rightBackDrive = rightBackDrive;
    }
}
