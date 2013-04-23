<!DOCTYPE html>
<html lang="en">
  	<head>
	    <meta charset="utf-8">
	    <title>UEye</title>
	
	    <!-- Le styles -->
	    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
	    <style type="text/css">
	      body {
	        padding-top: 20px;
	        padding-bottom: 60px;
	        background-repeat: repeat-x;
	        background-image: url(../img/grid-18px-masked.png);
	      }
	
	      /* Custom container */
	      .container {
	        margin: 0 auto;
	        max-width: 1000px;
	      }
	      .container > hr {
	        margin: 60px 0;
	      }
	
	      /* Main marketing message and sign up button */
	      .jumbotron {
	        margin: 80px 0;
	        text-align: center;
	      }
	      .jumbotron h1 {
	        font-size: 100px;
	        line-height: 1;
	      }
	      .jumbotron .lead {
	        font-size: 24px;
	        line-height: 1.25;
	      }
	      .jumbotron .btn {
	        font-size: 21px;
	        padding: 14px 24px;
	      }
	
	      /* Supporting marketing content */
	      .marketing {
	        margin: 60px 0;
	      }
	      .marketing p + h4 {
	        margin-top: 28px;
	      }
	
	
	      /* Customize the navbar links to be fill the entire space of the .navbar */
	      .navbar .navbar-inner {
	        padding: 0;
	      }
	      .navbar .nav {
	        margin: 0;
	        display: table;
	        width: 100%;
	      }
	      .navbar .nav li {
	        display: table-cell;
	        width: 1%;
	        float: none;
	      }
	      .navbar .nav li a {
	        font-weight: bold;
	        text-align: center;
	        border-left: 1px solid rgba(255,255,255,.75);
	        border-right: 1px solid rgba(0,0,0,.1);
	      }
	      .navbar .nav li:first-child a {
	        border-left: 0;
	        border-radius: 3px 0 0 3px;
	      }
	      .navbar .nav li:last-child a {
	        border-right: 0;
	        border-radius: 0 3px 3px 0;
	      }
	    </style>
	    <link href="${pageContext.request.contextPath}/css/bootstrap-responsive.css" rel="stylesheet">
  	</head>

  	<body>

	    <div class="container">
	
	      <div class="masthead">
	        <h3 class="muted">Project name</h3>
	        <div class="navbar">
	          <div class="navbar-inner">
	            <div class="container">
	              <ul class="nav">
	              </ul>
	            </div>
	          </div>
	        </div><!-- /.navbar -->
	      </div>
	
	      <!-- Jumbotron -->
	      <div class="jumbotron">
	        <h1></h1>
	        
	        <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/login" name="loginForm">
				<fieldset style="font-size: 20px;">
					<div class="control-group">
						<div>
							<span>用户名:</span> 
							<input class="input-medium focused" id="username" name="username" type="text" placeholder="username"/>
						</div>
					</div>

					<div class="control-group">
						<div>
							<span>密&nbsp;&nbsp;&nbsp;码:</span> 
							<input class="input-medium focused" id="password" name="password" type="password" placeholder="password"/>
						</div>
					</div>

					<div class="">
						<button type="submit" class="btn btn-primary btn-large">登&nbsp;&nbsp;&nbsp;陆</button>
					</div>
				</fieldset>
			</form>
	        
	      </div>
	
	      <hr>
	
	      <div class="footer" align="center">
	        <p>&copy; Company 2013</p>
	      </div>
	
	    </div> <!-- /container -->
	    
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
