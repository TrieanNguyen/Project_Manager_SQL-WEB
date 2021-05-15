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
<div id="wrapper">
    <div id="main">
        <div class="inner">
            <jsp:include page="_Header.jsp"></jsp:include>
            <section class="forms">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="section-heading">
                                <h2>Đổi mật khẩu</h2>
                            </div>
                            <form id="contact" action="" method="post">
                                <div class="row">
                                    <div class="col-md-6">
                                        <fieldset>
                                            <label>Nhập lại mật khẩu hiện tại</label> <input
                                                name="password" type="password" class="form-control"
                                                id="password" placeholder="Your password..." required="">
                                        </fieldset>
                                    </div>
                                    <div class="col-md-6">
                                        <fieldset>
                                            <label>Mật khẩu mới</label> <input name="newPassword"
                                                                               type="password" class="form-control" id="newPassword"
                                                                               placeholder="Your new password ..." required="">
                                        </fieldset>
                                    </div>
                                    <div class="col-md-6">
                                        <fieldset>
                                            <label>Nhập lại mật khẩu mới</label> <input
                                                name="reNewPassword" type="password" class="form-control"
                                                id="reNewPassword" placeholder="Your new password again..."
                                                required="">
                                        </fieldset>
                                        <div class="col-md-12">
                                            <button type="submit" id="form-submit" class="button">Sửa
                                                thông tin</button>
                                        </div>
                                        <div>
                                            <p style="color: red;">${error}</p>
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
</body>