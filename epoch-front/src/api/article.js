import request from '@/utils/request'

export function query(pagination, queryParam) {
  let page = pagination.page
  let pageSize = pagination.pageSize
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

export function remove(rows) {
  return request({
    url: '/base/role/remove',
    method: 'post',
    data: rows
  })
}
