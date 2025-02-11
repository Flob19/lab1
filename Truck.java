import java.awt.*;

public abstract class Truck extends Car {
    public Truck(int nrDoors, int enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
    }


    public double speedFactor() {
        return Math.max(getEnginePower() * 0.01, 0);
    }

    public abstract void raisePlatform();

    public abstract void lowerPlatform();


}
