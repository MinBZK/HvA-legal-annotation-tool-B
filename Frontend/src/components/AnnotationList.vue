<template>
  <div class="container-fluid">
    <UploadModal v-show="isModalVisible" @close="closeModal" />
    <div class="header-container d-flex align-items-center mb-3">
      <h2 class="h4 me-3 mr-2">Alle annotaties</h2>
    </div>

    <table class="table">
      <thead>
        <tr>
          <th scope="col">Annotatie</th>
          <th scope="col">Beschrijving</th>
          <th scope="col">Document</th>
          <th scope="col"></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(annotation, index) in annotations" :key="index">
          <td>
            {{ annotation["annotation"]["word"] || "Geen woord gevonden" }}
          </td>
          <td>{{ annotation["annotation"]["description"] }}</td>
          <td>
            {{ annotation["xmlName"] }}
          </td>
          <td><router-link to="/xmldata"><button>Ga naar xml</button></router-link></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      annotations: [],
      search: "",
    };
  },

  mounted() {
    this.fetchXmlFiles();
  },

  methods: {
    viewText(file) {
      console.log(`View text for file: ${file.name}`);
    },

    async fetchXmlFiles() {
      try {
        const response = await axios.get(
          `${process.env.VUE_APP_SERVERROOT}/annotations/`
        );
        this.annotations = response.data;
      } catch (error) {
        console.error("Error fetching XML files from backend:", error);
      }
    },

    viewXmlData(fileName) {
      console.log(`Clicked on file: ${fileName}`);
      this.$router.push({ name: "XmlData", params: { fileName } });
    },

    async reloadFiles() {
      await this.fetchXmlFiles();
    },

    getFileNameWithoutExtension(fileName) {
      return fileName.replace(/\.[^/.]+$/, "");
    },
  },
};
</script>

<style scoped>
.container-fluid {
  height: 100vh;
  background-color: #343a40;
  color: #f8f9fa;
  font-family: 'Roboto', sans-serif;
}

</style>
