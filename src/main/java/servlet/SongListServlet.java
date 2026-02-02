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
import model.Song;

@WebServlet("/songlist")
public class SongListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Session> list = new ArrayList<>();
		List<Song> solist = new ArrayList<>();
		String url = "jdbc:postgresql://localhost:5433/postgres";
		String user = "postgres";
		String password = System.getenv("DB_PASSWORD");
		String idStr = request.getParameter("id");

		if (idStr != null && !idStr.isEmpty()) {
			try {
				int sessionId = Integer.parseInt(idStr);

				try {
					Class.forName("org.postgresql.Driver");
					try (Connection conn = DriverManager.getConnection(url, user, password)) {

						// 1. セッション本体の情報を取得（一番上に表示する用）
						String sqlSession = "SELECT * FROM jam_sessions WHERE id = ?";
						try (PreparedStatement ps1 = conn.prepareStatement(sqlSession)) {

							ps1.setInt(1, sessionId);

							try (ResultSet rs1 = ps1.executeQuery()) {
								if (rs1.next()) {
									Session s = new Session();
									s.setId(rs1.getInt("id"));
									s.setSessionDate(rs1.getString("session_date"));
									s.setLocation(rs1.getString("location"));
									s.setTitle(rs1.getString("title"));
									list.add(s);
								}
							}
						}

						String sqlSongs = "SELECT * FROM session_songs WHERE session_id = ? ORDER BY id ASC";
						try (PreparedStatement ps2 = conn.prepareStatement(sqlSongs)) {
							ps2.setInt(1, sessionId);
							try (ResultSet rs2 = ps2.executeQuery()) {
								while (rs2.next()) {
									Song so = new Song();
									so.setId(rs2.getInt("id"));
									so.setSongName(rs2.getString("song_name"));
									so.setYoutubeUrl(rs2.getString("youtube_url"));
									so.setSongReview(rs2.getString("song_review"));
									solist.add(so);
								}
							}
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (NumberFormatException e) {
				System.out.println("IDが数字ではありませんでした");
			}
		} else {
			response.sendRedirect("sessionlist");
			return;
		}

		// JSPにリストを渡す
		request.setAttribute("sessionList", list);
		request.setAttribute("songList", solist);
		// list.jsp に画面を表示させる
		request.getRequestDispatcher("/WEB-INF/jsp/songlist.jsp").forward(request, response);
	}
}
