package com.oyster.free_board.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.oyster.free_board.vo.FreeBoardVO;

@Service("freeBoardService")
public class FreeBoardServiceImpl implements FreeBoardService  {

	@Override
	public List<FreeBoardVO> listArticles() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addNewArticle(Map articleMap) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FreeBoardVO viewArticle(int articleNO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modArticle(Map articleMap) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeArticle(int articleNO) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
