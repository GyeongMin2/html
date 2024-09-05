import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class WritePost {
    Connection connection = DbConnection.getConnection();
    Scanner sc = new Scanner(System.in);
    PageUi pageUi=new PageUi();
    String[] writeInfoArr = {"글제목 입력", "글 내용 입력"};

    public void write(String memberId) {
        String postTitle = null;
        String postText = null;
        while (true) {
            for (int i = 0; i < writeInfoArr.length; i++) {
                pageUi.writePostUi(writeInfoArr[i]); // 글쓰기 정보 출력
                String input = sc.nextLine();
                if(input.equals("이전")||input.equals("back")){
                    break;
                }else {
                    if (i == 0) {
                        postTitle = input;
                    } else if (i == 1) {
                        postText = input;
                    }
                }
            }
            if (postTitle != null && postText != null) {
                try {
                    String sql = "INSERT INTO tbl_post (user_id,post_title,post_text)VALUES(?,?,?)";
                    PreparedStatement statement = connection.prepareStatement(sql);

                    statement.setString(1, memberId);
                    statement.setString(2, postTitle);
                    statement.setString(3, postText);

                    int rowsInserted = statement.executeUpdate();

                    if (rowsInserted>0) {
                        System.out.println("글쓰기 성공~!");
                    } else {
                        System.out.println("글쓰기 실패한듯 ㅋㅋ");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
    public void editPost(int postId) {
        try {
            System.out.println("수정할 제목");
            String newTitle = sc.nextLine();

            System.out.println("수정할내용");
            String newContent = sc.nextLine();

            String sql = "UPDATE tbl_post SET post_title = ?, post_text = ? WHERE idx = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newTitle);
            statement.setString(2, newContent);
            statement.setInt(3, postId);

            int rowsUpdated = statement.executeUpdate();

            // 업데이트됐는지 안됐는지
            if (rowsUpdated > 0) {
                System.out.println("업데이트됨");
            } else {
                System.out.println("업데이트안됨 ㅋㅋ");
            }
        } catch (SQLException e) {
            System.out.println("업데이트 안됨 ㅋㅋ" + e.getMessage());
        }
    }
}
