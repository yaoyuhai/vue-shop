<template>
  <div class="login_container">
    <div class="login_box">
      <!-- 图片 -->
      <div class="login_img">
        <img src="../../assets/logo.png" alt="" />
      </div>
      <!-- from 表单 -->
      <div class="login_from">
        <el-form ref="form" :model="form" :rules="rules">
          <!-- 用户 -->
          <el-form-item prop="username">
             <el-input prefix-icon="el-icon-user-solid" placeholder="请输入用户名" v-model="form.username"></el-input>
          </el-form-item>
          <!-- 密码 -->
          <el-form-item prop="password">
             <el-input prefix-icon="el-icon-unlock" type="password" placeholder="请输入密码" @keyup.enter.native="submitForm('form')" v-model="form.password"></el-input>
          </el-form-item>
          <!-- 按钮 -->
          <el-form-item class="form_button">
            <el-button type="primary" @click="submitForm('form')">登录</el-button>
            <el-button type="info" @click="removeForm('form')">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
// 导入axios
import request from '@/axios/axios.js'
export default {
  name: 'Login',
  data() {
    return {
      form: {
        username: '',
        password: ''
      },
      // 表单验证规则
      rules: {
        username: [
          { required: true, message: '用户名不能为空', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    /**
     * @method submitForm() form表单提交
     * @param form  当前表单的数据
     */
    submitForm(form) {
      this.$refs[form].validate(async valid => {
        // 判断用户输入的值
        if (!valid) return false
        const { data: res } = await request.Post('/login', {
          username: this.form.username,
          password: this.form.password
        })
        // 判断是否请求成功和有没有token
        if (res.meta.status === 200 && res.data.token) {
          localStorage.setItem('token', res.data.token)
          this.$router.replace('/home')
        } else {
          alert('用户名或者密码不正确！！！')
          this.removeForm('form')
        }
      })
    },
    /**
     * @method removeForm() form表单的重置
     * @param form 当前表单的数据
     */
    removeForm(form) {
      this.$refs[form].resetFields()
    }
  }
}
</script>

<style lang="less" scoped>
.login_container {
  background-color: #35495e;
  height: 100%;
  .login_box {
    width: 450px;
    height: 300px;
    background-color: #fff;
    border-radius: 3px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    .login_img {
      width: 130px;
      height: 130px;
      border: 1px solid #eee;
      background-color: #fff;
      border-radius: 50%;
      padding: 10px;
      box-shadow: 0 0 10px #ddd;
      position: absolute;
      left: 50%;
      transform: translate(-50%, -40%);
      img {
        width: 100%;
        height: 100%;
        border-radius: 50%;
        background-color: #eee;
      }

    }
    .login_from {
      padding: 0 20px;
      .el-form {
        width: 410px;
        display: inline-block;
        margin-top: 20px;
        position: absolute;
        top: 50%;
        transform: translate(0,-30%);
      }
    }
    .form_button {
      display: flex;
      justify-content: flex-end;
    }
  }
}
</style>
