<template>
  <div class="app-container">
    <el-card class="box-table">
      <div slot="header" class="clearfix">
        <span>用户管理</span>
      </div>
      <el-form :inline="true" class="demo-form-inline" :model="searchForm">
        <el-form-item label="账号">
          <el-input placeholder="请输入账号" v-model="searchForm.username" size="mini" clearable></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input placeholder="请输入密码" v-model="searchForm.password" size="mini" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSearch" size="mini">查询</el-button>
          <el-button @click="handleAdd" size="mini">新增</el-button>
        </el-form-item>
      </el-form>
      <el-table
        v-loading="listLoading"
        :data="list"
        element-loading-text="Loading"
        border
        fit
        highlight-current-row
        size="small"
      >
    	<el-table-column align="center" label="ID" prop="id"></el-table-column>
    	<el-table-column align="center" label="账号" prop="username"></el-table-column>
        <el-table-column align="center" label="密码" prop="password"></el-table-column>
        <el-table-column fixed="right" label="操作" width="100">
          <template slot-scope="scope">
            <el-button @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
            <el-popconfirm title="确定删除吗？" @confirm="deleteCofirm(scope.row)">
              <el-button type="text" slot="reference" size="small">删除</el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        class="mt-20"
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[10, 20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </el-card>
    <!-- 添加/修改的弹框 -->
    <el-dialog :title="dialogTitle" :visible.sync="dialogFormVisible" :before-close="handleClose" :close-on-click-modal="false" :close-on-press-escape="false">
      <el-form :model="dialogForm" :rules="rules" ref="ruleForm" label-position="left" label-width="80px">
     	<el-form-item label="账号" prop="username">
          <el-input placeholder="请输入账号" v-model="dialogForm.username" autocomplete="off" size="mini"></el-input>
        </el-form-item>
     	<el-form-item label="密码" prop="password">
          <el-input type="password" placeholder="请输入密码" v-model="dialogForm.password" autocomplete="off" size="mini"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogConfirm" size="mini">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  findUsersPageAPI,
  addUsersAPI,
  modifyUsersAPI,
  removeUsersAPI,
} from "@/api/users";

export default {
  data() {
    return {
      list: [],
      listLoading: false,
      pageSize: 10,
      pageNum: 1,
      total: 0,
      searchForm: {},
      dialogTitle: "添加",
      dialogFormVisible: false,
      dialogForm: {},
      rules: {
        username: [ { required: true, message: '请输入账号', trigger: 'blur' }],
        password: [ { required: true, message: '请输入密码', trigger: 'blur' }],
      },
    };
  },
  async created() {
    this.fetchData();
  },
  methods: {
    handleSizeChange(val) {//每页显示条数改变
      this.pageNum = 1;
      this.pageSize = val;
      this.fetchData();
    },
    handleCurrentChange(val) {//当前页码改变
      this.pageNum = val;
      this.fetchData();
    },
    handleSearch() {//点击搜索按钮
      this.pageNum = 1;
      this.fetchData();
    },
    async fetchData() {//查询数据
      this.listLoading = true;
      let response = await findUsersPageAPI(
        this.pageNum,
        this.pageSize,
        this.searchForm
      );
      this.list = response.data.records;
      this.total = response.data.total;
      this.listLoading = false;
    },
    handleAdd() {//点击新增按钮-显示弹框
      this.dialogTitle = "新增";
      this.dialogFormVisible = true;
      this.dialogForm = {};
    },
    handleEdit(row) {//点击修改按钮-显示弹框
      this.dialogTitle = "修改";
      this.dialogFormVisible = true;
      this.dialogForm = { ...row };
    },
    dialogConfirm() {//点击添加or修改弹框确定按钮
      this.$refs['ruleForm'].validate(async(valid) => {
        if (valid) {//表单校验通过
          let res = null;
          if (this.dialogTitle == "新增") {//新增
            res = await addUsersAPI(this.dialogForm);
          } else {//修改
            res = await modifyUsersAPI(this.dialogForm);
          }
          this.$message.success(res.message);
          this.dialogFormVisible = false;
          await this.fetchData();
        }
      });

    },
    //弹框关闭：重置表单
    handleClose(){
      this.$refs['ruleForm'].resetFields();
      this.dialogFormVisible = false;
    },
    async deleteCofirm(row) {//点击确定删除按钮
      const res = await removeUsersAPI(row.id);
      this.$message.success(res.message);
      await this.fetchData();
    },
  },
};
</script>

<style lang="scss" scoped>

</style>
