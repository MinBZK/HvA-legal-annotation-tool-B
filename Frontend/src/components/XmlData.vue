<template>
  <div class="xml-container">
    <h2>Wet tekst annotatie:</h2>
    <pre>{{ xmlData }}</pre>
    <button class="terug-button">terug</button>
  </div>
</template>


<script>
import axios from 'axios';

export default {
  data() {
    return {
      xmlData: '',
    };
  },
  methods: {
    async fetchXMLData() {
      try {
        const response = await axios.get('http://localhost:8080/get-xml', { responseType: 'text' });
        this.xmlData = response.data;
      } catch (error) {
        console.error('Error fetching XML data', error);
      }
    }
  },
  mounted() {
    this.fetchXMLData();
  }
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

