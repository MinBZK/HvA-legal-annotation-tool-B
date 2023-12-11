<template>
  <div class="container">
    <UploadModal v-show="isModalVisible" @close="closeModal" />
    <div class="header-container d-flex align-items-center mb-3">
      <h2 class="h4 me-3">Alle Bestanden</h2>
      <input v-model="search" class="form-control me-3" placeholder="Zoeken op naam" />
      <button type="button" class="btn btn-primary btn-sm" @click="showModal">
        Upload een andere wet
      </button>
      <button @click="exportAllFiles" class="btn btn-dark btn-sm">Alles exporteren</button>
      <button @click="reloadFiles" class="btn btn-success btn-sm">Herladen</button>
    </div>

    <table class="table">
      <thead>
        <tr>
          <th scope="col"></th>
          <th scope="col" class="h6">Title</th>
          <th scope="col"></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(fileName, index) in filteredFiles" :key="index">
          <td><input type="checkbox" v-model="selectedFiles" :value="file" class="form-check-input" /></td>
          <td>{{ getFileNameWithoutExtension(fileName) }}</td>
          <td>
            <button @click="viewXmlData(fileName)" class="btn btn-primary">Bekijk tekst</button>
            <button @click="exportFile(fileName)" class="btn btn-dark">Exporteer</button>
          </td>
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
      return this.xmlFiles.filter((fileName) =>
        fileName !== '.gitkeep' && fileName.toLowerCase().includes(this.search.toLowerCase())
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

    viewXmlData(fileName) {
      console.log(`Clicked on file: ${fileName}`);
      this.$router.push({ name: 'XmlData', params: { fileName } });
    },

    async reloadFiles() {
      await this.fetchXmlFiles();
    },

    getFileNameWithoutExtension(fileName) {
      // Remove the file extension from the filename
      return fileName.replace(/\.[^/.]+$/, "");
    },
  },
};
</script>

<style scoped></style>