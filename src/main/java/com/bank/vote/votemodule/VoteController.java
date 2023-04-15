package com.bank.vote.votemodule;


import com.bank.vote.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vote")
@EnableTransactionManagement
public class VoteController {

    private final VoteRecordService voteRecordService;

    @Autowired
    public VoteController(VoteRecordService voteRecordService) {
        this.voteRecordService = voteRecordService;
    }


    @GetMapping("/voteRecords")
    public List<VoteRecordResult> getVoteItemResults() {
        return voteRecordService.getVoteItemResults();
    }

    @PostMapping("/voteRecords")
    public ResponseEntity<String> voteRecords(@RequestBody VoteRecordRequest voteRecordRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        Integer userId = userDetails.getId();
        boolean isSuccess = voteRecordService.voteRecords(userId, voteRecordRequest.getSelectedItems());
        if (isSuccess) {
            return ResponseEntity.ok().body("Success!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request!");
        }
    }
}
