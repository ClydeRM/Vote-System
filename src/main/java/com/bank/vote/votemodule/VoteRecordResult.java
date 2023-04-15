package com.bank.vote.votemodule;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VoteRecordResult {
    private Integer itemId;
    private String itemName;
    private Integer count;
}
