package com.shopping.dao;

import com.shopping.bean.Goods;
import com.shopping.bean.Image;

import java.io.*;
import java.sql.*;

//图片DAO
public class ImageDAO {
    InputStream in;
    //连接数据库
    public ImageDAO() {
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

//向数据库中插入一张图片

    public void addImage(Image image) {
        String sql = "insert into image values(?,?,?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            InputStream inputStream= new ByteArrayInputStream(image.getPhoto());
            ps.setString(1,image.getGood_name());
            ps.setBinaryStream(2,inputStream,inputStream.available());
            ps.setString(3,image.getDescription());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //从数据库读取商品图片
    public InputStream getImage(String good_name){
        String sql = "select * from image where good_name = ?";
        Image image = null;
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1,good_name);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
                in = rs.getBinaryStream("photo");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return in;
    }

}