package service;
/*
* 过腾讯审核，审核时隐藏信息发布功能，审核后再打开
* 状态0，审核完成，状态1，审核中
*/
import DAO.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ChangeStatus")
public class ChangeStatus extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String oldStatus = request.getParameter("old");
        String newStatus = request.getParameter("new");
        String password = request.getParameter("password");

        PrintWriter out = response.getWriter();
        try {
            if("iopjkl5.0".equals(password)) {
                boolean a = ServiceFactory.getInstance().changeStatus(oldStatus, newStatus);
                if (a)
                    out.write("状态更改成功！\n");
                else
                    out.write("状态更改失败！\n");
                out.write("现状态（0: 审核完成，1：审核中）：" + ServiceFactory.getInstance().getStatus());
            }
            else
                out.write("密码错误！");
        } catch (Exception e) {
            e.printStackTrace();
            out.write("发生错误！");
        }
    }
}
