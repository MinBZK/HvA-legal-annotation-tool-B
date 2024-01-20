<template>
  <div class="xml-container">
    <h2>Wet tekst annotatie:</h2>
    <pre>
      <p @click="callThing">
      {{ xmlData }}</p>
    </pre>
    <button class="terug-button">terug</button>
  </div>
  <DemoModal
    ref="DemoModal"
    v-show="isEditing"
    @close="closeModal"
    :word="word"
    :startIndex="startIndex"
    :endIndex="endIndex"
    :xmlId="xmlId"
    :existingDescription="existingDescription"
  />
</template>

<script>
import axios from "axios";
import DemoModal from "./anno_edit/DemoModal.vue";

export default {
  components: {
    DemoModal,
  },
  data() {
    return {
      xmlData: "",
      word: null,
      startIndex: 0,
      endIndex: 0,
      xmlId: 0,
      isEditing: false,
      existingDescription: "",
    };
  },
  methods: {
    async fetchXMLData(fileName) {
      try {
        const response = await axios.get(
          `${process.env.VUE_APP_SERVERROOT}/get-xml?fileName=${fileName}`,
          { responseType: "text" }
        );
        this.xmlData = response.data;
      } catch (error) {
        console.error("Error fetching XML data", error);
      }
    },
    async callThing() {
      console.log("called");
      let s = window.getSelection();
      var range = s.getRangeAt(0);
      var node = s.anchorNode;

      // Find starting point
      while (range.toString().indexOf(" ") != 0) {
        range.setStart(node, range.startOffset - 1);
      }
      range.setStart(node, range.startOffset + 1);

      // Find ending point
      do {
        range.setEnd(node, range.endOffset + 1);
      } while (
        range.toString().indexOf(" ") == -1 &&
        range.toString().trim() != ""
      );

      // Alert result
      var str = range.toString().trim().replace("\n", "");

      this.word = str;
      this.startIndex = range.startOffset;
      this.endIndex = range.endOffset;
      this.xmlId = 1;
      let result = await this.getInfoOfWords(
        this.xmlId,
        this.startIndex,
        this.endIndex,
        this.word
      );
      if (result != null) {
        this.$refs.DemoModal.fillData(result);
      }
      this.showModal();
    },
    showModal() {
      this.isEditing = true;
    },
    closeModal() {
      this.isEditing = false;
    },
    async getInfoOfWords(xml_id, startindex, endindex, word) {
      return await axios
        .get(
          `${process.env.VUE_APP_SERVERROOT}/annotations/byxml/${xml_id}/by-index/${startindex}-${endindex}`
        )
        .then((data) => {
          return data.data;
        })
        .catch(async (err) => {
          if (err.response.status == 404) {
            return await axios
              .get(
                `${process.env.VUE_APP_SERVERROOT}/annotations/byxml/${xml_id}/by-word/${word}`
              )
              .then((data) => {
                return data.data;
              })
              .catch((err) => {
                if (err.response.status == 404) {
                  return null;
                }
              });
          }
        });
    },
  },
  mounted() {
    const fileName = this.$route.query.fileName;
    this.fetchXMLData(fileName);
  },
};
</script>

<style>
.xml-container {
  margin: 20px;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 4px;
  background-color: #f9f9f9;
  background-color: #f2f2f2; /* Updated to gray background */
}

h2 {
  color: #333;
  font-family: Arial, sans-serif;
}

pre {
  background-color: #efefef;
  border: 1px solid #ddd;
  padding: 15px;
  border-radius: 4px;
  overflow-x: auto;
  white-space: pre-wrap;
}

.terug-button {
  background-color: #ff0000; /* Red background */
  color: #ffffff; /* White text */
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
  margin-top: 20px;
  margin-left: 2300px;
}

.terug-button:hover {
  background-color: #cc0000; /* Darker red on hover */
}
</style>
