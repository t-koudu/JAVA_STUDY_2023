package session1.calculator;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Calculate {

	public static void main(String[] args) {
		String input = (new Reader()).read();
		String[] inputs = input.split(" ");
		Operator operator = Operator.getOperator(inputs[1]);
		Calculator calculator = operator.getCalculator();
		try {
			Integer result = 0;
			result = calculator.calculate(Integer.valueOf(inputs[0]), Integer.valueOf(inputs[2]));
			System.out.println(input + " の計算結果は" + result + "です。");
		} catch (CalculateException e) {
			System.out.println(e.getErrorMsg());
		}
	}
}

class Reader {
	private static Pattern PATTERN = Pattern.compile("^[0-9]+$");

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
	}

	private boolean validate(String input) {
		String[] inputs = input.split(" ");
		if (inputs.length != 3) {
			return false;
		}
		// 対応する演算子がない場合
		if (Operator.getOperator(inputs[1]) == null) {
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

enum Operator {
	ADDITION("+") {
		@Override
		public Calculator getCalculator() {
			return new AdditionCalculator();
		}
	},
	SUBTRACTION("-") {
		@Override
		public Calculator getCalculator() {
			return new SubtractionCalculator();
		}

	},
	MULTIPLICATION("*") {

		@Override
		public Calculator getCalculator() {
			return new MultiplicationCalculator();
		}

	},
	DIVISION("/") {

		@Override
		public Calculator getCalculator() {
			return new DivisionCalculator();
		}

	};

	String operation;

	Operator(String str) {
		this.operation = str;
	}

	public static Operator getOperator(String val) {
		for (Operator ope : Operator.values()) {
			if (ope.operation.equals(val)) {
				return ope;
			}
		}
		return null;
	}

	public abstract Calculator getCalculator();

}

interface Calculator {
	public Integer calculate(Integer left, Integer right) throws CalculateException;
}

class AdditionCalculator implements Calculator {
	@Override
	public Integer calculate(Integer left, Integer right) throws CalculateException {
		return left + right;
	}
}

class SubtractionCalculator implements Calculator {
	@Override
	public Integer calculate(Integer left, Integer right) throws CalculateException {
		return left - right;
	}
}

class MultiplicationCalculator implements Calculator {
	@Override
	public Integer calculate(Integer left, Integer right) throws CalculateException {
		return left * right;
	}
}

class DivisionCalculator implements Calculator {
	@Override
	public Integer calculate(Integer left, Integer right) throws CalculateException {
		if (right == 0) {
			throw new CalculateException("左辺が0の時は割り算できません。");
		}
		return left / right;
	}
}

class CalculateException extends Exception {
	private String errorMsg;

	public CalculateException(String msg) {
		super();
		this.errorMsg = msg;
	}

	public String getErrorMsg() {
		return this.errorMsg;
	}
}
