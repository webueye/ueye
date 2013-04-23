<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye"%>

<!DOCTYPE html>
<html>

	<head>
		<title>部门管理</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
		
		<div class="container">
			
			<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/dept/search">
				<div class="search">
					<div class="row">
						<div class="span12">
							<div class="control-group">
					           	<span style="margin-left: 10px;">部门名称 </span>
					            <input class="input-small" name="filter_LIKES_name" value="${filter_LIKES_name}" placeholder="名称"/>
					           	
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
								<th colspan="8" align="right">
									<div align=right style="margin-right:10px;">
										<a href="${pageContext.request.contextPath}/dept/edit-new">
											部门添加
										</a>
									</div>
								</th>
							</tr>
							<tr>
								<th class="gray" colspan="8">部门列表</th>
							</tr>
							<tr>
								<th>编号</th>
								<th>部门编号</th>
								<th>部门名称</th>
								<th>上级部门</th>
								<th>电话</th>
								<th>传真</th>
								<th>操 作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="dept" items="${page.datas}">
								<tr>
									<td>${dept.id}</td>
									<td><u:valueFormat value="${dept.deptNo}"/></td>
									<td><u:valueFormat value="${dept.name}"/></td>
									<td><u:valueFormat value="${dept.parent.name}"/></td>
									<td><u:valueFormat value="${dept.telNo}"/></td>
									<td><u:valueFormat value="${dept.faxNo}"/></td>
									<td>
										<a href="${pageContext.request.contextPath}/dept/edit/${dept.id}">修改</a>
										<a href="${pageContext.request.contextPath}/dept/destroy/${dept.id}" class="deletePromptClass">删除</a>
									</td>
								</tr>
							</c:forEach>
							
							<jsp:include page="/common/no-data.jsp">
								<jsp:param value="8" name="colspan"/>
							</jsp:include>
							
						</tbody>
					</table>
					
					<jsp:include page="/common/page.jsp">
						<jsp:param name="actionURL" value="dept"/>
					</jsp:include>
						
				</div>
				
			</form>
				
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>