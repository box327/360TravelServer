package com.travel360.travel360Server.domain;

import java.util.Date;

public class TravelRecordDto {
	int seq;
	
	Date start_date;
	Date finish_date;
	
	String text;
	boolean temp;
	int user_info_seq;
	String presentation_image;
	
	String country;
	String city;
	String title;
	
	float evaluation;
	
	double latitude;
	double longitude;
	
	UserDto userinfo;
		
	public TravelRecordDto() {
		seq = 0;
		start_date = new Date();
		finish_date = new Date();
	}

	public float getEvaluation() {
		return evaluation;
	}


	public void setEvaluation(float evaluation) {
		this.evaluation = evaluation;
	}


	public UserDto getUserinfo() {
		return userinfo;
	}


	public void setUserinfo(UserDto userinfo) {
		this.userinfo = userinfo;
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
		this.title = title;
	}
	
	public String getPresentation_image() {
		return presentation_image;
	}


	
	
	
	public double getLatitude() {
		return latitude;
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

	public void setPresentation_image(String presentation_image) {
		this.presentation_image = presentation_image;
	}


	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
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
		return start_date.getTime();
	}
	public void setStart_date_client(long start_date) {
		this.start_date = new Date(start_date);
	}
	public long getFinish_date_client() {
		return finish_date.getTime();
	}
	public void setFinish_date_client(long finish_date) {
		this.finish_date = new Date(finish_date);
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isTemp() {
		return temp;
	}
	public void setTemp(boolean temp) {
		this.temp = temp;
	}
	public int getUser_info_seq() {
		return user_info_seq;
	}
	public void setUser_info_seq(int user_info_seq) {
		this.user_info_seq = user_info_seq;
	}
	
	@Override
	public String toString() {
		return "TravelRecordDto [seq=" + seq + ", start_date=" + start_date + ", finish_date=" + finish_date + ", text="
				+ text + ", temp=" + temp + ", user_info_seq=" + user_info_seq + ", presentation_image="
				+ presentation_image + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
}
