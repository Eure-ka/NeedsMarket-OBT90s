package com.oyster.reply.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.oyster.reply.vo.ReplyVO;

public interface ReplyDAO {
	public List<ReplyVO> selectAllReplyList(int rb_number, String boardKind) throws DataAccessException;
	public int addNewReply(ReplyVO replyVO) throws DataAccessException;
}
