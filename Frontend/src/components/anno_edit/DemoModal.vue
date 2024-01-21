<template>
<div class="demo-modal">
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
              value="RechtsSubject"
              id="RechtsSubject"
            />
            <label class="type1" for="type1">Rechtssubject</label>
          </li>
          <li>
            <input
              type="radio"
              name="anno_types"
              id="RechtsBetrekking"
              value="RechtsBetrekking"
              @change="CheckBoxes($event)"
            />
            <label class="type2" for="type2">Rechtsbetrekking</label>
          </li>
          <li>
            <input
              type="radio"
              name="anno_types"
              id="RechtsWaarde"
              value="RechtsWaarde"
            />
            <label class="type3" for="type3">Rechtswaarde</label>
          </li>
          <li>
            <input
              type="radio"
              name="anno_types"
              id="RechtsFeit"
              value="RechtsFeit"
              @change="CheckBoxes($event)"
            />
            <label class="type4" for="type4">Rechtsfeit</label>
          </li>
          <li>
            <input
              type="radio"
              name="anno_types"
              id="Voorwaarde"
              value="Voorwaarde"
            />
            <label class="type5" for="type5">Voorwaarde</label>
          </li>
          <li>
            <input
              type="radio"
              name="anno_types"
              id="AfleidingsRegel"
              value="AfleidingsRegel"
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
                    @change="addAnnotationSelectListener($event)"
                  />
                  <label class="type1" for="type1">Rechtssubject</label>
                  <table id="selectAnnotation1"></table>
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type2"
                    id="checkbox2"
                    value="2"
                    name="anno_relations"
                    @change="addAnnotationSelectListener($event)"
                  />
                  <label class="type2" for="type1">Rechtsbetrekking</label>
                  <table id="selectAnnotation2"></table>
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type2"
                    id="checkbox3"
                    value="3"
                    name="anno_relations"
                    @change="addAnnotationSelectListener($event)"
                  />
                  <label class="type3" for="type1">Rechtsobject</label>
                  <table id="selectAnnotation3"></table>
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type2"
                    id="checkbox4"
                    value="4"
                    name="anno_relations"
                    @change="addAnnotationSelectListener($event)"
                  />
                  <label class="type4" for="type1">Rechtsfeit</label>
                  <table id="selectAnnotation4"></table>
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type1"
                    id="checkbox5"
                    value="5"
                    name="anno_relations"
                    @change="addAnnotationSelectListener($event)"
                  />
                  <label class="type5" for="type1">Voorwaarde</label>
                  <table id="selectAnnotation5"></table>
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type1"
                    id="checkbox6"
                    value="6"
                    name="anno_relations"
                    @change="addAnnotationSelectListener($event)"
                  />
                  <label class="type6" for="type6">Afleidingsregel</label>
                  <table id="selectAnnotation6"></table>
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type1"
                    id="checkbox7"
                    value="7"
                    name="anno_relations"
                    @change="addAnnotationSelectListener($event)"
                  />
                  <label class="type7" for="type7">Variabele</label>
                  <table id="selectAnnotation7"></table>
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type1"
                    id="checkbox8"
                    value="8"
                    name="anno_relations"
                    @change="addAnnotationSelectListener($event)"
                  />
                  <label class="type8" for="type8">Variabelewaarde</label>
                  <table id="selectAnnotation8"></table>
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
                    @change="addAnnotationSelectListener($event)"
                  />
                  <label class="type9" for="type9">Parameter</label>
                  <table id="selectAnnotation9"></table>
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type10"
                    id="checkbox10"
                    value="10"
                    name="anno_relations"
                    @change="addAnnotationSelectListener($event)"
                  />
                  <label class="type10" for="type10">Parameterwaarde</label>
                  <table id="selectAnnotation10"></table>
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type11"
                    id="checkbox11"
                    value="11"
                    name="anno_relations"
                    @change="addAnnotationSelectListener($event)"
                  />
                  <label class="type11" for="type11">Operator</label>
                  <table id="selectAnnotation11"></table>
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type12"
                    id="checkbox12"
                    value="12"
                    name="anno_relations"
                    @change="addAnnotationSelectListener($event)"
                  />
                  <label class="type12" for="type12">Tijdsaanduiding</label>
                  <table id="selectAnnotation12"></table>
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type13"
                    id="checkbox13"
                    value="13"
                    name="anno_relations"
                    @change="addAnnotationSelectListener($event)"
                  />
                  <label class="type13" for="type13">Plaatsaanduiding</label>
                  <table id="selectAnnotatio13"></table>
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type14"
                    id="checkbox14"
                    value="14"
                    name="anno_relations"
                    @change="addAnnotationSelectListener($event)"
                  />
                  <label class="type14" for="type14"
                    >Delegatiebevoegdheid</label
                  >
                  <table id="selectAnnotation14"></table>
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type15"
                    id="checkbox15"
                    value="15"
                    name="anno_relations"
                    @change="addAnnotationSelectListener($event)"
                  />
                  <label class="type15" for="type15">Delegatie-invulling</label>
                  <table id="selectAnnotation15"></table>
                </li>
                <li>
                  <input
                    type="checkbox"
                    class="type16"
                    id="checkbox16"
                    value="16"
                    name="anno_relations"
                    @change="addAnnotationSelectListener($event)"
                  />
                  <label class="type16" for="type16">Brondefinitie</label>
                  <table id="selectAnnotation16"></table>
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
</div>
</template>

<script>
const annotationSelector =
  '<option disabled selected value="">Select an annotation</option>';
let lastId = 1;

const typeIds = {
  RechtsSubject: 1,
  RechtsBetrekking: 2,
  Rechtsobject: 3,
  Rechtsfeit: 4,
  Voorwaarde: 5,
  Afleidingsregel: 6,
  Variabele: 7,
  Variabelewaarde: 8,
  Parameter: 9,
  Parameterwaarde: 10,
  Operator: 11,
  Tijdsaanduiding: 12,
  Plaatsaanduiding: 13,
  Delegatiebevoegdheid: 14,
  "Delegatie-invulling": 15,
  Brondefinitie: 16,
};
console.log(typeIds);

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
      annotations: [],
      annoshidden: false,
    };
  },
  mounted() {
    let id = 1;
    axios
      .get(`${process.env.VUE_APP_SERVERROOT}/annotations/byxml/${id}`)
      .then((data) => {
        return data.data;
      })
      .then((data) => {
        for (let i of data) {
          this.annotations.push({ value: i.id, text: i.word });
        }
      });
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
        RechtsBetrekking: [1, 3],
        RechtsFeit: [3, 12],
        AfleidingsRegel: [7],
      };

      const checked = relations[clicked.target.value];
      const checkboxes = document.querySelectorAll(
        'input[name*="anno_relations"]'
      );

      checkboxes.forEach((checkbox) => {
        checkbox.checked = checked.includes(parseInt(checkbox.value));
      });
    },

    addAnnotationSelectListener(event) {
      this.addAnnotationSelect(event.target.value, event.target.checked);
    },

    addAnnotationSelect(id, checked) {
      if (checked) {
        document.getElementById(`selectAnnotation${id}`).innerHTML =
          "<tr>" +
          this.renderAnnotationSelector(id) +
          `<td><button id="addRelation${id}" value="${id}">Add a new relation</button></td></tr>`;
        this.addListeners(id);
        return id;
      } else document.getElementById(`selectAnnotation${id}`).innerHTML = "";
    },

    addListeners(id) {
      document
        .getElementById(`addRelation${id}`)
        .addEventListener("click", this.addAnotherAnnotationSelectionListener);
    },

    addAnotherAnnotationSelectionListener(event) {
      console.log(event);
      let id = event.target.value;
      return this.addAnotherAnnotationSelection(id);
    },

    addAnotherAnnotationSelection(id) {
      document.getElementById(`selectAnnotation${id}`).insertAdjacentHTML("beforeend", this.renderAnnotationSelector());
      this.addListeners(id);
    },

    renderAnnotationSelector() {
      let id = lastId++;
      let finalString =
        `<td><select class="annotationSelector" id="annotationSelector${id}">` +
        annotationSelector;
      for (let i of this.annotations) {
        finalString += `<option value=${i.value}>${i.text}</option>`;
      }
      finalString +=
        "<td>" +
        `</select> <input type="text" v-show="annoshidden" id=description${id}>` +
        "</td>";
      return finalString;
    },

    submitAnnotationsRelations(currentId) {
      let relations = [];
      for (let i of document.getElementsByClassName("annotationSelector")) {
        let domId = i.id.replace("annotationSelector", "");
        let description = document.getElementById(`description${domId}`).value;
        relations.push({
          annotation1Id: currentId,
          annotation2Id: Number(i.value),
          description: description,
        });
      }
      for (let i of relations) {
        axios
          .post(
            `${process.env.VUE_APP_SERVERROOT}/annotations/add-single-relation`,
            i
          )
          .then((data) => {
            return data.data;
          })
          .then((data) => {
            console.log(data);
          }).catch((err)=>{
            console.log(err);
          });
      }
    },

    submitAnnotation() {
      this.feedback = "";
      let data = { xmlId: this.xmlId };
      let type = document.querySelector('input[name="anno_types"]:checked').value
      if (this.multi == "yes") {
        data["multiAnnotations"] = [
          {
            color: "#FFFFFF",
            description: this.description,
            word: this.word,
            type: type
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
            type: type
          },
        ];
      }

      axios
        .post(`${process.env.VUE_APP_SERVERROOT}/annotations/add`, data)
        .then((res) => {
          if (res.status == 200) {
            this.feedback = "Annotatie is aangepast";
            return res.data;
          } else {
            this.feedback =
              "Er is iets mis gegaan tijdens het aanpassen van de annotatie probeer het later opnieuw";
            return null;
          }
        })
        .then((data) => {
          let id = data.id;
          this.submitAnnotationsRelations(id);
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
      document.getElementById(data["type"]).checked = true;
      if ("relatedAnnotations" in data) {
        let relations = data["relatedAnnotations"];
        let addedType = [];
        for (let i of relations) {
          let annotation = i["annotation"];
          
          document.getElementById(`checkbox${typeIds[annotation.type]}`).checked = true;
          
          if (!addedType.includes(annotation.type)) {
            this.addAnnotationSelect(typeIds[annotation.type], true);
            addedType.push(annotation.type)
          } else {
            this.addAnotherAnnotationSelection(typeIds[annotation.type]);
          }
          let descriptionId = lastId-1;
          document.getElementById(`annotationSelector${descriptionId}`).value = annotation.id;
          console.log(document.getElementById(`annotationSelector${descriptionId}`).value);
          document.getElementById(`description${descriptionId}`).value = i["description"];
        }
      }
    },
  },
};
</script>

<style scoped>
.modal-container,
.modal-container h1,
.modal-container h3,
.modal-container label,
.modal-container .save-message,
.modal-container .submit-btn,
.modal-container .close-btn {
  color: #ffffff;
}

button {
  height: 50px;
  width: 175px;
  border-radius: 15px;
  border: 2px solid #cccccc;
  background-color: #596db8;
  color: #ffffff;
  font-size: 20px;
}

.close-btn {
  background-color: #ff0404fa;
  color: #ffffff;
  font-size: 20px;
}
.modal-container {
  width: 70%; 
  margin: auto; 
  z-index: 1;
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
