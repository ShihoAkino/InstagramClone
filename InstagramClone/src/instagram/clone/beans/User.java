package instagram.clone.beans;

import java.sql.Timestamp;
import java.util.Date;

public class User {
	
	private String userName;
	private String password;
	private String bio;
	private Timestamp registrationDate;
	
	public User() {}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
	
	//returns convert Timestamp to Date and returns Date datatype
	public Date getRegistrationDate() {
		Date date = new Date(registrationDate.getTime());
		
		return date;
	}

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	
	

}
