package cn.jxufe.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.jxufe.entity.City;
import cn.jxufe.entity.Place;
import cn.jxufe.entity.User;
import cn.jxufe.service.PlaceService;
import cn.jxufe.service.UserService;
import cn.jxufe.util.Directory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Action("place")
@Namespace("/")
@ParentPackage("travel")
@Results({ @Result(name = "success", location = Directory.HOME), @Result(name = "find", location = Directory.SEARCH),
		@Result(name = "detail", location = Directory.DETAIL), @Result(name = "confirm", location = Directory.CONFIRM),
		@Result(name = "login", location = "user!jumpLogin", type = "redirectAction"), })
public class PlaceAction extends ActionSupport implements ModelDriven<Place> {

	private static final long serialVersionUID = 1L;

	private Place place = new Place();

	public Place getModel() {
		return place;
	}

	// 获取评论内容
	private String contain;

	public String getContain() {
		return contain;
	}

	public void setContain(String contain) {
		this.contain = contain;
	}

	@Autowired
	private UserService userService;
	@Autowired
	private PlaceService placeService;

	@Override
	// [发现景点]加载靠前的6个城市和城市的景点
	public String execute() {
		List<City> list = placeService.findAllCity();
		ActionContext.getContext().getValueStack().push(list);
		return "success";
	}

	// [搜索景点]搜索火热的几个景点给用户
	public String find() {
		List<Place> list;
		String name = place.getName();
		String type = place.getType();
		if (name != null && name.length() > 0) {
			list = placeService.findPlaceLikeName(name);
		} else if (type != null && type.length() > 0) {
			list = placeService.findByType(type);
		} else {
			type = "热门推荐";
			list = placeService.findByType(type);
		}
		ActionContext.getContext().getValueStack().push(list);
		return "find";
	}

	// [搜索 - 搜索景点]将用户提交的关键字查询结果通过AJAX返回
	public void search() throws IOException {
		// 获取查询关键字
		String name = place.getName();
		List<Place> places = Collections.emptyList();
		if (name.length() != 0) {
			places = placeService.findPlaceLikeName(name);
		} else {
			System.out.println("没有搜索内容...");
			return;
		}
		// 配置JSON避免循环
		JsonConfig config = new JsonConfig();
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		config.setExcludes(new String[] { "comment", "place" });
		// 构造数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "ok");
		map.put("data", places);
		JSONObject json = JSONObject.fromObject(map, config);
		// 输出JSON
		writeJson(json);
	}

	// 查看某个景点详细信息
	public String detail() {
		place = placeService.findPlaceById(place.getId());
		ActionContext.getContext().getValueStack().push(place);
		return "detail";
	}

	// [订票] ... 手动拦截
	public String order() {
		User user = (User) ActionContext.getContext().getSession().get("sessionUser");
		if (user == null) {
			return "login";
		} else {
			user = userService.findById(user.getId());
			place = placeService.findPlaceById(place.getId());
			ServletActionContext.getRequest().setAttribute("user", user);
			ServletActionContext.getRequest().setAttribute("place", place);
			return "confirm";
		}
	}

	// [用户评论] ... 手动拦截
	public void comment() throws IOException {
		User user = (User) ActionContext.getContext().getSession().get("sessionUser");
		if (user == null) {
			writeJson("fail", "尚未登录");
		} else {
			userService.comment(user, place.getId(), contain);
			writeJson("ok", "评论成功");
		}
	}

	private void writeJson(String status, String data) throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", status);
		map.put("data", data);
		JSONObject json = JSONObject.fromObject(map);
		out.print(json);
	}

        // 返回Json数据
	private void writeJson(JSONObject json) throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(json);
	}
}
