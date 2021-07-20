package com.oyster.reply.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oyster.reply.service.ReplyServiceImpl;
import com.oyster.reply.vo.ReplyVO;

@Controller("replyController")
public class ReplyController {

	@Autowired
	ReplyServiceImpl replyService;
	@Autowired
	private ReplyVO replyVO;

	@RequestMapping(value = "/reply/addNewReply.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView addNewReply(@ModelAttribute("replyVO") ReplyVO replyVO,
			@RequestParam("boardtype") String boardtype, @RequestParam("article_number") String article_number, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		//boardtype을 이용해 해당 글번호 산출
		String viewName;
		System.out.println("Controller  이후: >>>> " + article_number);
		System.out.println("Controller dfsfsads 이후: >>>> " + replyVO);
		
		if (boardtype.equals("free_board")) {
			replyVO.setFb_number(article_number);
			viewName = "redirect:/board/fb_board/fb_View.do?fb_number=" + article_number;
		} else if (boardtype.equals("trans_board")) {
			replyVO.setTb_number(article_number);
			viewName = "redirect:/board/tb_board/tb_View.do?tb_number=" + article_number;
		} else {
			replyVO.setRb_number(article_number);
			viewName = "redirect:/board/rb_board/rb_View.do?rb_number=" + article_number;
		}

		ModelAndView mav = new ModelAndView(viewName);
		System.out.println("viewName >>> " + viewName);
		System.out.println("Controller add 이후: >>>> " + replyVO);
		replyService.addNewReply(replyVO);
		return mav;

	}

	public ModelAndView viewReply(int reply_no, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		System.out.println("Controller viewArticles 입장: >>>> " + viewName);
		replyVO = replyService.viewReply(reply_no);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("article", replyVO);
		return mav;
	}

	@RequestMapping(value = "/reply/removeReply.do", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity removeReply(@RequestBody Map<String, String> replyMap,
			HttpServletResponse response) throws Exception {
		/* response.setContentType("text/html; charset=UTF-8"); */
		//System.out.println("Remove controller>>>>check>>>>>>>>>>" + request.getRequestURI());
		
		System.out.println("Remove controller>>>>>>>>>>>>>>" + replyMap.get("boardKind"));
		System.out.println("Remove controller>>>>>>>>>>>>>>" + replyMap.get("reply_no"));
		int reply_no = Integer.parseInt((String) replyMap.get("reply_no"));
		ResponseEntity resEnt;
		// String message;

		try {
			replyService.deleteReply(reply_no);
			resEnt = new ResponseEntity("success", HttpStatus.OK);

		} catch (Exception e) {
			resEnt = new ResponseEntity("fail", HttpStatus.NOT_MODIFIED);
			e.printStackTrace();
		}
		return resEnt;
	}

}
