import { createApp } from 'vue';
import App from './App.vue';
import HelloWorld from './components/HelloWorld.vue';
import XmlData from './components/XmlData.vue';
import IndexPage from './components/IndexPage.vue';
import { createRouter, createWebHistory } from 'vue-router';

const routes = [
    { path: '/', name: 'HelloWorld', component: HelloWorld },
    { path: '/index', name: 'IndexPage', component: IndexPage },
    { path: '/xmldata', name: 'XmlData', component: XmlData },
  ];



const router = createRouter({
    history: createWebHistory(),
    routes,
  });

import { library } from '@fortawesome/fontawesome-svg-core'

import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

import { faUserSecret, faFolder } from '@fortawesome/free-solid-svg-icons'

library.add(faUserSecret, faFolder)

createApp(App).use(router).component('font-awesome-icon', FontAwesomeIcon).mount('#app');