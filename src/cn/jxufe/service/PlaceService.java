package cn.jxufe.service;

import java.util.List;

import cn.jxufe.entity.City;
import cn.jxufe.entity.Comment;
import cn.jxufe.entity.Place;

/**
 * 有关景点评论的服务
 */
public interface PlaceService {

	// 用户评论景点
	void comment(Integer placeId,Comment comment);

	// 查询所有城市包括下面的景点
	List<City> findAllCity();

	// 通过关键字搜索景点的描述
	List<Place> findPlaceLikeName(String name);

	// 查询景点详细信息
	Place findPlaceById(Integer placeId);
	
	List<Place> findByType(String type);
}
