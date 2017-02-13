标题：附件服务器交互项目（包括Java工具包和jQuery插件包）
版本：1.2.0-beta
作者：xjxu@so-well.cn
时间：2015/12/04

更新说明：
	1. 新增图片(ImageDTO)、文档(WordDTO)、表格(ExcelDTO)三个附件(FileDTO)子类。分别拥有自己额外属性；
	2. 新增图片类附件远程操作接口工具类ImageTracker。提供缩放(scale)、裁剪(cut)、旋转(rotate)3个方法；
	3. 优化服务器消息过滤器，使服务器信息（包括异常）友好显示；
	4. 优化Tracker类使用方法，开放server、project等附件工程参数配置。
	
2016/03/01 --- 1.2.3-beta
	修复了FileDTO对象中无法获取group属性的bug。

2016/03/28 --- 1.3.0-beta
	优化了FileTracker类与附件服务器交互的方式，将JSESSIONID凭证修改为CACHE_KEY，后者为自定义缓存机制。
	
2016/04/22 --- 1.3.1-beta
	FileTracker类增加方法：getByRequest（根据当前请求从当前服务器缓存中取附件，与业务id无关）
	
2016/05/23 --- 1.3.2-beta
          优化FileTracker类通过请求上传的upload方法，增加其重载方法，用于解析jsp页面非文件字符串表单项，处理中文乱码问题
    
2016/07/06 --- 2.0.0-beta
          对接新版本文件服务器file_server(fs)
    
2016/08/11 --- 2.1.0-beta
    1. 优化synchronize方法和upload方法返回结果的结构，新增SyncResult和UploadResult类，详情查阅javadoc
    2. 优化了tracker类的一些内部算法，提高请求效率
    
2016/09/10 --- 2.2.2-beta
    1. 精简了一些方法，删除了“二次上传”的上传方法
    2. 修改了标签算法，简化部分参数

2016/09/15 --- 2.2.3-beta
    1. 增加了图片批量缩放接口（含批量规格）
    2. 修复了图片处理请求地址不正确的bug
    3. 优化了请求接口中参数的处理方式
    
2016/09/29 --- 2.2.4-beta
          修复了ImageTracker的批量缩放方法（scaleMultiply）请求不正确的问题
          
2017/01/21 --- 3.0.0-alpha
          增加内外网ip区分，重载了交互类的构造方法