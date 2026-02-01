<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>曲登録</title>
   <link rel="stylesheet" href="css/index.css" />
</head>
<header>
	<h1>曲登録</h1>
</header>

<form action="songregister" method="post">
	<table>
		<tr>
			<td>紐付けるセッション：</td>
			<td><select name="session_id" required>
					<option value="">選択してください</option>
					<c:forEach var="s" items="${sessionList}">
						<option value="${s.id}">${s.sessionDate} / ${s.location}</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td>曲名：</td>
			<td><input type="text" name="song_name"></td>
		</tr>
		<tr>
			<td>Youtubeリンク：</td>
			<td><input type="text" name="youtube_url"></td>
		</tr>
		<tr>
			<td>感想：</td>
			<td><textarea name="song_review"></textarea></td>
		</tr>
	</table>

	<button type="submit">登録</button>
</form>

<footer>
	<div class="container">
		<div>
			<a href="list">一覧</a>
		</div>
		<div>
			<a href="search">検索</a>
		</div>
		<div>
			<a href="main.html">メイン画面に戻る</a>
		</div>
	</div>
	<p>©セッションログ</p>
</footer>
</html>