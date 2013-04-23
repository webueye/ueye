<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>角色授权</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/account/authorize">
				<input type="hidden" name="id" value="${account.id}"/>
				<div>
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th class="gray" colspan="3">角色列表</th>
							</tr>
							<tr>
								<th>
									<input type="checkbox" onclick="common.reverseSelect(this, '.checkboxClass');"/>
								</th>
								<th>角色名称</th>
								<th>角色描述</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="role" items="${roles}">
								<tr>
									<td style="padding-left: 18px;">
										<input class="checkboxClass" type="checkbox" name="roleId" ${role.checked} value="${role.id}"/>
									</td>
									<td>${role.roleName}</td>
									<td>${role.description}</td>
								</tr>
							</c:forEach>
							
							<tr>
								<td colspan="3">
									<button type="submit" class="btn btn-primary">授权</button>
									<button type="button" class="btn btn-primary historyBackClass">返回</button>
								</td>
							</tr>
						</tbody>
					</table>
						
				</div>
			</form>
		
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>