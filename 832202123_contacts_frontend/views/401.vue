<template>
    <div class="error-page">
        <el-result icon="warning" title="401" sub-title="抱歉，您没有访问权限！">
            <template slot="extra">
                <el-button size="small" type="primary" @click="goLogin">重新登录</el-button>
            </template>
        </el-result>
    </div>
</template>

<script>
import { removeToken,removeVuexCache } from '@/utils/auth'
export default {
    name: '401',

    methods: {
        goLogin() {
            removeToken()
            removeVuexCache()
            this.$router.replace({ path: '/login' });
        }
    }
};
</script>

<style scoped>
.error-page {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: #f0f2f5;
}

.el-result {
  background: #fff;
  padding: 40px;
  border-radius: 10px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.el-result__icon svg {
  width: 100px;
  height: 100px;
}

.el-result__title {
  font-size: 48px;
  color: #f56c6c;
}

.el-result__subtitle {
  font-size: 24px;
  color: #606266;
  margin-top: 10px;
}

.home-button {
  margin-top: 20px;
}
</style>