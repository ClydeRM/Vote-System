<template>
  <div class="container">
    <h1>VotingPage</h1>
    <button @click="getVoteRecords">Get Vote Records</button>
    <div>
      <input type="text" v-model="state.selectedItems">
      <button @click="submitVoteRecords">Submit Vote Records</button>
    </div>
    <ul>
      <li v-for="(item, index) in voteItems" :key="index">ItemId: {{ item.itemId }} | ItemName: {{ item.itemName }} | TotalCount: {{ item.count }}</li>
    </ul>
    <div>
      <router-link to="/dashboard">Dashboard</router-link>
    </div>
  </div>
</template>

<script>
import { reactive } from 'vue';
import axios from 'axios';
import { store } from '../store';

export default {
  name: 'VoteView',

  setup() {
    const state = reactive({
      selectedItems: "",
    });
    const voteItems = reactive([]);

    const getVoteRecords = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/v1/vote/voteRecords', {
          headers: {
            Authorization: `Bearer ${store.state.accessToken}`,
          },
        });
        voteItems.length = 0;
        voteItems.push(...response.data);
      } catch (error) {
        console.error(error);
      }
    };

    const submitVoteRecords = async () => {
      try {
        const response = await axios.post('http://localhost:8080/api/v1/vote/voteRecords', {
          selectedItems: Array.from(state.selectedItems.split(','), item => parseInt(item)),
        }, {
          headers: {
            Authorization: `Bearer ${store.state.accessToken}`,
          },
        });

        await getVoteRecords();

      } catch (error) {
        console.error(error);
      }
    };

    return {
      state,
      voteItems,
      getVoteRecords,
      submitVoteRecords,
    };
  },
};
</script>
