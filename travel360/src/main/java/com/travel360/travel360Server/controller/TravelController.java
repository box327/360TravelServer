package com.travel360.travel360Server.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.travel360.travel360Server.domain.PictureDto;
import com.travel360.travel360Server.domain.PictureGroupDto;
import com.travel360.travel360Server.domain.SearchTravelDto;
import com.travel360.travel360Server.domain.TravelRecordCommentDto;
import com.travel360.travel360Server.domain.TravelRecordDto;
import com.travel360.travel360Server.domain.UserDto;
import com.travel360.travel360Server.service.TravelService;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.model.Picture;

@Controller
public class TravelController {
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	TravelService travelService;
	
	@ResponseBody
	@RequestMapping(value="/Image.do", produces = MediaType.IMAGE_JPEG_VALUE)
	public  byte[] getImage(String imageName,@RequestParam(defaultValue="100")int width,@RequestParam(defaultValue="100")int height,@RequestParam(defaultValue="false")boolean resize,HttpSession session) throws IOException{
		
		return travelService.getImage(imageName,width,height,resize);
	}
	
	@RequestMapping(value="/video.do", produces = MediaType.IMAGE_JPEG_VALUE)
	public  byte[] getVideo(String videoName,HttpSession session) throws IOException{
		
		InputStream in = servletContext.getResourceAsStream("/WEB-INF/image/test.jpg");
		return IOUtils.toByteArray(in);
	}
	
	@RequestMapping("/writeRecordReady.do")
	public ModelAndView writeRecordReady(UserDto user,HttpSession session){
		
		int travelSeq = travelService.WriteReady(user);
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		modelAndView.addObject("travelSeq",travelSeq);
		
		return modelAndView;
	}
	
	@RequestMapping("/uploadImage.do")
	public ModelAndView uploadImage(int userSeq,int groupSeq,int travelSeq,MultipartFile image,HttpSession session) throws IOException{
		
		UserDto user = new UserDto();
		user.setSeq(userSeq);
		
		PictureDto picture = travelService.uploadImage(user,groupSeq,travelSeq,image);
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		modelAndView.addObject("picture",picture);

		return modelAndView;
	}

	@RequestMapping("/uploadVideo.do")
	public ModelAndView uploadVideo(UserDto user,TravelRecordDto travelRecordDto,MultipartFile image,HttpSession session){
		
		String imageName = travelService.uploadVideo(user,travelRecordDto,image);
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		modelAndView.addObject("imageName",imageName);
		
		return modelAndView;
	}
	
	@RequestMapping("/writeComplete.do")
	public ModelAndView writeComplete(int userSeq,TravelRecordDto travelRecordDto,HttpSession session){
		
		UserDto user = new UserDto();
		user.setSeq(userSeq);
		
		boolean check = travelService.writeComplete(user,travelRecordDto);
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		modelAndView.addObject("check", check);
		
		return modelAndView;
	}
	
	@RequestMapping("/getTravelRecord.do")
	public ModelAndView getTravelRecord(TravelRecordDto travelRecordDto,HttpSession session){
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		travelRecordDto = travelService.getTravelRecord(travelRecordDto);
		List<PictureDto> images = travelService.getTravelImageList(travelRecordDto);
		
		modelAndView.addObject("travel",travelRecordDto);
		modelAndView.addObject("images", images);
		
		return modelAndView;
	}
	@RequestMapping("/getMainPageTravel.do")
	public ModelAndView getMainPageTravel()
	{
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		List<TravelRecordDto> travels = travelService.getMainPageTravels();
		modelAndView.addObject("travel",travels);
		
		return modelAndView;
	}
	
	@RequestMapping("/getTravelImageList.do")
	public ModelAndView getTravelImageList(TravelRecordDto travelRecordDto,HttpSession session){
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		List<PictureDto> images = travelService.getTravelImageList(travelRecordDto);
		modelAndView.addObject("images", images);
		return modelAndView;
	}
	
	@RequestMapping(value="/getTravelRecordList.do")
	public ModelAndView getTravelRecordList(SearchTravelDto searchOption, HttpSession session){
		
		List<TravelRecordDto> travels = travelService.getTravelList(searchOption);
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		modelAndView.addObject("travels",travels);
		
		return modelAndView;
	}
	
	@RequestMapping("/getTravelCommentList.do")
	public ModelAndView getTravelCommentList(int travelSeq, HttpSession session){
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		List<TravelRecordCommentDto> commentList = travelService.getTravelCommentList(travelSeq);
		modelAndView.addObject("comment",commentList);	
		
		return modelAndView;
	}
	
	@RequestMapping("/writeComment.do")
	public ModelAndView writeComment(TravelRecordCommentDto comment,int userSeq,int travelSeq, HttpSession session){
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		boolean check = travelService.insertTravelComment(comment,userSeq,travelSeq);
		modelAndView.addObject(check);
		
		return modelAndView;
	}
}
