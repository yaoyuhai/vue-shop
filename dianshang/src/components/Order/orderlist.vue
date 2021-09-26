<template>
  <div class="order_container">
    <!-- 面包屑 -->
    <Breadcrumb :nav="nav"></Breadcrumb>

    <!-- 卡片 -->
    <el-card>
      <!-- 搜索框 -->
      <el-row :gutter="0">
        <el-col :span="8">
          <el-input placeholder="请输入内容">
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
      </el-row>

      <!-- table表格 -->
      <el-table :data="Table" border>
        <el-table-column type="index" label="#" width="52">
        </el-table-column>
        <el-table-column label="订单编号" prop="Ordernumber" width="500">
        </el-table-column>
        <el-table-column label="订单价格" prop="Orderprice" width="100">
        </el-table-column>
        <el-table-column label="是否付款" prop="Payment" width="100">
        </el-table-column>
        <el-table-column label="是否发货" prop="Ship" width="100">
        </el-table-column>
        <el-table-column label="下单时间" prop="time" width="187">
        </el-table-column>
        <el-table-column label="操作" prop="index" width="180">
          <!-- 放个插槽方便传参 -->
          <template>
            <el-button type="primary" icon="el-icon-edit" size="small"></el-button>
            <el-button type="success" icon="el-icon-location" size="small"></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
import Breadcrumb from '@/components/Breadcrumbs/breadcrumbs.vue'
export default {
  name: 'Orderlist',
  data() {
    return {
      nav: { one: '订单管理', two: '订单列表' },
      // 表格数据
      Table: [
        { id: 1, Ordernumber: 'djasbduagui', Orderprice: 40, Payment: '未付款', Ship: '是', time: '2020-1-25 20:01:46' },
        { id: 2, Ordernumber: 'djasbduagui', Orderprice: 40, Payment: '未付款', Ship: '是', time: '2020-1-25 20:01:46' },
        { id: 3, Ordernumber: 'djasbduagui', Orderprice: 40, Payment: '未付款', Ship: '是', time: '2020-1-25 20:01:46' },
        { id: 4, Ordernumber: 'djasbduagui', Orderprice: 40, Payment: '未付款', Ship: '是', time: '2020-1-25 20:01:46' },
        { id: 5, Ordernumber: 'djasbduagui', Orderprice: 40, Payment: '未付款', Ship: '是', time: '2020-1-25 20:01:46' },
        { id: 6, Ordernumber: 'djasbduagui', Orderprice: 40, Payment: '未付款', Ship: '是', time: '2020-1-25 20:01:46' },
        { id: 7, Ordernumber: 'djasbduagui', Orderprice: 40, Payment: '未付款', Ship: '是', time: '2020-1-25 20:01:46' },
        { id: 8, Ordernumber: 'djasbduagui', Orderprice: 40, Payment: '未付款', Ship: '是', time: '2020-1-25 20:01:46' },
        { id: 9, Ordernumber: 'djasbduagui', Orderprice: 40, Payment: '未付款', Ship: '是', time: '2020-1-25 20:01:46' }
      ]
    }
  },
  components: {
    Breadcrumb
  }
}
</script>

<style lang="less" scoped>
.order_container {
  padding: 20px;
}
</style>
