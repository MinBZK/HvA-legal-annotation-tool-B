<template>
  <div class="container">
    <div class="header-container d-flex align-items-center mb-3">
      <h2 class="me-3">Alle Bestanden</h2>
      <input v-model="search" class="form-control me-3" placeholder="Zoeken op naam" />
      <button @click="importFiles" class="btn btn-primary me-3">Importeer bestand</button>
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
          <td><button @click="viewText(file)" class="btn btn-primary">Bekijk tekst</button></td>
          <td><button @click="exportFile(file)" class="btn btn-dark">Exporteer</button></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {

  methods: {


    filteredFiles() {
      return this.files.filter((file) =>
        file.name.toLowerCase().includes(this.search.toLowerCase())
      );
    },


    importFiles() {
      const input = document.createElement("input");
      input.type = "file";
      input.accept = ".xml";
      input.multiple = true;

      input.addEventListener("change", (event) => {
        const files = event.target.files;

        if (files && files.length > 0) {
          for (let i = 0; i < files.length; i++) {
            const file = files[i];
            const reader = new FileReader();

            reader.onload = (e) => {
              const content = e.target.result;
              this.files.push({ name: file.name, content });
            };

            reader.readAsText(file);
          }
        }
      });

      input.click();
    },

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

  },
};
</script>

<style scoped>
</style>
