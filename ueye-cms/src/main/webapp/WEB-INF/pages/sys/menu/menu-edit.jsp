<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>Menu Manager</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
					
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/menu/update">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th >父菜单名称:</th>
								<td class="ltd">
									<select id="parentMenus" name="parentId">
										<option value="">--请选择--</option>
									</select>
								</td>
				    		</tr>
							<tr class="th">
								<th >菜单名称:</th>
								<td class="ltd">
									<input type="hidden" name="id" value="${menu.id }"/>
									<input class="input-xlarge focused required" name="label" type="text" value="${menu.label}"/>
								</td>
							</tr>
							<tr class="th">
								<th>菜单地址:</th>
								<td class="ltd">
									<input class="input-xlarge focused" name="action" type="text" value="${menu.action}"/>
								</td>
							</tr>
							<tr class="th">
								<th>菜单序号:</th>
								<td class="ltd">
									<input class="input-xlarge focused required number" name="orderValue" type="text" value="${menu.orderValue}"/>
								</td>
							</tr>
							<tr class="th">
								<th>叶子节点:</th>
								<td class="ltd">
									<select name="leaf" onChange="rowEnable(this.value, 'texp')" style="width:150px">
										<option value="true" ${menu.leaf? 'selected': ''}>叶子节点</option>
										<option value="false" ${menu.leaf? '': 'selected'}>非叶子节点</option>
									</select>
								</td>				
							</tr>
							<tr class="th" id="texp" style="display:${menu.leaf? 'none;': ''}">
								<th>是否展开:</th>
								<td class="ltd">
				                    <select name="expanded" title="默认情况下此节点是否展开" style="width:150px;z-index:1;">
										<option value="false" ${menu.expanded? '': 'selected'}>不展开</option>
										<option value="true" ${menu.expanded? 'selected': ''}>展 &nbsp;开</option>
									</select>
								</td>
							</tr>
							<tr class="th" id="texp" style="display:table-row">
								<th align="right">菜单角色:</th>
								<td class="ltd">
				                   	<input class="input-xlarge required" name="roleType" value="true" type="radio" ${menu.roleType? 'checked': ''}/>工厂
									<input class="input-xlarge required" name="roleType" value="false" type="radio" ${menu.roleType == false? 'checked': ''} style="margin-left: 20px;"/>经销商
									<label style="display:none;" for="roleType" generated="true" class="error">必选字段</label>
								</td>
							</tr>
													
							<tr>
								<td colspan="2" align="center">
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
		
		<jsp:include page="/common/footer.jsp"/>
		
		<script type="text/javascript">
			$(document).ready(function() {
				$("#validateForm").validate();
			});	
		
			function rowEnable(vl, compName){
				if('true' == vl){
					$("#"+ compName).hide();
				}
				else if('false' == vl){
					$("#"+compName).show();
				}
			}
			
			getMenus('${menu.parent.id}');
			
			function getMenus(defaultSelect) {
				$.ajax({
					type : 'post',
					url : '${pageContext.request.contextPath}/menu/nodes',
					dataType : 'json',
					success : function(data) {
						if (data) {
							for ( var i = 0; i < data.length; i++) {
								var op = new Option(data[i].label, data[i].id);
								op.innerHTML = data[i].label;
								if(defaultSelect == data[i].id){
									op.selected = true;
								}
								$("#parentMenus").append(op);
							}
						}
					}
				});
			}
		</script>
		
	</body>

</html>