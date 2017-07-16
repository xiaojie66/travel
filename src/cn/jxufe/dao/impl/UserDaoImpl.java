package cn.jxufe.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.jxufe.dao.UserDao;
import cn.jxufe.entity.User;

@Repository("userDao")
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public User findById(Integer id) {
		return this.getHibernateTemplate().get(User.class, id);
	}

	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	@SuppressWarnings("unchecked")
	public User findByAccountAndPassword(String account, String password) {
		String hql = "from User where account = ? and password = ? ";
		List<User> list = this.getHibernateTemplate().find(hql, account, password);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public User findByAcconut(String account) {
		String hql = "from User where account = ?";
		List<User> list = this.getHibernateTemplate().find(hql, account);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

}
