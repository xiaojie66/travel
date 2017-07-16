<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
<head>
<title>购票 - 去旅游啊</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/travel/style/jquery.mobile-1.4.5.min.css">
<script src="/travel/style/jquery.min.js"></script>
<script src="/travel/style/jquery.mobile-1.4.5.min.js"></script>
</head>

<body>
	<div data-role="header" data-position="fixed" data-tap-toggle="false">
		<h1>确认订单</h1>
		<a href="#" data-role="button" data-iconpos="notext" data-icon="back" data-rel="back"></a>
	</div>

	<div data-role="main" class="ui-content">
		<div class="ui-field-contain">
			<table width="100%">
				<tr>
					<td>用户:</td>
					<td>${user.account }</td>
				</tr>
				<tr>
					<td>景点:</td>
					<td>${place.name }</td>
				</tr>
				<tr>
					<td>单价:</td>
					<td id="price">${place.price }</td>
				</tr>
				<tr>
					<td>数量:</td>
					<td><form action="/travel/order!purchase" method="post">
							<input type="hidden" name="place.id" value="${place.id }" /> <select name="number" id="number" data-iconpos="left">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
							</select>
						</form></td>
				</tr>
				<tr>
					<td>总价:</td>
					<td id="total">${place.price }</td>
				</tr>
				<tr>
					<td colspan="2"><input id="submit" type="button" value="确定提交" /></td>
				</tr>
			</table>
		</div>
	</div>
</body>
<script>
	$(document).ready(function() {
		var price = $("#price").text();
		$("#number").on("change", function() {
			$("#total").text($("#number").val() * price);
		});
		$("#submit").on("click", function() {
			$("form").submit();
		});
	});
</script>
</html>
