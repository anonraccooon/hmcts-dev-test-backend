package uk.gov.hmcts.reform.dev.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static uk.gov.hmcts.reform.dev.utils.JsoupHtmlSanitizer.sanitize;

@NoArgsConstructor
@Getter
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 100)
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be at most 100 characters")
    private String title;

    @Column(length = 500)
    @Size(max = 500, message = "Description must be at most 500 characters")
    private String description;

    @Column
    private String status;

    @Column
    private String createdDate = LocalDateTime.now().toString().substring(0, 16);

    @Column
    private String dueDate;

    public Task(String title, String description, String status, String dueDate) {
        setTitle(title);
        setDescription(description);
        setStatus(status);
        setDueDate(dueDate);
    }

    public void setTitle(String title) {
        this.title = sanitize(title);
    }

    public void setDescription(String description) {
        this.description = sanitize(description);
    }

    public void setStatus(String status) {
        if (status.equals("Completed")) {
            this.status = "Completed";
        }
        else {
            this.status = null;
        }
    }

    public void setDueDate(String dueDate) {
        if (dueDate != null) {
            this.dueDate = dueDate.trim();
            try {
                LocalDateTime.parse(this.dueDate);
            } catch (Exception e) {
                this.dueDate = null;
            }
        } else {
            this.dueDate = null;
        }
    }


}
