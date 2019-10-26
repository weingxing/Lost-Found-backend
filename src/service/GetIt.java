package service;

import DAO.FindService;
import DAO.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetIt")
public class GetIt extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String openid = request.getParameter("openid");
        String sn = request.getParameter("sn");
        String time = request.getParameter("time");
        FindService findService = ServiceFactory.getInstance();
        try {
            findService.getIt(openid, sn, time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
