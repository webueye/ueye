<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>Header</title>
<link href="${ctx}/css/bootstrap.css" rel="stylesheet" />
<link href="${ctx}/css/bootstrap-responsive.css" rel="stylesheet" />
</head>

<body>

	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner" style="min-height: 85px;">
			<div class="container-fluid">

				<a class="brand" href="#">cms</a>
				<div class="btn-group pull-right">
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
						<i class="icon-user"></i> ${currentAccount.username} <span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="${ctx}/login/logout">退出</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<script src="${ctx}/js/jquery.js"></script>
	<script src="${ctx}/js/bootstrap-dropdown.js"></script>

</body>

</html>