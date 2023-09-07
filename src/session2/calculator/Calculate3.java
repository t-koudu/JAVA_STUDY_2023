package session2.calculator;

import java.math.BigDecimal;

public class Calculate3 {

	public static void main(String[] args) {
		String input = (new Reader()).read();
		String[] inputs = input.split(" ");
		try {
			OriginDecimal decimal = new OriginDecimal(inputs[0]);
			String result = switch (inputs[1]) {
			case "+" -> decimal.add(inputs[2]);
			case "-" -> decimal.subtract(inputs[2]);
			case "*" -> decimal.multiply(inputs[2]);
			case "/" -> decimal.divide(inputs[2]);
			default -> throw new IllegalArgumentException("Unexpected value: " + inputs[1]);
			};
			System.out.println(input + " の計算結果は" + result + "です。");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class OriginDecimal extends BigDecimal {

	public OriginDecimal(String val) {
		super(val);
	}

	public String add(String val) {
		return this.add(new BigDecimal(val)).toString();
	}

	public String subtract(String val) {
		return this.subtract(new BigDecimal(val)).toString();
	}

	public String multiply(String val) {
		return this.multiply(new BigDecimal(val)).toString();
	}

	public String divide(String val) {
		return this.divide(new BigDecimal(val)).toString();
	}
}
