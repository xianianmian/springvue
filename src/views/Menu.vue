<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="name"></el-input>
      <el-input style="width: 200px" placeholder="请输入路径" suffix-icon="el-icon-search" v-model="path"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd(false)">新增菜单 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm class="ml-5" confirm-button-text='确定' cancel-button-text='我再想想' icon="el-icon-info"
        icon-color="red" title="您确定批量删除这些数据吗？" @confirm="delBatch">
        <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
    </div>
    <el-table :data="tableData" border stripe 
    :header-cell-class-name="'headerBg'"
      @selection-change="handleSelectionChange"
      row-key="id" default-expand-all>
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="ID" width="80"></el-table-column>
      <el-table-column prop="name" label="名称"></el-table-column>
      <el-table-column prop="path" label="路径"></el-table-column>
      <el-table-column prop="pagePath" label="页面路径"></el-table-column>
      <el-table-column label="图标">
        <template  slot-scope="scope" >
          <i :class="scope.row.icon" style="font-size: 20px;"></i>
        </template>
      </el-table-column>
      <el-table-column prop="description" label="描述" ></el-table-column>
      <el-table-column label="操作" width="320" align="center">
        <template slot-scope="scope" >
          <el-button type="primary" @click="handleAdd(scope.row.id)" v-if="!scope.row.pid && !scope.row.path">
            添加子菜单 <i class="el-icon-edit"></i>
          </el-button>
          <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
          <el-popconfirm class="ml-5" confirm-button-text='确定' cancel-button-text='我再想想' icon="el-icon-info"
            icon-color="red" title="您确定删除吗？" @confirm="del(scope.row.id)">
            <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="菜单信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="80px" size="small">
        <el-form-item label="名称">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="路径">
          <el-input v-model="form.path" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="页面路径">
          <el-input v-model="form.pagePath" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="图标">
          <template  slot-scope="scope" >
            <el-select clearable v-model="form.icon" placeholder="请选择" style="width: 80%;">
            <el-option v-for="item in options" :key="item.name" :lable="item.name" :value="item.value">
              <i :class="item.value"/>{{item.value}}
            </el-option>
            
            </el-select>
          </template>
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
  </div>
</template>

<script>
  export default {
    name: "Menu",
    data() {
      return {
        tableData: [],
        total: 0,
        pageNum: 1,
        pageSize: 5,
        name: "",
        email: "",
        address: "",
        path:"",
        form: {},
        dialogFormVisible: false,
        multipleSelection: [],
        options:[],

      }
    },
    created() {
      this.load()
    },
    methods: {
      load() {
        this.request.get("/menu", {
          params: {
            name:this.name,
          }
        }).then(res => {

          // 注意data
          this.tableData = res.data

        })
      },
      handleAdd(pid) {
        this.dialogFormVisible = true
        this.form = {}
        console.log(pid,'ss')
        if(pid ){
          this.form.pid = pid
        }
      },
      handleEdit(row) {
        this.form = row
        this.dialogFormVisible = true

        this.request.get("/menu/icons").then(res => {
          this.options = res.data
          console.log(this.options,'tt')
        })
      },
      handleSelectionChange(val) {
        console.log(val)
        this.multipleSelection = val
      },
      save() {console.log("tt")
        this.request.post("/menu", this.form).then(res => {
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
      handleExcelImportSuccess() {
        this.$message.success("导入成功")
        this.load()
      },
      exp() {
        window.open("http://localhost:9090/menu/export")
      },
      del(id) {
        this.request.delete("/menu/" + id).then(res => {
          if (res.code == "200") {
            this.$message.success("删除成功")
            this.load()
          } else {
            this.$message.error("删除失败")
          }
        })
      },
      delBatch() {
        let ids = this.multipleSelection.map(v => v.id)  // [{}, {}, {}] => [1,2,3]
        this.request.post("/menu/del/batch", ids).then(res => {
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
      savemenuMenu() {
        // this.request.post("/menu/menuMenu/" + this.menuId, this.$refs.tree.getCheckedKeys()).then(res => {
        //   if (res.code === '200') {
        //     this.$message.success("绑定成功")
        //     this.menuDialogVis = false

        //     // 操作管理员角色后需要重新登录
        //     if (this.menuFlag === 'menu_ADMIN') {
        //       this.$store.commit("logout")
        //     }

        //   } else {
        //     this.$message.error(res.msg)
        //   }
        // })
      },

      selectMenu(menu) {
        // this.menuId = menu.id
        // this.menuFlag = menu.flag

        // // 请求菜单数据
        // this.request.get("/menu").then(res => {
        //   this.menuData = res.data

        //   // 把后台返回的菜单数据处理成 id数组
        //   this.expends = this.menuData.map(v => v.id)
        // })

        // this.request.get("/menu/menuMenu/" + this.menuId).then(res => {
        //   this.checks = res.data

        //   this.request.get("/menu/ids").then(r => {
        //     const ids = r.data
        //     ids.forEach(id => {
        //       if (!this.checks.includes(id)) {
        //         this.$refs.tree.setChecked(id, false)
        //       }
        //     })


        //   })
        //   this.menuDialogVis = true
        // })
      },
    }
  }
</script>


<style>
  .headerBg {
    background: #eee !important;
  }
</style>