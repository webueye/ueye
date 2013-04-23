<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>密码修改</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
				
				<div style="margin: 10px;" align="center">
					<b><font color="red">${msg}</font></b>
				</div>

					<div class="title" >
					     <b>修改密码</b>
					</div>
			
				<form id="validatePasswordForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/user/modify">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth" style="width: 20%;">原密码：</th>
								<td class="ltd" style="width: 80%;">
									<input class="input-xlarge required {rangelength:[3,12]}" id="oldPassword" name="oldPassword" type="password"  size="12"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">新密码：</th>
								<td class="ltd">
									<input class="input-xlarge required {rangelength:[3,12]}" id="newPassword" name="newPassword" type="password"  size="12"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">新密码确认：</th>
								<td class="ltd">
									<input class="input-xlarge required {rangelength:[3,12]}" id="confirmPassword" name="confirmPassword" type="password"  size="12"/>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center">
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
			$(document).ready(function() {
				$("#validatePasswordForm").validate({
					rules: {
						oldPassword: "required",
						newPassword: {
							required: true,
						},
						confirmPassword: {
							required: true,
							equalTo: "#newPassword"
						},
					},
					messages: {
						oldPassword: "请输入原始密码",
						newPassword: {
							newPassword: "请输入新密码",
						},
						confirmPassword: {
							required: "请输入确认密码",
							equalTo: "确认密码与新密码不同,请重新输入"
						},
					}
				});
			});
		</script>
		
	</body>

</html>