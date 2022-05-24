<template>
  <v-app>
    <v-card class="overflow-hidden">
      <v-app-bar
        absolute
        color="orange lighten-1"
        dark
        scroll-target="#scrolling-techniques-8"
      >
        <v-toolbar-side-icon>
          <v-img
            :src="require('../assets/food.png')"
            contain
            height="80px"
            width="130px"
          ></v-img
        ></v-toolbar-side-icon>
        <v-toolbar-title>CULINARY WORLD</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-col cols="3"
          ><v-text-field single-line label="Search"></v-text-field
        ></v-col>
        <v-btn icon>
          <v-icon>mdi-magnify</v-icon>
        </v-btn>
        <v-btn icon>
          <v-icon @click="disconnect()">mdi-logout </v-icon>
        </v-btn>
      </v-app-bar>
      <v-sheet
        id="scrolling-techniques-8"
        class="overflow-y-auto"
        max-height="63"
      >
        <v-container style="height: 1500px"> </v-container>
      </v-sheet>
    </v-card>
    <v-container>
      <v-col>
        <v-card>
          <v-toolbar dark color="orange lighten-1">
            <v-toolbar-title
              >{{ recipename }} by Chef {{ chef.data }}</v-toolbar-title
            >
            <v-spacer></v-spacer>
            <v-btn icon>
              <v-icon @click="saveRecipe()">mdi-content-save</v-icon>
            </v-btn>
          </v-toolbar>
          <v-card-text>
            <v-list>
              <p style="font-size: 20px">Ingredients:</p>
              <v-list-item v-for="(item, i) in ingredients" :key="i">
                <v-list-item-content>
                  <v-list-item-title
                    >{{ item.title }}:{{ item.quantity }}
                    {{ item.measure }}</v-list-item-title
                  >
                </v-list-item-content>
              </v-list-item>
            </v-list>
            <v-divider></v-divider>
            <p style="font-size: 20px">Description:</p>
            {{ description }}
          </v-card-text>
        </v-card>

        <v-card>
          <v-toolbar dark color="orange lighten-1">
            <v-toolbar-title>Comments</v-toolbar-title>
          </v-toolbar>
          <v-card-text>
            <v-textarea v-model="comment" color="success"></v-textarea>
            <v-row class="mb-3">
              <v-spacer></v-spacer>
              <v-btn color="success" @click="postComment()">comment</v-btn>
            </v-row>
            <v-divider></v-divider>
            <v-list>
              <v-list-item v-for="(item, i) in comments" :key="i">
                <v-list-item-content>
                  <v-row>
                    <v-card height="75" width="200" elevation="0" outlined tile>
                      <v-card-title class="body-2">
                        {{ item[0] }}
                      </v-card-title>
                      <v-card-text>
                        {{ item[1] }}
                      </v-card-text>
                    </v-card>
                    <v-card height="75" width="850" outlined tile>
                      <v-card-text>
                        {{ item[2] }}
                      </v-card-text>
                    </v-card>
                  </v-row>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-card-text>
        </v-card>
      </v-col>
    </v-container>
  </v-app>
</template>

<script>
import axios from "axios";
export default {
  name: "Recipe",

  data: () => ({
    id: "",
    recipename: "",
    description: "",
    ingredients: [],
    comment: "",
    comments: [],
    chef: "",
  }),
  async mounted() {
    this.id = window.localStorage.getItem("recipeId");
    var obj = await axios.get("http://localhost:9999/getRecipeById/"+this.id);
    this.recipename = obj.data.title;
    this.description = obj.data.description;
    
    this.ingredients = (
      await axios.get("http://localhost:9999/getRecipeIngredients/"+this.id)
    ).data;
    this.comments = (
      await axios.get("http://localhost:9999/getRecipeComments/"+this.id)
    ).data;
    this.chef = await axios.get("http://localhost:9999/getChefByRecipeId/"+this.id);
    window.onpopstate = function(event) {
     window.localStorage.removeItem("recipeId")
   };
  },
  methods: {
    disconnect() {
      window.localStorage.removeItem("userId");
      window.localStorage.removeItem("userInfo");
      this.$router.back();
    },

    async postComment() {
      axios.post("http://localhost:9999/postComment/", {
        userId: window.localStorage.getItem("userId"),
        recipeId: id,
        commentDate: null,
        message: this.comment,
      });
      this.$forceUpdate();
      this.comment = "";
    },
    saveRecipe(){
      axios.post("http://localhost:9999/saveRecipe/"+window.localStorage.getItem("userId")+"/"+this.id);
    }
  },
};
</script>
