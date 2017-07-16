package cn.jxufe.service.impl;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxufe.dao.CommentDao;
import cn.jxufe.dao.PlaceDao;
import cn.jxufe.dao.UserDao;
import cn.jxufe.entity.Comment;
import cn.jxufe.entity.Place;
import cn.jxufe.entity.User;
import cn.jxufe.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private PlaceDao placeDao;
	@Autowired
	private CommentDao commentDao;
	
	public void regist(User user) {
		user.setAvatar("/travel/resource/avatar/man1.svg");
		userDao.save(user);
	}

	public User login(User user) {
		return userDao.findByAccountAndPassword(user.getAccount(), user.getPassword());
	}

	public boolean isExistAccount(String account) {
		return userDao.findByAcconut(account) == null ? false : true;
	}

	public User findById(Integer id) {
		return userDao.findById(id);
	}

	public void collect(User user, Integer placeId) {
		user = userDao.findById(user.getId());
		Set<Place> place = user.getCollectPlace();
		place.add(placeDao.findPlaceById(placeId));
	}

	public void comment(User user, Integer placeId, String commentContain) {
		Place place = placeDao.findPlaceById(placeId);
		Comment comment = new Comment();
		comment.setContain(commentContain);
		comment.setPlace(place);
		comment.setUser(user);
		comment.setTime(new Date());
		commentDao.save(comment);
	}

	public void changeAvatar(User user,String avatar) {
		user = userDao.findById(user.getId());
		user.setAvatar(avatar);
	}

}
