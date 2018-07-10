####已有分析：  
````
  //1645171780 张亚勤  
  //1197161814 李开复
````

###API: 
  
/app_id GET 获得appid 
````
/**
* 接口功能已删除
*/
{
  appid: String
}
````
/user GET 获得user
````
/**
* 接口功能已删除
*/
{
  user: uid用户
  is_new: 是否新用户
  success: 请求成功标志
}
````

/analysis GET 发起申请
- req
````
{
  uid: uid
}
````
- res
````
/**
* 对UID为1645171780的数据发起分析
*/
{
  success: Boolean 成功发起申请
  id: String 临时查询id
  uid: uid
}
````
/getResult GET 轮询进度
````
req{
  id: String 得到的临时查询id
  uid: uid
}
res
{
  err: Boolean 是否错误
  success: Boolean 是否发起成功
  done: Boolean 是否完成
  process: Int 1~100处理进度
  ......完成后将有所有作图参数
}
````
/getInfo GET 获得用户信息
````
{
  user_ID: 流水线主键
  uid: 唯一标识符
  Uname: 名字
  ……
}
````

使用示例:  
1. 先/analysis 得到id  
2. 再/getResult?id=xxx 得到进度和结果

参数说明:  
  - first_data: 
    - time_list: 发布时间统计图点
    - time_nums: 发布时间统计图点
    - image: 词云base64编码 直接<img src>
    - words: 词频图参数
    - values: 词频图参数
  - second_data: lda参数，有生成和引入的代码， 在房生成的一个LDA?xxx.HTML里面 复制上去就行了