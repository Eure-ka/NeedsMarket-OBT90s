package com.oyster.free_board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.oyster.free_board.vo.FreeBoardVO;

@Repository("freeBoardDAO")
public class FreeBoardDAOImpl implements FreeBoardDAO {
	
	
	
	@Override
	public List<FreeBoardVO> selectAllArticlesList() throws DataAccessException {
		
		List<FreeBoardVO> list = s
		return null;
	}

	@Override
	public int insertNewArticle(Map articleMap) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FreeBoardVO selectArticle(int articleNO) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateArticle(Map articleMap) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteArticle(int articleNO) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List selectImageFileList(int articleNO) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}