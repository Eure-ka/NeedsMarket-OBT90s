package com.oyster.report_board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;


import com.oyster.report_board.dao.Report_BoardDAO;
import com.oyster.report_board.vo.Report_boardVO;


@Repository("Report_BoardDAO")
public class Report_BoardDAOImpl implements Report_BoardDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Report_boardVO> selectAllArticlesList() throws DataAccessException {
		List<Report_boardVO> articlesList = sqlSession.selectList("mapper.report_Board.selectAllArticlesList");
		return articlesList;
	}
	
	@Override
	public void deleteArticle(int rb_number) throws DataAccessException {
		System.out.println("다오 도착했어요");
		sqlSession.delete("mapper.report_Board.deleteArticle", rb_number);
		
	}
	
	@Override
	public int insertNewArticle(Map articleMap) throws DataAccessException {
		//마지막 게시글 번호에 1을 더한 숫자를 받아옴
		int rb_number = selectNewArticleNO();
		//매개변수로 받은 맵에 글번호 추가
		articleMap.put("rb_number", rb_number);
		sqlSession.insert("mapper.report_Board.insertNewArticle",articleMap);
		return rb_number;
	}
	
	private int selectNewArticleNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.report_Board.selectNewArticleNO");
	}
//	private int selectNewImageFileNO() throws DataAccessException {
//		return sqlSession.selectOne("mapper.report_Board.selectNewImageFileNO");
//	}
	
	@Override
	public Report_boardVO selectArticle(int rb_number) throws DataAccessException {
		return sqlSession.selectOne("mapper.report_Board.selectArticle", rb_number);
	}

	@Override
	public void boardHit(int rb_number) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("mapper.report_Board.boardHit", rb_number);
	}
//	@Override
//	public List selectImageFileList(int rb_number) throws DataAccessException {
//		// TODO Auto-generated method stub
//		return null;
//	}
}