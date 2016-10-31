package com.travel360.travel360Server.service;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletContext;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.apache.commons.imaging.formats.tiff.TiffField;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata;
import org.apache.commons.imaging.formats.tiff.constants.ExifTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.GpsTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.TiffTagConstants;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.impl.Log4JLogger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.travel360.travel360Server.dao.TravelDao;
import com.travel360.travel360Server.domain.PictureDto;
import com.travel360.travel360Server.domain.SearchTravelDto;
import com.travel360.travel360Server.domain.TravelRecordCommentDto;
import com.travel360.travel360Server.domain.TravelRecordDto;
import com.travel360.travel360Server.domain.UserDto;


@Service
public class TravelService {

	@Autowired
	ServletContext servletContext;
	
	@Autowired
	TravelDao travelDao; 
	
	public int WriteReady(UserDto user) {
		
		TravelRecordDto travel = new TravelRecordDto();
		travel.setUser_info_seq(user.getSeq());
		travelDao.allocateTravelRecord(travel);
		System.out.println(travel.getSeq());
		return travel.getSeq();
	}

	
	public PictureDto uploadImage(UserDto user, int groupId, int travelSeq, MultipartFile image) throws IOException {
		Calendar date = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String filename = sdf.format(date.getTime()) + image.getOriginalFilename();
		
		String path = servletContext.getRealPath("/WEB-INF/image/" + filename);
		System.out.println(path);
		
		FileOutputStream output = new FileOutputStream(path);
		
		output.write(image.getBytes());
		output.close();
		
		PictureDto picture = new PictureDto();
		
		picture.setPicture_loc(filename);
		picture.setPicture_type(image.getContentType());
		picture.setPicture_group_seq(groupId);
		picture.setTravel_record_seq(travelSeq);
		picture.setUser_info_seq(user.getSeq());
		
		travelDao.uploadImage(picture);
		
	
		return picture;
	}

	public String uploadVideo(UserDto user, TravelRecordDto travelRecordDto, MultipartFile image) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean writeComplete(UserDto user, TravelRecordDto travelRecordDto) {
		
		PictureDto target = new PictureDto();
		target.setSeq(Integer.parseInt(travelRecordDto.getPresentation_image()));
		
		target = travelDao.getImage(target);
		String path = servletContext.getRealPath("/WEB-INF/image/" + target.getPicture_loc());
		
		File image = new File(path);
		
		setGpsForTravel(image, travelRecordDto);
		
		
		int check = travelDao.insertTravelRecord(travelRecordDto);
		if(check == 0)
			return false;
		else
			return true;
	}

	public List<PictureDto> getTravelImageList(TravelRecordDto travelRecordDto) {
		List<PictureDto> images = travelDao.getImageList(travelRecordDto);
		
		return images;
	}

	public byte[] getImage(String imageName, int width, int height, boolean resize) throws IOException {
		InputStream in = servletContext.getResourceAsStream("/WEB-INF/image/"+imageName);
		
		if(resize == false)
			return IOUtils.toByteArray(in);
		
		BufferedImage image = ImageIO.read(in);
		
		BufferedImage resized = getScaledInstance(image, width, height, RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(resized, "jpg", out);
		
		out.flush();
		
		byte[] imageInByte = out.toByteArray();
		
		out.close();
		
		return imageInByte;
	}

	public List<TravelRecordDto> getTravelList(SearchTravelDto searchOption) {
		System.out.println(searchOption);
		return travelDao.getTravelList(searchOption);
	}

	public TravelRecordDto getTravelRecord(TravelRecordDto travelRecordDto) {
		
		return travelDao.getTravelRecord(travelRecordDto);
	}

	public boolean insertTravelComment(TravelRecordCommentDto comment, int userSeq, int travelSeq) {
		comment.setUser_info_seq(userSeq);
		comment.setTravel_record_seq(travelSeq);
		
		return travelDao.insertTravelComment(comment);
	}

	public List<TravelRecordCommentDto> getTravelCommentList(int travelSeq) {
		TravelRecordDto travel = new TravelRecordDto();
		travel.setSeq(travelSeq);
		
		return travelDao.getTravelCommentList(travel);
	}

	
	private void setGpsForTravel(File image, TravelRecordDto travelRecordDto)
	{
		try {
			final ImageMetadata metadata = Imaging.getMetadata(image);
			if (metadata instanceof JpegImageMetadata) {
	            final JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;

	            // Jpeg EXIF metadata is stored in a TIFF-based directory structure
	            // and is identified with TIFF tags.
	            // Here we look for the "x resolution" tag, but
	            // we could just as easily search for any other tag.
	            //
	            // see the TiffConstants file for a list of TIFF tags.

	            // simple interface to GPS data
	            final TiffImageMetadata exifMetadata = jpegMetadata.getExif();
	            if (null != exifMetadata) {
	                final TiffImageMetadata.GPSInfo gpsInfo = exifMetadata.getGPS();
	                if (null != gpsInfo) {
	                    final double longitude = gpsInfo.getLongitudeAsDegreesEast();
	                    final double latitude = gpsInfo.getLatitudeAsDegreesNorth();
	                    
	                    travelRecordDto.setLongitude(longitude);
	                    travelRecordDto.setLatitude(latitude);
	                    
	                    String address = getAddressByGpsCoordinates(longitude +"", latitude + "");
	                    
	                    String[] spliteAddress = address.split(",");
	                    
	                    travelRecordDto.setCountry(spliteAddress[0]);
	                    travelRecordDto.setCity(spliteAddress[1]);
	                    
	                }
	            }
			}
		} catch (ImageReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String getAddressByGpsCoordinates(String lng, String lat)
            throws MalformedURLException, IOException, org.json.simple.parser.ParseException {
         
        URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?latlng="
                + lat + "," + lng + "&sensor=true");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        String formattedAddress = "";
 
        try {
            InputStream in = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String result, line = reader.readLine();
            result = line;
            while ((line = reader.readLine()) != null) {
                result += line;
            }
 
            JSONParser parser = new JSONParser();
            JSONObject rsp = (JSONObject) parser.parse(result);
 
            if (rsp.containsKey("results")) {
                JSONArray matches = (JSONArray) rsp.get("results");
                JSONObject data = (JSONObject) matches.get(0); //TODO: check if idx=0 exists
                formattedAddress = (String) data.get("formatted_address");
            }
 
            return "";
        } finally {
            urlConnection.disconnect();
            return formattedAddress;
        }

    }
    private static BufferedImage getScaledInstance(BufferedImage img, int targetWidth, int targetHeight, Object hint,
			boolean higherQuality) {
		int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB
				: BufferedImage.TYPE_INT_ARGB;
		BufferedImage ret = (BufferedImage) img;
		if (ret.getHeight() < targetHeight || ret.getWidth() < targetWidth) {
			higherQuality = false;
		}
		int w, h;
		if (higherQuality) {
			// Use multi-step technique: start with original size, then
			// scale down in multiple passes with drawImage()
			// until the target size is reached
			w = img.getWidth();
			h = img.getHeight();
		} else {
			// Use one-step technique: scale directly from original
			// size to target size with a single drawImage() call
			w = targetWidth;
			h = targetHeight;
		}

		do {
			if (higherQuality && w > targetWidth) {
				w /= 2;
				if (w < targetWidth) {
					w = targetWidth;
				}
			}

			if (higherQuality && h > targetHeight) {
				h /= 2;
				if (h < targetHeight) {
					h = targetHeight;
				}
			}

			BufferedImage tmp = new BufferedImage(w, h, type);
			Graphics2D g2 = tmp.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
			g2.drawImage(ret, 0, 0, w, h, null);
			g2.dispose();

			ret = tmp;
		} while (w != targetWidth || h != targetHeight);

		return ret;
	}


	public List<TravelRecordDto> getMainPageTravels() {
		List<TravelRecordDto> travels = travelDao.getManyCommentTravel();
		
		Random rand = new Random();
		
		if(travels.size() == 0)
			return travels;
		
		System.out.println(travels.size());
		
		while(travels.size() < 3)
		{
			travels.remove(rand.nextInt(travels.size()));
		}
		
		return travels;
	}
	
}
