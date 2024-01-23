package ensyuu.question1.type2;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

	private static final String SHAIN_INFO_TSV = "src/ensyuu/question1/shain_info.tsv";
	private static final String BUSHO_INFO_TSV = "src/ensyuu/question1/busho_info.tsv";

	public static void main(String[] args) throws Exception {
		List<Shain> shainList = FileReader.readFile(SHAIN_INFO_TSV, Shain.class);
		List<Busho> bushoList = FileReader.readFile(BUSHO_INFO_TSV, Busho.class);
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
}
