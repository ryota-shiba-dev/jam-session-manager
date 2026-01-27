<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>データ検索</title>
</head>
<body>
<form action="search" method="post">
        <table>
            <tr>
                <td>タイトル：</td>
                <td><input type="text" name="title"></td>
            </tr>
            <tr>
                <td>日付：</td>
                <td><input type="month" name="session_date"></td>
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

        <button type="submit">検索</button>
    </form>
</body>
</html>