import request from '@/utils/request'

/*
 * @description
 */
export function getLogin(username, password) {
  return request({
    url: '/auth/login',
    method: 'post',
    data: {
      "username": username,
      "password": password
    }
  })
}
