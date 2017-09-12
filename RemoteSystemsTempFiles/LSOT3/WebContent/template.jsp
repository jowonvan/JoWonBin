<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LSOT에 오신 것을 환영합니다.</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="http://malsup.github.com/jquery.cycle2.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 도로명 조합형 주소 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }
            // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
            if(fullRoadAddr !== ''){
                fullRoadAddr += extraRoadAddr;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample4_postcode').value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById('sample4_roadAddress').value = fullRoadAddr;
            document.getElementById('sample4_roadAddress').value = data.jibunAddress;

            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                //예상되는 도로명 주소에 조합형 주소를 추가한다.
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                document.getElementById('guide').innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                document.getElementById('guide').innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';

            } else {
                document.getElementById('guide').innerHTML = '';
            }
        }
    }).open();
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
	min-height : 100%;
	position: relative;
	margin:0px;
	padding:0px;
}

#section {
	width: 70%;
	margin-left:15%;
	padding-top:10%;
	padding-bottom: 15%;
}

#section .cycle-slideshow {
	text-align: center;	
	position: relative;
}

#section .cycle-slideshow img{
	max-width : 100%;
	border-radius: 3px;
} 
 
#section .cycle-pager{
	text-align:center;
	width:100%;
	overflow:hidden;
	position:absolute;
	top:2px;
	z-index: 501;
}

#section .cycle-pager span{
	font-size: 50px;
	display:inline-block;
	cursor: pointer;
	color: #ddd;
}

#section .cycle-pager span.cycle-pager-active{
	color:red;
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
</head>
<body>
	<div id="contents">
		<div id="header">
		</div>
		<div id="section">
			<div class="cycle-slideshow" 
				 cycle-slideshow data-cycle-loader="wait"
    			data-cycle-timeout=3000
    		>
		    <div class="cycle-pager"></div>
				<%-- <img src="./img/DoGive.jpg" data-cycle-pager-template="<a href=#>기부하기</a>">  --%>
				<img src="./img/DoGive.jpg"> 
				<img src="./img/TakeO.jpg"> 
				<img src="./img/TakeM.png"> 
				<img src="./img/review.png">
			</div>
		</div>
		<div id="footer">
			<hr />
			<div id="footcontLeft">
				<p>LSOT 대표 전화 : 032-111-1111</p>
				<p>LSOT 주소 : 인천 남동구 학익동</p>
			</div>
			<div id="footcontRight">
				<a href="/adminlogin"><img style="max-width: 100%;" src="./img/logo.png" /></a>				
			</div>
		</div>
	</div>
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
		function Ajf(url, formData, position) {
			$.ajax({
				url : url,
				type : 'post',
				data : formData,
				success : function(html) {
					$(position).html(html);
				},
				error : function(error) {
					console.log(error);
				}
			});
		}
		function Ajd(url, data, position) {
			console.log(url);
			console.log(data);
			console.log(position);
			$.ajax({
				url : url,
				type : 'get',
				data : data,
				success : function(html) {
					$(position).html(html);
				},
				error : function(error) {
					console.log(error);
				}
			});
		}

		   $(function() {      
		      if ('${msglogin}' != null) {
		         if ('${msglogin}' == 'unknownid') {
		            alert('존재하지 않는 아이디입니다.');
		         } 
		         if ('${msglogin}' == 'unknownpw') {
		            alert('존재하지 않는 비밀번호입니다.');
		         }
		      }
		   });
	</script>
</body>
</html>