package DAO;

import VO.Log;
import VO.OldThing;
import VO.Thing;
import VO.User;
import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.List;


public class FindService implements IFindService{
    DatabaseConnection databaseConnection = new DatabaseConnection();

    @Override
    public void addThing(Thing thing) throws Exception {
        try {
            FindDAO findDAO = DAOFactory.getFindDAO(databaseConnection.getConnection());
            findDAO.doCreate(thing);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            databaseConnection.close();
        }
    }

    @Override
    public void addLog(Log log) throws Exception {
        try {
            FindDAO findDAO = DAOFactory.getFindDAO(databaseConnection.getConnection());
            findDAO.doCreate(log);
        } catch (Exception e) {

        } finally {
//            写入log后，还要查询找回方式，数据库连接再查询找回方式后关闭，不在此处关闭
//            databaseConnection.close();
        }
    }

    @Override
    public void addUser(User user) throws Exception {
        try {
            FindDAO findDAO = DAOFactory.getFindDAO(databaseConnection.getConnection());
            findDAO.doCreate(user);
        } catch (Exception e) {

        } finally {
            databaseConnection.close();
        }
    }

    @Override
    public String getInfo(String key, String type) throws Exception {
        List<Thing> list = null;
        try {
            FindDAO findDAO = DAOFactory.getFindDAO(databaseConnection.getConnection());
            if(type.equals("search"))
                list = findDAO.findByKey(key);
            else if(type.equals("found"))
                list = findDAO.findThingByOpenid(key);
            else if(type.equals("lost"))
                list = findDAO.findOldThingbyOpenid(key);

            return JSON.toJSONString(list);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            databaseConnection.close();
        }
        return "null";
    }

    @Override
    public String search(String key) throws Exception {
        try {
            return getInfo(key, "search");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            databaseConnection.close();
        }
        return "null";
    }

    @Override
    public String getName(String photoTempURL) {
        String[] temp = photoTempURL.replaceAll("//", "/").split("/");
        //防止出现IndexOutOfBounds
        if(photoTempURL.equals("NaN")) {
            return "NaN";
        }
        //从相册选择的图片
        else if(temp[0].equals("http:")) {
            String fileName = temp[2];
            return fileName;
        }
        //从相机拍摄的照片
        else if(temp[0].equals("wxfile:")) {
            String fileName = temp[1];
            return fileName;
        }
        //没有上传图片
        return "NaN";
    }

    @Override
    public void deletePhoto(String fileName) throws Exception {
        //删除文件命令
        String path = "/www/server/tomcat/webapps/find/img/" + fileName;
        String[] cmd = new String[] { "/bin/sh", "-c", "rm -rf " + path };
        //执行命令
        Runtime run = Runtime.getRuntime();
        Process process = run.exec(cmd);
        //process.waitFor();
        process.destroy();
    }

    @Override
    public  String sendGet(String code) {
        String result = null;
        BufferedReader in = null;
        try {
            String full_url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + WeChat.getAPPID() + "&secret=" +
                    WeChat.getSECRET() + "&js_code=" + code + "&grant_type=authorization_code";

            //请求微信服务器
            java.net.URL connURL = new java.net.URL(full_url);
            URLConnection urlConnection = connURL.openConnection();
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) urlConnection;
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            httpConn.connect();

            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result = line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public String showWay(String sn, String openid) throws Exception{
        try {
            FindDAO findDAO = new FindDAO(databaseConnection.getConnection());
            if(findDAO.findByOpenid(openid) != null)
                return findDAO.findBySn(sn).getBackWay();
            else
                return "NoUser";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            databaseConnection.close();
        }
        return "null";
    }

    @Override
    public String getStatus() throws Exception {
        try {
            FindDAO findDAO = new FindDAO(databaseConnection.getConnection());
            return findDAO.getStatus();
        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        } finally {
            databaseConnection.close();
        }
    }

    @Override
    public boolean changeStatus(String oldStatus, String newStatus) throws Exception {
        try {
            FindDAO findDAO = new FindDAO(databaseConnection.getConnection());
            return findDAO.updateStatus(oldStatus, newStatus);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            databaseConnection.close();
        }
    }

    @Override
    public void getIt(String openidGet, String sn, String timeGet) throws Exception {
        try {
            FindDAO findDAO = new FindDAO(databaseConnection.getConnection());

            Thing thing = findDAO.findBySn(sn);
            OldThing oldThing = new OldThing();
            oldThing.setThing(thing);
            oldThing.setOpenidGet(openidGet);
            oldThing.setTimeGet(timeGet);
            boolean a = findDAO.doCreate(oldThing);
            boolean b = findDAO.doRemove(thing);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            databaseConnection.close();
        }
    }
}
