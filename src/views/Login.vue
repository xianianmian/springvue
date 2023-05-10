<template>
  <div class="wrapper">
    <div
      style="margin: 200px auto; background-color:rgba(177, 201, 65, 0.4) ; width: 350px; height: 300px; padding: 20px; border-radius: 10px">
      <div style="margin: 20px 0; text-align: center; font-size: 24px"><b> Arknights</b></div>
      <el-form :model="user" :rules="rules" ref="userForm">
        <el-form-item prop="username">
          <el-input size="medium" id="my-input1" style="margin: 10px 0;" prefix-icon="el-icon-user"
            v-model="user.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input size="medium" id="my-input2" style="margin: 10px 0;" prefix-icon="el-icon-lock" show-password
            v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item style="margin: 10px 0; text-align: right">
          <el-button size="small" autocomplete="off" @click="login"
            style="background-color: rgba(177, 201, 65, 1);">登录</el-button>
          <el-button size="small" autocomplete="off" @click="$router.push('/register')">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
  import { setRouters } from "../router/index.js"
  export default {

    name: "Login",
    data() {
      return {
        user: {},
        rules: {
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 2, max: 10, message: '长度在 2  到 5 个字符', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
          ],
        }
      }
    },
    methods: {
      login() {
        this.$refs['userForm'].validate((valid) => {
          if (valid) {  // 表单校验合法
            this.request.post("/user/login", this.user).then(res => {
              if (res.code === '200') {
                localStorage.setItem("user", JSON.stringify(res.data))  // 存储用户信息到浏览器
                localStorage.setItem("menus", JSON.stringify(res.data.menus))  // 存储菜单信息到浏览器
                setRouters()
                this.$message.success("登录成功")
                if (res.data.role == "ROLE_USER") {
                  this.$router.push('/front/home')
                } else {
                  this.$router.push("/")
                }
              } else {
                this.$message.error(res.msg)
              }
            })
          }
        });
      }
    }
  }
</script>

<style>
  .wrapper {
    height: 100vh;
    background-image: url("../../public/1681399691396.jpg");
    overflow: hidden;
  }

  #my-input1,
  #my-input2 {
    background-color: rgba(54, 55, 47, 0.4);
    color: rgb(250, 248, 248);
  }
</style>