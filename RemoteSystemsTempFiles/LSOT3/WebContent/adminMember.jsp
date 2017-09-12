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
</head>
<body>
<!-- <div id="header"></div> -->
	${memberlist}
	${blacklist}</br>
	<div>${memberdetail}</div>
	<input type="button" onclick="location.href='/home'" value="돌아가기"/>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<div id="header"></div>
<script>
/* Aj('header', '#header');
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
} */
</script>
</html>