<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
</style>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	function back(obj){
		if(obj.value=='돌아가기'){
			document.pw.action="home";
			document.pw.submit();
		}
	}
</script>
</head>
<body>
	<div id="header"></div>
	<h2>비밀번호 변경</h2>
	<form name="pw" action="pwUpdate" method="post">
		ID ${id }</br>
		현재 비밀번호 <input type="password" name="nowPW"/></br>
		새 비밀번호 <input type="password" name="newPW"/></br>
		새 비밀번호확인 <input type="password" name="newPWchk"/></br>
		<input type="submit" value="비밀번호 변경"/>
		<input type="button" onclick="back(this)" value="돌아가기"/>
	</form>
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