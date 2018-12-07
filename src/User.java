public class User {
	private String first_name;
	private String last_name;
	private String PIN;
	private String dob;
	private String city;
	private String state;
	private String postal_code;
	private String phone;
	private String address;
	
	public User(String PIN, String last_name, String first_name, String dob, String phone, String address, String city, String state, String postal_code) {
		this.first_name = first_name;
		this.phone = phone;
		this.address = address;
		this.last_name = last_name;
		this.PIN = PIN;
		this.dob = dob;
		this.city = city;
		this.state = state;
		this.postal_code = postal_code;
	}
	public void setPIN(String PINInput) {
		PIN = PINInput;
	}
	public String getPIN() {
		return PIN;
	}
	public void setFirst_Name(String inputName) {
		first_name = inputName;
	}
	public void setLast_Name(String inputName) {
		last_name = inputName;
	}
	public void setPhone(String inputPhone) {
		phone = inputPhone;
	}
	public void setAddress(String inputAddress) {
		address = inputAddress;
	}
	public String getFirst_Name() {
		return first_name;
	}
	public String getLast_Name() {
		return last_name;
	}
	public String getPhone() {
		return phone;
	}
	public String getAddress() {
		return address;
	}
	public void setDob(String inputDob) {
		dob = inputDob;
	}
	public void setCity(String inputCity) {
		city = inputCity;
	}
	public void setState(String inputState) {
		state = inputState;
	}
	public void setPostal_Code(String inputPostal) {
		postal_code = inputPostal;
	}
	public String getdob() {
		return dob;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getPostal_Code() {
		return postal_code;
	}
}
