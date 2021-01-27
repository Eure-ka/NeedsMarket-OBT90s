package com.oyster.free_board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.oyster.free_board.vo.FreeBoardVO;


public interface FreeBoardDAO {
	public List selectAllArticlesList() throws DataAccessException;
	public int insertNewArticle(Map articleMap) throws DataAccessException;
	//public void insertNewImage(Map articleMap) throws DataAccessException;
	
	public FreeBoardVO selectArticle(int articleNO) throws DataAccessException;
	public void updateArticle(Map articleMap) throws DataAccessException;
	public void deleteArticle(int articleNO) throws DataAccessException;
	public List selectImageFileList(int articleNO) throws DataAccessException;
	
}
