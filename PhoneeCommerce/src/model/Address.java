package model;

public class Address {

    private long id;
    private String namelastname;
    private String address;
    private String city;
    private String province;
    private String zipcode;
    private String tel;
    private User user;
    
	public Address() {
    	namelastname = "";
    	address = "";
    	city = "";
    	province = "";
    	zipcode = "";
    	tel = "";
    	user = null;
    }

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNamelastname() {
		return namelastname;
	}

	public void setNamelastname(String namelastname) {
		this.namelastname = namelastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
    public String toString() {
	return "Indirzzo [id=" + id + ", nomeecognome=" + namelastname + ", address=" + address + ", city=" + city + ", province=" + province + ", zipcode=" + zipcode + ", tel=" + tel + ", User=" + user.toString() + " ]";
    }

}