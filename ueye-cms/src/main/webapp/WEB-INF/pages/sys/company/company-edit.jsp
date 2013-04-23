<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>经销商档案修改</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
				
				<div style="margin: 10px;" align="center">
					<b><font color="red">${msg}</font></b>
				</div>
			
				<div class="grayDiv">
					<div class="divFont"><b>经销商档案修改</b></div>
				</div>
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/user/update-company/${company.id}">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr>
								<th>企业名称：</th>
								<td>
									<input name="id" value="${company.id}" type="hidden" style="width: 80%"/>
									<input class="input-xlarge required" name="companyName" value="${company.companyName}" type="text" style="width: 80%"/>
								</td>
								<th>状态：</th>
								<td>
									<input class="input-xlarge required" name="status" value="true" type="radio" ${company.status != false? 'checked': ''}/>启用
									<input class="input-xlarge required" name="status" value="false" type="radio" ${company.status == false? 'checked': ''} style="margin-left: 20px;"/>禁用
								</td>
							</tr>
							<tr>
								<th>企业编号：</th>
								<td>
									<input class="input-xlarge required" name="compnayNo" value="${company.compnayNo}" type="text" style="width: 80%"/>
								</td>
								<th>企业类型：</th>
								<td>
									<select name="companyType.id" style="width:80%;">
										<option value="">--请选择--</option>
										<c:forEach var="companyType" items="${companyTypes}">
											<option value="${companyType.id}" ${company.companyType.id == companyType.id? 'selected': ''}>${companyType.name}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<th>销售区域：</th>
								<td>
									<select name="saleRegion.id" style="width:80%;">
										<option value="">--请选择--</option>
										<c:forEach var="saleRegion" items="${saleRegions}">
											<option value="${saleRegion.id}" ${company.saleRegion.id == saleRegion.id? 'selected': ''}>${saleRegion.name}</option>
										</c:forEach>
									</select>
								</td>
								<th>所在城市：</th>
								<td>
									<select name="city.id" style="width:80%;">
										<option value="">--请选择--</option>
										<c:forEach var="city" items="${citys}">
											<option value="${city.id}" ${company.city.id == city.id? 'selected': ''}>${city.name}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<th>企业性质：</th>
								<td>
									<select name="companyNature.id" style="width:80%;">
										<option value="">--请选择--</option>
										<c:forEach var="companyNature" items="${companyNatures}">
											<option value="${companyNature.id}" ${company.companyNature.id == companyNature.id? 'selected': ''}>${companyNature.name}</option>
										</c:forEach>
									</select>
								</td>
								<th>销售形式：</th>
								<td>
									<select name="saleForm.id" style="width:80%;">
										<option value="">--请选择--</option>
										<c:forEach var="saleForm" items="${saleForms}">
											<option value="${saleForm.id}" ${company.saleForm.id == saleForm.id? 'selected': ''}>${saleForm.name}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							
							<tr>
								<th>企业规模：</th>
								<td>
									<input class="input-xlarge number" name="scale" value="${company.scale}" type="text" style="width: 80%"/>
								</td>
								<th>仓库面积：</th>
								<td>
									<input class="input-xlarge number" name="depotArea" value="${company.depotArea}" type="text" style="width: 80%"/>
								</td>
							</tr>
							
							<tr>
								<th>员工人数：</th>
								<td>
									<input class="input-xlarge number" name="employeeCount" value="${company.employeeCount}" type="text" style="width: 80%"/>
								</td>
								<th>业务人员人数：</th>
								<td>
									<input class="input-xlarge number" name="businessCount" value="${company.businessCount}" type="text" style="width: 80%"/>
								</td>
							</tr>
							
							<tr>
								<th>企业角色：</th>
								<td>
									<input class="input-xlarge required" name="roleType" value="true" type="radio" ${company.roleType != false? 'checked': ''}/>工厂
									<input class="input-xlarge required" name="roleType" value="false" type="radio" ${company.roleType == false? 'checked': ''} style="margin-left: 20px;"/>经销商
								</td>
								<th>公司地址：</th>
								<td colspan="1">
									<input class="input-xlarge" name="address" type="text" value="${company.address}" style="width: 80%"/>
								</td>
							</tr>
							
							<tr>
								<th>邮编：</th>
								<td>
									<input class="input-xlarge number" name="companyZip" value="${company.companyZip}" type="text" style="width: 80%"/>
								</td>
								<th>单位帐号：</th>
								<td>
									<input class="input-xlarge" name="bankAccountNo" value="${company.bankAccountNo}" type="text" style="width: 80%"/>
								</td>
							</tr>
							
							<tr>
								<th>开户行：</th>
								<td>
									<input class="input-xlarge" name="belongBankName" value="${company.belongBankName}" type="text" style="width: 80%"/>
								</td>
								<th>开户名：</th>
								<td>
									<input class="input-xlarge" name="bankAccountName" value="${company.bankAccountName}" type="text" style="width: 80%"/>
								</td>
							</tr>
							
							<tr>
								<th>收货地址：</th>
								<td colspan="3">
									<input class="input-xlarge" name="shippingAddress" value="${company.shippingAddress}" type="text" style="width: 80%"/>
								</td>
							</tr>
							
							<tr>
								<th>收货人：</th>
								<td>
									<input class="input-xlarge" name="shippingMan" value="${company.shippingMan}" type="text" style="width: 80%"/>
								</td>
								<th>收货电话：</th>
								<td>
									<input class="input-xlarge" name="shippingPhone" value="${company.shippingPhone}" type="text" style="width: 80%"/>
								</td>
							</tr>
							
							<tr>
								<th>企业备注：</th>
								<td colspan="3">
									<textarea name="memo" style="height: 54px; width: 80%; ">${company.memo}</textarea>
								</td>
							</tr>
							
							<tr>
								<td colspan="4" align="center">
									<div align="center">
										<button type="submit" class="btn btn-primary">修改</button>
										<button type="button" class="btn btn-primary historyBackClass">返回</button>
									</div>
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