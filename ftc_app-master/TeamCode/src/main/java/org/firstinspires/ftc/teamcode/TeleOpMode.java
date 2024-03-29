/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Zatch TeleOp", group="Iterative Opmode")

//@Disabled
public class TeleOpMode extends OpMode
{
    private ElapsedTime runtime = null;
    private Robot robot = null;

    double leftPower;
    double rightPower;
    double drive;
    double turn;

    boolean yDown = false;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init()
    {
        telemetry.addData("Status", "Started Initialization");
        runtime = new ElapsedTime();
        robot = new Robot(hardwareMap, runtime, telemetry);
        robot.telemetry.addData("Status", "Finished Initialization");
        /*//robot.getTelemetry().addData("Status", "Initialized");
        //telemetry.addData("Status", "Initialized");

        leftFrontDrive  = hardwareMap.get(DcMotor.class, "111leftFront");
        leftBackDrive = hardwareMap.get(DcMotor.class, "leftBack");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFront");
        rightBackDrive = hardwareMap.get(DcMotor.class, "rightBack");
        //Robot.getTelemetry().addLine("BRUHHHHHHHHHHHHHH");

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        //leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        //rightDrive = hardwareMap.get(DcMotor.class, "right_drive");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        //leftDrive.setDirection(DcMotor.Direction.FORWARD);
        //rightDrive.setDirection(DcMotor.Direction.REVERSE);

        // Tell the driver that initialization is complete.
        //robot.getTelemetry().addData("Status", "Initialized");*/

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        //leftFrontDrive  = hardwareMap.get(DcMotor.class, "leftFront");
        //rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFront");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        //leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        //rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);

        // Tell the driver that initialization is complete.
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop()
    {

    }
    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start()
    {
        //robot.getRuntime().reset();
        runtime.reset();
    }
    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop()
    {
        // Setup a variable for each drive wheel to save power level for telemetry

        // Choose to drive using either Tank Mode, or POV Mode
        // Comment out the method that's not used.  The default below is POV.

        // POV Mode uses left stick to go forward, and right stick to turn.
        // - This uses basic math to combine motions and is easier to drive straight.
        switch(robot.getDriveMode())
        {
            case Arcade:
                drive = -gamepad1.right_stick_x;
                turn = gamepad1.left_stick_y;
                leftPower = Range.clip(drive + turn, -1.0, 1.0);
                rightPower = Range.clip(drive - turn, -1.0, 1.0);

                robot.getLeftFrontDrive().setPower(leftPower);
                robot.getLeftBackDrive().setPower(leftPower);
                robot.getRightBackDrive().setPower(rightPower);
                robot.getRightFrontDrive().setPower(rightPower);
                break;

            case Tank:
                leftPower  = gamepad1.left_stick_y ;
                rightPower = -gamepad1.right_stick_y ;

                robot.getLeftFrontDrive().setPower(leftPower);
                robot.getLeftBackDrive().setPower(leftPower);
                robot.getRightBackDrive().setPower(rightPower);
                robot.getRightFrontDrive().setPower(rightPower);
                break;
            case Mecanum:
                double rightX = gamepad1.right_stick_x;
                double leftX = gamepad1.left_stick_x;
                double leftY = -gamepad1.left_stick_y;

                double robotSpeed = Math.pow(leftX, 2) + Math.pow(leftY, 2);
                robotSpeed = Math.sqrt(robotSpeed);

                double changeDirectionSpeed = -rightX;

                double desiredRobotAngle = Math.atan2(-leftX, leftY);

                double frontLeft = robotSpeed * Math.sin(-desiredRobotAngle + (Math.PI / 4)) - changeDirectionSpeed;
                double frontRight = robotSpeed * Math.cos(-desiredRobotAngle + (Math.PI / 4)) + changeDirectionSpeed;
                double backLeft = robotSpeed * Math.cos(-desiredRobotAngle + (Math.PI / 4)) - changeDirectionSpeed;
                double backRight = robotSpeed * Math.sin(-desiredRobotAngle + (Math.PI / 4)) + changeDirectionSpeed;

                robot.getLeftFrontDrive().setPower(frontLeft);
                robot.getLeftBackDrive().setPower(backLeft);
                robot.getRightBackDrive().setPower(backRight);
                robot.getRightFrontDrive().setPower(frontRight);
                break;
        }

        if(gamepad1.y)
        {
            if(!yDown)
            {
                robot.switchDrive();
                yDown = true;
            }
        }
        else if(yDown)
        {
            yDown = false;
        }

        // Tank Mode uses one stick to control each wheel.
        // - This requires no math, but it is hard to drive forward slowly and keep straight.

        // Send calculated power to wheels

        // Show the elapsed game time and wheel power.
        //robot.getTelemetry().addData("Status", "Run Time: " + runtime.toString());
        //robot.getTelemetry().addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop()
    {

    }
}
