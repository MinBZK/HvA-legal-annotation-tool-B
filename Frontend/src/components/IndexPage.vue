<template>
  <div class="container">
    <UploadModal v-show="isModalVisible" @close="closeModal" />
    <div class="header-container d-flex align-items-center mb-3">
      <h2 class="me-3">Alle Bestanden</h2>
      <input v-model="search" class="form-control me-3" placeholder="Zoeken op naam" />
      <button type="button" class="btn btn-primary" @click="showModal">
        Upload een nieuwe wet
      </button>
      <button @click="exportAllFiles" class="btn btn-dark">Alles exporteren</button>
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
        <tr v-for="(file, index) in filteredFiles" :key="index">
          <td><input type="checkbox" v-model="selectedFiles" :value="file" class="form-check-input" /></td>
          <td>{{ file.name }}</td>
          <td>{{ file.fullName }}</td>
          <td><button @click="viewXmlData(file)" class="btn btn-primary">Bekijk tekst</button></td>
          <td><button @click="exportFile(file)" class="btn btn-dark">Exporteer</button></td>
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

  methods: {


    viewText(file) {
      console.log(`View text for file: ${file.name}`);
    },

    // async importFiles() {
    //   const input = document.createElement('input');
    //   input.type = 'file';
    //   input.accept = '.xml';
    //   input.multiple = true;

    //   input.addEventListener('change', async (event) => {
    //     const files = event.target.files;

    //     if (files && files.length > 0) {
    //       for (let i = 0; i < files.length; i++) {
    //         const file = files[i];

    //         const formData = new FormData();
    //         formData.append('file', file);

    //         try {
    //           await axios.post('http://localhost:8080/files/upload', formData);
    //           await this.fetchFilesFromBackend();
    //         } catch (error) {
    //           console.error('Error uploading file:', error);
    //         }
    //       }
    //     }
    //   });

    //   input.click();
    // },

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

    viewXmlData(file) {
      console.log(`Clicked on file: ${file.name}`);
      this.$router.push({ name: 'XmlData' });
    },
    async fetchFilesFromBackend() {
      try {
        const response = await axios.get('http://localhost:8080/get-xmls');
        this.files = response.data;
      } catch (error) {
        console.error('Error fetching files from backend:', error);
      }
    },
    // importFilesModal() {
    //   this.$refs.uploadModal.show();
    // }
  },
};
</script>

<style scoped></style>