package com.jwt.define;

/**
 * Created by lzp on 2017/9/16.
 */
public class ResultBean<T> {
    private String state;
    private T msg;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(T msg) {
        this.msg = msg;
    }

    public ResultBean(String state, T msg) {
        this.state = state;
        this.msg = msg;
    }

    public ResultBean() {
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "state='" + state + '\'' +
                ", msg=" + msg +
                '}';
    }
}
