package entity;

import java.util.Date;
public class News {
    private int postId;
    private String userId;
    private String title;
    private String photoUrl;
    private String content;
    private Date createdDate;
    private String contentType;

    public News() {}

    public News(int postId, String userId, String title, String photoUrl, String content, Date createdDate, String contentType) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.photoUrl = photoUrl;
        this.content = content;
        this.createdDate = createdDate;
        this.contentType = contentType;
    }

    // Getters và Setters
    public int getPostId() { return postId; }
    public void setPostId(int postId) { this.postId = postId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }

    public String getContentType() { return contentType; }
    public void setContentType(String contentType) { this.contentType = contentType; }
}