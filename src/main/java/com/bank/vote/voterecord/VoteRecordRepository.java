package com.bank.vote.voterecord;

import com.bank.vote.voteitem.VoteItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoteRecordRepository extends JpaRepository<VoteRecord, Integer> {
    @Procedure(value = "count_by_vote_item_id")
    Integer countByVoteItemId(@Param("voteItemId") Integer voteItemId);

    @Procedure(value = "find_by_user_id_and_vote_item_id")
    VoteRecord findByUserIdAndVoteItemId(@Param("userId") Integer userId, @Param("voteItemId") Integer voteItemId);

    @Procedure(value = "delete_by_vote_item_id")
    void deleteByVoteItemId(@Param("voteItemId") Integer voteItemId);

    @Procedure(value = "find_all_vote_records")
    List<VoteItem> findAllVoteRecords();
}
