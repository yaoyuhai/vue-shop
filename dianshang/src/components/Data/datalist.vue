<template>
  <div class="data_container">
    <!-- 面包屑 -->
    <Breadcrumbs :nav="nav"></Breadcrumbs>

    <!-- 卡片区域 -->
    <el-card>
      <div class="echarts" style="width: 800px;height: 400px;"></div>
    </el-card>
  </div>
</template>

<script>
// 导入echarts图表库
import * as echarts from 'echarts'
import Breadcrumbs from '@/components/Breadcrumbs/breadcrumbs.vue'

export default {
  name: 'Datalist',
  data() {
    return {
      nav: { one: '数据统计', two: '数据报表' }
    }
  },
  mounted() {
    // 初始化echarts
    const Myecharts = echarts.init(document.querySelector('.echarts'))

    // 图表的配置和数据
    const option = {
      // 左边的标题
      title: {
        text: '用户来源'
      },
      // 提示框组件
      tooltip: {
        trigger: 'axis', // 触犯类型：axis坐标轴
        axisPointer: {
          type: 'cross', // 十字架指示器
          // 设置选中字体的color
          label: {
            backgroundColor: '#6a7985'
          }
        }
      },
      // 定义上面标题的文字
      legend: {
        data: ['华东', '华裔', '华北', '西部', '其他']
      },
      grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
      },
      // x坐标轴
      xAxis: [
        {
          type: 'category',
          boundarGap: false,
          data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
        }
      ],
      // y  坐标轴
      yAxis: [
        {
          type: 'value'
        }
      ],
      series: [
        {
          name: '华东',
          type: 'line',
          stack: '总量',
          areaStyle: {},
          emphasis: {
            focus: 'series'
          },
          data: [120, 132, 101, 134, 90, 230, 210]
        },
        {
          name: '华裔',
          type: 'line',
          stack: '总量',
          areaStyle: {},
          emphasis: {
            focus: 'series'
          },
          data: [220, 182, 191, 234, 290, 330, 310]
        },
        {
          name: '华北',
          type: 'line',
          stack: '总量',
          areaStyle: {},
          emphasis: {
            focus: 'series'
          },
          data: [150, 232, 201, 154, 190, 330, 410]
        },
        {
          name: '西部',
          type: 'line',
          stack: '总量',
          areaStyle: {},
          emphasis: {
            focus: 'series'
          },
          data: [320, 332, 301, 334, 390, 330, 320]
        },
        {
          name: '其他',
          type: 'line',
          stack: '总量',
          label: {
            show: true,
            position: 'top'
          },
          areaStyle: {},
          emphasis: {
            focus: 'series'
          },
          data: [820, 932, 901, 934, 1290, 1330, 1320]
        }
      ]
    }

    // 把配置和数据显示在图表上
    Myecharts.setOption(option)
  },
  components: {
    Breadcrumbs
  }
}
</script>

<style lang="less" scoped>
.data_container {
  padding: 20px;
}
</style>
