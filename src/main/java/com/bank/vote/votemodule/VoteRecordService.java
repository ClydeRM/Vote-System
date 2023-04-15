package com.bank.vote.votemodule;


import com.bank.vote.user.User;
import com.bank.vote.user.UserRepository;
import com.bank.vote.voteitem.VoteItem;
import com.bank.vote.voteitem.VoteItemRepository;
import com.bank.vote.voterecord.VoteRecord;
import com.bank.vote.voterecord.VoteRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<VoteRecordResult> getVoteItemResults() {
        List<VoteItem> voteItems = voteItemRepository.findAll();
        List<VoteRecordResult> results = new ArrayList<>();

        for (VoteItem item : voteItems) {
            Integer count = voteRecordRepository.countByVoteItemId(item.getItemId());
            results.add(new VoteRecordResult(item.getItemId(), item.getItemName(), count));
        }

        return results;
    }

    @Transactional(readOnly= false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public boolean voteRecords(int userId, List<Integer> selectedItems) {
        // Check user、 voteItem is existed
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()){
            return false;
        }

        for (Integer itemId : selectedItems) {
            Optional<VoteItem> item = voteItemRepository.findById(itemId);
            if (item.isEmpty()){
                return false;
            }
            Optional<VoteRecord> record = Optional.ofNullable(voteRecordRepository.findByUserIdAndVoteItemId(userId, itemId));
            if (record.isPresent())
                continue;
            VoteRecord voteRecord = new VoteRecord();
            voteRecord.setUserId(userId);
            voteRecord.setVoteItemId(itemId);
            voteRecordRepository.save(voteRecord);
        }
        return true;
    }

    @Transactional(readOnly= false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void deleteRecord(int itemId){
        Optional<VoteItem> item = voteItemRepository.findById(itemId);
        if (item.isEmpty()){
            return;
        }
        voteRecordRepository.deleteByVoteItemId(itemId);
    }
}
