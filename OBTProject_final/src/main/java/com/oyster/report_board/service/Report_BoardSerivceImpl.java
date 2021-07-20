package com.oyster.report_board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oyster.reply.dao.ReplyDAOImpl;
import com.oyster.reply.vo.ReplyVO;
import com.oyster.report_board.dao.Report_BoardDAO;
import com.oyster.report_board.vo.ImageVO;
import com.oyster.report_board.vo.Report_boardVO;

@Service("report_BoardSerivce")
@Transactional(propagation = Propagation.REQUIRED)
public class Report_BoardSerivceImpl implements Report_BoardSerivce {
	@Autowired
	Report_BoardDAO report_Boarddao;
	
	@Autowired
	ReplyDAOImpl replyDAO;
	

	public List<Report_boardVO> listArticles() throws Exception {
		List<Report_boardVO> articlesList = report_Boarddao.selectAllArticlesList();
		return articlesList;
	}

	@Override
	public int addNewArticle(Map articleMap) throws Exception {
		int rb_number = report_Boarddao.insertNewArticle(articleMap);
		articleMap.put("rb_number", rb_number);
		report_Boarddao.insertNewImage(articleMap);

		return rb_number;
	}

	@Override
	public void removeArticle(int rb_number) throws Exception {
		report_Boarddao.deleteArticle(rb_number);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	/*
	 * @Override public Map viewArticle(int rb_number) throws Exception { Map
	 * articleMap = new HashMap(); Report_boardVO report_boardvo =
	 * report_Boarddao.selectArticle(rb_number); List<ImageVO> imageFileList =
	 * report_Boarddao.selectImageFileList(rb_number); articleMap.put("article",
	 * report_boardvo); articleMap.put("imageFileList", imageFileList);
	 * report_Boarddao.boardHit(rb_number); return articleMap;
	 */
		
		@Override
		public Map<String, Object> viewArticle(int rb_number) throws Exception {
		Report_boardVO report_boardvo = report_Boarddao.selectArticle(rb_number);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("reportBoard", report_boardvo);
			String boardKind = "rb";
			List<ReplyVO> replList = replyDAO.selectAllReplyList(rb_number, boardKind);
			resultMap.put("replList", replList);
			List<ImageVO> imageFileList = report_Boarddao.selectImageFileList(rb_number);
			resultMap.put("imageFileList", imageFileList);
			report_Boarddao.boardHit(rb_number);
			return resultMap;
		}
	

	@Override
	public int insertNewreply(Map replyMap) throws Exception {
		int rb_number = report_Boarddao.insertNewreply(replyMap);
		replyMap.put("rb_number", rb_number);
		System.out.println("서비스 replyMap>>>>>>>>>>>"+replyMap);
		return rb_number;
	}

	@Override
	public void modArticle(Map articleMap) throws Exception {
		report_Boarddao.updateArticle(articleMap);
	}
	
	@Override
	   public void recommend(int rb_number) throws Exception {
	      System.out.println("설마 서비스도 찍히냐?? rb_number>>>>>"+rb_number);
	      report_Boarddao.recommend(rb_number);
	      }
}
