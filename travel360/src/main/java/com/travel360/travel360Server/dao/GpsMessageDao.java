package com.travel360.travel360Server.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel360.travel360Server.domain.GPSMessageDto;

@Repository
public class GpsMessageDao {
	@Autowired
	SqlSessionTemplate sessionTemplate;
	
	public void insertMessage(GPSMessageDto gpsMessage)
	{
		sessionTemplate.insert("gps.insertMessage",gpsMessage);
	}

	public List<GPSMessageDto> selectLocalMessage(GPSMessageDto gps) {
		return sessionTemplate.selectList("gps.selectLocalMessage",gps);
	}
}
