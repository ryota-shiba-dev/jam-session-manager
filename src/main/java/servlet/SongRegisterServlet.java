package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Session;

/**
 * 曲情報をDBに登録するサーブレット
 */
@WebServlet("/songregister")

public class SongRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Session> sessionList = new ArrayList<>();
		String url = "jdbc:postgresql://localhost:5433/postgres";
		String user = "postgres";
		String password = System.getenv("DB_PASSWORD");

		try {
			Class.forName("org.postgresql.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, password);
					PreparedStatement ps = conn
							.prepareStatement(
									"SELECT id, substring(session_date,1,10) AS short_date, location FROM jam_sessions ORDER BY session_date DESC");
					ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					Session s = new Session();
					s.setId(rs.getInt("id"));
					s.setSessionDate(rs.getString("short_date"));
					s.setLocation(rs.getString("location"));
					sessionList.add(s);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// セッション一覧をリクエスト属性にセットしてJSPへ
		request.setAttribute("sessionList", sessionList);
		request.getRequestDispatcher("/WEB-INF/jsp/songregister.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		// 1. フォーム(main.html)からの値を受け取る 
		String sessionIdStr = request.getParameter("session_id");
		String song_name = request.getParameter("song_name");
		String youtube_url = request.getParameter("youtube_url");
		String song_review = request.getParameter("song_review");

		// 2. DB接続情報の準備
		String url = "jdbc:postgresql://localhost:5433/postgres";
		String user = "postgres";
		// パスワードは環境変数から取得（セキュリティ対策）
		String password = System.getenv("DB_PASSWORD");

		// 3. SQL文（親テーブル jam_sessions への登録） [cite: 1]
		String sql = "INSERT INTO public.session_songs (session_id, song_name, youtube_url, song_review) VALUES (?, ?, ?, ?)";

		try {
			// JDBCドライバの読み込み
			Class.forName("org.postgresql.Driver");

			// 4. DB実行（try-with-resources を使用）
			try (Connection conn = DriverManager.getConnection(url, user, password);
					PreparedStatement ps = conn.prepareStatement(sql)) {

				int sessionId = Integer.parseInt(sessionIdStr);
				ps.setInt(1, sessionId);
				ps.setString(2, song_name);
				ps.setString(3, youtube_url);
				ps.setString(4, song_review);

				ps.executeUpdate();
			}

			// 5. 登録成功後、結果表示JSPへ移動
			request.getRequestDispatcher("/WEB-INF/jsp/register_result.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			// エラー時の簡易表示
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().println("<h3>登録エラーが発生しました</h3>");
			response.getWriter().println("<p>" + e.getMessage() + "</p>");
			response.getWriter().println("<a href='main.html'>戻る</a>");
		}
	}
}