# JavaCQ SDK Tool

## 前言

> 本插件将酷Q扩展可以间接使用Java进行开发    
> 本文档适用于有一定Java基础的开发者阅读     
> 以下提供详细步骤，推荐使用maven进行开发    
> 视频教学：[[小白]如何使用Java开发酷Q插件](https://www.bilibili.com/video/av21077001/)     
> 酷Q帖子：https://cqp.cc/t/37318   

## 开发环境
1) 电脑上已安装32位 [JDK(JRE)](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) **1.6以上版本**    
    注：酷Q未提供64位版本，所以JRE必须为32位，否则JRE无法正常加载
2) 下载 [酷Q](https://cqp.cc/t/23253) 和 [JavaCQ SDK Tool](https://cqp.cc/t/37318) 插件 ，安装并启用工具插件   
    注：启用时发生依赖DLL链接错误，多半因为JVM相关 [VC库](https://www.microsoft.com/en-us/download/details.aspx?id=5555) 未安装 
3) Java开发工具：MyEclipse、Eclipse、Intelij IDEA等 主流开发工具均可开发

## JCQ Tool 目录结构 

根目录 (例：酷Q/app/com.sobte.cqp.jcq/)

| 目录        |  描述                               |
| --------   | :----------------------------------|
| bin/       | 存放重要的JCQ插件管理工具和酷QAPI工具  |
| app/       | 插件放置处，即放置您所编写并打包好的jar |
| conf/      | 核心配置目录                         |
| lib/       | 全局lib目录，需手动创建               |

小提示
> jar插件同名文件夹下创建lib，可以使用独立lib    
> 优先级：独立lib > 全局lib > JCQ自带lib      
> 每个插件都是独立的，互不影响，各插件加载类时优先级越高的最先加载  
> 全局lib 和 独立lib 根据需求使用 注意类加载优先级

配置文件

| 文件        |  描述                               |
| --------   | :----------------------------------:|
| setting.ini| JCQ配置文件                          |

## JCQ Tool 配置文件
 
JavaVM 相关

| key        | value                                 |
| --------   | :------------------------------------|
| JrePath    | 自定义JRE路径配置 (默认从注册表读JRE目录) |
| MinSize    | JVM堆内存最小设置                       |
| MaxSize    | JVM堆内存最大设置                       |
| MinPermSize| JVM非堆内存最小设置                     |
| MaxPermSize| JVM非堆内存最大设置                     |

JVM内存限制

> 32位虽然可控内存空间有4GB,但是具体的操作系统会给一个限制，这个限制一般是2GB-3GB
> 一般来说Windows系统下为1.5G-2G，Linux系统下为2G-3G，而64bit以上的处理器就不会有限制了

JavaCQ 相关

| key        | value                                 |
| --------   | :------------------------------------|
| Version    | JCQ Tool版本(清空此值，则重新生成核心文件)|

## JSON 文件介绍

正常插件是由以下两部分组成的
- json:此文件用于描述插件信息  
- jar :此文件是插件所有class打包而成  

JSON文件是用于存储插件信息的文本文件
下面介绍下Json基本信息，详情查看Demo中json注释

整体部分

```json  
{  
    "ret"        : 1,   // 返回码，固定为1
    "apiver"     : 9,   // Api版本，本SDK为9
    "name"       : "name",  // 应用的名称
    "version"    : "1.0.0",    // 应用文本版本号 例：1.0.0
    "version_id" : 1,        // 应用顺序版本（每次发布时至少+1）
    "author"     : "author",    // 应用作者
    //"class"      : "com.example.Demo", // 应用加载主类，注意必须填写全类名，默认使用appid加载，如需使用则删除前面注释
    "description": "", // 应用描述
    "event"      : [],  //  事件列表，同一事件类型可重复定义（发布前请删除无用事件）
    "menu"       : [],  // 设置菜单 (暂无实际功能，后期加入)
    "status"     : [],  // 悬浮窗状态 (暂无实际功能，后期加入)
    "auth"       : []   // 应用权限 (暂无实际功能，后期加入)
}  
```  

事件部分

```json  
{
	// 这是事件部分json，其他部分与上面一致
	"event":
	[  
		// 这里列举私聊事件为例，其他参考Demo中的json
		{  
			"id"      : 1,                      // 事件ID，保持唯一即可
			"type"    : 21,                     // 事件类型，事件类型，用于区分各函数的功能  
			"name"    : "私聊消息处理",          // 事件名称
			"function": "privateMsg",           // 事件对应主类中的函数  
			"priority": 30000                   // 事件优先级 (参见 cqp.im/deveventpriority)  
		}  
	]  
}
```  

最后必须将文件名改为 **[[appid]](https://d.cqp.me/Pro/%E5%BC%80%E5%8F%91/%E5%9F%BA%E7%A1%80%E4%BF%A1%E6%81%AF#appid).json** 并和你的jar插件放置到app目录下  

## 使用Demo开发

> 如果有一定Java基础，则可以转到下文的快速开发

1) 下载 [Demo](https://cqp.cc/t/37318) ，导入到开发工具中
2) 参考上方json介绍，根据自己需求修改json文件，文件名需和 [appid](https://d.cqp.me/Pro/%E5%BC%80%E5%8F%91/%E5%9F%BA%E7%A1%80%E4%BF%A1%E6%81%AF#appid) 相同
3) 查看项目中`lib`目录，导入其中所需jar文件
4) 打开包中的应用主类，这里建议包到主类的全类名尽量和 [appid](https://d.cqp.me/Pro/%E5%BC%80%E5%8F%91/%E5%9F%BA%E7%A1%80%E4%BF%A1%E6%81%AF#appid) 保持一致  
   注：酷Q是**事件驱动**的模式，json中定义的事件，在主类中实现事件
5) 根据自己的需求 编写，修改 所需代码，相关CQ类说明在文档下方

```java
    // 群消息事件，详情参考Demo中的主类
    public int groupMsg(int subType, int msgId, long fromGroup, long fromQQ, String fromAnonymous, String msg, int font) {
        // 以下是群消息要执行的代码
        CQ.sendGroupMsg(fromGroup, msg + "Java插件");
        return 0;
    }
```

6) 然后使用开发工具进行编译打包成jar
7) 将jar依赖lib导入放置到，全局lib或单独lib中，jcq-coolq和commons-codec，工具自带了，可以不用导入      
   注：导入的依赖jar，尽量保证版本一致，避免不必要的错误
8) 将打包好的jar和json，一起复制到 `app` 目录下
9) 启动或重启酷Q即可运行插件    
   注：因为JRE不能重新加载，重新加载插件需重启酷Q，重启插件无效
10) 触发事件开始体验自己的插件吧，喵呜~

## JCQ-CoolQ 常用类说明

CoolQ 类     
酷Q操作的核心类

| 方法         | 说明                                |
| --------    | :------------------------------------|
| deleteMsg   | 撤回消息,需Pro版 |
| getAppDirectory   | 获取应用目录,如果目录不存在则自动创建,目录末尾带 "\" |
| getCookies   | 获取Cookie,慎用,此接口需要严格授权 |
| getCsrfToken   | 获取CsrfToken,即QQ网页用到的bkn/g_tk等 慎用,此接口需要严格授权 |
| getGroupList   | 获取群列表 |
| getGroupMemberInfoV2   | 获取群成员信息 |
| getGroupMemberList   | 获取群成员列表 |
| getLoginNick   | 获取登录昵称 |
| getLoginQQ   | 获取登录QQ |
| getRecord   | 接收消息中的语音(record) |
| getStrangerInfo   | 获取陌生人信息 |
| addLog   | 不推荐使用本方法,请使用log开头的方法 |
| logDebug   | 添加日志，级别：调试，颜色：灰色 |
| logError   | 添加日志，级别：错误，颜色：红色 |
| logFatal   | 添加日志，级别：致命错误，颜色：深红 |
| logInfo    | 添加日志，级别：信息，颜色：黑色 |
| logInfoRecv| 添加日志，级别：信息(接收)，颜色：蓝色 |
| logInfoSend| 添加日志，级别：信息(发送)，颜色：绿色|
| logInfoSuccess| 添加日志，级别：信息(成功)，颜色：紫色|
| logWarning| 添加日志，级别：警告，颜色：橙色|
| sendDiscussMsg| 发送讨论组消息|
| sendGroupMsg| 发送群消息|
| sendLikeV2| 发送手机赞|
| sendPrivateMsg| 发送私聊消息|
| setDiscussLeave| 退出讨论组|
| setFatal| 置错误提示|
| setFriendAddRequest| 处理好友添加请求|
| setGroupAddRequestV2| 处理群添加请求|
| setGroupAdmin| 设置群管理员|
| setGroupAnonymous| 群匿名设置|
| setGroupAnonymousBan| 禁言匿名群员|
| setGroupBan| 禁言群员|
| setGroupCard| 设置群成员名片|
| setGroupKick| 移除群员|
| setGroupLeave| 退出QQ群,慎用,此接口需要严格授权|
| setGroupSpecialTitle| 设置群成员专属头衔,需群主权限|
| setGroupWholeBan| 全群禁言|

CQCode 类    
[CQ码](https://d.cqp.me/Pro/CQ%E7%A0%81) ，用于辅助开发加快开发效率，以下方法均返回 [CQ码](https://d.cqp.me/Pro/CQ%E7%A0%81) 文本

| 方法         | 说明                                |
| --------    | :------------------------------------|
| decode      | 特殊字符，反转义 |
| encode      | 特殊字符，转义，避免冲突 |
| anonymous   | 匿名发消息(anonymous) - 仅支持群 |
| at          | 提醒某人，@某人(at) |
| contact     | 发送名片分享(contact) |
| emoji       | emoji表情(emoji) |
| face       | 表情QQ表情(face) |
| image       | 发送图片(image),需Pro版 |
| location    | 发送位置分享(location) |
| music       | 发送音乐(music) |
| record      | 发送语音(record) |
| shake       | 窗口抖动(shake) - 仅支持好友 |
| share       | 发送链接分享(share) |

## 支持赞助作者