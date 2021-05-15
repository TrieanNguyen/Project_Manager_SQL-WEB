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
                      <form action="ProjectList">
                          <button class="btn btn-outline-danger my-2 my-sm-0" type="submit"> Quay lại</button>
                      </form>
                      <ul class="nav nav-tabs">
                          <li class="nav-item">
                              <a class="nav-link active"  data-toggle="tab" href="#project-info">Dự án</a>
                          </li>
                          <li class="nav-item">
                              <a class="nav-link" data-toggle="tab" href="#project-contract">Hợp đồng</a>
                          </li>
                      </ul>
                      <div class="tab-content">
                          <div class="tab-pane container active" id="project-info">
                              <div>
                                  <form id="contact" action="" method="post">
                                      <div class="row">
                                          <div id ="info" class="col-md-12">
                                              <c:if test="${hasChange == true}">
                                                  <div class="alert alert-success" >
                                                      <strong>${info}</strong>
                                                  </div>
                                              </c:if>
                                          </div>
                                          <div class="col-md-6">
                                              <fieldset>
                                                  <label>ID</label>
                                                  <input disabled type="text" name="id" value="${project.id}" placeholder="ID" class="form-control" />
                                              </fieldset>
                                          </div>
                                          <div class="col-md-6">
                                              <fieldset>
                                                  <label>Tên dự án</label>
                                                  <input type="text" value="${project.name}" name="name"
                                                         placeholder="Project name*" class="form-control">
                                              </fieldset>
                                          </div>
                                          <div class="col-md-6">
                                              <fieldset>
                                                  <label>Trưởng dự án</label>
                                                  <%--<input type="text" value="${project.leader}" name="leader"
                                                              placeholder="Leader*" class="form-control">--%>
                                                  <select id="leaderId" name="leader">
                                                      <c:forEach items="${idEmployee}" var="entry">
                                                          <option value="${entry.id}">${entry.name}</option>
                                                      </c:forEach>
                                                  </select>
                                              </fieldset>
                                          </div>
                                          <div class="col-md-6">
                                              <fieldset>
                                                  <label>Thời gian bắt đầu</label>
                                                  <input type="date" value="${project.timeStart}" name="timeStart"
                                                         placeholder="TimeStart*" class="form-control">
                                              </fieldset>
                                          </div>
                                          <div class="col-md-6">
                                              <fieldset>
                                                  <label>Thời gian hoàn thành</label>
                                                  <input type="date" value="${project.estimatedTimeEnd}" name="estimatedTimeEnd"
                                                         placeholder="EstimatedTimeEnd*" class="form-control">
                                              </fieldset>
                                          </div>
                                          <div class="col-md-6">
                                              <fieldset>
                                                  <label>Mã phòng ban</label>
                                                  <%--<input type="text" value="${project.departmentId}" name="departmentId"
                                                              placeholder="DepartmentID*" class="form-control">--%>
                                                  <select name="departmentId" onchange="changeDepartment()" id = "departmentId">
                                                      <c:forEach items="${idDepartment}" var="entry">
                                                          <option value="${entry.id}">${entry.name}</option>
                                                      </c:forEach>
                                                  </select>
                                              </fieldset>
                                          </div>
                                          <div class="col-md-6">
                                              <fieldset>
                                                  <label>Tổng tiền dự án</label>
                                                  <input type="number" min="0" value="${project.totalRevenue}" name="totalRevenue"
                                                         placeholder="TotalRevenue*" class="form-control">
                                              </fieldset>
                                          </div>
                                          <div class="col-md-6">
                                              <fieldset>

                                                  <div class="stars">
                                                      <form action="">
                                                          <div>
                                                              <label>Thái độ trưởng dự án</label></div>
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
                                              <input name="note" value="${project.note}" id="demo-message" placeholder="Enter your message" rows="6"/>
                                          </div>
                                          <div class="col-12">
                                              <input type="hidden" id="attitude" name="attitudeLeader" value="${project.attitudeLeader}"/>
                                          </div>

                                          <div class="col-md-12">
                                              <button type="submit" id="form-submit" class="button">Lưu</button>
                                          </div>

                                      </div>

                                  </form>
                              </div>
                          </div>

                          <div class="tab-pane container" id="project-contract">
                              <div id ="infoUpload" class="col-md-12">
                              </div>
                              <div class="col-12">
                              	<img class = "file-upload-image" src="./UploadFile/${project.contract}">
                                  <form id="dinh-kem-tep-chinh-sua" class="form-horizontal" enctype = "multipart/form-data">
                                      <div class="addfile">
                                          <div class="space-fileup">
                                              <label class="input-label" for="file">
                                                  <i class="fa fa-upload"></i>
                                                  <span class="label-span">Tải lên hợp đồng mới</span></label>
                                              <input onchange="readURL(this)" type="file" name="filepost" id="file" accept="image/*">
                                          </div>
                                      </div>
                                      <div id="upFile">
                                          <%--<button type="button" onclick="upLoadContract()">
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
    function setAttitude(value){
        document.getElementById("attitude").value = value;
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

       if(files.length==0){

       }else
       {

           console.log(files);
           console.log($('#dinh-kem-tep-chinh-sua')[0]);
           console.log($('#dinh-kem-tep-chinh-sua'));

           $.ajax({
               url: 'UploadFileControllers?type=contract&id='+${project.id},
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
/*
   $(document).ready(function (){
       $("#file").on("change",function(e){

       })
   })*/

</script>
</body>

</html>
