import java.util.*;

public class AutoRepairShop<T extends Car> implements Loadable<T> {

    private final int maxCars;
    private final String shopName;
    private Stack<T> loadedCars;

    public AutoRepairShop(int maxLoad, String shopName){
        maxCars = maxLoad;
        loadedCars = new Stack<>();
        this.shopName = shopName;
    }

    public Stack<T> getLoadedCars() {
        return loadedCars;
    }

    public String getShopName() {
        return shopName;
    }

    public int getMaxCars() {
        return maxCars;
    }

    @Override
    public T offLoad() {
        return loadedCars.pop();
    }

     @Override
    public void load(T car) {
         if (getLoadedCars().size() < getMaxCars()) {
             loadedCars.push(car);
         } else {
             throw new IllegalArgumentException("Shop is full");
         }
     }
}
