package cn.edu.swpu.cins.studentManagement.async;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by miaomiao on 17-9-25.
 */
public class EventModel {

    private EventType eventType;
    private String actor;
    private String accept;

    private Map<String,String> exts = new HashMap<>();

    public EventModel() {
    }

    public EventModel(EventType eventType) {
        this.eventType = eventType;
    }

    public EventType getEventType() {
        return eventType;
    }

    public EventModel setEventType(EventType eventType) {
        this.eventType = eventType;
        return this;
    }

    public String getActor() {
        return actor;
    }

    public EventModel setActor(String actor) {
        this.actor = actor;
        return this;
    }

    public String getAccept() {
        return accept;
    }

    public EventModel setAccept(String accept) {
        this.accept = accept;
        return this;
    }

    public Map<String, String> getExts() {
        return exts;
    }

    public EventModel setExts(Map<String, String> exts) {
        this.exts = exts;
        return this;
    }
}
