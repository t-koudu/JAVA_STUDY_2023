package ensyuu.question1.type2;

public class Busho extends BaseData {
	private int bushoBango;
	private String bushoName;

	public int getBushoBango() {
		return bushoBango;
	}

	public void setBushoBango(int bushoBango) {
		this.bushoBango = bushoBango;
	}

	public String getBushoName() {
		return bushoName;
	}

	public void setBushoName(String bushoName) {
		this.bushoName = bushoName;
	}

	@Override
	public String toString() {
		return "Busho [bushoBango=" + bushoBango + ", bushoName=" + bushoName + "]";
	}

	@Override
	public Busho create(String[] arr) {
		Busho busho = new Busho();
		busho.setBushoBango(Integer.parseInt(arr[0]));
		busho.setBushoName(arr[1]);
		return busho;
	}
}
