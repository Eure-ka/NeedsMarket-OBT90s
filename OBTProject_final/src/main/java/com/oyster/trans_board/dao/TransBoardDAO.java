package com.oyster.trans_board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.oyster.trans_board.vo.TransBoardVO;

public interface TransBoardDAO {
	public List selectAllArticlesList() throws DataAccessException;
	public void deleteArticle(int tb_number) throws DataAccessException;
	public int insertNewArticle(Map articleMap) throws DataAccessException;
	public void insertNewImage(Map articleMap) throws DataAccessException;
	public TransBoardVO selectArticle(int tb_number) throws DataAccessException;
	public void boardHit(int tb_number) throws Exception;
	public List selectImageFileList(int tb_number) throws DataAccessException;
	public void updateArticle(Map articleMap) throws Exception;
	public void recommend(int tb_number) throws Exception;
}
