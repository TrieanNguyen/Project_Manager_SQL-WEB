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
                      <h2>${department.name}</h2>
                    </div>
                      <form action="DepartmentList">
                          <button class="btn btn-outline-danger my-2 my-sm-0" type="submit"> Quay lại</button>
                      </form>
                    <form id="contact" action="" method="post">
                      <div class="row">
                        <div class="col-md-6">	
                          <fieldset>
                          	<label>ID</label>
                          	<input disabled type="text" name="id"  class="form-control" placeholder="ID:${department.id}" class="contact-input" />
                          </fieldset>
                        </div>
                        <div class="col-md-6">
                          <fieldset>
                          	<label>Phòng</label>
                          	 <input ${disabled} type="text" name="roomNumber" value="${department.roomNumber}"class="form-control">
                          </fieldset>
                        </div>
                        <div class="col-md-6">
                          <fieldset>
                          	<label>Ngân sách</label>
                          	<input ${disabled} type="number" min="0" name="budget"  value="${department.budget}" class="form-control">
                          </fieldset>
                        </div>
                        <div class="col-md-6">
                          <fieldset ${disabled}>
                          	<label>ID trưởng phòng</label>
                          	<%--<input type="text" name="leader" value="${department.getLeader()}" class="form-control">--%>
                              <select id="leaderId" name="leader">
                                  <c:forEach items="${employeeList}" var="entry">
                                      <option value="${entry.id}">${entry.name}</option>
                                  </c:forEach>
                              </select>
                          </fieldset>
                        </div>
                        <div class="col-md-12">
                          <button style="${display}"  type="submit" id="form-submit" class="button">Lưu</button>
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

</script>
</body>

</html>
