<template>
  <div class="xml-data">

  <div class="xml-container">
    <h2>Wet tekst annotatie:</h2>
    <pre>
      <p @click="callThing">
      {{ xmlData }}</p>
    </pre>
    <button class="global-button my-2" @click="redirectToIndexPage">Opslaan</button>
  </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  components: {
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
          `${process.env.VUE_APP_SERVERROOT}/get-xml?fileName=${fileName}`
        );
        console.log(response.data);
        this.xmlData = response.data.text;
        this.xmlId = response.data.id;
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
      let result = await this.getInfoOfWords(
        this.xmlId,
        this.startIndex,
        this.endIndex,
        this.word
      );
      if (result != null) {
        this.$emit('fill-data', result);
      }
      this.showModal();
      this.$emit('word-clicked', {
        word: this.word,
        startIndex: this.startIndex,
        endIndex: this.endIndex,
        xmlId: this.xmlId
      });
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
    redirectToIndexPage() {
      this.$router.push({ name: 'IndexPage' });
    }
  },
  mounted() {
    const fileName = this.$route.query.fileName;
    this.fetchXMLData(fileName);
  },
};
</script>

<style scoped>
.xml-container {
  max-width: 100%;
  box-sizing: border-box;
}

h2 {
  color: #ffffff;
  font-family: Arial, sans-serif;
}

pre {
  background-color: #ffffff;
  border: 1px solid #ffffff;
  padding: 15px;
  border-radius: 4px;
  overflow-x: auto;
  white-space: pre-wrap;
}

</style>
