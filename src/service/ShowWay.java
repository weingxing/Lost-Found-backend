package service;

import DAO.FindService;
import DAO.ServiceFactory;
import VO.Log;
import javafx.print.Printer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ShowWay")
public class ShowWay extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String openid = request.getParameter("openid");
        String sn = request.getParameter("sn");
        String time = request.getParameter("time");

        String backWay = null;
        Log log = new Log();
        log.setOpenid(openid);
        log.setSn(sn);
        log.setTime(time);
        FindService findService = ServiceFactory.getInstance();
        try {
            findService.addLog(log);
            backWay = findService.showWay(sn, openid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.write(backWay);
    }
}
