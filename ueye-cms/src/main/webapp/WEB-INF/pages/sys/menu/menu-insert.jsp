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
					
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/menu">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th align="center" colspan="2">菜单添加</th>
							</tr>
							<tr class="th">
								<th align="right">父菜单名称:</th>
								<td class="ltd">
									<select id="parentMenus" name="parentId" style="width:150px">
										<option value="">请选择</option>
									</select>
								</td>
							</tr>
							<tr class="th">
								<th align="right">菜单名称:</th>
								<td class="ltd">
									<input class="input-xlarge focused required" name="label" type="text"/>
								</td>
							</tr>
							<tr class="th">
								<th align="right">菜单地址:</th>
								<td class="ltd">
									<input class="input-xlarge focused" name="action" type="text"/>
								</td>
							</tr>
							<tr class="th">
								<th>菜单序号:</th>
								<td class="ltd">
									<input class="input-xlarge focused required number" name="orderValue" type="text" value="10"/>
								</td>
							</tr>
							<tr class="th">
								<th align="right">叶子节点:</th>
								<td class="ltd">
									<select name="leaf" onChange="rowEnable(this.value, 'texp')" style="width:150px">
										<option value="false"> 非叶子节点</option>
										<option value="true"> 叶子节点</option>					
									</select>
								</td>				
							</tr>
							<tr class="th" id="texp" style="display:table-row">
								<th align="right">是否展开:</th>
								<td class="ltd">
				                    <select name="expanded" title="默认情况下此节点是否展开" style="width:150px">
										<option value="false"> 不展开</option>
										<option value="true"> 展 &nbsp;开</option>
									</select>
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