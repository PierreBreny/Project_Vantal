package be.bf.android.vantal.api.dto;

public class Rental {
	private int id;
	private int vanId;
	private String endDate;
	private int userId;
	private String startDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setVanId(int vanId){
		this.vanId = vanId;
	}

	public int getVanId(){
		return vanId;
	}

	public void setEndDate(String endDate){
		this.endDate = endDate;
	}

	public String getEndDate(){
		return endDate;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setStartDate(String startDate){
		this.startDate = startDate;
	}

	public String getStartDate(){
		return startDate;
	}

	@Override
	public String toString() {
		return "Rental{" +
				"id=" + id +
				", vanId=" + vanId +
				", endDate='" + endDate + '\'' +
				", userId=" + userId +
				", startDate='" + startDate + '\'' +
				'}';
	}
}
