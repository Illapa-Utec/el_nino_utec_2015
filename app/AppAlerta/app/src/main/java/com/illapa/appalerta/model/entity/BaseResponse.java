package com.illapa.appalerta.model.entity;

/**
 * Created by emedinaa on 05/09/15.
 */
public class BaseResponse {
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess()
    {
        if(this.status==1)
        {
            return true;
        }
        return false;
    }
}
