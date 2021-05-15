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
				<section class="forms">
					<div class="container-fluid">
						<!-- <div class="row">
							<div class="col-md-12"> -->
								<form action="SalaryList" method="post" name="fill">
									<div class="row">
										<div class="col-md-2">
											<select name="group"
												onchange="changeGroup(this.value)" >
												<option value="công ty">Công ty</option>
												<option value="phòng ban">Phòng ban</option>
											</select>
										</div>

										<div class="col-md-2">
											<input name="groupValue"
												aria-describedby="button-addon1"
												class="form-control border-0 bg-light" placeholder="HDCP">
										</div>
										<div class="col-md-2">
											<select name="time"
												onchange="changeTime(this.value)">
												<option value="tháng">Tháng</option>
												<option value="quý">Quý</option>
											</select>
										</div>

										<div class="col-md-2">
											<input type="number" name="timeValue"
												aria-describedby="button-addon1"
												class="form-control border-0 bg-light"
												placeholder="Nhập tháng tại đây" max="12" min="1" step="1">
										</div>
										<div class="col-md-2">
											<select id="category" name="year"
												onchange="change_country(this.value)">
												<option value="2021">2021</option>
												<option value="2020">2020</option>
												<option value="2019">2019</option>
												<option value="2018">2018</option>
												<option value="2017">2017</option>
												<option value="2016">2016</option>
												<option value="2015">2015</option>
											</select>
										</div>
										<div class="col-md-2">
											<button class="btn btn-outline-danger my-2 my-sm-0"
												type="submit">Thống kê</button>
										</div>
									</div>
								</form>
							</div>
						<!-- </div>
					</div> -->
				</section>
				<section>
					<div class="container-fluid">
						<!-- <div class="row"> -->
							<div class="col-md-12">
								<div>
									<p style="color: red;">${error}</p>
								</div>
								<div class="section-heading">
									<h2>Dự án</h2>
								</div>
								<div>
									<table id="tableInfo">
										<thead>
											<tr style="background: #03a9f4">
												<th>STT</th>
												<th>Mã nhân viên</th>
												<th>Tên nhân viên</th>
												<th>Chức vụ</th>
												<th>Tên phòng ban</th>
												<th>Tổng lương</th>
											</tr>
										</thead>
										<tbody>
											<%
												int i = 1;
											%>
											<c:forEach items="${salary}" var="entry">
												<tr>
													<th><%=i%></th>
													<%
														i = i + 1;
													%>
													<th>${entry.id}</th>
													<th>${entry.employeeName}</th>
													<th>${entry.position}</th>
													<th>${entry.departmentName}</th>
													<th>${entry.sumSalary}</th>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<div class="col-md-12">
									<button type="button" id="form-submit" class="button" onclick="exportExcel()">In
										thông tin</button>
								</div>
							</div>
						</div>
					<!-- </div> -->
				</section>
			</div>
		</div>
		<div id="sidebar" class ="inactive">

			<div class="inner">
				<jsp:include page="_Menu.jsp"></jsp:include>
				<jsp:include page="_Footer.jsp"></jsp:include>
			</div>
		</div>

	</div>
	<script type="text/javascript">
		$(document).ready(function (){
			changeGroup("công ty");
			$('#tableInfo').DataTable( {
				"pagingType": "full_numbers"
			} );
		});
		function exportExcel(){
	
			$.ajax({
				dataType: "json",
				url: "ExportExcel",
				type : "post",
				data : {},
				success: function (result){
					console.log(result)
				}

			})
		};
		function changeGroup(type) {
			if (type == "công ty") {
				document.forms['fill'].groupValue.value = "DHPT";
				document.forms['fill'].groupValue.readOnly = true;
			} else {
				document.forms['fill'].groupValue.value = "";
				document.forms['fill'].groupValue.placeholder = "Nhập mã phòng ban tại đây";
				document.forms['fill'].groupValue.readOnly = false;
				document.forms['fill'].groupValue.required = "";
			}
		};
		function changeTime(type) {
			var a = type;

			if (a.toString() == "tháng") {
				document.forms['fill'].timeValue.value = "";
				document.forms['fill'].timeValue.placeholder = "Nhập tháng tại đây";
				document.forms['fill'].timeValue.max = "12"
				document.forms['fill'].timeValue.min = "1"
				document.forms['fill'].timeValue.step = "1"
			} else {

				document.forms['fill'].timeValue.value = "";
				document.forms['fill'].timeValue.placeholder = "Nhập quý tại đây";
				document.forms['fill'].timeValue.max = "4"
				document.forms['fill'].timeValue.min = "1"
				document.forms['fill'].timeValue.step = "1"
			}
		};
	</script>
</body>

</html>