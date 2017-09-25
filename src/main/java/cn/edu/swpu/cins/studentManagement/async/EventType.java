package cn.edu.swpu.cins.studentManagement.async;

/**
 * Created by miaomiao on 17-9-25.
 */
public enum EventType {

    LIKE(0);

    private int value;

    EventType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
