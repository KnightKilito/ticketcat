

# 一、引言
项目地址：
|项目名|项目内容|项目地址|开发人员|
|--|--|--|--|
|ticketcat|SpringBoot后台服务|[戳我](https://github.com/KnightKilito/ticketcat)|[@闪光桐人](https://github.com/KnightKilito)<br/>[@汉克](https://github.com/hankhorse/hankhorse)|
|ticketcat-web-user|Web前端服务-用户端|[戳我](https://github.com/KnightKilito/ticketcat-web-user)|[@东东](https://github.com/zxd16)<br/>[@闪光桐人](https://github.com/KnightKilito)<br/>[@汉克](https://github.com/hankhorse/hankhorse)|
|ticketcat-web-manager|Web前端服务-管理员端|[戳我](https://github.com/KnightKilito/ticketcat-web-manager)|[@东东](https://github.com/zxd16)<br/>[@闪光桐人](https://github.com/KnightKilito)<br/>[@汉克](https://github.com/hankhorse/hankhorse)|
|ticketcat-wechat-miniprogram|微信小程序前端服务（记得安装vant for wx）|[戳我](https://github.com/KnightKilito/ticketcat-wechat-miniprogram)|[@东东](https://github.com/zxd16)|

**前端的项目请求地址记得改哦**，**前端的项目请求地址记得改哦**，**前端的项目请求地址记得改哦**，当初做项目的时候负责web端的同学偷懒没搞页面路由，而且用的还是传统三剑客，辛苦各位逐一修改了

涉及到AppID、AppSecret等配置项的还得辛苦诸位自己配置一下哈，例如支付宝支付或者微信小程序的一些东西

如果因为网络原因无法进入GitHub，可以去我的Gitee主页找同名项目，内容一致。
https://gitee.com/KnightKilito
[点击前往](https://gitee.com/KnightKilito)
**喜欢的话别忘了star哦 (๑•̀ㅂ•́)و✧**

# 二、项目介绍

## ticketcat
- 开发框架：基于SpringBoot的前后端分离框架 
- 开发技术栈：Java+MySQL+Tomcat+IDEA+Git +MybatisPlus
- 项目简介：影城购票系统
- 开发模块：用户登录注册、微信用户扫码登录、密码加密、token签发、图片上传、支付宝沙盒支付等
- 技术要点：使用MybatisPlus完成基础类的设计和增删改查操作；使用MD5+UUID（在数据库中以salt加密盐值形式存储）的方式对密码进行加解密；与微信后台配合完成用户扫码登录的验证；利用JWT完成登录用户的token的签发并进行拦截器权限校验；利用Ajax跨域请求完成前后端数据传输；使用Ajax异步编程的方式实现局部更新的功能；用js完成base64编解码实现海报上传至七牛云服务器功能；使用支付宝沙盒支付测试完成购票并存储购票信息至数据库；用SQL语句完成复杂数据之间的交互；统一规范了返回参数体；使用Git完成分布式开发；用在线UML工具完成文档编写；将项目部署在云服务器上

## ticketcat-wechat-miniprogram
- 开发框架：Vant Weapp+WXML+WXSS+JS

## ticketcat-web-user
- 开发框架：HTML+CSS+JS

## ticketcat-web-manager
- 开发框架：HTML+CSS+JS

# 三、项目截图
项目截图远不止这些，意味着项目不止这些页面，但是懒得一张张传到这上面来，所以你们自行操作

## wechat-miniprogram
微信小程序前端由我们伟大的[@东桑](https://github.com/zxd16)负责，效果非常完美，绝赞无敌的前端工程师

![小程序选座](https://blog-source-cdn.moechun.fun/md/%E6%B7%98%E7%A5%A8%E7%8C%AB%E5%BD%B1%E5%9F%8E%E7%B3%BB%E7%BB%9F-Java%E7%89%88/%E9%A1%B9%E7%9B%AE%E6%88%AA%E5%9B%BE/wechat-miniprogram/%E9%80%89%E5%BA%A7%E9%A1%B5%E9%9D%A2.jpg)
![个人中心](https://blog-source-cdn.moechun.fun/md/%E6%B7%98%E7%A5%A8%E7%8C%AB%E5%BD%B1%E5%9F%8E%E7%B3%BB%E7%BB%9F-Java%E7%89%88/%E9%A1%B9%E7%9B%AE%E6%88%AA%E5%9B%BE/wechat-miniprogram/%E4%B8%AA%E4%BA%BA%E4%B8%BB%E9%A1%B5.jpg)
![影片详情](https://blog-source-cdn.moechun.fun/md/%E6%B7%98%E7%A5%A8%E7%8C%AB%E5%BD%B1%E5%9F%8E%E7%B3%BB%E7%BB%9F-Java%E7%89%88/%E9%A1%B9%E7%9B%AE%E6%88%AA%E5%9B%BE/wechat-miniprogram/%E7%94%B5%E5%BD%B1%E8%AF%A6%E6%83%85%E9%A1%B5.jpg)
![用户登录](https://blog-source-cdn.moechun.fun/md/%E6%B7%98%E7%A5%A8%E7%8C%AB%E5%BD%B1%E5%9F%8E%E7%B3%BB%E7%BB%9F-Java%E7%89%88/%E9%A1%B9%E7%9B%AE%E6%88%AA%E5%9B%BE/wechat-miniprogram/%E7%99%BB%E5%BD%95.jpg)
![评分页面](https://blog-source-cdn.moechun.fun/md/%E6%B7%98%E7%A5%A8%E7%8C%AB%E5%BD%B1%E5%9F%8E%E7%B3%BB%E7%BB%9F-Java%E7%89%88/%E9%A1%B9%E7%9B%AE%E6%88%AA%E5%9B%BE/wechat-miniprogram/%E8%AF%84%E5%88%86%E9%A1%B5%E9%9D%A2.jpg)

## web-user
![主页](https://blog-source-cdn.moechun.fun/md/%E6%B7%98%E7%A5%A8%E7%8C%AB%E5%BD%B1%E5%9F%8E%E7%B3%BB%E7%BB%9F-Java%E7%89%88/%E9%A1%B9%E7%9B%AE%E6%88%AA%E5%9B%BE/web-user/%E4%B8%BB%E9%A1%B5.png)
![选座](https://blog-source-cdn.moechun.fun/md/%E6%B7%98%E7%A5%A8%E7%8C%AB%E5%BD%B1%E5%9F%8E%E7%B3%BB%E7%BB%9F-Java%E7%89%88/%E9%A1%B9%E7%9B%AE%E6%88%AA%E5%9B%BE/web-user/%E9%80%89%E5%BA%A7.png)
![注册](https://blog-source-cdn.moechun.fun/md/%E6%B7%98%E7%A5%A8%E7%8C%AB%E5%BD%B1%E5%9F%8E%E7%B3%BB%E7%BB%9F-Java%E7%89%88/%E9%A1%B9%E7%9B%AE%E6%88%AA%E5%9B%BE/web-user/%E6%B3%A8%E5%86%8C.png)
![登录](https://blog-source-cdn.moechun.fun/md/%E6%B7%98%E7%A5%A8%E7%8C%AB%E5%BD%B1%E5%9F%8E%E7%B3%BB%E7%BB%9F-Java%E7%89%88/%E9%A1%B9%E7%9B%AE%E6%88%AA%E5%9B%BE/web-user/%E7%99%BB%E5%BD%95.png)
![选择影城](https://blog-source-cdn.moechun.fun/md/%E6%B7%98%E7%A5%A8%E7%8C%AB%E5%BD%B1%E5%9F%8E%E7%B3%BB%E7%BB%9F-Java%E7%89%88/%E9%A1%B9%E7%9B%AE%E6%88%AA%E5%9B%BE/web-user/%E6%A0%B9%E6%8D%AE%E7%94%B5%E5%BD%B1%E9%80%89%E5%BD%B1%E9%99%A2.png)
![头像上传](https://blog-source-cdn.moechun.fun/md/%E6%B7%98%E7%A5%A8%E7%8C%AB%E5%BD%B1%E5%9F%8E%E7%B3%BB%E7%BB%9F-Java%E7%89%88/%E9%A1%B9%E7%9B%AE%E6%88%AA%E5%9B%BE/web-user/%E4%B8%8A%E4%BC%A0%E5%9B%BE%E7%89%87.png)

## web-manager
![用户列表](https://blog-source-cdn.moechun.fun/md/%E6%B7%98%E7%A5%A8%E7%8C%AB%E5%BD%B1%E5%9F%8E%E7%B3%BB%E7%BB%9F-Java%E7%89%88/%E9%A1%B9%E7%9B%AE%E6%88%AA%E5%9B%BE/web-manager/%E7%94%A8%E6%88%B7%E5%88%97%E8%A1%A8.png)
![订单列表](https://blog-source-cdn.moechun.fun/md/%E6%B7%98%E7%A5%A8%E7%8C%AB%E5%BD%B1%E5%9F%8E%E7%B3%BB%E7%BB%9F-Java%E7%89%88/%E9%A1%B9%E7%9B%AE%E6%88%AA%E5%9B%BE/web-manager/%E7%94%A8%E6%88%B7%E8%AE%A2%E5%8D%95.png)
![新增影片](https://blog-source-cdn.moechun.fun/md/%E6%B7%98%E7%A5%A8%E7%8C%AB%E5%BD%B1%E5%9F%8E%E7%B3%BB%E7%BB%9F-Java%E7%89%88/%E9%A1%B9%E7%9B%AE%E6%88%AA%E5%9B%BE/web-manager/%E6%B7%BB%E5%8A%A0%E5%BD%B1%E7%89%87.png)
![影片列表](https://blog-source-cdn.moechun.fun/md/%E6%B7%98%E7%A5%A8%E7%8C%AB%E5%BD%B1%E5%9F%8E%E7%B3%BB%E7%BB%9F-Java%E7%89%88/%E9%A1%B9%E7%9B%AE%E6%88%AA%E5%9B%BE/web-manager/%E5%85%A8%E9%83%A8%E5%BD%B1%E7%89%87.png)

# 四、License
MIT
