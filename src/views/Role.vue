<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="name"></el-input>

      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm class="ml-5" confirm-button-text='确定' cancel-button-text='我再想想' icon="el-icon-info"
        icon-color="red" title="您确定批量删除这些数据吗？" @confirm="delBatch">
        <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>

    </div>
    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"
      @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="ID" width="80"></el-table-column>
      <el-table-column prop="name" label="名称"></el-table-column>
      <el-table-column prop="flag" label="唯一标识"></el-table-column>
      <el-table-column prop="description" label="描述"></el-table-column>
      <el-table-column label="操作" width="300" align="center">
        <template slot-scope="scope">
          <el-button type="info" @click="selectMenu(scope.row.id)">分配菜单 <i class="el-icon-menu"></i></el-button>
          <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
          <el-popconfirm class="ml-5" confirm-button-text='确定' cancel-button-text='我再想想' icon="el-icon-info"
            icon-color="red" title="您确定删除吗？" @confirm="del(scope.row.id)">
            <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageNum"
        :page-sizes="[2, 5, 10, 20]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>

    <el-dialog title="角色信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="80px" size="small">
        <el-form-item label="名称">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="唯一标识">
          <el-input v-model="form.flag" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="form.description" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="菜单分配" :visible.sync="menuDialogVis" width="30%">
      <el-tree 
      :props = "props"
      ref="tree"
      :data="menuData" 
      show-checkbox 
      node-key="id"  
      :default-expanded-keys="expends"
      :default-checked-keys="checks"
      :check-strictly="true"
      >
        <span class="custom-tree-node" slot-scope="{ node, data }">
          <span><i :class="data.icon"></i> {{ data.name }}</span>
        </span>
      </el-tree>
      <div slot="footer" class="dialog-footer">
        <el-button @click="menuDialogVis = false">取 消</el-button>
        <el-button type="primary" @click="saveRoleMenu">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  export default {
    name: "Role",
    data() {
      return {
        tableData: [],
        total: 0,
        pageNum: 1,
        pageSize: 5,
        name: "",
        form: {},
        dialogFormVisible: false,
        menuDialogVis:false,
        multipleSelection: [],
        menuData: [],
        props:{lable:"name"},
        expends:[],
        checks:[1,4,6],
        roleId:0,
      }
    },
    created() {
      this.load()
    },
    methods: {
      load() {
        this.request.get("/role/page", {
          params: {
            pageNum: this.pageNum,
            pageSize: this.pageSize,

          }
        }).then(res => {

          // 注意data
          this.tableData = res.data.records
          this.total = res.data.total
        })

      },
      handleAdd() {
        this.dialogFormVisible = true
        this.form = {}
      },
      handleEdit(row) {
        this.form = row
        this.dialogFormVisible = true
      },
      handleSelectionChange(val) {
        console.log(val)
        this.multipleSelection = val
      },
      save() {
        this.request.post("/role", this.form).then(res => {
          if (res.code == "200") {
            this.$message.success("保存成功")
            this.dialogFormVisible = false
            
            this.load()
          } else {
            this.$message.error("保存失败")
          }
        })
      },
      handleSizeChange(pageSize) {
        console.log(pageSize)
        this.pageSize = pageSize
        this.load()
      },
      handleCurrentChange(pageNum) {
        console.log(pageNum)
        this.pageNum = pageNum
        this.load()
      },
      del(id) {
        this.request.delete("/role/" + id).then(res => {
          if (res.code == "200") {
            this.$message.success("删除成功")
            this.load()
          } else {
            this.$message.error("删除失败")
          }
        })
      },
      delBatch() {
        let ids = this.multipleSelection.map(v => v.id)  
        this.request.post("/role/del/batch", ids).then(res => {
          if (res.code == "200") {
            this.$message.success("批量删除成功")
            this.load()
          } else {
            this.$message.error("批量删除失败")
          }
        })
      },
      reset() {
        this.name = ""
        this.load()
      },
      saveRoleMenu() {
        this.request.post("/role/roleMenu/" + this.roleId, 
        this.$refs.tree.getCheckedKeys().concat(this.$refs.tree.getHalfCheckedKeys()))
        .then(res => {
          if(res.code == "200"){
            this.$message.success("修改成功")
            this.menuDialogVis = false
            this.$store.commit("resetLogout")
            
          }else{
            this.$message.error(res.msg)
          }
        })
      },

      selectMenu(roleId) {
        this.menuDialogVis = true
        this.roleId = roleId
        this.request.get("/menu").then(res => {
          this.menuData = res.data
          this.expends= this.menuData.map(v=>{ return v.id})
        })

        this.request.get("/role/roleMenu/" + roleId)
        .then(res => {
          console.log(roleId,res.data)
          this.checks = res.data
        })
      },
      handleCheckChange(data,checked,indesterminate){
        // console.log(data,checked,indesterminate)
      }
    }
  }
</script>


<style>
  .headerBg {
    background: #eee !important;
  }
</style>