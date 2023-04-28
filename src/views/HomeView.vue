<template>
  <div class="home">
    <el-container>
      <el-aside :width="asideWidth+'px'" style="min-height: 100%;">
        <el-menu :default-openeds="['1', '3']" class="el-menu-vertical-demo" background-color=" rgba(177, 201, 65, 1) "
          text-color="#fff" active-text-color=" rgba(177, 201, 65, 1) " :collapse="iscollapse"
          :collapse-transition="false">
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-message"></i>
              <span>导航一</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title">选项4</template>
              <el-menu-item index="1-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>导航二</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="2-1">选项1</el-menu-item>
              <el-menu-item index="2-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="2-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="2-4">
              <template slot="title">选项4</template>
              <el-menu-item index="2-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-setting"></i>
              <span>导航三</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="3-1">选项1</el-menu-item>
              <el-menu-item index="3-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="3-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="3-4">
              <template slot="title">选项4</template>
              <el-menu-item index="3-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <el-header style="
        display: flex;
        justify-content: space-between;
        font-size: 12px; 
        border-bottom:rgba(177, 201, 65, 1) 1px solid;
        line-height: 60px;">
          <div style="text-align: left; width: 100px;">
            <i class="el-icon-s-fold" @click="kaiguan"></i>
          </div>
          <div>
            <!-- <span>王小虎</span> -->
            <el-dropdown>
              <i :class="kaiguantubiao" style="margin-right: 15px;" >王小虎</i>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>查看</el-dropdown-item>
                <el-dropdown-item>新增</el-dropdown-item>
                <el-dropdown-item>删除</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>

          </div>
        </el-header>

        <el-main>
          <div>
            <el-row :gutter="20">
              <el-col :span="6">            
                <el-input  placeholder="请输入内容" v-model="username">
                <i class="el-icon-search el-input__icon" slot="suffix">
                </i>
              </el-input>
            </el-col>
              <el-col :span="6">            
                <el-input  placeholder="请输入内容" v-model="test">
                <i class="el-icon-edit el-input__icon" slot="suffix">
                </i>
              </el-input>
            </el-col>
              <el-col :span="6">            
                <el-input  placeholder="请输入内容" v-model="test">
                <i class="el-icon-edit el-input__icon" slot="suffix">
                </i>
              </el-input>
            </el-col>
            <el-button  type="primary" @click="load">搜索</el-button>
            </el-row>
            <el-row :gutter="20">
              <el-button type="primary">新增</el-button><el-button type="danger">批量删除</el-button>
              <el-button type="primary">导入</el-button>
            </el-row>
          </div>
          <el-table :data="tableData"  stripe>
            <el-table-column prop="id" label="ID" width="140">
            </el-table-column>
            <el-table-column prop="username" label="用户名" width="140">
            </el-table-column>
            <el-table-column prop="nickname" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="address" label="地址">
            </el-table-column>
            <el-table-column prop="phone" label="电话">
            </el-table-column>
            <el-table-column prop="email" label="邮箱">
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <div class="block">
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="pageNum"
              :page-sizes="[2, 5, 10, 20]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total">
            </el-pagination>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  // @ is an alias to /src
  import HelloWorld from '@/components/HelloWorld.vue'

  export default {
    name: 'HomeView',
    components: {
      HelloWorld
    },
    created(){
      this.load()
    },
    data() {
      return {
        tableData: [],
        iscollapse: false,
        asideWidth: 200,
        test:'',
        kaiguantubiao:'el-icon-setting',
        currentPage4: 4,
        total:0,
        pageSize:2,
        pageNum:1,
        username:''
      }
    },
    methods: {
      load(){
        fetch(`http://localhost:9090/user/page?pageNum=${this.pageNum}&pageSize=${this.pageSize}&username=${this.username}`)
      .then(res=>res.json())
      .then(res => {
        console.log(res)
      this.tableData = res.data
      this.total = res.total
      
    })
      },

      kaiguan() {
        this.iscollapse = !this.iscollapse
        if (this.asideWidth == 200) {
          this.asideWidth = 64
          this.kaiguantubiao='el-icon-s-unfold'
        } else {
          this.asideWidth = 200
          this.kaiguantubiao='el-icon-setting'
        }
      },
      handleSizeChange(pageSize) {
        console.log(`每页 ${pageSize} 条`);
        this.pageSize = pageSize
        this.load()
      },
      handleCurrentChange(pageNum) {
        console.log(`当前页: ${pageNum}`);
        this.pageNum = pageNum
        this.load()
      }
    }
  }
</script>
<style>
  div.home {
    height: 100%;
  }

  .el-aside {
    color: #333;
    background-color: rgba(177, 201, 65, 1);
    /* line-height: 100%; */
    min-height: 100%;
  }
</style>