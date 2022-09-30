package be.bf.android.vantal.api.dto;

import java.io.Serializable;

public class User implements Serializable {
	private String firstName;
	private String lastName;
	private String image;
	private String password;
	private String birthdate;
	private String isOwner;
	private int id;
	private String email;

	public User(String firstName, String lastName, String password, String birthdate, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.birthdate = birthdate;
		this.email = email;
	}

	public User(String firstName, String lastName, String password, String email, String birthdate, String image, int id, String isOwner) {
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setBirthdate(String birthdate){
		this.birthdate = birthdate;
	}

	public String getBirthdate(){
		return birthdate;
	}

	public void setIsOwner(String isOwner){
		this.isOwner = isOwner;
	}

	public String getIsOwner(){
		return isOwner;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"firstName = '" + firstName + '\'' + 
			",lastName = '" + lastName + '\'' + 
			",image = '" + image + '\'' + 
			",password = '" + password + '\'' + 
			",birthdate = '" + birthdate + '\'' + 
			",isOwner = '" + isOwner + '\'' + 
			",id = '" + id + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}
