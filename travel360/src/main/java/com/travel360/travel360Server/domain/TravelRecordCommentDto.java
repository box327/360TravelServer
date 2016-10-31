package com.travel360.travel360Server.domain;

import java.util.Date;

public class TravelRecordCommentDto {

	int seq;
	Date write_date;
	String comment;
	float evaluation;
	int travel_record_seq;
	int user_info_seq;
	
	String id;
	
	public TravelRecordCommentDto() {
		write_date = new Date();
	}

	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public float getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(float evaluation) {
		this.evaluation = evaluation;
	}


	public int getTravel_record_seq() {
		return travel_record_seq;
	}
	public void setTravel_record_seq(int travel_record_seq) {
		this.travel_record_seq = travel_record_seq;
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
	
	public long getWrite_date_client() {
		return write_date.getTime();
	}
	public void setWrite_date_client(long write_date) {
		this.write_date = new Date(write_date);
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getUser_info_seq() {
		return user_info_seq;
	}
	public void setUser_info_seq(int user_info_seq) {
		this.user_info_seq = user_info_seq;
	}
	@Override
	public String toString() {
		return "TravelRecordCommentDto [seq=" + seq + ", write_date=" + write_date + ", comment=" + comment
				+ ", evaluation=" + evaluation + ", travel_record_seq=" + travel_record_seq + ", user_info_seq="
				+ user_info_seq + ", id=" + id + "]";
	}
	
	
}
