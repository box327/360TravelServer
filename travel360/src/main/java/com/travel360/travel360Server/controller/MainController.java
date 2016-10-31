package com.travel360.travel360Server.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.jcodec.common.model.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.travel360.travel360Server.domain.PictureDto;
import com.travel360.travel360Server.domain.UserDto;
import com.travel360.travel360Server.service.TravelService;
import com.travel360.travel360Server.service.UserService;

@Controller
public class MainController {

	@Autowired
	UserService userService;

	@Autowired
	TravelService TravelService;

	
	@RequestMapping("/login.do")
	public ModelAndView login(UserDto user,HttpSession session){
		
		UserDto loginUser = userService.login(user);
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		if(loginUser == null)
			modelAndView.addObject("login","false");
		else
			modelAndView.addObject("login","true");
			
		modelAndView.addObject(loginUser);
		return modelAndView;
	}
	
	@RequestMapping("/getUserInfo.do")
	public ModelAndView getUserInfo(UserDto user,HttpSession session){
		
		UserDto loginUser = userService.getUserInfo(user);
		
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		modelAndView.addObject(loginUser);
		
		return modelAndView;
	}
	
	@RequestMapping("/addFriend.do")
	public ModelAndView addFriend(UserDto user,int targetSeq,HttpSession session){
		
		boolean check = userService.addFriend(user,targetSeq);
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		modelAndView.addObject(check);
		return modelAndView;
	}
	
	@RequestMapping("/addFavorite.do")
	public ModelAndView addFavorite(UserDto user,int targetSeq,HttpSession session){
		
		boolean check = userService.addFavorite(user,targetSeq);
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		modelAndView.addObject(check);
		return modelAndView;
	}
	
	@RequestMapping("/acceptFriend.do")
	public ModelAndView acceptFriend(UserDto user,int targetSeq,HttpSession session){
		
		boolean check = userService.acceptFriend(user,targetSeq);
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		modelAndView.addObject(check);
		return modelAndView;
	}
	
	@RequestMapping("/registUser.do")
	public ModelAndView registUser(UserDto user,MultipartFile image,HttpSession session) throws IOException{
		
		PictureDto picture = TravelService.uploadImage(user, 0, 0, image);
		
		user.setProfile_image(picture.getPicture_loc());
		boolean check = userService.registUser(user);
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		modelAndView.addObject(check);
		
		return modelAndView;
	}
	
	@RequestMapping("/searchUser.do")
	public ModelAndView searchUser(UserDto user,HttpSession session) throws IOException{
		
		
		List<UserDto> users = userService.searchUser(user);
		ModelAndView modelAndView = new ModelAndView("jsonView");
		modelAndView.addObject("users",users);
		
		return modelAndView;
	}
	
	@RequestMapping("/getCountFavorite.do")
	public ModelAndView getCountFavorite(UserDto user,HttpSession session){
		int count = userService.countFavorite(user);
		ModelAndView modelAndView = new ModelAndView("jsonView");
		modelAndView.addObject("favoriteCount", count);
		return modelAndView;
	}
	
	
	@RequestMapping("/getCountFriends.do")
	public ModelAndView getCountFriends(UserDto user,HttpSession session){
		int count = userService.countFriends(user);
		ModelAndView modelAndView = new ModelAndView("jsonView");
		modelAndView.addObject("friendsCount", count);
		return modelAndView;
	}
	
	@RequestMapping("/friendsList.do")
	public ModelAndView friendsList(UserDto user,HttpSession session){
		List<UserDto> friendsList = userService.friendsList(user,true);
		ModelAndView modelAndView = new ModelAndView("jsonView");
		modelAndView.addObject(friendsList);
		return modelAndView;
	}
	
	@RequestMapping("/friendsRequesList.do")
	public ModelAndView friendsRequesList(UserDto user,HttpSession session){
		List<UserDto> friendsList = userService.friendsList(user,false);
		ModelAndView modelAndView = new ModelAndView("jsonView");
		modelAndView.addObject(friendsList);
		return modelAndView;
	}
	
	@RequestMapping("/RequestfriendsList.do")
	public ModelAndView RequestfriendsList(UserDto user,HttpSession session){
		List<UserDto> friendsList = userService.requestfriendsList(user);
		ModelAndView modelAndView = new ModelAndView("jsonView");
		modelAndView.addObject(friendsList);
		return modelAndView;
	}
}