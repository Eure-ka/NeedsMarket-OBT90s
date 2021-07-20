package com.oyster.free_board.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.oyster.free_board.vo.FreeBoardVO;
import com.oyster.free_board.vo.ImageVO;

@Repository("freeBoardDAO")
public class FreeBoardDAOImpl implements FreeBoardDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<FreeBoardVO> selectAllArticlesList() throws DataAccessException {
		List<FreeBoardVO> articlesList = sqlSession.selectList("mapper.free_board.selectAllArticlesList");
		return articlesList;
	}
	
	@Override
	public void deleteArticle(int fb_number) throws DataAccessException {
		sqlSession.delete("mapper.free_board.deleteArticle", fb_number);
		
	}
	
	@Override
	public int insertNewArticle(Map articleMap) throws DataAccessException {
		//마지막 게시글 번호에 1을 더한 숫자를 받아옴
		int fb_number = selectNewArticleNO();
		//매개변수로 받은 맵에 글번호 추가
		articleMap.put("fb_number", fb_number);
		sqlSession.insert("mapper.free_board.insertNewArticle",articleMap);
		
		return fb_number;
	}
	@Override
	public void insertNewImage(Map articleMap) throws DataAccessException {
		List<ImageVO> imageFileList = (ArrayList)articleMap.get("imageFileList");
		int fb_number = (Integer)articleMap.get("fb_number");
		int imageFileNO = selectNewImageFileNO();
		for(ImageVO imageVO : imageFileList){
			imageVO.setImage_no(++imageFileNO);
			imageVO.setfb_number(fb_number);
		}
		sqlSession.insert("mapper.free_board.insertNewImage",imageFileList);
	}
	
	@Override
	public FreeBoardVO selectArticle(int fb_number) throws DataAccessException {
		return sqlSession.selectOne("mapper.free_board.selectArticle", fb_number);
	}

	@Override
	public void boardHit(int fb_number) throws Exception {
		sqlSession.update("mapper.free_board.boardHit", fb_number);
	}
	@Override
	public List selectImageFileList(int fb_number) throws DataAccessException {
		List<ImageVO> imageFileList = null;
		imageFileList = sqlSession.selectList("mapper.free_board.selectImageFileList",fb_number);
		return imageFileList;
	}
	private int selectNewArticleNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.free_board.selectNewArticleNO");
	}
	
	private int selectNewImageFileNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.free_board.selectNewImageFileNO");
	}
	
	
	@Override
	public void updateArticle(Map articleMap) throws DataAccessException{
		sqlSession.selectOne("mapper.free_board.updateArticle",articleMap);
	}
	
	
	@Override
    public void recommend(int fb_number) throws Exception {
		System.out.println("다오도???"+fb_number);
        sqlSession.update("mapper.free_board.recommend", fb_number);
    }
}
