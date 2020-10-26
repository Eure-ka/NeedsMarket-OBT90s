package com.oyster.free_board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.oyster.free_board.service.FreeBoardServiceImpl;
import com.oyster.free_board.vo.FreeBoardVO;

@Controller("freeBoardController")
public class FreeBoardControllerImpl implements FreeBoardController{
	
	@Autowired
	FreeBoardServiceImpl freeBoardService;
	
	  @RequestMapping(value="/*.do" ,method={RequestMethod.POST,RequestMethod.GET})
	  protected ModelAndView viewForm(HttpServletRequest request,
	  HttpServletResponse response) throws Exception { String
	  viewName=(String)request.getAttribute("viewName"); ModelAndView mav = new
	  ModelAndView(viewName); return mav; }
	 
	@Override
	@RequestMapping(value = "/board/fb_listArticles", method = {RequestMethod.GET})
	public ModelAndView fb_listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getParameter("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		List<FreeBoardVO> list = freeBoardService.listArticles();
		mav.addObject("list", list);
		return mav;
	}

	@Override
	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView viewArticle(int articleNO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity removeArticle(int articleNO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
