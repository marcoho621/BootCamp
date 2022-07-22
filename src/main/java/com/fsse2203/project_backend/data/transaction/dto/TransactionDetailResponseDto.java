package com.fsse2203.project_backend.data.transaction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fsse2203.project_backend.data.PaymentStatus;
import com.fsse2203.project_backend.data.transaction.TransactionDetailData;
import com.fsse2203.project_backend.data.transaction.TransactionProductDetailData;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDetailResponseDto {
    @JsonPropertyOrder({"transaction_id","User_id","dateTime","status","total","items"})
    @JsonProperty("transaction_id")
    private Integer tid;
    @JsonProperty("User_id")
    private Integer uid;
    @JsonProperty("dateTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HH:mm:ss")
    private LocalDateTime dateTime;
    @JsonProperty("status")
    private PaymentStatus status;
    @JsonProperty("total")
    private BigDecimal total;
    @JsonProperty("items")
    private List<TransactionProductResponseDto> items = new ArrayList<>();

    public TransactionDetailResponseDto(TransactionDetailData data) {
        setTid(data.getTid());
        setUid(data.getUser().getUid());
        setStatus(data.getStatus());
        setTotal(data.getTotal());
        setItems(data.getItems());
        setDateTime(data.getDateTime());
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<TransactionProductResponseDto> getItems() {
        return items;
    }

    public void setItems(List<TransactionProductDetailData> items) {
        for(TransactionProductDetailData cartItem : items) {
            this.items.add(new TransactionProductResponseDto(cartItem));
        }
    }
}
