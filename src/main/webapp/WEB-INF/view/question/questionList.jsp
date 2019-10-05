<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/navbar.jsp"%>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span2"></div>
		<div class="span5">
			<form class="form-search" action="searchQuestion" method="post">
				<input type="text" class="input-medium search-query" name="questionTitle"
					style="width: 350px; height: 20px">
				<button type="submit" class="btn">Search</button>
			</form>
			<div>
				<small>提干信息：<cite>${searchCondition.question}</cite></small>
			</div>
			<div>
				<small>知识点：
					<c:forEach items="${searchCondition.skillNameList}" var="skillName" varStatus="status">
						<cite>${skillName}</cite>
					</c:forEach>
				 </small>
			</div>
			<div>
				<small>题型： 
					<c:forEach items="${searchCondition.questionTypeList}" var="questionType" varStatus="status">
						<cite>${questionType}</cite>
					</c:forEach>
				</small>
			</div>
		</div>
		<div class="span5"></div>
	</div>
	<div class="row-fluid">
		<div class="span2">
			
		</div>
		<div class="span5">

			<c:forEach items="${questionList}" var="question" varStatus="status">
				<blockquote data-toggle="modal"
					data-target="#myModal${question.questionId}">
					<p>${question.questionTitle}</p>
					<small>知识点 <cite>${question.skillName}</cite></small>
					<small>题型 <cite>${question.cateName}</cite></small>
				</blockquote>

				<!-- Modal -->
				<div id="myModal${question.questionId}" class="modal hide fade"
					tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
					aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h3 id="myModalLabel">试题详情</h3>
					</div>

					<div class="container-fluid">
						<div class="row-fluid">
							<div class="span2">
								<h5>题目</h5>
							</div>
							<div class="span10">
								<p>${question.questionTitle}</p>
							</div>
						</div>
						<c:if test="${(question.cateName)==\"单选题\"}">
							<div class="row-fluid">
								<div class="span2"></div>
								<div class="span10">
									<div class="row-fluid">
										<div class="span2">
											<h5>A</h5>
										</div>
										<div class="span10">
											<blockquote>
												<p>${question.a}</p>
											</blockquote>
										</div>
									</div>
									<div class="row-fluid">
										<div class="span2">
											<h5>B</h5>
										</div>
										<div class="span10">
											<blockquote>
												<p>${question.b}</p>
											</blockquote>
										</div>
									</div>
									<div class="row-fluid">
										<div class="span2">
											<h5>C</h5>
										</div>
										<div class="span10">
											<blockquote>
												<p>${question.c}</p>
											</blockquote>
										</div>
									</div>
									<div class="row-fluid">
										<div class="span2">
											<h5>D</h5>
										</div>
										<div class="span10">
											<blockquote>
												<p>${question.d}</p>
											</blockquote>
										</div>
									</div>
								</div>
							</div>
						</c:if>
					</div>
					<div class="modal-footer">
						<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
						<button class="btn btn-primary" onclick=collectQuestion("${question.questionId}")  >收藏</button>
					</div>
				</div>


			</c:forEach>



			<div class="pagination">
				<ul>
					<li><a href="#">上一页</a></li>
					<li><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#">下一页</a></li>
				</ul>
			</div>
		</div>
		<div class="span5">
			<div class="row-fluid">
				<div class="span4"></div>
				<div class="span8">
					<h4>热门试题</h4>
					<table class="table table-hover table-bordered">
						<thead>
							<tr>
								<th>id</th>
								<th>题目</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td>n个数值选出最大m个的最小算法复杂度是</td>
							</tr>
							<tr class="success">
								<td>2</td>
								<td>如何在Spring中使用Hibernate的事务？</td>
							</tr>
							<tr class="success">
								<td>3</td>
								<td>在jquery中，如果想要从DOM中删除所有匹配的元素，下面哪一个是正确的？</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>


<script language=javascript>
function collectQuestion(questionId){
	var question = {
			questionId : questionId
		};
	
	$.ajax({
		url : "http://localhost:8080/SearchSystem/question/collectQuestion",
		type : "get",
		data : question,
		success : function(data) {
			$('#myModal'+questionId).modal('hide');
		}
	});
}
</script>

<%@include file="../common/footer.jsp"%>