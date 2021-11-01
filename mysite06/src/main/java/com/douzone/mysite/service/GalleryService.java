package com.douzone.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.repository.GalleryReppository;
import com.douzone.mysite.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryReppository galleryRepository;
	
	private static String SAVE_PATH = "/upload-mysite";
	private static String URL_BASE = "/images";
	
	public String restore(MultipartFile multipartFile) {

		String url = null;
		try {
			if(multipartFile.isEmpty()) {
				return url;
			}
			
			String originFileName = multipartFile.getOriginalFilename();
			String extName = originFileName.substring(originFileName.lastIndexOf('.') + 1); 
			String saveFileName = generateSaveFileName(extName);
			long fileSize = multipartFile.getSize();
			
			System.out.println("#######" + originFileName);
			System.out.println("#######" + fileSize);
			System.out.println("#######" + saveFileName);
			
			byte[] data = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
			os.write(data);
			os.close();
			
			url = URL_BASE + "/" + saveFileName;
			System.out.println(url);
			
		} catch(IOException ex) {
			throw new RuntimeException("file upload error:" + ex);
		}
		
		return url;
	}

	private String generateSaveFileName(String extName) {
		
		String filename = "";
		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		
		return filename;
	}

	public void add(String url, String comments) {
	
		galleryRepository.insert(url, comments);
	}
	
	public List<GalleryVo> list() {
		
		List<GalleryVo> list = galleryRepository.findAll();
		return list;
	}

	public void delete(int no) {
		galleryRepository.delete(no);
		
	}
	
}
