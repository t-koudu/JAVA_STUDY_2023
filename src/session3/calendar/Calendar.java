package session3.calendar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Calendar {

	public static void main(String[] args) {
		// yyyy mm
		String input = (new Reader()).read();
		// yyyy mm
		String[] inputs = input.split(" ");
		try {
			Model model = createCalendarData(Integer.valueOf(inputs[0]), Integer.valueOf(inputs[1]));
			view(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void view(Model model) {
		System.out.println(String.join(" ", model.getHeader()));
		for (List<Integer> week : model.getBody()) {
			List<String> strWeek = week.stream().map(e -> {
				return (e != 0) ? String.format("%02d", e) : "  ";
			}).collect(Collectors.toList());
			System.out.println(String.join(" ", strWeek));
		}

	}

	private static Model createCalendarData(Integer year, Integer month) {
		Month m = new Jun(year);
		return m.getData();
	}
}

class Model {
	private List<String> header = new ArrayList<String>(Arrays.asList("日", "月", "火", "水", "木", "金", "土"));
	private List<List<Integer>> body;

	public List<String> getHeader() {
		return header;
	}

	public void setHeader(List<String> header) {
		this.header = header;
	}

	public List<List<Integer>> getBody() {
		return body;
	}

	public void setBody(List<List<Integer>> body) {
		this.body = body;
	}
}

class Jun extends BaseMonth {

	Jun(Integer year) {
		super(year);
		month = 1;
	}

	@Override
	protected int getMonthCount() {
		return 31;
	}

}

class BaseMonth extends Month {

	BaseMonth(Integer year) {
		super(year);
	}

	@Override
	protected int getStartDayOfWeek() {
		return getDayOfWeek(year, month);
	}

	@Override
	protected int getMonthCount() {
		return 0;
	}

	@Override
	public Model getData() {
		Model result = new Model();
		List<Integer> monthList = new ArrayList<Integer>();
		int day = getMonthCount();
		for (int i = 1; i < day + 1; i++) {
			monthList.add(i);
		}
		int startDayOfweek = getStartDayOfWeek();
		for (int i = 0; i < startDayOfweek; i++) {
			monthList.add(0, 0);
		}

		int partitionSize = 7;
		List<List<Integer>> weekList = IntStream
				.range(0, ((monthList.size() - 1) / partitionSize) + 1) // いくつリストを作るかを設定
				.mapToObj(e -> monthList.subList(
						e * partitionSize, Math.min((e + 1) * partitionSize, monthList.size()) // fromIndexとtoIndexを計算してセットする
				)).toList();
		result.setBody(weekList);
		return result;
	}

	/**
	 * Zellerの公式を使って月初の曜日を返す
	 * @param year
	 * @param month
	 * @return 	
	 */
	private int getDayOfWeek(int year, int month) {
		// Zellerの公式
		// 月初めの曜日を取得します。
		// 日 : 0,月 : 1,火 : 2,水 : 3,木 : 4,金 : 5,土 : 6
		int day = ((year + year / 4 - year / 100 + year / 400 + (13 * month + 8) / 5 + 1) % 7);
		return DAY_OF_WEEK.getType(day).getValue();
	}
}

enum DAY_OF_WEEK {
	MONDAY("月", 1), TUESDAY("火", 2), WEDNESDAY("水", 3), THURSDAY("木", 4), FRIDAY("金", 5), SATURDAY("土", 6), SUNDAY("日",
			0),
			;

	private final String text;
	private final int value;

	DAY_OF_WEEK(String text, int value) {
		this.text = text;
		this.value = value;
	}

	public String getText() {
		return this.text;
	}

	public int getValue() {
		return this.value;
	}

	public static DAY_OF_WEEK getType(final int value) {
		DAY_OF_WEEK[] types = DAY_OF_WEEK.values();
		for (DAY_OF_WEEK type : types) {
			if (type.getValue() == value) {
				return type;
			}
		}
		return null;
	}
}

abstract class Month {
	protected Integer year;
	protected Integer month;

	Month(Integer year) {
		this.year = year;
	}

	//　何曜日スタートか
	abstract protected int getStartDayOfWeek();

	//  何日か
	abstract protected int getMonthCount();

	//  データ作成
	abstract public Model getData();

}

class Reader {
	private static Pattern PATTERN = Pattern.compile("^[0-9]+$");

	public String read() {
		// 入力
		String input;
		boolean isFinish = false;
		try (Scanner scan = new Scanner(System.in)) {
			do {
				System.out.println("年月を入力してください。例）2023 10");
				input = scan.nextLine();
				if (!validate(input)) {
					System.out.println("入力内容が間違っています。年（数字）、半角スペース、月（数字）で入力して下さい。");
					continue;
				}
				isFinish = true;
			} while (!isFinish);
		}
		return input;
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
