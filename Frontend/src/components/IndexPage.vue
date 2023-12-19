<template>
  <div class="container">
    <UploadModal v-show="isModalVisible" @close="closeModal" />
    <div class="header-container d-flex align-items-center mb-3">
      <h2 class="h4 me-3 mr-2">Alle Bestanden</h2>
      <input v-model="search" class="form-control me-3 mr-2" placeholder="Zoeken op naam" />
      <button type="button" class="btn btn-primary btn-sm mr-2" @click="showModal">
        Upload een andere wet
      </button>
      <button @click="exportAllFiles" class="btn btn-dark btn-sm mr-2">Alles exporteren</button>
      <button @click="reloadFiles" class="btn btn-success btn-sm mr-2">Herladen</button>
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
          <td><input type="checkbox" class="form-check-input" /></td>
          <td>{{ getFileNameWithoutExtension(fileName) }}</td>
          <td>
            <button @click="viewXmlData(fileName)" class="btn btn-primary mr-2">Bekijk tekst</button>
            <button class="btn btn-dark mr-2">Exporteer</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>


<script>
import axios from 'axios';

export default {
  data() {
    return {
      xmlFiles: [],
      search: "",
    };
  },
  computed: {
    // filters XML files based on search query
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
    /**
     * log the view action for a specific file.
     */
    viewText(file) {
      console.log(`View text for file: ${file.name}`);
    },

    /**
     * fetch XML files from the backend using an HTTP GET request.
     * handles the response and updates the component's xmlFiles data.
     */
    async fetchXmlFiles() {
      try {
        const response = await axios.get('http://localhost:8080/get-xmls');
        this.xmlFiles = response.data;
      } catch (error) {
        console.error('Error fetching XML files from backend:', error);
      }
    },

    /**
     * navigate to the 'XmlData' route to view details of a specific XML file.
     */
    viewXmlData(fileName) {
      console.log(`Clicked on file: ${fileName}`);
      this.$router.push({ name: 'XmlData', params: { fileName } });
    },

    /**
     * reload XML files by calling the fetchXmlFiles method.
     */
    async reloadFiles() {
      await this.fetchXmlFiles();
    },

    /**
     * remove the file extension from the provided fileName.
     */
    getFileNameWithoutExtension(fileName) {
      return fileName.replace(/\.[^/.]+$/, "");
    },
  },
};
</script>

<style scoped></style>