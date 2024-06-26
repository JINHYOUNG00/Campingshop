package com.camcam.user.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.camcam.common.Control;
import com.camcam.user.service.UserService;
import com.camcam.user.service.impl.UserServiceImpl;
import com.camcam.user.vo.UserVO;

public class IdFindControl implements Control {

    @Override
    public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String tel = req.getParameter("tel");

        System.out.println(name + "****************************");
        System.out.println(tel + "****************************");

        UserService svc = new UserServiceImpl();
        UserVO uvo = svc.loginFind(name, tel);

        HttpSession session = req.getSession();
        String jsonResponse = "";

        if (uvo != null) {
            session.setAttribute("userId", uvo.getUserId());
            jsonResponse = "{\"userId\": \"" + uvo.getUserId() + "\"}";
        } else {
            session.setAttribute("errorMessage", "아이디를 찾을 수 없습니다. 다시 시도해 주세요.");
            jsonResponse = "{\"errorMessage\": \"아이디를 찾을 수 없습니다. 다시 시도해 주세요.\"}";
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonResponse);
    }
}
