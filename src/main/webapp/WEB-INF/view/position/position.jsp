<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/navbar.jsp"%>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="tabbable" id="tabs-839486">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#panel-81121" data-toggle="tab">职位需求列表</a>
					</li>
					<li><a href="#panel-214843" data-toggle="tab">新增职位</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="panel-81121">
						<!-- Modal -->
						<div id="myModal" class="modal hide fade" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">×</button>
								<h3 id="myModalLabel">分析结果</h3>
							</div>
							<div class="modal-body">
								<p id="positionAnazyResult">One fine body…</p>
							</div>
							<div class="modal-footer">
								<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
								<button class="btn btn-primary">Save changes</button>
							</div>
						</div>
						
						
						<!-- Modal -->
						<div id="myModal2" class="modal hide fade" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">×</button>
								<h3 id="myModalLabel">简历与招聘需求分析</h3>
							</div>
							<form id= "uploadForm">  
      							<p >上传文件： <input type="file" name="file"/></p>  
      							<input type="button" value="上传" onclick="doUpload()" />  
							</form> 
							<h5>简历分析结果</h5>
							<div class="modal-body">
								<p id="resumeAnazyResult"></p>
							</div>
							<h5>招聘分析结果</h5>
							<div class="modal-body">
								<p id="positionResult2"></p>
							</div>
							<h5>简历与招聘需求分析结果</h5>
							<div class="modal-body">
								<p id="resumeAndpositionAnazyResult"></p>
							</div>
							<div class="modal-footer">
								<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
								<button class="btn btn-primary" onclick=anazyResumeAndposition()>分析匹配度</button>
							</div>
						</div>
						
						<c:forEach items="${positionList}" var="position"
							varStatus="status">
							<div class="container-fluid">
								<div class="row-fluid">
									<div class="span12">
										<div class="row-fluid">
											<div class="span2"></div>
											<div class="span6">
												<dl>
													<dt>职位名称</dt>
													<dd>${position.positionName}</dd>
													<dt>职位描述</dt>
													<dd>${position.positionDesc}</dd>
													<dt>职位职责</dt>
													<dd>${position.positionRequest}</dd>
												</dl>
											</div>
											<div class="span4"></div>
										</div>
										<div class="row-fluid">
											<div class="span4"></div>
											<div class="span4">
												<div class="btn-group">
													<button class="btn" type="button" onclick=anazyPosition(${position.positionId})>分析需求</button>
													<button class="btn" type="button" onclick=anazyResume(${position.positionId})>分析匹配度</button>
												</div>
											</div>
											<div class="span4"></div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<div class="tab-pane" id="panel-214843">
						<p>
						<div class="container-fluid">
							<div class="row-fluid">
								<div class="span12">
									<form class="form-horizontal" action="savePosition"
										method="post">
										<div class="control-group">
											<label class="control-label" for="inputEmail">职位名称</label>
											<div class="controls">
												<input name="positionName" type="text" id="inputEmail"
													placeholder="职位名称" style="height: 30px;">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="inputPassword">职位描述</label>
											<div class="controls">
												<input name="positionDesc" type="text" id="inputEmail"
													placeholder="职位描述" style="height: 30px;">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="inputPassword">职位要求</label>
											<div class="controls">
												<input name="positionRequest" type="text" id="inputEmail"
													placeholder="职位要求" style="height: 30px;">
											</div>
										</div>
										<div class="control-group">
											<div class="controls">
												<button type="submit" class="btn">提交</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script language=javascript>
function anazyPosition(positionId){
	var position = {
			positionId : positionId
		};
	
	$.ajax({
		url : "http://localhost:8080/SearchSystem/position/anazyPosition1",
		type : "get",
		data : position,
		success : function(data) {
		document.getElementById("positionAnazyResult").innerHTML=data;
		$('#myModal').modal('show');
		}
	});
}

function anazyResume(positionId){
	var position = {
			positionId : positionId
		};
	
	$.ajax({
		url : "http://localhost:8080/SearchSystem/position/anazyPosition1",
		type : "get",
		data : position,
		success : function(data) {
		document.getElementById("positionAnazyResult").innerHTML=data;
			$('#myModal2').modal('show');
			document.getElementById("positionResult2").innerHTML=data;
		}
	});
	
	
	
	
}

function anazyResumeAndposition(){
	var resum=document.getElementById("resumeAnazyResult").innerHTML;
	var position=document.getElementById("positionResult2").innerHTML;
	var positionResum={
			position:position,
			resume:resum
	}
	$.ajax({
		url : "http://localhost:8080/SearchSystem/position/anazyPositionAndResum",
		type : "get",
		data : positionResum,
		success : function(data) {
			document.getElementById("resumeAndpositionAnazyResult").innerHTML=data;
		}
	});
}

function doUpload() {  
    var formData = new FormData($( "#uploadForm" )[0]);  
    $.ajax({  
        url: 'http://localhost:8080/SearchSystem/position/uploadResume',  
        type: 'POST',  
        data: formData,
        async: false,  
        cache: false,  
        contentType: false,  
        processData: false,  
        success: function (returndata) {  
        	document.getElementById("resumeAnazyResult").innerHTML=returndata;
        },  
        error: function (returndata) {  
           
        }  
   });
}  

</script>
<%@include file="../common/footer.jsp"%>