<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
<head>
<title>景点详细 - 去旅游啊</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/travel/style/jquery.mobile-1.4.5.min.css">
<script src="/travel/style/jquery.min.js"></script>
<script src="/travel/style/jquery.mobile-1.4.5.min.js"></script>
<style>
</style>
</head>

<body>
	<div data-role="page">
		<div data-role="header" data-position="fixed" data-tap-toggle="false">
			<h1>${name }</h1>
			<a href="/travel/place!find" data-role="button" data-iconpos="notext" data-icon="back" data-rel="external"></a>
		</div>

		<div data-role="main" class="ui-content">
			<div>
				<img id="background" src="${photoA }" width="100%" />
			</div>
			<div>
				<p>景点地址:${address }</p>
				<p>开放时间:${opening }</p>
				<p>景点类型:${type }</p>
				<p>景点级别:${level }</p>
				<p>票价:${price }</p>
				<p>详细介绍:${desc }</p>
			</div>
			<div>
				<p>游客评论:</p>
				<s:iterator value="comment">
				<div style="margin:20px 0;">
					<img src="${user.avatar }" width="20%" style="border-radius:1000px; float:left"/>
					<div><h4>${user.account }</h4></div>
					<div>${contain }</div>
					<div style="text-align:right;">
						<s:date name="time" format="yyyy-MM-dd" />
					</div>
				</div>	
				</s:iterator>
			</div>
		</div>

		<div data-role="popup" id="pinglun" class="ui-corner-all" style="min-width:250px;">
			<a href="#" data-rel="back" data-role="button" data-icon="delete" data-iconpos="notext" class="ui-btn-right"></a>
			<div data-role="content" class="ui-corner-bottom ui-content">
				<form action="/travel/place!comment" method="post" id="user-comment">
					<input type="hidden" name="id" value=${id } />
					<textarea name="contain" style="width: 100%; height: 200px;"></textarea>
					<input type="button" value="评论" id="cmt"/>
				</form>
			</div>
		</div>
		<div data-role="footer" data-position="fixed" data-tap-toggle="false">
			<div data-role="navbar">
				<ul>
					<li><a href="#" data-icon="heart">收藏</a></li>
					<li><a href="#pinglun" data-rel="popup" data-icon="comment">评论</a></li>
					<li><a href="/travel/place!order?id=${id }" rel="external" data-icon="check">立即预定</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
<script>
	$("document").ready(function() {
		$("#cmt").on("click", function() {
			$.ajax({
				url : "/travel/place!comment",
				data : $("#user-comment").serialize(),
				type : "post",
				dataType : "json",
				success : function(obj) {
					if (obj.status == 'ok') {
						location.reload();
					} else {
						console.log("数据拉取失败");
					}
				},
				error : function(obj) {
					console.log("数据拉取错误");
				}
			});
		});
		
		var index = 0;		
		setInterval(function(){
			index = (index+1)%3;
			var img = ["${photoA}","${photoB}","${photoC}"];
			$("#background").attr("src",img[index]);
		}, 1500);
	});
	
	
</script>
</html>
