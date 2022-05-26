package cn.mldn.shop.factory;

import cn.mldn.shop.service.back.*;
import cn.mldn.shop.service.back.impl.*;
import cn.mldn.shop.service.front.impl.MemberServiceFrontImpl;

public class ServiceBackFactory {
    public static IAdminServiceBack getIAdminServiceBackInstance() {
        return new AdminServiceBackImpl();
    }
    public static IMemberServiceBack getIMemberServiceBackInstance() {
        return new MemberServiceBackImpl() ;
    }
    public static IItemServiceBack getIItemServiceBackInstance() {
        return new ItemServiceBackImpl() ;
    }
    public static IGoodsServiceBack getIGoodsServiceBackInstance() {
        return new GoodsServiceBackImpl() ;
    }
    public static IOrdersServiceBack getIOrdersServiceBackInstance() {
        return new OrdersServiceBackImpl() ;
    }
}
