<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="zxx">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Công ty quản lý phần mềm</title>
	<script src="views/vendor/jquery/jquery.min.js"></script>
<script src="views/file/bootstrap.min.js"></script>
</head>
<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<div id="main">
			<div class="inner">
				<jsp:include page="_Header.jsp"></jsp:include>
				<c:if test="${isAdmin == true}">
					<form class="form-inline my-2 my-lg-0" method="get"
						  name="insertDepartment" action="InsertDepartment">

						<button class="btn btn-outline-danger my-2 my-sm-0" type="submit"
								style="${style}" >Thêm phòng ban</button>
					</form>
				</c:if>

	<section>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<div>
						<p style="color: red;">${error}</p>
					</div>
					<div class="section-heading">
						<h2>Danh sách phòng ban</h2>
					</div>
					<c:if test="${success == true}">
						<div class="alert alert-success">
							<strong>${info}</strong>
						</div>
					</c:if>
					<div>
						<table id="tableInfo">
							<thead>
								<tr style="background: #03a9f4">
								<th>STT</th>
								<th>Mã phòng ban</th>
								<th>Tên phòng ban</th>
								<th>Trưởng phòng</th>
								<th>Phòng</th>
								<th>Ngân sách</th>
								<th></th>
								</tr>
							</thead>
							<tbody>

								<%
									int i = 1;
								%>
								<c:forEach items="${listNameLeaderOfDepartment}" var="entry">
									<tr>
											<%--<td><input type="text" name="stt" value=<%=i%>/></td>--%>

											<%--<td><input type="text" name="id" value=${entry.key.id}/></td>
                                        <td><input type="text" name="name" value=${entry.key.name}/></td>
                                        <td><input type="text" name="value" value=${entry.value}/></td>
                                        <td><input type="text" name="room" value=${entry.key.roomNumber}/></td>
                                        <td><input type="text" name="budget" value=${entry.key.budget}/></td>--%>
										<td><%=i%></td>
											<%--<td><%=i%></td>--%>

										<%
											i = i + 1;
										%>

										<td>${entry.key.id}</td>

										<td>${entry.key.name}</td>


										<td>${entry.value}</td>


										<td>${entry.key.roomNumber}</td>


										<td>${entry.key.budget}</td>
										<td>
											<button style="${entry.key.style}" class='btn btn-xs btn-default btn-quick'
													title='Hiển thị phòng ban' type="submit"
													onclick="callServletEditDepartment(${entry.key.id})">
												<i class='fa fa-book fa-lg'></i>
											</button>
											<c:if test="${isAdmin == true}">
												<button class='btn btn-xs btn-default btn-quick'
														title='Xóa phòng ban' type="submit"
														onclick="callServletDeleteDepartment(${entry.key.id},'${entry.key.name}')">
													<i class='fa fa-times fa-lg'></i>
												</button>
											</c:if>
										</td>

									</tr>
								</c:forEach>
						</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</section>
	</div>
	</div>
		<form name="departmentList">
			<input type="hidden" name="idClick" >
		</form>
	<!-- Sidebar -->
	<div id="sidebar" class ="inactive">

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
		function callServletEditDepartment(id) {
		document.forms['departmentList'].idClick.value = id;
		document.forms['departmentList'].action = "EditDepartment";
		document.forms['departmentList'].submit();
	}
		function callServletDeleteDepartment(id, departmentName) {
		var result = confirm("Bạn có thực sự muốn xóa phòng ban nay "+ departmentName);
		if (result == true) {
		document.forms['departmentList'].idClick.value = id;
		document.forms['departmentList'].action = "DeleteDepartment";
		document.forms['departmentList'].submit();
		}
	}
</script>
</body>

</html>