<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>

 <header class="jumbotron my-4">
   <h1 class="display-3"> 환영합니다. </h1>
    <p class="lead">책 검색, 히스토리 조회 기능을 사용하실 수 있습니다.</p>
 </header>
 
 
<script>
$(document).ready(function(){
		$('#nav-login').html('로그아웃')
					   .prop('href' , $('#nav-login').prop('href').replace('/loginForm', '/logout'));
	}
)
</script>