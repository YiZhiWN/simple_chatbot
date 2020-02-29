package com.yango.robot.vo;

public class Bean<E> {
    private int respCode;  //失败为1，成功为0
    private String message; //失败返回原因，成功返回success
    private E data; //返回数据

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
