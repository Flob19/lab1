import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class TruckTest {
    private Scania scania;
    private CarTransport transport;
    private Saab95 car1;
    private Volvo240 car2;
    private AutoRepairShop<Car> auto1;
    private AutoRepairShop<Saab95> auto2;

    @Before
    public void setUp() {
        scania = new Scania();
        transport = new CarTransport(2, "TestTransport");
        car1 = new Saab95();
        car2 = new Volvo240();
        auto1 = new AutoRepairShop<Car>(1,"numb1");
        auto2 = new AutoRepairShop<Saab95>(2, "numb2");
        car1.x = 1;
        car1.y = 1;
    }

    @Test
    public void testPlatformLower() {
        scania.lowerPlatform();
        assertEquals(0, scania.getPlatformAngle(), 0.0);
    }

    @Test
    public void testPlatformRaise() {
        for (int i = 0; i < 10; i++) {
            scania.raisePlatform();
        }
        assertEquals(70, scania.getPlatformAngle(), 0.0);
    }

    @Test
    public void testLowerPlatformWhenMoving() {
        transport.startEngine();
        transport.gas(1);

        assertThrows(IllegalArgumentException.class, () -> transport.lowerPlatform());
    }

    @Test
    public void testLoadCar() {
        transport.lowerPlatform();
        transport.load(car1);
        assertEquals(1, transport.getLoadedCars().size(), 0.0);
        assertEquals(transport.x, car1.x, 0.0);
        assertEquals(transport.y, car1.y, 0.0);
    }

    @Test
    public void testCarPositionWhenMoving() {
        // Load car
        transport.lowerPlatform();
        transport.load(car1);
        transport.raisePlatform();

        // Start transport engine, gas and move
        transport.startEngine();
        transport.gas(1);
        transport.move();

        // Check if car position updates with transport position
        assertEquals(transport.x, car1.x, 0.0);
        assertEquals(transport.y, car1.y, 0.0);
    }

    @Test
    public void testAutoRepair() {
        auto1.load(car1);
        assertThrows(IllegalArgumentException.class, () -> auto1.load(car2));
        assertEquals(1, auto1.getMaxCars());
        assertEquals("numb1", auto1.getShopName());
        assertEquals(car1, auto1.offLoad());
        assertFalse(auto1.getLoadedCars().contains(car1));
        auto2.load(car1);
        assertTrue(auto2.getLoadedCars().contains(car1));
        auto2.removeSpecific(car1);
        assertFalse(auto2.getLoadedCars().contains(car1));
    }
}