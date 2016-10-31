package com.travel360.travel360Server.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel360.travel360Server.domain.TravelReviewComment;
import com.travel360.travel360Server.domain.TravelReviewDto;

@Repository
public class ReviewDao {
	@Autowired
	SqlSessionTemplate sessionTemplate;

	public void insertReview(TravelReviewDto review) {
		sessionTemplate.insert("review.insertReview",review);
	}

	public void insertReviewComment(TravelReviewComment comment) {
		sessionTemplate.insert("review.insertReviewComment",comment);
	}

	public List<TravelReviewComment> getReviewCommentList(int reviewSeq) {
		return null;
	}

	public List<TravelReviewDto> getReviewRankingList() {
		return sessionTemplate.selectList("review.getReviewRankingList");
	}

	public List<TravelReviewDto> getReviewList(TravelReviewDto review) {
		return sessionTemplate.selectList("review.getReviewList",review);
	}

	public TravelReviewDto getReview(TravelReviewDto review) {
		return sessionTemplate.selectOne("review.getReview",review);
	}	
}
