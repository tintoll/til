import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest4 {
	private Calculator cal;

	@Before
	public void setup() {
		cal = new Calculator();
		System.out.println("setUp!");
	}
	@Test
	public void add() {
		assertEquals(3, cal.add(2, 1));
		System.out.println("testAdd!");
	}
	@Test
	public void sub() {
		assertEquals(1, cal.sub(2, 1));
		System.out.println("testSub!");
	}
	
	@After
	public void tearDown() {
		System.out.println("tearDown!");
	}
}
