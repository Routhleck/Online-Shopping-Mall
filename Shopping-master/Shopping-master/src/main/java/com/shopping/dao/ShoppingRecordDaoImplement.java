package com.shopping.dao;

import com.shopping.entity.ShoppingCar;
import com.shopping.entity.ShoppingRecord;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Repository
public class ShoppingRecordDaoImplement implements ShoppingRecordDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public ShoppingRecord getShoppingRecord(int userId, int productId,String time) {
        //查询购物记录
        String hql = "from ShoppingRecord where userId=? and productId=? and time=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, productId);
        query.setParameter(2, time);
        return (ShoppingRecord) query.uniqueResult();
    }

    @Override
    public void addShoppingRecord(ShoppingRecord shoppingRecord) {
        //增加购物记录
        System.out.println("传到ShoppingRecordDao里的shopId is "+shoppingRecord.getShopId());
        sessionFactory.getCurrentSession().save(shoppingRecord);
    }

    @Override
    public boolean deleteShoppingRecord(int userId, int productId) {
        //删除购物记录
        String hql = "delete ShoppingRecord where userId=? and productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, productId);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean updateShoppingRecord(ShoppingRecord shoppingRecord) {
        //更新购物记录
        String hql = "update ShoppingReocrd set orderStatus=? where userId=? and productId=? and time=?";
        String sql = "update shopping_record set order_status="+shoppingRecord.getOrderStatus()+" where user_id="+shoppingRecord.getUserId()+" and product_id="+shoppingRecord.getProductId()+" and time='"+shoppingRecord.getTime()+"'";
//        Query query = sessionFactory.getCurrentSession().createQuery(hql);
//        query.setParameter(0,shoppingRecord.getOrderStatus());
//        query.setParameter(1,shoppingRecord.getUserId());
//        query.setParameter(2,shoppingRecord.getProductId());
//        query.setParameter(3,shoppingRecord.getTime());
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        return query.executeUpdate() > 0;
    }

    @Override
    public List<ShoppingRecord> getShoppingRecords(int userId) {
        //通过用户ID获取购物记录
        String hql = "from ShoppingRecord where userId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,userId);
        List<ShoppingRecord> shoppingRecordList=query.list();
       System.out.println("shoppingRecordList的大小是 "+shoppingRecordList.size());
        for(int i=0;i<shoppingRecordList.size();i++){
            System.out.println("在Dao中的productId,userId(买家),shopId(卖家)是："+shoppingRecordList.get(i).getProductId()+"  "
                    +shoppingRecordList.get(i).getUserId()+" "+shoppingRecordList.get(i).getShopId()+" "
                    +shoppingRecordList.get(i).getTime()+" "+
                    shoppingRecordList.get(i).getProductPrice()+" "
                    +shoppingRecordList.get(i).getCounts()+" "+shoppingRecordList.get(i).getOrderStatus());
        }
        return query.list();
    }

    @Override
    public List<ShoppingRecord> getAllShoppingRecords() {
        //获取全部购物记录
        String hql = "from ShoppingRecord ";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        List<ShoppingRecord> records=new ArrayList<>();
        records=query.list();
        System.out.println("这里是dao");
        for(int i=0;i<records.size();i++){
            System.out.println("user is "+records.get(i).getUserId());
        }
        return query.list();
    }

    @Override
    public List<ShoppingRecord> getShoppingRecordsByOrderStatus(int orderStatus) {
        //通过订单状态获取购物记录
        String hql = "from ShoppingRecord where orderStatus=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,orderStatus);
        return query.list();
    }

    @Override
    public boolean getUserProductRecord(int userId,int productId) {
        //指定用户和购物ID获取购物记录
        String hql = "from ShoppingRecord where userId=? and productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,userId);
        query.setParameter(1,productId);
        return query.list().size()>0;
    }

    @Override
    public boolean deleteShoppingRecordByUser(int userId) {
        //指定用户ID删除购物记录
        String hql = "delete ShoppingRecord where userId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean deleteShoppingRecordByProductId(int productId) {
        //通过产品ID删除购物记录
        System.out.println("deleteShoppingRecordByProductId id"+productId);
        String hql = "delete ShoppingRecord where productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, productId);
        return query.executeUpdate() > 0;
    }
}
