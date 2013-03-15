<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	function paginationHandle(pageNum, pageSize){
		if(!isNaN(pageNum)){
			document.getElementById("pageNum").value = pageNum;
		}
		if(!isNaN(pageSize)){
			document.getElementById("pageSize").value = pageSize;
		}
		
		if(document.getElementById("validateForm")){
			document.getElementById("validateForm").submit();
			return false;
		}else{
			var currentUrl = window.location.href;
			pageNum = document.getElementById("pageNum").value;
			pageSize = document.getElementById("pageSize").value;
			
			if(currentUrl.indexOf("?") == -1){
				window.location.href = currentUrl + "?pageNum="+pageNum+"&pageSize="+pageSize;
			}else if(currentUrl.indexOf("pageSize=") == -1){
				window.location.href = currentUrl + "&pageSize="+pageSize;
			}else if(currentUrl.indexOf("pageNum=") == -1){
				window.location.href = currentUrl + "&pageNum="+pageSize;
			}else{
				var url = currentUrl.substring(0, currentUrl.indexOf("&pageSize"));
				window.location.href = (url + "&pageSize="+pageSize);
			}
			return false;
		}
		return true;
	}
	
	function jumpPage(){
		var jumpPageValue = document.getElementById("jumpPageValue").value;
		if(isNaN(parseInt(jumpPageValue))){
			alert('请输入一个整数值');
			return;
		}

		var totals = "${page.totalPages}";
		if(parseInt(jumpPageValue) > parseInt(totals)){
			alert('你所要跳转的页数不存在');
			return;
		}
		document.getElementById("pageNum").value = jumpPageValue;
		if(document.getElementById("validateForm")){
			document.getElementById("validateForm").submit();
			return false;
		}
	}
</script>

<c:if test="${page.totalPages > 0}">
	<div class="search" style="margin: 1px; padding: 5px;">
		
		<span style="margin-left: 5px;">
			总记录${page.totalCount}条
			第${page.pageNum}页/共${page.totalPages}页
		</span>
		
		<span>
			每页显示
			<select name="pageSize" style="height:25px; line-height: 20px;width: 55px;margin:3px;" onchange='paginationHandle("${page.pageNum}", this.value)'>
				<option ${page.pageSize == '10'? 'selected': ''}>10</option>
				<option ${page.pageSize == '25'? 'selected': ''}>25</option>
				<option ${page.pageSize == '50'? 'selected': ''}>50</option>
				<option ${page.pageSize == '100'? 'selected': ''}>100</option>
			</select>
			条记录
		</span>
		
		<span style="float:right;margin:5px;">
			<c:if test="${page.totalPages > 1}">
				<c:if test="${page.pageNum == 1}">
					[<span>首页</span> |
					<span>上一页</span> |
					<a href="${pageContext.request.contextPath}/${param.actionURL}?pageNum=${page.pageNum+1}${param.param}" onclick='return paginationHandle("${page.pageNum+1}");'>下一页</a> |
					<a href="${pageContext.request.contextPath}/${param.actionURL}?pageNum=${page.totalPages}${param.param}" onclick='return paginationHandle("${page.totalPages}");'>末页</a>]
				</c:if>
				<c:if test="${page.pageNum > 1 and page.pageNum < page.totalPages}">
					[<a href="${pageContext.request.contextPath}/${param.actionURL}?pageNum=1${param.param}" onclick='return paginationHandle("${a}1");'>首页</a> |
					<a href="${pageContext.request.contextPath}/${param.actionURL}?pageNum=${page.pageNum-1}${param.param}" onclick='return paginationHandle("${page.pageNum-1}");'>上一页</a> |
					<a href="${pageContext.request.contextPath}/${param.actionURL}?pageNum=${page.pageNum+1}${param.param}" onclick='return paginationHandle("${page.pageNum+1}");'>下一页</a> |
					<a href="${pageContext.request.contextPath}/${param.actionURL}?pageNum=${page.totalPages}${param.param}" onclick='return paginationHandle("${page.totalPages}");'>末页</a>]
				</c:if>
				<c:if test="${page.pageNum == page.totalPages}">
					[<a href="${pageContext.request.contextPath}/${param.actionURL}?pageNum=1${param.param}" onclick='return paginationHandle("${a}1");'>首页</a> |
					<a href="${pageContext.request.contextPath}/${param.actionURL}?pageNum=${page.pageNum-1}${param.param}" onclick='return paginationHandle("${page.pageNum-1}");'>上一页</a> |
					<span>下一页</span> |
					<span>末页</span>]
				</c:if>	
			</c:if>
			<c:if test="${page.totalPages == 1}">
				[<span>首页</span> |
				<span>上一页</span> |
				<span>下一页</span> |
				<span>末页</span>]
			</c:if>
		
			<span>
				<input id="jumpPageValue" value="${page.pageNum}" type="text" style="width:20px;margin-left: 5px;"/>
				<span style="cursor: pointer;" onclick="jumpPage()">Go</span>
			</span>
			
		</span>
		
	</div>
	
	<input id="pageSize" name="pageSize" value="${page.pageSize}" type="hidden" />
	<input id="pageNum" name="pageNum" value="${page.pageNum}" type="hidden" />
	
</c:if>


<input id="order" name="order" value="${page.order}" type="hidden" />
<input id="orderBy" name="orderBy" value="${page.orderBy}" type="hidden" />
<input id="className" name="className" value="${page.className}" type="hidden" />