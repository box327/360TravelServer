package com.travel360.travel360Server.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.travel360.travel360Server.domain.TravelReviewComment;
import com.travel360.travel360Server.domain.TravelReviewDto;
import com.travel360.travel360Server.service.ReviewService;

@Controller
public class ReviewController {
	@Autowired
	ReviewService reviewService;
	
	@RequestMapping("/travelReviewList.do")
	public ModelAndView getReviewList(String location,HttpSession session){
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		List<TravelReviewDto> travelReview = reviewService.getReviewList(location);
		
		modelAndView.addObject("reviews", travelReview);
		
		return modelAndView;
	}
	
	@RequestMapping("/travelReviewRankingList.do")
	public ModelAndView getReviewRankingList(HttpSession session){
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		List<TravelReviewDto> travelReview = reviewService.getReviewRankingList();
		
		modelAndView.addObject("reviews", travelReview);
		
		return modelAndView;
	}
	
	@RequestMapping("/getTravelReview.do")
	public ModelAndView getTravelReview(int reviewSeq, HttpSession session){
		
		ModelAndView modelAndView = new ModelAndView("jsonView");

		TravelReviewDto travelReview = reviewService.getReview(reviewSeq);
		
		modelAndView.addObject("review", travelReview);
		
		return modelAndView;
	}
	
	@RequestMapping("/writeTravelReview.do")
	public ModelAndView writeTravelReview(TravelReviewDto review ,int userSeq, HttpSession session){
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		reviewService.writeTravelReview(review,userSeq);
		
		return modelAndView;
	}
	
	@RequestMapping("/writeReviewComment.do")
	public ModelAndView writeReviewComment(TravelReviewComment comment,int reviewSeq, int userSeq ,HttpSession session){
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		reviewService.writeReviewComment(comment,reviewSeq,userSeq);
		
		return modelAndView;
	}
	
	
	
	@RequestMapping("/getReviewCommentList.do")
	public ModelAndView getReviewCommentList(int reviewSeq, HttpSession session){
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		

		List<TravelReviewComment> commentList = reviewService.getReviewCommentList(reviewSeq);
		
		modelAndView.addObject("comment", commentList);
		
		return modelAndView;
	}
	
}
