<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<html lang="zh-CN">

<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta name="description" content="">
<meta name="author" content="">

<title>SearchSystem</title>

<!-- Bootstrap core CSS -->
<link href="../css/bootstrap.css" rel="stylesheet">

<script src="../js/jquery-1.12.4.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

</head>

<body>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="navbar">
					<div class="navbar-inner">
						<div class="container-fluid">
							<a class="btn btn-navbar"
								data-target=".navbar-responsive-collapse" data-toggle="collapse">zhai</a>
							<a class="brand" href="#">招聘试题系统</a>
							<div class="nav-collapse collapse navbar-responsive-collapse">
								<ul class="nav">
									<li><a href="<%=request.getContextPath()%>/home/getHome">主页</a></li>
									<li><a
										href="<%=request.getContextPath()%>/question/getQuestionList">试题搜索</a></li>
									<li><a
										href="<%=request.getContextPath()%>/position/position">职位需求管理</a></li>
									<li><a
										href="<%=request.getContextPath()%>/question/getQuestionManage">试题管理</a></li>
									<li><a href="<%=request.getContextPath()%>/exam/exam">试卷生成</a></li>
								</ul>
							</div>

						</div>
					</div>

				</div>
			</div>
		</div>
	</div>