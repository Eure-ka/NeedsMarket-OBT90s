package com.oyster.report_board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.oyster.report_board.vo.Report_boardVO;

public interface Report_BoardDAO {
	public List selectAllArticlesList() throws DataAccessException;
	public void deleteArticle(int rb_number) throws DataAccessException;
	public int insertNewArticle(Map articleMap) throws DataAccessException;
	public void insertNewImage(Map articleMap) throws DataAccessException;
	public Report_boardVO selectArticle(int rb_number) throws DataAccessException;
	public void boardHit(int rb_number) throws Exception;
	public List selectImageFileList(int rb_number) throws DataAccessException;
	public int insertNewreply(Map replyMap) throws DataAccessException;
	public void updateArticle(Map articleMap) throws Exception;
	public void recommend(int rb_number) throws Exception;
}
