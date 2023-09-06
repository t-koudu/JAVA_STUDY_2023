package session1.calendar;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Calendar {

	public static void main(String[] args) {
		InputData input = (new Reader()).read();
	}

}

class InputData {
	int year;
	int month;

	public InputData(int year, int month) {
		this.year = year;
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

}

class Reader {
	private static Pattern PATTERN = Pattern.compile("^[0-9]+$");

	public InputData read() {
		// 入力
		String input;
		boolean isFinish = false;
		try (Scanner scan = new Scanner(System.in)) {
			do {
				System.out.println("表示する年月を入力してください。例）2023 07 ");
				input = scan.nextLine();
				if (!validate(input)) {
					System.out.println("入力内容が間違っています。数字、半角スペース、数字で入力して下さい。");
					continue;
				}
				isFinish = true;
			} while (!isFinish);
		}

		String[] inputs = input.split(" ");
		return new InputData(Integer.valueOf(inputs[0]), Integer.valueOf(inputs[1]));
	}

	private boolean validate(String input) {
		String[] inputs = input.split(" ");
		if (inputs.length != 2) {
			return false;
		}
		// 左辺と右辺に数字が入力されていない場合
		if (!isNumber(inputs[0]) || !isNumber(inputs[1])) {
			return false;
		}
		return true;
	}

	private boolean isNumber(String str) {
		return PATTERN.matcher(str).matches();
	}
}