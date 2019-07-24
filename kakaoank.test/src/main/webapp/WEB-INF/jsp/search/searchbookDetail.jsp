<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
<!-- 상세 정보에는 제목, 도서 썸네일, 소개, ISBN, 저자, 출판사, 출판일, 정가, 판매가가 포함되어야 합니다. -->

	<table class="type01">
		<caption> 도서 상세 조회 </caption>
		
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
				<td>제목</td>
				<td>${title}</td>
			</tr>
			<tr>
				<td>ISBN</td>
				<td>${isbn}</td>
			</tr>
			<tr>
				<td>저자</td>
				<td>
					${author}	
				</td>
			</tr>
			<tr>
				<td>출판사</td>
				<td>${publisher}</td>
			</tr>
			<tr>
				<td>출판일</td>
				<td>${datetime.substring(0,10)}</td>
			</tr>
			<tr>
				<td>정가</td>
				<td>${price}원</td>
			</tr>
			<tr>
				<td> 소개</td>
				<td>${contents}</td>
			</tr>
		</tbody>
	</table>
</div>