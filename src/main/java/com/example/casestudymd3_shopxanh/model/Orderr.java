package com.example.casestudymd3_shopxanh.model;

import java.util.Date;

public class Orderr {
    private Long id;
    private Long accountId;
    private Date createDate;
    private int status;

    public Orderr() {
    }

    public Orderr(Long accountId, Date createDate, int status) {
        this.accountId = accountId;
        this.createDate = createDate;
        this.status = status;
    }

    public Orderr(Long id, Long accountId, Date createDate, int status) {
        this.id = id;
        this.accountId = accountId;
        this.createDate = createDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
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
