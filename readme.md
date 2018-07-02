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
````
/**
* 对UID为1645171780的数据发起分析
*/
````
/getResult GET 轮询进度
````
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