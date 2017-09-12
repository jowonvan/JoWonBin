<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
</style>
<script>
	function admin(obj) {
		var val = obj.value;
		if (val == '회원리스트') {
			document.adminform.action = 'adminMember';
			document.adminform.submit();
		} else if (val == '블랙리스트') {
			document.adminform.action = 'adminBlackList';
			document.adminform.submit();
		} else if (val == '기부하기') {
			document.adminform.action = 'adminGive';
			document.adminform.submit();
		} else if (val == '기부받기(개인)') {
			document.adminform.action = 'adminTi';
			document.adminform.submit();
		} else if (val == '기부받기(단체)') {
			document.adminform.action = 'adminTg';
			document.adminform.submit();
		} else if (val == '후기') {
			document.adminform.action = 'adminReview';
			document.adminform.submit();
		} else if (val == '신청관리') {
			document.adminform.action = 'adminRequest';
			document.adminform.submit();
		} else if (val == '공지사항') {
			document.adminform.action = 'adminNotice';
			document.adminform.submit();
		} else if (val == '비밀번호 변경') {
			document.adminform.action = 'pwMove';
			document.adminform.submit();
		} else if (val == '홈으로 가기') {
			document.adminform.action = 'Index';
			document.adminform.submit();
		} else if (val == '로그아웃') {
			document.adminform.action = 'logout';
			document.adminform.submit();
		}
	}
</script>
</head>
<body>
	<form name="adminform" method="post">
		<input type="button" onclick="admin(this)" value="홈으로 가기" /> 
		<input type="button" onclick="admin(this)" value="회원리스트" /> 
		<input type="button" onclick="admin(this)" value="블랙리스트" /> 
		<input type="button" onclick="admin(this)" value="기부하기" /> 
		<input type="button" onclick="admin(this)" value="기부받기(개인)" /> 
		<input type="button" onclick="admin(this)" value="기부받기(단체)" /> 
		<input type="button" onclick="admin(this)" value="후기" />
		<input type="button" onclick="admin(this)" value="공지사항" /> 
		<input type="button" onclick="admin(this)" value="비밀번호 변경" /> 
		<input type="button" onclick="admin(this)" value="로그아웃" />
	</form>
</body>
</html>