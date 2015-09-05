package com.illapa.appalerta.model.entity;

import java.util.List;

/**
 * Created by emedinaa on 05/09/15.
 */
public class EventsResponse extends BaseResponse {
    private List<EventEntity> data;


    public List<EventEntity> getData() {
        return data;
    }

    public void setData(List<EventEntity> data) {
        this.data = data;
    }
}
