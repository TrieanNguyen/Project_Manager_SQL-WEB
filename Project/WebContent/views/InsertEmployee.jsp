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
				<!-- Tables -->
				<section class="forms">
              <div class="container-fluid">
                <div class="row">
                  <div class="col-md-12">
                      <div class="section-heading">
                          <h2>Thêm nhân viên</h2>
                      </div>
                      <form id="contact" action="InsertUser" method="get">
                          <div class="row">
                              <div class="col-md-6">
                                  <fieldset>
                                      <label>ID</label>
                                      <input disabled  type="text"  id="w3lName"
                                             placeholder="ID: ${newEmployeeId}"class="contact-input" />
                                      <input type="hidden" name="id" value="${newEmployeeId}">
                                  </fieldset>
                              </div>
                              <div class="col-md-6">
                                  <fieldset>
                                      <label>Tên</label>
                                      <input type="text" name="name"
                                             placeholder="Name*"class="form-control" required="">
                                  </fieldset>
                              </div>
                              <div class="col-md-6">
                                  <fieldset>
                                      <div class="radio-item">
                                          <label>Giới tính : </label>
                                          <input type="checkbox" checked
                                                 id="boy" value="nam" ><label
                                              for="boy">Nam</label>
                                          <input type="checkbox" id="girl" value="nữ" ${sex[1]}>
                                          <label for="girl">Nữ </label>
                                      </div>
                                  </fieldset>
                              </div>
                              <div class="col-md-6">
                                  <fieldset>
                                      <label>Ngày tháng năm sinh</label>
                                      <input type="date" name="birthDate" id="birthDate"
                                             placeholder="BirthDate*" class="form-control" required="">
                                  </fieldset>
                              </div>
                              <div class="col-md-6">
                                  <fieldset>
                                      <label>Số điện thoại</label>
                                      <input type="text" name="phone"
                                             placeholder="Phone*" class="form-control" required="">
                                  </fieldset>
                              </div>
                              <div class="col-md-6">
                                  <fieldset>
                                      <label>Email</label>
                                      <input type="email" name="email"
                                             placeholder="Email*" class="form-control" required="">
                                  </fieldset>
                              </div>
                              <div class="col-md-6">
                                  <fieldset>
                                      <label>Địa chỉ</label>
                                      <input type="text" name="address"
                                             placeholder="Address*" class="form-control" required="">
                                  </fieldset>
                              </div>
                              <div class="col-md-6">
                                  <fieldset>
                                      <label>Ngày bắt đầu</label>
                                      <input type="date" name="timeStart"
                                             placeholder="StartDate*" class="form-control" required="">
                                  </fieldset>
                              </div>
                              <div class="col-md-6">
                                  <fieldset>
                                      <label>Chức vụ</label>
                                      <select name="position">
                                          <option value="Nhân viên">Nhân viên</option>
                                          <option value="Thư ký">Thư ký</option>
                                          <option value="Trưởng phòng">Trưởng phòng</option>
                                      </select>
                                  </fieldset>
                              </div>
                              <div class="col-md-6">
                                  <fieldset>
                                      <label>Trình độ học vấn</label>
<%--                                      <input type="text" name="degree"
                                             placeholder="Degree*"class="form-control" required="">--%>
                                      <select name="degree">
                                          <option value="Đại học">Đại học</option>
                                          <option value="Thạc sĩ">Thạc sĩ</option>
                                          <option value="Tiến sĩ">Tiến sĩ</option>
                                          <option value="Cấp 3">Cấp 3</option>                                          
                                          <option value="Trung cấp">Trung cấp</option>
                                      </select>
                                  </fieldset>
                              </div>
                              <div class="col-md-6">
                                  <fieldset>
                                      <label>Mã phòng ban</label>
                                      <select id="departmentId" name="departmentId">
                                          <c:forEach items="${departmentList}" var="entry">
                                              <option value="${entry.id}">${entry.name}</option>
                                          </c:forEach>
                                      </select>

                                  </fieldset>
                              </div>

                              <div class="col-md-6">
                                  <fieldset>
                                      <label>Lương</label>
                                      <input type="number" min="0" name="salary"
                                             placeholder="Salary*" class="form-control" required="">
                                  </fieldset>
                              </div>
                              <div class="col-md-6">
                                  <fieldset>
                                      <label>Điểm mạnh</label>
                                      <input type="text" name="advantage"
                                             placeholder="Advantage*"class="form-control" required="">
                                  </fieldset>
                              </div>
                              <div class="col-md-6">
                                  <fieldset>
                                      <label>Điểm yếu</label>
                                      <input type="text" name="disadvantage"
                                             placeholder="Disadvantage*" class="form-control" required="">
                                  </fieldset>
                              </div>
                              <div class="col-md-6">
                                  <fieldset>
                                      <label>Ngoại ngữ</label>
                                      <input type="text" name="foreignLanguage"
                                             placeholder="ForeignLanguage*" class="form-control" required="">
                                  </fieldset>
                              </div>
                              <div class="col-md-6">
                                  <fieldset>
                                      <label>Ghi chú</label>
                                      <input name="note" id="demo-message" placeholder="Enter your message" rows="6" >
                                  </fieldset>
                              </div>
                              <div class="col-12">
                                  <button type="submit" id="form-submit" class="button">Thêm nhân viên</button>
                              </div>
                              <div class="col-12">
                                  <input type="hidden" name="sex" id="sex" value="nam">
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
				<jsp:include page="_Menu.jsp"></jsp:include>
				<jsp:include page="_Footer.jsp"></jsp:include>
			</div>
		</div>		
	</div>
    <script>
        $("#birthDate").click(function (){
            var today = new Date();
            var dd = today.getDate();
            var mm = today.getMonth()+1;
            var yyyy = today.getFullYear() - 18;
            if(dd<10){
                dd='0'+dd
            }
            if(mm<10){
                mm='0'+mm
            }
            today = yyyy+'-'+mm+'-'+dd;
            this.max = today;
            /* console.log(this.max); */
/*             var timeStart = document.getElementById("timeStart").value; */
            document.getElementById("birthDate").max = today;
        });
        $("#boy").click(function() {
            var checkBoxesGirl = $('#girl');
            checkBoxesGirl.prop("checked", false);
            var checkBoxesBoy = $('#boy');
            checkBoxesBoy.prop("checked", true);
            document.getElementById("sex").value= "nam";
        });
        $("#girl").click(function() {
            var checkBoxesBoy = $('#boy');
            checkBoxesBoy.prop("checked", false);
            var checkBoxesGirl = $('#girl');
            checkBoxesGirl.prop("checked", true);
            document.getElementById("sex").value= "nữ";
        });
        function readURL(input) {
            if (input.files && input.files[0]) {

                var reader = new FileReader();

                reader.onload = function(e) {
                    $('.file-upload-image').attr('src', e.target.result);
                };

                reader.readAsDataURL(input.files[0]);
            }
        };
        function upLoadContract(){
            var files=$('#file')[0].files;

            if(files.length==0){

            }else
            {

                console.log(files);
                console.log($('#dinh-kem-tep-chinh-sua')[0]);
                console.log($('#dinh-kem-tep-chinh-sua'));

                $.ajax({
                    url: 'UploadFileControllers?type=portrait-&id='+${newEmployeeId},
                    type: 'POST',
                    data: new FormData($('#dinh-kem-tep-chinh-sua')[0]),
                    processData: false,
                    contentType: false
                }).done(function (result) {
                    console.log(result);
                    $("#re-review-tep").attr({'src': "./UploadFile/" + result});
                    $("#fileName").val(result);
                });
            }
        };
    </script>

</body>

</html>
