package com.shopping.dao;

import com.shopping.entity.Evaluation;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class EvaluationDaoImplement implements EvaluationDao {

    //SessionFactory在Hibernate中实际上起到了一个缓冲区的作用
    // 他缓冲了HIbernate自动生成SQL语句和其他的映射数据，还缓冲了一些将来有可能重复利用的数据
    private SessionFactory sessionFactory;

    //
    public Evaluation getEvaluation(int userId, int productId, String time) {
        String hql = "from Evaluation where userId=? and productId=? and time=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, productId);
        query.setParameter(2, time);
        return (Evaluation) query.uniqueResult();
    }

    @Override
    public void addEvaluation(Evaluation evaluation) {
        sessionFactory.getCurrentSession().save(evaluation);
    }

    //删除评价
    public boolean deleteEvaluation(int userId, int productId, String time) {
        String hql = "delete Evaluation where userId=? and productId=? and time=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, productId);
        query.setParameter(2, time);
        return query.executeUpdate() > 0;
    }

    //更新评价
    public boolean updateEvaluation(Evaluation evaluation) {
        String hql = "update Evaluation set content=? where userId=? and productId=? and time=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, evaluation.getContent());
        query.setParameter(1, evaluation.getUserId());
        query.setParameter(2, evaluation.getProductId());
        query.setParameter(3, evaluation.getTime());
        return query.executeUpdate() > 0;
    }

    //列出某一商品的评价
    public List<Evaluation> getProductEvaluation(int productId) {
        String hql = "from Evaluation where productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, productId);
        return  query.list();
    }

    //删除用户的评价
    public boolean deleteEvaluationByUser(int userId) {
        String hql = "delete Evaluation where userId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        return query.executeUpdate() > 0;
    }

    //删除商品的评价
    public boolean deleteEvaluationByProduct(int productId) {
        System.out.println("deleteEvaluationByProduct productId "+productId);
        String hql = "delete Evaluation where productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, productId);
        return query.executeUpdate() > 0;
    }
}