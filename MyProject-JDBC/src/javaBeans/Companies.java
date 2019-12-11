package javaBeans;

import java.util.ArrayList;

public class Companies {

	private int id;
	private String name;
	private String email;
	private String password;
	private ArrayList<Coupons> coupons;

	public Companies(int id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public Companies(String name, String email, String password, ArrayList<Coupons> coupons) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.coupons = coupons;
	}

	public Companies(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}
	

	public Companies(int id, String name, String email, String password, ArrayList<Coupons> coupons) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.coupons = coupons;
	}
	
	

	public Companies( String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Coupons> getCoupons() {
		return coupons;
	}

	public void setCoupons(ArrayList<Coupons> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "Companies [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", coupons="
				+ coupons + "]";
	}

}
