package com.bank.vote.votemodule.DTO;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteRecordResponse {
    private Integer itemId;
    private String itemName;
    private Integer count;
}
