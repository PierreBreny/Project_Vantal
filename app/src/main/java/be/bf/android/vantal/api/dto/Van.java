package be.bf.android.vantal.api.dto;

import java.io.Serializable;
import java.util.List;

public class Van implements Serializable {
	private String country;
	private List<String> images;
	private String city;
	private String latitude;
	private double rating;
	private String description;
	private String title;
	private int userId;
	private int price;
	private String model;
	private int id;
	private String brand;
	private String longitude;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setImages(List<String> images){
		this.images = images;
	}

	public List<String> getImages(){
		return images;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setLatitude(String latitude){
		this.latitude = latitude;
	}

	public String getLatitude(){
		return latitude;
	}

	public void setRating(double rating){
		this.rating = rating;
	}

	public double getRating(){
		return rating;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setModel(String model){
		this.model = model;
	}

	public String getModel(){
		return model;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setBrand(String brand){
		this.brand = brand;
	}

	public String getBrand(){
		return brand;
	}

	public void setLongitude(String longitude){
		this.longitude = longitude;
	}

	public String getLongitude(){
		return longitude;
	}

	@Override
 	public String toString(){
		return 
			"Van{" + 
			"country = '" + country + '\'' + 
			",images = '" + images + '\'' + 
			",city = '" + city + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",rating = '" + rating + '\'' + 
			",description = '" + description + '\'' + 
			",title = '" + title + '\'' + 
			",userId = '" + userId + '\'' + 
			",price = '" + price + '\'' + 
			",model = '" + model + '\'' + 
			",id = '" + id + '\'' + 
			",brand = '" + brand + '\'' + 
			",longitude = '" + longitude + '\'' + 
			"}";
		}
}