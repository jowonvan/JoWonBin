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
	min-height : 100%;
	position: relative;
	margin:0px;
	padding:0px;
}
#section {
	width: 80%;
	margin-left:10%;
	padding-top:10%;
	padding-bottom: 20%;	
	text-align: center;
	position: relative;
}  
#essay{
	width:100%;
}

#essay tr td.con{
	border-bottom: 1px solid #aaaaff;
	height: 50px;
}

#essay tr td.col{
	background-color:#082d71;
	border-bottom: 1px solid white;
	height: 50px;
	color : white;
} 

table#dd {
	width: 100%;
	border-bottom: 1px solid gray;
	border-top: 1px solid gray;
}

table#dd tr td {
	padding-bottom: 1%;
	border-bottom: dotted 1px;
}
#footer {
	position: absolute; 
	background-color : #ffffff;
	bottom: 0;
	left: 0;
	width: 100%;
	height: 10%;
}

#footcontLeft {
	float:left;
	width: 20%;
}

#footcontRight{
	float: right;
	width: 20%;
}
</style>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
$(function(){
	if('${msg}' == "삭제할 권한이 없습니다."){
		alert('${msg}');
	}else if('${msg}' == "수정할 권한이 없습니다."){
		alert('${msg}');
	}
});
</script>
</head>
<body>
	<div id="contents">
		<div id="header"></div>
		<div id="section">
			${givedetail}
			${commentList}
		</div>
		<div id="footer">
			<hr />
			<div id="footcontLeft">
				<p>LSOT 대표 전화 : 032-111-1111</p>
				<p>LSOT 주소 : 인천 남동구 학익동</p>
			</div>
			<div id="footcontRight">
				<a href="adminlogin.jsp">
					<img style="max-width: 100%;" src="./img/logo.png" />
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