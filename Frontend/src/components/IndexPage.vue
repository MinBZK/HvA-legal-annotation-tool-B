<template>
  <div class="container">
    <UploadModal v-show="isModalVisible" @close="closeModal" />
    <div class="header-container d-flex align-items-center mb-3">
      <h2 class="me-3">Alle Bestanden</h2>
      <input v-model="search" class="form-control me-3" placeholder="Zoeken op naam" />
      <button type="button" class="btn btn-primary" @click="showModal">
        Upload een andere wet
      </button>
      <button @click="exportAllFiles" class="btn btn-dark">Alles exporteren</button>
      <button @click="reloadFiles" class="btn btn-success">Herladen</button>
    </div>

    <table class="table">
      <thead>
        <tr>
          <th scope="col"></th>
          <th scope="col">Title</th>
          <th scope="col">Bestandnaam</th>
          <th scope="col"></th>
          <th scope="col"></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(fileName, index) in xmlFiles" :key="index">
          <td><input type="checkbox" v-model="selectedFiles" :value="file" class="form-check-input" /></td>
          <td>{{ fileName }}</td>
          <td>{{ fileName }}</td>
          <td><button @click="viewXmlData(fileName)" class="btn btn-primary">Bekijk tekst</button></td>
          <td><button @click="exportFile(fileName)" class="btn btn-dark">Exporteer</button></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data() {
    return {
      xmlFiles: [],
      files: [],
      selectedFiles: [],
      search: "",
    };
  },
  computed: {
    filteredFiles() {
      return this.files.filter((file) =>
        file.name.toLowerCase().includes(this.search.toLowerCase())
      );
    },
  },

  mounted() {
    this.fetchXmlFiles(); 
  },

  methods: {


    viewText(file) {
      console.log(`View text for file: ${file.name}`);
    },

    exportFiles() {
      if (this.selectedFiles.length === 0) {
        alert("Please select at least one file to export.");
        return;
      }

      for (let i = 0; i < this.selectedFiles.length; i++) {
        const file = this.selectedFiles[i];
        const dataURL = "data:text/xml;charset=utf-8," + encodeURIComponent(file.content);

        window.open(dataURL, "_blank");
      }
    },

    async fetchXmlFiles() {
      try {
        const response = await axios.get('http://localhost:8080/get-xmls');
        this.xmlFiles = response.data;
      } catch (error) {
        console.error('Error fetching XML files from backend:', error);
      }
    },

    viewXmlData(file) {
      console.log(`Clicked on file: ${file.name}`);
      this.$router.push({ name: 'XmlData' });
    },

    async reloadFiles() {
      await this.fetchXmlFiles();
    },
  },
};
</script>

<style scoped></style>