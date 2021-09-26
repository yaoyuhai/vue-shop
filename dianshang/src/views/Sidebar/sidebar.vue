<template>
  <el-aside :width="isCollapse === false ? '200px' : '64px'">
    <!-- 导航折叠 -->
    <div class="toggle-button" @click="open">|||</div>
    <!-- 菜单区域 -->
    <el-menu background-color="#323743" text-color="#fff" active-text-color="#ffd04b"
      unique-opened router :collapse="isCollapse" :collapse-transition="false">
      <!-- 一级菜单 -->
      <el-submenu :index="item.uid + ''" v-for="item in list" :key="item.uid">
        <!-- 一级菜单模板区 -->
        <template slot="title">
          <!-- 图标 -->
          <i :class="item.path"></i>
          <!-- 文本 -->
          <span>{{ item.autoName }}</span>
        </template>
        <!-- 二级菜单 -->
        <el-menu-item :index="item.rou + ''" v-for="item in item.children" :key="item.id">
          <!-- 图标 -->
          <i :class="item.path"></i>
          <!-- 文本 -->
          <span>{{ item.autoName }}</span>
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </el-aside>
</template>

<script>
export default {
  name: 'Sidebar',
  data() {
    return {
      // 是否折叠
      isCollapse: false,
      // 导航菜单的数据
      list: [
        {
          uid: 1,
          autoName: '用户管理',
          path: 'el-icon-user-solid',
          children: [
            { id: 101, autoName: '用户列表', path: 'el-icon-menu', rou: '/home/user' }
          ]
        },
        {
          uid: 2,
          autoName: '权限管理',
          path: 'el-icon-takeaway-box',
          children: [
            { id: 102, autoName: '角色列表', path: 'el-icon-menu', rou: '/home/rolelist' },
            { id: 103, autoName: '权限列表', path: 'el-icon-menu', rou: '/home/authoritylist' }
          ]
        },
        {
          uid: 3,
          autoName: '商品管理',
          path: 'el-icon-s-cooperation',
          children: [
            { id: 104, autoName: '商品列表', path: 'el-icon-menu', rou: '/home/commoditylist' },
            { id: 105, autoName: '分类参数', path: 'el-icon-menu', rou: '/home/classification_parameters' },
            { id: 106, autoName: '商品分类', path: 'el-icon-menu', rou: '/home/categories' }
          ]
        },
        {
          uid: 4,
          autoName: '订单管理',
          path: 'el-icon-s-order',
          children: [
            { id: 107, autoName: '订单列表', path: 'el-icon-menu', rou: '/home/orderlist' }
          ]
        },
        {
          uid: 5,
          autoName: '数据统计',
          path: 'el-icon-s-platform',
          children: [
            { id: 108, autoName: '数据报表', path: 'el-icon-menu', rou: '/home/datalist' }
          ]
        }
      ]
    }
  },
  methods: {
    // 导航是否折叠
    open() {
      this.isCollapse = !this.isCollapse
    }
  }
}
</script>

<style lang="less" scoped>
.el-aside[data-v-6997abc5] {
  text-align: left;
  .toggle-button {
    background-color: #4a5064;
    font-size: 12px;
    line-height: 26px;
    color: #fff;
    text-align: center;
    letter-spacing: 0.2em;
    cursor: pointer;
  }
}
.el-menu {
  border: 0;
}
</style>
