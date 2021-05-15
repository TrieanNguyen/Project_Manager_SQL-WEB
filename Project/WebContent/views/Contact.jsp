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
	<jsp:include page="Check.jsp"></jsp:include>

</head>
<body class="is-preload">

<div id="wrapper">
	<div id="main">
		<div class="inner">
			<jsp:include page="_Header.jsp"></jsp:include>
			<section class="forms">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<div class="section-heading">
								<h2>Thông tin nhân viên</h2>
							</div>
							<form id="contact" action="" method="post">
								<div class="row">
									<div class="col-md-6">
										<fieldset>
											<label>Họ tên</label> <input disabled name="name"
																		 type="text" class="form-control" id="name"
																		 value="${employee.name}" placeholder="Your name..."
																		 required="">
										</fieldset>
									</div>
									<div class="col-md-6">
										<fieldset>
											<div class="radio-item">
												<label>Giới tính : </label>
												<input type="checkbox" ${sex[0]}
													   id="boy" value="nam" ><label
													for="boy">Nam</label>
												<input type="checkbox" id="girl" value="nữ" ${sex[1]} >
												<label for="girl">Nữ </label>
											</div>
										</fieldset>
									</div>
									<div class="col-md-6">
										<fieldset>
											<label>Số điện thoại</label> <input disabled name="phone"
																				type="text" class="form-control" id="phone"
																				value="${employee.phone}" placeholder="Your phone..."
																				required="">
										</fieldset>
									</div>
									<div class="col-md-6">
										<fieldset>
											<label>Ngày tháng năm sinh</label> <input disabled
																					  name="birthdate" type="date" class="form-control"
																					  id="birthdate" value="${employee.birthDate}"
																					  placeholder="Your birthdate..." required="">
										</fieldset>
									</div>
									<div class="col-md-6">
										<fieldset>
											<label>Email</label> <input disabled name="email"
																		type="email" class="form-control" id="email"
																		value="${employee.email}" placeholder="Your email..."
																		required="">
										</fieldset>
									</div>
									<div class="col-md-6">
										<fieldset>
											<label>Địa chỉ</label> <input disabled name="address"
																		  type="text" class="form-control" id="address"
																		  value="${employee.address}" placeholder="Your address..."
																		  required="">
										</fieldset>
									</div>
									<div class="col-md-6">
										<fieldset>
											<label>Chức vụ</label> <input disabled name="position"
																		  type="text" class="form-control" id="position"
																		  value="${employee.position}" placeholder="Your position..."
																		  required="">
										</fieldset>
									</div>
									<div class="col-md-6">
										<fieldset>
											<label>Trình độ</label> <input disabled name="degree"
																		   type="text" class="form-control" id="degree"
																		   value="${employee.degree}" placeholder="Your degree..."
																		   required="">
										</fieldset>
									</div>

									<div class="col-md-6">
										<fieldset>
											<label>Mã phòng ban</label> <input disabled
																			   name="departmentId" type="number" class="form-control"
																			   id="departmentId" value="${employee.departmentId}"
																			   placeholder="Your departmentId..." required="">
										</fieldset>
									</div>
									<div class="col-md-6">
										<fieldset>

											<div class="stars">
												<form action="">
													<div>
														<label>Thái độ</label>
													</div>
													<div>
														<input class="star star-5" id="star-5" type="radio"
															   name="star" /> <label class="star star-5" for="star-5"></label>
														<input class="star star-4" id="star-4" type="radio"
															   name="star" /> <label class="star star-4" for="star-4"></label>
														<input class="star star-3" id="star-3" type="radio"
															   name="star" /> <label class="star star-3" for="star-3"></label>
														<input class="star star-2" id="star-2" type="radio"
															   name="star" /> <label class="star star-2" for="star-2"></label>
														<input class="star star-1" id="star-1" type="radio"
															   name="star" /> <label class="star star-1" for="star-1"></label>
													</div>
												</form>
											</div>
										</fieldset>
									</div>
									<div class="col-md-6">
										<fieldset>
											<label>Điểm mạnh</label> <input disabled name="advantage"
																			type="text" class="form-control" id="advantage"
																			value="${employee.advantage}"
																			placeholder="Your advantage..." required="">
										</fieldset>
									</div>
									<div class="col-md-6">
										<fieldset>
											<label>Điểm yếu</label> <input disabled name="disadvantage"
																		   type="text" class="form-control" id="disadvantage"
																		   value="${employee.disadvantage}"
																		   placeholder="Your disadvantage..." required="">
										</fieldset>
									</div>
									<div class="col-md-6">
										<fieldset>
											<label>Ngoại ngữ</label> <input disabled
																			name="foreignLanguage" type="text" class="form-control"
																			id="foreignLanguage" value="${employee.foreignLanguage}"
																			placeholder="Your foreignLanguage ..." required="">
										</fieldset>
									</div>
									<div class="col-md-6">
										<fieldset>
											<label>Chân dung</label>
											<img class = "file-upload-image" src="./UploadFile/${employee.portrait}">
										</fieldset>
									</div>
									<div class="col-12">
											<textarea name="demo-message" id="demo-message"
													  placeholder="Enter your message" rows="6"></textarea>
									</div>
									<div class="col-md-12">
										<button type="submit" id="form-submit" class="button">Sửa
											thông tin</button>
									</div>
									<div class="col-md-12">
										<input type="hidden" name="sex" id="sex" value="${employee.sex}">
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</section>
		</div>
	</div>
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
	$("#boy").click(function() {
		var checkBoxesGirl = $('#girl');
		checkBoxesGirl.prop("checked", false);
		var checkBoxesBoy = $('#boy');
		checkBoxesBoy.prop("checked", true);
		$('#sex').value = 'nam';
	});
	$("#girl").click(function() {
		var checkBoxesBoy = $('#boy');
		checkBoxesBoy.prop("checked", false);
		var checkBoxesGirl = $('#girl');
		checkBoxesGirl.prop("checked", true);
		$('#sex').value = 'nữ';
	});
</script>
</body>
</html>