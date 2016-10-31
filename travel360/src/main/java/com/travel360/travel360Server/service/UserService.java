package com.travel360.travel360Server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.travel360.travel360Server.dao.UserInfoDao;
import com.travel360.travel360Server.domain.FavoriteDto;
import com.travel360.travel360Server.domain.FriendsDto;
import com.travel360.travel360Server.domain.UserDto;

@Service
public class UserService {

	@Autowired
	UserInfoDao userInfoDao;
	
	public UserDto login(UserDto user)
	{
		return userInfoDao.login(user);
	}

	public int countFriends(UserDto user) {
		return userInfoDao.countFriends(user);
	}

	public UserDto getUserInfo(UserDto user) {
		return userInfoDao.getUserInfo(user);
	}

	public boolean addFriend(UserDto user, int targetSeq) {
		
		FriendsDto friends = new FriendsDto();
		friends.setUser_info_seq(user.getSeq());
		friends.setFriends_seq(targetSeq);
		
		return userInfoDao.addFriend(friends);
	}

	public boolean acceptFriend(UserDto user, int targetSeq) {
		FriendsDto friends = new FriendsDto();
		friends.setUser_info_seq(user.getSeq());
		friends.setFriends_seq(targetSeq);
		
		return userInfoDao.acceptFriend(friends);
	}

	public boolean registUser(UserDto user) {
		user.setPermission_check(true);
		return userInfoDao.registUser(user);
	}

	public List<UserDto> friendsList(UserDto user, boolean b) {
		user.setPermission_check(b);
		return userInfoDao.friendsList(user);
	}

	public List<UserDto> requestfriendsList(UserDto user) {
		return userInfoDao.requestFriendsList(user);
	}

	public boolean addFavorite(UserDto user, int targetSeq) {

		FavoriteDto favorite = new FavoriteDto();
		favorite.setUser_info_seq(user.getSeq());
		favorite.setTraveler(targetSeq);
		
		return userInfoDao.addFavorite(favorite);
	}

	public int countFavorite(UserDto user) {
		return userInfoDao.countFavorite(user);
	}

	public List<UserDto> searchUser(UserDto user) {
		user.setId("%" + user.getId() + "%");
		return userInfoDao.searchUser(user);
	}
}
