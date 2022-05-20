package edu.eci.ieti.betsgo.entity;

public class GenericResponse<T> {

    private String type;
    private int answer;
    private String message;
    private T body;

    public GenericResponse() {
        type = "";
        answer = 0;
        message = "";
        body = null;
    }

    public GenericResponse(String bodyType, Object body) {
        type = "";
        answer = 0;
        message = "";
        this.body = null;
    }

    public GenericResponse(String type, int answer, String message, T body) {
        this.type = type;
        this.answer = answer;
        this.message = message;
        this.body = body;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}