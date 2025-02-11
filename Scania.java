import java.awt.*;

public class Scania extends Car implements HasPlatform {

    private double platformAngle;

    public Scania() {
        super(2, 200, Color.black, "Scania");
        platformAngle = 0;
    }

    public double getPlatformAngle() {
        return platformAngle;
    }

    @Override
    public double speedFactor() {
        return Math.max(getEnginePower() * 0.01, 0);
    }

    @Override
    public void raisePlatform() {
        if (getCurrentSpeed() > 0) {
            throw new IllegalArgumentException("Angle of platform can not be adjusted while moving");
        } else { platformAngle = Math.min(getPlatformAngle() + 10, 70); }
    }

    @Override
    public void lowerPlatform() {
        if (getCurrentSpeed() > 0) {
            throw new IllegalArgumentException("Angle of platform can not be adjusted while moving");
        } else { platformAngle = Math.max(getPlatformAngle() - 10, 0);}
    }

    @Override
    public void gas(double amount){
        if (getPlatformAngle() == 0) {
            super.gas(amount);}
        else {throw new IllegalArgumentException("Unable to gas when angle of platform is not 0"); }
    }
}
