package com.oyster.trans_board.vo;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component("transBoardVO")
public class TransBoardVO {
   private int tb_number;/* 거래글 번호 */
   private String sales_price; /* 가격 */
   private String product_name; /* 물건 이름 */
   private String trans_sort; /* 판매/거래 */
   private String contract_sort; /* 거래 방식 */
   private String tb_title; /* 거래글 제목 */
   private String tb_content; /* 거래글 내용 */
   private Date write_date;/* 작성일 */
   private int tb_countnum; /* 조회수 */
   private int tb_likenum; /* 좋아요 */
   private String member_id; /* 아이디 */
   
   
   public String getSales_price() {
      return sales_price;
   }
   public void setSales_price(String sales_price) {
      this.sales_price = sales_price;
   }
   public String getProduct_name() {
      return product_name;
   }
   public void setProduct_name(String product_name) {
      this.product_name = product_name;
   }
   public String getTrans_sort() {
      return trans_sort;
   }
   public void setTrans_sort(String trans_sort) {
      this.trans_sort = trans_sort;
   }
   public String getContract_sort() {
      return contract_sort;
   }
   public void setContract_sort(String contract_sort) {
      this.contract_sort = contract_sort;
   }
   public int getTb_number() {
      return tb_number;
   }
   public void setTb_number(int tb_number) {
      this.tb_number = tb_number;
   }
   public String getTb_title() {
      return tb_title;
   }
   public void setTb_title(String tb_title) {
      this.tb_title = tb_title;
   }
   public String getTb_content() {
      return tb_content;
   }
   public void setTb_content(String tb_content) {
      this.tb_content = tb_content;
   }
   public Date getWrite_date() {
      return write_date;
   }
   public void setWrite_date(Date write_date) {
      this.write_date = write_date;
   }
   public int getTb_countnum() {
      return tb_countnum;
   }
   public void setTb_countnum(int tb_countnum) {
      this.tb_countnum = tb_countnum;
   }
   public int getTb_likenum() {
      return tb_likenum;
   }
   public void setTb_likenum(int tb_likenum) {
      this.tb_likenum = tb_likenum;
   }
   public String getMember_id() {
      return member_id;
   }
   public void setMember_id(String member_id) {
      this.member_id = member_id;
   }


}