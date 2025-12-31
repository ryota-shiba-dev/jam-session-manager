package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ジャムセッション情報をDBに登録するサーブレット
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 文字化け対策
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        // 1. フォーム(main.html)からの値を受け取る 
        String title = request.getParameter("title");
        String session_date = request.getParameter("session_date");
        String location = request.getParameter("location");
        String review = request.getParameter("review");

        // 2. DB接続情報の準備
        String url = "jdbc:postgresql://localhost:5433/postgres";
        String user = "postgres";
        // パスワードは環境変数から取得（セキュリティ対策）
        String password = System.getenv("DB_PASSWORD"); 
        
        // 3. SQL文（親テーブル jam_sessions への登録） [cite: 1]
        String sql = "INSERT INTO public.jam_sessions (title, session_date, location, review) VALUES (?, ?, ?, ?)";

        try {
            // JDBCドライバの読み込み
            Class.forName("org.postgresql.Driver");

            // 4. DB実行（try-with-resources を使用）
            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, title);
                // HTMLの日付(yyyy-MM-dd)をDBのTIMESTAMP型に変換
                ps.setTimestamp(2, Timestamp.valueOf(session_date + " 00:00:00"));
                ps.setString(3, location);
                ps.setString(4, review);

                ps.executeUpdate();
            }
            
            // 5. 登録成功後、結果表示JSPへ移動
            request.getRequestDispatcher("register_result.jsp").forward(request, response);

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