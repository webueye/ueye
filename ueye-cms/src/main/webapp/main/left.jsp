<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%@ taglib prefix="ueye" uri="ueye"%>

<html>
<head>
<title>模块列表</title>
<style type="text/css">
<!--
A:link {
	COLOR: #000000;
	TEXT-DECORATION: none;
}

A:visited {
	COLOR: #000000;
	TEXT-DECORATION: none;
}

A:hover {
	COLOR: #339900;
}

body {
	background-color: #DEE5FD;
	background-image: url(../img/left_bg.png);
	background-repeat: repeat-y;
	background-repeat: repeat-x;
}

.ltd {
	font-size: 10pt;
	color: #000000;
	text-align: left;
	LINE-HEIGHT: 22px;
	padding-left: 3;
	padding-top: 0;
	padding-right: 0;
	padding-bottom: 0;
}
-->
</style>
</head>

<body style="overflow-x: hidden">

	<table border="0" cellspacing="0" cellpadding="0" width="300">

		<ueye:forEach items="menus" var="menu" indexed="index">
			<tr valign="middle">

				<c:forEach var="row" items="${menu.rows}">
					<td width="16px">&nbsp;</td>
				</c:forEach>

				<td width="16px" style="width: 16px">
					<c:choose>
						<c:when test="${menu.leaf}">
							<img border="0" width="10" height="12" title="叶子节点" src="${ctx}/img/blank.gif" />
						</c:when>
						<c:when test="${!menu.expanded}">
							<a href="${ctx }/menu/${menu.id}/menu">
								<img width="16" height="13" border="0" title="展 开" src="${ctx}/img/folder.gif" />
							</a>
						</c:when>
						<c:when test="${menu.expanded}">
							<a href="${ctx }/menu/${menu.id}/menu">
								<img width="16" height="13" border="0" title="折 叠" src="${ctx}/img/folder_open.gif" />
							</a>
						</c:when>
					</c:choose>
				</td>

				<td colspan="${15-menu.level }" class="ltd" width="${300-menu.level*16 }">
					<c:if test="${menu.action==''}">
	 						${menu.label }
	 					</c:if>
					<c:if test="${menu.action!=''}">
						<a href="${ctx }/${menu.action}" target='mainFrame'> ${menu.label } </a>
					</c:if>
				</td>

			</tr>
		</ueye:forEach>

	</table>

</body>

</html>
