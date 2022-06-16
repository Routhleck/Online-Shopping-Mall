package com.shopping.dao;

import com.shopping.entity.Card;
import com.shopping.entity.Page;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Repository
public class PageDaoImplement implements PageDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    //getCurrentSession().save(page)���Session���ҽ�page���󱣴�
    public void addPage(Page page) {sessionFactory.getCurrentSession().save(page);
    }
}
