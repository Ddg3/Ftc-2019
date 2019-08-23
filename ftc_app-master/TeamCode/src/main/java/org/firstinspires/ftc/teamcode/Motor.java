package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import java.util.Vector;

public class Motor implements DcMotor
{
    private Motor master = null;
    private Vector<Motor> slaves = new Vector<>(1);
    private double power = 0;
    private double turn = 0;

    /*public Motor(Motor master)
    {
        this.master = master;
    }*/

    public Vector<Motor> getSlaves()
    {
        return slaves;
    }

    public void setSlaves(Vector<Motor> slaves)
    {
        this.slaves = slaves;
    }

    public Motor getMaster()
    {
        return master;
    }

    public void setMaster(Motor master)
    {
        this.master = master;

        for(int i = 0; i < master.slaves.size(); i++)
        {
            if(master.slaves.get(i) == this)
                 return;
        }
        master.slaves.add(this);
    }

    @Override
    public MotorConfigurationType getMotorType() {
        return null;
    }

    @Override
    public void setMotorType(MotorConfigurationType motorType) {

    }

    @Override
    public DcMotorController getController() {
        return null;
    }

    @Override
    public int getPortNumber() {
        return 0;
    }

    @Override
    public void setZeroPowerBehavior(ZeroPowerBehavior zeroPowerBehavior) {

    }

    @Override
    public ZeroPowerBehavior getZeroPowerBehavior() {
        return null;
    }

    @Override
    public void setPowerFloat() {

    }

    @Override
    public boolean getPowerFloat() {
        return false;
    }

    @Override
    public void setTargetPosition(int position) {

    }

    @Override
    public int getTargetPosition() {
        return 0;
    }

    @Override
    public boolean isBusy() {
        return false;
    }

    @Override
    public int getCurrentPosition() {
        return 0;
    }

    @Override
    public void setMode(RunMode mode)
    {

    }

    @Override
    public RunMode getMode() {
        return null;
    }

    @Override
    public void setDirection(Direction direction) {

    }

    @Override
    public Direction getDirection()
    {
        return null;
    }

    @Override
    public void setPower(double power)
    {
        if(master == null)
        {
            this.power = power;
            for (int i = 0; i < slaves.size(); i++)
            {
                slaves.get(i).power = power;
            }
        }
    }

    @Override
    public double getPower()
    {
        return power;
    }

    @Override
    public Manufacturer getManufacturer() {
        return null;
    }

    @Override
    public String getDeviceName() {
        return null;
    }

    @Override
    public String getConnectionInfo() {
        return null;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public void resetDeviceConfigurationForOpMode() {

    }

    @Override
    public void close() {

    }
}
