#	项目名称：阳江市测绘地理信息监管与服务平台

工程名称：surveying-information-platform

项目架构：分布式微服务架构，采用springboot+springcloud（eureka+feign+zuul+hystrix+config）+mybatis+redis+ftp+token+es+mycat



##	工程分析

* common：公共项目
  + 这是一个公共项目，里边存放一些共用的东西，比如util工具类、静态常量、通用的controller、通用的注解(自定义注解)、枚举状态...
  + 需要注意的是，common项目永远都不会有任何的依赖，因为这个项目就叫做通用。
  + surveying-common（jar）
* config：中间件的配置项目
  + 里边主要存放各种中间件框架，如redis、ftp、elasticsearch等等，这个项目也是通用项目。这个项目中必须要有个依赖就是common，因为在配置redis等框架的时候需要用到静态常量。
  + surveying-config（jar）
* eureka：注册中心
  + 注册中心是必不可少的一个项目，注册中心没有依赖，将来一定会单独的放在服务器上，谁用谁注册和发现。为了方便于横向扩展和jar包服用，可以用一个父级项目包裹多个eureka。
  + surveying-eureka-management（pom）
  + surveying-eureka-7081
  + surveying-eureka-7082
  + surveying-eureka-7083
* consumer：消费者
  + 消费者项目是调用提供者的项目，为了方便横向扩展以及jar包的复用，也需要做一个父级项目。consumer必然会产生依赖，因为目前springcloud的所使用的声明式服务调用(feign)，所以它依赖于api项目。
  + surveying-comsumer-management（pom）
  + surveying-system-consumer-6081
  + 有关于consumer的所有项目
    系统管理
    测绘管理
    系统主页
* provider：生产者
  + 服务提供者，主要是调用service做服务提供，需要依赖于service层。为了方便横向扩展以及jar包的复用，也需要做一个父级项目
  + surveying-provider-management（pom）
  + surveying-system-provider-5081
  + 有关于consumer的所有项目
    系统管理
    测绘管理
    系统主页
* management：有关于业务和数据持久化的项目
  + 存放model，mapper，service，api的项目
  + surveying-management（pom）
  + surveying-model：存放实体类以及po类型还有通用model，这个model项目不需要产生任何的依赖
  + surveying-mapper：存放mapper接口以及mapper.xml的地方，这里会使用到数据源，所以动态数据源(多数据源切换)也会放在mapper项目中。另外，mapper需要依赖于model。
  + surveying-service：存放业务逻辑的地方，service需要依赖于mapper
  + surveying-api：声明式服务调用接口的地方
* zuul：路由网关
  + surveying-server-zuul（jar）
  + 这个路由中也可能使用到common中的工具类、静态常量、枚举...，所以需要依赖于common项目。其实路由还需要依赖，因为暂时没有用，用到的时候再添加api的依赖
