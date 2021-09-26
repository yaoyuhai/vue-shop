<template>
  <div class="authority_container">
    <!-- 面包屑 -->
    <Breadcrumbs :nav="nav"></Breadcrumbs>

    <!-- 权限列表 -->
    <el-card>
      <el-row>
        <el-table :data="list" border>
          <el-table-column type="index" label="#">
          </el-table-column>
          <el-table-column prop="name" label="权限名称">
          </el-table-column>
          <el-table-column prop="path" label="路径">
          </el-table-column>
          <el-table-column prop="" label="权限等级">
            <template #default="{ row }">
              <el-tag v-if="row.grade === 1">一级</el-tag>
              <el-tag v-else-if="row.grade === 2" type="success">二级</el-tag>
              <el-tag v-else type="warning">三级</el-tag>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
    </el-card>
  </div>
</template>

<script>
import Breadcrumbs from '@/components/Breadcrumbs/breadcrumbs.vue'

export default {
  name: 'Authoritylist',
  data() {
    return {
      nav: { one: '权限管理', two: '权限列表' },
      list: [
        {
          id: 1,
          name: '商品管理',
          path: 'goods',
          grade: 1
        },
        {
          id: 2,
          name: '商品管理',
          path: 'goods',
          grade: 1
        },
        {
          id: 3,
          name: '商品管理',
          path: 'goods',
          grade: 1
        },
        {
          id: 4,
          name: '商品管理',
          path: 'goods',
          grade: 2
        },
        {
          id: 5,
          name: '商品管理',
          path: 'goods',
          grade: 3
        },
        {
          id: 6,
          name: '商品管理',
          path: 'goods',
          grade: 2
        },
        {
          id: 7,
          name: '商品管理',
          path: 'goods',
          grade: 2
        }
      ]
    }
  },
  components: {
    Breadcrumbs
  }
}
</script>

<style lang="less" scoped>
.authority_container {
  padding: 20px;
}
</style>
