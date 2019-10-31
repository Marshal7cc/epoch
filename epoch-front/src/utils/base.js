/**
 * @author Marshal
 * @date   2019/10/31
 * @desc   基础通用方法
 */
export default {
  /**
   * 通用处理返回结果
   * @param response
   * @param scope
   */
  parseResponse: function (response, scope) {
    if (!response.success) {
      scope.$notify({
        message: response.message,
        type: "error",
        duration: 2000
      })
    } else {
      if (response.data) {
        scope.rows = response.data.rows
        scope.pagination.total = response.data.total
        setTimeout(() => {
          scope.loading = false
        }, 0.5 * 1000)
      } else {
        scope.$notify({
          message: response.message,
          type: "success",
          duration: 2000
        })
      }
    }
  },

  /**
   * 批量删除
   * 是否选中数据校验
   * @param scope
   */
  removeCheck: function (scope) {
    if (scope.$refs.dataGrid.selection.length == 0) {
      scope.$notify({
        message: "请至少选择一条数据",
        type: "warning",
        duration: 2000
      })
    }
  }
}
