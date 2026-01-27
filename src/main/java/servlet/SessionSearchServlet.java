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

@WebServlet("/search")
public class SessionSearchServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/search.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<Session> list = new ArrayList<>();
		String url = "jdbc:postgresql://localhost:5433/postgres";
		String user = "postgres";
		String password = System.getenv("DB_PASSWORD");
		String title = request.getParameter("title");
		String session_date = request.getParameter("session_date");
		String location = request.getParameter("location");
		String review = request.getParameter("review");
		StringBuilder sql = new StringBuilder("SELECT * FROM jam_sessions WHERE 1=1");
		List<String> params = new ArrayList<>();

		// 値が入っている時だけ、SQLに条件を追加していく
		if (title != null && !title.isEmpty()) {
			sql.append(" AND title LIKE ?");
			params.add("%" + title + "%");
		}
		if (session_date != null && !session_date.isEmpty()) {
			sql.append(" AND session_date LIKE ?");
			params.add("%" + session_date + "%");
		}
		if (location != null && !location.isEmpty()) {
			sql.append(" AND location LIKE ?");
			params.add("%" + location + "%");
		}
		if (review != null && !review.isEmpty()) {
			sql.append(" AND review LIKE ?");
			params.add("%" + review + "%");
		}

		sql.append(" ORDER BY session_date DESC");
		System.out.println("データを見つけました！: " + session_date);
		try {
			Class.forName("org.postgresql.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, password);
					PreparedStatement ps = conn.prepareStatement(sql.toString())) {

				// paramsリストに入れた順番で、PreparedStatementにセットする
				for (int i = 0; i < params.size(); i++) {
					ps.setString(i + 1, params.get(i));
				}

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {

						Session s = new Session();
						s.setId(rs.getInt("id"));
						s.setTitle(rs.getString("title"));
						s.setSessionDate(rs.getString("session_date"));
						s.setLocation(rs.getString("location"));
						s.setReview(rs.getString("review"));
						list.add(s);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// JSPにリストを渡す
		request.setAttribute("sessionList", list);
		// list.jsp に画面を表示させる
		request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
	}
}
