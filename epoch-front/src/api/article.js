import request from '@/utils/request'

export function fetchList(pagination,queryParam) {
  return request({
    url: '/base/role/query/' + pagination.page + '/' + pagination.pageSize + '',
    method: 'post',
    data: queryParam
  })
}

export function fetchArticle(id) {
  return request({
    url: '/article/detail',
    method: 'get',
    params: {id}
  })
}

export function fetchPv(pv) {
  return request({
    url: '/article/pv',
    method: 'get',
    params: {pv}
  })
}

export function createArticle(data) {
  return request({
    url: '/article/create',
    method: 'post',
    data
  })
}

export function updateArticle(data) {
  return request({
    url: '/article/update',
    method: 'post',
    data
  })
}
