package com.example.casestudymd3_shopxanh.model;

import java.util.Date;

public class Orderr {
    private int id;
    private int accountId;
    private Date createDate;
    private int status;

    public Orderr() {
    }

    public Orderr(int accountId, Date createDate, int status) {
        this.accountId = accountId;
        this.createDate = createDate;
        this.status = status;
    }

    public Orderr(int id, int accountId, Date createDate, int status) {
        this.id = id;
        this.accountId = accountId;
        this.createDate = createDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public java.sql.Date getCreateDate() {
        return (java.sql.Date) createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
