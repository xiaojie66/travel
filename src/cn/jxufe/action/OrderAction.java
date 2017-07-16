package cn.jxufe.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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

import cn.jxufe.entity.Order;
import cn.jxufe.entity.User;
import cn.jxufe.service.OrderService;
import cn.jxufe.util.Directory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Action("order")
@Namespace("/")
@ParentPackage("travel")
@Results({ 
	@Result(name = "success", location = Directory.ORDER), 
	@Result(name = "self", location = "user", type = "redirectAction"),
})
public class OrderAction extends ActionSupport implements ModelDriven<Order>{

	private static final long serialVersionUID = 1L;

	private Order order = new Order();

	public Order getModel() {
		return order;
	}

	@Autowired
	private OrderService orderService;
	
	// 用户点击下单
	public String purchase() throws IOException {
		User user = (User) ActionContext.getContext().getSession().get("sessionUser");
		order.setUser(user);
		order.setTime(new Date());
		orderService.save(order);
		writeJson("ok","保存成功");
		return "self";
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
