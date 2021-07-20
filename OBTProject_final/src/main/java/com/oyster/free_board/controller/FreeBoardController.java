package com.oyster.free_board.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.oyster.free_board.service.FreeBoardServiceImpl;
import com.oyster.free_board.vo.FreeBoardVO;
import com.oyster.free_board.vo.ImageVO;
import com.oyster.member.vo.MemberVO;

@Controller("freeBoardController")
@RequestMapping(value = "/board")
public class FreeBoardController {
	private static final String ARTICLE_IMAGE_REPO = "C:\\upload";
	@Autowired
	FreeBoardServiceImpl freeBoardService;
	@Autowired
	private FreeBoardVO freeBoardVO;

	// 글쓰기 창 넘어가기
	@RequestMapping(value = "/fb_board/fb_articleForm.do", method = { RequestMethod.GET, RequestMethod.POST })
	private ModelAndView newArticleform(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}

	// 수정하기 창 넘어가기
	@RequestMapping(value = "/fb_board/fb_articlemodForm.do", method = { RequestMethod.GET, RequestMethod.POST })
	private ModelAndView modArticleform(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		int fb_number = Integer.parseInt(request.getParameter("fb_number"));
		ModelAndView mav = new ModelAndView();
		mav.addObject("fb_number", fb_number);
		mav.setViewName(viewName);
		return mav;
	}

	// 게시판 목록 보여주기
	@RequestMapping(value = "/fb_board/fb_listArticles.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		List<FreeBoardVO> articlesList = freeBoardService.listArticles();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("articlesList", articlesList);
		return mav;
	}

	// 글 보여주기
	@RequestMapping(value = "/fb_board/fb_View.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView viewArticle(@RequestParam("fb_number") int fb_number, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		System.out.println("Controller viewArticles 입장: >>>> " + viewName);
		ModelAndView mav = new ModelAndView(viewName);
		Map<String, Object> resultMap = freeBoardService.viewArticle(fb_number);
		mav.addObject("resultMap", resultMap);
		System.out.println("view controller>>>>" + resultMap);
		return mav;
	}

	// 글 삭제하기
	@RequestMapping(value = "/fb_board/removefreeboard.do", method = { RequestMethod.POST })
	@ResponseBody
	public ResponseEntity removeArticle(@RequestParam("fb_number") int fb_number, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			freeBoardService.removeArticle(fb_number);
			message = "<script>";
			message += " alert('글을 삭제했습니다.');";
			message += " location.href='" + request.getContextPath() + "/board/fb_board/fb_listArticles.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {
			message = "<script>";
			message += " alert('작업중 오류가 발생했습니다.다시 시도해 주세요.');";
			message += " location.href='" + request.getContextPath() + "/board/fb_board/fb_listArticles.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	// 글 수정하기
	@RequestMapping(value = "/fb_board/modarticle.do", method = { RequestMethod.GET, RequestMethod.POST })
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
			freeBoardService.modArticle(articleMap);
			message = "<script>";
			message += " alert('글이 수정되었습니다.');";
			message += " location.href='" + request.getContextPath() + "/board/fb_board/fb_listArticles.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = " <script>";
			message += " alert('오류가 발생했습니다. 다시 시도해 주세요');');";
			message += " location.href='" + request.getContextPath() + "/board/fb_board/fb_listArticles.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

    // 다중 이미지 글쓰기
    @RequestMapping(value = "/fb_board/addNewfree.do", method = { RequestMethod.GET, RequestMethod.POST })
       @ResponseBody
       public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
             throws Exception {
        multipartRequest.setCharacterEncoding("utf-8");
        String imageFileName = null;
        Map articleMap = new HashMap();
        Enumeration enu=multipartRequest.getParameterNames();
        while (enu.hasMoreElements()) {
           String name = (String) enu.nextElement();
           String value = multipartRequest.getParameter(name);
           articleMap.put(name, value);
        }

        // 로그인 시 세션에 저장된 회원 정보에서 글쓴이 아이디를 얻어와서 Map에 저장합니다.
        HttpSession session = multipartRequest.getSession();
        MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
        if (memberVO != null && memberVO.getMember_id() != null) {
           String id = memberVO.getMember_id();
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
              System.out.println("컨트롤러 fileList>>>>>>>>>" + fileName);
           }
           articleMap.put("imageFileList", imageFileList);
        }
        String message;
        ResponseEntity resEnt = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");
        try {
           int fb_number = freeBoardService.addNewArticle(articleMap);
           if (imageFileList != null && imageFileList.size() != 0) {
              for (ImageVO imageVO : imageFileList) {
                 imageFileName = imageVO.getImageFileName();
                 File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
                 File destDir = new File(ARTICLE_IMAGE_REPO);
                 // destDir.mkdirs();
                 FileUtils.moveFileToDirectory(srcFile, destDir, true);
                 System.out.println("컨트롤러 imageVO>>>>>>>>>>" + imageVO);
              }
           }

           message = " <script>";
           message += " alert('새 글을 추가했습니다.');";
           message += " location.href='" + multipartRequest.getContextPath()
            + "/board/fb_board/fb_listArticles.do'; ";
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
           message += " alert('새 글을 추가했습니다.');";
           message += " location.href='" + multipartRequest.getContextPath()
            + "/board/fb_board/fb_listArticles.do'; ";
      message += " </script>";
           resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
           e.printStackTrace();
        }
        return resEnt;
     }
    
     // 다중 이미지 업로드하기
     private List<String> upload(MultipartHttpServletRequest multipartRequest) throws Exception{
        List<String> fileList= new ArrayList<String>();
        Iterator<String> fileNames = multipartRequest.getFileNames();
        while(fileNames.hasNext()){
           String fileName = fileNames.next();
           MultipartFile mFile = multipartRequest.getFile(fileName);
           String originalFileName=mFile.getOriginalFilename();
           UUID uuid = UUID.randomUUID();
           originalFileName = uuid.toString() + "_" + originalFileName;
           fileList.add(originalFileName);
           File file = new File(ARTICLE_IMAGE_REPO +"\\"+ fileName);
           if(mFile.getSize()!=0){ //File Null Check
              if(! file.exists()){ 
                 if(file.getParentFile().mkdirs()){ 
                       file.createNewFile();
                 }
              }
              mFile.transferTo(new File(ARTICLE_IMAGE_REPO +"\\"+"temp"+ "\\"+originalFileName));
           }
        }
        return fileList;
     }

	// 추천 기능
	@RequestMapping(value = "/fb_board/recommend.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity recommend(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		int fb_number = Integer.parseInt(request.getParameter("fb_number"));
		System.out.println("컨트롤러 타는거야???>>>" + fb_number);

		try {
			freeBoardService.recommend(fb_number);
			message = "<script>";
			message += " alert('추천을 완료했습니다.');";
			message += " location.href='" + request.getContextPath() + "/board/fb_board/fb_listArticles.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {
			message = "<script>";
			message += " alert('작업중 오류가 발생했습니다.다시 시도해 주세요.');";
			message += " location.href='" + request.getContextPath() + "/board/fb_board/fb_listArticles.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt; // 페이지값을 그대로 넘겨받기위해서 포워딩을 사용해 컨트롤러로 리턴시킨다.
	}
}