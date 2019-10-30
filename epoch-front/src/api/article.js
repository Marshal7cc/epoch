import request from '@/utils/request'

export function query(page, pageSize, queryParam) {
  return request({
    url: '/base/role/query/' + page + '/' + pageSize + '',
    method: 'post',
    data: queryParam
  })
}

export function submit(dto) {
  return request({
    url: '/base/role/submit',
    method: 'post',
    data: dto
  })
}

export function remove(idList) {
  return request({
    url: '/base/role/remove',
    method: 'post',
    data: idList
  })
}

export function queryById(id) {
  return request({
    url: '/base/role/queryById',
    method: 'get',
    params: {id}
  })
}
