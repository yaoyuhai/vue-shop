<template>
  <el-container>
    <!-- 标贴 -->
    <el-header>
      电商管理系统
      <el-button type="info" class="container_button" @click="Quit">退出</el-button>
    </el-header>

    <!-- 内容 -->
    <el-container>
      <!-- 侧边栏 -->
      <Sidebar></Sidebar>
      <!-- 内容主体 -->
      <el-main>
        <Main></Main>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import Sidebar from '@/views/Sidebar/sidebar.vue'
import Main from '@/components/Main/main.vue'

export default {
  name: 'Home',
  methods: {
    /**
     * @method Quit() 退出后台
     */
    Quit() {
      this.$router.replace('/login')
      localStorage.removeItem('token')
    }
  },
  components: {
    Sidebar,
    Main
  }
}
</script>

<style lang="less" scoped>
.el-container {
  height: 100%;
  color: #fff;
}
.el-header {
  background-color: #373d3f;
  line-height: 60px;
  font-size: 30px;
  display: flex;
  justify-content: space-between;
  .container_button {
    height: 40px;
    margin-top: 10px;
    font-size: 18px;
    &:hover {
      background-color: #409fe6;
    }
  }
}
.el-aside {
  background-color: #323743;
  text-align: center;
}
.el-main {
  background-color: #e9edf0;
  color: #333;
  text-align: center;
  padding: 0;
}

</style>
