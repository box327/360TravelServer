package com.travel360.travel360Server.domain;

import java.io.File;

public class PictureDto {
	int seq;
	String picture_loc;
	String picture_type;
	int picture_group_seq;
	int travel_record_seq;
	File image;
	
	int user_info_seq;
	
	
	
	
	public int getUser_info_seq() {
		return user_info_seq;
	}
	public void setUser_info_seq(int user_info_seq) {
		this.user_info_seq = user_info_seq;
	}
	public int getTravel_record_seq() {
		return travel_record_seq;
	}
	public void setTravel_record_seq(int travel_record_seq) {
		this.travel_record_seq = travel_record_seq;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getPicture_loc() {
		return picture_loc;
	}
	public void setPicture_loc(String picture_loc) {
		this.picture_loc = picture_loc;
	}
	public String getPicture_type() {
		return picture_type;
	}
	public void setPicture_type(String picture_type) {
		this.picture_type = picture_type;
	}
	public int getPicture_group_seq() {
		return picture_group_seq;
	}
	public void setPicture_group_seq(int picture_group_seq) {
		this.picture_group_seq = picture_group_seq;
	}
	@Override
	public String toString() {
		return "PictureDto [seq=" + seq + ", picture_loc=" + picture_loc + ", picture_type=" + picture_type
				+ ", picture_group_seq=" + picture_group_seq + "]";
	}
	
	
}
