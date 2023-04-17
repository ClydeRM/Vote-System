package com.bank.vote.voteitem;

import com.bank.vote.common.Exceptions.ValueNotAllowException;
import com.bank.vote.common.Exceptions.VoteItemAlreadyExistedException;
import com.bank.vote.common.Exceptions.VoteItemNotFoundException;
import com.bank.vote.voterecord.VoteRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VoteItemService {

    private final VoteItemRepository voteItemRepository;
    private final VoteRecordRepository voteRecordRepository;
    @Autowired
    public VoteItemService(VoteItemRepository voteItemRepository, VoteRecordRepository voteRecordRepository) {
        this.voteItemRepository = voteItemRepository;
        this.voteRecordRepository = voteRecordRepository;
    }

    @Transactional(readOnly= false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public List<VoteItem> getAllVoteItems() {
        return voteItemRepository.findAllVoteItems();
    }

    @Transactional(readOnly= false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public VoteItem addVoteItem(VoteItem voteItem) {
        if (!isValidItemName(voteItem.getItemName())){
            throw new ValueNotAllowException("ItemName not Allow!!");
        }
        Optional<VoteItem> existingVoteItem = voteItemRepository.findVoteItemByItemName(voteItem.getItemName());
        if (existingVoteItem.isPresent())
            throw new VoteItemAlreadyExistedException("Item already existed.");
        return voteItemRepository.save(voteItem);
    }

    @Transactional(readOnly= false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void removeVoteItem(Integer itemId) {
        if(voteItemRepository.findById(itemId).isEmpty()){
            throw new VoteItemNotFoundException("Item not Found.");
        }
        voteRecordRepository.deleteByVoteItemId(itemId);
        voteItemRepository.deleteById(itemId);
    }

    private boolean isValidItemName(String itemName) {
        String regex = "^[\\p{L}A-Za-z0-9]+$";
        return itemName.matches(regex);
    }
}
