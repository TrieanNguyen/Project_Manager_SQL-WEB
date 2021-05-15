<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="views/assets/css/css.css" rel="stylesheet">
    <title>Quản lý công ty phần mềm</title>
    <!-- Bootstrap core CSS -->
    <link href="views/vendor/bootstrap/css/bootstrap.min.css"
          rel="stylesheet">
    <link rel="stylesheet" href="views/assets/css/fontawesome.css">
    <link rel="stylesheet" href="views/assets/css/templatemo-style.css">
    <link rel="stylesheet" href="views/assets/css/owl.css">
</head>
<body>
<nav id="menu">
    <ul>
        <li><a href="Home">Trang chủ</a></li>
<%--        <li><a href="EditDepartment">Xem phòng ban</a></li>--%>
        <li><a href="ProjectList">Quản lý dự án</a></li>
        <li><span class="opener">Tài khoản</span>
            <ul>
                <li><a href="UserInfo">Xem thông tin</a></li>
                <li><a href="ChangePassWord">Đổi mật khẩu</a></li>
                <li><a href="Logout">Đăng xuất</a></li>
            </ul>
        </li>
    </ul>
</nav>
</body>
</html>