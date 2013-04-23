<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<title>角色添加</title>
<jsp:include page="/common/header.jsp" />
</head>

<body>

	<div class="container">
		<div class="row">
			<div class="search">
				<div style="margin-left: 10px; margin-bottom: 10px; font: bold;">
					<b>角色添加</b>
				</div>
			</div>
			<form id="validateForm" class="form-horizontal" method="post" action="${ctx}/role">
				<table class="table table-bordered table-striped">
					<tbody>
						<tr>
							<td>角色名称：</td>
							<td>
								<input class="input-meduim required" name="roleName" type="text" />
							</td>
						</tr>
						<tr>
							<td>角色描述：</td>
							<td>
								<textarea name="description" rows="3" class="input-xlarge"></textarea>
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

	<jsp:include page="/common/footer.jsp" />

</body>

</html>