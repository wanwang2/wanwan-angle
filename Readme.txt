Readme
——这是一个关于代码生成的项目


1 词语解释
1.1, Express
	BeanEntity-Entity表达式/FileEntity-Entity表达式/LinuxEntity-Entity表达式/ 
	1.1.1 解释BeanEntity表示Child在文件名里面表示module, '-'后面的内容表示describe
	
1.2, @Node 表示文件作用域下的局部作用域

2 操作用法
2.1, list工具用法：找到 org.wanwanframework.angle.link.LinkController 执行里面的main方法就可以生成代码
	2.1.1 生成的代码在resources\list_out目录下
	2.1.2生成的原理依靠resoures\list目录下的模板文件和参数文件
	
2.2, 每个包下都有Controller类，里面有main方法的就是工具Controller，执行就能生成相应的代码
	

	