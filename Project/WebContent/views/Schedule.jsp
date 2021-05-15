<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Quản lý công ty phần mềm</title>

<jsp:include page="cssSchedule.jsp"></jsp:include>
<script>
	window.console = window.console || function(t) {
	};
</script>
<script>
	if (document.location.search.match(/type=embed/gi)) {
		window.parent.postMessage("resize", "*");
	}
</script>


</head>

<body>

	<div id="wrapper">
		<div id="main">
			<div class="inner">
				<jsp:include page="_Header.jsp"></jsp:include>
				<%-- 	<jsp:include page="_Header.jsp"></jsp:include>
 --%>
				<div class="content">

					<div class="calendar-container">
						<div class="calendar">
							<div class="year-header">
								<span class="left-button" id="prev"> &lang; </span> <span
									class="year" id="label"></span> <span class="right-button"
									id="next"> &rang; </span>
							</div>
							<table class="months-table">
								<tbody>
									<tr class="months-row">
										<td class="month">Jan</td>
										<td class="month">Feb</td>
										<td class="month">Mar</td>
										<td class="month">Apr</td>
										<td class="month">May</td>
										<td class="month">Jun</td>
										<td class="month">Jul</td>
										<td class="month">Aug</td>
										<td class="month">Sep</td>
										<td class="month">Oct</td>
										<td class="month">Nov</td>
										<td class="month">Dec</td>
									</tr>
								</tbody>
							</table>

							<table class="days-table">
								<td class="day">Sun</td>
								<td class="day">Mon</td>
								<td class="day">Tue</td>
								<td class="day">Wed</td>
								<td class="day">Thu</td>
								<td class="day">Fri</td>
								<td class="day">Sat</td>
							</table>
							<div class="frame">
								<table class="dates-table">
									<tbody class="tbody">
									</tbody>
								</table>
							</div>
							<button class="button" id="add-button">Add Event</button>
						</div>
					</div>
					<div class="events-container"></div>
					<div class="dialog" id="dialog">
						<h2 class="dialog-header">Add New Event</h2>
						<form class="form" id="form">
							<div class="form-container" align="center">
								<label class="form-label" id="valueFromMyButton" for="name">Event
									name</label> <input class="input" type="text" id="name" maxlength="36">
								<label class="form-label" id="valueFromMyButton" for="count">Number
									of people to invite</label> <input class="input" type="number"
									id="count" min="0" max="1000000" maxlength="7"> <input
									type="button" value="Cancel" class="button" id="cancel-button">
								<input type="button" value="OK" class="button" id="ok-button">
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>

		<!-- Dialog Box-->
		<script src="views/schedule/jquery-3.2.1.min.js"
			integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
			crossorigin="anonymous"></script>
		<script src="app.js"></script>
		<script
			src="https://cpwebassets.codepen.io/assets/common/stopExecutionOnTimeout-157cd5b220a5c80d4ff8e0e70ac069bffd87a61252088146915e8726e5d9f147.js"></script>

		<jsp:include page="Js.jsp"></jsp:include>
		<div id="sidebar" class="inactive">
			<div class="inner">
				<jsp:include page="_Menu.jsp"></jsp:include>
				<jsp:include page="_Footer.jsp"></jsp:include>
			</div>
		</div>
	</div>
</body>

</html>

