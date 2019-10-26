package service;

import DAO.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Status")
public class Status extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         * 状态为1，正在审核
         * 状态为0，审核完成
         */
        try {
            PrintWriter out = response.getWriter();
            out.write(ServiceFactory.getInstance().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
