package cn.jxufe.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.jxufe.entity.User;
import cn.jxufe.service.UserService;
import cn.jxufe.util.Directory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Action("user")
@Namespace("/")
@ParentPackage("travel")
@Results({ 
	//@Result(name = "input", location = Directory.LOGIN),
	@Result(name = "success", location = Directory.SELF),
	@Result(name = "reload", location = "user", type="redirectAction"),
	@Result(name = "order", location = Directory.ORDER),
	@Result(name = "jumpLogin", location = Directory.LOGIN), 
	@Result(name = "jumpRegist", location = Directory.REGIST),
	@Result(name = "home", location = "place", type = "redirectAction"), 
})
public class UserAction extends ActionSupport implements ModelDriven<User> {

	private static final long serialVersionUID = 1L;

	private User user = new User();

	@Autowired
	private UserService userService;

	public User getModel() {
		return user;
	}

	// 获取用户收藏景点id
	private Integer placeId;

	public Integer getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Integer placeId) {
		this.placeId = placeId;
	}

	// [个人中心]加载用户个人信息 ... 手动拦截
	@Override
	public String execute() {
		User user = (User) ActionContext.getContext().getSession().get("sessionUser");
		if (user == null) {
			return "jumpLogin";
		} else {
			user = userService.findById(user.getId());
			ActionContext.getContext().getValueStack().push(user);
			return "success";
		}
	}

	// [用户订单]加载用户订单 ... 手动拦截
	public String order(){
		User user = (User) ActionContext.getContext().getSession().get("sessionUser");
		if (user == null) {
			return "jumpLogin";
		} else {
			user = userService.findById(user.getId());
			user.getOrder();
			ActionContext.getContext().getValueStack().push(user);
			return "order";
		}
	}
	
	// [用户登录]
	public String login() {
		User sessionUser = (User) ActionContext.getContext().getSession().get("sessionUser");
		// 无用户session
		if (sessionUser == null) {
			user = userService.login(user);
			if (user == null) {
				this.addActionError("用户名或密码错误");
				return "jumpLogin";
			} else {
				ActionContext.getContext().getSession().put("sessionUser", user);
			}
		}
		return "reload";
	}

	// [用户注册]
	public String regist() {
		userService.regist(user);
		return "jumpLogin";
	}

	// 跳转注册
	public String jumpRegist() {
		return "jumpRegist";
	}

	// 跳转登录
	public String jumpLogin() {
		return "jumpLogin";
	}

	// [退出登录]
	public String logout() {
		ActionContext.getContext().getSession().remove("sessionUser");
		return "home";
	}

	// 用户收藏景点
	public void collect() throws IOException {
		User user = (User) ActionContext.getContext().getSession().get("sessionUser");
		userService.collect(user, placeId);
		writeJson("ok", "已收藏");
	}

	// 检测重复账号
	public void check() throws IOException {
		boolean exist = userService.isExistAccount(user.getAccount());
		if (exist) {
			writeJson("fail", "用户名已存在");
		} else {
			writeJson("ok", "可以使用的用户名");
		}
	}
	
	public void changeAvatar(){
		String avatar = user.getAvatar();
		User user = (User) ActionContext.getContext().getSession().get("sessionUser");
		userService.changeAvatar(user,avatar);
	}

        // 返回Json数据
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
}
