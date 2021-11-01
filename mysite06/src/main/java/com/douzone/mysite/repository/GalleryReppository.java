package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GalleryVo;

@Repository
public class GalleryReppository {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(String url, String comments) {
		
		Map<String, String> map = new HashMap<>();
		map.put("u", url);
		map.put("c", comments);
		int count = sqlSession.insert("gallery.insert", map);
		return count == 1;
	}
	
	public List<GalleryVo> findAll() {
		return sqlSession.selectList("gallery.findAll");
	}

	public void delete(int no) {
		sqlSession.delete("gallery.delete", no);
		
	}
}
