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
<style type="text/css">
		<jsp:include page="Check.jsp"></jsp:include>
</style>
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
						<div class="row">
							<div class="col-md-12">
								<div class="section-heading">
									<h2>Công việc ${task.name}</h2>
								</div>
								<form action="TaskList" ${disabled}>
									<button class="btn btn-outline-danger my-2 my-sm-0" type="submit"> Quay lại</button>
                                    <input type="hidden" name="departmentId" value="${departmentId}">
									<input type="hidden" name="projectId" value="${projectId}">
								</form>
								<div class="row">
									<div class= "col-md-6">
										<label for="WasBonus">Đã thưởng</label><input disabled type="number" min="0" value=${bonus} name="WasBonus" id="WasBonus">
									</div>
									<div class ="col-md-6">
										<label for="bonus">Thưởng thêm</label><input style="${style}" type="number" min="0" value="0" name="bonus" id="bonus">
									</div>
								</div>
								<button style="${style}" type="button" id="btnBonus">Thưởng</button>
								
								<form id="contact" action="" method="post">
									<div class="row">
										<div class="col-md-6">
											<fieldset>
												<label>ID</label> 
												<input disabled type="text" name="id"
                                       placeholder="ID: ${task.id}" class="form-control" required=""/>
											</fieldset>
										</div>
										<div class="col-md-6">
											<fieldset ${disabled}>
												<label>Tên</label>  
												<input type="text" name="name" value="${task.name}"
                                       placeholder="Task name*"class="form-control" required="">
											</fieldset>
										</div>
										<div class="col-md-6">
											<fieldset ${disabled}>
												<label>Thời gian bắt đầu</label> <input type="date" name="timeStart" value="${task.timeStart}"
                                       placeholder="TimeStart*"class="form-control" required="">
											</fieldset>
										</div>
										<div class="col-md-6">
											<fieldset ${disabled}>
												<label>Thời gian hoàn thành</label> <input type="date" name="estimatedTimeEnd" value="${task.estimatedTimeEnd}"
                                       placeholder="EstimatedTimeEnd*" class="form-control" required="">
											</fieldset>
										</div>
										<div class="col-md-6">
											<fieldset >
												<label>Hệ số</label> 
												<input ${disabled} type="text" name="coefficient" value="${task.coefficient}"
                                       placeholder="Coefficient*" class="form-control" required="">
											</fieldset>
										</div>
										<div class="col-md-6">
											<fieldset ${disabled}>
												<label>Mã nhân viên</label>
												<select name="employeeId1" id = "employeeId1" required="">
													<c:forEach items="${employeeList}" var="entry">
														<option value="${entry.id}">${entry.name}</option>
													</c:forEach>
												</select>
											</fieldset>
										</div>
										<div class="col-md-6">
											<fieldset>
												<div class="radio-item">
													<label>Trạng thái : </label>
													<input type="checkbox" ${completed[0]}
														   id="true"><label
														for="true">HT</label>
													<input type="checkbox" id="false"${completed[1]}>
													<label for="false">Chưa HT</label>
												</div>
											</fieldset>
										</div>
										<div class="col-md-6">
											<fieldset>
												<label>LinkCheckTask</label>  
												<input type="" name="linkCheckTask" value="${task.linkCheckTask}"
                                    placeholder="LinkCheckTask*" class="form-control" required="">
											</fieldset>
										</div>
										<div class="col-md-6">
											<fieldset ${disabled}>
												<div class="stars">
													<form action="">
													<div>
														<label>Thái độ</label></div>
														<div>
															<input class="star star-5" id="star-5" type="radio" value="5"
																   name="star" ${attitude[4]} onclick="setAttitude(this.value)"/> <label class="star star-5" for="star-5"></label>
															<input class="star star-4" id="star-4" type="radio" value="4"
																   name="star" ${attitude[3]} onclick="setAttitude(this.value)"/> <label class="star star-4" for="star-4"></label>
															<input class="star star-3" id="star-3" type="radio" value="3"
																   name="star" ${attitude[2]}  onclick="setAttitude(this.value)"/> <label class="star star-3" for="star-3"></label>
															<input class="star star-2" id="star-2" type="radio" value="2"
																   name="star" ${attitude[1]} onclick="setAttitude(this.value)"/> <label class="star star-2" for="star-2"></label>
															<input class="star star-1" id="star-1" type="radio" value="1"
																   name="star" ${attitude[0]} onclick="setAttitude(this.value)"/> <label class="star star-1" for="star-1"></label>
														</div>
													</form>
												</div>
											</fieldset>
										</div>
										
										<div class="col-12">

											<label for="demo-message">Ghi chú</label><input name="note" id="demo-message" value="${task.note}"
																					 placeholder="Enter your message" rows="6">
										</div>
										<input type="hidden" name="id" value= "${task.id}" >
										<div class="col-12">
											<input type="hidden" name="completed" id="completed" value="${task.completed}">
										</div>
										<div class="col-md-12" >
											<button type="submit" id="form-submit" class="button">Cập nhật</button>
											<input type="hidden" id="attitude" name="attitude" value="${task.attitude}">
											<input type="hidden" name="projectId" value="${projectId}">

										</div>
									</div>

								</form>
							</div>
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
		$('#btnBonus').click(function (){
			var bonus = $('#bonus').value;
		});
		function setAttitude(value){
			document.getElementById("attitude").value = value;
		};
		$("#true").click(function() {
			var checkBoxesGirl = $('#false');
			checkBoxesGirl.prop("checked", false);
			var checkBoxesBoy = $('#true');
			checkBoxesBoy.prop("checked", true);
			document.getElementById("completed").value = 'true';
			console.log(document.getElementById("completed").value);
		});
		$("#false").click(function() {
			var checkBoxesBoy = $('#true');
			checkBoxesBoy.prop("checked", false);
			var checkBoxesGirl = $('#false');
			checkBoxesGirl.prop("checked", true);
			document.getElementById("completed").value = 'false';
			console.log(document.getElementById("completed").value);

		});
		$(document).ready(function (){
			console.log($("#completed").value);
		})
		/*function changeDepartment(){
			var departmentId=$("#departmentId").val();
			console.log(departmentId)
			$.ajax({
				type: 'POST',
				data: {
					departmentId: departmentId
				},
				dataType: 'json',
				url: 'DepartmentSelection',
				success: function(res) {
					var html = "";
					$.each(res, function (key, value) {
						html += "<option value = "+value.id+">"+value.name+"</option>";
					})

					$("#employeeId1").html(html);
				}
			})
		}*/
	</script>
</body>

</html>