<template>

   <v-app id="inspire">
     <v-card class="overflow-hidden">
    <v-app-bar
      absolute
      color="orange lighten-1"
      dark
      scroll-target="#scrolling-techniques-8"
    >
      <v-toolbar-side-icon><v-img :src="require('../assets/food.png')" contain height="80px" width="130px"></v-img></v-toolbar-side-icon>
      <v-toolbar-title>CULINARY WORLD</v-toolbar-title>
      <v-spacer></v-spacer>
    </v-app-bar>
    <v-sheet
      id="scrolling-techniques-8"
      class="overflow-y-auto"
      max-height="63"
    >
      <v-container style="height: 1500px;">
      </v-container>
    </v-sheet>
  </v-card>
      <v-content>
         <v-container fluid fill-height>
            
            <v-layout align-center justify-center>
               <v-flex xs12 sm8 md4>
                  <v-card class="elevation-12">
                     <v-toolbar dark color="orange lighten-1">
                        <v-toolbar-title>Login</v-toolbar-title>
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
                        </v-form>
                     </v-card-text>
                     <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="orange lighten-1" @click="login()">Login</v-btn>
                        <v-btn color="orange lighten-1" @click="$router.push({ name: 'register' })">Register</v-btn>
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
    name: 'Login',
    data() {
      return {
        email: "",
        password: ""
      }
    },
    methods: {
      async login() {
        try { 
          var result = await axios.post("http://localhost:9999/login/", { userName:"",email: this.email, pass: this.password });
          if(result.data!=-1){
            window.localStorage.setItem("userId", result.data);
            var url="http://localhost:9999/users/"+result.data;
            var userInfo=await axios.get(url)
            window.localStorage.setItem("userInfo",JSON.stringify(userInfo));
            this.$router.push({ name: 'user' });
          }
          
        } catch (e) {
          alert("Credentiale incorecte!");
        }
      }
    }
  }
</script>
