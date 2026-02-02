<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.Session"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>セッション詳細一覧</title>
<link rel="stylesheet" href="css/index.css" />
</head>
<body>
	<h1>セッション一覧</h1>
	<%-- セッション情報の表示 --%>
	<c:forEach var="s" items="${sessionList}">
		<h2>${s.sessionDate}： ${s.location} の曲一覧</h2>
		<h3>タイトル: ${s.title}</h3>
	</c:forEach>

	<hr>

	<%-- 曲一覧の表示 --%>
	<table>
	<tr>
			<th>曲名</th>
			<th>感想</th>
			<th>Youtubeリンク</th>
		</tr>
		<c:forEach var="song" items="${songList}">
			<tr>
				<td>${song.songName}</td>
				<td>${song.songReview}</td>
				<td><a href="${song.youtubeUrl}">YouTube</a></td>

			</tr>
		</c:forEach>
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