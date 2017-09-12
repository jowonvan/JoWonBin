<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
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

#joinForm {
	width: 50%;
	padding-top: 15%;
	padding-left: 25%;
}

.lefter {
	float: left;
}

.righter {
	float: right;
}

h6 {
	display: none;
	color: red;
	text-align: center;
}

.sizing {
	height: 3%;
	border-radius: 3px;
}

#sample4_roadAddress {
	width: 50%;
}
</style>
<script>

</script>
</head>
<body>
	<div id="contents">
		<div id="header"></div>
		<div id="joinForm">
			<h1 style="text-align: center;">회원정보수정 - 시설 / 단체</h1>
			<br />
			<form id="joinGroup" name="joinGroup">
				<p>
					<span class="lefter">아이디 : ${id}</span> <span class="righter">담당자
						: <input type="text" name="name" class="sizing" value="${name}" />
					</span>
				</p>
				<br />
				<p>
					<span class="lefter">비밀번호 : <input type="password" name="pw"
						class="sizing" /></span> <span class="righter">비밀번호 확인 : <input
						type="password" name="pwConfirm" class="sizing" /></span>
				</p>
				<br />
				<p>
					<span class="lefter"> 전화 번호 : <select name="telValue"
						class="sizing">
							<option value="02">02</option>
							<option value="032">032</option>
							<option value="033">033</option>
					</select>-<input type="text" style="width: 50px;" name="telsecond"
						class="sizing" />-<input type="text" style="width: 50px;"
						name="telthird" class="sizing" />
					</span> <span class="righter"> 담당자 핸드폰 : <select name="phoneValue"
						class="sizing">
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="017">017</option>
					</select>-<input type="text" style="width: 50px;" name="second"
						class="sizing" />-<input type="text" style="width: 50px;"
						name="third" class="sizing" />
					</span>
				</p>
				<br />
				<p>
					<span class="lefter">시설 이름 : <input type="text"
						name="officeName" class="sizing" value="${officename}" /></span> <span
						class="righter">이메일 : <input type="text" name="email"
						class="sizing" value="${email}" /></span>
				</p>
				<br />
				<p style="clear: both;">
					<input type="button" onclick="sample4_execDaumPostcode()"
						value="주소 찾기" class="sizing"> <input type="text"
						id="sample4_postcode" placeholder="우편번호" name="num" class="sizing"
						readonly="readonly"> - <input type="text"
						id="sample4_roadAddress" placeholder="입력한 주소" name="doro"
						class="sizing" readonly="readonly"> <span id="guide"
						style="color: #999"></span>
				</p>
				<p>
					<span class="lefter">성별 : <input type="radio" value="남성"
						name="gender" value="${gender}" />남성 <input type="radio"
						value="여성" name="gender" value="${gender}" />여성
					</span> <span class="righter"><select name="pwQuestion"
						class="sizing">
							<option>비밀번호 질문을 선택하십시오</option>
							<option value="Q1">당신의 보물 1호는?</option>
							<option value="Q2">반려동물의 이름은?</option>
							<option value="Q3">가장 존경하는 인물은?</option>
							<option value="Q4">가장 기억에 남는 장소는?</option>
							<option value="Q5">처음으로 간 해외여행 장소는?</option>
							<option value="Q6">가장 즐겨했던 게임은?</option>
							<option value="Q7">가장 좋아하는 연예인은?</option>
					</select></span>
				</p>
				<br />
				<p>
					<span class="lefter">시설 설립일 : <input type="text"
						name="birth" placeholder="ex : 20170101" class="sizing"
						value="${birth}" /></span> <span class="righter">비밀번호 답 : <input
						type="text" name="pwAnswer" class="sizing" /></span>
				</p>
				<br /> <input type="hidden" name="kind" value="단체" /> <input
					type="hidden" name="point" value="0" />
				<h6>비밀번호를 확인해 주십시오.</h6>
				<p style="clear: both; text-align: center;">
					<input type="button" value="회원정보수정" id="joinResult" class="sizing" onclick="confirm()"/>
					<input type="button" value="뒤로가기" id="toBack" class="sizing" />
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
function uupdate(){
	document.joinGroup.action="myUpdate";
	document.joinGroup.submit();
}
function confirm() {

	if ($('#pw').val() == $('#pwConfirm').val()) {
		uupdate();
	} else {
		$('h6').css('display', 'block');

	}
}
$('#toBack').click(function() {
	console.log('click!');
	location.href = './myInfo';
})
$(function(){
	if('${msg}' == "수정되었습니다."){
		alert('${msg}');
	}else if('${msg}' == "수정 실패했습니다."){
		alert('${msg}');
	}
});
	function sample4_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
						var extraRoadAddr = ''; // 도로명 조합형 주소 변수

						// 법정동명이 있을 경우 추가한다. (법정리는 제외)
						// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
						if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
							extraRoadAddr += data.bname;
						}
						// 건물명이 있고, 공동주택일 경우 추가한다.
						if (data.buildingName !== '' && data.apartment === 'Y') {
							extraRoadAddr += (extraRoadAddr !== '' ? ', '
									+ data.buildingName : data.buildingName);
						}
						// 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
						if (extraRoadAddr !== '') {
							extraRoadAddr = ' (' + extraRoadAddr + ')';
						}
						// 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
						if (fullRoadAddr !== '') {
							fullRoadAddr += extraRoadAddr;
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample4_postcode').value = data.zonecode; //5자리 새우편번호 사용
						document.getElementById('sample4_roadAddress').value = fullRoadAddr;
						document.getElementById('sample4_roadAddress').value = data.jibunAddress;

						// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
						if (data.autoRoadAddress) {
							//예상되는 도로명 주소에 조합형 주소를 추가한다.
							var expRoadAddr = data.autoRoadAddress
									+ extraRoadAddr;
							document.getElementById('guide').innerHTML = '(예상 도로명 주소 : '
									+ expRoadAddr + ')';

						} else if (data.autoJibunAddress) {
							var expJibunAddr = data.autoJibunAddress;
							document.getElementById('guide').innerHTML = '(예상 지번 주소 : '
									+ expJibunAddr + ')';

						} else {
							document.getElementById('guide').innerHTML = '';
						}
					}
				}).open();
	}

	$(function() {
		if ('${msg}' == "수정되었습니다.") {
			alert('${msg}');
		} else if ('${msg}' == "수정 실패했습니다.") {
			alert('${msg}');
		}
	});
</script>
</html>