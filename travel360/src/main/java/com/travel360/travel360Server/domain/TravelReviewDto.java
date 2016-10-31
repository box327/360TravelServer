package com.travel360.travel360Server.domain;

import java.util.Date;

public class TravelReviewDto {
	int seq;
	Date write_date;
	String text;
	Boolean temp;
	
	int user_info_seq;
	
	float evaluation;
	
	UserDto user;
	String location;
	
	public TravelReviewDto() {
		seq = 0;
		write_date = new Date();
	}
	
	public float getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(float evaluation) {
		this.evaluation = evaluation;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	
	public long getWrite_date_client() {
		return write_date.getTime();
	}
	public void setWrite_date_client(long finish_date) {
		this.write_date = new Date(finish_date);
	}
	
	public int getUser_info_seq() {
		return user_info_seq;
	}
	public void setUser_info_seq(int user_info_seq) {
		this.user_info_seq = user_info_seq;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public Date getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Boolean getTemp() {
		return temp;
	}
	public void setTemp(Boolean temp) {
		this.temp = temp;
	}
	
	@Override
	public String toString() {
		return "TravelReviewDto [seq=" + seq + ", write_date=" + write_date + ", text=" + text + ", temp=" + temp
				+ ", user=" + user + ", location=" + location + "]";
	}
	
	
}
