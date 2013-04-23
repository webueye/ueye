<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<title>角色管理</title>
<jsp:include page="/common/header.jsp" />
</head>

<body>

	<div class="container">
		<form id="validateForm" class="form-horizontal" method="post" action="${ctx}/role/search">
			<div class="search">
				<div class="row">
					<div class="span12">
						<div class="control-group">
							<span style="margin-left: 10px;">角色名称 </span>
							<input class="input-small" name="filter_LIKES_roleName" value="${filter_LIKES_roleName}" placeholder="名称" />

							<button type="submit" class="btn btn-primary">查询</button>
						</div>
					</div>
				</div>
			</div>

			<div>
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th colspan="6">
								<div align=right style="margin-right: 10px;">
									<a href="${ctx}/role/edit-new/${company.id}"> 添加 </a>
								</div>
							</th>
						</tr>
						<tr>
							<th><input type="checkbox" /></th>
							<th>编号</th>
							<th>角色名称</th>
							<th>菜单授权</th>
							<th>角色描述</th>
							<th>编辑</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="role" items="${page.datas}">
							<tr>
								<td>
									<span style="padding-left: 10px;"><input type="checkbox" /></span>
								</td>
								<td>${role.id}</td>
								<td>${role.roleName}</td>
								<td>
									<a href="${ctx}/role/menu/${role.id}">菜单授权</a>
								</td>
								<td>${role.description}</td>
								<td>
									<a href="${ctx}/role/edit/${role.id}">修改</a>
									<a href="${ctx}/role/destroy/${role.id}">删除</a>
								</td>
							</tr>
						</c:forEach>

						<jsp:include page="/common/no-data.jsp">
							<jsp:param value="5" name="colspan" />
						</jsp:include>

					</tbody>
				</table>

				<jsp:include page="/common/page.jsp">
					<jsp:param name="actionURL" value="role" />
				</jsp:include>

			</div>
		</form>

	</div>

	<jsp:include page="/common/footer.jsp" />

</body>

</html>