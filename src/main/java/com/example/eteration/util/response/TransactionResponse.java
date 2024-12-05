package com.example.eteration.util.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class TransactionResponse {

    private String status;
    private String approvalCode;

    public TransactionResponse(String status, String approvalCode) {
        this.status = status;
        this.approvalCode = approvalCode;
    }
}
