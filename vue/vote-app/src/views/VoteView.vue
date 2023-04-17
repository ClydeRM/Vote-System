<template>
  <h1>VotingPage</h1>
  <div class="container container d-flex flex-column justify-content-center align-items-center">
    <button @click="getVoteRecords">目前投票數紀錄</button>
    <div>
      <input type="text" v-model="state.selectedItems">
      <button @click="submitVoteRecords">投票</button>
    </div>
      <table class="table table-striped">
        <thead>
          <tr>
            <th class="px-4 text-center">項目ID</th>
            <th class="px-4 text-center">項目名稱</th>
            <th class="px-4 text-center">投票總數</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in voteItems" :key="index">
            <td class="px-4 text-center">{{ item.itemId }}</td>
            <td class="px-4 text-center">{{ item.itemName }}</td>
            <td class="px-4 text-center">{{ item.count }}</td>
          </tr>
        </tbody>
      </table>
    <div>
      <router-link to="/dashboard">Dashboard</router-link>
    </div>
  </div>
</template>

<script>
import { reactive } from 'vue';
import axios from 'axios';
import { store } from '../store';
import { checkValue } from '../common/CheckValue';

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
        const validStatement = checkValue(state.selectedItems);
        await axios.post('http://localhost:8080/api/v1/vote/voteRecords', {
          selectedItems: Array.from(validStatement.split(','), item => parseInt(item)),
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
