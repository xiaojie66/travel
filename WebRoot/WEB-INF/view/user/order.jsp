<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
<head>
<title>用户订单 - 去旅游啊</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/travel/style/jquery.mobile-1.4.5.min.css">
<script src="/travel/style/jquery.min.js"></script>
<script src="/travel/style/jquery.mobile-1.4.5.min.js"></script>
</head>

<body>
	<div data-role="page">
		<div data-role="header" data-position="fixed" data-tap-toggle="false">
			<h1>用户订单</h1>
			<a href="/travel/user" data-role="button" data-iconpos="notext" data-icon="back" data-rel="external"></a>
		</div>
		<div data-role="main" class="ui-content">
			<div data-role="collapsibleset">
				<s:iterator value="order">
					<div data-role="collapsible">
						<h3>${place.name }<s:date name="time" format="yyyy-MM-dd"/></h3>
						<p>票数:${number }</p>
						<p>票单价:${place.price }</p>
						<p>总价:${place.price * number }</p>
					</div>
				</s:iterator>
			</div>
		</div>
	</div>
</body>
</html>
