<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
var a = "";
function searChg(){
	a = updateForm.selectValue.options[document.updateForm.selectValue.selectedIndex].value;
	return a;
}
function updateInput(){
	searChg();
	document.updateForm.action = 'tgUpdate';
	document.updateForm.submit();
}
</script>
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
	text-align: center;
}

input[type=text] {
	width: 300px;
	height: 30px;
}

div#adder {
	width: 30%;
	margin-left: 35%;
}

div.lefter, div.righter {
	display: block;
	padding-bottom: 3%;
}

div.lefter {
	float: left;
	clear: both;
	margin-left: 5%:
}

div.righter {
	float: right;
	clear: both;
}

#submitBtn {
	clear: both;
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

#footcontright {
	float: right;
	width: 20%;
}
</style> 
</head>
<body>
	<div id="contents">
		<div id="header"></div>
		<div id="section">
			<div id="adder">
				<form name="updateForm">
					<div class="floor">
						<div class="lefter">
							<h2>제목 ${t_title}</h2>
						</div>
					</div>
					<div class="floor">
						<div class="lefter">재능 기부내용 (구체적으로)</div>
						<div class="righter">
							<textarea cols=40 rows=5 name="content"></textarea>
						</div>
					</div>
					<div class="floor">
						<div class="lefter">신청 기간</div>
						<div class="righter">
							<input type="date" name="start_request_period" /> ~ <input
								type="date" name="end_request_period" />
						</div>
					</div>
					<div class="floor">
						<div class="lefter">봉사 기간</div>
						<div class="righter">
							<input type="date" name="service_period" />
						</div>
					</div>
					<div class="floor">
						<div class="lefter">재능 기부자 성별</div>
						<div class="righter">
							<input type="radio" value="남성" name="gender" />남성 <input
								type="radio" value="여성" name="gender" />여성 <input type="radio"
								value="무관" name="gender" />무관
						</div>
					</div>
					<div class="floor">
						<div class="lefter">재능 기부자 나이</div>
						<div class="righter">
							<select name="selectValue" onChange="searChg()">
								<option value="10대">10대</option>
								<option value="20대">20대</option>
								<option value="30대">30대</option>
								<option value="40대">40대</option>
							</select>
						</div>
					</div>
					<div class="floor">
						<div class="lefter">모집 인원</div>
						<div class="righter">
							<input type="text" name="personnel" />
						</div>
					</div>
					<div class="floor">
						<div class="lefter">기타</div>
						<div class="righter">
							<input type="text" name="etc" />
						</div>
					</div>
					<p id="submitBtn">
						<input type="hidden" value="${t_code}" name="t_code" /> 
						<input type="hidden" value="${t_title}" name="t_title" /> 
						<input type="hidden" value="${t_kind}" name="t_kind" /> 
						<input type="hidden" value="${t_mid}" name="t_mid" />
						<button onclick="updateInput()">수정하기</button>
					</p>
				</form>
			</div>
		</div>
		<div id="footer">
			<hr />
			<div id="footcontLeft">
				<p>LSOT 대표 전화 : 032-111-1111</p>
				<p>LSOT 주소 : 인천 남동구 학익동</p>
			</div>
			<div id="footcontright">
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
</script>
</html>