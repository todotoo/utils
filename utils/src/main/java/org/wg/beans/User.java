package org.wg.beans;

public class User {
	private int id;

	private String name;

	private int age;

	private String gender;
	
	public User() {
		super();
		
	}
	public User(int id, String name, int age, String gender) {
		this.id = id; 
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age
				+ ", gender=" + gender + "]";
	}

}
