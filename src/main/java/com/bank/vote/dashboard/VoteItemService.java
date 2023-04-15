package com.bank.vote.dashboard;

import com.bank.vote.voterecord.VoteRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import com.bank.vote.voteitem.VoteItemRepository;
import com.bank.vote.voteitem.VoteItem;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VoteItemService {

    private final VoteItemRepository voteItemRepository;
    private final VoteRecordRepository voteRecordRepository;
    @Autowired
    public VoteItemService(VoteItemRepository voteItemRepository, VoteRecordRepository voteRecordRepository) {
        this.voteItemRepository = voteItemRepository;
        this.voteRecordRepository = voteRecordRepository;
    }

    public List<VoteItem> getAllVoteItems() {
        return voteItemRepository.findAll();
    }

    public VoteItem addVoteItem(VoteItem voteItem) {
        return voteItemRepository.save(voteItem);
    }

    @Transactional(readOnly= false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void removeVoteItem(Integer itemId) throws ChangeSetPersister.NotFoundException {
        voteRecordRepository.deleteByVoteItemId(itemId);
        voteItemRepository.deleteById(itemId);
    }
}
