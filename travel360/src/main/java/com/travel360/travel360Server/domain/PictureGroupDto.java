package com.travel360.travel360Server.domain;

import java.util.List;

public class PictureGroupDto {
	int seq;
	String group_loc;
	int travel_record_seq;
	List<PictureDto> images;
	
	
	
	public List<PictureDto> getImages() {
		return images;
	}
	public void setImages(List<PictureDto> images) {
		this.images = images;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getGroup_loc() {
		return group_loc;
	}
	public void setGroup_loc(String group_loc) {
		this.group_loc = group_loc;
	}
	public int getTravel_record_seq() {
		return travel_record_seq;
	}
	public void setTravel_record_seq(int travel_record_seq) {
		this.travel_record_seq = travel_record_seq;
	}
	
	@Override
	public String toString() {
		return "PictureGroupDto [seq=" + seq + ", group_loc=" + group_loc + ", travel_record_seq=" + travel_record_seq
				+ ", images=" + images + "]";
	}
	
	
}
