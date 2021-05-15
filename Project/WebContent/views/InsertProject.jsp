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
                      <ul class="nav nav-tabs">
                          <form action="ProjectList">
                              <button class="btn btn-outline-danger my-2 my-sm-0" type="submit"> Quay lại</button>
                          </form>
                          <li class="nav-item">
                              <a class="nav-link active"  data-toggle="tab" href="#project-info">Thêm dự án</a>
                          </li>
                          <li class="nav-item">
                              <a class="nav-link" data-toggle="tab" href="#project-contract">Hợp đồng</a>
                          </li>
                      </ul>
                      <div class="tab-content">
                          <div class="tab-pane container active" id="project-info">
                              <c:if test="${success == true}">
                                  <div class="alert alert-success">
                                      <strong>Thêm dự án thành công, bạn vui lòng thêm hợp đồng tab kế bên</strong>
                                  </div>
                              </c:if>
                              <form id="contact" action="" method="post">
                                  <div class="row">
                                      <div class="col-md-6">
                                          <fieldset>
                                              <label>ID</label>
                                              <input disabled type="text" value="${newProjectId}" class="contact-input" />
                                          </fieldset>
                                      </div>
                                      <div class="col-md-6">
                                          <fieldset>
                                              <label>Tên dự án</label>
                                              <input type="text" name="name" class="form-control" required="">
                                          </fieldset>
                                      </div>
                                      <div class="col-md-6">
                                          <fieldset>
                                              <label>Thời gian bắt đầu</label>
                                              <input type="Date" name="timeStart"class="form-control" required="" id="timeStart" onchange="checkTime()">
                                          </fieldset>
                                      </div>
                                      <div class="col-md-6">
                                          <fieldset >
                                              <label>Thời gian dự kiến kết thúc</label>
                                              <input type="Date" name="estimatedTimeEnd"class="form-control" required="" id="estimatedTimeEnd"onclick="checkTime()">
                                          </fieldset>
                                      </div>
                                      <div class="col-md-6">
                                          <fieldset>
                                              <label>Tên phòng ban</label>
                                              <select id="departmentId" name="departmentId" onclick="changeDepartment()">
                                                  <c:forEach items="${departmentList}" var="entry">
                                                      <option value="${entry.id}">${entry.name}</option>
                                                  </c:forEach>
                                              </select>
                                          </fieldset>
                                      </div>
                                      <div class="col-md-6">
                                          <fieldset>
                                              <label>Leader</label>
                                              <select id="leaderId" name="leader">
                                                  <c:forEach items="${employeeList}" var="entry">
                                                      <option value="${entry.id}">${entry.name}</option>
                                                  </c:forEach>
                                              </select>
                                          </fieldset>
                                      </div>
                                      <div class="col-md-6">
                                          <fieldset>
                                              <label>Giá trị dự án(USD)</label>
                                              <input type="number" name="totalRevenue" class="form-control">
                                          </fieldset>
                                      </div>
                                      <div class="col-12">
                                          <input name="note" id="demo-message" placeholder="Enter your message" rows="6">
                                      </div>
                                      <div class="col-md-12">
                                          <button type="submit" id="form-submit" class="button">Thêm dự án</button>
                                      </div>
                                  </div>
                              </form>
                          </div>
                          <div class="tab-pane container" id="project-contract" >
                              <div class="col-12">
                                  <div id="infoUpload">
<%--                                      <div class="alert alert-success" >

                                      </div>--%>
                                  </div>
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
                                          <%--<button type="button" onclick="upLoadContract()">
                                              Tải tệp
                                          </button>--%>
                                      </div>

                                  </form>
                                  <img class = "file-upload-image" src="./UploadFile/"+${project.contract}/>
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
		<div id="sidebar" class="inactive">

			<div class="inner">
				<jsp:include page="_Menu.jsp"></jsp:include>
				<jsp:include page="_Footer.jsp"></jsp:include>
			</div>
		</div>		
	</div>
    <script>
        function checkTime(){
            var timeStart = document.getElementById("timeStart").value;
            document.getElementById("estimatedTimeEnd").min = timeStart;
        }
        function readURL(input) {
            if (input.files && input.files[0]) {

                var reader = new FileReader();

                reader.onload = function(e) {
                    $('.file-upload-image').attr('src', e.target.result);
                };

                reader.readAsDataURL(input.files[0]);
                var html=`<button type="button" onclick="upLoadContract()">
                                              Tải tệp
                                          </button>`;
                $("#upFile").html(html);
            }
        }
        function upLoadContract(){
            var files=$('#file')[0].files;

            if(files.lenth==0){

            }else
            {

                console.log(files);
                console.log($('#dinh-kem-tep-chinh-sua')[0]);
                console.log($('#dinh-kem-tep-chinh-sua'));

                $.ajax({
                    url: 'UploadFileControllers?type=contract-&id='+${newProjectId},
                    type: 'POST',
                    data: new FormData($('#dinh-kem-tep-chinh-sua')[0]),
                    processData: false,
                    contentType: false
                }).done(function (result) {
                    console.log(result);
                    $("#re-review-tep").attr({'src': "./UploadFile/" + result});
                    $("#fileName").val(result);
                    var html=`<div class="alert alert-success" >
                                  <strong>Ảnh của hợp đồng được cập nhật thành công</strong>
                              </div>`
                    $("#infoUpload").html(html);
                });
            }
        }
        function changeDepartment(){
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
                        console.log(value.name);
                    })

                    $("#leaderId").html(html);
                }
            })
        }
    </script>
</body>

</html>
