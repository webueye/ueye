<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<title>用户列表</title>
<jsp:include page="/common/header.jsp" />
</head>

<body>

	<div class="container">
		<div class="title"></div>

		<form id="validateForm" class="form-horizontal" method="post" action="${ctx}/account/list">
			<div class="search">
				<div class="row">
					<div class="span12">
						<div class="control-group">
							<span style="margin-left: 10px;">用户名 </span>
							<input class="input-small" name="filter_LIKES_username" value="${filter_LIKES_username}" placeholder="用户名" />

							<button type="submit" class="btn btn-primary">查询</button>
						</div>
					</div>
				</div>
			</div>

			<div>
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th class="th" colspan="8" align="right">
								<div align=right style="margin-right: 10px;">
									<a href="${ctx}/account/edit-new/${company.id}"> 添加 </a>
								</div>
							</th>
						</tr>
						<tr>
							<th><input type="checkbox" /></th>
							<th>编号</th>
							<th>用户名</th>
							<th>电子邮箱</th>
							<th>帐号状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="account" items="${page.datas}">
							<tr>
								<td>
									<span style="padding-left: 10px;"><input type="checkbox" /></span>
								</td>
								<td>${account.id}</td>
								<td>${account.username}</td>
								<td>${account.email}</td>
								<td>
									<a onclick="return handleState('${ctx}/account/state/${account.id}');" href="#">${account.status? '已启用': '已冻结'}</a>
								</td>
								<td>
									<a href="${ctx}/account/role/${account.id}">角色分配</a>
									<a href="${ctx}/account/show/${account.id}">详情</a>
									<a href="${ctx}/account/edit/${account.id}">修改</a>
									<a href="${ctx}/account/destroy/${account.id}">删除</a>
								</td>
							</tr>
						</c:forEach>

						<jsp:include page="/common/no-data.jsp">
							<jsp:param value="10" name="colspan" />
						</jsp:include>

					</tbody>
				</table>

				<jsp:include page="/common/page.jsp">
					<jsp:param name="actionURL" value="account" />
				</jsp:include>


			</div>
		</form>
	</div>

	<jsp:include page="/common/footer.jsp" />

	<script type="text/javascript">
		var stateUrl;
		function handleState(url) {
			$("#messageAlert").html("是否更改状态?");
			$("#modalDiv").show();
			$("#modalDiv").modal({
				backdrop : true
			});
			stateUrl = url;
			return false;
		}

		function confirmMessage() {
			window.location.href = stateUrl;
		}
	</script>

</body>

</html>