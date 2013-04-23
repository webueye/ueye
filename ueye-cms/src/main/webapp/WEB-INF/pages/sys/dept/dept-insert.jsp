<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>部门添加</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
				<div class="search">
					<div style="margin-left: 10px;margin-bottom: 10px;font: bold;">
					     <b>部门添加</b>
					</div>
				</div>
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/dept">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth">部门编号：</th>
								<td class="ltd">
									<input class="input-xlarge required number" name="deptNo" type="text" />
									<input type="hidden" name="company.id" value="${currentAccount.company.id}"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">部门名称：</th>
								<td class="ltd">
									<input class="input-xlarge required" name="name" type="text"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">上级部门：</th>
								<td class="ltd">
									<select id="parentDept" name="parentId" class="input-xlarge">
										<option value="">--请选择--</option>
									</select>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">电话：</th>
								<td class="ltd">
									<input class="input-xlarge" name="telNo" type="text"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">传真：</th>
								<td class="ltd">
									<input class="input-xlarge" name="faxNo" type="text"/>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<div align="center">
										<button type="submit" class="btn btn-primary">添加</button>
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
		
		<script type="text/javascript">
			getDepts('${dept.parent.id}');
			
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
								$("#parentDept").append(op);
							}
						}
					}
				});
			}
		</script>
		
	</body>

</html>