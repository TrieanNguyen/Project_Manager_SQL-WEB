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
<script type="text/javascript">
        function deleteTask(idTask, taskName){
				var result = confirm("Bạn có thực sự muốn xóa công việc "+ taskName);
				if (result == true) {
					document.forms['task'].taskId.value = idTask;
					document.forms['task'].action = "DeleteTask";
					document.forms['task'].submit();
				}
        }
        function editTask(taskId, idEmployee) {
            document.forms['task'].taskId.value = taskId;
			document.forms['task'].employeeId.value = idEmployee;
            document.forms['task'].action = "EditTask";
            document.forms['task'].submit();
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
                <div class="section-heading">
                    <h2>Danh sách công việc trong dự án ${projectName}</h2>
                    <form method="GET" action="ProjectList" class="form-inline my-2 my-lg-0">
						<div class="col-md-12">
							<button <%-- style="${styleDeleteButton}" --%> class="btn btn-style btn-primary" type="submit">Quay lại</button>
						</div>
					</form>
					<form name="insertTask" method="GET" action="InsertTask" class="form-inline my-2 my-lg-0">
						<div class="text-center">
							<button style="${styleDeleteButton}" class="btn btn-style btn-primary" type="submit">Thêm
								công việc</button>
							<input type="hidden" name="projectId" value=${projectId}>
							<input type="hidden" name="departmentId" value=${departmentId}>
						</div>
					</form>
					
                </div>


				<div>
					<section>
						<div class="container-fluid">
							<div class="row">
								<div class="col-md-12">
									<div>
										<p style="color: red;">${error}</p>
									</div>
									<c:if test="${success == true}">
										<div class="alert alert-success" id="successInfo">
											<strong>${info}</strong>
										</div>
									</c:if>
									<div>
										<table id="tableInfo">
											<caption>Danh sách công việc</caption>
											<thead>
											<tr style="background: #03a9f4">
												<th>STT</th>
												<th>ID</th>
												<th>Tên công việc</th>
												<th>Mã người phụ trách</th>
												<th>Ngày bắt đầu</th>
												<th>Ngày dự kiến kết thúc</th>
												<th>Mức độ hoàn thành</th>
												<th>Hệ số công việc</th>
												<th>Điểm thái độ</th>
<%--												<th>Link</th>--%>
<%--												<th>Đã hoàn thành</th>--%>
<%--												<th>Ghi chú</th>--%>
												<th></th>
											</tr>
											</thead>
											<tbody id="info">
											<%
												int i = 1;
											%>
											<c:forEach items="${taskList}" var="entry">
												<c:choose>
													<c:when test="${entry.completed == true}">
														<tr style="background-color:#fefbd8">
													</c:when>
													<c:otherwise>
														<tr style="background-color:#FAFAFA">
													</c:otherwise>
												</c:choose>
												<%--<tr style="background-color:#FF0000">--%>
													<td><%=i%></td>
													<%
														i = i + 1;
													%>
													<td>${entry.id} </td>
													<td>${entry.name}</td>
													<td>${entry.employeeId}</td>
													<td>${entry.timeStart}</td>
													<td>${entry.estimatedTimeEnd}</td>
													<td>${entry.levelTaskCompleted}</td>
													<td>${entry.coefficient}</td>
													<td>${entry.attitude}</td>
<%--													<td>${entry.linkCheckTask}</td>--%>

													<%--<td>${entry.completed}</td>--%>
<%--													<td>${entry.note}</td>--%>
													<td>
														<button style="${entry.style}" class='btn btn-xs btn-default btn-quick'
																title='Edit Row'
																onclick="editTask(${entry.id}, ${entry.employeeId}), ${entry.departmentId}">
															<i class='fa fa-pencil fa-lg'></i>
														</button>
														<button style="${styleDeleteButton}" class='btn btn-xs btn-default btn-quick'
																title='Delete' onclick="deleteTask(${entry.id},'${entry.name}')">
															<i class='fa fa-times fa-lg'></i>
														</button>
													</td>
												</tr>
											</c:forEach>

											</tbody>

										</table>
										<form name="task">
											<input type="hidden" name="taskId">
											<input type="hidden" name="projectId" value=${projectId}>
											<input type="hidden" name="departmentId" value=${departmentId}>
											<input type="hidden" name="employeeId">
										</form>
									</div>
								</div>
							</div>
						</div>
					</section>
				</div>

			</div>
		</div>

		<!-- Sidebar -->
		<div id="sidebar" class="inactive">
			<div class="inner">
				<c:choose>
					<c:when test="${type == 3 or type == 4}">
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
		$(document).ready(function() {
			$('#tableInfo').DataTable( {
				"pagingType": "full_numbers"
			} );
		} );
    </script>
</body>

</html>
