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
												type="submit">Tính tổng</button>
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
												<th>Mã công việc</th>
												<th>Tên công việc</th>
												<th>Số tiền đã nhân</th>
												<th>Bonus</th>
												<th>Tổng lương</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>1</td>
												<td>2</td>
												<td>Quản lý</td>
												<td>2000</td>
												<td>200</td>
												<td>2200</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					<!-- </div> -->
				</section>
			</div>
		</div>
		<div id="sidebar" class="inactive">

			<div class="inner">
				<jsp:include page="_Menu.jsp"></jsp:include>
				<jsp:include page="_Footer.jsp"></jsp:include>
			</div>
		</div>

	</div>

</body>

</html>