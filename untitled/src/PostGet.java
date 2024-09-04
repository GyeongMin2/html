import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostGet {
    Connection connection = DbConnection.getConnection();
    WritePost writePost = new WritePost();
    String sql;

    PostGet() {
    }
//    PostGet(int page, int limitPage) {
//        this.page = page;
//        this.limitPage = limitPage;
//    }

    public Post[] getPost(int page, int limitPage) {
        if (connection != null) {
            try {
                Post[] posts = new Post[limitPage];
                sql = "SELECT TP.POST_TITLE,TP.POST_TEXT,TP.USER_ID,date(TP.REG_DATE) as `date` FROM TBL_POST TP ORDER BY TP.REG_DATE DESC LIMIT ?,?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, page);
                statement.setInt(2, limitPage);
                ResultSet resultSet = statement.executeQuery();
                int idx = 0;
                while (resultSet.next()) {
                    String title = resultSet.getString("POST_TITLE");
                    String text = resultSet.getString("POST_TEXT");
                    String userId = resultSet.getString("USER_ID");
                    String date = resultSet.getString("date");
                    //String title, String userName, String regDate, String text
                    Post postTest = new Post(title, userId, date, text);
                    // Post 객체 생성
                    posts[idx] = postTest;
                    idx++;
                }
                return posts;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            System.out.println("db연결안됨 ㅅㄱ");
            return null;
        }

    }
}