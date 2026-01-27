<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.Session" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>セッション一覧</title>
</head>
<body>
    <h1>セッション一覧</h1>
    <table border="1">
        <tr>
            <th>タイトル</th><th>日付</th><th>場所</th><th>メモ</th>
        </tr>
        <%
            List<Session> list = (List<Session>) request.getAttribute("sessionList");
            if (list != null) {
                for (Session s : list) {
        %>
        <tr>
            <td><%= s.getTitle() %></td>
            <td><%= s.getSessionDate() %></td>
            <td><%= s.getLocation() %></td>
            <td><%= s.getReview() %></td>
        </tr>
        <%
                }
            }
        %>
    </table>
    <br>
    <a href="main.html">新規登録に戻る</a>
</body>
</html>