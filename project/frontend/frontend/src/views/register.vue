<template>
  <v-app id="inspire">
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
      </v-app-bar>
      <v-sheet
        id="scrolling-techniques-8"
        class="overflow-y-auto"
        max-height="63"
      >
        <v-container style="height: 1500px"> </v-container>
      </v-sheet>
    </v-card>
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <v-card class="elevation-12">
              <v-toolbar dark color="orange lighten-1">
                <v-toolbar-title>Register</v-toolbar-title>
              </v-toolbar>

              <v-card-text>
                <v-form>
                  <v-text-field
                    v-model="email"
                    name="email"
                    label="Email"
                    type="text"
                  ></v-text-field>
                  <v-text-field
                    v-model="password"
                    id="password"
                    name="password"
                    label="Password"
                    type="password"
                  ></v-text-field>
                  <v-text-field
                    v-model="name"
                    id="name"
                    name="name"
                    label="Name"
                    type="name"
                  ></v-text-field>
                  <v-radio-group v-model="checks" mandatory column >
                    <v-radio label="user" value="1"></v-radio>
                    <v-radio label="chef" value="2"></v-radio>
                  </v-radio-group>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="orange lighten-1" @click="register()"
                  >Register</v-btn
                >
                <v-btn color="orange lighten-1" @click="$router.back()"
                  >Back</v-btn
                >
              </v-card-actions>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
import axios from "axios";
export default {
  name: "register",
  data() {
    return {
      name: "",
      email: "",
      password: "",
      checks:""
    };
  },
  methods: {
    async register() {
      try {
        if(this.checks==1)
          var result = await axios.post("http://localhost:9999/users/", {
          userName: this.name,
          email: this.email,
          pass: this.password,
        });
        else
          var result= await axios.post("http://localhost:9999/postChef/", {
          userName: this.name,
          email: this.email,
          pass: this.password,
          ordering:false
        });
        //this.data=result;
        console.log(result.status);
        this.$router.push({ name: "login" });
      } catch (e) {}
    },
  },
};
</script>
