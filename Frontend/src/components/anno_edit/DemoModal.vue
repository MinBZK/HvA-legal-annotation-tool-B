<template>
  <div class="modal-container">
    <div class="modal-inner">
      <h1>Defineer {{ word }}</h1>
      <div class="modal-column-list">
        <ul>
          <h3 class="modal-subtitle">Type:</h3>
          <li>
            <input
              type="radio"
              name="anno_types"
              value="Rechtssubject"
              id="radiotype1"
            />
            <label class="type1" for="type1">Rechtssubject</label>
          </li>
          <li>
            <input
              type="radio"
              name="anno_types"
              id="type2"
              value="Rechtsbetrekking"
              @change="CheckBoxes($event)"
            />
            <label class="type2" for="type2">Rechtsbetrekking</label>
          </li>
          <li>
            <input
              type="radio"
              name="anno_types"
              id="type3"
              value="Rechtswaarde"
            />
            <label class="type3" for="type3">Rechtswaarde</label>
          </li>
          <li>
            <input
              type="radio"
              name="anno_types"
              id="type4"
              value="Rechtsfeit"
              @change="CheckBoxes($event)"
            />
            <label class="type4" for="type4">Rechtsfeit</label>
          </li>
          <li>
            <input
              type="radio"
              name="anno_types"
              id="type5"
              value="Voorwaarde"
            />
            <label class="type5" for="type5">Voorwaarde</label>
          </li>
          <li>
            <input
              type="radio"
              name="anno_types"
              id="type6"
              value="Afleidingsregel"
              @change="CheckBoxes($event)"
            />
            <label for="type6">Afleidingsregel</label>
          </li>
        </ul>
        <ul>
          <div class="row">
            <div class="col">
              <ul>
                <h3 class="modal-subtitle">Relaties:</h3>
                <li>
                  <input
                    type="checkbox"
                    class="type1"
                    id="checkbox1"
                    value="1"
                    name="anno_relations"
                  />
                  <label class="type1" for="type1">Rechtssubject</label>
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type2"
                    id="checkbox2"
                    value="2"
                    name="anno_relations"
                  />
                  <label class="type2" for="type1">Rechtsbetrekking</label>
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type2"
                    id="checkbox3"
                    value="3"
                    name="anno_relations"
                  />
                  <label class="type3" for="type1">Rechtsobject</label>
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type2"
                    id="checkbox4"
                    value="4"
                    name="anno_relations"
                  />
                  <label class="type4" for="type1">Rechtsfeit</label>
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type1"
                    id="checkbox5"
                    value="5"
                    name="anno_relations"
                  />
                  <label class="type5" for="type1">Voorwaarde</label>
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type1"
                    id="checkbox6"
                    value="6"
                    name="anno_relations"
                  />
                  <label class="type6" for="type6">Afleidingsregel</label>
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type1"
                    id="checkbox7"
                    value="7"
                    name="anno_relations"
                  />
                  <label class="type7" for="type7">Variabele</label>
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type1"
                    id="checkbox8"
                    value="8"
                    name="anno_relations"
                  />
                  <label class="type8" for="type8">Variabelewaarde</label>
                </li>
              </ul>
            </div>
            <div class="col">
              <ul class="relations-group-2">
                <li>
                  <input
                    type="checkbox"
                    class="type9"
                    id="checkbox9"
                    value="9"
                    name="anno_relations"
                  />
                  <label class="type9" for="type9">Parameter</label>
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type10"
                    id="checkbox10"
                    value="10"
                    name="anno_relations"
                  />
                  <label class="type10" for="type10">Parameterwaarde</label>
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type11"
                    id="checkbox11"
                    value="11"
                    name="anno_relations"
                  />
                  <label class="type11" for="type11">Operator</label>
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type12"
                    id="checkbox12"
                    value="12"
                    name="anno_relations"
                  />
                  <label class="type12" for="type12">Tijdsaanduiding</label>
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type13"
                    id="checkbox13"
                    value="13"
                    name="anno_relations"
                  />
                  <label class="type13" for="type13">Plaatsaanduiding</label>
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type14"
                    id="checkbox14"
                    value="14"
                    name="anno_relations"
                  />
                  <label class="type14" for="type14"
                    >Delegatiebevoegdheid</label
                  >
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type15"
                    id="checkbox15"
                    value="15"
                    name="anno_relations"
                  />
                  <label class="type15" for="type15">Delegatie-invulling</label>
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type16"
                    id="checkbox16"
                    value="16"
                    name="anno_relations"
                  />
                  <label class="type16" for="type16">Brondefinitie</label>
                </li>
              </ul>
            </div>
          </div>
        </ul>
      </div>
      <div>
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
            >Pas toe voor elk keer dat "{{ word }}" voorkomt in het
            document.</label
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
  props: {
    text: String,
    xmlId: Number,
    word: String,
    startIndex: Number,
    endIndex: Number,
    existingDescription: String,
  },
  data() {
    return {
      anno_types: null,
      anno_relations: null,
      selectedWord: "",
      saveMessage: false,
      description: "",
      multi: true,
      feedback: "",
    };
  },
  methods: {
    save() {
      this.$emit(
        "save",
        this.anno_types,
        this.anno_relations,
        this.selectedWord,
        this.description
      );
      this.saveMessage = true; // Set saveMessage to true when the save operation is successful
      setTimeout(() => {
        this.saveMessage = false; // Hide the message after 3 seconds
      }, 3000);
    },
    close() {
      this.$router.push({ name: "HelloWorld" });
    },
    CheckBoxes(clicked) {
      let relations = {
        Rechtsbetrekking: [1, 3],
        Rechtsfeit: [3, 12],
        Afleidingsregel: [7],
      };

      const checked = relations[clicked.target.value];
      const checkboxes = document.querySelectorAll(
        'input[name*="anno_relations"]'
      );

      checkboxes.forEach((checkbox) => {
        checkbox.checked = checked.includes(parseInt(checkbox.value));
      });
    },
    submitAnnotation() {
      this.feedback = "";
      let data = { xmlId: this.xmlId };
      if (this.multi == "yes") {
        data["multiAnnotations"] = [
          {
            color: "#FFFFFF",
            description: this.description,
            word: this.word,
          },
        ];
      } else {
        data["singleAnnotations"] = [
          {
            color: "#FFFFFF",
            description: this.description,
            startIndex: this.startIndex,
            endIndex: this.endIndex,
            word: this.word,
          },
        ];
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
    fillData(data) {
      console.log(data);
      this.description = data["description"];
      this.multi = data["startIndex"] == null ? "yes" : "no";
    }
  },
};
</script>

<style>
.modal-container {
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
.row {
  align-items: center;
}
ul {
  list-style-type: none;
  padding: 0;
}
.relations-group-2 {
  margin-top: 22%;
}
li {
  display: flex;
  margin: 10px 10px;
  align-items: baseline;
}
.modal-column-list {
  display: flex;
  justify-content: space-around;
  padding-top: 5%;
}
.modal-subtitle {
  text-align: left;
  margin-left: 10px;
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
label {
  margin-left: inherit;
  margin-bottom: 0 !important;
}
.save-message {
  font-weight: bolder;
  font-size: 20px;
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
