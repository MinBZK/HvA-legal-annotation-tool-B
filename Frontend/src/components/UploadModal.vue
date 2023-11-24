<template>
  <div class="modal-backdrop">
    <div class="modal">
      <header class="modal-header">
        <slot name="header"> Upload een XML </slot>
        <button type="button" class="btn-close" @click="close">x</button>
      </header>

      <section class="modal-body">
        <slot name="body">
          <input type="file" @change="uploadFile" ref="file" accept=".xml"/>
        </slot>
      </section>

      <footer class="modal-footer">
        <slot name="footer" id="feedback">{{feedback}}</slot>
        <button type="button" class="btn-green" @click="submitFile">
          Upload
        </button>
      </footer>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  name: "UploadModal",
  data() {
    return {
      feedback: ""
    }
  },
  methods: {
    close() {
      this.$emit("close");
    },
    uploadFile() {
      this.XML = this.$refs.file.files[0];
      this.feedback = "";
    },
    submitFile() {
      const formData = new FormData();
      formData.append("file", this.XML);
      const headers = { "Content-Type": "multipart/form-data" };
      axios
        .post("http://localhost:8080/files/upload", formData, { headers })
        .then((res) => {
          console.log(res.data.files);
          console.log(res.status);
          this.feedback = "File has been uploaded";
        });
    },
  },
};
</script>

<style>
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

.modal {
  background: #ffffff;
  box-shadow: 2px 2px 20px 1px;
  overflow-x: auto;
  display: flex;
  flex-direction: column;
}

.modal-header,
.modal-footer {
  padding: 15px;
  display: flex;
}

.modal-header {
  position: relative;
  border-bottom: 1px solid #eeeeee;
  color: #4aae9b;
  justify-content: space-between;
}

.modal-footer {
  border-top: 1px solid #eeeeee;
  flex-direction: column;
  justify-content: flex-end;
}

.modal-body {
  position: relative;
  padding: 20px 10px;
}

.btn-close {
  position: absolute;
  top: 0;
  right: 0;
  border: none;
  font-size: 20px;
  padding: 10px;
  cursor: pointer;
  font-weight: bold;
  color: #4aae9b;
  background: transparent;
}

.btn-green {
  color: white;
  background: #4aae9b;
  border: 1px solid #4aae9b;
  border-radius: 2px;
}
</style>
