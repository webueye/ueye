<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${page.totalCount == 0}">
	<tr>
		<td colspan="${param.colspan}"><div align="center">暂无数据……</div></td>
	</tr>
</c:if> 