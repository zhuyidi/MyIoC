# **1. 项目描述**

这是一个Spring IoC容器的简单实现.

# **2. 项目设计**

(1) 首先初始化IoC容器, 进行Resource资源定位, 加载配置文件.
(2) 然后将配置文件里面的信息转化为容器内部的数据结构:BeanDefinition.
(3) 使用延迟加载的模式来实例化对应的Bean(将BeanDefinition实例化为相应的对象).
(4) 最后注入对象之间的依赖关系.
