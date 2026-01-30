package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    // ======= CHỈNH Ở ĐÂY THEO MÁY CỦA BẠN =======
    private static final String HOST = "localhost";
    private static final String PORT = "3306";

    // TÊN DATABASE trong MySQL Workbench (schema)
    // Ví dụ bạn tạo: quanlytiemnet_simple
    private static final String DB_NAME = "quanlytiemnet_simple";

    private static final String USER = "root";
    private static final String PASS = "12345"; // đổi theo máy bạn
    // ============================================

    private static final String URL =
            "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME
                    + "?useSSL=false"
                    + "&allowPublicKeyRetrieval=true"
                    + "&serverTimezone=UTC"
                    + "&characterEncoding=utf8"
                    + "&useUnicode=true";

    static {
        // Nạp driver (Connector/J 8+ thường tự nạp, nhưng để chắc chắn)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Không tìm thấy MySQL JDBC Driver!", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static void closeQuietly(AutoCloseable c) {
        if (c != null) {
            try { c.close(); } catch (Exception ignored) {}
        }
    }
}
