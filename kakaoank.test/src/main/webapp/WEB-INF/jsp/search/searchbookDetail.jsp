<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
<!-- �� �������� ����, ���� �����, �Ұ�, ISBN, ����, ���ǻ�, ������, ����, �ǸŰ��� ���ԵǾ�� �մϴ�. -->

	<table class="type01">
		<caption> ���� �� ��ȸ </caption>
		
		<colgroup>
			<col width=40%>
			<col width=10%>
			<col width=50%>
		</colgroup>
		<thead>
		</thead>
		<tbody>
			<tr>
				<td rowspan="7"><img class="card-img-top" src="${thumbnail}" alt=""></td>
				<td>����</td>
				<td>${title}</td>
			</tr>
			<tr>
				<td>ISBN</td>
				<td>${isbn}</td>
			</tr>
			<tr>
				<td>����</td>
				<td>
					${author}	
				</td>
			</tr>
			<tr>
				<td>���ǻ�</td>
				<td>${publisher}</td>
			</tr>
			<tr>
				<td>������</td>
				<td>${datetime.substring(0,10)}</td>
			</tr>
			<tr>
				<td>����</td>
				<td>${price}��</td>
			</tr>
			<tr>
				<td> �Ұ�</td>
				<td>${contents}</td>
			</tr>
		</tbody>
	</table>
</div>