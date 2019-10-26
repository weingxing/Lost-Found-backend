package service;

import DAO.FindService;
import DAO.ServiceFactory;
import VO.Thing;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddThing")
public class AddThing extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String locate = request.getParameter("locate");
        String backWay = request.getParameter("backWay");
        String time = request.getParameter("time");
        String lostInfo = request.getParameter("lostInfo");
        String describe = request.getParameter("describe");
        String photo = request.getParameter("img");
        String openid = request.getParameter("openid");
        Long snTemp = System.currentTimeMillis();
        String sn = snTemp.toString();
        String img = "https://wx.mapletown.xyz/find/img/" + ServiceFactory.getInstance().getName(photo);

        Thing thing = new Thing();
        thing.setOpenid(openid);
        thing.setTime(time);
        thing.setImg(img);
        thing.setBackWay(backWay);
        thing.setDescribe(describe);
        thing.setSn(sn);
        thing.setLostInfo(lostInfo);
        thing.setLocate(locate);
        FindService findService = ServiceFactory.getInstance();
        try {
            findService.addThing(thing);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
