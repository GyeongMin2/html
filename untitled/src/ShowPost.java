//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Scanner;
//
//public class ShowPost {
//    private static final String MYPAGE = "mypost";
//    private static final String ALLPOST = "allpost";
//
//    int page = 1;
//    int limitPage = 5;
//    Scanner sc = new Scanner(System.in);
//    PostGet postGet = new PostGet();
//    WritePost writePost = new WritePost();
//
//    public void showPosting(Member member) {
//        loop1:
//        while (true) {
//            System.out.println("내글보기, mypost or 전체글목록, allpost");
//            String action = sc.next().toLowerCase();
//
//            switch (action) {
//                case MYPAGE:
//                case "내글보기":
//                    if (member.loginStatus) {
//                        handlePost(member, MYPAGE);
//                    } else {
//                        System.out.println("로그인해야 mypage를 볼 수 있음 ㅋㅋ");
//                    }
//                    break;
//
//                case ALLPOST:
//                case "전체글목록":
//                    handlePost(member, ALLPOST);
//                    break;
//
//                case "main":
//                case "메인":
//                    break loop1;
//            }
//        }
//    }
//
//    // 게시글 목록 메서드
//    private void handlePost(Member member, String action) {
//        page = 1;
//        limitPage = 5;
//        Post[] posts;
//
//        if (action.equals(MYPAGE)) {
//            posts = postGet.getMyPost(member.getUserId(), (page - 1) * limitPage, limitPage);
//            getMaxPageNum(page, MYPAGE, member.getUserId());
//            printPosts(posts);
//        } else {
//            posts = postGet.getPost((page - 1) * limitPage, limitPage);
//            getMaxPageNum(page, ALLPOST, null);
//            printPosts(posts);
//        }
//
//        while (true) {
//            System.out.println("게시글 번호를 선택 또는 0 입력하면 뒤로");
//            String nextAction = sc.next().toLowerCase();
//
//            if (nextAction.equals("나가기") || nextAction.equals("gotomain")) {
//                break;
//            } else if (nextAction.equals("다음페이지") || nextAction.equals("next")) {
//                pageing("next", action, member); // 페이징 처리
//                if (action.equals(MYPAGE)) {
//                    posts = postGet.getMyPost(member.getUserId(), (page - 1) * limitPage, limitPage);
//                } else {
//                    posts = postGet.getPost((page - 1) * limitPage, limitPage);
//                }
//                printPosts(posts);
//            } else if (nextAction.equals("이전페이지") || nextAction.equals("prev")) {
//                pageing("prev", action, member); // 페이징 처리
//                if (action.equals(MYPAGE)) {
//                    posts = postGet.getMyPost(member.getUserId(), (page - 1) * limitPage, limitPage);
//                } else {
//                    posts = postGet.getPost((page - 1) * limitPage, limitPage);
//                }
//                printPosts(posts);
//            } else {
//                //예외처리랑 숫자 들어왔을때
//                try {
//                    int postNumber = Integer.parseInt(nextAction); // 숫자 여부 확인 아니면 catch
//                    if (postNumber >= 1 && postNumber <= 5) {
//                        int postId = posts[postNumber - 1].getPostIdx(); // 선택된 게시물의 ID 가져오기
//                        if (action.equals(MYPAGE)) {
//                            modifyPost(postId); // 수정 또는 삭제 로직 처리
//                        } else {
//                            System.out.println("로그인하고 내글만보기 하고 삭제하든가 하샘");
//                        }
//                    } else if (postNumber == 0) {
//                        System.out.println("뒤로");
//                        break;
//                    } else {
//                        System.out.println("게시글 번호를 적든가 0을 적든가 똑바로 적으샘.");
//                    }
//                } catch (NumberFormatException e) {
//                    System.out.println("숫자입력해라");
//                }
//            }
//        }
//    }
//
//    // 수정 또는 삭제 처리
//    private void modifyPost(int postId) {
//        System.out.println("1. 수정");
//        System.out.println("2. 삭제");
//        System.out.println("3. 취소");
//        String editOrDelete = sc.nextLine();
//
//        switch (editOrDelete) {
//            case "1":
//            case "수정":
//                writePost.editPost(postId); // 수정 기능
//                break;
//
//            case "2":
//            case "삭제":
//                deletePost(postId); // 삭제 기능
//                break;
//
//            case "3":
//            case "취소":
//                System.out.println("취소하고 뒤로");
//                break;
//
//            default:
//                System.out.println("똑바로 입력하샘");
//                break;
//        }
//    }
//    // 최대 페이지 수 계산 메서드
//    private void getMaxPageNum(int page, String action, String userId) {
//        int maxPage = 0;
//        String sql;
//        PreparedStatement statement;
//
//        try (Connection connection = DbConnection.getConnection()) {
//            if (action.equals(MYPAGE)) {
//                // 특정 사용자의 게시물에 따른 최대 페이지 수 계산
//                sql = "SELECT CEIL(COUNT(*) / ?) FROM tbl_post WHERE user_id = ?";
//                statement = connection.prepareStatement(sql);
//                statement.setInt(1, limitPage); // limitPage 값을 첫 번째 물음표에 넣음
//                statement.setString(2, userId); // userId를 두 번째 물음표에 넣음
//            } else {
//                // 전체 게시물에 따른 최대 페이지 수 계산
//                sql = "SELECT CEIL(COUNT(*) / ?) FROM tbl_post";
//                statement = connection.prepareStatement(sql);
//                statement.setInt(1, limitPage); // limitPage 값을 첫 번째 물음표에 넣음
//            }
//
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                maxPage = resultSet.getInt(1); // 최대 페이지 수 가져오기
//            }
//
//            // 페이지 출력 로직
//            System.out.print("페이지: ");
//            for (int i = 1; i <= maxPage; i++) {
//                if (i == page) {
//                    // 현재 페이지를 []로 표시
//                    System.out.print("[" + i + "] ");
//                } else {
//                    // 다른 페이지는 일반 출력
//                    System.out.print(i + " ");
//                }
//            }
//            System.out.println();
//
//        } catch (SQLException e) {
//            System.out.println("페이지 계산 중 오류 발생: " + e.getMessage());
//        }
//    }
//
//
//    // 게시글 삭제 메서드
//    private void deletePost(int postIdx) {
//        try (Connection connection = DbConnection.getConnection()) {
//            String sql = "DELETE FROM tbl_post WHERE idx = ?";
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setInt(1, postIdx);
//            int rowsDeleted = statement.executeUpdate();
//
//            if (rowsDeleted > 0) {
//                System.out.println("삭제됨");
//            } else {
//                System.out.println("삭제안됨");
//            }
//        } catch (SQLException e) {
//            System.out.println("삭제안됨ㅅㄱ" + e.getMessage());
//        }
//    }
//    //게시글출력메서드임
//    private void printPosts(Post[] posts) {
//        if (posts == null || posts.length == 0) {
//            System.out.println("게시글없음");
//            return;
//        }
//
//        for (int i = 0; i < posts.length; i++) {
//            if (posts[i] != null) {
//                System.out.println((i + 1) + "제목 ||" + posts[i].getTitle() + " (글번호: " + posts[i].getPostIdx() + ")"+"날짜 ||"+posts[i].getRegDate()+"글내용 ||"+posts[i].getText());
//            }
//        }
//    }
//    //페이징메서드
//    private void pageing(String nextAction, String action, Member member) {
//        if ("next".equals(nextAction)) {
//            page++;
//        } else if ("prev".equals(nextAction) && page > 1) {
//            page--;
//        } else {
//            System.out.println("첫 페이지이거나 마지막 페이지입니다.");
//        }
//    }
//
//
//
//}
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ShowPost {
    private static final String MYPAGE = "mypost";
    private static final String ALLPOST = "allpost";

    int page = 1;
    int limitPage = 5;
    Scanner sc = new Scanner(System.in);
    PostGet postGet = new PostGet();
    WritePost writePost = new WritePost();

    public void showPosting(Member member) {
        loop1:
        while (true) {
            System.out.println("내글보기, mypost or 전체글목록, allpost");
            String action = sc.next().toLowerCase();

            switch (action) {
                case MYPAGE:
                case "내글보기":
                    if (member.loginStatus) {
                        handlePost(member, MYPAGE);
                    } else {
                        System.out.println("로그인해야 mypage를 볼 수 있음 ㅋㅋ");
                    }
                    break;

                case ALLPOST:
                case "전체글목록":
                    handlePost(member, ALLPOST);
                    break;

                case "main":
                case "메인":
                    break loop1;
            }
        }
    }

    // 게시글 목록 메서드
    private void handlePost(Member member, String action) {
        page = 1;
        limitPage = 5;
        Post[] posts;

        if (action.equals(MYPAGE)) {
            posts = postGet.getMyPost(member.getUserId(), (page - 1) * limitPage, limitPage);
            getMaxPageNum(page, MYPAGE, member.getUserId());
            printPosts(posts);
        } else {
            posts = postGet.getPost((page - 1) * limitPage, limitPage);
            getMaxPageNum(page, ALLPOST, null);
            printPosts(posts);
        }

        while (true) {
            String nextAction = sc.next().toLowerCase();
            if (nextAction.equals("나가기") || nextAction.equals("gotomain")) {
                break;
            } else if (nextAction.equals("다음페이지") || nextAction.equals("next")) {
                pageing("next", action, member); // 페이징 처리
                if (action.equals(MYPAGE)) {
                    posts = postGet.getMyPost(member.getUserId(), (page - 1) * limitPage, limitPage);
                    getMaxPageNum(page, MYPAGE, member.getUserId());
                    System.out.println("게시글 번호를 선택 또는 0 입력하면 뒤로");
                } else {
                    posts = postGet.getPost((page - 1) * limitPage, limitPage);
                    getMaxPageNum(page, ALLPOST, null);
                }
                printPosts(posts);
            } else if (nextAction.equals("이전페이지") || nextAction.equals("prev")) {
                pageing("prev", action, member); // 페이징 처리
                if (action.equals(MYPAGE)) {
                    posts = postGet.getMyPost(member.getUserId(), (page - 1) * limitPage, limitPage);
                    getMaxPageNum(page, MYPAGE, member.getUserId());
                    System.out.println("게시글 번호를 선택 또는 0 입력하면 뒤로");
                } else {
                    posts = postGet.getPost((page - 1) * limitPage, limitPage);
                    getMaxPageNum(page, ALLPOST, null);
                }
                printPosts(posts);
            } else {
                // 예외처리랑 숫자 들어왔을때
                try {
                    int postNumber = Integer.parseInt(nextAction); // 숫자 여부 확인 아니면 catch
                    if (postNumber >= 1 && postNumber <= 5) {
                        int postId = posts[postNumber - 1].getPostIdx(); // 선택된 게시물의 ID 가져오기
                        if (action.equals(MYPAGE)) {
                            modifyPost(postId); // 수정 또는 삭제 로직 처리
                        } else {
                            System.out.println("로그인하고 내글만보기 하고 삭제하든가 하샘");
                        }
                    } else if (postNumber == 0) {
                        System.out.println("뒤로");
                        break;
                    } else {
                        System.out.println("게시글 번호를 적든가 0을 적든가 똑바로 적으샘.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("숫자입력해라");
                }
            }
        }
    }

    // 수정 또는 삭제 처리
    private void modifyPost(int postId) {
        System.out.println("1. 수정");
        System.out.println("2. 삭제");
        System.out.println("3. 취소");
        String editOrDelete = sc.next();

        switch (editOrDelete) {
            case "1":
            case "수정":
                writePost.editPost(postId); // 수정 기능
                break;

            case "2":
            case "삭제":
                deletePost(postId); // 삭제 기능
                break;

            case "3":
            case "취소":
                System.out.println("취소하고 뒤로");
                break;

            default:
                System.out.println("똑바로 입력하샘");
                break;
        }
    }

    // 최대 페이지 수 계산 메서드
    private void getMaxPageNum(int page, String action, String userId) {
        int maxPage = 0;
        String sql;
        PreparedStatement statement = null;

        try (Connection connection = DbConnection.getConnection()) {
            if (action.equals(MYPAGE)) {
                // 특정 사용자의 게시물에 따른 최대 페이지 수 계산
                sql = "SELECT CEIL(COUNT(*) / ?) FROM tbl_post WHERE user_id = ?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, limitPage); // limitPage 값을 첫 번째 물음표에 넣음
                statement.setString(2, userId); // userId를 두 번째 물음표에 넣음
            } else {
                // 전체 게시물에 따른 최대 페이지 수 계산
                sql = "SELECT CEIL(COUNT(*) / ?) FROM tbl_post";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, limitPage); // limitPage 값을 첫 번째 물음표에 넣음
            }

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                maxPage = resultSet.getInt(1); // 최대 페이지 수 가져오기
            }

            // 페이지 출력 로직
            System.out.print("페이지: ");
            for (int i = 1; i <= maxPage; i++) {
                if (i == page) {
                    // 현재 페이지를 []로 표시
                    System.out.print("[" + i + "] ");
                } else {
                    // 다른 페이지는 일반 출력
                    System.out.print(i + " ");
                }
            }
            System.out.println();

        } catch (SQLException e) {
            System.out.println("페이지 계산 중 오류 발생: " + e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 게시글 삭제 메서드
    private void deletePost(int postIdx) {
        try (Connection connection = DbConnection.getConnection()) {
            String sql = "DELETE FROM tbl_post WHERE idx = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, postIdx);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("삭제됨");
            } else {
                System.out.println("삭제안됨");
            }
        } catch (SQLException e) {
            System.out.println("삭제안됨ㅅㄱ" + e.getMessage());
        }
    }

    // 게시글 출력 메서드
    private void printPosts(Post[] posts) {
        if (posts == null || posts.length == 0) {
            System.out.println("게시글없음");
            return;
        }

        for (int i = 0; i < posts.length; i++) {
            if (posts[i] != null) {
                System.out.println((i + 1) + "제목 ||" + posts[i].getTitle() + " (글번호: " + posts[i].getPostIdx() + ")" + "날짜 ||" + posts[i].getRegDate() +"글쓴이 ||"+posts[i].getUserID()+"글내용 ||" + posts[i].getText());
            }
        }
    }

    // 페이징 메서드
    private void pageing(String nextAction, String action, Member member) {
        if ("next".equals(nextAction)) {
            page++;
        } else if ("prev".equals(nextAction) && page > 1) {
            page--;
        } else {
            System.out.println("첫페이지또는 마지막페이지임");
        }
    }
}
