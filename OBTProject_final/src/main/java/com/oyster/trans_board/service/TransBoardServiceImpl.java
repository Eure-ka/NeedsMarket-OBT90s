package com.oyster.trans_board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


import com.oyster.reply.dao.ReplyDAOImpl;
import com.oyster.reply.vo.ReplyVO;
import com.oyster.trans_board.dao.TransBoardDAOImpl;
import com.oyster.trans_board.vo.ImageVO;
import com.oyster.trans_board.vo.TransBoardVO;

@Service("transBoardService")
@Transactional
public class TransBoardServiceImpl implements TransBoardService {

	@Autowired
	TransBoardDAOImpl transBoardDAO;
	@Autowired
	ReplyDAOImpl replyDAO;

	public List<TransBoardVO> listArticles() throws Exception {
		List<TransBoardVO> articlesList = transBoardDAO.selectAllArticlesList();
		return articlesList;
	}

	@Override
	public int addNewArticle(Map articleMap) throws Exception {
		int tb_number = transBoardDAO.insertNewArticle(articleMap);
		articleMap.put("tb_number", tb_number);
		transBoardDAO.insertNewImage(articleMap);

		return tb_number;
	}

	@Override
	public void removeArticle(int tb_number) throws Exception {
		transBoardDAO.deleteArticle(tb_number);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	   @Override
	   public Map<String, Object> viewArticle(int tb_number) throws Exception {
	   TransBoardVO transBoardVO = transBoardDAO.selectArticle(tb_number);
	      Map<String, Object> resultMap = new HashMap<String, Object>();
	      resultMap.put("transBoard", transBoardVO);
	      String boardKind = "tb";
	      List<ReplyVO> replList = replyDAO.selectAllReplyList(tb_number, boardKind);
	      resultMap.put("replList", replList);
	      List<ImageVO> imageFileList = transBoardDAO.selectImageFileList(tb_number);
	      resultMap.put("imageFileList", imageFileList);
	      transBoardDAO.boardHit(tb_number);
	      return resultMap;
	   }


	@Override
	public void modArticle(Map articleMap) throws Exception {
		transBoardDAO.updateArticle(articleMap);
	}
	
	@Override
	   public void recommend(int tb_number) throws Exception {
	      System.out.println("설마 서비스도 찍히냐?? tb_number>>>>>"+tb_number);
	      transBoardDAO.recommend(tb_number);
	      }
	
}