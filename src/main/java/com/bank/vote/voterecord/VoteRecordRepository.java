package com.bank.vote.voterecord;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRecordRepository extends JpaRepository<VoteRecord, Integer> {
    Integer countByVoteItemId(Integer itemId);
    VoteRecord findByUserIdAndVoteItemId(Integer userId, Integer voteItemId);
}
