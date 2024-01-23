package ensyuu.question1.type1;

public class Busho {
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
}
