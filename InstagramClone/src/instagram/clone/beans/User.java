package instagram.clone.beans;

import java.sql.Timestamp;
import java.util.Date;

public class User {
	
	private String userName;
	private String password;
	private String bio;
	
	// This field is automatically filled in DBUtils.insertUser with current time. 
	// Do not fill this manually.
	private Timestamp registrationDate;
	
	public User() {
		
	}
	
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	
	public User(String userName, String password, String bio) {
		super();
		this.userName = userName;
		this.password = password;
		this.bio = bio;

	}

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
	public Date getRegistrationDateAsDateType() {
		Date date = new Date(registrationDate.getTime());
		
		return date;
	}
	
	public Timestamp getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	
	

}
