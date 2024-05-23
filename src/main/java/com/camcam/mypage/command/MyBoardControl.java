package com.camcam.mypage.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camcam.common.Control;
import com.camcam.mypage.service.MyPageService;
import com.camcam.mypage.service.impl.MyPageServiceImpl;
import com.camcam.product.vo.QnAVO;

public class MyBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = "mypage/myboard.tiles";
		//String id = req.getParameter("userId");
		
		MyPageService svc = new MyPageServiceImpl();
		List<QnAVO> list = svc.getMyboard("user01");
		req.setAttribute("list", list);
		
		req.getRequestDispatcher(path).forward(req, resp);
		
		
		
	}

}
