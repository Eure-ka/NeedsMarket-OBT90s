package com.oyster.trans_board.service;

import java.util.List;
import java.util.Map;


import com.oyster.trans_board.vo.TransBoardVO;

public interface TransBoardService {
	public List<TransBoardVO> listArticles() throws Exception;
	public int addNewArticle(Map articleMap) throws Exception;
	public Map<String, Object> viewArticle(int tb_number) throws Exception;
	public void modArticle(Map articleMap) throws Exception;
	public void removeArticle(int tb_number) throws Exception;
	public void recommend(int tb_number) throws Exception;
}
