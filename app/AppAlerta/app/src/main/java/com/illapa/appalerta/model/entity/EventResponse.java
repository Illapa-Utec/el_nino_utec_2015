package com.illapa.appalerta.model.entity;

/**
 * Created by emedinaa on 05/09/15.
 */
public class EventResponse extends BaseResponse {

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "EventResponse{" +
                "status="+this.getStatus()+" "+
                "data=" + data +
                '}';
    }
}
