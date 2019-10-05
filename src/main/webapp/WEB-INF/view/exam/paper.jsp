<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/navbar.jsp"%>

<p>试卷难度：${difficulty}</p>
<p>试卷适应度：${adaptationDegree}</p>
<c:forEach items="${questionBeanList}" var="question" varStatus="status">
	<blockquote data-toggle="modal"
		data-target="#myModal${question.type}">
		<cite>${question.type}:</cite>
		 <cite>${question.stem}</cite>
	</blockquote>
</c:forEach>

<%@include file="../common/footer.jsp"%>