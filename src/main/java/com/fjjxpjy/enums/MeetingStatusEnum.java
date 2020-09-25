package com.fjjxpjy.enums;

/**
 * @author fangjj  //会议状态 0:未开始， 1：进行中 2：已结束
 * @date 2020/9/25
 * @description
 */
public enum  MeetingStatusEnum  {
    NO_START(0),
    RUNNING(1),
    END(2);

    private Integer value;

    MeetingStatusEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
