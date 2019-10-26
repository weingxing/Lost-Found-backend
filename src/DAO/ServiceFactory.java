package DAO;

public class ServiceFactory {
    public static FindService getInstance() {
        return new FindService();
    }
}
