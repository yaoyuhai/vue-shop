<template>
  <div class="role_container">
    <!--面包屑 -->
    <Breadcrumbs :nav="nav"></Breadcrumbs>

    <!-- 角色列表 -->
    <el-card>
       <!-- 添加角色 -->
      <el-row>
        <el-col>
          <el-button type="primary">添加角色</el-button>
        </el-col>
      </el-row>

      <!-- 列表区  -->
      <el-table :data="tableData" border>
        <!-- 侧边展开区 -->
        <el-table-column type="expand">
          <template #default="">
            123
          </template>
        </el-table-column>

        <!-- 表头区 -->
        <el-table-column type="index" label="#">
        </el-table-column>
        <el-table-column label="角色名称" prop="name">
        </el-table-column>
        <el-table-column label="角色描述" prop="describe">
        </el-table-column>
        <el-table-column label="操作" prop="desc">
          <!-- 操作按钮 -->
          <el-button type="primary" icon="el-icon-edit">编辑</el-button>
          <el-button type="danger" icon="el-icon-delete">删除</el-button>
          <el-button type="warning" icon="el-icon-setting">分配权限</el-button>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
import Breadcrumbs from '@/components/Breadcrumbs/breadcrumbs.vue'

export default {
  name: 'Role',
  data() {
    return {
      nav: { one: '权限管理', two: '角色列表' },
      tableData: [
        {
          id: '101',
          name: '主管',
          describe: '技术负责人',
          children: [
            {
              cid: 1,
              autoname: '商品管理',
              children: [
                {
                  countid: 10,
                  autoname: '商品列表'
                },
                {
                  countid: 11,
                  autoname: '分类参数'
                },
                {
                  countid: 12,
                  autoname: '商品分类'
                }
              ]
            },
            {
              cid: 2,
              autoname: '订单管理'
            },
            {
              cid: 3,
              autoname: '权限管理'
            }
          ]
        },
        {
          id: '102',
          name: '测试角色1号',
          describe: '测试角色描述'
        },
        {
          id: '103',
          name: '测试角色2号',
          describe: '测试角色描述'
        },
        {
          id: '104',
          name: '测试角色2号',
          describe: '技术负责人'
        },
        {
          id: '105',
          name: '测试角色2号',
          describe: '技术负责人'
        },
        {
          id: '106',
          name: '测试角色2号',
          describe: '技术负责人'
        },
        {
          id: '107',
          name: '测试角色2号',
          describe: '技术负责人'
        }
      ]
    }
  },
  methods: {
  },
  components: {
    Breadcrumbs
  }
}
</script>

<style lang="less" scoped>
.role_container {
  padding: 20px;
  .el-tag {
    margin: 7px 7px 7px 0;
  }
}
.dbtop {
  border-top: 1px solid #eeee;
}
.dbbutton {
  border-bottom: 1px solid #eee;
}

</style>
