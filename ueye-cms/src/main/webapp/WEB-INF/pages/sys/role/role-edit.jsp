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
			<div class="search">
				<div style="margin-left: 10px; margin-bottom: 10px; font: bold;">
					<b>修改</b>
				</div>
			</div>
			<form id="validateForm" class="form-horizontal" method="post" action="${ctx}/role/update/${role.id}">
				<table class="table table-bordered table-striped">
					<tbody>
						<tr>
							<td>角色名称：</td>
							<td>
								<input name="id" value="${role.id}" type="hidden" />
								<input class="input-meduim required" name="roleName" type="text" value="${role.roleName}" />
							</td>
						</tr>
						<tr>
							<td>角色描述：</td>
							<td>
								<textarea name="description" rows="3" cols="" class="input-meduim">${role.description}</textarea>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<div align="center">
									<button type="submit" class="btn btn-primary">修 改</button>
										<button type="button" class="btn btn-primary historyBackClass">返回</button>
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