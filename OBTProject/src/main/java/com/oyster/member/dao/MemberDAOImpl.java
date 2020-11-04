package com.oyster.member.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.oyster.member.vo.MemberVO;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	   public MemberVO standard_login(MemberVO memberVO) {
	      MemberVO member = (MemberVO)sqlSession.selectOne("mapper.member.standard_login",memberVO);
	      System.out.println("왔니?" + member);
	      return member;
	   }

	@Override
	public void insertNewMember(MemberVO memberVO) throws DataAccessException {
		sqlSession.insert("mapper.member.insertNewMember",memberVO);
	}

	@Override
	public String selectOverlappedID(String id) throws DataAccessException {
		String result =  sqlSession.selectOne("mapper.member.selectOverlappedID",id);
		return result;
	}
	
	@Override
	public void removeMember(MemberVO memberVO) throws DataAccessException {
		System.out.println("다오 memberVO>>>>>>>"+memberVO);
		sqlSession.insert("mapper.member.memberDelete",memberVO);
	}
	
}
