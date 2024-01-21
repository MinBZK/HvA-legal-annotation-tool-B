<template>
  <div class="container-fluid">
    <UploadModal v-show="isModalVisible" @close="closeModal" />
    <div class="header-container d-flex align-items-center mb-3">
      <h2 class="h4 me-3 mr-2">Alle Bestanden</h2>
      <input v-model="search" class="form-control me-3 mr-2" placeholder="Zoeken op naam"/>
      <button type="button" class="btn btn-primary btn-sm mr-2" @click="showModal">
        Upload een andere wet
      </button>
      <button @click="exportAllFiles" class="btn btn-light btn-sm mr-2">Alles exporteren</button>
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
        <td><input type="checkbox" v-model="selectedFiles" :value="file" class="form-check-input"/></td>
        <td>{{ getFileNameWithoutExtension(fileName) }}</td>
        <td>
          <button @click="viewXmlData(fileName)" class="global-button mr-2">Bekijk tekst</button>
          <button @click="generateXml(fileName)" class="global-button mr-2">Exporteer</button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>


<script>
import axios from 'axios'
import JSZip from 'jszip';
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
      this.$router.push({name: 'XmlViewer', query: {fileName: fileName}});
    },
    async exportAllFiles() {
      const response = await axios.get('http://localhost:8080/get-xmls');
      const fileNames = response.data;
      const zip = new JSZip();

      for (const fileName of fileNames) {
        if (fileName !== '.gitkeep') {
          const fileResponse = await axios.get(`${process.env.VUE_APP_SERVERROOT}/get-xml?fileName=${fileName}`);
          zip.file(fileName, fileResponse.data);
        }
      }

      zip.generateAsync({ type: "blob" })
          .then(blob => {
            const url = URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.href = url;
            a.download = 'files.zip';
            a.click();
            URL.revokeObjectURL(url);
          });
    },

    async generateXml(fileName) {
      try {
        // Fetch the content of the specific XML file
        const response = await axios.get(`${process.env.VUE_APP_SERVERROOT}/get-xml?fileName=${fileName}`);
        const xmlContent = response.data;

        // Trigger the download of the XML file
        const link = document.createElement("a");
        link.href = "data:text/xml," + encodeURIComponent(xmlContent);
        link.download = fileName;
        link.click();

      } catch (error) {
        console.error(error);
      }
    },
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
  mounted() {
    this.fetchXmlFiles();
  },
};
</script>

<style scoped>
.container-fluid {
  padding-left: 0;
  padding-right: 0;
  background-color: #343a40;
  color: #ffffff;
  min-height: 100vh;
}


.table thead th {
  background-color: #343a40;
  color: #ffffff;
}


</style>
