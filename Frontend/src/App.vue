<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <router-link to="/">HelloWorld</router-link>
    <router-link to="/index">IndexPage</router-link>
    <router-link to="/xmldata">XmlData</router-link>
    <HelloWorld></HelloWorld>
    <button @click="openModal">Open</button>
    <DemoModal v-show="isModalVisible" @close="closeModal" />
  </div>  
  <router-view/>
</template>
<script>
import HelloWorld from './components/HelloWorld.vue';
import DemoModal from './components/anno_edit/DemoModal.vue';
//import IndexPage from './components/IndexPage.vue';

export default {
  name: 'App',
  components: {
    DemoModal,
    HelloWorld
   // IndexPage
  },
  data() {
    return {
      isModalVisible: false,
    };
  },
  methods: {
    openModal() {
      this.isModalVisible = true;
    },
    closeModal() {
      this.isModalVisible = false;
    }
  }
};
</script>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
