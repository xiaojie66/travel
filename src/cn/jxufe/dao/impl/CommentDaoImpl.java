package cn.jxufe.dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.jxufe.dao.CommentDao;
import cn.jxufe.entity.Comment;
@Repository("commentDao")
public class CommentDaoImpl extends HibernateDaoSupport implements CommentDao {

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public void save(Comment comment) {
		this.getHibernateTemplate().save(comment);
	}

}
