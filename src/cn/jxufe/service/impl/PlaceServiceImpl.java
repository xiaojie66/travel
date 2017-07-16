package cn.jxufe.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxufe.dao.PlaceDao;
import cn.jxufe.dao.UserDao;
import cn.jxufe.entity.City;
import cn.jxufe.entity.Comment;
import cn.jxufe.entity.Place;
import cn.jxufe.service.PlaceService;

@Service("placeService")
@Transactional
public class PlaceServiceImpl implements PlaceService {

	@Autowired
	private PlaceDao placeDao;
	@Autowired
	private UserDao userDao;
	
	public void comment(Integer placeId,Comment comment) {
		Place place = placeDao.findPlaceById(placeId);
		Comment c = new Comment();
		c.setContain(comment.getContain());
		c.setTime(new Date());
		c.setUser(userDao.findById(comment.getUser().getId()));
		c.setPlace(place);
		placeDao.save(c);
	}

	public List<City> findAllCity() {
		List<City> citys = placeDao.findAllCity();
		for (City city : citys) {
			city.getPlace();
		}
		return citys;
	}

	public List<Place> findPlaceLikeName(String name) {
		List<Place> places = placeDao.findPlaceLikeName(name);
		return places;
	}

	public Place findPlaceById(Integer placeId) {
		Place place = placeDao.findPlaceById(placeId);
		place.getComment();
		return place;
	}

	public List<Place> findByType(String type) {
		return placeDao.findByType(type);
	}


}
