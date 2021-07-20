package com.oyster.trans_board.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.oyster.trans_board.vo.ImageVO;
import com.oyster.trans_board.vo.TransBoardVO;


@Repository("transBoardDAO")
public class TransBoardDAOImpl implements TransBoardDAO{

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<TransBoardVO> selectAllArticlesList() throws DataAccessException {
		List<TransBoardVO> articlesList = sqlSession.selectList("mapper.transboard.selectAllArticlesList");
		return articlesList;
	}
	
	@Override
	public void deleteArticle(int tb_number) throws DataAccessException {
		sqlSession.delete("mapper.transboard.deleteArticle", tb_number);
		
	}
	
	@Override
	public int insertNewArticle(Map articleMap) throws DataAccessException {
		//마지막 게시글 번호에 1을 더한 숫자를 받아옴
		int tb_number = selectNewArticleNO();
		//매개변수로 받은 맵에 글번호 추가
		articleMap.put("tb_number", tb_number);
		sqlSession.insert("mapper.transboard.insertNewArticle",articleMap);
		
		return tb_number;
	}
	@Override
	public void insertNewImage(Map articleMap) throws DataAccessException {
		List<ImageVO> imageFileList = (ArrayList)articleMap.get("imageFileList");
		int tb_number = (Integer)articleMap.get("tb_number");
		int imageFileNO = selectNewImageFileNO();
		for(ImageVO imageVO : imageFileList){
			imageVO.setImage_no(++imageFileNO);
			imageVO.settb_number(tb_number);
		}
		sqlSession.insert("mapper.transboard.insertNewImage",imageFileList);
	}
	
	@Override
	public TransBoardVO selectArticle(int tb_number) throws DataAccessException {
		return sqlSession.selectOne("mapper.transboard.selectArticle", tb_number);
	}

	@Override
	public void boardHit(int tb_number) throws Exception {
		sqlSession.update("mapper.transboard.boardHit", tb_number);
	}
	@Override
	public List selectImageFileList(int tb_number) throws DataAccessException {
		List<ImageVO> imageFileList = null;
		imageFileList = sqlSession.selectList("mapper.transboard.selectImageFileList",tb_number);
		return imageFileList;
	}
	private int selectNewArticleNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.transboard.selectNewArticleNO");
	}
	
	private int selectNewImageFileNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.transboard.selectNewImageFileNO");
	}
	
	
	@Override
	public void updateArticle(Map articleMap) throws DataAccessException{
		sqlSession.selectOne("mapper.transboard.updateArticle",articleMap);
	}
	
	@Override
    public void recommend(int tb_number) throws Exception {
		System.out.println("다오도???"+tb_number);
        sqlSession.update("mapper.transboard.recommend", tb_number);
    }
}
