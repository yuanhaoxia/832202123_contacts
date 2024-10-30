<template>
    <div class="container">
        <div class="login-box">
            <h2 class="title pointer">{{ $sysTitle }}登录</h2>
            <el-form :model="loginForm" label-position="left">
                <el-form-item>
                    <el-input v-model="loginForm.username" placeholder="请输入账户"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-input v-model="loginForm.password" type="password" placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-radio-group v-model="loginForm.role">
                        <el-radio label="ADMIN">管理员</el-radio>
                        <el-radio label="USER">用户</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" style="width: 400px;" @click="login">登录</el-button>
                </el-form-item>
              <el-form-item>
                <el-button type="primary" style="width: 400px;" @click="toRegister">用户注册</el-button>
              </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
import { loginAPI } from '@/api/system'
import { mapMutations } from 'vuex'
import { setToken } from '@/utils/auth'
export default {
    name: 'Login',
    data() {
        return {
            loginForm: {
                role: "ADMIN",
                username: '',
                password: ""
            }
        }
    },
    methods: {
        ...mapMutations(['SET_TOKEN']),
        async login() {
            const res = await loginAPI(this.loginForm)
            this.$message.success(res.message)
            //设置token
            setToken(res.data) //缓存
            this.SET_TOKEN(res.data) //vuex
            setTimeout(() => {
                this.$router.replace("/admin")
            }, 1000);
        },
      toRegister(){
          this.$router.push({name:'register'})
      },
      async register() {
        const res = await loginAPI(this.loginForm)
        this.$message.success(res.message)
        //设置token
        setToken(res.data) //缓存
        this.SET_TOKEN(res.data) //vuex
        setTimeout(() => {
          this.$router.replace("/admin")
        }, 1000);
      }
    }
}
</script>

<style lang="scss" scoped>
.container {
    width: 100vw;
    min-height: 100vh;
    background: $--color-primary;
    // background: #409eff;
    // background: #282c35;
    // background: linear-gradient(to right, #ffcccc, #ffb3b3);
    // background: linear-gradient(135deg, #c7e9fb, #a6defa, #80d4f9, #5bc9f8, #35bef7);
    // background: linear-gradient(135deg, #272727, #4a4a4a, #6d6d6d, #909090, #b3b3b3, #d6d6d6, #f9f9f9);
    // background: linear-gradient(135deg, #001f3f, #0088a9, #00c9a7, #92d5c6, #ebf5ee);
    //background: url(@/assets/login-background.jpg) no-repeat;
    background-size: cover;
    display: flex;

    .login-box {
        padding: 10px;
        border-radius: 10px;
        min-width: 400px;
        min-height: 200px;
        background-color: aliceblue;
        margin: auto;

        .title {
            text-align: center;
        }
    }
}
</style>
