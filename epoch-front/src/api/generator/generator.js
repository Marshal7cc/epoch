import request from '@/utils/request'

export function query(pagination, queryParam) {
  const page = pagination.page
  const pageSize = pagination.pageSize
  return request({
    url: '/base/role/query/' + page + '/' + pageSize + '',
    method: 'post',
    data: queryParam
  })
}

export function submit(dto) {
  // var form = document.createElement("form");
  // form.id = "form_id";
  // form.name = "form_name";
  // // 添加到 body 中
  // document.body.appendChild(form);
  // // 创建一个输入框
  // var input = document.createElement("input");
  // // 设置相应参数
  // input.type = "text";
  // input.name = "username";
  // input.value = "zhangsan";
  // // 将该输入框插入到 form 中
  // form.appendChild(input);
  // // form 提交方式
  // form.method = "POST";
  // // form 提交路径
  // form.action = "/index";
  // // 执行提交
  // form.submit();
  // // 删除该 form
  // document.body.removeChild(form);

  return request({
    url: '/epoch-generator/generator/create',
    method: 'post',
    data: {
      'generateMethod': 'zip',
      'packagePath': 'sys',
      'parentPackagePath': 'com.marshal.epoch',
      'projectPath': 'D:\\',
      'targetName': 'sys_user'
    }
  })
}

export function remove(rows) {
  return request({
    url: '/base/role/remove',
    method: 'post',
    data: rows
  })
}
