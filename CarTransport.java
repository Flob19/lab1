import java.awt.*;
import java.util.*;
import java.awt.Point;

public class CarTransport extends Truck implements Loadable<Car> {
    private String platformStatus;
    private Stack<Car> loadedCars;
    private final int maxCars;

    public CarTransport(int maxLoad, String name) {
        super(2, 200, Color.red, name);
        loadedCars = new Stack<Car>();
        maxCars = maxLoad;
        raisePlatform();
    }

    public String getPlatformStatus() {
        return platformStatus;
    }

    public Stack<Car> getLoadedCars() {
        return loadedCars;
    }

    @Override
    public void raisePlatform() {
        if (getCurrentSpeed() == 0){
            platformStatus = "up";
        } else {
            throw new IllegalArgumentException("Can not raise platform while moving");
        }
    }

    @Override
    public void lowerPlatform() {
        if (getCurrentSpeed() == 0){
            platformStatus = "down";
        } else {
            throw new IllegalArgumentException("Can not lower platform while moving");
        }
    }

    @Override
    public void gas(double amount) {
        if (getPlatformStatus().equals("up")) {
            super.gas(amount);
        }
    }

    @Override
    public void load(Car car){
        if (getPlatformStatus().equals("down") &&
                !(car instanceof Truck) &&
                (maxCars > loadedCars.size()) &&
                (Math.sqrt(Math.pow(car.getXCoordinate() - this.getXCoordinate(), 2) +
                        Math.pow(car.getYCoordinate() - this.getYCoordinate(), 2)) < 2)) {
                loadedCars.push(car);
                car.x = this.x;
                car.y = this.y;
        } else {throw new IllegalArgumentException("Can not load");}
    }
    @Override
    public Car offLoad(){
        if (getPlatformStatus().equals("down")) {
            Car offLoadedCar = loadedCars.pop();
            offLoadedCar.x = this.x - 1;
            offLoadedCar.y = this.y - 1;
            return offLoadedCar;
        } else {throw new IllegalArgumentException("Can not offload");}
    }

    @Override
    public void move() {
        if (getPlatformStatus().equals("up")) {

            switch (direction) {
                case 0:
                    y += getCurrentSpeed();
                    break;
                case 1:
                    x += getCurrentSpeed();
                    break;
                case 2:
                    y -= getCurrentSpeed();
                    break;
                case 3:
                    x -= getCurrentSpeed();
            }

            for (Car car : loadedCars) {
                car.x = this.x;
                car.y = this.y;
            }
        } else { throw new IllegalArgumentException("Can not move while platform is down"); }
    }
}
