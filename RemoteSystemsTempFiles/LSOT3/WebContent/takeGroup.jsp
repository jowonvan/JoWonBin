<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
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
	text-align: center;
	position: relative;
}

#section table{
	width:100%;
}

#section table .col{
	background-color: #082d71;
	height: 50px;
	color : white;
}

#section table td{
	border-bottom: 1px solid black;
}

table tr td a {
	text-decoration: none;
	color: black;
	font-size: 20px;
} 
input#write{
	position: absolute;
	right: 0;
}

#section #search{ 
	width:50%;
	left:25%;
	bottom:0;
	position: absolute;
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
</head>
<body>
	<div id="contents">
   		<div id="header"></div>
		<div id="section">
			<h2>기부받기(${kind})</h2>
			<form name="groupDetail">
         		${html}
      		</form>
			<div id="search">
				<form name="searchForm" method="post">
         			<select name="selectValue" onChange="searChg()">
            			<option value="">선택</option>
            			<option value="제목">제목</option>
           				<option value="등록자">등록자</option>
         			</select> 
         			<input type="hidden" value="${kind}" name="gpkind" /> 
         			<input type="text" value="" name="inputSerch" />
         			<input type="button" value="검색" onclick="searchBtn(this)" />
      			</form>
			</div>
		</div>
		<div id="footer">
			<hr />
			<div id="footcontLeft">
				<p>LSOT 대표 전화 : 032-111-1111</p>
				<p>LSOT 주소 : 인천 남동구 학익동</p>
			</div>
			<div id="footcontRight">
				<a href="adminlogin.jsp"><img style="max-width: 100%;"
					src="./img/logo.png" /></a>
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
	

	var a = "";
	function searChg() {
		a = searchForm.selectValue.options[document.searchForm.selectValue.selectedIndex].value;
		return a;
	}
	function searchBtn() {
		searChg();
		var t_input = "";
		if (a == '제목') {
			t_input = document.searchForm.inputSerch.value;
			document.searchForm.action = 'titleBtn?t_title=' + t_input;
			document.searchForm.submit();
		} else if (a == '등록자') {
			t_input = document.searchForm.inputSerch.value;
			document.searchForm.action = 'idBtn?t_mid=' + t_input;
			document.searchForm.submit();
		}
	}
	function groupWrite() {
	      document.searchForm.action = "tgAdd";
	      document.searchForm.submit();
	}
	$(function(){
		if('${msg}'=="로그인 후 이용 가능합니다."){
			alert('${msg}');
		}else if('${msg}'=="단체회원만 작성할 수 있습니다."){
			alert('${msg}');
		}else if('${msg}'=="일반회원만 작성할 수 있습니다."){
			alert('${msg}');
		}
	});
</script>
</html>