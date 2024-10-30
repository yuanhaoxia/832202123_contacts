<template>
    <div class="container">
        <div class="login-box">
            <h2 class="title pointer">{{ $sysTitle }}注册</h2>
            <el-form :model="registerForm" label-position="left">
                <el-form-item>
                    <el-input v-model="registerForm.username" placeholder="请输入账户"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-input v-model="registerForm.password" type="password" placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" style="width: 400px;" @click="register">确定注册</el-button>
                </el-form-item>
              <el-form-item>
                <el-button type="primary" style="width: 400px;" @click="goBack">返回</el-button>
              </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
import { registerAPI } from '@/api/system'
export default {
    name: 'Register',
    data() {
        return {
            registerForm: {
                role: "ADMIN",
                username: 'admin',
                password: "1"
            }
        }
    },
    methods: {
      async register() {
        const res = await registerAPI(this.registerForm)
        this.$message.success(res.message)
      },
      goBack(){
        this.$router.go(-1)
      }
    }
}
</script>

<style lang="scss" scoped>
.container {
    width: 100vw;
    min-height: 100vh;
    background: $--color-primary;
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
