import request from '@/utils/request'
/*
 * @description
 */
export function getLogin(username, password) {
  return request({
    url: '/login',
    method: 'get',
    params: ''
  })
}
