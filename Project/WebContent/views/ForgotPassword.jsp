<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
	<div id="wrapper">

		<!-- Main -->
		<div id="main">
			<div class="inner">
				<jsp:include page="_Header.jsp"></jsp:include>
				
				<form class="form-inline my-2 my-lg-0" method="post"
					action="ForgotPassword" style="${styleForm}">
					<div class = "col-md-6">
						<h5>Điền mã nhân viên vào đây</h5>
					</div>
					<div class ="col-md-6">
						<input class="form-control mr-sm-6" type="number" name="id"
							value="${id}">
					</div>
					<div class ="col-md-6">
						 <input class="btn btn-danger"
							type="submit" value="Gửi mật khẩu mới đến email" />
					</div>
				</form>
				
				<div class ="row">
					<div class ="col-12">
					<div class ="col-md-6">
						<span class="badge-warning btn-lg" style="${style}"> ${status} </span>
					</div>
					<div class ="col-md-6">

						<a class="txt2" style="${style}" href="Login">Quay lại trang đăng nhập </a>
					</div>
					</div>
					
				</div> 
			</div>
		</div>
	</div>
</body>
</html>
