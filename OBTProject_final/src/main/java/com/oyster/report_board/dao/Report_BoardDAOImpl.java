package com.oyster.report_board.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.oyster.report_board.vo.ImageVO;
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
	@Override
	public void insertNewImage(Map articleMap) throws DataAccessException {
		List<ImageVO> imageFileList = (ArrayList)articleMap.get("imageFileList");
		int rb_number = (Integer)articleMap.get("rb_number");
		int imageFileNO = selectNewImageFileNO();
		for(ImageVO imageVO : imageFileList){
			imageVO.setImage_no(++imageFileNO);
			imageVO.setRb_number(rb_number);
		}
		sqlSession.insert("mapper.report_Board.insertNewImage",imageFileList);
	}
	
	@Override
	public Report_boardVO selectArticle(int rb_number) throws DataAccessException {
		return sqlSession.selectOne("mapper.report_Board.selectArticle", rb_number);
	}

	@Override
	public void boardHit(int rb_number) throws Exception {
		sqlSession.update("mapper.report_Board.boardHit", rb_number);
	}
	@Override
	public List selectImageFileList(int rb_number) throws DataAccessException {
		List<ImageVO> imageFileList = null;
		imageFileList = sqlSession.selectList("mapper.report_Board.selectImageFileList",rb_number);
		return imageFileList;
	}
	private int selectNewArticleNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.report_Board.selectNewArticleNO");
	}
	
	private int selectNewImageFileNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.report_Board.selectNewImageFileNO");
	}
	
	@Override
	public int insertNewreply(Map replyMap) throws DataAccessException {
		//마지막 게시글 번호에 1을 더한 숫자를 받아옴
		int rb_number = selectNewArticleNO();
		//매개변수로 받은 맵에 글번호 추가
		replyMap.put("rb_number", rb_number);
		System.out.println("replyMap>>>>>>>>>>>>"+replyMap);
		sqlSession.selectOne("mapper.report_Board.insetNewReArticle",replyMap);
		return rb_number;
	}
	
	@Override
	public void updateArticle(Map articleMap) throws DataAccessException{
		sqlSession.selectOne("mapper.report_Board.updateArticle",articleMap);
	}
	
	@Override
    public void recommend(int rb_number) throws Exception {
		System.out.println("다오도???"+rb_number);
        sqlSession.update("mapper.report_Board.recommend", rb_number);
    }
}
