import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ShowPost {
    int page = 1; // 페이징
    int limitPage = 5; //몇개씩 갖고올건지
    Scanner sc = new Scanner(System.in);
    PostGet postGet = new PostGet();

    ShowPost() {
    }

    public void showPosting() {
        printPosts(postGet.getPost((page - 1) * limitPage, limitPage));
        getMaxPageNum(page);
        while (true) {
            String action = sc.next();
            if (action.equals("다음") || action.equals("next")) {
                page++;
                Post[] posts = postGet.getPost((page - 1) * limitPage, limitPage); // 페이지 업데이트
                if (posts[0] == null) {
                    System.out.println("마지막 페이지");
                    page--;
                } else {
                    printPosts(posts); // 배열 출력
                }
            } else if (action.equals("이전") || action.equals("previous")) {
                if (page > 1) {
                    page--;
                    Post[] posts = postGet.getPost((page - 1) * limitPage, limitPage); // 페이지 업데이트
                        printPosts(posts); // 배열 출력
                } else {
                    System.out.println("첫 번째 페이지임");
                }
            } else if (action.equals("검색") || action.equals("gotopage")) {
                System.out.println("몇페이지 볼거임?");
                page = sc.nextInt();
                Post[] posts = postGet.getPost((page - 1) * limitPage, limitPage); // 페이지 업데이트
                if (posts[0] == null) {
                    System.out.println("마지막 페이지");
                } else {
                    printPosts(posts); // 배열 출력
                }
            } else if (action.equals("main") || action.equals("메인")) {
                page = 1;
                limitPage = 5; // 초기화
                break;
            }
            getMaxPageNum(page);
        }
    }

    public int getMaxPageNum(int page) {
        Connection connection = DbConnection.getConnection();
        int maxPage;
        if (connection != null) {
            try {
                String sql = "SELECT CEIL(COUNT(*) / ?) AS max_page FROM TBL_POST";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, limitPage);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    maxPage = resultSet.getInt("max_page");
                    for (int i = 1; i <= maxPage; i++) {
                        if (i==page){
                            System.out.print("["+i+"]     ");
                        }else{
                            System.out.print(i + "     ");
                        }
                    }
                    System.out.println("");
                    return maxPage;
                } else {
                    return 0;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("db연결안됨 ㅅㄱ");
        }
        return 0;
    }

    public void printPosts(Post[] posts) {
        for (Post post : posts) {
            if (post != null) {
                System.out.println(post.toString());
            }
        }
    }
}
