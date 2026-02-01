<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>セッションログ登録</title>
<link rel="stylesheet" href="css/index.css" />
</head>
<header>
	<h1>セッションログ登録</h1>
</header>

<form action="register" method="post">
	<table>
		<tr>
			<td>タイトル：</td>
			<td><input type="text" name="title"></td>
		</tr>
		<tr>
			<td>日付：</td>
			<td><input type="date" name="session_date"></td>
		</tr>
		<tr>
			<td>場所：</td>
			<td><input type="text" name="location"></td>
		</tr>
		<tr>
			<td>感想：</td>
			<td><textarea name="review"></textarea></td>
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