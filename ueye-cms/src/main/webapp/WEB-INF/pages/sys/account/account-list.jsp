<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>员工帐号列表</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="title" >
			     <b>员工帐号列表</b>
			</div>
			
			<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/account/search">
				<div class="search">
					<div class="row">
						<div class="span12">
							<div class="control-group">
					           	<span style="margin-left: 10px;">工号 </span>
					            <input class="input-large" name="filter_LIKES_userNo" value="${filter_LIKES_userNo}" style="width: 100px;" placeholder="工号"/>
					           	<span style="margin-left: 10px;">员工姓名 </span>
					            <input class="input-large" name="filter_LIKES_nickname" value="${filter_LIKES_nickname}" style="width: 100px;" placeholder="员工姓名"/>
					           	<span style="margin-left: 10px;">性别 </span>
					            <select name="filter_EQB_sex" class="input-small">
					           		<option value="">请选择</option>
					           		<option value="1" ${filter_EQB_sex == '1'? 'selected': ''}>男</option>
					           		<option value="0"  ${filter_EQB_sex == '0'? 'selected': ''}>女</option>
					           	</select>
					           	<span style="margin-left: 10px;">部门 </span>
							  	<select id="dept" name="filter_EQL_dept.id" class="input-small">
									<option value="">--请选择--</option>
								</select>
							  	
							  	
							  	<button type="submit" class="btn btn-primary">查询</button>
					        </div>
						</div>
					</div>
				</div>
			</form>				
						
			<div >
					<table class="table table-bordered table-striped">
						<tbody>
							<tr>
								<th  class="th" colspan="8" align="right">
									<div align=right style="margin-right:10px;">
										<a href="${pageContext.request.contextPath}/account/edit-new/${company.id}">
											新增员工
										</a>
									</div>
								</th>
							</tr>
							<tr class="gray">
								<th style="width: 10%;">工号</th>
								<th style="width: 10%;">昵称</th>
								<th style="width: 10%;">登陆名</th>
								<th style="width: 10%;">性别</th>
								<th style="width: 15%;">电话</th>
								<th style="width: 15%;">所属部门</th>
								<th style="width: 10%;">帐号状态</th>
								<th style="width: 20%;">操作</th>
							</tr>
							<c:forEach var="account" items="${page.datas}">
								<tr>
									<td>${account.userNo}</td>
									<td>${account.nickname}</td>
									<td>${account.username}</td>
									<td>${account.sex? '男': '女'}</td>
									<td>${account.mobile}<br>${account.phone}</td>
									<td>${account.dept.name}</td>
									<td>
										<a onclick="return handleState('${pageContext.request.contextPath}/account/state/${account.id}');" href="#">${account.status? '已启用': '已冻结'}</a>
									</td>
									<td>
										<a href="${pageContext.request.contextPath}/account/role/${account.id}">角色分配</a>
										<a href="${pageContext.request.contextPath}/account/show/${account.id}">详情</a>
										<a href="${pageContext.request.contextPath}/account/edit/${account.id}">修改</a><br/>
										<a href="${pageContext.request.contextPath}/account/destroy/${account.id}" style="display: none;">冻结激活（待做）</a>
									</td>
								</tr>
							</c:forEach>
							
							<jsp:include page="/common/no-data.jsp">
								<jsp:param value="10" name="colspan"/>
							</jsp:include>
							
						</tbody>
					</table>
					
					<jsp:include page="/common/page.jsp">
						<jsp:param name="actionURL" value="account"/>
					</jsp:include>
					
					
			</div>
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
		<script type="text/javascript">
			var stateUrl;
			function handleState(url){
				$("#messageAlert").html("是否更改状态?");
				$("#modalDiv").show();
				$("#modalDiv").modal({
					backdrop:true
				});
				stateUrl = url;
				return false;
			}
			
			function confirmMessage(){
				window.location.href = stateUrl;
			}
			
			getDepts('${filter_EQL_dept_id}');
			
			function getDepts(defaultSelect) {
				$.ajax({
					type : 'post',
					url : '${pageContext.request.contextPath}/dept/nodes',
					dataType : 'json',
					success : function(data) {
						if (data) {
							for ( var i = 0; i < data.length; i++) {
								var op = new Option(data[i].name, data[i].id);
								op.innerHTML = data[i].name;
								if(defaultSelect == data[i].id){
									op.selected = true;
								}
								$("#dept").append(op);
							}
						}
					}
				});
			}
			
		</script>
		
	</body>

</html>