<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
<head>
<title>用户登录 - 去旅游啊</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/travel/style/jquery.mobile-1.4.5.min.css">
<script src="/travel/style/jquery.min.js"></script>
<script src="/travel/style/jquery.mobile-1.4.5.min.js"></script>
</head>

<body>
	<div data-role="page">
		<div data-role="header" data-position="fixed" data-tap-toggle="false">
			<h1>用户登录</h1>
			<a href="#" data-role="button" data-iconpos="notext" data-icon="back" data-rel="back"></a>
		</div>

		<div data-role="main" class="ui-content">
			<form method="post" action="/travel/user!login">
				<div>用户名</div>
				<input type="text" name="account" id="account" />
				<div>密码</div>
				<input type="password" name="password" id="password" />
				<input type="submit" value="登录" />
			</form>
			<a href="/travel/user!jumpRegist" rel="external" class="ui-btn ui-corner-all ui-shadow">注册</a>
			<s:actionerror/>
		</div>
	</div>
</body>

</html>
