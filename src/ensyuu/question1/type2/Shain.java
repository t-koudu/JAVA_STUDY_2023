package ensyuu.question1.type2;

public class Shain extends BaseData {
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

	@Override
	public BaseData create(String[] arr) {
		Shain shain = new Shain();
		shain.setShainBango(Integer.parseInt(arr[0]));
		shain.setName(arr[1]);
		shain.setAge(Integer.parseInt(arr[2]));
		shain.setBushoBango(Integer.parseInt(arr[3]));
		return shain;
	}
}
