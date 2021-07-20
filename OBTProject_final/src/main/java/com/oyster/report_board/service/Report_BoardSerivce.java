package com.oyster.report_board.service;

import java.util.List;
import java.util.Map;

import com.oyster.report_board.vo.Report_boardVO;

public interface Report_BoardSerivce {

	public List<Report_boardVO> listArticles() throws Exception;
	public void removeArticle(int rb_number) throws Exception;
	public int addNewArticle(Map articleMap) throws Exception;
	public Map<String, Object> viewArticle(int rb_number) throws Exception;
	public int insertNewreply(Map replyMap)throws Exception;
	public void modArticle(Map articleMap) throws Exception;
	public void recommend(int rb_number) throws Exception;
}
