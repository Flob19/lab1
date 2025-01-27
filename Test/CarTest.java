import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class CarTest {
    private Volvo240 volvo;
    private Saab95 saab;

    @Before
    public void setUp() {
        volvo = new Volvo240();
        saab = new Saab95();
    }
    @Test
    public void testGetNrDoors() {
        assertEquals(4, volvo.getNrDoors());
        assertEquals(2, saab.getNrDoors());
    }
    @Test
    public void testGetEnginePower() {
        assertEquals(100, volvo.getEnginePower(),0.0);
    }
    @Test
    public void testGetCurrentSpeed() {
        assertEquals(0, volvo.getCurrentSpeed(),0.0);
    }
    @Test
    public void testGetColor() {
        assertSame(Color.BLACK,volvo.getColor());
    }
    @Test
    public void testSetColor() {
        assertSame(Color.RED,saab.getColor());
        saab.setColor(Color.BLUE);
        assertSame(Color.BLUE,saab.getColor());
    }
    @Test
    public void testStartEngine() {
        volvo.startEngine();
        assertEquals(0.1, volvo.getCurrentSpeed(),0.0);
    }
    @Test
    public void testStopEngine() {
        volvo.startEngine();
        volvo.stopEngine();
        assertEquals(0, volvo.getCurrentSpeed(),0.0);
    }
    @Test
    public void testSpeedFactor() {
        assertEquals(1.25, volvo.speedFactor(),0.0);
        saab.setTurboOn();
        assertEquals(1.625, saab.speedFactor(),0.0);
    }
    @Test
    public void testGas() {
        volvo.startEngine();
        volvo.gas(0.5);
        assertEquals(0.725, volvo.getCurrentSpeed(),0.0);
    }
    @Test
    public void testBrake() {
        volvo.startEngine();
        volvo.gas(0.5);//0,725, 0,1 + 1,25 * 0,5 = 0,725
        volvo.brake(0.1);//0,725 - 1,25 * 0,1 = 0,725 -0,125 = 0,6
        assertEquals(0.6, volvo.getCurrentSpeed(),0.0);
    }
    @Test
    public void testMove() {
        volvo.startEngine();
        volvo.gas(0.5);
        volvo.move();
        assertEquals(0, volvo.getXCoordinate(),0.0);
        assertEquals(0.725, volvo.getYCoordinate(),0.0);
    }
    @Test
    public void testTurnLeft() {
        volvo.turnLeft();
        assertEquals(3,volvo.getDirection());
    }
    @Test
    public void testTurnRight() {
        volvo.turnRight();
        assertEquals(1,volvo.getDirection());
    }
}