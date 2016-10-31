package com.travel360.travel360Server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.travel360.travel360Server.dao.GpsMessageDao;
import com.travel360.travel360Server.domain.GPSMessageDto;
import com.travel360.travel360Server.domain.TravelReviewDto;
import com.travel360.travel360Server.service.GPSService;

@Controller
public class GPSController {
	
	@Autowired
	GPSService gpsService;
	
	@RequestMapping("/insertMessage.do")
	public ModelAndView insertMessage(GPSMessageDto message)
	{
		gpsService.insertMessage(message);
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		return modelAndView;
	}
	
	@RequestMapping("/localMessage.do")
	public ModelAndView localMessage(double lat, double lon)
	{

		ModelAndView modelAndView = new ModelAndView("jsonView");
		List<GPSMessageDto> messageList = gpsService.localMessage(lat,lon);
		
		modelAndView.addObject("messages", messageList);
		return modelAndView;
	}
}
