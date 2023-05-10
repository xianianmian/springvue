<template>
  <div class="home">
    <div style="display: flex; justify-content: space-around; ">
      <div class="topleft">left</div>

      <div class="navbar" style="width: 66%;">
        <a href="#home">Home</a>
        <a href="#news">News</a>
        <div class="dropdown">
          <button class="dropbtn">Dropdown
            <i class="el-icon-arrow-down el-icon--right"></i>
          </button>
          <div class="dropdown-content">
            <a href="#">Link 1</a>
            <a href="#">Link 2</a>
            <a href="#">Link 3</a>
          </div>
        </div>
        <a style="position: relative; top:-3px">
          <el-dropdown>
            <span style="color: aliceblue;">
              更多菜单<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>黄金糕</el-dropdown-item>
              <el-dropdown-item>狮子头</el-dropdown-item>
              <el-dropdown-item>螺蛳粉</el-dropdown-item>
              <el-dropdown-item>双皮奶</el-dropdown-item>
              <el-dropdown-item>蚵仔煎</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </a>
      </div>

      <div class="topright">
        <div v-if="!user.username">
          <el-button @click="goTologin">登录</el-button>
          <el-button @click="goToRegist">注册</el-button>
        </div>
        <div v-else>
          <el-dropdown style="width: 100px; cursor: pointer">
            <div style="display: inline-block">
              <el-avatar :size="30" @error="errorHandler" style="position: relative; top: 10px; right: 5px">
                <img :src="user.avatarUrl" />
              </el-avatar>
              <span>{{ user.nickname }}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
            </div>
            <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center">
              <el-dropdown-item style="font-size: 14px; padding: 5px 0">
                <router-link to="/person">个人信息</router-link>
              </el-dropdown-item>
              <el-dropdown-item style="font-size: 14px; padding: 5px 0">
                <span style="text-decoration: none" @click="logout">退出</span>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>

      </div>
    </div>
  </div>
</template>
<script>
  export default {
    name: "FrontHome",
    data() {
      return {
        user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
        files:[]
      }
    },
    methods: {
      goTologin() {
        // this.$router.push('/longin')
        this.$router.push('/home').catch(() => { })

      },
      goToRegist() {
        this.$router.push('/register')
      },
      logout() {
        this.$store.commit("resetLogout")
        this.$message.success("退出成功")
      },
      errorHandler() {
        return true
      }
    },
    created(){
      this.request.get("/echarts/file/front/all").then(res=>{
        this.files = res.data.filter(x=>{
         return x.type == 'png' ||x.type == 'jpg'||x.type == 'weapp'
        })
        console.log(this.files)
      })
    }
  }
</script>
<style scoped>
  .topleft {
    width: 15%;
    text-align: center;
    margin-left: 2%;
    line-height: 60px;
  }

  .topright {
    width: 15%;
    text-align: center;
    margin-right: 2%;
    line-height: 60px;
  }

  div.navbar {
    height: 60px;
    background-color: rgba(177, 201, 65, 1);
    color: rgba(249, 248, 246, 1);
    align-items: center;
  }

  .navbar a {
    text-decoration: none;
    display: inline-block;
    background-color: rgba(177, 201, 65, 1);
    color: rgba(249, 248, 246, 1);
    font-size: 20px;
    line-height: 60px;
    margin: 0 20px;
  }

  .dropdown {
    height: 200px;
    width: 100px;
    display: inline;
    position: relative;
  }

  .dropdown button {
    border: none;
    background-color: rgba(177, 201, 65, 1);
    color: rgba(249, 248, 246, 1);
    font-size: 20px;
  }

  .dropdown-content {
    display: none;
    height: 200px;
    position: absolute;
    left: -22px;
    height: 160px;
    transition: 0.5s;
  }

  .dropdown-content a {
    display: block;
    width: 100px;
    padding-left: 10px;
  }

  .dropdown:hover .dropdown-content {
    display: block;
  }
</style>