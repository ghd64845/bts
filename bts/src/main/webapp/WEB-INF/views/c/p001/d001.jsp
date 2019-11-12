<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 메인</title>
<link rel="stylesheet"
	href="${contextPath}/resources/css/mypage/d001.css">
<link rel="stylesheet"
	href="/bts/resources/library/bootstrap/css/bootstrap.min.css" />
<script src="${contextPath }/resources/js/c/p001/c_d001.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	
	$('#btn-form-submit').click(function(){
		var profile = $('#frm-profile')[0];
		profile.action="${contextPath }/my/update";
		profile.submit();
		/*
		console.log(profile);
		console.log(profile.profile_image);
		*/
	});
	
})
</script>
</head>
<body>
	<!--<div id = "container">
<img src ="http://www.artinsight.co.kr/data/tmp/1703/96103d28d38e5fb86a31b713cde7626c_sRZKrRWIDpl7sJnjq5rz2yR.jpg?s=1400x467">
</div>-->
	<div id="content">
		<div class="wrapper-page"></div>
		<div class="package">
			<div class="row">
				<div class="col-md-9 sub-container">
					<h3 class="space-5">여행자 정보 등록</h3>
					<form id="frm-profile" autocomplete="off" method="post">
						<input type="hidden" name="email_id" id="email_id"> <input
							type="hidden" name="email_host" id="email_host">
						<div class="mypage-picture row" action="${contextPath}/my/image">

							<c:choose>
								<c:when test="${not empty sessionScope.memberInfo.profile_image }">
									<c:if test="${sessionScope.memberInfo.member_type =='kakao' }">
										<img src="${sessionScope.memberInfo.profile_image }" id="profImg">
									</c:if>
									<c:if test="${sessionScope.memberInfo.member_type !='kakao' }">
										<img src="${contextPath}/${sessionScope.memberInfo.profile_image }" id="profImg">									
									</c:if>
								</c:when>

								<c:otherwise>
									<img
										src="https://d2mgzmtdeipcjp.cloudfront.net/files/member/profile.png"
										class="user-picture" id="profImg">
								</c:otherwise>
							</c:choose>

							<input type="file" data-toggle="modal" name="profile_image"
								data-target="#modal-set-profile-img"
								class="btn btn-sm btn-default" value="사진 올리기" id="input_img" />
						</div>
						<div class="row">
							<div class="col-md-6">
								<label class="title">이름</label> <input type="text"
									value="${sessionScope.memberInfo.name}" id="name" name="name"
									placeholder="name" class="form-control"
									onkeypress="specialCharNotPress();" required>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<label class="title">닉네임</label> <input type="text"
									value="${sessionScope.memberInfo.nick_name}" id="nick_name"
									name="nick_name" placeholder="닉네임" class="form-control"
									required>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<label class="title">비밀번호</label> <input type="password"
									value="${sessionScope.memberInfo.password}" id="password"
									name="password" placeholder="password" class="form-control"
									onkeypress="specialCharNotPress();" required>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<label class="title">성별</label>
								<!--<input type="text" id="gender" name="gender" value="" class="form-control" engonly="true" required="">-->
								<select class="form-control" id="gender" name="gender">
									<c:if test="${sessionScope.memberInfo.gender =='M'}">
										<option value="M" selected>남</option>
										<option value="F">여</option>
									</c:if>
									<c:if test="${sessionScope.memberInfo.gender =='F'}">
										<option value="M">남</option>
										<option value="F" selected>여</option>
									</c:if>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<label class="title">생년월일</label> <input type="text"
									value="${sessionScope.memberInfo.birth}" id="birth"
									name="birth" placeholder="YYYY-MM-DD" class="form-control"
									required>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<label class="title">이메일</label> <input type="email"
									value="${sessionScope.memberInfo.email}" id="email"
									name="email" placeholder="email" class="form-control" required>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<label class="title">휴대전화번호</label> <input type="tel"
									value="${sessionScope.memberInfo.tel_no}" id="tel_no"
									name="tel_no" placeholder="tel" class="form-control" required
									onkeypress="onlyNumOnKeyPress();">
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">
								<button type="button" id="btn-form-submit" class="btn btn-form-submit form-control"
									style="margin-bottom: 40px; background: #ec008c;  /* fallback for old browsers */
										background: -webkit-linear-gradient(to right, #fc6767, #ec008c);  /* Chrome 10-25, Safari 5.1-6 */
										background: linear-gradient(to right, #fc6767, #ec008c); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */">저장</button>
							</div>
						</div>
					</form>
					<div class="row">
						<div class="col-md-6" style="display: flex;">
							<div style="margin: auto;">
								<div data-toggle="modal" data-target="#modal-out-agreement"
									class="btn-out"
									style="cursor: pointer; color: lightgrey; text-decoration: underline;">
									<a href="${contextPath }/my/exitMain">회원탈퇴</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>