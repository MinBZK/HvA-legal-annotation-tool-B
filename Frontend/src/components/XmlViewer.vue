<template>
  <div class="container-fluid">
    <XmlData class="xml-data" @word-clicked="handleWordClicked" @fill-data="handleFillData"/>
    <DemoModal
      class="demo-modal"
      ref="DemoModal"
      v-show="isEditing"
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
    },
    handleFillData(detail) {
      console.log("fillledi");
      console.log(this.$refs);
      this.$refs.DemoModal.fillData(detail);
    }
  },
  mounted() {
  }
};
</script>

<style scoped>
.container-fluid {
  background-color: #343a40;
  display: flex;
  height: auto;
}

.xml-data, .demo-modal {
  flex: 1;
}

@media (max-width: 768px) {
  .container-fluid {
    flex-direction: column;
  }
}
</style>
