<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
<head>
<title>用户注册 - 去旅游啊</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/travel/style/jquery.mobile-1.4.5.min.css">
<script src="/travel/style/jquery.min.js"></script>
<script src="/travel/style/jquery.mobile-1.4.5.min.js"></script>
</head>

<body>
	<div data-role="page">
		<div data-role="header" data-position="fixed" data-tap-toggle="false">
			<h1>用户注册</h1>
			<a href="#" data-role="button" data-iconpos="notext" data-icon="back" data-rel="back"></a>
		</div>

		<div data-role="main" class="ui-content">
			<form method="post" action="/travel/user!regist" id="regist-form" >
				<div id="tishia" style="float:right;"></div>
				<div>用户名</div>
				<input type="text" name="account" id="account" placeholder="输入4-16个字符"/>
				<div id="tishib" style="float:right;"></div>
				<div>密码</div>
				<input type="password" name="password" id="password" placeholder="输入4-16个字符"/>
				<input type="button" id="user-submit" value="注册" />
			</form>
		</div>
	</div>
</body>
<script>
	$(document).ready(function() {
		$("#account").on("blur", function() {
			var pattern = new RegExp("^\\w{4,16}$");
			var account = $("#account").val();
			if(!pattern.test(account)){
				$("#tishia").text("用户名不规范");
				return;
			}else{
				$("#tishia").text("");
			}
			$.ajax({
				url : "/travel/user!check",
				data : {
					"account" : account,
				},
				type : "post",
				dataType : "json",
				success : function(obj) {
					$("#tishia").text(obj.data);
				},
				error : function(obj) {
					console.log("数据拉取错误");
				}
			});
		});
		
		$("#password").on("blur", function() {
			var pattern = new RegExp("^\\w{4,16}$");
			var password = $("#password").val();
			if(!pattern.test(password)){
				$("#tishib").text("密码不规范");
			}else{
				$("#tishib").text("");
			}
		});
		
		$("#user-submit").on("click", function() {
			var pattern = new RegExp("^\\w{4,16}$");
			var account = $("#account").val();
			var password = $("#password").val();
			if (!pattern.test(account) || !pattern.test(password)) {
				alert("用户名或密码不规范");
				return;
			} else {
				$("#regist-form").submit();
			}
		});
	});
</script>
</html>
