package com.kh.spring.demo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.demo.model.service.DemoService;
import com.kh.spring.demo.model.vo.Dev;

import lombok.extern.slf4j.Slf4j;

/*
 * <h1>컨트롤러클래스의 핸들러 메소드가 가질수 있는 파라미터</h1>
 * <pre>
 * HttpServletRequest
 * HttpServletResponse
 * HttpSession

 * java.util.Locale : 요청에 대한 Locale
 * InputStream/Reader : 요청에 대한 입력스트림
 * OutputStream/Writer : 응답에 대한 출력스트림. ServletOutputStream, PrintWriter

  사용자입력값처리
 * Command객체 : http요청 파라미터를 커맨드객체에 저장한 VO객체
 * CommandMap :  HandlerMethodArgumentResolver에 의해 처리된 사용자입력값을 가진 Map객체
 * @Valid : 커맨드객체 유효성 검사객체
 * Error, BindingResult : Command객체에 저장결과(Command객체 바로 다음위치시킬것.)
 * @PathVariable : 요청url중 일부를 매개변수로 취할 수 있다.
 * @RequestParam : 사용자입력값을 자바변수에 대입처리(필수여부 설정)
 * @RequestHeader : 헤더값
 * @CookieValue : 쿠키값
 * @RequestBody : http message body에 작성된 json을 vo객체로 변환처리

  뷰에 전달할 모델 데이터 설정
 * ModelAndView
 * ModelMap 
 * Model

 * @ModelAttribute : model속성에 대한 getter
 * @SessionAttribute : session속성에 대한 getter(required여부 선택가능)
 * 		(@SessionAttributes : session에서 관리될 속성명을 class-level에 작성)
 * SessionStatus: @SessionAttributes로 등록된 속성에 대하여 사용완료(complete)처리. 세션을 폐기하지 않고 재사용한다.
 
  기타
 * MultipartFile : 업로드파일 처리 인터페이스. CommonsMultipartFile
 * RedirectAttributes : DML처리후 요청주소 변경을 위한 redirect를 지원
 * </pre>
 */
@Controller // Component의 기능도 포함 자동으로 bean으로 등록됨과 동시에 Controller의 역할을 한다
@Slf4j
public class DemoController {

	/*
	 * logging framework : Slf4j(서비스) -> Log4j(구현체) private static Logger log =
	 * LoggerFactory.getLogger(DemoController.class);
	 */
	/*
	 * 의존 주입 DI 처리 1. 필드 2. 생성자 3. setter
	 */
	@Autowired
	private DemoService demoService;

	/*
	 * 사용자 요청 처리 GET /spring/demp/devForm.do
	 */
	@RequestMapping("/demo/devForm.do")
	public String devForm() {
		log.info("/demo/devForm.do가 호출되었습니다");
		return "demo/devForm"; // viewName -> /WEB-INF/views/demo/devForm.jsp
	}

	/*
	 * handler method : Controller의 사용자 요청을 처리하는 메소드
	 * 
	 * @RequestMethod method 속성 - 지정하지 않으면 모든 메소드 허용 - 지정하면 지정한 메소드만 허용
	 */
	@RequestMapping(value = { "/demo/dev1.do" }, method = { RequestMethod.POST })
	public String dev1(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		int career = Integer.parseInt(request.getParameter("career"));
		String email = request.getParameter("name");
		String gender = request.getParameter("gender");
		String[] lang = request.getParameterValues("lang");

		log.info("name = {}", name);
		log.info("career = {}", career);
		log.info("email = {}", email);
		log.info("gender = {}", gender);
		log.info("lang = {}", Arrays.toString(lang));

		Dev dev = new Dev(0, name, career, email, gender, lang);
		log.info("dev = {}", dev);

		request.setAttribute("dev", dev);

		return "demo/devResult";
	}

	@RequestMapping(value = "/demo/dev2.do", method = RequestMethod.POST)
	public String dev2(
			@RequestParam(name = "name", required = true, defaultValue = "홍길동") String name, 
			//같은 이름을 받아 저장하나 다를 경우는 @RequestParam뒤에 name 안에 따로 설정  
			//required의 기본 값은 true이며 반드시 존재해야한다 빈 문자열이라도 존재해야 함 false로 설정 시 없어도 넘어감 
			//defaultValue 설정 시 required가 true여도 defaultValue값이 입력되어 정상 처리 됨 
			@RequestParam int career,
			@RequestParam String email,
			@RequestParam String gender,
			@RequestParam String[] lang,
			Model model
			) 
	{
		Dev dev = new Dev(0, name, career, email, gender, lang);
		log.info("dev = {}", dev);
		
		//model : view단에 데이터를 전달하기 위한 Map 객체
		model.addAttribute("dev", dev);
		
		return "demo/devResult";
	}
	/*
	 * 커맨드 객체의 모든 필드는 상응하는 사용자 입력 값으로 채워져 있다 
	 * 커맨드 객체는 자동으로 Model 객체의 속성으로 추가된다 (여러모로 편리)
	 */
	@RequestMapping(value = "/demo/dev3.do")
	public String dev3(@ModelAttribute Dev dev, @RequestParam String token) { 
		//@ModelAttribute는 생략 가능 객체 이름이 다를 경우 ("dev")로 키 값 지정 가능 
		log.info("dev = {}", dev);
		log.info("token = {}", token);
		return "demo/devResult";
	}
	
	@RequestMapping(value = "/demo/insertDev.do", method=RequestMethod.POST)
	public String insertDev(Dev dev) {
		log.info("dev = {}", dev);
		int result = demoService.insertDev(dev);
		String msg = result > 0 ? "Dev 등록 성공" : "Dev 등록 실패";
		log.info("msg = {}", msg);
		
		return "redirect:/demo/devList.do";
	}
	/*
	 * select * from dev order by no desc
	 * -> vo 
	 * -> map 
	 */
	@RequestMapping("/demo/devList.do")
	public String devList(Model model) {
		List<Dev> list = demoService.selectDevList();
		log.info("list = {}", list);
		
		model.addAttribute("list", list);
		
		log.info("12345678");
		return "demo/devList";
	}
	
}
