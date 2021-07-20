package com.oyster.reply.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.oyster.reply.vo.ReplyVO;

@Repository("replyDAO")
public class ReplyDAOImpl {

	@Autowired
	private SqlSession sqlSession;

	public int addNewReply(ReplyVO replyVO) {
		int reply_no = selectNewReplyNO();
		replyVO.setReply_no(reply_no);

		// System.out.println("DAO new번호 가릿 insert 도착 ㅋㅋㅋㅋ >>>>>>>>>>>>>>>>>>>
		// "+replyVO);
		int result = sqlSession.insert("mapper.reply.insertNewReply", replyVO);
		// System.out.println("dao result >>> " +result);
		return result;
	}

	private int selectNewReplyNO() {
		return sqlSession.selectOne("mapper.reply.selectNewReplyNO");
	}

	public ReplyVO selectReply(int reply_no) {
		return sqlSession.selectOne("mapper.reply.selectReply", reply_no);
	}

	public List<ReplyVO> selectAllReplyList(int number, String boardKind) {
		// System.out.println("DAO selectAllReplyList 도착 ㅋㅋㅋㅋ >>>>>>>>>>>>>>>>>>> " +
		// number+ ": "+ boardKind);
		if (boardKind.equals("fb")) {
			return sqlSession.selectList("mapper.reply.select_fbReply", number);
		} else if (boardKind.equals("tb")) {
			return sqlSession.selectList("mapper.reply.select_tbReply", number);
		} else {
			return sqlSession.selectList("mapper.reply.select_rbReply", number);
		}
	}

	public void deleteReply(int reply_no) throws DataAccessException {
		// System.out.println("Remove dao>>>>>>>>>>>>>>"+reply_no);
		sqlSession.delete("mapper.reply.deleteReply", reply_no);

	}

}
