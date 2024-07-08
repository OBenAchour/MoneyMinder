
package Models;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class Reclamation {
    private int id;
    private String title;
    private String description;
    private ErrorCategory errorCategory;
    private Status status;
    private List<String> attachments;
    private String creationDate;
    private int userId;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Reclamation(int id, String title,String description, ErrorCategory errorCategory, Status status, List<String> attachments, int userId, String creationDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.errorCategory = errorCategory;
        this.status = status;
        this.attachments = attachments;
        this.userId = userId;
        this.creationDate = creationDate;
    }

    public Reclamation() {}

    public Reclamation( String title,String description, ErrorCategory errorCategory, int userId) {
        this.title = title;
        this.description = description;
        this.errorCategory = errorCategory;
        this.status = Status.IN_PROGRESS;
        this.userId = userId;
        this.attachments = Arrays.asList("log1.txt", "log2.txt");

    }
    public Reclamation(String title,String description, ErrorCategory errorCategory, Status status, List<String> attachments, int userId) {
        this.title = title;
        this.description = description;
        this.errorCategory = errorCategory;
        this.status = status;
        this.attachments = attachments;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public ErrorCategory getErrorCategory() { return errorCategory; }
    public void setErrorCategory(ErrorCategory errorCategory) { this.errorCategory = errorCategory; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public List<String> getAttachments() { return attachments; }
    public void setAttachments(List<String> attachments) { this.attachments = attachments; }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", errorCategory=" + errorCategory +
                ", status=" + status +
                ", attachments=" + attachments +
                ", creationDate='" + creationDate + '\'' +
                ", userId=" + userId +
                '}';
    }
}




