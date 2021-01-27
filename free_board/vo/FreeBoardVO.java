package com.oyster.free_board.vo;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component("freeBoardVO")
public class FreeBoardVO {
	private int fb_number;/* 자유글 번호 */
	private String fb_title;/* 자유글 제목 */
	private String fb_content;/* 자유글 내용 */
	private Date fb_write_date; /* 작성일 */
	private int fb_countnum; /* 조회수 */
	private int fb_likenum ; /* 좋아요 */
	private String member_id;
	
	
	
	public int getFb_number() {
		return fb_number;
	}
	public void setFb_number(int fb_number) {
		this.fb_number = fb_number;
	}
	public String getFb_title() {
		return fb_title;
	}
	public void setFb_title(String fb_title) {
		this.fb_title = fb_title;
	}
	public String getFb_content() {
		return fb_content;
	}
	public void setFb_content(String fb_content) {
		this.fb_content = fb_content;
	}
	public Date getFb_write_date() {
		return fb_write_date;
	}
	public void setFb_write_date(Date fb_write_date) {
		this.fb_write_date = fb_write_date;
	}
	public int getFb_countnum() {
		return fb_countnum;
	}
	public void setFb_countnum(int fb_countnum) {
		this.fb_countnum = fb_countnum;
	}
	public int getFb_likenum() {
		return fb_likenum;
	}
	public void setFb_likenum(int fb_likenum) {
		this.fb_likenum = fb_likenum;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
}
