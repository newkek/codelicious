package domain;

public class Address {
	private long id;
	private String zip;
	private String street;
	private String city;
	private String country;
	
	public Address(){
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public void init(){
		System.out.println("init address done");
	}
	
	public void cleanup(){
		System.out.println("cleanup address done");
	}

}
