<template>
  <div class="modal">
    <div class="modal-inner">
      <h1>Defineer {{ word }}</h1>
      <div class="modal-column-list">
        <ul>
          <h3 class="modal-subtitle">Type:</h3>
          <li>
            <input
              type="radio"
              name="type1"
              value="color1"
              id="type1"
              v-model="selectedColor"
            />
            <label class="type1" for="type1">Rechtssubject</label>
          </li>
          <li>
            <input
              type="radio"
              name="type2"
              id="type2"
              value="color2"
              v-model="selectedColor"
            />
            <label class="type2" for="type2">Rechtsbetrekking</label>
          </li>
          <li>
            <input
              type="radio"
              name="type3"
              id="type3"
              value="color2"
              v-model="selectedColor"
            />
            <label class="type3" for="type3">Rechtswaarde</label>
          </li>
          <li>
            <input
              type="radio"
              name="type4"
              id="type4"
              value="color2"
              v-model="selectedColor"
            />
            <label class="type4" for="type4">Rechtsfeit</label>
          </li>
          <li>
            <input
              type="radio"
              name="type5"
              id="type5"
              value="color2"
              v-model="selectedColor"
            />
            <label class="type5" for="type5">Voorwaarde</label>
          </li>
          <li>
            <input
              type="radio"
              name="type6"
              id="type6"
              value="color2"
              v-model="selectedColor"
            />
            <label for="type6">Anders</label>
          </li>
        </ul>
        <ul>
          <h3 class="modal-subtitle">Relaties:</h3>
          <li>
            <input type="checkbox" class="type1" name="fruits" value="apple" />
            <label class="type1" for="type1">Rechtssubject</label>
          </li>
          <li>
            <input type="checkbox" class="type2" name="fruits" value="orange" />
            <label class="type2" for="type1">Rechtsbetrekking</label>
          </li>
          <li>
            <input type="checkbox" class="type3" name="fruits" value="orange" />
            <label class="type3" for="type1">Rechtswaarde</label>
          </li>
          <li>
            <input type="checkbox" class="type4" name="fruits" value="orange" />
            <label class="type4" for="type1">Rechtsfeit</label>
          </li>
          <li>
            <input type="checkbox" class="type5" name="fruits" value="orange" />
            <label class="type5" for="type1">Voorwaarde</label>
          </li>
        </ul>
      </div>
      <input type="color" id="labelColor" name="labelColor" v-model="color" />
      <div class="modal-footer">
        <ul>
          <li>
            <h3>Definitie:</h3>
          </li>
          <li class="textarea">
            <textarea
              class="description"
              v-model="description"
              placeholder="Een korte beschrijving..."
            />
          </li>
        </ul>
        <li>
          <input
            type="checkbox"
            name="description-box"
            v-model="multi"
            true-value="yes"
            false-value="no"
          />
          <label for="type1"
            >Pas toe voor elk keer dat "id" voorkomt in het document.</label
          >
        </li>
      </div>
      {{ feedback }}
      <div class="modal-btns">
        <ul>
          <li>
            <button class="submit-btn" @click="submitAnnotation">
              Toepassen
            </button>
            <button class="close-btn" @click="close">Annuleren</button>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  components: {},
  methods: {
    close() {
      this.$emit("close");
    },
    submitAnnotation() {
      this.feedback = "";
      console.log(this.multi == "yes");
      let data = { xmlId: this.xmlId };
      if (this.multi == "yes") {
        data["multiAnnotations"] = [{
          color: this.color,
          description: this.description,
          word: this.word,
        }];
      } else {
        data["singleAnnotations"] = [{
          color: this.color,
          description: this.description,
          startIndex: this.startIndex,
          endIndex: this.endIndex,
        }];
      }

      axios
        .post(`${process.env.VUE_APP_SERVERROOT}/annotations/add`, data)
        .then((res) => {
          if (res.status == 200) {
            this.feedback = "Annotatie is aangepast";
          } else {
            this.feedback =
              "Er is iets mis gegaan tijdens het aanpassen van de annotatie probeer het later opnieuw";
          }
        })
        .catch(() => {
          this.feedback =
            "Er is iets mis gegaan tijdens het aanpassen van de annotatie probeer het later opnieuw";
        });

      console.log(data);
    },
  },
  props: {
    text: String,
    xmlId: Number,
    word: String,
    startIndex: Number,
    endIndex: Number,
  },
  data() {
    return {
      color: "",
      description: "",
      multi: true,
      feedback: "",
    };
  },
};
</script>

<style>
.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: lightseagreen;
}
.modal-inner {
  border: 2px solid #686868;
  background-color: white;
  border-radius: 0.5em;
  padding: 2em;
  margin: auto;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: flex;
  margin: 10px 10px;
}
.modal-column-list {
  display: flex;
  justify-content: space-around;
}
.modal-subtitle {
  text-align: left;
}

.modal-btns li {
  display: flex;
  justify-content: space-around;
}
.textarea {
  margin-top: -15px;
  display: flex;
  flex-direction: column;
}
button {
  height: 50px;
  width: 175px;
  border-radius: 15px;
  border: 2px solid #cccccc;
}
.submit-btn {
  background-color: #596db8;
  color: white;
  font-size: 20px;
}
.close-btn {
  background-color: #ff0404fa;
  color: white;
  font-size: 20px;
}
.type1 {
  background-color: #c2e7ff;
  color: black;
}
.type2 {
  background-color: #70a4ff;
  color: black;
}
.type3 {
  background-color: #98bef1;
  color: black;
}
.type4 {
  background-color: #97d6fe;
  color: black;
}
.type5 {
  background-color: #91e8d3;
  color: black;
}
</style>
