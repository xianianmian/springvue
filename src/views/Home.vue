<template>
  <div class="">
    <div class="top" style="margin: 20px 0;">
      <el-row>
        <el-col :span="6">
          <el-card>
            <div style="color:#409EFF">用户总数</div>
            <div style="padding: 10px 0;text-align: center;font-size: 20px;">100</div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card>
            <div style="color:#67c23A">销售总量</div>
            <div style="padding: 10px 0;text-align: center;font-size: 20px;">1900</div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card>
            <div style="color:#e6a23c">用户总数</div>
            <div style="padding: 10px 0;text-align: center;font-size: 20px;">100</div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card>
            <div style="color:#F56C6C">用户总数</div>
            <div style="padding: 10px 0;text-align: center;font-size: 20px;">100</div>
          </el-card>
        </el-col>
        </el-col>
      </el-row>
    </div>

    <el-row>
      <el-col :span="12">
        <el-card>
          <div id="main" style="width: 500px; height: 400px"></div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card>
          <div id="pie" style="width: 500px; height: 400px"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import * as echarts from 'echarts'
  export default {
    data() {
      return {

      }
    },
    methods: {
      lodeChartLine() {
        var option = {
          title: {
            text: '各季度会员数量统计',
            subtext: '趋势图',
            left: 'center'
          },
          xAxis: {
            type: 'category',
            // data: []
            data: ["第一季度", "第二季度", "第三季度", "第四季度"]
          },
          yAxis: {
            type: 'value'
          },
          series: [
            {
              data: [],
              type: 'line'
            },
            {
              data: [],
              type: 'bar'
            }
          ]
        };

        var chartDom = document.getElementById('main');
        var myChart = echarts.init(chartDom);

        this.request.get("/echarts/members").then(res => {
          // 填空
          console.log(res)
          option.series[0].data = res.data
          option.series[1].data = res.data
          // 数据准备完毕之后再set
          myChart.setOption(option);

        })
        this.request.get("/echarts/lizi").then(res => {
          // 填空
          console.log(res,"sdfsdf")

        })
      },
      lodeChartPie() {
        var pieOption = {
          title: {
            text: '各季度会员数量统计',
            subtext: '比例图',
            left: 'center'
          },
          tooltip: {
            trigger: 'item'
          },
          legend: {
            orient: 'vertical',
            left: 'left'
          },
          series: [
            {
              type: 'pie',
              radius: '60%',
              label: {            //饼图图形上的文本标签
                normal: {
                  show: true,
                  position: 'inner', //标签的位置
                  textStyle: {
                    fontWeight: 300,
                    fontSize: 14,    //文字的字体大小
                    color: "#fff"
                  },
                  formatter: '{d}%'
                }
              },
              data: [],  // 填空
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        };
        var pieDom = document.getElementById('pie');
        var pieChart = echarts.init(pieDom);
        this.request.get("/echarts/members").then(res => {
          pieOption.series[0].data = [
            { name: "第一季度", value: res.data[0] },
            { name: "第二季度", value: res.data[1] },
            { name: "第三季度", value: res.data[2] },
            { name: "第四季度", value: res.data[3] },
          ]
          pieChart.setOption(pieOption)
        })
      }
    },
    mounted() {
      this.lodeChartLine()
      this.lodeChartPie()
    }
  }
</script>

<style scoped>
</style>