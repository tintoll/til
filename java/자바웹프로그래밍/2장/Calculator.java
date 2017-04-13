
public class Calculator {

	int add(int i, int j) {
		return i + j;
	}
	int sub(int i, int j) {
		return i - j;
	}
	int mul(int i, int j) {
		return i * j;
	}
	int div(int i, int j) {
		return i / j;
	}
	
	
	public static void main(String[] args) {
		Calculator cal = new Calculator();
		System.out.println(cal.add(1, 2));
		System.out.println(cal.sub(1, 2));
		System.out.println(cal.mul(1, 2));
		System.out.println(cal.div(4, 2));

	}

}
