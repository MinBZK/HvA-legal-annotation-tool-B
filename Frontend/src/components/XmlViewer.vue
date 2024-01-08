<template>
  <div class="container">
    <XmlData class="xml-data" @word-clicked="handleWordClicked"/>
    <DemoModal
      class="demo-modal"
      v-if="isEditing"
      @close="isEditing = false"
      :word="selectedWord"
      :startIndex="startIndex"
      :endIndex="endIndex"
      :xmlId="xmlId"
    />
  </div>
</template>

<script>
import XmlData from './XmlData.vue';
import DemoModal from './anno_edit/DemoModal.vue';

export default {
  components: {
    XmlData,
    DemoModal
  },
  data() {
    return {
      selectedWord: '',
      startIndex: 0,
      endIndex: 0,
      xmlId: 0,
      isEditing: false
    };
  },
  methods: {
    handleWordClicked(detail) {
      this.selectedWord = detail.word;
      this.startIndex = detail.startIndex;
      this.endIndex = detail.endIndex;
      this.xmlId = detail.xmlId;
      this.isEditing = true;
    }
  }
};
</script>

<style scoped>
.container {
  display: flex;
  /* Adjust the container height as needed, or remove if not needed */
  height: 100vh;
}

.xml-data, .demo-modal {
  flex: 1;
}

/* Optional: Add additional responsive design rules as necessary */
@media (max-width: 768px) {
  .container {
    flex-direction: column;
  }
}
</style>
