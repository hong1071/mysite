package com.douzone.mysite.service;

import java.util.List;

import javax.servlet.RequestDispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	public List<BoardVo> listAll() {
		
		// 전체 글 갯수의 길이를 구해 paging을 위한 수를 구한다.
		List<BoardVo> AllList = boardRepository.findAll();
		
		return AllList;
		
	}

	public List<BoardVo> listPageNum(int pageNo) {

		//페이지에 따른 글 갯수를 나타낸다.
		List<BoardVo> list = boardRepository.findByPage(pageNo - 1);
		
		return list;
		
	}

	public BoardVo view(int no) {

		BoardVo vo = new BoardVo();
		vo = boardRepository.findByNo(no);
		return vo;
		
	}

	public void insert(BoardVo vo) {
		boardRepository.insert(vo);
		
	}

	public BoardVo findByNo(int boardNo) {
		BoardVo vo = boardRepository.findByNo(boardNo);
		return vo;
	}

	public void update(BoardVo vo) {
		boardRepository.Update(vo);
		
	}

	public void delete(int boardNo) {
		boardRepository.delete(boardNo);
		
	}
	
}
