package com.bank.vote.voting;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VoteRecordResult {
    private Integer itemId;
    private String itemName;
    private Integer count;
}
