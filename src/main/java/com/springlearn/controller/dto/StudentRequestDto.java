package com.springlearn.controller.dto;

import java.util.HashMap;

public class StudentRequestDto {

    private String name;

    private Long uin;

    HashMap<String, Object> questionably;

    public HashMap<String, Object> getQuestionably() {
        return questionably;
    }

    public void setQuestionably(HashMap<String, Object> questionably) {
        this.questionably = questionably;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUin() {
        return uin;
    }

    public void setUin(Long uin) {
        this.uin = uin;
    }

    public String getQuestionablyToString() {
        StringBuilder mapAsString = new StringBuilder("{");
        for (String key : questionably.keySet()) {
            mapAsString.append("\"").append(key).append("\"")
                    .append(":").
                    append("\"").append(questionably.get(key)).append("\"")
                    .append(",");
        }
        mapAsString.delete(mapAsString.length() - 1, mapAsString.length()).append("}");
        return mapAsString.toString();
    }
}
