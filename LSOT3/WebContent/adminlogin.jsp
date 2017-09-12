<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
</style>
<script src="https://code.jquery.com/jquery-3.2.1.min.js">
</script>
<script>
$(function(){
	if('${msg}' == "비밀번호가 틀렸습니다."){
		alert('${msg}');
	}else if('${msg}' == "접근 권한이 없습니다."){
		alert('${msg}');
	}
});
</script>
</head>
<body>
	<form action="/adminlogin" method="post">
		아이디 : <input type="text" name="id"/>
		비밀번호 : <input type="password" name="pw"/>
		<input type="submit" value="확인"/>
		<a href="Index"><input type="button" value="home"/></a>
	</form>
</body>
</html>