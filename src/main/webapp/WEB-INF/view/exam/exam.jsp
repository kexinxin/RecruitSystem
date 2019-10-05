<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/navbar.jsp"%>



<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="tabbable" id="tabs-201610">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#panel-526157" data-toggle="tab">根据知识点生成试卷</a>
					</li>
					<li><a href="#panel-139315" data-toggle="tab"></a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="panel-526157">
						<form class="form-horizontal" action="paper" method="post">

							<h4 contenteditable="true">题型数量</h4>
							<div class="control-group">
								<label class="control-label" for="inputEmail">题目总数</label>
								<div class="controls">
									<input type="text" id="inputEmail" placeholder="题目总数"
										style="height: 30px;">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputPassword">选择题数量</label>
								<div class="controls">
									<input name="singleNum" type="text" id="inputEmail" placeholder="选择题"
										style="height: 30px;">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputPassword">简答题数量</label>
								<div class="controls">
									<input name="completeNum" type="text" id="inputEmail" placeholder="简答题数量"
										style="height: 30px;">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputPassword">编程题数量</label>
								<div class="controls">
									<input name="subjectiveNum" type="text" id="inputEmail" placeholder="编程题数量"
										style="height: 30px;">
								</div>
							</div>
							<h4 contenteditable="true">试卷总体难度</h4>
							<div class="control-group">
								<label class="control-label" for="inputPassword">试卷总体难度</label>
								<div class="controls">
									<input name="difficulty" type="text" id="inputEmail" placeholder="试卷总体难度"
										style="height: 30px;">
								</div>
							</div>
							<h4 contenteditable="true">知识点选择</h4>
							<div>
								<label class="checkbox inline"> <input name="totalPointName" type="checkbox"
								id="inlineCheckbox1" value="操作系统"> 操作系统
							</label> <label class="checkbox inline"> <input name="totalPointName" type="checkbox"
								id="inlineCheckbox2" value="Linux"> Linux
							</label> <label class="checkbox inline"> <input name="totalPointName" type="checkbox"
								id="inlineCheckbox3" value="java"> java
							</label><label class="checkbox inline"> <input name="totalPointName" type="checkbox"
								id="inlineCheckbox2" value="SQL"> SQL
							</label> <label class="checkbox inline"> <input name="totalPointName" type="checkbox"
								id="inlineCheckbox3" value="Jquery"> Jquery
							</label>
							<label class="checkbox inline"> <input name="totalPointName" type="checkbox"
								id="inlineCheckbox3" value="查找"> 查找
							</label>
							</div>
							<div>
								<label class="checkbox inline"> <input name="totalPointName" type="checkbox"
								id="inlineCheckbox3" value="动态规划"> 动态规划
							</label>
							<label class="checkbox inline"> <input name="totalPointName" type="checkbox"
								id="inlineCheckbox3" value="复杂度"> 复杂度
							</label>
							<label class="checkbox inline"> <input name="totalPointName" type="checkbox"
								id="inlineCheckbox3" value="自动化测试"> 自动化测试
							</label>
							<label class="checkbox inline"> <input name="totalPointName" type="checkbox"
								id="inlineCheckbox3" value="软件工程"> 软件工程
							</label>
							</div>
							<div class="control-group">
								<h4 contenteditable="true">附加描述</h4>
								<div class="controls">
									<textarea rows="3" name="describle"></textarea>
								</div>
							</div>
							<div class="control-group">
								<div class="controls">
									<button type="submit" class="btn">提交</button>
								</div>
							</div>
						</form>
					</div>
					<div class="tab-pane" id="panel-139315">
						<p>第二部分内容.</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>



<%@include file="../common/footer.jsp"%>