package cn.jxufe.dao;

import java.util.List;

import cn.jxufe.entity.City;
import cn.jxufe.entity.Comment;
import cn.jxufe.entity.Place;

public interface PlaceDao {

	// 通过主键查找
	Place findPlaceById(Integer id);

	// 保存新评论
	void save(Comment comment);

	// 找所有城市
	List<City> findAllCity();

	// 模糊查询景点
	List<Place> findPlaceLikeName(String name);

	// 通过类型查找景点
	List<Place> findByType(String type);
}
