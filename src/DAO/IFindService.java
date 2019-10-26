package DAO;

import VO.Log;
import VO.Thing;
import VO.User;

public interface IFindService {
    public void addThing(Thing thing) throws Exception;
    public void addLog(Log log) throws Exception;
    public void addUser(User user) throws Exception;
    public String search(String key) throws Exception;
    public String getName(String photoTmpURL) throws Exception;
    public void deletePhoto(String fileName) throws Exception;
    public String showWay(String sn, String openid) throws Exception;
    public String sendGet(String code);
    public String getInfo(String key, String type) throws Exception;
    public String getStatus() throws Exception;
    public boolean changeStatus(String oldStatus, String newStatus) throws Exception;
    public void getIt(String openidGet, String sn, String timeGet) throws Exception;
}
