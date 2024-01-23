package ensyuu.question1.type1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

	private static final String SHAIN_INFO_TSV = "src/ensyuu/question1/shain_info.tsv";
	private static final String BUSHO_INFO_TSV = "src/ensyuu/question1/busho_info.tsv";

	public static void main(String[] args) {
		List<Shain> shainList = new ArrayList<Shain>();
		List<String> shainFileData = readFile(SHAIN_INFO_TSV);
		for (String line : shainFileData) {
			Shain shain = convertToShain(line);
			shainList.add(shain);
		}

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

}
