<template>
  <div style="width: 100%; overflow: hidden; padding-left: 5%">
    <button type="button" class="btn btn-primary" @click="showModal">Show modal
    </button>
    <input type="text" v-model="word">
    <input type="text" v-model="startIndex">
    <input type="text" v-model="endIndex">
    <input type="text" v-model="xmlId">
  </div>
  <DemoModal v-show="isEditing" @close="closeModal"  :word="word" :startIndex="startIndex" :endIndex="endIndex" :xmlId="xmlId"/>
</template>

<script>
import DemoModal from './anno_edit/DemoModal.vue';

export default {
  name: "HelloWorld",
  components: {
    DemoModal,
  },
  props: {
    msg: String,
  },
  methods: {
    showModal() {
      this.isEditing = true;
    },
    closeModal() {
      this.isEditing = false;
    },
    redirectToXmlData() {
      this.$router.push({ name: "XmlData" });
    },
  },
  data() {
    return {
      isEditing: false,
      word: null,
      startIndex: 0,
      endIndex: 0,
      xmlId: 0,

    };
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}

.modal-backdrop {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.3);
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
