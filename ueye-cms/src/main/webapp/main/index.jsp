<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>cms</title>
</head>

<frameset rows="85,*" cols="*" frameborder="no" border="2" bordercolor="#999999" framespacing="0">
	<frame src="${ctx}/main/header.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
	<frameset rows="*" cols="164,*" framespacing="0" frameborder="no" border="0">
		<frame src="${ctx}/menu/left" bordercolor="#999999" name="leftFrame" scrolling="auto" noresize="noresize" id="leftFrame" title="菜单" />
		<frame src="${ctx}/main/main.jsp" name="mainFrame" id="mainFrame" title="mainFrame" />
	</frameset>
</frameset>

</html>
