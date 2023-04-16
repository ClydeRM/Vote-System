package com.bank.vote.votemodule.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VoteRecordResponse {
    private Integer itemId;
    private String itemName;
    private Integer count;
}
