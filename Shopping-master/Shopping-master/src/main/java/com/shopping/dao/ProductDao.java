package com.shopping.dao;

import com.shopping.entity.Product;

import java.util.List;

//�ӿ�ProductDao
public interface ProductDao {
    //ͨ��id�����ȡ��Ʒ
    public Product getProduct(int id);
    //ͨ��Boss��id����ȡ��Ʒ
    public List<Product> getProductsByBoss(int bossId);

    //ͨ����Ʒ��name��ȡ��Ʒ
    public Product getProduct(String name);

    //��Ӳ�Ʒ
    public void addProduct(Product product);

    //ɾ����Ʒ��ͨ����Ʒid
    public boolean deleteProduct(int id);

    //���²�Ʒ
    public boolean updateProduct(Product product);

    //ͨ���ؼ��ʻ�ȡ��Ʒ
    public List<Product> getProductsByKeyWord(String searchKeyWord);

    //�õ���Ʒ������
    public List<Product> getProductsByType(int type);

    //�õ����е���Ʒ�б�
    public List<Product> getAllProduct();


}
