package com.oyster.reply.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oyster.reply.dao.ReplyDAOImpl;
import com.oyster.reply.vo.ReplyVO;

@Service("replyService")
@Transactional
public class ReplyServiceImpl {

	@Autowired
	ReplyDAOImpl replyDAO;

	public int addNewReply(ReplyVO replyVO) {
		return replyDAO.addNewReply(replyVO);
	}

	public ReplyVO viewReply(int reply_no) {
		ReplyVO replyVO = replyDAO.selectReply(reply_no);
		return replyVO;
	}

	public void deleteReply(int reply_no) throws Exception{
        replyDAO.deleteReply(reply_no);
	
	}

	
	
	
}
