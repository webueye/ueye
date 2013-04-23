<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>角色编辑</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
				<div class="search">
					<div style="margin-left: 10px;margin-bottom: 10px;font: bold;">
					     <b>角色修改</b>
					</div>
				</div>
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/role/update/${role.id}">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth">角色名称：</th>
								<td class="ltd">
									<input  name="id" value="${role.id}" type="hidden"/>
									<input type="hidden" name="company.id" value="${currentAccount.company.id}"/>
									<input class="input-xlarge required" name="roleName" value="${role.roleName}" type="text"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">角色描述：</th>
								<td class="ltd">
									<input class="input-xlarge" name="description" value="${role.description}" type="text"/>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<div align="center">
										<button type="submit" class="btn btn-primary">修 改</button>
										<button type="button" class="btn btn-primary historyBackClass">返回</button>
									</div>
								</td>
							</tr>							
						</tbody>
					</table>
				</form>	
						
			</div>
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>