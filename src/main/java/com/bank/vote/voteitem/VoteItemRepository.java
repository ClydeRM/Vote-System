package com.bank.vote.voteitem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VoteItemRepository extends JpaRepository<VoteItem, Integer> {

    @Procedure(value = "find_vote_item_by_item_name")
    Optional<VoteItem> findVoteItemByItemName(@Param("ItemName") String ItemName);

}
