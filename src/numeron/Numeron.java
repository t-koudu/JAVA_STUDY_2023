package numeron;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Numeron {
	private static Integer[] NUMBER = {0,1,2,3,4,5,6,7,8,9};
	private static Pattern PATTERN = Pattern.compile("^[0-9]+$");
	private static Integer NUMBER_MAX = 3;
	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in)) {
			System.out.println("ヌメロンスタート");
			List<Integer> answer = generateNumber();
			boolean winFlag = false;
			int count = 0;
			do {
				System.out.println("数字を入力してください。");
				String input = scan.next();
				if(!validate(input)) {
					System.out.println("入力値が正しくありません。123のように数字を３つ入力してください。　入力値:" + input);
					continue;
				}
				count++;
				List<Integer> inputList = splitInput(input);
				int bite = checkBite(answer,inputList);
				int eat = checkEat(answer,inputList);
				System.out.println(eat + " EAT-" + (bite - eat) +" BITE です。");
				if(eat == 3) {
					winFlag = true;
					System.out.println("正解は " + answer);
				}
			} while (!winFlag);
			System.out.println("おめでとうございます！ " + count +"回でクリアしました。");
		}
	}

	private static int checkEat(List<Integer>answer,List<Integer> inputList) {
		int count = 0;
		for (int i = 0; i < answer.size(); i++) {
			if(answer.get(i).equals(inputList.get(i))){
				count++;
			}
		}
		return count;
	}

	private static int checkBite(List<Integer>answer,List<Integer> inputList) {
		int count = 0;
		for(Integer i:inputList) {
			if(answer.contains(i)) {
				count++;
			}
		}
		return count;
	}

	private static List<Integer> splitInput(String input) {
		return Arrays.stream(input.split("")).map(Integer::parseInt).toList();
	}

	/**
	 * 
	 * @param input
	 * @return true OK false Error
	 */
	private static boolean validate(String input) {
		return PATTERN.matcher(input).matches() && input.length() == NUMBER_MAX;
	}
	
	public static List<Integer> generateNumber(){
		List<Integer> arr = Arrays.asList(NUMBER);
		Collections.shuffle(arr);
		return arr.subList(0, NUMBER_MAX);
	}

}
