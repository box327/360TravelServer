package com.travel360.travel360Server.domain;

import java.util.Date;

public class TravelReviewComment {
	int seq;
	Date write_date;
	String comment;
	float evaluation;
	int travel_review_seq;
	int user_info_seq;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public long getWrite_date_client() {
		return write_date.getTime();
	}
	public void setWrite_date_client(long finish_date) {
		this.write_date = new Date(finish_date);
	}
	public float getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(float evaluation) {
		this.evaluation = evaluation;
	}
	public int getTravel_review_seq() {
		return travel_review_seq;
	}
	public void setTravel_review_seq(int travel_review_seq) {
		this.travel_review_seq = travel_review_seq;
	}
	public int getUser_info_seq() {
		return user_info_seq;
	}
	public void setUser_info_seq(int user_info_seq) {
		this.user_info_seq = user_info_seq;
	}
	@Override
	public String toString() {
		return "TravelReviewComment [seq=" + seq + ", write_date=" + write_date + ", comment=" + comment
				+ ", evaluation=" + evaluation + ", travel_review_seq=" + travel_review_seq + ", user_info_seq="
				+ user_info_seq + "]";
	}
	
	
}
