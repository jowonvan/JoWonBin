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
	padding-bottom: 15%;	
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
<script src="https://code.jquery.com/jquery-3.2.1.min.js">
	
</script>
<script> 
	function func(obj) {
		if (obj.value == "개인정보 확인") {
			document.mypage.action = "myDetail";
			document.mypage.submit();
		} else if (obj.value == "돌아가기") {
			document.mypage.action = "Index";
			document.mypage.submit();
		}
	}
	$(function() {
		if ('${msg}' == "비밀번호가 일치하지 않습니다.") {
			alert('${msg}');
		}
	});
</script>
</head>
<body>
	<div id="contents">
		<div id="header"></div>
		<div id="section">
			<form id="mypageForm" name="mypage">
				<p>아아디 ${id }</p>
				<p>
					비밀번호 <input type="password" name="pw" />
				</p>
				<p>
					<input type="button" id="infocheck" onclick="func(this)"
						value="개인정보 확인" /> <input type='button' id="return"
						onclick="func(this)" value='돌아가기' />
				</p>
			</form>
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