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
	function showTask(projectId, departmentId) {
		document.forms['project'].projectId.value = projectId;
        document.forms['project'].departmentId.value = departmentId;
		document.forms['project'].action = "TaskList";
		document.forms['project'].submit();
	}
	function deleteProject(projectId, projectName) {
		var result = confirm("Bạn có thực sự muốn xóa dự án "+ projectName);
		if (result == true) {
			document.forms['project'].projectId.value = projectId;
			document.forms['project'].action = "DeleteProject";
			document.forms['project'].submit();
		}
	}
	function editProject(projectId) {
		document.forms['department'].projectId.value = projectId;
		document.forms['department'].action = "EditProject";
		document.forms['department'].submit();
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
					<h2>Danh sách dự án</h2>
				</div>
				<form class="form-inline my-2 my-lg-0"method="get" action="InsertProject">
      				<button style="${styleButtonInsert}" class="btn btn-outline-danger my-2 my-sm-0" type="submit"> Thêm dự án</button>
   				</form>

			<div>
				<!-- Tables -->
				<section>
					<div class="container-fluid">
						<div class="row">
							<div class="col-md-12">
								<c:if test="${success == true}">
									<div class="alert alert-success" id="notification">
										<strong>${info}</strong>
									</div>
								</c:if>
								<div>
									<table id="tableInfo">
										<thead >
											<tr style=" background:#03a9f4">
												<th>STT</th>
												<th>ID</th>
												<th>Name</th>
												<th>ID NQL</th>
												<th>Tổng tiền DA ($)</th>
												<th>Bậc tiến độ</th>
												<th>Mức độ hoàn thành</th>
												<th>Thời gian dự kiến hoàn thành</th>
												<th></th>
											</tr>
										</thead>
										<tbody id="info">
											<%
												int i = 1;
											%>
												<c:forEach items="${listProject}" var="entry">
													<tr>
														<td><%=i%></td>

														<%
															i = i + 1;
														%>
														<td>${entry.id}</td>
														<td>${entry.name}</td>
														<td>${entry.leader}</td>
														<td>${entry.totalRevenue}</td>
														<td>${entry.levelProjectCompleted}</td>
														<td>${entry.progress}</td>
														<td>${entry.estimatedTimeEnd}</td>
														<td>
															<%--<button class='btn btn-xs btn-default btn-quick'
																	title='Edit Row' onclick="editProject(${entry.id})"
																	style="${entry.style}">
																<i class='fa fa-pencil fa-lg'></i>
															</button>--%>
															<c:choose>
																<c:when test="${type == 3}">
																	<button class='btn btn-xs btn-default btn-quick'
																			title='Edit Row' onclick="editProject(${entry.id})"
																			style="${entry.style}">
																		<i class='fa fa-pencil fa-lg'></i>
																	</button>
																</c:when>
																<c:otherwise>
																	<button class='btn btn-xs btn-default btn-quick'
																			title='Edit Row' onclick="editProject(${entry.id})"
																			style="${styleButtonEdit}">
																		<i class='fa fa-pencil fa-lg'></i>
																	</button>
																</c:otherwise>
															</c:choose>
															<button class='btn btn-xs btn-default btn-quick'
																	style="${styleButtonDelete}" title='Delete'
																	onclick="deleteProject(${entry.id}, '${entry.name}')">
																<i class='fa fa-times fa-lg'></i>
															</button>
															<button style="${entry.style}" class='btn btn-xs btn-default btn-quick'
																	title='Hiển thị công việc'
																	onclick="showTask(${entry.id}, ${entry.departmentId})">
																<i class='fa fa-book fa-lg'></i>
															</button>
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
		</div>
		<form name="project">
			<input type="hidden" name="projectId">
            <input type="hidden" name="departmentId">
		</form>
		<form name="department">
			<input type="hidden" name="projectId">
		</form>
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
	$(document).ready(function() {
		$('#tableInfo').DataTable( {
			"pagingType": "full_numbers"
		} );
	} );
	function hideNotification(){
		$('#notification').hide();
	};

</script>
</body>

</html>
