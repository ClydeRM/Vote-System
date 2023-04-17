<template>
  <h1>Dashboard</h1>
  <div class="container container d-flex flex-column justify-content-center align-items-center">
    <button @click="getAllVoteItems">得到所有投票項目</button>
    <div>
      <input type="text" v-model="itemName" placeholder="New Item" />
      <button @click="addItem">增加新投票項目</button>
    </div>
    <ul>
      <li v-for="(item, index) in voteItems" :key="index">
        {{ item.itemName }}
        <button @click="deleteItem(item.itemId, item.itemName)">刪除</button>
      </li>
    </ul>
    <div>
      <router-link to="/voting">去投票</router-link>
    </div>
  </div>
</template>

<script>
import { reactive, toRefs } from "vue";
import { store } from "../store";
import axios from "axios";
import { checkValue } from '../common/CheckValue';

export default {
  setup() {
    const state = reactive({
      itemName: "",
    });
    const voteItems = reactive([]);

    async function getAllVoteItems() {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/v1/dashboard/voteItems",
          {
            headers: {
              Authorization: `Bearer ${store.state.accessToken}`,
            },
          }
        );
        voteItems.splice(0);
        voteItems.push(...response.data);
      } catch (error) {
        console.error(error);
      }
    }

    async function addItem() {
      
      try {
        const validItemName = checkValue(state.itemName);
        const response = await axios.post(
          "http://localhost:8080/api/v1/dashboard/voteItems",
          {
            itemName: validItemName,
          },
          {
            headers: {
              Authorization: `Bearer ${store.state.accessToken}`,
            },
          }
        );
        voteItems.push(response.data);
        state.itemName = "";
      } catch (error) {
        console.error(error);
        throw error;
      }
    }

    async function deleteItem(itemId, itemName) {
      try {
        await axios.delete(
          `http://localhost:8080/api/v1/dashboard/voteItems/${itemId}`,
          {
            headers: {
              Authorization: `Bearer ${store.state.accessToken}`,
            },
          }
        );
        const itemIndex = voteItems.findIndex(
          (item) => item.itemName === itemName
        );
        if (itemIndex !== -1) {
          voteItems.splice(itemIndex, 1);
        }
      } catch (error) {
        console.error(error);
      }
    }

    return {
      ...toRefs(state),
      voteItems,
      getAllVoteItems,
      addItem,
      deleteItem,
    };
  },
};
</script>
