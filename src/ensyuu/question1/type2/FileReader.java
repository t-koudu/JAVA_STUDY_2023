package ensyuu.question1.type2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {
	public static <T extends BaseData> List<T> readFile(String filePath, Class<T> clazz) throws Exception {
		List<T> list = new ArrayList<T>();
		List<String> arr = read(filePath);
		for (String str : arr) {
			String[] tsv = str.split("\t");
			T t = clazz.getDeclaredConstructor().newInstance();
			list.add(convert(tsv, t));
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	private static <T extends BaseData> T convert(String[] tsvData, T data) {
		return (T) data.create(tsvData);
	}

	private static List<String> read(String filePath) {
		List<String> lines = null;
		try {
			lines = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

}
