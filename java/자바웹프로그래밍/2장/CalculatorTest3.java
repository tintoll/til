import junit.framework.TestCase;

public class CalculatorTest3 extends TestCase {
	Calculator cal;
	
	@Override
	protected void setUp() throws Exception {
		// 중복되는 객체생성을 미리 생성 해놓고 사용하도록 한다.
		cal = new Calculator(); 
		System.out.println("setUp!");
	}
	public void testAdd() {
		int result = cal.add(2, 1);		
		assertEquals(3, result);
		System.out.println("testAdd!");
	}
	public void testSub() {
		int result = cal.sub(2, 1);		
		assertEquals(1, result);
		System.out.println("testSub!");
	}
	public void testMul() throws Exception {
		int result = cal.mul(3, 2);		
		assertEquals(6, result);
		System.out.println("testMul!");
	}
	
	@Override
	// 해지 할때 실행되는 메소드 
	protected void tearDown() throws Exception {
		System.out.println("tearDown!");
	}
}
