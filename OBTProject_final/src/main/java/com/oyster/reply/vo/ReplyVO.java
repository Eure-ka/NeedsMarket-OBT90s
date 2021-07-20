package com.oyster.reply.vo;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component("replyVO")
public class ReplyVO {

	private int reply_no; /* 댓글 번호 */
	private String reply_content; /* 댓글 내용 */
	private Date reply_date;  /* 작성일 */
	private String fb_number; /* 자유글 번호 */
	private String tb_number; /* 거래글 번호 */
	private String rb_number; /* 신고글 번호 */
	private String member_id;/* 아이디 */
	
	public int getReply_no() {
		return reply_no;
	}

	public void setReply_no(int reply_no) {
		this.reply_no = reply_no;
	}

	public String getReply_content() {
		return reply_content;
	}

	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}

	public Date getReply_date() {
		return reply_date;
	}

	public void setReply_date(Date reply_date) {
		this.reply_date = reply_date;
	}

	public String getFb_number() {
		return fb_number;
	}

	public void setFb_number(String fb_number) {
		this.fb_number = fb_number;
	}

	public String getTb_number() {
		return tb_number;
	}

	public void setTb_number(String tb_number) {
		this.tb_number = tb_number;
	}

	public String getRb_number() {
		return rb_number;
	}

	public void setRb_number(String rb_number) {
		this.rb_number = rb_number;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	@Override
	public String toString() {
		return "ReplyVO [reply_no=" + reply_no + ", reply_content=" + reply_content + ", reply_date=" + reply_date
				+ ", fb_number=" + fb_number + ", tb_number=" + tb_number + ", rb_number=" + rb_number + ", member_id="
				+ member_id + "]";
	}
	
	
	
}
