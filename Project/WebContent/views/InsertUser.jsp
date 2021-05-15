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
		function checkPassWork(){
			var form = document.forms['insertUser'];
			alert(form.passWord.value.toString().trim());
			if(form.passWord.value.toString().trim() == form.rePassWord.value.toString().trim())
			{
				return true;
			}
			else{
				return false;
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
				<!-- Tables -->
				<section class="forms">
              <div class="container-fluid">
                <div class="row">
                  <div class="col-md-12">
					  <ul class="nav nav-tabs">
						  <li class="nav-item">
							  <a class="nav-link active"  data-toggle="tab" href="#user-info">Tạo tài khoản cho nhân viên</a>
						  </li>
						  <li class="nav-item">
							  <a class="nav-link" data-toggle="tab" href="#employee-portrait">Chân dung</a>
						  </li>
					  </ul>
					  <div class="tab-content">
						  <div class="tab-pane container active" id="user-info">
							  <c:if test="${success == true}">
								  <div class="alert alert-success">
									  <strong>Thêm nhân viên thành công, bạn vui lòng thêm chân dung ở tab kế bên</strong>
								  </div>
							  </c:if>
							  <form id="contact" action="" method="post">
								  <div class="row">
									  <div class="col-md-6">
										  <fieldset>
											  <label>ID</label>
											  <input disabled type="text" placeholder="ID: ${employee.id}"
													 class="form-control" />
										  </fieldset>
									  </div>
									  <div class="col-md-6">
										  <fieldset>
											  <label>Password</label>
											  <input type="password" name="passWord"
													 placeholder="Mật khẩu*" class="form-control" required>
										  </fieldset>
									  </div>
									  <div class="col-md-6">
										  <fieldset>
											  <label>Password again</label>
											  <input type="password" name="rePassWord"
													 placeholder="Nhập lại mật khẩu*" class="form-control" required>
										  </fieldset>
									  </div>
									  <div class="col-md-12">
										  <button type="submit" id="form-submit" class="button">Lưu thông tin</button>
									  </div>
									  <input type="hidden" name="id" value="${employee.id}">
								  </div>
							  </form>
						  </div>
						  <div class="tab-pane container" id="employee-portrait">
							  <div class="col-12">
								  <form id="dinh-kem-tep-chinh-sua" class="form-horizontal" enctype = "multipart/form-data">
									  <div class="addfile">
										  <div class="space-fileup">
											  <label class="input-label" for="file">
												  <i class="fa fa-upload"></i>
												  <span class="label-span">Tải lên chân dung</span></label>
											  <input onchange="readURL(this)" type="file" name="filepost" id="file" accept="image/*">
										  </div>
									  </div>
									  <div id="upFile">
									  </div>
								  </form>
								  <img class = "file-upload-image" />
							  </div>
						  </div>
					  </div>

                  </div>
                </div>
              </div>
            </section>
			</div>
		</div>

		<!-- Sidebar -->
		<div id="sidebar">

			<div class="inner">


				<jsp:include page="_Menu.jsp"></jsp:include>
				<jsp:include page="_Footer.jsp"></jsp:include>
			</div>
		</div>		
	</div>
	<form id="goToHome" action="EmployeeList" method="get">
		<input name="hasInsert" value="true">
	</form>
	<script>
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

			if(files.lenth==0){

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
					$("#goToHome").submit();
				});
			}
		}
	</script>
</body>

</html>
