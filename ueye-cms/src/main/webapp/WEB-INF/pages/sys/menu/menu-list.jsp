<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ueye" uri="ueye" %>

<!DOCTYPE html>
<html>

	<head>
		<title>Menu</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
					
				<form class="form-horizontal" method="post" action="${pageContext.request.contextPath }/menu">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr>
								<th  class="th" colspan="6" align="right">
									<div align=right style="margin-right:10px;">
										<a href="${pageContext.request.contextPath}/menu/edit-new">
											菜单添加
										</a>
									</div>
								</th>
							</tr>
							<tr >
								<th class="th">菜单名称</th>
								<th class="th">排序</th>
								<th class="th">菜单角色</th>
								<th class="th">添加子节点</th>
								<th class="th">修 改</th>
								<th class="th">删 除</th>
							</tr>
							<ueye:forEach var="menu" items="menus">
							<tr>
								<td align="left" height="25">
									<table>
										<tr>							
											<c:forEach var="row" items="${menu.rows}">
							 					<td width="18px"></td>
							 				</c:forEach>
											<td>
																
											<c:if test="${!menu.leaf}">						
													<c:if test="${menu.expanded}">
														<a href="${pageContext.request.contextPath}/menu/${menu.id }/expand">
															<img border='0' width="16" height="13" title="折 叠" src="${pageContext.request.contextPath}/img/folder_open.gif"/>
														</a>
													</c:if>
													<c:if test="${!menu.expanded}">
														<a href="${pageContext.request.contextPath}/menu/${menu.id }/expand">
															<img border='0' width="16" height="13" title="展 开" src="${pageContext.request.contextPath}/img/folder.gif"/>
														</a>
													</c:if>
											</c:if>
									
											<c:if test="${menu.leaf}">
												<img border='0' title="叶子节点" src="${pageContext.request.contextPath}/img/blank.gif"/>
											</c:if>
									
											</td>
											<td colspan="${10-menu.level}" style="font-size:10pt;" title="链接地址: ${menu.action }">					
												${menu.label}
											</td>
										</tr>
									</table>
								</td>
								<td class="td">
									${menu.orderValue}
								</td>								
								<td class="td">
									${menu.roleType? '工厂': ''}
									${menu.roleType? '': '经销商'}
								</td>								
								<td> 
									<c:if test="${menu.leaf}">
										<img style='filter:gray;' border='0' title='此节点为叶子节点' src='${pageContext.request.contextPath}/img/insert.gif'/>
									</c:if>
									<c:if test="${!menu.leaf}">
										<a href='${pageContext.request.contextPath}/menu/new/${menu.id}'>
											<img border='0' title='添 加' src='${pageContext.request.contextPath}/img/insert.gif'/>
										</a>
									</c:if>
								</td>
								<td class="td">
									<a href='${pageContext.request.contextPath}/menu/${menu.id}'>
										<img border='0' title='修 改' src='${pageContext.request.contextPath}/img/edit.gif'/>
									</a>					
								</td>
								<td class="td">
									<a href='${pageContext.request.contextPath}/menu/destroy/${menu.id}'>
										<img border='0' title='删 除' src='${pageContext.request.contextPath}/img/delete.gif'/>
									</a>
								</td>				
							</tr>	
							</ueye:forEach>
													
						</tbody>
					</table>
				</form>	
						
			</div>
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
		<script type="text/javascript">
			function rowEnable(vl, compName){
				if('true' == vl){
					$("#"+ compName).hide();
				}
				else if('false' == vl){
					$("#"+compName).show();
				}
			}
		</script>
		
	</body>

</html>