<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style>
#joinForm {
	width:60%;
	padding-left:20%;
} 
h6{
	display:none;
	color: red;
	text-align: center;
}

.sizing{
	height: 4%;
	margin-bottom : 1%;
	border-radius: 3px;
}
#sample4_roadAddress{
	width:50%;
}
</style>
</head>
<body>
	<div id="joinForm">
		<h1 style="text-align: center;">회원가입 - 시설 / 단체</h1>
		<br/>
		<form name="joinGroupForm">
			<p>
				아이디 : <input type="text" name="id" class="sizing"/>
			</p>
			<p>
				비밀번호 : <input type="password" name="pw" class="sizing"/>
			</p>
			<p>
				비밀번호 확인 : <input type="password" name="pwConfirm" class="sizing" />
			</p>
			<p>
				담당자 : <input type="text" name="name" class="sizing"/>
			</p>
			<p>	
				담당자 핸드폰 : <select name="phoneValue" class="sizing">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="017">017</option>
					</select>-<input type="text" style="width: 50px;" name="second" class="sizing"/>-<input
					type="text" style="width: 50px;" name="third" class="sizing"/>
			</p>
			<p>
				전화 번호 : <select name="telValue" class="sizing">
						<option value="02">02</option>
						<option value="032">032</option>
						<option value="033">033</option>
					</select>-<input type="text" style="width: 50px;" name="telsecond" class="sizing"/>-<input
					type="text" style="width: 50px;" name="telthird" class="sizing" />
			</p>	
			<p>
				시설 이름 : <input type="text" name="officeName" class="sizing"/>
			</p>
			<p>
				이메일 : <input type="text" name="email" class="sizing"/>
			</p>
			<p style="clear:both;">
				<input type="button" onclick="sample4_execDaumPostcode()" value="주소 찾기" class="sizing">
				　<input type="text" id="sample4_postcode" placeholder="우편번호" name="num" class="sizing" readonly="readonly"> - <input type="text" id="sample4_roadAddress" placeholder="입력한 주소" name="doro" class="sizing" readonly="readonly">
				<span id="guide" style="color:#999"></span>
			</p>
			<p>
				성별 : <input type="radio" value="남성" name="gender" />남성 <input
					type="radio" value="여성" name="gender" />여성
			</p>
			<p>
				시설 설립일 : <input type="text" name="birth" placeholder="ex : 20170101" class="sizing"/> 
			</p>
			<p>
				질문 : <select name="pwQuestion" class="sizing">
					<option>비밀번호 질문을 선택하십시오</option>		
					<option value="Q1">당신의 보물 1호는?</option>
					<option value="Q2">반려동물의 이름은?</option>
					<option value="Q3">가장 존경하는 인물은?</option>
					<option value="Q4">가장 기억에 남는 장소는?</option>
					<option value="Q5">처음으로 간 해외여행 장소는?</option>
					<option value="Q6">가장 즐겨했던 게임은?</option>
					<option value="Q7">가장 좋아하는 연예인은?</option>
				</select>
			</p>
			<p>
				비밀번호 답 : <input type="text" name="pwAnswer" class="sizing"/> 
			</p>
			<input type="hidden" name="kind" value="단체" /> 
			<input type="hidden" name="point" value="0" /> 
			<h6>비밀번호를 확인해 주십시오.</h6>
			<p style="clear:both; text-align: center;">
				<input type="button" value="회원가입" id="joinResultG" class="sizing"/>
				<input type="button" value="뒤로가기" id="toBack" class="sizing"/>
			</p>
		</form>
	</div>
</body>
<script>
	
	$(function() {
		$('#joinResultG').click(function() {
			if ($('#pw').val() == $('#pwConfirm').val()) {
				document.joinGroupForm.action = 'joinGroup';
				document.joinGroupForm.submit();
			} else {
				$('h6').css('display', 'block');
			}
		});
		$('#toBack').click(function() {
			Aj('AjaxLogin', '#section');
		});
		if ('${msg}' != null) {
			if ('${msg}' == 'idoverlap') {
				alert('아이디가 중복되었습니다.');
			} else if ('${msg}' == 'emailoverlap') {
				alert('이메일이 중복되었습니다.')
			}
		}
	});
</script>
</html>