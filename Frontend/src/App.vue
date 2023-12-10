<template>
    <div id="app">
        <img alt="Vue logo" src="./assets/logo.png">
        <HelloWorld></Helloworld>
        <button @click="openModal">Open</button>
        <!-- <DemoModal v-show="isModalVisible" @close="closeModal"/> -->
        <tempEdit></tempEdit>
    </div>
</template>
<script>
    import HelloWorld from './components/HelloWorld.vue';
    // import DemoModal from './components/anno_edit/DemoModal.vue';
    import tempEdit from './components/tempEdit.vue';

    export default {
        name: 'App',
        components: {
            // DemoModal,
            HelloWorld,
            tempEdit
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
