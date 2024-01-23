package ensyuu.question2.type1;

public class Shain {
	private int shainBango;
	private String name;
	private int age;
	private int bushoBango;

	public int getShainBango() {
		return shainBango;
	}

	public void setShainBango(int shainBango) {
		this.shainBango = shainBango;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getBushoBango() {
		return bushoBango;
	}

	public void setBushoBango(int bushoBango) {
		this.bushoBango = bushoBango;
	}

	@Override
	public String toString() {
		return "Shain [shainBango=" + shainBango + ", name=" + name + ", age=" + age + ", bushoBango=" + bushoBango
				+ "]";
	}
}
