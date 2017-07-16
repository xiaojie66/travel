package cn.jxufe.service;

import cn.jxufe.entity.User;

/**
 * 有关用户业务的服务
 */
public interface UserService {

	// 用户注册
	void regist(User user);

	// 用户登录
	User login(User user);

	// 用户名是否已存在
	boolean isExistAccount(String account);
	
	// 通过主键查找用户
	User findById(Integer id);
	
	// 用户收藏景点
	void collect(User user,Integer placeId);

	// 用户评论景点
	void comment(User user, Integer placeId, String commentContain);

	// 用户修改头像
	void changeAvatar(User user, String avatar);
}
