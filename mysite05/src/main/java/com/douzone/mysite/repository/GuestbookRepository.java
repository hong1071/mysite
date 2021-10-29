package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.exception.GuestbookRepositoryException;
import com.douzone.mysite.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(GuestbookVo vo) {
		
		int count = sqlSession.insert("guestbook.insert", vo);
		System.out.println(vo);
		return count == 1;
	}
	
	public List<GuestbookVo> findAll() throws GuestbookRepositoryException{

		
		return sqlSession.selectList("guestbook.findAll");
	}
	
	/*  delete를 vo로 받자*/
	public Boolean delete(int no, String password) {
		Map<String, String> map = new HashMap<>();
		String guestbookNo = Integer.toString(no);
		map.put("no", guestbookNo);
		map.put("password", password);
		int result = sqlSession.delete("guestbook.delete", map);
		return result == 1;
	}
	
}
