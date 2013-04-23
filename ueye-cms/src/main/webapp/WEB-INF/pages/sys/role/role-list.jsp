<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>角色管理</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/role/search">
				<div class="search">
					<div class="row">
						<div class="span12">
							<div class="control-group">
					           	<span style="margin-left: 10px;">角色名称 </span>
					            <input class="input-small" name="filter_LIKES_roleName" value="${filter_LIKES_roleName}" placeholder="角色名称"/>
					           	
							  	<button type="submit" class="btn btn-primary">查询</button>
					            <input type="hidden" name="filter_EQL_company.id" value="${currentAccount.company.id}"/>
					        </div>
						</div>
					  </div>
				</div>
				
				<div>
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th colspan="5">
									<div align=right style="margin-right:10px;">
										<a href="${pageContext.request.contextPath}/role/edit-new/${company.id}">
											角色添加
										</a>
									</div>
								</th>
							</tr>
							<tr>
								<th class="gray" colspan="5">角色列表</th>
							</tr>
							<tr>
								<th>角色编号</th>
								<th>角色名称</th>
								<th>菜单授权</th>
								<th>角色描述</th>
								<th>编辑</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="role" items="${page.datas}">
								<tr>
									<td>${role.id}</td>
									<td>${role.roleName}</td>
									<td>
										<a href="${pageContext.request.contextPath}/role/menu/${role.id}">菜单授权</a>
									</td>
									<td>${role.description}</td>
									<td>
										<a href="${pageContext.request.contextPath}/role/edit/${role.id}">修改</a>
										<a href="${pageContext.request.contextPath}/role/destroy/${role.id}">删除</a>
									</td>
								</tr>
							</c:forEach>
							
							<jsp:include page="/common/no-data.jsp">
								<jsp:param value="5" name="colspan"/>
							</jsp:include>
							
						</tbody>
					</table>
					
					<jsp:include page="/common/page.jsp">
						<jsp:param name="actionURL" value="box-spec"/>
					</jsp:include>
						
				</div>
			</form>
		
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>