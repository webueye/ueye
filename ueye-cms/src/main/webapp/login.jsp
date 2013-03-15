<%@ page language="java" contentType="text/html;charset=utf-8"%>

<!DOCTYPE html>
<html>

	<head>
		<title>UEye</title>
		<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/css/bootstrap-responsive.css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/css/docs.css" rel="stylesheet" />
		
		<style type="text/css">
			<!--
			body {
				background-image: url(../img/grid-18px-masked.png);
			}
			-->
		</style>
	</head>

	<body>

		<div class="navbar navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container-fluid">
				</div>
			</div>
		</div>

    	<div class="container">
				<header class="masthead">
					<div class="inner">
						<h1>UEye</h1>
					</div>
				</header>

			<div class="row" align="center">
	
				<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/login" name="loginForm">
					<fieldset style="font-size: 20px;">
						<div class="control-group">
							<div>
								<span>用户名:</span> 
								<input class="input-xlarge focused" id="username" name="account.username" type="text" placeholder="username"/>
							</div>
						</div>
	
						<div class="control-group">
							<div>
								<span>密&nbsp;&nbsp;&nbsp;码:</span> 
								<input class="input-xlarge focused" id="password" name="account.password" type="password" placeholder="password"/>
							</div>
						</div>
	
						<div class="">
							<button type="submit" class="btn btn-primary btn-large">登&nbsp;&nbsp;&nbsp;陆</button>
						</div>
					</fieldset>
				</form>
	
			</div>

			<hr class="soften"/>
		</div>

		<script type="text/javascript">
		<!--
			if (window.self != window.top) {
				window.open(".", "_top");
			}
			document.forms["loginForm"].elements["username"].focus();
		// -->
		</script>

	</body>

</html>
