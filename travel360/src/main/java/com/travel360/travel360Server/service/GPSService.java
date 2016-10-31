package com.travel360.travel360Server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel360.travel360Server.dao.GpsMessageDao;
import com.travel360.travel360Server.domain.GPSMessageDto;

@Service
public class GPSService {
	@Autowired
	GpsMessageDao gpsMessageDao;
	
	public void insertMessage(GPSMessageDto message)
	{
		gpsMessageDao.insertMessage(message);
	}

	public List<GPSMessageDto> localMessage(double lat, double lon) {
		GPSMessageDto gps = new GPSMessageDto();
		gps.setLat(lat);
		gps.setLon(lon);
		
		return gpsMessageDao.selectLocalMessage(gps);
	}

}
