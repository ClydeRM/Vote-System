package com.bank.vote.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bank.vote.voteitem.VoteItemRepository;
import com.bank.vote.voteitem.VoteItem;

import java.util.List;

@Service
public class VoteItemService {

    private final VoteItemRepository voteItemRepository;
    @Autowired
    public VoteItemService(VoteItemRepository voteItemRepository ) {
        this.voteItemRepository = voteItemRepository;
    }

    public List<VoteItem> getAllVoteItems() {
        return voteItemRepository.findAll();
    }

    public VoteItem addVoteItem(VoteItem voteItem) {
        return voteItemRepository.save(voteItem);
    }

    public void removeVoteItem(Integer itemId) {
        voteItemRepository.deleteById(itemId);
    }
}
