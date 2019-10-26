package DAO;

import VO.Log;
import VO.OldThing;
import VO.Thing;
import VO.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FindDAO implements IFindDAO {
    private Connection conn = null;
    private PreparedStatement pstm = null;

    public FindDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean doCreate(Thing thing) throws Exception {
        String sql = "INSERT INTO thing(sn,`describe`,time,lost_info,locate,img,openid,back_way)" +
                "VALUES(?,?,?,?,?,?,?,?);";
        pstm = conn.prepareStatement(sql);
        pstm = conn.prepareStatement(sql);
        pstm.setString(1, thing.getSn());
        pstm.setString(2, thing.getDescribe());
        pstm.setString(3, thing.getTime());
        pstm.setString(4, thing.getLostInfo());
        pstm.setString(5, thing.getLocate());
        pstm.setString(6, thing.getImg());
        pstm.setString(7, thing.getOpenid());
        pstm.setString(8, thing.getBackWay());

        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean doCreate(User user) throws Exception {
        String sql = "INSERT INTO user(openid,sid)VALUES(?,?)";
        pstm = conn.prepareStatement(sql);
        pstm.setString(1, user.getOpenid());
        pstm.setString(2, user.getSid());
        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean doCreate(Log log) throws Exception {
        String sql = "INSERT INTO log(openid,sn,time)VALUES (?,?,?)";
        pstm = conn.prepareStatement(sql);
        pstm.setString(1, log.getOpenid());
        pstm.setString(2, log.getSn());
        pstm.setString(3, log.getTime());
        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean doCreate(OldThing oldThing) throws Exception {
        String sql = "INSERT INTO old(sn,`describe`,time,lost_info,locate,img,openid,back_way,get_time,get_openid)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?);";
        pstm = conn.prepareStatement(sql);
        pstm.setString(1, oldThing.getThing().getSn());
        pstm.setString(2, oldThing.getThing().getDescribe());
        pstm.setString(3, oldThing.getThing().getTime());
        pstm.setString(4, oldThing.getThing().getLostInfo());
        pstm.setString(5, oldThing.getThing().getLocate());
        pstm.setString(6, oldThing.getThing().getImg());
        pstm.setString(7, oldThing.getThing().getOpenid());
        pstm.setString(8, oldThing.getThing().getBackWay());
        pstm.setString(9, oldThing.getTimeGet());
        pstm.setString(10, oldThing.getOpenidGet());

        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean doRemove(Thing thing) throws Exception {
        String sql = "DELETE FROM thing WHERE sn = " + thing.getSn() + ";";
        pstm = conn.prepareStatement(sql);
        return pstm.executeUpdate() > 0;
    }

    @Override
    public List<Thing> findAll() throws Exception {
        String sql = "SELECT * FROM thing;";
        pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        return dataHandle(rs);
    }

    @Override
    public List<Thing> findThingByOpenid(String openid) throws Exception {
        String sql = "SELECT * FROM thing WHERE  openid='" + openid + "' ORDER BY id DESC;";
        pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        return dataHandle(rs);
    }

    @Override
    public List<Thing> findOldThingbyOpenid(String openid) throws Exception {
        String sql = "SELECT * FROM old WHERE  get_openid='" + openid + "';";
        pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        return dataHandle(rs);
    }

    @Override
    public Thing findBySn(String sn) throws Exception {
        String sql = "SELECT * FROM thing WHERE  sn='" + sn + "';";
        pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<Thing> list = dataHandle(rs);
        return list.get(0);
    }

    @Override
    public List<Thing> findByKey(String key) throws Exception {
        String sql = "SELECT * FROM thing WHERE locate  LIKE '%"+key+"%' OR time LIKE '%"+key+"%' OR back_way LIKE" +
        "'%"+key+"%' OR lost_info LIKE '%"+key+"%' OR `describe` LIKE '%"+key+"%'" + "ORDER BY id DESC " + ";";
        pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        return dataHandle(rs);
    }

    @Override
    public User findByOpenid(String openid) throws Exception {
        String sql = "SELECT * FROM user WHERE openid='" + openid + "';";
        pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()) {
            User user = new User();
            user.setOpenid(rs.getString("openid"));
            user.setSid(rs.getString("sid"));
            return user;
        }
        return null;
    }

    @Override
    public String getStatus() throws Exception {
        String sql = "SELECT * FROM status;";
        pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        if(rs.next())
            return rs.getString("status");
        return null;
    }

    @Override
    public boolean updateStatus(String oldStatus, String newStatus) throws Exception {
        String sql = "UPDATE status SET status=" + newStatus +" WHERE status=" + oldStatus + ";";
        pstm = conn.prepareStatement(sql);
        return pstm.executeUpdate() > 0;
    }

    @Override
    public List<Thing> dataHandle(ResultSet rs) throws Exception{
        List<Thing> list = new ArrayList<>();
        while(rs.next()) {
            Thing thing = new Thing();
            thing.setSn(rs.getString("sn"));
            thing.setLocate(rs.getString("locate"));
            thing.setLostInfo(rs.getString("lost_info"));
            thing.setDescribe(rs.getString("describe"));
            thing.setBackWay(rs.getString("back_way"));
            thing.setImg(rs.getString("img"));
            thing.setTime(rs.getString("time"));
            thing.setOpenid(rs.getString("openid"));
            list.add(thing);
        }
        return list;
    }
}
