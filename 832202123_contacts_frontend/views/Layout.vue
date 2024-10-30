<template>
  <div :class="{ home: true, openMenus: !isCollapse, closeMenus: isCollapse }">
    <div class="menus">
        <div class="text-center font-bold text-white pt-20 pb-10 flex items-center justify-center">
            <img :title="$sysTitle" src="@/assets/logo.png" height="30px"/><span class="title">{{ $sysTitle }}</span>
        </div>
      <el-menu
        :collapse="isCollapse"
        default-active="/admin"
        background-color="bg-primary"
        text-color="#eaecf0"
        active-text-color="#fcfcfd"
        router
      >
      <el-menu-item index="/admin">
          <i class="el-icon-menu"></i>
          <span slot="title">首页</span>
        </el-menu-item>
        <el-menu-item v-if="role == 'ADMIN'" index="/admin/admin">
          <i class="el-icon-menu"></i>
                <span slot="title">管理员管理</span>
        </el-menu-item>
        <el-menu-item index="/admin/addressBook">
          <i class="el-icon-menu"></i>
                <span v-if="role == 'USER'" slot="title">通讯录管理</span>
                <span v-else slot="title">通讯录管理</span>
        </el-menu-item>
        <el-menu-item v-if="role == 'ADMIN'" index="/admin/users">
          <i class="el-icon-menu"></i>
                <span slot="title">用户管理</span>
        </el-menu-item>
      </el-menu>
    </div>
    <div class="content">
        <div class="navbar">
            <div class="navbar-btn" @click="isCollapse = !isCollapse">
                <i class="el-icon-s-fold" v-if="!isCollapse"></i>
                <i class="el-icon-s-unfold" v-else></i>
            </div>
            <div class="navbar-right">
                <el-dropdown trigger="click">
                  <span class="el-dropdown-link">
                      <el-avatar v-if="head" :src="$baseFileUrl + head" :size="40"></el-avatar>
                      <img v-else :src="require('@/assets/head.jpg')" style="height:40px;width: 40px;border-radius: 50%;" />
                      {{username}}<i class="el-icon-caret-bottom el-icon--right"></i>
                  </span>
                    <el-dropdown-menu slot="dropdown">
                        <!-- <el-dropdown-item icon="el-icon-s-custom">个人信息</el-dropdown-item> -->
                        <el-dropdown-item @click.native="handleLogout" icon="el-icon-caret-left">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>
        </div>
        <!-- 内容部分 -->
        <div class="section">
	        <transition name="fade">
	          <router-view></router-view>
	          </transition>
        </div>
    </div>
  </div>
</template>

<script>
import {mapActions,mapGetters,mapMutations} from 'vuex'
export default {
  name: "Layout",

  data() {
    return {
      isCollapse: false,
    };
  },

  computed: {
    ...mapGetters(['username','head','role'])
  },

  mounted() {
    this.GET_USER_INFO()
  },

  methods: {
    ...mapActions(['GET_USER_INFO','LOGOUT']),
    ...mapMutations(['SET_USER','SET_TOKEN']),
    handleLogout(){
      this.$confirm('此操作将退出登录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.LOGOUT().then(res => {
            this.$router.push('/login')
          })
        }).catch(() => {});
    }
  },
};
</script>

<style lang="scss" scoped>
.openMenus {
  .menus {
    height: 100vh;
    width: 200px;
    overflow-y: auto;
    position: fixed;
    left: 0;
    top: 0;
    background-color: $--color-primary!important;
    transition: all 0.5s;
    .el-menu {
      border-right: none;
      .el-menu-item{
        background-color: $--color-primary!important;
        i{
          color: #fff;
        }
      }
      .el-menu-item.is-active{
        font-weight: bold;
        font-size: 15px;
      }
    }
  }
  .content {
    // background: darkcyan;
    margin-left: 200px;
    width: calc(100% - 200px);
    height: 100vh;
    transition: all 0.5s;
  }
}

.closeMenus {
  .menus {
    height: 100vh;
    width: 64px;
    overflow-y: auto;
    position: fixed;
    left: 0;
    top: 0;
    transition: all 0.5s;
    background-color: $--color-primary!important;
    .title{
        display: none;
    }
    .el-menu {
      border-right: none;
      .el-menu-item{
        background-color: $--color-primary!important;
        i{
          color: #fff;
        }
      }
      .el-menu-item.is-active{
        font-weight: bold;
        font-size: 16px;
      }
    }
  }
  .content {
    // background: darkcyan;
    margin-left: 64px;
    width: calc(100% - 64px);
    height: 100vh;
    transition: all 0.5s;
  }
}

.home{
    .navbar{
        height: 60px;
        // background-color: darkcyan;
        display: flex;
        align-items: center;
        padding: 0 10px;
        justify-content: space-between;
        border-bottom: 1px solid #eee;
        .navbar-btn{
            // width: 60px;
            height: 40px;
            line-height: 40px;
            text-align: center;
            cursor: pointer;
            // background-color: darkblue;
        }
        .navbar-right{
            height: 40px;
            // background-color: darkmagenta;
            cursor: pointer;
            .el-dropdown-link{
              display: flex;
              align-items: center;
            }
        }
    }
    .content{
      .section{
        padding: 16px;
      }
    }
}

</style>
