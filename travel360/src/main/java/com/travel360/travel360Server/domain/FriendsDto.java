package com.travel360.travel360Server.domain;

public class FriendsDto {
	int seq;
	int user_info_seq;
	int friends_seq;
	boolean permission;
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getUser_info_seq() {
		return user_info_seq;
	}
	public void setUser_info_seq(int user_info_seq) {
		this.user_info_seq = user_info_seq;
	}
	public int getFriends_seq() {
		return friends_seq;
	}
	public void setFriends_seq(int friends_seq) {
		this.friends_seq = friends_seq;
	}
	public boolean isPermission() {
		return permission;
	}
	public void setPermission(boolean permission) {
		this.permission = permission;
	}
	
	

}
