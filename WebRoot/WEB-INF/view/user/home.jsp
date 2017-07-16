<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
<head>
<title>发现景点 - 去旅游啊</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/travel/style/jquery.mobile-1.4.5.min.css">
<script src="/travel/style/jquery.min.js"></script>
<script src="/travel/style/jquery.mobile-1.4.5.min.js"></script>
<style>
	.ui-grid-d img{
		width: 80%;
	}
	.ui-grid-d div{
		font-size: 85%;
	}
	.group{
		margin-top:15px;
	}
	.city .cname{
		position: absolute;
		color:#FFF;
		left:50%;
		top: 50%;
    	transform: translate(-50%, -50%);
		font-size: 180%;
	}
	.city{
		position:relative;
	}
</style>
</head>

<body>
	<div data-role="page">
		<div data-role="header" data-position="fixed" data-tap-toggle="false">
			<h1>发现景点</h1>
		</div>
		<div class="ui-grid-d">
			<div class="ui-block-a" style="text-align: center;">
				<img src="/travel/resource/icon/1.svg"/><div>游乐园</div>
			</div>
			<div class="ui-block-b" style="text-align: center;">
				<img src="/travel/resource/icon/2.svg"/><div>小吃街</div>
			</div>
			<div class="ui-block-c" style="text-align: center;">
				<img src="/travel/resource/icon/3.svg"/><div>文化遗址</div>
			</div>
			<div class="ui-block-d" style="text-align: center;">
				<img src="/travel/resource/icon/4.svg"/><div>历史古迹</div>
			</div>
			<div class="ui-block-e" style="text-align: center;">
				<img src="/travel/resource/icon/5.svg"/><div>海边风光</div>
			</div>
			<div class="ui-block-a" style="text-align: center;">
				<img src="/travel/resource/icon/6.svg"/><div>购物中心</div>
			</div>
			<div class="ui-block-b" style="text-align: center;">
				<img src="/travel/resource/icon/7.svg"/><div>动物园</div>
			</div>
			<div class="ui-block-c" style="text-align: center;">
				<img src="/travel/resource/icon/8.svg"/><div>地方标志</div>
			</div>
			<div class="ui-block-d" style="text-align: center;">
				<img src="/travel/resource/icon/9.svg"/><div>登山必选</div>
			</div>
			<div class="ui-block-e" style="text-align: center;">
				<img src="/travel/resource/icon/10.svg"/><div>大学学府</div>
			</div>
		</div>

		<div data-role="main">
			<s:iterator>
				<div class="group" title="${name }">
					<div class="city">
						<div class="cname">${name }</div>
						<img src="${photo }" style="width:100%" />
					</div>
					<div class="place">
						<s:iterator value="place">
							<div style="display:inline-block; width:32%; font-size:80%;">${name }</div>
							<%-- <div>${name }</div>--%>
						</s:iterator>
					</div>
				</div>
			</s:iterator>
		</div>
		<div data-role="footer" data-position="fixed" data-tap-toggle="false">
			<div data-role="navbar">
				<ul>
					<li><a href="/travel" rel="external" data-icon="home" class="ui-btn-active">发现景点</a></li>
					<li><a href="/travel/place!find" rel="external" data-icon="search">搜索景点</a></li>
					<li><a href="/travel/user" rel="external" data-icon="user">个人中心</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
<script>
$(document).ready(function(){
	$(".group").on("click", function(){
		location.href = "/travel/place!find?name=" + $(this).attr("title");
	});
	
	$(".ui-grid-d div").on("click", function(){
		location.href = "/travel/place!find?type=" + $(this).children("div").text();
	});
});
</script>
</html>
