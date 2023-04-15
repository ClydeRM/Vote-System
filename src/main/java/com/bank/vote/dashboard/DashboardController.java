package com.bank.vote.dashboard;

import com.bank.vote.voteitem.VoteItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    private final VoteItemService voteItemService;

    @Autowired
    public DashboardController(VoteItemService voteItemService) {
        this.voteItemService = voteItemService;
    }

    @GetMapping("/voteItems")
    public List<VoteItem> getAllVoteItem() {
        return voteItemService.getAllVoteItems();
    }

    @PostMapping("/voteItems")
    public ResponseEntity<VoteItem> addVoteItem(@RequestBody VoteItem voteItem) {
        VoteItem createdVoteItem = voteItemService.addVoteItem(voteItem);
        return ResponseEntity.ok(createdVoteItem);
    }

    @DeleteMapping("/voteItems/{itemId}")
    public ResponseEntity<Void> removeVoteItem(@PathVariable Integer itemId) {
        voteItemService.removeVoteItem(itemId);
        return ResponseEntity.noContent().build();
    }

}
