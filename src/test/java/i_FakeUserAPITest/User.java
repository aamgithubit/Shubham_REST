package i_FakeUserAPITest;

import i_FakeUserAPITest.FakeUser.Address;
import i_FakeUserAPITest.FakeUser.Name;

public class User {

	private String email;
	private String username;
	private String password;
	private String phone;
	private Name name;
	private Address address;

	public User(String email, String username, String password, String phone, Name name, Address address) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.name = name;
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public static class Name {
		private String firstname;
		private String lastname;

		public Name(String firstname, String lastname) {
			this.firstname = firstname;
			this.lastname = lastname;
		}

		public String getFirstname() {
			return firstname;
		}

		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}

		public String getLastname() {
			return lastname;
		}

		public void setLastname(String lastname) {
			this.lastname = lastname;
		}

	}

}