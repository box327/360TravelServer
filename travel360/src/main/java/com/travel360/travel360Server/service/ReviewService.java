package com.travel360.travel360Server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel360.travel360Server.dao.ReviewDao;
import com.travel360.travel360Server.domain.TravelRecordCommentDto;
import com.travel360.travel360Server.domain.TravelReviewComment;
import com.travel360.travel360Server.domain.TravelReviewDto;

@Service
public class ReviewService {

	@Autowired
	ReviewDao reviewDao;
	
	public void writeTravelReview(TravelReviewDto review, int userSeq) {
		review.setUser_info_seq(userSeq);
		reviewDao.insertReview(review);
	}

	public void writeReviewComment(TravelReviewComment comment, int reviewSeq, int userSeq) {
		comment.setUser_info_seq(userSeq);
		comment.setTravel_review_seq(reviewSeq);
		reviewDao.insertReviewComment(comment);
	}

	public List<TravelReviewComment> getReviewCommentList(int reviewSeq) {
		return reviewDao.getReviewCommentList(reviewSeq);
	}

	public List<TravelReviewDto> getReviewRankingList() {
		
		return reviewDao.getReviewRankingList();
	}

	public List<TravelReviewDto> getReviewList(String location) {
		TravelReviewDto review = new TravelReviewDto();
		review.setLocation(location);
		return reviewDao.getReviewList(review);
	}

	public TravelReviewDto getReview(int reviewSeq) {
		TravelReviewDto review = new TravelReviewDto();
		review.setSeq(reviewSeq);
		return reviewDao.getReview(review);
	}

}
