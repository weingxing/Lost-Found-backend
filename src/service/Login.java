package service;

import DAO.FindService;
import DAO.ServiceFactory;
import VO.WechatSession;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String code = request.getParameter("code");
        FindService findService = ServiceFactory.getInstance();
        String result = findService.sendGet(code);

        PrintWriter out = response.getWriter();
        if(result.contains("openid")) {
            WechatSession wechatSession = JSON.parseObject(result, WechatSession.class);
            out.write(wechatSession.getOpenid());
        }
        else
            out.write("ERROR");
    }
}
