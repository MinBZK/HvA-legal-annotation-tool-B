<template>
  <div class="container-fluid homepage">
    <div class="row">
      <div class="col-12 text-center mt-5">
        <h3>Annotation tools</h3>
        <p>
          Dit is een annotatie tool voor het toevoegen van annotaties aan
          specifieke wetteksten, voeg een XML bestand toe om verder te gaan.
        </p>
      </div>
    </div>
    <div class="row">
      <div class="col-12 d-flex flex-column align-items-center">
        <div class="button-container">
          <button type="button" class="global-button my-2" @click="showModal">
            Upload XML
          </button>
          <button class="global-button my-2" @click="redirectToXmlData">View XML Data</button>
          <button class="global-button my-2" @click="redirectToIndexPage">All files</button>
          <button class="global-button my-2" @click="redirectToAnnotationList">All annotations</button>
        </div>
      </div>
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
    redirectToXmlData() {
      this.$router.push({ name: 'XmlData' });
    },
    redirectToIndexPage() {
      this.$router.push({ name: 'IndexPage' });
    },
    redirectToAnnotationList() {
      this.$router.push({ name: "AnnotationListVue" })
    }
  },
  data() {
    return {
      isModalVisible: false,
    };
  },
};
</script>

<style scoped>
h3 {
  margin: 40px 0 0;
}

a {
  color: #42b983;
}

.homepage {
  height: 100vh;
  background-color: #343a40;
  color: #f8f9fa;
  font-family: 'Roboto', sans-serif;
}

h3 {
  font-size: 2.5rem;
}

p {
  font-size: 1.25rem;
}

.button-container {
  display: flex;
  flex-direction: column;
  align-items: center;
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
}</style>
