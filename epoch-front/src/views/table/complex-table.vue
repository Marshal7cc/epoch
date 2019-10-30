<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="queryParam.roleName" placeholder="角色名称" style="width: 200px;" class="filter-item"/>
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="query">
        查询
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit"
                 @click="add">
        新增
      </el-button>
    </div>
    <el-table
      :key="0"
      v-loading="loading"
      :data="rows"
      border
      fit
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column
        type="selection">
      </el-table-column>
      <el-table-column label="角色ID" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.roleId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="角色代码" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.roleCode }}</span>
        </template>
      </el-table-column>
      <el-table-column label="角色名称" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.roleName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="生效日期" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.startActiveDate }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="{row}">
          <el-button type="primary" size="mini" @click="edit(row)">
            编辑
          </el-button>
          <el-button size="mini" type="danger" @click="remove(row.roleId)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="pagination.total>0" :total="pagination.total" :page.sync="pagination.page"
                :pageSize.sync="pagination.pageSize"
                @pagination="query"/>

    <el-dialog :title="dialog.title" :visible.sync="dialog.visible">
      <el-form ref="dataForm" :rules="rules" :model="dto" label-position="left" label-width="70px"
               style="width: 400px; margin-left:50px;">
        <el-form-item label="Type" prop="type">
          <el-select v-model="dto.roleName" class="filter-item" placeholder="Please select">
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialog.visible = false">
          取消
        </el-button>
        <el-button type="primary" @click="save">
          确认
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {query, submit, remove, queryById} from '@/api/article'
  import Pagination from '@/components/Pagination'

  export default {
    components: {Pagination},
    data() {
      return {
        rows: null,
        queryParam: {},
        dto: {},
        pagination: {
          total: 0,
          page: 1,
          pageSize: 10
        },
        dialog: {
          title: null,
          visible: false
        },
        loading: true
      }
    },
    created() {
      this.query()
    },
    methods: {
      query() {
        this.loading = true
        query(this.pagination.page, this.pagination.pageSize, this.queryParam).then(response => {
          if (response.success) {
            this.rows = response.data.rows
            this.pagination.total = response.data.total
            setTimeout(() => {
              this.loading = false
            }, 0.5 * 1000)
          } else {
            this.$notify({
              message: response.message,
              type: "error",
              duration: 2000
            })
          }
        })
      },
      reset() {
        this.dto = {}
      },
      add() {
        this.reset()
        this.dialog.visible = true
        this.dialog.title = '新增'
      },
      edit(row) {
        this.dto = Object.assign({}, row)
        this.dialog.visible = true
        this.dialog.title = '编辑'
      },
      save() {
        submit(this.dto).then((response) => {
          this.dialog.visible = false
          this.$notify({
            message: response.message,
            type: response.success ? "success" : "error",
            duration: 2000
          })
        })
      },
      delete(row) {
        this.query()
      }
    }
  }
</script>
