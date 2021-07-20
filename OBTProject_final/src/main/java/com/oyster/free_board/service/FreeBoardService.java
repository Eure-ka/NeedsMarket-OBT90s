package com.oyster.free_board.service;

import java.util.List;
import java.util.Map;

import com.oyster.free_board.vo.FreeBoardVO;


public interface FreeBoardService {
	public List<FreeBoardVO> listArticles() throws Exception;
	public int addNewArticle(Map articleMap) throws Exception;
	public Map<String, Object> viewArticle(int fb_number) throws Exception;
	public void modArticle(Map articleMap) throws Exception;
	public void removeArticle(int fb_number) throws Exception;
	public void recommend(int fb_number) throws Exception;
}
