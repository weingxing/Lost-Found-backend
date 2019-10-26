package service;

import DAO.FindService;
import DAO.ServiceFactory;
import VO.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddUser")
public class AddUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String openid = request.getParameter("openid");
        String sid = request.getParameter("sid");
        User user = new User();
        user.setSid(sid);
        user.setOpenid(openid);

        try {
            ServiceFactory.getInstance().addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
