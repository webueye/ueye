<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye"%>

<!DOCTYPE html>
<html>

	<head>
		<title>角色分配</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
		
		<div class="container">
			
			<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/dept/role">
				
				<div>
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th class="gray" colspan="8">角色分配列表</th>
							</tr>
							<tr>
								<th><input class="checkedClass" type="checkbox" onclick="checkHandle(this);"/></th>
								<th>角色名称</th>
								<th>角色描述</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="role" items="${page.datas}">
								<tr>
									<td>${role.id}
										<input type="checkbox" value="${menu.id}" name="roles"/>
									</td>
									<td><u:valueFormat value="${role.roleName}"/></td>
									<td><u:valueFormat value="${role.description}"/></td>
								</tr>
							</c:forEach>
							
						</tbody>
					</table>
					
				</div>
				
			</form>
				
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
		<script type="text/javascript">
			function checkHandle(elem){
				if($(elem).attr("checked")){
					$(".checkedClass").each(function(i){
						$(this).attr("checked", "true");
					});
				}else{
					$(".checkedClass").each(function(i){
						$(this).removeAttr("checked");
					});
				}
			}
		</script>
		
	</body>

</html>