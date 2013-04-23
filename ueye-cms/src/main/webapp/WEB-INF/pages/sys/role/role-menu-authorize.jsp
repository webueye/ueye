<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye" %>
<html>
	<head>
		<title>菜单授权</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
		
		<div class="container">
			<div class="">
					
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/role/authorize">	
					<input type="hidden" name="id" value="${role.id}"/>
					<table class="table table-bordered table-striped">
						
						<tbody>
							<tr >
								<th class="th">菜单名称</th>
							</tr>
							<u:forEach var="menu" items="roleMenus">
								<tr class="th">
									<td align="left" height="25">	
										<table>
											<tr>
												<c:forEach var="row" items="${menu.rows}">
								 					<td width="18px"></td>
								 				</c:forEach>
												<td align="right">
													<c:if test="${!menu.leaf}">
														<c:if test="${menu.expanded}">
															<input style="width: 18px;" type="checkbox" ${menu.checked} id="${menu.parent.id}_${menu.id}" value="${menu.id}" name="menus" onclick="menu.cascadeCheck(true, this, true);"/>
															<img border="0" alt="折 叠" src="${pageContext.request.contextPath}/img/minus.jpg">
														</c:if>
														<c:if test="${!menu.expanded}">
															<input style="width: 18px;" type="checkbox" ${menu.checked} id="${menu.parent.id}_${menu.id}" value="${menu.id}" name="menus" onclick="menu.cascadeCheck(true, this, true);"/>
															<img border="0" alt="展 开" src="${pageContext.request.contextPath}/img/plus.jpg">
														</c:if>
													</c:if>
													<c:if test="${menu.leaf}">
														<input style="width: 18px;" type="checkbox" ${menu.checked} id="${menu.parent.id}_${menu.id}" value="${menu.id}" name="menus" onclick="menu.cascadeCheck(true, this, true);"/>
														<img border='0' title="叶子节点" src="${pageContext.request.contextPath}/img/blank.gif"/>
													</c:if>
												</td>
												<td colspan="${10-menu.level}" style="font-size:10pt;" bgcolor="#EFF3FF" align="left">					
													&nbsp;${menu.label}
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</u:forEach>
							<tr class="th">
								<td>
									<button type="submit" class="btn btn-primary">授权</button>
									<button type="button" class="btn btn-primary historyBackClass">返回</button>
								</td>
							</tr>
						</tbody>
					</table>
				</form>	
						
			</div>
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>	
	
</html>
