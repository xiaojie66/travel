<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
<head>
<title>个人中心 - 去旅游啊</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/travel/style/jquery.mobile-1.4.5.min.css">
<script src="/travel/style/jquery.min.js"></script>
<script src="/travel/style/jquery.mobile-1.4.5.min.js"></script>
<style>
	#xiugai img{
		border: solid 5px white;
		border-radius: 1000px;
	}
</style>
</head>
<body>
	<div data-role="page">
		<div data-role="header" data-position="fixed" data-tap-toggle="false">
			<h1>个人中心</h1>
		</div>

		<div data-role="main" class="ui-content">
			<div id="touxiang" style="width:60%;margin:0 auto;">
				<a href="#xiugai" data-rel="popup" class="ui-btn1"><img src="${avatar }" style="width:100%;border-radius:1000px;" /> </a>
			</div>
			<div id="account" style="text-align:center;">${account }</div>
			<ul data-role="listview" data-inset="true">
				<li><a href="/travel/user!order" rel="external">我的订单</a></li>
				<li><a href="#">我的收藏</a></li>
			</ul>
			<div data-role="popup" id="xiugai" class="ui-content" style="min-width:250px;">
				<form method="post">
					<div>
						<h3 style="text-align:center;">修改头像</h3>
						<img src="/travel/resource/avatar/man1.svg" width="45%"/>
						<img src="/travel/resource/avatar/man2.svg" width="45%"/>
						<img src="/travel/resource/avatar/woman1.svg" width="45%"/>
						<img src="/travel/resource/avatar/woman2.svg" width="45%"/>
						<input type="button" value="提交"/>
					</div>
				</form>
			</div>
			<a href="/travel/user!logout" rel="external" class="ui-btn ui-corner-all ui-shadow" style="color:#F33">退出</a>
		</div>
		<div data-role="footer" data-position="fixed" data-tap-toggle="false">
			<div data-role="navbar">
				<ul>
					<li><a href="/travel" rel="external" data-icon="home">发现景点</a></li>
					<li><a href="/travel/place!find" rel="external" data-icon="search">搜索景点</a></li>
					<li><a href="/travel/user" rel="external" data-icon="user" class="ui-btn-active">个人中心</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
<script>
	$(document).ready(function(){
		var src;
		$("#xiugai img").on("click",function(){
			src=$(this).attr("src");
			$("#xiugai img").css("border","solid 5px white");
			$(this).css("border","solid 5px #6F9");
		});
		$("#xiugai input").on("click",function(){
			if(src==null||src.length<0)
				return;
			$.ajax({
				url : "/travel/user!changeAvatar",
				data : {
					"avatar" : src,
				},
				type : "post",
				success : function() {
					location.reload();
				},
				error : function() {
					console.log("数据拉取错误");
				}
			});
		});
	});
</script>
</html>
