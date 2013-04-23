<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<title>修改</title>
<jsp:include page="/common/header.jsp" />
</head>

<body>

	<div class="container">
		<div class="row">

			<div style="margin: 10px;" align="center">
				<b><font color="red">${msg}</font></b>
			</div>

			<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/account/update/${account.id}">
				<table class="table table-bordered table-striped">
					<tbody>
						<tr>
							<td>用户名：</td>
							<td>
								<input name="id" value="${account.id}" type="hidden" />
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
								<input class="input-medium required" name="password" type="password" placeholder="不修改请留空" />
							</td>
							<td>确认密码：</td>
							<td>
								<input class="input-medium required" name="password" type="password" placeholder="不修改请留空" />
							</td>
						</tr>
						<tr>
							<td>禁用：</td>
							<td colspan="3">
								<input class="input-medium required" name="status" value="true" type="radio" ${account.status? 'checked': ''} />
								启用
								<input class="input-medium required" name="status" value="false" type="radio" style="margin-left: 20px;" ${account.status == false? 'checked': ''} />
								禁用
							</td>
						</tr>
						<tr>
							<td>真实姓名：</td>
							<td>
								<input class="input-medium" name="realname" value="${account.realname}" type="text" />
							</td>
							<td>性别：</td>
							<td>
								<input class="input-medium" name="sex" value="true" type="radio" ${account.sex? 'checked': ''} />
								男
								<input class="input-medium" name="sex" value="false" type="radio" style="margin-left: 20px;" ${account.sex == false?'checked':''} />
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

</body>

</html>