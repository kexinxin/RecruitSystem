<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="css/bootstrap.css" rel="stylesheet">

<script src="js/jquery-1.12.4.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#add").click(function() {
			var userName = $("#userName").val();
			var password = $("#password").val();
			var user = {
				userName : userName,
				password : password
			};

			$.ajax({
				url : "http://localhost:8080/SearchSystem/question/test",
				type : "get",
				data : user,
				success : function(data) {
					alert("userName--->" + data.userName);
				}
			});

		});
	});
</script>

<script language=javascript>
	function aa(str){
	alert("text的value是:"+str);
	var user = {
			userName : str,
			password : str
		};
	
	$.ajax({
		url : "http://localhost:8080/SearchSystem/question/testCrawler",
		type : "get",
		data : user,
		success : function(data) {
			alert("userName--->" + data.userName);
		}
	});
}
</script>

<html>
<body>

	<h2>Hello World!</h2>
	<form action="http://localhost:8080/SearchSystem/upload" method="post"
		enctype="multipart/form-data">
		选择文件:<input type="file" name="file" width="120px"> <input
			type="submit" value="上传">
	</form>
	<hr>
	<form action="http://localhost:8080/SearchSystem/down" method="get">
		<input type="submit" value="下载">
	</form>

	<h>json添加用户</h>

	
	姓名：
	<input type="text" name="userName" id="userName" /> 密码：
	<input type="text" name="password" id="password" />
	<input type="button" value="添加" onclick=aa(userName.value) />
	
</body>
</html>
