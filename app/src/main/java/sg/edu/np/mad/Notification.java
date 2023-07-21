package sg.edu.np.mad;

public class Notification {
    String Title;
    String Content;

    public Notification() {
    }

    public Notification(String title, String content) {
        Title = title;
        Content = content;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
