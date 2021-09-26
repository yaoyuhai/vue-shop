<template>
  <div class="user_container">
    <!-- 面包屑 -->
    <Breadcrumbs :nav="nav"></Breadcrumbs>

    <!-- 卡片视图 -->
    <el-card>
      <!-- 搜索和添加 -->
      <el-row :gutter="10">
        <el-col :span="7">
          <el-input placeholder="请输入姓名查询" v-model="queryIf.query" clearable @clear="initlist" @keyup.enter.native="initlist">
            <el-button slot="append" icon="el-icon-search" @click="initlist"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addList">添加用户</el-button>
        </el-col>
      </el-row>

      <!-- 用户列表区域 -->
      <el-table class="table_box" :data="tableData" border>
          <el-table-column type="index" label="#" width="60">
          </el-table-column>
          <el-table-column prop="username" label="姓名">
          </el-table-column>
          <el-table-column prop="email" label="邮箱">
          </el-table-column>
          <el-table-column prop="mobile" label="电话">
          </el-table-column>
          <el-table-column prop="roleName" label="角色" >
          </el-table-column>
          <el-table-column prop="state" label="状态">
            <!-- 插槽 -->
            <template #default="scope">
              <el-switch v-model="scope.row.state" @change="stateChange(scope.row.id,$event)"></el-switch>
            </template>
          </el-table-column>
          <el-table-column prop="" label="操作" width="220">
            <template #default="scope">
              <el-button type="primary" size="medium" icon="el-icon-edit" @click="Revise(scope.row.id)"></el-button>
              <el-button type="danger" size="medium" icon="el-icon-delete" @click="Delete(scope.row.id)"></el-button>
              <el-button type="warning" size="medium" icon="el-icon-setting" @click="Distribute(scope.row.id)"></el-button>
            </template>
          </el-table-column>
      </el-table>

      <!-- 分页区域 -->
      <el-pagination
          :page-sizes="[2, 3, 5]"
          :page-size="10"
          layout="total, sizes, prev, pager, next, jumper"
          :total="totalpage"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange">
      </el-pagination>

      <!-- 添加用户的对话框 -->
      <el-dialog title="添加用户" :visible.sync="dialogFormVisible">
         <!-- 添加用户的表单 -->
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px">
          <el-form-item label="用户名" prop="user" required>
            <el-input v-model="ruleForm.user"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="pass" required>
            <el-input type="password" v-model="ruleForm.pass"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="mail" required>
            <el-input v-model="ruleForm.mail"></el-input>
          </el-form-item>
          <el-form-item label="电话" prop="template" required>
            <el-input v-model="ruleForm.template" maxlength="11"></el-input>
          </el-form-item>
        </el-form>

        <!-- 确定和取消按钮 -->
        <div slot="footer">
          <el-button @click="Cancel('ruleForm')">取 消</el-button>
          <el-button type="primary" @click="Sure('ruleForm')">确 定</el-button>
        </div>
      </el-dialog>

      <!-- 修改用户的对话框 -->
      <el-dialog title="修改用户" :visible.sync="dialogRevise">
        <!-- 修改用户的表单 -->
        <el-form :model="reviseForm" :rules="reviseRules" ref="reviseForm" label-width="100px">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="reviseForm.username" disabled></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email" required>
            <el-input v-model="reviseForm.email"></el-input>
          </el-form-item>
          <el-form-item label="电话" prop="mobile" required>
            <el-input maxlength="11" v-model="reviseForm.mobile"></el-input>
          </el-form-item>
        </el-form>

        <!-- 确定和取消按钮 -->
        <div slot="footer">
          <el-button @click="dialogRevise = false">取消</el-button>
          <el-button type="primary" @click="reviseSure('reviseForm')">确定</el-button>
        </div>
      </el-dialog>

      <!-- 改变用户的角色 -->
      <el-dialog title="分配角色" :visible.sync="dialogDistribute">
        <el-select v-model="roleName.role" placeholder="请选择用户角色">
          <el-option label="普通用户" value="1"></el-option>
          <el-option label="超级管理员" value="2"></el-option>
        </el-select>

        <div slot="footer">
          <el-button @click="dialogDistribute = false">取消</el-button>
          <el-button type="primary" @click="distributeInfo">确定</el-button>
        </div>
      </el-dialog>
    </el-card>
  </div>
</template>

<script>
// 导入公用面包屑组件
import Breadcrumbs from '@/components/Breadcrumbs/breadcrumbs.vue'
import request from '@/axios/axios.js'
export default {
  name: 'User',
  data() {
    return {
      nav: { one: '用户管理', two: '用户列表' },
      // 用来第一次请求的数据
      queryIf: {
        // 要搜索的值
        query: '',
        // 默认第一页
        pagenum: 1,
        // 一页显示多少条数据
        pagesize: 2
      },
      // 当前总页条数
      totalpage: 0,
      // 用来控制添加用户的对话框的显示和隐藏
      dialogFormVisible: false,
      // 用来控制修改用户的对话框的显示和隐藏
      dialogRevise: false,
      // 用来控制分配角色的对话框的显示和隐藏
      dialogDistribute: false,
      roleName: {
        id: 0,
        role: ''
      },
      // 用户的数据
      tableData: [],
      // 添加用户的表单数据
      ruleForm: {
        user: '',
        pass: '',
        mail: '',
        template: '',
        role: ''
      },
      // 修改用户的表单数据
      reviseForm: {},
      // 表单的验证
      rules: {
        user: [
          { required: true, message: '用户名不能为空', trigger: 'blur' }
        ],
        pass: [
          { required: true, message: '密码不能为空', trigger: 'blur' }
        ],
        mail: [
          { required: true, message: '邮箱不能为空', trigger: 'blur' }
        ],
        template: [
          { required: true, message: '电话不能为空', trigger: 'blur' }
        ],
        role: [
          { required: true, message: '角色不能为空', trigger: 'change' }
        ]
      },
      reviseRules: {
        email: [
          { required: true, message: '邮箱不能为空', trigger: 'change' }
        ],
        mobile: [
          { required: true, message: '电话不能为空', trigger: 'change' }
        ]
      }
    }
  },
  created() {
    this.initlist()
  },
  methods: {
    /**
     * @methods initlist() 拿到所有用户的信息
     */
    async initlist() {
      const { data: res } = await request.Get('/users', this.queryIf)
      res.data.users.map(item => {
        item.state = item.state === '1'
      })
      this.tableData = res.data.users
      this.totalpage = res.data.totalpage
    },
    /**
     * @method selectId() 通过id去获取数据
     * @param id 用户的id
     */
    async selectId(id) {
      const { data: res } = await request.Get('/users/' + id)
      this.reviseForm = res.data
    },
    /**
     * @method stateChange() 当状态发生改变
     * @param state 状态变化之后的值
     */
    async stateChange(id, state) {
      const { data: res } = await request.Put('/users/' + id + '/state/' + state)
      console.log(res)
    },
    /**
     * @method addList() 添加用户
     */
    addList() {
      this.dialogFormVisible = true
    },
    /**
     * @method Delete() 删除用户
     * @param id 要删除用户的id值
     */
    async Delete(id) {
      const Confirm = await this.$confirm('此用户信息将被永久删除，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warninga'
      }).catch(err => err)

      // 如果用户点了取消就会返回一个cancel,否则返回confirm
      // 当判断Confirm不等于confrim时，代表用户点了取消
      if (Confirm !== 'confirm') {
        return this.$message.info('已取消删除')
      }
      // 用户点了确定就会发起请求
      const { data: res } = await request.Delete('/users/' + id)
      if (res.meta.status !== 200) {
        return this.$message.error('用户删除失败')
      }
      this.$message.success('删除成功')
      this.initlist()
    },
    /**
     * @method Revise() 修改用户
     * @param id 要修改用户的id值
     */
    Revise(id) {
      this.dialogRevise = true
      this.selectId(id)
      this.roleName.id = id
    },
    /**
     *@method Sure() 添加数据
     *@param ruleForm 当前表单数据
     */
    Sure(ruleForm) {
      this.$refs[ruleForm].validate(async(valid) => {
        if (!valid) return false
        // 发起添加用户的请求
        const { data: res } = await request.Post('/users', {
          username: this.ruleForm.user,
          password: this.ruleForm.pass,
          email: this.ruleForm.mail,
          mobile: this.ruleForm.template
        })
        if (res.meta.status === 201) {
          // 关闭弹窗
          this.dialogFormVisible = false
          this.initlist()
          return this.$message.success('用户添加成功')
        }
      })
    },
    /**
     * @methods reviseSure() 修改用户
     * @param reviseForm 当前表单数据
     */
    reviseSure(reviseForm) {
      this.$refs[reviseForm].validate(async valid => {
        if (!valid) return false
        // 发起修改用户的请求
        const { data: res } = await request.Get('/user', {
          id: this.roleName.id,
          email: this.reviseForm.email,
          mobile: this.reviseForm.mobile
        })
        if (res.meta.status === 200) {
          this.dialogRevise = false
          this.$message.success('更新成功')
          this.initlist()
        }
      })
    },
    /**
     *@method Cancel() 关闭弹窗
     *@param ruleForm 当前表单数据
     */
    Cancel(ruleForm) {
      // 清空表单和退出弹窗
      this.$refs[ruleForm].resetFields()
      this.dialogFormVisible = false
    },
    /**
     * @method handleSizeChange() 每页多少条
     * @param num 选择框改变之后的值
     */
    handleSizeChange(num) {
      this.queryIf.pagesize = num
      this.initlist()
    },
    /**
     * @method handleCurrentChange() 当前页码改变
     * @param page 页码改变之后的值
     */
    handleCurrentChange(page) {
      this.queryIf.pagenum = page
      this.initlist()
    },
    /**
     * @method Distribute() 分配用户的角色
     * @param id 当前用户的id
     */
    Distribute(id) {
      this.dialogDistribute = true
      this.roleName.id = id
    },
    /**
     * @method distributeChange() 获取到选择框中的value
     */
    async distributeInfo() {
      const { data: res } = await request.Put('/users/' + this.roleName.id + '/' + this.roleName.role)
      if (res.meta.status === 200) {
        this.dialogDistribute = false
        this.initlist()
        return this.$message.success('用户角色分配成功')
      }
    }
  },
  components: {
    Breadcrumbs
  }
}
</script>

<style lang="less" scoped>
.user_container {
  padding: 20px;
}
/deep/.el-select {
  width: 100%;
}
</style>
