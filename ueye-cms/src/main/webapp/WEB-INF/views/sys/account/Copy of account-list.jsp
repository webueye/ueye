<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>帐号管理</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
		
		<div class="container">
			<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/account/search">
				<div class="search">
					<div class="row">
						<div class="span12">
							<div class="control-group">
					           	<span style="margin-left: 10px;">昵称 </span>
					            <input class="input-small" name="filter_LIKES_nickname" value="${filter_LIKES_nickname}" placeholder="昵称"/>
					           	
							  	<button type="submit" class="btn btn-primary">查询</button>
					            <input type="hidden" name="filter_EQL_companyId" value="${company.id}"/>
					        </div>
						</div>
					  </div>
				</div>
				<div>
					<table class="table table-bordered table-striped">
						<tbody>
							<tr>
								<th  class="th" colspan="8" align="right">
									<div align=right style="margin-right:10px;">
										<a href="${pageContext.request.contextPath}/account/edit-new/${company.id}">
											帐号添加
										</a>
									</div>
								</th>
							</tr>
							<tr>
								<th>帐号编号</th>
								<th>昵称</th>
								<th>登陆名</th>
								<th>邮箱</th>
								<th>帐号类型</th>
								<th>帐号状态</th>
								<th>角色分配</th>
								<th>编辑</th>
							</tr>
							<c:forEach var="account" items="${page.datas}">
								<tr>
									<td>${account.id}</td>
									<td>${account.nickname}</td>
									<td>${account.username}</td>
									<td>${account.email}</td>
									<td>${account.admin? '管理员': '普通帐号'}</td>
									<td>${account.status? '启用': '冻结'}</td>
									<td>
										<a href="${pageContext.request.contextPath}/account/role/${account.id}">角色分配</a>
									</td>
									<td>
										<a href="${pageContext.request.contextPath}/account/show/${account.id}">详情</a>
										<a href="${pageContext.request.contextPath}/account/edit/${account.id}">修改</a>
										<a href="${pageContext.request.contextPath}/account/destroy/${account.id}">删除</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<jsp:include page="/common/page.jsp">
					<jsp:param name="actionURL" value="account"/>
				</jsp:include>
			</form>	
					
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
		<script type="text/javascript">
			$(document).ready(function() {
				
			});
		</script>
		
	</body>

</html>