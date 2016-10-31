package com.travel360.travel360Server.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.travel360.travel360Server.domain.FavoriteDto;
import com.travel360.travel360Server.domain.FriendsDto;
import com.travel360.travel360Server.domain.UserDto;

@Repository
public class UserInfoDao {
	@Autowired
	SqlSessionTemplate sessionTemplate;
	
	public UserDto login(UserDto user)
	{
		UserDto loginUser = (UserDto)sessionTemplate.selectOne("user.login",user);
		System.out.println(loginUser);
		return loginUser;
	}

	public int countFriends(UserDto user) {
		int count = (Integer)sessionTemplate.selectOne("user.countFriend", user);
		return count;
	}

	public UserDto getUserInfo(UserDto user) {
		UserDto loginUser = (UserDto)sessionTemplate.selectOne("user.getUserInfo",user);
		return loginUser;
	}

	public boolean addFriend(FriendsDto user) {
		
		
		int check = sessionTemplate.insert("user.addFriend",user);
		if(check == 0)
			return false;
		else
			return true;
	}

	public boolean acceptFriend(FriendsDto friends) {
		int check = sessionTemplate.update("user.acceptFriend",friends);
		if(check == 0)
			return false;
		else
			return true;
	}

	public boolean registUser(UserDto user) {
		int check = sessionTemplate.insert("user.registUser",user);
		if(check == 0)
			return false;
		else
			return true;	}

	public List<UserDto> friendsList(UserDto user) {
		return sessionTemplate.selectList("user.friendsList",user);
	}

	public List<UserDto> requestFriendsList(UserDto user) {
		return sessionTemplate.selectList("user.requestFriendsList",user);
	}

	public boolean addFavorite(FavoriteDto favorite) {
		int check = sessionTemplate.insert("user.addFavorite",favorite);
		if(check == 0)
			return false;
		else
			return true;	}

	public int countFavorite(UserDto user) {
		int count = (Integer)sessionTemplate.selectOne("user.countFavorite", user);
		return count;
	}

	public List<UserDto> searchUser(UserDto user) {
		return sessionTemplate.selectList("user.searchUser",user);
	}
}
