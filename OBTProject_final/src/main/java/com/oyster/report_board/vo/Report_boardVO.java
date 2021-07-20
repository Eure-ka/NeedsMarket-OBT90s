package com.oyster.report_board.vo;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component("report_boardVO")
public class Report_boardVO {
	private int rb_level;
    private int rb_number;
    private String rb_title;
    private String rb_content;
    private int parent_no;
    private Date write_date;
    private int rb_countnum;
	private int rb_likenum;
    private String member_id;
	public int getRb_number() {
		return rb_number;
	}
	public void setRb_number(int rb_number) {
		this.rb_number = rb_number;
	}
	public String getRb_title() {
		return rb_title;
	}
	public void setRb_title(String rb_title) {
		this.rb_title = rb_title;
	}
	public String getRb_content() {
		return rb_content;
	}
	public void setRb_content(String rb_content) {
		this.rb_content = rb_content;
	}
	public int getParent_no() {
		return parent_no;
	}
	public void setParent_no(int parent_no) {
		this.parent_no = parent_no;
	}
	public Date getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}
	public int getRb_countnum() {
		return rb_countnum;
	}
	public void setRb_countnum(int rb_countnum) {
		this.rb_countnum = rb_countnum;
	}
	public int getRb_likenum() {
		return rb_likenum;
	}
	public void setRb_likenum(int rb_likenum) {
		this.rb_likenum = rb_likenum;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getRb_level() {
		return rb_level;
	}
	public void setRb_level(int rb_level) {
		this.rb_level = rb_level;
	}
	
	
	
	
}
