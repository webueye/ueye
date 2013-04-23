<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>员工信息</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
					
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/account/update/${account.id}">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th>所属公司：</th>
								<th colspan="3">${currentAccount.company.companyName}
									<input name="id" value="${account.id}" type="hidden"/>
								</th>
							</tr>
							<tr class="th">
								<td class="gray" style="width: 20%;">工号：</td>
								<td style="width: 30%;">${account.userNo}</td>
								<td class="gray" style="width: 20%;">所属部门：</td>
								<td style="width: 30%;">${account.dept.name}</td>
							</tr>
							<tr class="th">
								<td class="gray">姓名：</td>
								<td >${account.username}</td>
								<td class="gray">昵称：</td>
								<td>${account.nickname}</td>								
							</tr>
							<tr class="th">
								<td class="gray">邮箱：</td>
								<td>${account.email}</td>
								<td class="gray">性别：</td>
								<td>${account.sex? '男': '女'}</td>
							</tr>
							<tr class="th">
								<td class="gray">电话：</td>
								<td>${account.phone}</td>
								<td class="gray">手机：</td>
								<td>${account.mobile}</td>
							</tr>
							<tr class="th">
								<td class="gray">用户类型：</td>
								<td>${account.admin? '管理员': '普通用户'}</td>
								<td class="gray">用户状态：</td>
								<td>${account.status? '启用': '禁用'}</td>
							</tr>
							<tr>
								<td colspan="4" align="center">
									<div align="center">
										<button type="button" class="btn btn-primary historyBackClass">返&nbsp;&nbsp;&nbsp;回</button>
									</div>
								</td>
							</tr>							
						</tbody>
					</table>
				</form>	
						
			</div>
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
		<script type="text/javascript">
			getDepts("${account.dept.id}");
			
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
								$("#deptId").append(op);
							}
						}
					}
				});
			}
		</script>
		
	</body>

</html>