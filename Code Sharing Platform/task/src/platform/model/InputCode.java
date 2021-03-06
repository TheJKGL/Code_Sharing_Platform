package platform.model;

import org.springframework.stereotype.Component;

@Component
public class InputCode {

    private String code;

    private int time;

    private int views;

    public String getCode() {
        return code;
    }

    public int getTime() {
        return time;
    }

    public int getViews() {
        return views;
    }
}
