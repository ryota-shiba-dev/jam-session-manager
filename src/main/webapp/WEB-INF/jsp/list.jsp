<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.Session"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>セッション一覧</title>
<link rel="stylesheet" href="css/index.css" />
</head>
<body>
	<h1>セッション一覧</h1>
	<table border="1">
		<tr>
			<th>タイトル</th>
			<th>日付</th>
			<th>場所</th>
			<th>メモ</th>
			<th>詳細</th>
		</tr>
		<%
		List<Session> list = (List<Session>) request.getAttribute("sessionList");
		if (list != null) {
			for (Session s : list) {
		%>
		<tr>
			<td><%=s.getTitle()%></td>
			<td><%=s.getSessionDate()%></td>
			<td><%=s.getLocation()%></td>
			<td><%=s.getReview()%></td>
			<td><a href="songlist?id=<%=s.getId()%>">詳細を見る</a></td>
		</tr>
		<%
		}
		}
		%>
	</table>
	<br>
	<div class="container">
		<div>
			<a href="search">検索</a>
		</div>
		<div>
			<a href="main.html">メイン画面に戻る</a>
		</div>
	</div>
</body>
</html>