package DTO;

import java.util.Date;

public class Users {
	private String email;
	private String password;
	private String name;
	private String gender;
	private String DoB;
	private int status; // 0 - chưa verify hoặc server block; 1 - là bth; 2 - ko cho tạo đề thi; 3 -
						// không cho thi;
	private int totalMatch;

	public Users(String email, String password, String name, String gender, String doB, int status, int totalMatch) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.DoB = doB;
		this.status = status;
		this.totalMatch = totalMatch;
	}

	public Users() {
		super();
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDoB() {
		return DoB;
	}

	public void setDoB(String doB) {
		DoB = doB;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTotalMatch() {
		return totalMatch;
	}

	public void setTotalMatch(int totalMatch) {
		this.totalMatch = totalMatch;
	}

}
