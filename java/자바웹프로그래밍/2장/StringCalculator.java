
public class StringCalculator {

	public int add(String string) {
		
		if(string == null || string.isEmpty() ){
			return 0;
		}
		
		String[] values = string.split(",");
		int sum = 0;
		for(String value : values) {
			sum += Integer.parseInt(value);
		}
		return sum;
		
	}

}
