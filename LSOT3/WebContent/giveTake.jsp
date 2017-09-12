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
	function searChg() {
		a = searchForm.selectValue.options[document.searchForm.selectValue.selectedIndex].value;
		return a;
	}
	function searchBtn() {
		searChg();
		var t_value = "";
		if (a == '제목') {
			t_title = document.searchForm.inputSerch.value;
			document.searchForm.action = 'titleBtn?t_title=' + t_title;
			document.searchForm.submit();
		} else if (a == '등록자') {

		}
	}
	$(function(){
		if('${msg}'== "관리자만 가능합니다."){
			alert('${msg}');
		}
</script>
</head>
<body>
<div id="header"></div>
	${TgList }
	${giveList }
	${TiList }
	${noticeList}
	${reviewList}
	<form name="searchForm">
		검색 <select name="selectValue" onChange="searChg()">
			<option value="">선택</option>
			<option value="제목">제목</option>
			<option value="등록자">등록자</option>
			</select> 
			<input type="text" value="" name="inputSerch" />
			<input type="button" value="검색" onclick="searchBtn(this)" />
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