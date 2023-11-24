<template>
  <div style="width: 100%; overflow: hidden">
    <div style="width: 50%; float:left;">
      <h3>Annotation tools</h3>
      <p>
        Dit is een annotatie tool voor het toevoegen van annotaties aan
        specifieke wetteksten, voeg een XML bestand toe om verder te gaan
      </p>
    </div>
    <div style="width: 40%; overflow: hidden; text-align: center; justify-content: center; height: 100%;">
      <button type="button" class="btn" @click="showModal">
        Upload een nieuwe wet
      </button>
    </div>
  </div>
  <UploadModal v-show="isModalVisible" @close="closeModal" />
</template>

<script>
import UploadModal from "./UploadModal.vue";

export default {
  name: "HelloWorld",
  components: {
    UploadModal,
  },
  props: {
    msg: String,
  },
  methods: {
    showModal() {
      this.isModalVisible = true;
    },
    closeModal() {
      this.isModalVisible = false;
    },
  },
  data() {
    return {
      isModalVisible: false,
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
