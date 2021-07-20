package com.oyster.member.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oyster.member.dao.MemberDAO;
import com.oyster.member.vo.MemberVO;

@Service("memberService")
@Transactional(propagation = Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDAO;

	@Override
	public MemberVO standard_login(MemberVO memberVO) throws Exception {
		return memberDAO.standard_login(memberVO);
	}

	@Override
	public void addMember(MemberVO memberVO) throws Exception {
		memberDAO.insertNewMember(memberVO);

	}

	@Override
	public String overlapped(String id) throws Exception {
		return memberDAO.selectOverlappedID(id);
	}

	@Override
	public void removeMember(MemberVO memberVO) throws Exception {
		memberDAO.removeMember(memberVO);
	}

	@Override
	public void modmember(Map memberInfoMap) throws Exception {
		memberDAO.updatemember(memberInfoMap);
	}
}
