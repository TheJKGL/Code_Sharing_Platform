package platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ResponseCode {

    private String code;

    private final LocalDateTime date;

    private int time;

    private int views;

    @JsonIgnore
    private boolean timeRestriction = false;

    @JsonIgnore
    private boolean viewsRestriction = false;

    public ResponseCode(SharingCode code) {
        this.time = code.getTime();
        this.views = code.getViews();
        this.code = code.getCode();
        this.date = code.getLocalDate();
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonIgnore
    public LocalDateTime getLocalDate() {
        return date;
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }

    @JsonIgnore
    public boolean isTimeRestriction() {
        return timeRestriction;
    }

    @JsonIgnore
    public void setTimeRestriction(boolean timeRestriction) {
        this.timeRestriction = timeRestriction;
    }

    @JsonIgnore
    public boolean isViewsRestriction() {
        return viewsRestriction;
    }

    @JsonIgnore
    public void setViewsRestriction(boolean viewsRestriction) {
        this.viewsRestriction = viewsRestriction;
    }
}
