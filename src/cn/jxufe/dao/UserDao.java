package cn.jxufe.dao;

import cn.jxufe.entity.User;

public interface UserDao {

	// 通过主键查找用户
	User findById(Integer id);

	// 保存新注册的用户
	void save(User user);

	// 通过账号密码匹配用户
	User findByAccountAndPassword(String account,String password);

	// 找重复账号
	User findByAcconut(String account);
}
