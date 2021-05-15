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
                    <form action="TaskList">
                        <button class="btn btn-outline-danger my-2 my-sm-0" type="submit"> Quay lại</button>
                        <input type="hidden" name="projectId" value="${projectId}">
                        <input type="hidden" name="departmentId" value="${departmentId}">
                    </form>
                  <div class="col-md-12">
                    <div class="section-heading">
                      <h2>Thêm công việc</h2>
                    </div>
                    <form id="contact" action="" method="post">
                      <div class="row">
                        <div class="col-md-6">	
                          <fieldset>
                          	<label>ID công việc</label>
								<input disabled type="text" name="id"
										placeholder="ID: ${newTaskId}" class="contact-input" />
                          </fieldset>
                        </div>
                        <div class="col-md-6">
                          <fieldset>
                          	<label>Tên công việc</label>
								<input type="text" name="name"
										placeholder="Task name*"  class="form-control" required>
                          </fieldset>
                        </div>
                        <div class="col-md-6">
                          <fieldset>
                          	<label>Thời gian bắt đầu</label>
								<input type="date" name="timeStart"
										placeholder="TimeStart*"class="form-control" id="timeStart" onchange="checkTime()" required>
                          </fieldset>
                        </div>
                        <div class="col-md-6">
                          <fieldset>
                          	<label>Thời gian hoàn thành</label>
								<input type="date" name="estimatedTimeEnd"
										placeholder="EstimatedTimeEnd*" class="form-control" id="estimatedTimeEnd" onclick="checkTime()" required>
                          </fieldset>
                        </div>
                        <div class="col-md-6">
                          <fieldset>
                          	<label>Hệ số</label>
								<input type="number" min="0" max="1" step="0.01" name="coefficient"
										placeholder="Coefficient*"  class="form-control" required>
                          </fieldset>
                        </div>
                        <div class="col-md-6">
                          <fieldset>
                          	<label>Mã nhân viên</label>
                              <select id="employeeId" name="employeeId">
                                  <c:forEach items="${employeeList}" var="entry">
                                      <option value="${entry.id}">${entry.name}</option>
                                  </c:forEach>
                              </select>
                          </fieldset>
                        </div>
                        
                        <div class="col-12">
                          <input name="note" id="demo-message" placeholder="Enter your message" rows="6">
                        </div>
                        <input type="hidden" name="id" value= ${newTaskId} >
                        <div class="col-md-12">
                          <button type="submit" id="form-submit" class="button">Thêm công việc</button>
                        </div>
                        <div><p style="color: red;">${error}</p></div>
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
    function checkTime(){
        var timeStart = document.getElementById("timeStart").value;
        document.getElementById("estimatedTimeEnd").min = timeStart;
    }
</script>
</body>

</html>
