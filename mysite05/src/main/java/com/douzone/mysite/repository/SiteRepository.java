package com.douzone.mysite.repository;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.SiteVo;

@Repository
public class SiteRepository {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;

	public SiteVo find() {
		return sqlSession.selectOne("site.find");
	}

	public boolean update(SiteVo vo) {
		int count = sqlSession.update("site.update", vo);
		return count == 1;
	}
}