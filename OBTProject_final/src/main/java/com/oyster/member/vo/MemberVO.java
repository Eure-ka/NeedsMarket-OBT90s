package com.oyster.member.vo;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component("memberVO")
public class MemberVO {

	private String member_id; /* 아이디 */
	private String member_pw; /* 비밀번호 */
	private String name; /* 이름 */
	private String gender; /* 성별 */
	private String address; /* 주소 */
	private Date joinDate; /* 가입일 */
	private String email_head; /* 이메일1 */
	private String email_tail; /* 이메일2 */
	private String email_naver; /* 네이버 아이디 */
	int birth_year; /* 생일_연도 */
	int birth_month; /* 생일_월 */
	int birth_day; /* 생일_일 */
	
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_pw() {
		return member_pw;
	}
	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getEmail_head() {
		return email_head;
	}
	public void setEmail_head(String email_head) {
		this.email_head = email_head;
	}
	public String getEmail_tail() {
		return email_tail;
	}
	public void setEmail_tail(String email_tail) {
		this.email_tail = email_tail;
	}
	public String getEmail_naver() {
		return email_naver;
	}
	public void setEmail_naver(String email_naver) {
		this.email_naver = email_naver;
	}
	public int getBirth_year() {
		return birth_year;
	}
	public void setBirth_year(int birth_year) {
		this.birth_year = birth_year;
	}
	public int getBirth_month() {
		return birth_month;
	}
	public void setBirth_month(int birth_month) {
		this.birth_month = birth_month;
	}
	public int getBirth_day() {
		return birth_day;
	}
	public void setBirth_day(int birth_day) {
		this.birth_day = birth_day;
	}

}
