import java.util.*;

public class AutoRepairShop<T extends Car> implements Loadable<T> {

    private final int maxCars;
    private final String shopName;
    private ArrayList<T> loadedCars;

    public AutoRepairShop(int maxLoad, String shopName){
        maxCars = maxLoad;
        this.loadedCars = new ArrayList<>();
        this.shopName = shopName;
    }

    public ArrayList<T> getLoadedCars() {
        return loadedCars;
    }

    public String getShopName() {
        return shopName;
    }

    public int getMaxCars() {
        return maxCars;
    }

    public T removeSpecific(T car) {
        if (loadedCars.contains(car)) {
            loadedCars.remove(car);
            return car;
        } else {  throw new NoSuchElementException("Car not in shop"); }

    }

    @Override
    public T offLoad() {
        if (loadedCars.isEmpty()) {
            throw new NoSuchElementException("No cars to offload");
        } else { return loadedCars.removeFirst(); }
    }

    @Override
    public void load(T car) {
         if (getLoadedCars().size() < getMaxCars()) {
             loadedCars.add(car);
         } else {
             throw new IllegalArgumentException("Shop is full");
         }
     }
}
