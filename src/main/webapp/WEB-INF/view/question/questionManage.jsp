<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/navbar.jsp"%>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="tabbable" id="tabs-609216">
				<ul class="nav nav-tabs">
					<li class=" active"><a href="#panel-486892" data-toggle="tab">收藏的试题</a>
					</li>
					<li><a href="#panel-134890" data-toggle="tab">新增选择题</a></li>
					<li><a href="#panel-134891" data-toggle="tab">新增简答题</a></li>
					<li><a href="#panel-134892" data-toggle="tab">新增编程题</a></li>
					<li><a href="#panel-134893" data-toggle="tab">Excel试题导入</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="panel-486892">
						<div class="row-fluid">
							<div class="span2"></div>
							<div class="span5">

								<!-- Modal -->
								<div id="myModal" class="modal hide fade" tabindex="-1"
									role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
												<p>
													<em>Git</em>是一个分布式的版本控制系统，最初由<strong>Linus
														Torvalds</strong>编写，用作Linux内核代码的管理。在推出后，Git在其它项目中也取得了很大成功，尤其是在Ruby社区中。
												</p>
											</div>
										</div>
										<div class="row-fluid">
											<div class="span2"></div>
											<div class="span10">
												<div class="row-fluid">
													<div class="span2">
														<h5>A</h5>
													</div>
													<div class="span10">
														<blockquote>
															<p>github是一个全球化的开源社区.</p>
														</blockquote>
													</div>
												</div>
												<div class="row-fluid">
													<div class="span2">
														<h5>B</h5>
													</div>
													<div class="span10">
														<blockquote>
															<p>github是一个全球化的开源社区.</p>
														</blockquote>
													</div>
												</div>
												<div class="row-fluid">
													<div class="span2">
														<h5>C</h5>
													</div>
													<div class="span10">
														<blockquote>
															<p>github是一个全球化的开源社区.</p>
														</blockquote>
													</div>
												</div>
												<div class="row-fluid">
													<div class="span2">
														<h5>D</h5>
													</div>
													<div class="span10">
														<blockquote>
															<p>github是一个全球化的开源社区.</p>
														</blockquote>
													</div>
												</div>
											</div>
										</div>
									</div>

									<div class="modal-footer">
										<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
										<button class="btn btn-primary">收藏</button>
									</div>
								</div>

								<c:forEach items="${questionList}" var="question"
									varStatus="status">
									<blockquote data-toggle="modal" data-target="#myModal">
										<p>${question.questionTitle}</p>
										<small>知识点 <cite>${question.skillName}</cite></small>
									</blockquote>
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
								<div class="row-fluid"></div>
							</div>
						</div>
					</div>
					<div class="tab-pane" id="panel-134890">
						<form class="form-horizontal" action="saveSelectQuestion"
							method="post">
							<div class="control-group">
								<label class="control-label" for="inputEmail">题目</label>
								<div class="controls">
									<input name="questionTitle" type="text" id="inputEmail"
										placeholder="题目" style="height: 30px;">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputPassword">A</label>
								<div class="controls">
									<input name="a" type="text" id="inputEmail" placeholder="A"
										style="height: 30px;">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputPassword">B</label>
								<div class="controls">
									<input name="b" type="text" id="inputEmail" placeholder="B"
										style="height: 30px;">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputPassword">C</label>
								<div class="controls">
									<input name="c" type="text" id="inputEmail" placeholder="C"
										style="height: 30px;">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputPassword">D</label>
								<div class="controls">
									<input name="d" type="text" id="inputEmail" placeholder="D"
										style="height: 30px;">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputPassword">答案</label>
								<div class="controls">
									<input name="questionAnswer" type="text" id="inputEmail"
										placeholder="答案" style="height: 30px;">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputPassword">知识点</label>
								<div class="controls">
									<input name="skillName" type="text" id="inputEmail"
										placeholder="答案" style="height: 30px;">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputPassword">难度</label>
								<div class="controls">
									<input name="questionDifficulty" type="text" id="inputEmail"
										placeholder="答案" style="height: 30px;">
								</div>
							</div>

							<div class="control-group">
								<div class="controls">
									<button type="submit" class="btn">提交</button>
								</div>
							</div>
						</form>
					</div>
					<div class="tab-pane" id="panel-134891">
						<form class="form-horizontal" action="saveBlankQuestion"
							method="post">
							<div class="control-group">
								<label class="control-label" for="inputEmail">题目</label>
								<div class="controls">
									<input name="questionTitle" type="text" id="inputEmail"
										placeholder="题目" style="height: 30px;">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputPassword">答案</label>
								<div class="controls">
									<input name="questionAnswer" type="text" id="inputEmail"
										placeholder="答案" style="height: 30px;">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="inputPassword">知识点</label>
								<div class="controls">
									<input name="skillName" type="text" id="inputEmail"
										placeholder="知识点" style="height: 30px;">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputPassword">难度</label>
								<div class="controls">
									<input name="questionDifficulty" type="text" id="inputEmail"
										placeholder="难度" style="height: 30px;">
								</div>
							</div>

							<div class="control-group">
								<div class="controls">
									<button type="submit" class="btn">提交</button>
								</div>
							</div>
						</form>
					</div>


					<div class="tab-pane" id="panel-134892">
						<form class="form-horizontal" action="saveCodeQuestion"
							method="post">
							<div class="control-group">
								<label class="control-label" for="inputEmail">题目</label>
								<div class="controls">
									<input name="questionTitle" type="text" id="inputEmail"
										placeholder="题目" style="height: 30px;">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="inputEmail">内容</label>
								<div class="controls">
									<input name="questionContent" type="text" id="inputEmail"
										placeholder="内容" style="height: 30px;">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputPassword">答案</label>
								<div class="controls">
									<input name="questionAnswer" type="text" id="inputEmail"
										placeholder="答案" style="height: 30px;">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="inputPassword">知识点</label>
								<div class="controls">
									<input name="skillName" type="text" id="inputEmail"
										placeholder="知识点" style="height: 30px;">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputPassword">难度</label>
								<div class="controls">
									<input name="questionDifficulty" type="text" id="inputEmail"
										placeholder="难度" style="height: 30px;">
								</div>
							</div>

							<div class="control-group">
								<div class="controls">
									<button type="submit" class="btn">提交</button>
								</div>
							</div>
						</form>
					</div>
					<div class="tab-pane" id="panel-134893">
						<div class="container-fluid">
							<div class="row-fluid">
								<div class="span12">
									<form action="uploadQuestion"
										method="post" enctype="multipart/form-data">
										选择文件:<input type="file" name="file" width="120px">
										<div class="control-group">
											<div class="controls">
												<button type="submit" class="btn">提交</button>
											</div>
										</div>
									</form>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="../common/footer.jsp"%>