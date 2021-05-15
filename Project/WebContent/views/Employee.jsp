<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="zxx">
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>Công ty quản lý phần mềm</title>
<meta content="" name="descriptison">
<meta content="" name="keywords">
<script type="text/javascript">
	function callServletEmployee() {
		document.forms['insertEmployee'].action = "InsertEmployee";
		document.forms['insertEmployee'].submit();
	}
	function callServletEditEmployee(id) {
		document.forms['action'].idEmployee.value = id;
		document.forms['action'].action = "EditEmployee";
		document.forms['action'].submit();
	}
	function callServletDeleteEmployee(id, employeeName) {
		var result = confirm("Bạn có thực sự muốn xóa nhân viên "+ employeeName);
		if (result == true){
			document.forms['action'].idEmployee.value = id;
			document.forms['action'].action = "DeleteEmployee";
			document.forms['action'].submit();
		}
	}
</script>


</head>
<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<div id="main">
			<div class="inner">
				<jsp:include page="_Header.jsp"></jsp:include>
				<form class="form-inline my-2 my-lg-0" method="get"
					name="insertEmployee" action="InsertEmployee">
					<button class="btn btn-outline-danger my-2 my-sm-0" type="submit">Thêm nhân viên</button>
				</form>
				<section>

					<div class="container-fluid">
						<div class="row">
							<div class="col-md-12">
								<div class="section-heading">
									<h2>Danh sách nhân viên</h2>
								</div>
								<c:if test="${success == true}">
									<div class="alert alert-success" id="successInfo">
										<strong>${info}</strong>
									</div>
								</c:if>
								<div>
									<table id="tableInfo">
										<thead>
											<tr style="background:#03a9f4">
												<th>STT</th>
												<th>Mã nhân viên</th>
												<th>Họ Tên</th>
												<th>Giới tính</th>
												<th>Email</th>
												<th>Địa chỉ</th>
												<th>Phòng</th>
												<th></th>
											</tr>
										</thead>
										<tbody id="info">
											<%
												int i = 1;
											%>
											<c:forEach items="${mapEmployeeAndDepartmentName}"
												var="entry">
												<tr>
													<th><%=i%></th>
													<%
														i = i + 1;
													%>
													<th>${entry.key.id}</th>
													<th>${entry.key.name}</th>
													<th>${entry.key.sex}</th>
													<th>${entry.key.email}</th>
													<th>${entry.key.address}</th>
													<th>${entry.value}</th>
													<th>

														<button onclick="callServletEditEmployee(${entry.key.id})"
															class='btn btn-xs btn-default btn-quick' title='Chi tiết'>
															<i class='fa fa-pencil-square fa-lg'></i>
														</button>
														<button
															onclick="callServletDeleteEmployee(${entry.key.id}, '${entry.key.name}')"
															class='btn btn-xs btn-default btn-quick' title='Xóa'>
															<i class='fa fa-times fa-lg'></i>
														</button>
												</tr>
											</c:forEach>

										</tbody>
									</table>
									<form name="action">
										<input type="hidden" name="idEmployee">
									</form>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
		</div>

		<!-- Sidebar -->
		<div id="sidebar" class="inactive" >
			<div class="inner">
				<jsp:include page="_Menu.jsp"></jsp:include>
				<jsp:include page="_Footer.jsp"></jsp:include>
			</div>
		</div>
	</div>
	<script>
		$(document).ready(function() {
			$('#tableInfo').DataTable( {
				"pagingType": "full_numbers"
			} );
		} );
		function search(){
			var searchvalue=$("#searchValue").val();
			console.log(searchvalue)
			$.ajax({
				type: 'POST',
				data: {
					searchValue: searchvalue,
					type: "employee"
				},
				dataType: 'json',
				url: 'Search',
				success: function(res) {
					var html = "";
					console.log("thanh cong");
					$("#info").empty();
					var table = document.getElementById("info");
					var stt = 1;
					$.each(res, function (key, value) {
						var row = table.insertRow(-1);
						var cell1 = row.insertCell(0);
						var cell2 = row.insertCell(1);
						var cell3 = row.insertCell(2);
						var cell4 = row.insertCell(3);
						var cell5 = row.insertCell(4);
						var cell6 = row.insertCell(5);
						var cell7 = row.insertCell(6);
						var cell8 = row.insertCell(7);
						var cell9 = row.insertCell(8);
						cell1.innerHTML = stt;
						stt = stt + 1;
						cell2.innerHTML = value.id;
						cell3.innerHTML = value.name;
						cell4.innerHTML = value.leader;
						cell5.innerHTML = value.totalRevenue;
						cell6.innerHTML = value.levelProjectCompleted;
						cell7.innerHTML = value.progress;
						cell8.innerHTML = value.estimatedTimeEnd;
						cell9.innerHTML =
								`
							<button class='btn btn-xs btn-default btn-quick'
								title='Edit Row'
								onclick="editProject(`+value.id+`)"
								style="${style}">
								<i class='fa fa-pencil fa-lg'></i>
							</button>
							<button class='btn btn-xs btn-default btn-quick'
								style="${style}" title='Delete'
								onclick="deleteProject(`+value.id+`)">
								<i class='fa fa-times fa-lg'></i>
							</button>
							<button class='btn btn-xs btn-default btn-quick'
								title='Hiển thị công việc'
								onclick="showTask(`+value.id+`)">
								<i class='fa fa-book fa-lg'></i>
							</button>


							`
						;
					})
				}
			})
		}
	</script>
</body>

</html>