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

@WebServlet("/list")
public class SessionListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<Session> list = new ArrayList<>();
        String url = "jdbc:postgresql://localhost:5433/postgres";
        String user = "postgres";
        String password = System.getenv("DB_PASSWORD");

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = conn.prepareStatement("SELECT * FROM jam_sessions ORDER BY session_date DESC");
                 ResultSet rs = ps.executeQuery()) {

            	System.out.println("SQLを実行しました");
            	
                while (rs.next()) {
                	System.out.println("データを見つけました！: " + rs.getString("title"));
                    Session s = new Session();
                    s.setId(rs.getInt("id"));
                    s.setTitle(rs.getString("title"));
                    s.setSessionDate(rs.getString("session_date"));
                    s.setLocation(rs.getString("location"));
                    s.setReview(rs.getString("review"));
                    list.add(s);
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
