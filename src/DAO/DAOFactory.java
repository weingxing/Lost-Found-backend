package DAO;

import java.sql.Connection;

public class DAOFactory {
    public static FindDAO getFindDAO(Connection conn) {
        return new FindDAO(conn);
    }
}
