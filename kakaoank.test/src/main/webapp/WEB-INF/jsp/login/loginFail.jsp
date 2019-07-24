<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>

 <header class="jumbotron my-4">
   <h1 class="display-3">로그인 실패</h1>
   <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
    <font color="red">
        <p>Your login attempt was not successful due to <br/>
            ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
        <c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
    </font>
	</c:if>

   <button class="login-btn waves-effect waves-light btn"  onclick="javascript:ajaxLoad('GET', '/loginForm', '', null , null); return false">로그인 페이지로 이동</button>
 </header>