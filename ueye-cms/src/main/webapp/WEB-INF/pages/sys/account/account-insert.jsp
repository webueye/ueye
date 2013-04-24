<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<title>用户添加</title>
<jsp:include page="/common/header.jsp" />
</head>

<body>

	<div class="container">
		<div class="row">

			<div style="margin: 10px;" align="center">
				<b><font color="red">${msg}</font></b>
			</div>

			<form id=validatePasswordForm class="form-horizontal" method="post" action="${ctx}/account">
				<table class="table table-bordered table-striped">
					<tbody>
						<tr>
							<td>用户名：</td>
							<td>
								<input class="input-medium required" name="username" value="${account.username}" type="text" />
							</td>
							<td>电子邮箱：</td>
							<td>
								<input class="input-medium required email" name="email" value="${account.email}" type="text" />
							</td>
						</tr>
						<tr>
							<td>登陆密码：</td>
							<td>
								<input class="input-medium required" id="password" name="password" type="password" />
							</td>
							<td>确认密码：</td>
							<td>
								<input class="input-medium required" id="confirmPassword" name="confirmPassword" type="password" />
							</td>
						</tr>
						<tr>
							<td>真实姓名：</td>
							<td>
								<input class="input-medium" name="realname" value="${account.realname}" type="text" />
							</td>
							<td>性别：</td>
							<td>
								<input class="input-medium" name="sex" value="true" type="radio" />
								男
								<input class="input-medium" name="sex" value="false" type="radio" style="margin-left: 20px;" />
								女
							</td>
						</tr>
						<tr>
							<td>电话：</td>
							<td>
								<input class="input-medium" name="phone" value="${account.phone}" type="text" />
							</td>
							<td>手机：</td>
							<td>
								<input class="input-medium" name="mobile" value="${account.mobile}" type="text" />
							</td>
						</tr>
						<tr>
							<td>备注：</td>
							<td colspan="3">
								<textarea name="memo" rows="3" style="width: 80%;">${account.memo}</textarea>
								<input name="status" value="true" type="hidden" />
							</td>
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

	<jsp:include page="/common/footer.jsp" />

	<script type="text/javascript">
		$(document).ready(function() {
			$("#validatePasswordForm").validate({
				rules : {
					password : {
						required : true,
					},
					confirmPassword : {
						required : true,
						equalTo : "#password"
					},
				},
				messages : {
					password : {
						password : "请输入新密码",
					},
					confirmPassword : {
						required : "请输入确认密码",
						equalTo : "确认密码与新密码不同,请重新输入"
					},
				}
			});
		});
	</script>

</body>

</html>