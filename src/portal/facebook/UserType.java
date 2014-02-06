package portal.facebook;

public enum UserType {
	NORMAL("Энгийн"),
	BRONZE("Хүрэл"), 
	SILVER("Мөнгөн"), 
	GOLD("Алтан");
	
	private String name;

	private UserType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
