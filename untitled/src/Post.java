public class Post {
    String title;
    String userID;
    String regDate;
    String text;
    int postIdx; // 게시글의 고유 번호, Primary Key

    public Post() {}

    public Post(String title, String userID, String regDate, String text, int postIdx) {
        this.title = title;
        this.userID = userID;
        this.regDate = regDate;
        this.text = text;
        this.postIdx = postIdx;
    }

    @Override
    public String toString() {
        return "Post{" +
                "제목 ='" + title + '\'' +
                ", 글쓴이 ='" + userID + '\'' +
                ", 작성일 ='" + regDate + '\'' +
                ", 내용 ='" + text + '\'' +
                '}';
    }

    // Getter & Setter for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter & Setter for userID
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    // Getter & Setter for regDate
    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    // Getter & Setter for text
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // Getter & Setter for postIdx
    public int getPostIdx() {
        return postIdx;
    }

    public void setPostIdx(int postIdx) {
        this.postIdx = postIdx;
    }
}
