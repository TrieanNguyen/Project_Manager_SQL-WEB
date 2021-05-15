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

<style type="text/css">
		<jsp:include page="Check.jsp"></jsp:include>
</style>
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
								<form action="EmployeeList" id="back">
									<button class="btn btn-outline-danger my-2 my-sm-0" type="submit"> Quay lại</button>
								</form>
								<ul class="nav nav-tabs">
									<li class="nav-item">
										<a class="nav-link active"  data-toggle="tab" href="#employee-info">Sửa nhân viên</a>
									</li>
									<li class="nav-item">
										<a class="nav-link" data-toggle="tab" href="#employee-portrait">Chân dung</a>
									</li>
								</ul>
								<div class="tab-content">
									<div class="tab-pane container active" id="employee-info">
										<form id="contact" action="" method="post">
											<div class="row">
												<div id ="info" class="col-md-12">
													<c:if test="${success == true}">
														<div class="alert alert-success" >
															<strong>${info}</strong>
														</div>
													</c:if>
												</div>
												<div class="col-md-6">
													<fieldset>
														<label>Họ tên</label> <input name="name"
																					 type="text" class="form-control" id="name"
																					 value="${employee.name}" placeholder="Your name..."
																					 required="">
													</fieldset>
												</div>
												<div class="col-md-6">
													<fieldset>
														<div class="radio-item">
															<label>Giới tính : </label>
															<input type="checkbox"
																   id="boy" value="nam" ${sex[0]} ><label
																for="boy">Nam</label>
															<input type="checkbox" id="girl" value="nữ" ${sex[1]} >
															<label for="girl">Nữ </label>
														</div>
													</fieldset>
												</div>
												<div class="col-md-6">
													<fieldset>
														<label>Số điện thoại</label> <input name="phone"
																							type="text" class="form-control" id="phone"
																							value="${employee.phone}" placeholder="Your phone..."
																							required="">
													</fieldset>
												</div>
												<div class="col-md-6">
													<fieldset>
														<label>Ngày tháng năm sinh</label> <input
															name="birthDate" type="date" class="form-control"
															id="birthdate" value="${employee.birthDate}"
															placeholder="Your birthdate..." required="">
													</fieldset>
												</div>
												<div class="col-md-6">
													<fieldset>
														<label>Email</label> <input name="email"
																					type="email" class="form-control" id="email"
																					value="${employee.email}" placeholder="Your email..."
																					required="">
													</fieldset>
												</div>
												<div class="col-md-6">
													<fieldset>
														<label>Địa chỉ</label> <input name="address"
																					  type="text" class="form-control" id="address"
																					  value="${employee.address}" placeholder="Your address..."
																					  required="">
													</fieldset>
												</div>
                                                <div class="col-md-6">
                                                    <fieldset>
                                                        <label>Chức vụ</label>
                                                        <select name="position" ${limitEdit}>
                                                            <option value="Nhân viên" ${position[0]} >Nhân viên</option>
                                                            <option value="Thư ký" ${position[1]}>Thư ký</option>
                                                            <option value="Trưởng phòng" ${position[2]}>Trưởng phòng</option>
                                                        </select>
                                                    </fieldset>
                                                </div>
												<div class="col-md-6">
													<fieldset>
														<label>Trình độ học vấn</label>
														<%--                                      <input type="text" name="degree"
                                                                                                     placeholder="Degree*"class="form-control" required="">--%>
														<select name="degree" ${limitEdit}>
															<option value="Đại học" ${degree[0]}>Đại học</option>
															<option value="Thạc sĩ" ${degree[1]}>Thạc sĩ</option>
															<option value="Tiến sĩ" ${degree[2]}>Tiến sĩ</option>
															<option value="Cấp 3" ${degree[3]}>Cấp 3</option>
															<option value="Trung cấp" ${degree[4]}>Trung cấp</option>
														</select>
													</fieldset>
												</div>

												<div class="col-md-6">
													<fieldset>
														<label>Mã phòng ban</label>
														<select id="departmentId" name="departmentId" ${limitEdit}>
															<c:forEach items="${departmentList}" var="entry">
																<option value="${entry.id}">${entry.name}</option>
															</c:forEach>
														</select>
													</fieldset>
												</div>
												<div class="col-md-6">
											<fieldset ${limitEdit}>
												
												<div class="stars">
													<form action="">
													<div >
														<label>Thái độ</label></div>
														<div >
														<input class="star star-5" id="star-5" type="radio" value="5"
															name="star" onclick="setAttitude(this.value)" ${attitude[4]} /> <label class="star star-5" for="star-5"></label>
														<input class="star star-4" id="star-4" type="radio" value="4"
															name="star" onclick="setAttitude(this.value)" ${attitude[3]} /> <label class="star star-4" for="star-4"></label>
														<input class="star star-3" id="star-3" type="radio" value="3"
															name="star" onclick="setAttitude(this.value)" ${attitude[2]} /> <label class="star star-3" for="star-3"></label>
														<input class="star star-2" id="star-2" type="radio" value="2"
															name="star" onclick="setAttitude(this.value)" ${attitude[1]}/> <label class="star star-2" for="star-2"></label>
														<input class="star star-1" id="star-1" type="radio" value="1"
															name="star" onclick="setAttitude(this.value)" ${attitude[0]}/> <label class="star star-1" for="star-1"></label>
														</div>
													</form>
												</div>
											</fieldset>
										</div>
												<div class="col-md-6">
													<fieldset>
														<label>Điểm mạnh</label> <input name="advantage"
																						type="text" class="form-control" id="advantage"
																						value="${employee.advantage}"
																						placeholder="Your advantage..." required="">
													</fieldset>
												</div>
												<div class="col-md-6">
													<fieldset>
														<label>Điểm yếu</label> <input name="disadvantage"
																					   type="text" class="form-control" id="disadvantage"
																					   value="${employee.disadvantage}"
																					   placeholder="Your disadvantage..." required="">
													</fieldset>
												</div>
												<div class="col-md-6">
													<fieldset>
														<label>Ngoại ngữ</label> <input
															name="foreignLanguage" type="text" class="form-control"
															id="foreignLanguage" value="${employee.foreignLanguage}"
															placeholder="Your foreignLanguage ..." required="">
													</fieldset>
												</div>
												<div class="col-md-6">
													<fieldset>
														<label>Lương</label>
														<input name="salary" type="number" min="0" value="${employee.salary}"
															   placeholder="Enter your message" required ${limitEdit}>
													</fieldset>
												</div>
												<div class="col-12">
													<fieldset>
														<label>Ghi chú</label>
														<input name="note" id="demo-message" value="${employee.note}"
															   placeholder="Enter your message" required ${limitEdit}>
													</fieldset>
												</div>

												<div class="col-md-12">
													<fieldset>
														<button type="submit" id="form-submit" class="button">Lưu
															thông tin</button>
														<input type="hidden" id="attitude" value="${employee.attitude}" name="attitude">
													</fieldset>
												</div>
												<div class="col-md-12">
													<input type="hidden" name="sex" id="sex" value="${employee.sex}">
												</div>
											</div>
										</form>
									</div>
									<div class="tab-pane container " id="employee-portrait">
										<div id ="infoUpload" class="col-md-12">
										</div>
										<div class="col-12">
											<img class = "file-upload-image" src="./UploadFile/${employee.portrait}">
											<form id="dinh-kem-tep-chinh-sua" class="form-horizontal" enctype = "multipart/form-data">
												<div class="addfile">
													<div class="space-fileup">
														<label class="input-label" for="file">
															<i class="fa fa-upload"></i>
															<span class="label-span">Tải lên tệp</span></label>
														<input onchange="readURL(this)" type="file" name="filepost" id="file" accept="image/*">
													</div>
												</div>
												<div id="upFile">
<%--													<button type="button" onclick="upLoadPortrait()">
														Tải tệp
													</button>--%>
												</div>
											</form>
											
										</div>
									</div>
								</div>

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
	</div>
	<script>
		$(document).ready(function (){
			/*changeGroup("công ty");
			$('#tableInfo').DataTable( {
				"pagingType": "full_numbers"
			} );*/
			var check = "${limitEdit}";
			console.log(check);
			if(check === "disabled"){
				$('#back').attr('action','Home');
			}
		})
		function setAttitude(value){
			document.getElementById("attitude").value = value;
		}
		function readURL(input) {
			if (input.files && input.files[0]) {

				var reader = new FileReader();

				reader.onload = function(e) {
					$('.file-upload-image').attr('src', e.target.result);
				};

				reader.readAsDataURL(input.files[0]);
				var html=`<button type="button" onclick="upLoadPortrait()">
                                              Tải tệp
                                          </button>`;
				$("#upFile").html(html);
			}
		}
		function upLoadPortrait(){
			var files=$('#file')[0].files;

			if(files.length==0){

			}else
			{

				console.log(files);
				console.log($('#dinh-kem-tep-chinh-sua')[0]);
				console.log($('#dinh-kem-tep-chinh-sua'));

				$.ajax({
					url: 'UploadFileControllers?type=portrait-&id='+${employee.id},
					type: 'POST',
					data: new FormData($('#dinh-kem-tep-chinh-sua')[0]),
					processData: false,
					contentType: false
				}).done(function (result) {
					console.log(result);
					$("#re-review-tep").attr({'src': "./UploadFile/" + result});
					$("#fileName").val(result);
					var html=`<div class="alert alert-success" >
                                  <strong>Chân dung của nhân viên được cập nhật thành công</strong>
                              </div>`
					var backAction = $('#back').attr('action');
					if(backAction==='Home')
						html=`<div class="alert alert-success" >
                                  <strong>Chân dung của bạn được cập nhật thành công</strong>
                              </div>`
					$("#infoUpload").html(html);
				});
			}
		};
		$("#boy").click(function() {
			var checkBoxesGirl = $('#girl');
			checkBoxesGirl.prop("checked", false);
			var checkBoxesBoy = $('#boy');
			checkBoxesBoy.prop("checked", true);
			/*$('#sex').value = 'nam';*/
			document.getElementById("sex").value = "nam";
		});
		$("#girl").click(function() {
			var checkBoxesBoy = $('#boy');
			checkBoxesBoy.prop("checked", false);
			var checkBoxesGirl = $('#girl');
			checkBoxesGirl.prop("checked", true);
			/*$('#sex').value = 'nữ';*/
			document.getElementById("sex").value = "nữ";
		});
	</script>

</body>
</html>