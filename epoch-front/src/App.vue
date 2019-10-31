<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
  export default {
    name: 'App'
  }
</script>

<style>
  /* 解决element-ui的table表格控件表头与内容列不对齐问题 add by Marshal 2019/10/31 */
  .el-table th.gutter {
    display: table-cell !important;
  }
</style>
