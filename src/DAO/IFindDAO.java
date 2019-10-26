package DAO;

import VO.Log;
import VO.OldThing;
import VO.Thing;
import VO.User;

import java.sql.ResultSet;
import java.util.List;

public interface IFindDAO {
    public boolean doCreate(Thing thing) throws Exception;
    public boolean doCreate(User user) throws Exception;
    public boolean doCreate(Log log) throws Exception;
    public boolean doCreate(OldThing oldThing) throws Exception;
    public boolean doRemove(Thing thing) throws Exception;
    public List<Thing> findAll() throws Exception;
    public Thing findBySn(String sn) throws Exception;
    public List<Thing> findByKey(String key) throws Exception;
    public User findByOpenid(String openid) throws Exception;
    public List<Thing> findThingByOpenid(String openid) throws Exception;
    public List<Thing> findOldThingbyOpenid(String openid) throws Exception;
    public String getStatus() throws Exception;
    public boolean updateStatus(String oldStatus, String newStatus) throws Exception;
    public List<Thing> dataHandle(ResultSet rs) throws Exception;
}
