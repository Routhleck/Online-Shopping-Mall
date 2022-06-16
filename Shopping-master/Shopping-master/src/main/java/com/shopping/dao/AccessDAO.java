package com.shopping.dao;

import com.shopping.bean.Access;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


//用户评价模块
public class AccessDAO {
    //登录连接数据库
    public AccessDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/finaltest?characterEncoding=UTF-8&serverTimezone=GMT",
                "root",
                "root");
    }


    //查询商品的所有评价
    public List<Access> seleteAccessByGoods(String goods_name) {
        List<Access> accesses = new ArrayList<Access>();
        //定义c为连接数据库
        try (Connection c = getConnection();
             //创建语句查询数据库中评价表
             PreparedStatement ps = c.prepareStatement("select * from access where goods_name=?");) {
            //将goods_name定义为查询到的goods_name
            ps.setString(1,goods_name);
            //executeQuery(String sql)方法为执行给定的 select 语句，该语句返回单个 ResultSet 对象；
            ResultSet rs = ps.executeQuery();
            //循环得到所有数据，并调用access函数存储
            while (rs.next()) {
                Access access=new Access();
                String user_account=rs.getString(1);
                String temp_goods_name=rs.getString(2);
                String content=rs.getString(3);
                //double price=rs.getDouble(3);
                //String temp_owner=rs.getString(4);
                access.setContent(content);
                access.setUser_account(user_account);
                access.setGoods_name(temp_goods_name);
                //加入到评价存储数组中
                accesses.add(access);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accesses;
    }

    //增加商品评价
    public void addAccess(Access access) {
        //定义sql语句
        String sql = "insert into access values(?,?,?)";
        //连接数据库并实现sql语句
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            //第一个？号用来记录账号
            ps.setString(1,access.getUser_account());
            //第二个？号用来记录商品名称
            ps.setString(2,access.getGoods_name());
            //第三个？号用来记录评价内容
            ps.setString(3,access.getContent());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除商品评价
    public void deleteAccess(Access access) {
        //定义sql语句
        String sql = "delete from access where user_account = ? and goods_name = ?";
        //连接数据库并实现sql语句
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            //第一个？号是账号
            ps.setString(1,access.getUser_account());
            //第二个？号是商品名称
            ps.setString(2,access.getGoods_name());
            //execute(String sql) 执行给定的 SQL 语句，该语句可能返回多个结果
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}