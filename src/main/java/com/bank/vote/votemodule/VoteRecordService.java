package com.bank.vote.votemodule;


import com.bank.vote.common.Exceptions.VoteItemNotFoundException;
import com.bank.vote.user.User;
import com.bank.vote.user.UserRepository;
import com.bank.vote.voteitem.VoteItem;
import com.bank.vote.voteitem.VoteItemRepository;
import com.bank.vote.votemodule.DTO.VoteRecordResponse;
import com.bank.vote.voterecord.VoteRecord;
import com.bank.vote.voterecord.VoteRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VoteRecordService {

    private final VoteRecordRepository voteRecordRepository;
    private final VoteItemRepository voteItemRepository;
    private final UserRepository userRepository;

    @Autowired
    public VoteRecordService(VoteRecordRepository voteRecordRepository, VoteItemRepository voteItemRepository, UserRepository userRepository) {
        this.voteRecordRepository = voteRecordRepository;
        this.voteItemRepository = voteItemRepository;
        this.userRepository = userRepository;
    }

    public List<VoteRecordResponse> getVoteItemResults() {
        List<VoteItem> voteItems = voteItemRepository.findAll();
        List<VoteRecordResponse> results = new ArrayList<>();

        for (VoteItem item : voteItems) {
            Integer count = voteRecordRepository.countByVoteItemId(item.getItemId());
            results.add(new VoteRecordResponse(item.getItemId(), item.getItemName(), count));
        }

        return results;
    }

    @Transactional(readOnly= false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public boolean voteRecords(int userId, List<Integer> selectedItems) {
        // Check user、 voteItem is existed
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        for (Integer itemId : selectedItems) {
            Optional<VoteItem> item = voteItemRepository.findById(itemId);
            if (item.isEmpty()){
                throw new VoteItemNotFoundException("Item not found");
            }
            Optional<VoteRecord> record = Optional.ofNullable(voteRecordRepository.findByUserIdAndVoteItemId(userId, itemId));
            // Can't re-poll same VoteItem
            if (record.isPresent())
                continue;
            VoteRecord voteRecord = new VoteRecord();
            voteRecord.setUserId(userId);
            voteRecord.setUsername(existingUser.get().getRealUsername());
            voteRecord.setVoteItemId(itemId);
            voteRecordRepository.save(voteRecord);
        }
        return true;
    }

    @Transactional(readOnly= false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void deleteRecord(int itemId){
        Optional<VoteItem> existingItem = voteItemRepository.findById(itemId);
        if (existingItem.isEmpty()){
            throw new VoteItemNotFoundException("Item not found.");
        }
        voteRecordRepository.deleteByVoteItemId(itemId);
    }
}
