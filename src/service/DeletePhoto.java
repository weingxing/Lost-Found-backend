package service;

import DAO.FindService;
import DAO.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeletePhoto")
/**
 * 删除图片
 * 用户在提交页面上传图片后，显示删除按钮，若用户决定删除，在前端回显上传图标后
 * 删除服务器上的图片，节约服务器存储空间
 * @author Oxygen
 */
public class DeletePhoto extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String img= request.getParameter("img");
        FindService findService = ServiceFactory.getInstance();
        String fileName= findService.getName(img);
        try {
            findService.deletePhoto(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
