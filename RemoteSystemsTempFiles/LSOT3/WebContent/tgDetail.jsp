<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	padding-bottom: 20%;
	text-align: center;
	position: relative;
}

table {
	width:100%;
}

#essay tr td.con {
	border-bottom: 1px solid #aaaaff;
	height: 50px;
}

#essay tr td.col {
	background-color: #082d71;
	border-bottom: 1px solid white;
	height: 50px;
	color : white;
}

table#dd {
	border-bottom: 1px solid gray;
	border-top: 1px solid gray;
}

table#dd tr td {
	padding-bottom: 1%;
	border-bottom: dotted 1px;
}

#requesters {
	margin-top: 5%;
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
</style>
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js">
	
</script>
<script>
	$(function() {
		if ('${msg}' == "접근 권한이 없습니다.") {
			alert('${msg}');
		} else if ('${msg}' == "수락되었습니다.") {
			alert('${msg}');
		} else if ('${msg}' == "거절되었습니다.") {
			alert('${msg}');
		} else if ('${msg}' == "신청할 수 없습니다.") {
			alert('${msg}');
		} else if ('${msg}' == "신청되었습니다.") {
			alert('${msg}');
		} else if ('${msg}' == "이미 신청되었습니다.") {
			alert('${msg}');
		} else if ('${msg}' == "작성자만 삭제할 수 있습니다.") {
			alert('${msg}');
		} else if ('${msg}' == "수정하였습니다.") {
			alert('${msg}');
		} else if ('${msg}' == "작성자만 수정할 수 있습니다.") {
			alert('${msg}');
		} else if ('${msg}' == "삭제되었습니다.") {
			alert('${msg}');
		} else if ('${msg}' == "신청마감되었습니다.") {
			alert('${msg}');
		} else if ('${msg}' == "포인트가 지급되지않았습니다.") {
			alert('${msg}');
		}
	});
</script>
<body>
	<div id="contents">
		<div id="header"></div>
		<div id="section">${html} ${commentList} ${Requesthtml}</div>
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