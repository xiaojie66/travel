<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
<head>
<title>搜索景点 - 去旅游啊</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/travel/style/jquery.mobile-1.4.5.min.css">
<script src="/travel/style/jquery.min.js"></script>
<script src="/travel/style/jquery.mobile-1.4.5.min.js"></script>
<style>
</style>
</head>

<body>
	<div data-role="header" data-position="fixed" data-tap-toggle="false">
			<table width="100%">
				<tr>
					<td width="80%"><input type="search" name="name" placeholder="你想去哪里" id="keyword" value="${request.name }"></td>
					<td width="20%"><a class="ui-btn" id="search">搜索</a></td>
				</tr>
			</table>
	</div>
	<div data-role="main" class="ui-content">
		<ul id="result" data-role="listview" data-inset="true">
			<s:if test="#request.name==null">
				<li>热门推荐</li>
			</s:if>
			<s:iterator>
				<li>
					<a href="/travel/place!detail?id=${id }" rel="external">
					<img src="${photo }" />
					<div>[${city.name }] ${name }</div>
					<p>${intro }</p>
					</a>
				</li>
			</s:iterator>
		</ul>
	</div>
	<div data-role="footer" data-position="fixed" data-tap-toggle="false">
		<div data-role="navbar">
			<ul>
				<li><a href="/travel" rel="external" data-icon="home">发现景点</a></li>
				<li><a href="/travel/place!find" rel="external" data-icon="search" class="ui-btn-active">搜索景点</a></li>
				<li><a href="/travel/user" rel="external" data-icon="user">个人中心</a></li>
			</ul>
		</div>
	</div>
</body>
<script>
	$("document").ready(function(){
		$("#search").on("click",function(){
			$.ajax({
				url: "/travel/place!search",
				data: {
					"name": $("#keyword").val(),
				},
				type: "post",
				dataType: "json",
				success: function(obj) {
					if(obj.status == 'ok') {
						parseData(obj.data);
					} else {
						console.log("数据拉取失败");
					}
				},
				error: function(obj) {
					console.log("数据拉取错误");
				}
			});
		});
	function parseData(data) {
		$("#result li").remove();
		var ul = $("#result");
		for ( var i = 0; i < data.length; i++) {
			var li = $('<li class="ui-li-has-thumb ui-first-child ui-last-child"></li>');
			var a = $('<a href="/travel/place!detail?id=' + data[i].id
					+ '" class="ui-btn ui-btn-icon-right ui-icon-carat-r" rel="external"></a>');
			a.append('<img src="' + data[i].photo + '"/>');
			a.append('<div>' + '[' + data[i].city.name + '] ' +data[i].name + '</div>');
			a.append('<p>' + data[i].intro + '</p>');
			li.append(a);
			ul.append(li);
		}
	}
	});
</script>
</html>
