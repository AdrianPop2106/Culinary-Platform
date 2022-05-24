<template>
  <v-app>
    <v-card class="overflow-hidden">
      <v-app-bar
        absolute
        color="orange lighten-1"
        dark
        scroll-target="#scrolling-techniques-8"
      >
        <v-toolbar-side-icon
          ><v-img
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
    <v-container grid-list-md>
      <!--<v-row class="text-center">
      <v-col cols="12">
        <v-img
          :src="require('../assets/food.png')"
          class="my-3"
          contain
          height="200"
        />
      </v-col>-->

      <!--<v-col class="mb-4">-->
      <!--<h1 class="display-2 font-weight-bold mb-3">
          Welcome to the Culinary App {{ name }}
        </h1>-->

      <!---<p class="subheading font-weight-regular">
           
          For help and collaboration with other Vuetify developers,
          <br>please join our online
          <a
            href="https://community.vuetifyjs.com"
            target="_blank"
          >Discord Community</a>
          
          If you see this, then you've succesfully logged in!
        </p>--->

      <!--- <v-card class="mx-auto" max-width="300" tile>
          <v-list>
            <v-subheader>USERS</v-subheader>
            <v-list-item-group v-model="selectedItem" color="primary">
              <v-list-item v-for="(item, i) in items" :key="i">
                <v-list-item-content>
                  <v-list-item-title v-text="item.userName"></v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list-item-group>
          </v-list>
        </v-card> --->

      <v-row align-start>
        <v-col md="4">
          <v-card md="2">
            <v-toolbar dark color="orange lighten-1">
              <v-toolbar-title>Welcome, {{ name }}</v-toolbar-title>
            </v-toolbar>
            <v-card-text>
              <v-form>
                <v-text-field
                  v-model="emailText"
                  name="change"
                  label="Change email"
                  type="text"
                ></v-text-field>
                <v-text-field
                  v-model="passwordText"
                  id="password"
                  name="password"
                  label="Change Password"
                  type="password"
                ></v-text-field>
                <v-text-field
                  v-model="nameText"
                  name="nameText"
                  label="Change Name"
                  type="text"
                ></v-text-field>
              </v-form>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="orange lighten-1" @click="changeInfo()"
                >modify</v-btn
              >
              <v-btn color="error" @click="deleteUser()">Delete</v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
        <v-col>
          <v-card class="elevation-12">
            <v-toolbar dark color="orange lighten-1">
              <v-toolbar-title>Saved recipes</v-toolbar-title>
            </v-toolbar>
            <v-list>
                <v-list-item v-for="(item, i) in savedRecipes" :key="i" @click="visitRecipe(item.id)">
                  <v-list-item-content>
                    {{ item.title }}
                  </v-list-item-content>
                </v-list-item>
            </v-list>
          </v-card>
        </v-col>
      </v-row>
      <!--</v-col>-->
      <!--</v-row>-->
    </v-container>
  </v-app>
</template>

<script>
import axios from "axios";
export default {
  name: "HelloWorld",

  data: () => ({
    id: "",
    name: "",
    emailText: "",
    passwordText: "",
    nameText: "",
    savedRecipes: [],
    selectedItem: 0,
    chefName: "",
  }),
  async mounted() {
    this.id = localStorage.getItem("userId");
    var obj = JSON.parse(window.localStorage.getItem("userInfo"));
    this.name = obj.data.userName;
    this.savedRecipes = (
      await axios.get(
        "http://localhost:9999/getSavedRecipes/" +
         window.localStorage.getItem("userId")
      )
    ).data;
    console.log(this.savedRecipes);

  },
  methods: {
    disconnect() {
      window.localStorage.removeItem("userId");
      window.localStorage.removeItem("userInfo");
      this.$router.back();
    },

    deleteUser() {
      var id = this.items[this.selectedItem].id;
      axios.delete("http://localhost:9999/users/" + id);
    },

    async changeInfo() {
      var result = await axios.put(
        "http://localhost:9999/users/" + window.localStorage.getItem("userId"),
        {
          userName: this.nameText,
          email: this.emailText,
          pass: this.passwordText,
        }
      );
      this.nameText = "";
      var url =
        "http://localhost:9999/users/" + window.localStorage.getItem("userId");
      result = await axios.get(url);
      window.localStorage.removeItem("userInfo");
      window.localStorage.setItem("userInfo", JSON.stringify(result));
    },
    async getChef(i) {
      this.chefName = await axios.get(
        "http://localhost:9999/getChefByRecipeId/" + i
      );
      return this.chefName.then((data) => {
        this.chefName = data;
      });
    },
     async visitRecipe(i) {
      window.localStorage.setItem("recipeId",i);
      this.$router.push({ name: 'recipe' });
    },
  },
};
</script>
