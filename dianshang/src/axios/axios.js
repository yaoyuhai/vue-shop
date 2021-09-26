import axios from 'axios'
import qs from 'qs'

const request = axios.create({
  baseURL: 'http://localhost:8081'
})

export default {
  // get请求
  Get(url, params) {
    return new Promise((resolve, reject) => {
      request({
        method: 'get',
        url: url,
        params: params
      })
        .then(res => resolve(res))
        .catch(err => {
          console.log(err, '错误')
        })
    })
  },
  // post请求
  Post(url, data) {
    return new Promise((resolve, reject) => {
      request({
        method: 'post',
        url: url,
        // 解决post中data传参
        transformRequest: [function(data) {
          return qs.stringify(data)
        }],
        data: data
      })
        .then(res => resolve(res))
        .catch(err => {
          console.log(err, '错误')
        })
    })
  },
  // put请求
  Put(url) {
    return new Promise((resolve, reject) => {
      request({
        method: 'put',
        url: url
      })
        .then(res => resolve(res))
        .catch(err => {
          console.log(err, '错误')
        })
    })
  },
  // delete请求
  Delete(url) {
    return new Promise((resolve, reject) => {
      request({
        method: 'delete',
        url: url
      })
        .then(res => resolve(res))
        .catch(err => {
          console.log(err, '错误')
        })
    })
  }
}
