import request from '@/utils/request'

/*
 * @description
 */
export function getLogin(username, password) {
  return request({
    url: '/base/login',
    method: 'post',
    data: {
      "username": username,
      "password": password
    }
  })
}
