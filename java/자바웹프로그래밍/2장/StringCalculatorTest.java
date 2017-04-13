import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTest {
	private StringCalculator cal;		
	
	@Before
	public void setup() {
		cal = new StringCalculator();
	}
	@Test
	public void test_add_null_empty() {
		assertEquals(0, cal.add(""));
		assertEquals(0, cal.add(null));
	}
	
	@Test
	public void test_add_숫자한개입력일경우() {
		assertEquals(1, cal.add("1"));
	}
	@Test
	public void test_add_쉼표구분자() {
		assertEquals(3, cal.add("1,2"));
	}
	@Test
	public void test_add_쉼표_또는_콜론_구분자() {
		assertEquals(6, cal.add("1,2:3"));
	}

}
