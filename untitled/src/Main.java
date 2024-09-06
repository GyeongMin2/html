import java.util.Scanner;
public class
    Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Member member = new Member();
        PageUi pageui = new PageUi(member);
        ShowPost showPost = new ShowPost();
        WritePost writePost = new WritePost();
        DbConnection connection = new DbConnection();
        while (true) {
            if (member.loginStatus) {
                // 로그인 됐으면 afterLoginUi 호출
                pageui.afterLoginUi();
            } else {
                // 비로그인시 그냥 mainUi 호출
                pageui.mainUi();
            }
            String action = sc.next().toLowerCase(); // 소문자로 변환해서 비교 대문자로 LOGIN 해도 로그인될듯
            switch (action) {
                case "로그인":
                case "login":
                    if (!member.loginStatus) {
                        LoginPage loginPage = new LoginPage();
                        loginPage.LoginForm(member);
                    } else {
                        System.out.println("이미 로그인됨");
                    }
                    break;

                case "회원가입":
                case "register":
                    if (!member.loginStatus) {
                        RegisterPage regPage = new RegisterPage();
                        regPage.registerForm();
                    } else {
                        System.out.println("이미 로그인됨 로그아웃하고 회원가입하샘");
                    }
                    break;

                case "게시물 목록":
                case "postlist":
                    showPost.showPosting(member);
                    break;

                case "글쓰기":
                case "write":
                    if (member.loginStatus) {
                        // 글쓰기 메서드 추가
                        writePost.write(member.getUserId());
                    } else {
                        System.out.println("로그인하고 글쓰기 가능");
                    }
                    break;

                case "로그아웃":
                case "logout":
                    if (member.loginStatus) {
                        member.logout();
                        System.out.println("로그아웃 되었습니다.");
                    } else {
                        System.out.println("로그인을 해야 로그아웃을 할듯");
                    }
                    break;

                case "종료":
                case "exit":
                    System.out.println("Bye");
                    break;

                case "도움말":
                case "help":
                    pageui.helpUi();
                    break;
                default:
                    System.out.println("똑바로 입력하샘");
                    break;
            }
        }
    }
}