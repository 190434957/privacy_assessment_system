/app_id GET 获得appid 
````
{
  appid: String
}
````
/user GET 获得user
````
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