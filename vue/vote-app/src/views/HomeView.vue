<template>
  <div>
    <h1>Dashboard</h1>
    <button @click="getAllVoteItems">Get All Vote Items</button>
    <br />
    <input type="text" v-model="itemName" placeholder="New Item" />
    <button @click="addItem">Add New Item</button>
    <br />
    <ul>
      <li v-for="(item, index) in voteItems" :key="index">
        {{ item.itemName }}
        <button @click="deleteItem(item.itemId, item.itemName)">Delete</button>
      </li>
    </ul>
    <div>
      <router-link to="/voting">投票</router-link>
    </div>
  </div>
</template>

<script>
import { reactive, toRefs, ref } from "vue";
import { store } from "../store";
import axios from "axios";

export default {
  setup() {
    const state = reactive({
      itemName: "",
    });
    const voteItems = ref([]);

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
        voteItems.value.splice(0);
        voteItems.value.push(...response.data);
      } catch (error) {
        console.error(error);
      }
    }

    async function addItem() {
      try {
        const response = await axios.post(
          "http://localhost:8080/api/v1/dashboard/voteItems",
          {
            itemName: state.itemName,
          },
          {
            headers: {
              Authorization: `Bearer ${store.state.accessToken}`,
            },
          }
        );
        voteItems.value.push(response.data);
        state.itemName = "";
      } catch (error) {
        console.error(error);
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
        const itemIndex = voteItems.value.findIndex(
          (item) => item.itemName === itemName
        );
        if (itemIndex !== -1) {
          voteItems.value.splice(itemIndex, 1);
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
