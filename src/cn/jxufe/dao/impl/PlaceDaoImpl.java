package cn.jxufe.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.jxufe.dao.PlaceDao;
import cn.jxufe.entity.City;
import cn.jxufe.entity.Comment;
import cn.jxufe.entity.Place;

@Repository("abcdefg")
public class PlaceDaoImpl extends HibernateDaoSupport implements PlaceDao {

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public Place findPlaceById(Integer placeId) {
		return this.getHibernateTemplate().get(Place.class, placeId);
	}

	public void save(Comment comment) {
		this.getHibernateTemplate().save(comment);
	}

	public List<City> findAllCity() {
		return this.getHibernateTemplate().loadAll(City.class);
	}

	// 城市名 与 景点名模糊查询
	@SuppressWarnings("unchecked")
	public List<Place> findPlaceLikeName(String name) {
		String hql = "from Place as p where p.name like :name or p.city.name like :name";
		Query q = getSession().createQuery(hql);
		q.setString("name", "%" + name + "%");
		return q.list();
	}

	@SuppressWarnings("unchecked")
	public List<Place> findByType(String type) {
		String hql = "from Place as p where p.type = :type";
		Query q = getSession().createQuery(hql);
		q.setString("type", type);
		return q.list();
	}

}
