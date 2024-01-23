package ensyuu.question2.type1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

	private static final String SHAIN_INFO_TSV = "src/ensyuu/question2/shain_info.tsv";
	private static final String BUSHO_INFO_TSV = "src/ensyuu/question2/busho_info.tsv";

	public static void main(String[] args) throws Exception {
		int param = parseParameter(args);
		if (param == 0) {
			System.out.print("パラメータが不正です。" + args);
			return;
		}

		List<Shain> shainList = new ArrayList<Shain>();
		List<String> shainFileData = readFile(SHAIN_INFO_TSV);
		for (String line : shainFileData) {
			Shain shain = convertToShain(line);
			shainList.add(shain);
		}

		Comparator<Shain> compare = createCompare(param);
		shainList.sort(compare);

		List<Busho> bushoList = new ArrayList<Busho>();
		List<String> bushoFileData = readFile(BUSHO_INFO_TSV);

		for (String line : bushoFileData) {
			Busho busho = convertToBusho(line);
			bushoList.add(busho);
		}
		Map<Integer, String> bushoMap = bushoList.stream()
				.collect(Collectors.toMap(s -> s.getBushoBango(), s -> s.getBushoName()));

		// ---- header ----
		System.out.print("社員番号");
		System.out.print("\t");
		System.out.print("社員名");
		System.out.print("\t");
		System.out.print("年齢");
		System.out.print("\t");
		System.out.print("部署名");
		System.out.print("\n");
		for (Shain shain : shainList) {
			// ---- data ----
			System.out.print(shain.getShainBango());
			System.out.print("\t");
			System.out.print(shain.getName());
			System.out.print("\t");
			System.out.print(shain.getAge());
			System.out.print("\t");
			System.out.print(bushoMap.get(shain.getBushoBango()));
			System.out.print("\n");
		}

	}

	private static int parseParameter(String[] args) {
		if (args.length != 1) {
			return 0;
		}
		int val = 0;
		try {
			val = Integer.valueOf(args[0]);
			if (val <= 0 && 4 > val) {
				val = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return val;
	}

	public static List<String> readFile(String filePath) {
		List<String> lines = null;
		try {
			lines = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	public static Shain convertToShain(String str) {
		Shain shain = new Shain();
		String[] tsv = str.split("\t");
		shain.setShainBango(Integer.parseInt(tsv[0]));
		shain.setName(tsv[1]);
		shain.setAge(Integer.parseInt(tsv[2]));
		shain.setBushoBango(Integer.parseInt(tsv[3]));
		return shain;
	}

	public static Busho convertToBusho(String str) {
		Busho busho = new Busho();
		String[] tsv = str.split("\t");
		busho.setBushoBango(Integer.parseInt(tsv[0]));
		busho.setBushoName(tsv[1]);
		return busho;
	}

	private static Comparator<Shain> createCompare(int param) throws Exception {
		Comparator<Shain> compare = null;
		switch (param) {
		case 1:
			compare = new Comparator<Shain>() {
				@Override
				public int compare(Shain a, Shain b) {
					return b.getShainBango() - a.getShainBango();
				}
			};

			break;
		case 2:
			compare = new Comparator<Shain>() {
				@Override
				public int compare(Shain a, Shain b) {
					return b.getBushoBango() - a.getBushoBango();
				}
			};
			break;
		case 3:
			compare = new Comparator<Shain>() {
				@Override
				public int compare(Shain a, Shain b) {
					return a.getAge() - b.getAge();
				}
			};
			break;
		case 4:
			compare = new Comparator<Shain>() {
				@Override
				public int compare(Shain a, Shain b) {
					return b.getAge() - a.getAge();
				}
			};
			break;

		default:
			throw new Exception("パラメータが不正です。");
		}
		return compare;
	}

}
