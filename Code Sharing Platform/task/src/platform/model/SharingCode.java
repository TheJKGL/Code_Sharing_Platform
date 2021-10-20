package platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
public class SharingCode {

    @JsonIgnore
    @Id
    private String id;

    private String code;

    private final LocalDateTime date;

    private int time;

    private int views;

    /*@JsonIgnore
    private boolean isRestricted = false;*/

    public SharingCode() {
        this.date = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
    }

    public SharingCode(String code) {
        this.code = code;
        this.date = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }

    @JsonIgnore
    public LocalDateTime getLocalDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getTime() {
        return time;
    }

    public int getViews() {
        return views;
    }

   /* @JsonIgnore
    public boolean isRestricted() {
        return isRestricted;
    }

    @JsonIgnore
    public void setRestriction(boolean arg) {
        this.isRestricted = arg;
    }*/
}
