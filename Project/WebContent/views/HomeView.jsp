<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport"
		  content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Quản lý công ty phần mềm</title>


</head>

<body class="is-preload">

<!-- Wrapper -->
<div id="wrapper">

	<!-- Main -->
	<div id="main">
		<div class="inner">

			<jsp:include page="_Header.jsp"></jsp:include>
			<!-- Banner -->
			<section class="main-banner">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<div class="banner-content">
								<div class="row">
									<div class="col-md-12">
										<div class="banner-caption">
											<h4>
												CÔNG TY DHPT
											</h4>
											<span></span>
											<p></p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>

			<!-- Services -->
			<section class="services">
				<div class="container-fluid">
					<div class="row">

						<div class="col-md-4">
							<a href="ProjectList">
								<div class="service-item first-item">
									<div class="icon"></div>
									Quản lý dự án
								</div>
							</a>
						</div>
						<div class="col-md-4">
							<a href="DepartmentList">
								<div class="service-item second-item">
									<div class="icon"></div>
									Quản lý phòng ban
								</div>
							</a>
						</div>
						<div class="col-md-4">
							<a href="EmployeeList">
								<div class="service-item third-item">
									<div class="icon"></div>
									Quản lý nhân viên
								</div>
							</a>
						</div>
						<div class="col-md-4">
							<a href="SalaryList">
								<div class="service-item fourth-item">
									<div class="icon"></div>
									Quản lý lương
								</div>
							</a>
						</div>
						<div class="col-md-4">
							<a href="Schedule">
								<div class="service-item fivth-item">
									<div class="icon"></div>
									Quản lý lịch
								</div>
							</a>
						</div>
						<div class="col-md-4">
							<a href="UserInfo">
								<div class="service-item sixth-item">
									<div class="icon"></div>
									Thông tin cá nhân

								</div>
							</a>
						</div>
					</div>
				</div>
			</section>

			<!-- Right Image -->
			<h2>Vị trí</h2>
			<section class="right-image">
				<div class="container-fluid">
                    	<div id="map">
                      		<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3918.48423188134!2d106.7697336147445!3d10.850726660785039!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752763f23816ab%3A0x282f711441b6916f!2zVHLGsOG7nW5nIMSQ4bqhaSBo4buNYyBTxrAgcGjhuqFtIEvhu7kgdGh14bqtdCBUaMOgbmggcGjhu5EgSOG7kyBDaMOtIE1pbmg!5e0!3m2!1svi!2s!4v1610612810942!5m2!1svi!2s" width=100% height=450 frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
                    	</div>
                    </div>
			</section>

		</div>
	</div>

	<!-- Sidebar -->
	<div id="sidebar" class="inactive">
		<div class="inner">
			<c:choose>
				<c:when test="${limitHome == true}">
					<jsp:include page="_MenuLimit.jsp"></jsp:include>
				</c:when>
				<c:otherwise>
					<jsp:include page="_Menu.jsp"></jsp:include>
				</c:otherwise>
			</c:choose>
			<jsp:include page="_Footer.jsp"></jsp:include>
		</div>
	</div>

</div>

<script>
/*	$(document).ready(function (){
		turnOffSidebar()
	})
	function turnOffSidebar(){
		alert("abc")
	}*/
</script>
</body>

</html>