// pages/consult/consult.js
import fetch from '../../utils/fetch.js';
import {evalue} from '../../utils/filter.js'

var page = 0;
var num = 0;
var iTime;
var diagList = [];
function GetList(that, source) {
  fetch({
    url: "/health/diagnosis/querybypage",
    baseUrl: "https://health.lianlianchains.com",
    // baseUrl: "http://192.168.50.157:9999",
    data: {
      'page': 5 * num,
      'openid': 'asdfgh2',
      hasMore: false
    },
    method: "POST",
    header: { 'content-type': 'application/x-www-form-urlencoded' }
  }).then(result => {
    console.log(result);
    if (source == "refreshs"){
      diagList.length = 0;
      for (var i = 0; i < result.diagnosis.length; i++) {
        diagList.push(result.diagnosis[i]);
      }
      that.setData({
        diagList: diagList,
        totalpage: result.totalpage
      })
    }else{
      for (var i = 0; i < result.diagnosis.length; i++) {
        diagList.push(result.diagnosis[i]);
      }
      console.log(diagList);
      that.setData({
        diagList: diagList,
        totalpage: result.totalpage
      })
    }
    
  }).catch(err => {
    console.log("出错了")
    console.log(err)
  });
}
Page({

  /**
   * 页面的初始数据
   */
  data: {
    docArray: [

    ],
    hidden: true,
    diagList: [],
    scrollTop: 0,
    scrollHeight: 0,
    totalpage:0,
    loadingMsg:"正在加载..."
  },
  bindDownLoad: function () {
    //   该方法绑定了页面滑动到底部的事件
    var that = this;
    clearTimeout(iTime);
    if (num < that.data.totalpage-1){
      iTime = setTimeout(function () {
        //需要执行的事件
        num++;
        GetList(that);
      }, 100);
      
    }else{
      that.setData({
        loadingMsg: "没有更多了"
      });
    }
    
  },
  scroll (event) {
    // console.log(222);
    //   该方法绑定了页面滚动时的事件，我这里记录了当前的position.y的值,为了请求数据之后把页面定位到这里来。
    this.setData({
      scrollTop: event.detail.scrollTop
    });
  },
  refreshs (event) {
    //   该方法绑定了页面滑动到顶部的事件，然后做上拉刷新
    // var that = this;
    // num = 0;
    // clearTimeout(iTime);
    // iTime = setTimeout(function(){
    //   //需要执行的事件
    //   GetList(that, "refreshs");
    //   that.setData({
    //     hasMore: false
    //   });
    // }, 100);
    
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    GetList(this);
    wx.getSystemInfo({
      success: function (res) {
        console.info(res.windowHeight);
        that.setData({
          scrollHeight: res.windowHeight
        });
      }
    });
    // fetch({
    //   url: "/health/diagnosis/save",
    //   // baseUrl: "http://192.168.50.157:9999",
    //   baseUrl: "https://health.lianlianchains.com",
    //   data: {
    //     'diagnosisid':"xrf670",
    //     'openid':'asdfgh2',
    //     'datetime':"2017",
    //     'symptom':"很好",
    //     'amt':"money",
    //     'hospital':"sanhuan",
    //     'doctor':'doctor',
    //     'evaluate':0,
    //     'label':'001'
    //   },
    //   method: "POST",
    //   header: { 'content-type': 'application/x-www-form-urlencoded' }
    //   // header: { 'content-type': 'application/json' }
    // }).then(result => {
    //   console.log(result);
    // }).catch(err => {
    //   console.log("出错了")
    //   console.log(err)
    // });
    // fetch({
    //   url: "/health/diagnosis/query",
    //   baseUrl: "http://192.168.50.157:9999",
    //   data: {
    //     'diagnosisid': "xrf109",
    //     'openid':'asdfgh2',
    //   },
    //   method: "POST",
    //   header: { 'content-type': 'application/x-www-form-urlencoded' }
    // }).then(result => {
    //   console.log(result);
    // }).catch(err => {
    //   console.log("出错了")
    //   console.log(err)
    // });
    //querybypage
    // fetch({
    //   url: "/health/diagnosis/querybypage",
    //   baseUrl: "http://192.168.50.157:8888",
    //   data: {
    //     'page': 0,
    //     'openid':'asdfgh2',
    //   },
    //   method: "POST",
    //   header: { 'content-type': 'application/x-www-form-urlencoded' }
    // }).then(result => {
    //   console.log(result);
    // }).catch(err => {
    //   console.log("出错了")
    //   console.log(err)
    // });
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    console.log(1111);
    wx.stopPullDownRefresh();
  },
  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    this.onShow();
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {
    console.log(1111111111111111111111111);
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  }
})