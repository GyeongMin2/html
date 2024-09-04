public class Post {
    String title;
    String userID;
    String regDate;
    String text;

    public Post() {
    }

    ;

    public Post(String title, String userID, String regDate, String text) {
        this.title = title;
        this.userID = userID;
        this.regDate = regDate;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", userID='" + userID + '\'' +
                ", regDate='" + regDate + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}