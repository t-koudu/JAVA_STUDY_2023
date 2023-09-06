package session2.calculator;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Calculate {

	public static void main(String[] args) {
		String input = (new Reader()).read();
		String[] inputs = input.split(" ");
		Target target = new Adapter();
		try {
			String result = switch (inputs[1]) {
			case "+" -> target.addition(inputs[0], inputs[2]);
			case "-" -> target.subtraction(inputs[0], inputs[2]);
			case "*" -> target.multiplication(inputs[0], inputs[2]);
			case "/" -> target.division(inputs[0], inputs[2]);
			default -> throw new IllegalArgumentException("Unexpected value: " + inputs[1]);
			};
			System.out.println(input + " の計算結果は" + result + "です。");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

interface Target {
	public String addition(String a, String b);

	public String subtraction(String a, String b);

	public String multiplication(String a, String b);

	public String division(String a, String b);
}

class Adapter implements Target {

	@Override
	public String addition(String a, String b) {
		BigDecimal adaptee = new BigDecimal(a);
		return (adaptee.add(new BigDecimal(b))).toString();
	}

	@Override
	public String subtraction(String a, String b) {
		BigDecimal adaptee = new BigDecimal(a);
		return (adaptee.subtract(new BigDecimal(b))).toString();
	}

	@Override
	public String multiplication(String a, String b) {
		BigDecimal adaptee = new BigDecimal(a);
		return (adaptee.multiply(new BigDecimal(b))).toString();
	}

	@Override
	public String division(String a, String b) {
		BigDecimal adaptee = new BigDecimal(a);
		return (adaptee.divide(new BigDecimal(b))).toString();
	}
}

class Reader {
	private static Pattern PATTERN = Pattern.compile("[+-]?\\d+(\\.\\d+)?");

	public String read() {
		// 入力
		String input;
		boolean isFinish = false;
		try (Scanner scan = new Scanner(System.in)) {
			do {
				System.out.println("計算式を入力してください。例）1 + 1 ");
				input = scan.nextLine();
				if (!validate(input)) {
					System.out.println("入力内容が間違っています。数字、半角スペース、計算方法（+-*/）、半角スペース、数字で入力して下さい。");
					continue;
				}
				isFinish = true;
			} while (!isFinish);
		}
		return input;
		// ["12.3","-","15.2"]
	}

	private boolean validate(String input) {
		String[] inputs = input.split(" ");
		if (inputs.length != 3) {
			return false;
		}
		// 左辺と右辺に数字が入力されていない場合
		if (!isNumber(inputs[0]) || !isNumber(inputs[2])) {
			return false;
		}
		return true;
	}

	private boolean isNumber(String str) {
		return PATTERN.matcher(str).matches();
	}
}
