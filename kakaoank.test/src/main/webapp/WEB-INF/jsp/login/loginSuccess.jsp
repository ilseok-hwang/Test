<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>

 <header class="jumbotron my-4">
   <h1 class="display-3"> ȯ���մϴ�. </h1>
    <p class="lead">å �˻�, �����丮 ��ȸ ����� ����Ͻ� �� �ֽ��ϴ�.</p>
 </header>
 
 
<script>
$(document).ready(function(){
		$('#nav-login').html('�α׾ƿ�')
					   .prop('href' , $('#nav-login').prop('href').replace('/loginForm', '/logout'));
	}
)
</script>