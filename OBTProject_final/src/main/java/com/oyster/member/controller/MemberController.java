package com.oyster.member.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oyster.member.service.MemberService;
import com.oyster.member.vo.MemberVO;

@Controller("memberController")
@RequestMapping(value = "/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberVO memberVO;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	ModelAndView mav = new ModelAndView();

	@RequestMapping(value = "/*.do", method = { RequestMethod.POST, RequestMethod.GET })
	protected ModelAndView viewForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}

	@RequestMapping(value = "/standard_login.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView login(@ModelAttribute("memberVO") MemberVO memberVO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();

		MemberVO login = memberService.standard_login(memberVO);

		if (login != null && bCryptPasswordEncoder.matches(memberVO.getMember_pw(), login.getMember_pw())) {
			HttpSession session = request.getSession();
			session.setAttribute("memberInfo", login);
			session.setAttribute("isLogin", true);

			String action = (String) session.getAttribute("action");
			if (action != null && action.equals("/main.do")) {
				mav.setViewName("forward:" + action);
			} else {
				mav.setViewName("redirect:/main.do");
			}
		} else {
			String wronglogin="아라아라아라";
			mav.addObject("wronglogin", wronglogin);
			mav.setViewName("/member/loginForm");
		}

		return mav;
	}

	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.invalidate();
		/* session.setAttribute("isLogin", false); */
		/* session.removeAttribute("memberInfo"); */
		mav.setViewName("redirect:/main.do");
		return mav;
	}

	@RequestMapping(value = "/addMember.do", method = RequestMethod.POST)
	public ResponseEntity addMember(@ModelAttribute("memberVO") MemberVO _memberVO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		String password = _memberVO.getMember_pw();

		String encPassword = bCryptPasswordEncoder.encode(password);


		_memberVO.setMember_pw(encPassword);

		try {
			memberService.addMember(_memberVO);
			message = "<script>";
			message += " alert('회원 가입을 마쳤습니다.로그인창으로 이동합니다.');";
			message += " location.href='" + request.getContextPath() + "/member/loginForm.do';";
			message += " </script>";

		} catch (Exception e) {
			message = "<script>";
			message += " alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요');";
			message += " location.href='" + request.getContextPath() + "/member/memberForm.do';";
			message += " </script>";
			e.printStackTrace();
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}

	@RequestMapping(value = "/overlapped.do", method = RequestMethod.POST)
	public ResponseEntity overlapped(String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ResponseEntity resEntity = null;
		String result = memberService.overlapped(id);
		resEntity = new ResponseEntity(result, HttpStatus.OK);
		return resEntity;
	}

	
	@RequestMapping(value = "/memberDeleteView", method = {RequestMethod.POST })
	public String memberDeleteView() throws Exception {
		
		return "/member/memberDeleteView";
	}

	
	@RequestMapping(value = "/removeMember.do", method = RequestMethod.POST)
	public ResponseEntity memberDelete(MemberVO memberVO, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, RedirectAttributes rttr) throws Exception {
		// 세션에 있는 member를 가져와 member변수에 넣어줍니다.
		memberVO = (MemberVO) session.getAttribute("memberInfo");
		// 세션에있는 비밀번호
		String sessionPass = memberVO.getMember_pw();
		// vo로 들어오는 비밀번호
		String voPass = memberVO.getMember_pw();
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			if (sessionPass.equals(voPass)) {
				memberService.removeMember(memberVO);
				session.invalidate();
				message = "<script>";
				message += " alert('탈퇴에 성공했습니다. 다음에 또 뵈요!');";
				message += " location.href='" + request.getContextPath() + "/member/loginForm.do';";
				message += " </script>";
				resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			message = "<script>";
			message += " alert('작업중 오류가 발생했습니다.다시 시도해 주세요.');";
			message += " location.href='" + request.getContextPath() + "/member/memberInfo.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}
	
	//회원정보 수정
  @RequestMapping(value="/modmemberInfo.do" ,method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity modmemberInfo(HttpServletRequest request,  
    HttpServletResponse response) throws Exception{
   request.setCharacterEncoding("utf-8");
	Map memberInfoMap = new HashMap();
	Enumeration enu=request.getParameterNames();
	while(enu.hasMoreElements()){
		String name=(String)enu.nextElement();
		String value=request.getParameter(name);
		memberInfoMap.put(name,value);
	}
	
//	HttpSession session = request.getSession();
//	MemberVO memberVO = (MemberVO) session.getAttribute("member");
	String id = memberVO.getMember_id();
	memberInfoMap.put("id", id);
	System.out.println("memberInfoMap>>>>>>>>"+memberInfoMap);
	/* String articleNO=(String)articleMap.get("articleNO"); */
	String message;
	ResponseEntity resEnt=null;
	HttpHeaders responseHeaders = new HttpHeaders();
	responseHeaders.add("Content-Type", "text/html; charset=utf-8");
    try {
    	memberService.modmember(memberInfoMap);
       message = "<script>";
	   message += " alert('회원정보를 수정했습니다.');";
	   message += " location.href='"+request.getContextPath()+"/main.do';";
	   message +=" </script>";
       resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
    }catch(Exception e) {
      message = "<script>";
	  message += " alert('오류가 발생했습니다');";
	  message += " location.href='"+request.getContextPath()+"/member/memberInfo.do';";
	  message +=" </script>";
      resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
    }
    return resEnt;
  }
}
