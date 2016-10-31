package com.travel360.travel360Server.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.jdbc.interceptors.ServerStatusDiffInterceptor;
import com.travel360.travel360Server.domain.PictureDto;
import com.travel360.travel360Server.domain.SearchTravelDto;
import com.travel360.travel360Server.domain.TravelRecordCommentDto;
import com.travel360.travel360Server.domain.TravelRecordDto;
import com.travel360.travel360Server.domain.UserDto;

@Repository
public class TravelDao {

	@Autowired
	SqlSessionTemplate sessionTemplate;
	
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public int allocateTravelRecord(TravelRecordDto travel) {
		
		return sessionTemplate.insert("travel.allocateRecord",travel);
		
	}


	public PictureDto getImage(PictureDto picture)
	{
		return (PictureDto)sessionTemplate.selectOne("travel.getImage", picture);
	}
	
	public void uploadImage(PictureDto picture) {
		sessionTemplate.insert("travel.uploadImage",picture);
	}

	public List<PictureDto> getImageList(TravelRecordDto travelRecordDto) {
		List<PictureDto> picture = sessionTemplate.selectList("travel.getImageList",travelRecordDto);
		
		return picture;
	}

	public List<TravelRecordDto> getTravelList(SearchTravelDto searchOption) {
		List<TravelRecordDto> travels = sessionTemplate.selectList("travel.getTravelList",searchOption);
		return travels;
	}

	public TravelRecordDto getTravelRecord(TravelRecordDto travelRecordDto) {
		TravelRecordDto travel = sessionTemplate.selectOne("travel.getTravelRecord",travelRecordDto);
		return travel;
	}

	public int insertTravelRecord(TravelRecordDto travelRecordDto) {
		return sessionTemplate.update("travel.updateTravelRecord",travelRecordDto);
	}

	public boolean insertTravelComment(TravelRecordCommentDto comment) {
		int check = sessionTemplate.insert("travel.insertTravelComment",comment);
		if(check == 0)
			return false;
		else
			return true;
	}

	public List<TravelRecordCommentDto> getTravelCommentList(TravelRecordDto travel) {
		return sessionTemplate.selectList("travel.getTravelCommentList", travel);
	}


	public List<TravelRecordDto> getManyCommentTravel() {
		
		return sessionTemplate.selectList("travel.getManyCommentTravels");
	}
	
	

}
