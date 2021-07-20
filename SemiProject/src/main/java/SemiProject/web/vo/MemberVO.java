package SemiProject.web.vo;

public class MemberVO {
	private String id,pw,name,address;
	private int age;
	
	

	public MemberVO(String id, String pw, String name, String address, int age) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.address = address;
		this.age = age;
	}

	public MemberVO(String id, String pw, String name) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
	}

	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pw=" + pw + ", name=" + name + ", address=" + address + ", age=" + age + "]";
	}

}
