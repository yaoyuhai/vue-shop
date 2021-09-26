<template>
  <el-breadcrumb separator-class="el-icon-arrow-right">
    <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>{{ nav.one }}</el-breadcrumb-item>
    <el-breadcrumb-item>{{ nav.two }}</el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script>
export default {
  name: 'Breadcrumbs',
  props: {
    nav: {
      type: Object,
      default: function() {
        return { nav: {} }
      }
    }
  }
}
</script>

<style lang="less" scoped>
.el-breadcrumb {
  font-size: 16px;
  margin-bottom: 20px;
}
</style>
