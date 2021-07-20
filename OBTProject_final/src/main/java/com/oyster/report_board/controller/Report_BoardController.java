package com.oyster.report_board.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.oyster.member.vo.MemberVO;
import com.oyster.report_board.service.Report_BoardSerivce;
import com.oyster.report_board.vo.ImageVO;
import com.oyster.report_board.vo.Report_boardVO;

//import sun.java2d.opengl.WGLSurfaceData.WGLVSyncOffScreenSurfaceData;

@Controller("Report_BoardController")
@RequestMapping(value = "/board")

//�떊怨� 寃뚯떆�뙋 而⑦듃濡ㅻ윭
public class Report_BoardController {
	private static final String ARTICLE_IMAGE_REPO = "C:\\upload";
	@Autowired
	private Report_BoardSerivce report_boardserivce;

	@Autowired
	private Report_boardVO report_boardvo;
	
	// 湲��벐湲� 李� �꽆�뼱媛�湲�
	@RequestMapping(value = "/rb_board/rb_articleForm.do", method = { RequestMethod.GET, RequestMethod.POST })
	private ModelAndView newArticleform(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}

	// �닔�젙�븯湲� 李� �꽆�뼱媛�湲�
	@RequestMapping(value = "/rb_board/rb_articlemodForm.do", method = { RequestMethod.GET, RequestMethod.POST })
	private ModelAndView modArticleform(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		int rb_number = Integer.parseInt(request.getParameter("rb_number"));
		ModelAndView mav = new ModelAndView();
		mav.addObject("rb_number", rb_number);
		mav.setViewName(viewName);
		return mav;
	}

	// �떟湲��벐湲� 李� �꽆�뼱媛�湲�
	@RequestMapping(value = "/rb_board/rb_rearticleForm.do", method = { RequestMethod.GET, RequestMethod.POST })
	private ModelAndView reArticleform(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		int parent_no = Integer.parseInt(request.getParameter("parent_no"));
		ModelAndView mav = new ModelAndView();
		mav.addObject("parent_no", parent_no);
		mav.setViewName(viewName);
		return mav;
	}

	// 寃뚯떆�뙋 紐⑸줉 蹂댁뿬二쇨린
	@RequestMapping(value = "/rb_board/rb_listarticles.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listarticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		List<Report_boardVO> articlesList = report_boardserivce.listArticles();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("articlesList", articlesList);
		return mav;
	}

	// 湲� 蹂댁뿬二쇨린

	   @RequestMapping(value = "/rb_board/rb_View.do", method = { RequestMethod.GET, RequestMethod.POST })
	   public ModelAndView viewArticle(@RequestParam("rb_number") int rb_number, HttpServletRequest request,
	         HttpServletResponse response) throws Exception {
	      String viewName = (String) request.getAttribute("viewName");
	      System.out.println("Controller viewarticles �엯�옣: >>>> " + viewName);
	      ModelAndView mav = new ModelAndView(viewName);
	      Map<String, Object> resultMap = report_boardserivce.viewArticle(rb_number);
	      mav.addObject("resultMap", resultMap);
	      System.out.println("view controller>>>>" + resultMap);
	      return mav;
	   }

	// 異붿쿇 湲곕뒫
	@RequestMapping(value = "/rb_board/recommend.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity recommend(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		int rb_number = Integer.parseInt(request.getParameter("rb_number"));
		System.out.println("而⑦듃濡ㅻ윭 ���뒗嫄곗빞???>>>" + rb_number);

		try {
			report_boardserivce.recommend(rb_number);
			message = "<script>";
			message += " alert('異붿쿇�쓣 �셿猷뚰뻽�뒿�땲�떎.');";
			message += " location.href='" + request.getContextPath() + "/board/rb_board/rb_listarticles.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {
			message = "<script>";
			message += " alert('�옉�뾽以� �삤瑜섍� 諛쒖깮�뻽�뒿�땲�떎.�떎�떆 �떆�룄�빐 二쇱꽭�슂.');";
			message += " location.href='" + request.getContextPath() + "/board/rb_board/rb_listarticles.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt; // �럹�씠吏�媛믪쓣 洹몃�濡� �꽆寃⑤컺湲곗쐞�빐�꽌 �룷�썙�뵫�쓣 �궗�슜�빐 而⑦듃濡ㅻ윭濡� 由ы꽩�떆�궓�떎.
	}

	// 湲� �궘�젣�븯湲�
	@RequestMapping(value = "/rb_board/removereport_board.do", method = { RequestMethod.POST })
	@ResponseBody
	public ResponseEntity removeArticle(@RequestParam("rb_number") int rb_number, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			report_boardserivce.removeArticle(rb_number);
			message = "<script>";
			message += " alert('湲��쓣 �궘�젣�뻽�뒿�땲�떎.');";
			message += " location.href='" + request.getContextPath() + "/board/rb_board/rb_listarticles.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {
			message = "<script>";
			message += " alert('�옉�뾽以� �삤瑜섍� 諛쒖깮�뻽�뒿�땲�떎.�떎�떆 �떆�룄�빐 二쇱꽭�슂.');";
			message += " location.href='" + request.getContextPath() + "/board/rb_board/rb_listarticles.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}
	
	// �떟湲� �벐湲�
	@RequestMapping(value = "/rb_board/report_boardNewReArticle.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity addnewReply(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> replyMap = new HashMap<String, Object>();
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = request.getParameter(name);
			replyMap.put(name, value);
		}

		System.out.println("replyMap>>>>>>" + replyMap);
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			report_boardserivce.insertNewreply(replyMap);
			message = "<script>";
			message += " alert('�떟湲��쓣 異붽��뻽�뒿�땲�떎.');";
			message += " location.href='" + request.getContextPath() + "/board/rb_board/rb_listarticles.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = " <script>";
			message += " alert('�삤瑜섍� 諛쒖깮�뻽�뒿�땲�떎. �떎�떆 �떆�룄�빐 二쇱꽭�슂');');";
			message += " location.href='" + request.getContextPath() + "/board/rb_board/rb_rearticleForm.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	// 湲� �닔�젙�븯湲�
	@RequestMapping(value = "/rb_board/modarticle.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity modArticle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> articleMap = new HashMap<String, Object>();
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = request.getParameter(name);
			articleMap.put(name, value);
		}
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			report_boardserivce.modArticle(articleMap);
			message = "<script>";
			message += " alert('湲��씠 �닔�젙�릺�뿀�뒿�땲�떎.');";
			message += " location.href='" + request.getContextPath() + "/board/rb_board/rb_listarticles.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = " <script>";
			message += " alert('�삤瑜섍� 諛쒖깮�뻽�뒿�땲�떎. �떎�떆 �떆�룄�빐 二쇱꽭�슂');');";
			message += " location.href='" + request.getContextPath() + "/board/rb_board/rb_articlemodForm.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	// �떎以� �씠誘몄� 湲��벐湲�
	@RequestMapping(value = "/rb_board/addNewreport_board.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		String imageFileName = null;
		Map articleMap = new HashMap();
		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			articleMap.put(name, value);
		}

		// 濡쒓렇�씤 �떆 �꽭�뀡�뿉 ���옣�맂 �쉶�썝 �젙蹂댁뿉�꽌 湲��벖�씠 �븘�씠�뵒瑜� �뼸�뼱���꽌 Map�뿉 ���옣�빀�땲�떎.
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
		if (memberVO != null && memberVO.getMember_id() != null) {
			String id = memberVO.getMember_id();
			articleMap.put("parentNO", 0);
			articleMap.put("member_id", id);
			articleMap.put("imageFileName", imageFileName);
		}
		System.out.println("upload Contoller >>> " + articleMap);
		List<String> fileList = upload(multipartRequest);
		List<ImageVO> imageFileList = new ArrayList<ImageVO>();
		if (fileList != null && fileList.size() != 0) {
			for (String fileName : fileList) {
				ImageVO imageVO = new ImageVO();
				imageVO.setImageFileName(fileName);
				imageFileList.add(imageVO);
				System.out.println("而⑦듃濡ㅻ윭 fileList>>>>>>>>>" + fileName);
			}
			articleMap.put("imageFileList", imageFileList);
		}
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			int rb_number = report_boardserivce.addNewArticle(articleMap);
			if (imageFileList != null && imageFileList.size() != 0) {
				for (ImageVO imageVO : imageFileList) {
					imageFileName = imageVO.getImageFileName();
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
					File destDir = new File(ARTICLE_IMAGE_REPO);
					// destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					System.out.println("而⑦듃濡ㅻ윭 imageVO>>>>>>>>>>" + imageVO);
				}
			}

			message = "<script>";
			message += " alert('�깉 湲��쓣 異붽��뻽�뒿�땲�떎.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/rb_board/rb_listarticles.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {
			if (imageFileList != null && imageFileList.size() != 0) {
				for (ImageVO imageVO : imageFileList) {
					imageFileName = imageVO.getImageFileName();
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
					srcFile.delete();
				}
			}

			message = " <script>";
			message += " alert('�깉 湲��쓣 異붽��뻽�뒿�땲�떎.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/rb_board/rb_listarticles.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	// �떎以� �씠誘몄� �뾽濡쒕뱶�븯湲�
	private List<String> upload(MultipartHttpServletRequest multipartRequest) throws Exception {
		List<String> fileList = new ArrayList<String>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String originalFileName = mFile.getOriginalFilename();
			UUID uuid = UUID.randomUUID();
			originalFileName = uuid.toString() + "_" + originalFileName;
			fileList.add(originalFileName);
			File file = new File(ARTICLE_IMAGE_REPO + "\\" + fileName);
			if (mFile.getSize() != 0) { // File Null Check
				if (!file.exists()) {
					if (file.getParentFile().mkdirs()) {
						file.createNewFile();
					}
				}
				mFile.transferTo(new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + originalFileName));
			}
		}
		return fileList;
	}
}