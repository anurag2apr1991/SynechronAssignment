public class InputData {

	@Override
	public String toString() {
		return " [id=" + id + ", name=" + name + ", pinCode=" + pinCode + ", gender=" + gender + ", city="
				+ city + "]";
	}
	
	
	private String id;
	private String name;
	private String pinCode;
	private String gender;
	private String city;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public boolean equals(Object obj) {
		InputData otherInputData = (InputData)obj;
	boolean idCheck = this.id.equalsIgnoreCase(otherInputData.getId());
	boolean nameCheck = this.name.equalsIgnoreCase(otherInputData.getName());
	boolean pinCodeCheck = this.pinCode.equalsIgnoreCase(otherInputData.getPinCode());
	boolean genderCheck = this.gender.equalsIgnoreCase(otherInputData.getGender());
	boolean cityCheck = this.city.equalsIgnoreCase(otherInputData.getCity());
	
	
		boolean equal = idCheck && nameCheck && pinCodeCheck && genderCheck && cityCheck;


		return equal;
	}
	
	
}
