package com.oyster.member.service;

import java.util.Map;

import com.oyster.member.vo.MemberVO;

public interface MemberService {

	public MemberVO standard_login(MemberVO memberVO) throws Exception;
	public void addMember(MemberVO memberVO) throws Exception;
	public String overlapped(String id) throws Exception;
	public void removeMember(MemberVO memberVO) throws Exception;
	public void modmember(Map memberInfoMap) throws Exception;
	
}
