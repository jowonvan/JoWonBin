<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
html, body {
	width: 100%;
	height: 100%;
	margin: 0px;
	padding: 0px;
}

input[type=button] {
	cursor: pointer;
}

#contents {
	min-height: 100%;
	position: relative;
	margin: 0px;
	padding: 0px;
}

#section {
	width: 80%;
	margin-left: 10%;
	padding-top: 10%;
	padding-bottom: 15%;
}

#footer {
	position: absolute;
	background-color: #ffffff;
	bottom: 0;
	left: 0;
	width: 100%;
	height: 10%;
}

#footcontLeft {
	float: left;
	width: 20%;
}

#footcontRight {
	float: right;
	width: 20%;
}
table{
	border-bottom: 1px solid gray;
	text-align: center;
	padding:1%;
	width:100%;
}
</style>
<script src="https://code.jquery.com/jquery-3.2.1.min.js">
</script>
<script>
$(function(){
	if('${msg}'== "신청이 취소되었습니다."){
		alert('${msg}');
	}else if('${msg}'== "신청 취소가 실패했습니다."){
		alert('${msg}');
	}else if('${msg}'== "후원 실패했습니다."){
		alert('${msg}');
	}else if('${msg}'== "후원되었습니다."){
		alert('${msg}');
	}
});
</script>
</head>
<body>
	<div id="contents">
		<div id="header"></div>
		<div id="section">${detail } ${point} ${RequestList } ${List } ${memberlist } ${blacklist } </br> ${memberdetail }</div>
		<div id="footer">
			<hr />
			<div id="footcontLeft">
				<p>LSOT 대표 전화 : 032-111-1111</p>
				<p>LSOT 주소 : 인천 남동구 학익동</p>
			</div>
			<div id="footcontRight">
				<a href="adminlogin.jsp"> <img style="max-width: 100%;"
					src="./img/logo.png" />
				</a>
			</div>
		</div>
	</div>
</body>
<script>
Aj('header', '#header');
function Aj(url, position) {
	$.ajax({
		url : url,
		type : 'get',
		success : function(html) {
			$(position).html(html);
		},
		error : function(error) {
			console.log(error);
		}
	});
}
</script>
</html>