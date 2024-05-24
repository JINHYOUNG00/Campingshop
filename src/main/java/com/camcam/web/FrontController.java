package com.camcam.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camcam.category.command.GetCategoryControl;
import com.camcam.common.Control;
import com.camcam.home.command.HomeControl;
import com.camcam.mypage.command.MyBoardListControl;
import com.camcam.mypage.command.DeleteMyboardControl;
import com.camcam.mypage.command.MyBoardInfoControl;
import com.camcam.mypage.command.MyBoardModFormControl;
import com.camcam.mypage.command.MyInterestListControl;
import com.camcam.mypage.command.MyOrderListControl;
import com.camcam.mypage.command.MyPageControl;
import com.camcam.mypage.command.UpdateMyBoardControl;
import com.camcam.product.command.ProductListControl;
import com.camcam.user.command.CheckInControl;
import com.camcam.user.command.LoginControl;
import com.camcam.user.command.LoginFormControl;
import com.camcam.user.command.LogoutControl;
import com.camcam.user.command.SignInControl;
import com.camcam.user.command.SignInFormControl;
import com.camcam.user.command.UserListControl;
import com.camcam.user.command.WithdrawControl;
import com.camcam.user.command.WithdrawFormControl;

@WebServlet
public class FrontController extends HttpServlet {

	// 필드
	Map<String, Control> map;

	// 생성자.
	public FrontController() {
		map = new HashMap<>();
	}

	// init.
	@Override
	public void init(ServletConfig config) throws ServletException {
		// url패턴과 실행할 Control(인터페이스) 구현클래스 정의
		// 첫 화면
		map.put("/home.do", new HomeControl());
		
		// 제품 관련
		map.put("/productList.do", new ProductListControl());
		
		// 카테고리
		map.put("/getCategory.do", new GetCategoryControl());
		// 유저 관련(유저)
		map.put("/signInForm.do", new SignInFormControl());  // 회원가입 화면
		map.put("/signIn.do", new SignInControl());  // 회원가입 기능
		map.put("/checkUserId.do", new CheckInControl()); //회원가입 아이디중복 기능
		map.put("/logForm.do", new LoginFormControl()); // 로그인 화면 
		map.put("/login.do", new LoginControl());  // 로그인 기능
		map.put("/logout.do", new LogoutControl());  // 로그아웃 기능
		map.put("/withdrawForm.do", new WithdrawFormControl());  // 회원탈퇴 화면
		map.put("/withdraw.do", new WithdrawControl());  // 회원탈퇴 기능
		// 유저 관련(관리자)
		map.put("/userList.do", new UserListControl());  // 회원 리스트
		
		// 마이페이지
		map.put("/myPage.do", new MyPageControl()); //마이페이지로 이동
		map.put("/myBoardList.do", new MyBoardListControl()); //내 게시글 리스트로 이동
		map.put("/myBoardInfo.do", new MyBoardInfoControl()); //내 게시판 상세화면으로 이동
		map.put("/myBoardMod.do", new MyBoardModFormControl()); //내 게시글 수정 이동
		map.put("/updateMyboard.do", new UpdateMyBoardControl()); //내 게시글 수정 기능
		map.put("/deleteMyboard.do" , new DeleteMyboardControl()); //내 게시글 삭제 기능
		map.put("/myOrderList.do", new MyOrderListControl()); //주문내역 리스트로 이동
		map.put("/myInterestList.do", new MyInterestListControl()); //관심상품 리스트로 이동
		
		
		
	}

	// service.
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("utf-8");
//		resp.setContentType("text/html;charset=utf-8"); //??
//		resp.setCharacterEncoding("utf-8"); // 요청정보 한글처리
		
		String uri = req.getRequestURI(); // url 에서 localhost 를 뺀
		String context = req.getContextPath(); // 프로젝트 이름
		System.out.println("uri: "+ uri +", context: "+context);
		String path = uri.substring(context.length());
		System.out.println(context.length());
		System.out.println("path: "+path); // 요청한 페이지
		
		Set<String> keySet = map.keySet();
		
		for (String string : keySet) {
			System.out.println(string + ":" +  map.get(string));
		}
	
		Control control = map.get(path);
		control.exec(req,resp);
	}

	// destroy.
	@Override
	public void destroy() {
		
	}

}
