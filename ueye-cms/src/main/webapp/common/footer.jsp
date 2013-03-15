<%@ page language="java" pageEncoding="UTF-8"%>

<script type="text/javascript">
<!--
	var basePath = "${pageContext.request.contextPath}";
//-->
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#validateForm").validate();
		$("button").filter(".historyBackClass").each(function(i){
			$(this).bind("click", function(){
				//window.history.go(-1);
				javascript:history.back(); 
			});
		});
	});
	
	function confirmMessage(){
		
	}
</script>
