package com.bank.vote.voterecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface VoteRecordRepository extends JpaRepository<VoteRecord, Integer> {
//    Integer countByVoteItemId(Integer itemId);
//    VoteRecord findByUserIdAndVoteItemId(Integer userId, Integer voteItemId);

    @Procedure(name = "countbyvoteitemid")
    Integer countByVoteItemId(@Param("item_id") Integer itemId);

    @Procedure(name = "findbyuseridandvoteitemid")
    VoteRecord findByUserIdAndVoteItemId(@Param("user_id") Integer userId, @Param("item_id") Integer voteItemId);
}
