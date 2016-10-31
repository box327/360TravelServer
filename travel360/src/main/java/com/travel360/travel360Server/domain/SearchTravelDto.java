package com.travel360.travel360Server.domain;

import java.util.Date;

public class SearchTravelDto {
	String country;
	String city;
	String title;
	Date start_date;
	Date finish_date;
	Integer user;
	
	String order;
	
	double latitude;
	double longitude;
	boolean distenceFlag;	
	
	int start_num;
	int number;
	
	public SearchTravelDto() {
		country = null;
		city = null;
		title = null;
		start_date = new Date(0);
		finish_date = new Date(92233720368547l);
		user = 0;
		order = "start_date";
		start_num = 0;
		number = 10;
		longitude = 0;
		latitude = 0;
		distenceFlag = false;
	}
	
	public boolean isDistenceFlag() {
		return distenceFlag;
	}

	public void setDistenceFlag(boolean distenceFlag) {
		this.distenceFlag = distenceFlag;
	}

	public double getLatitude() {
		return latitude;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title =  "%" + title + "%";
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getFinish_date() {
		return finish_date;
	}
	public void setFinish_date(Date finish_date) {
		this.finish_date = finish_date;
	}
	
	public long getStart_date_client() {
		if(start_date == null)
			return 0;
		return start_date.getTime();
	}
	public void setStart_date_client(long start_date) {
		this.start_date = new Date(start_date);
	}
	public long getFinish_date_client() {
		if(finish_date == null)
			return 0;
		return finish_date.getTime();
	}
	public void setFinish_date_client(long finish_date) {
		this.finish_date = new Date(finish_date);
	}
	
	public Integer getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	
	public int getStart_num() {
		return start_num;
	}
	
	public void setStart_num(int start_num) {
		this.start_num = start_num;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "SearchTravelDto [country=" + country + ", city=" + city + ", title=" + title + ", start_date="
				+ start_date + ", finish_date=" + finish_date + ", user=" + user + ", order=" + order + ", latitude="
				+ latitude + ", longitude=" + longitude + ", start_num=" + start_num + ", number=" + number + "]";
	}
	
	
	
	
	
}
