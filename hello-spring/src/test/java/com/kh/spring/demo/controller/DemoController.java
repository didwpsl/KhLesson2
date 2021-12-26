package com.kh.spring.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kh.spring.demo.model.service.DemoService;
import com.kh.spring.demo.model.vo.Dev;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DemoController {

	/*
	 *<h1> 컨트롤러 클래스 핸들러 메소드가 가질 수 있는 파라미터</h1>
	 *
	 *<pre>
	 * logging framework : Slf4j(서비스) -> Log4j(구현체)
	 *
	// private static Logger log = LoggerFactory.getLogger(DemoController.class);
	/*
	 * 컨트롤러클래스의 핸들러 메소드가 가질수 있는 파라미터 HttpServletRequest HttpServletResponse
	 * HttpSession
	 * 
	 * java.util.Locale : 요청에 대한 Locale InputStream/Reader : 요청에 대한 입력스트림
	 * OutputStream/Writer : 응답에 대한 출력스트림. ServletOutputStream, PrintWriter
	 * 
	 * 사용자입력값처리 Command객체 : http요청 파라미터를 커맨드객체에 저장한 VO객체 CommandMap :
	 * HandlerMethodArgumentResolver에 의해 처리된 사용자입력값을 가진 Map객체
	 * 
	 * @Valid : 커맨드객체 유효성 검사객체 Error, BindingResult : Command객체에 저장결과(Command객체 바로
	 * 다음위치시킬것.)
	 * 
	 * @PathVariable : 요청url중 일부를 매개변수로 취할 수 있다.
	 * 
	 * @RequestParam : 사용자입력값을 자바변수에 대입처리(필수여부 설정)
	 * 
	 * @RequestHeader : 헤더값
	 * 
	 * @CookieValue : 쿠키값
	 * 
	 * @RequestBody : http message body에 작성된 json을 vo객체로 변환처리
	 * 
	 * 뷰에 전달할 모델 데이터 설정 ModelAndView ModelMap Model
	 * 
	 * @ModelAttribute : model속성에 대한 getter
	 * 
	 * @SessionAttribute : session속성에 대한 getter(required여부 선택가능)
	 * 
	 * @SessionAttributes : session에서 관리될 속성명을 class-level에 작성
	 * SessionStatus: @SessionAttributes로 등록된 속성에 대하여 사용완료(complete)처리. 세션을 폐기하지 않고
	 * 재사용한다.
	 * 
	 * 
	 * 기타 MultipartFile : 업로드파일 처리 인터페이스. CommonsMultipartFile RedirectAttributes :
	 * DML처리후 요청주소 변경을 위한 redirect를 지원
	 * 
	 *
	 * 의존주입 DI 처리 1. 필드 2. 생성자 3. setter
	 */
	@Autowired
	private DemoService demoService;

	/*
	 * GET /spring/demo/devForm.do
	 * 
	 * @return
	 */
	@RequestMapping("/demo/devForm.do")
	public String devForm() {
		log.info("/demo/devForm.do 가 호출되었습니다.");
		return "demo/devForm";
	}

	/*
	 * handler method
	 * 
	 * @RequestMappig method속성 - 지정하지 않으면, 모든 메소드 허용 - 지정하면, 지정한 메소드만 허용
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/demo/dev1.do" }, method = { RequestMethod.POST })
	public String dev1(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		int career = Integer.parseInt(request.getParameter("career"));
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String[] lang = request.getParameterValues("lang");

		Dev dev = new Dev(0, name, career, email, gender, lang);
		log.info("dev = {}", dev);

		request.setAttribute("dev", dev);

		return "demo/devResult";
	}

	@RequestMapping(value = "/demo/dev2.do", method = RequestMethod.POST)
	public String dev2() {
		return "demo/devResult";
	}

}
