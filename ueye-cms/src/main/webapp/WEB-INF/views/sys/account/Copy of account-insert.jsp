<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>用户添加</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
				
				<div style="margin: 10px;" align="center">
					<b><font color="red">${msg}</font></b>
				</div>
				
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/account">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr>
								<th>所属公司：</th>
								<th colspan="3">${company.companyName}
									<input name = "companyId" value="${company.id}" type="hidden"/>
								</th>
							</tr>
							<tr>
								<td>用户编号：</td>
								<td>
									<input class="input-xlarge" name="userNo" value="${account.userNo}" type="text" style="width: 80%"/>
								</td>
								<td>用户昵称：</td>
								<td>
									<input class="input-xlarge" name="nickname" value="${account.nickname}" type="text" style="width: 80%"/>
								</td>
							</tr>
							<tr>
								<td>登陆名称：</td>
								<td>
									<input class="input-xlarge required" name="username" value="${account.username}" type="text" style="width: 80%"/>
								</td>
								<td>登陆密码：</td>
								<td>
									<input class="input-xlarge required" name="password" type="password" style="width: 80%"/>
								</td>
							</tr>
							<tr>
								<td>邮箱：</td>
								<td>
									<input class="input-xlarge required email" name="email" value="${account.email}" type="text" style="width: 80%"/>
								</td>
								<td>性别：</td>
								<td>
									男<input class="input-xlarge" name="sex" style="margin-right: 20px;" value="true" type="radio" ${account.sex? 'checked': ''}/>
									女<input class="input-xlarge" name="sex" value="false" type="radio" ${account.sex? '': 'checked'}/>
								</td>
							</tr>
							<tr>
								<td>电话：</td>
								<td>
									<input class="input-xlarge" name="phone" value="${account.phone}" type="text" style="width: 80%"/>
								</td>
								<td>手机：</td>
								<td>
									<input class="input-xlarge" name="mobile" value="${account.mobile}" type="text" style="width: 80%"/>
								</td>
							</tr>
							<tr>
								<td>用户类型：</td>
								<td>
									管理员<input class="input-xlarge required" name="admin" style="margin-right: 20px;" value="true" type="radio" ${account.admin? 'checked': ''}/>
									普通用户<input class="input-xlarge required" name="admin" value="false" type="radio"  ${account.admin? '': 'checked'}/>
								</td>
								<td>用户状态：</td>
								<td>
									启用<input class="input-xlarge required" name="status" style="margin-right: 20px;" value="true" type="radio" ${account.status or account.status == null? 'checked': ''}/>
									禁用<input class="input-xlarge required" name="status" value="false" type="radio" ${account.status or account.status == null? '': 'checked'}/>
								</td>
							</tr>
							<tr>
								<td>所属部门:</td>
								<td colspan="1">
									<select id="deptId" name="deptId" class="input-xlarge">
										<option value="">--请选择--</option>
									</select>
								</td>
								<td colspan="2"></td>
							</tr>
							<tr>
								<td colspan="4" align="center">
									<div align="center">
										<button type="submit" class="btn btn-primary">提&nbsp;&nbsp;&nbsp;交</button>
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
			getDepts();
			
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