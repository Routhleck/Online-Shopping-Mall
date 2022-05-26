package cn.mldn.shop.factory;

import cn.mldn.shop.dao.*;
import cn.mldn.shop.dao.impl.*;

import java.sql.Connection;

public class DAOFactory {
    public static IMemberDAO getIMemberDAOInstance(Connection conn) {
        return new MemberDAOImpl(conn);
    }
    public static IAdminDAO getIAdminDAOInstance(Connection conn) {
        return new AdminDAOImpl(conn) ;
    }
    public static IItemDAO getIItemDAOInstance(Connection conn) {
        return new ItemDAOImpl(conn) ;
    }
    public static IGoodsDAO getIGoodsDAOInstance(Connection conn) {
        return new GoodsDAOImpl(conn) ;
    }
    public static IShopcarDAO getIShopcarDAOInstance(Connection conn) {
        return new ShopcarDAOImpl(conn) ;
    }
    public static IOrdersDAO getIOrdersDAOInstance(Connection conn) {
        return new OrdersDAOImpl(conn) ;
    }
    public static IDetailsDAO getIDetailsDAOInstance(Connection conn) {
        return new DetailsDAOImpl(conn) ;
    }
}
