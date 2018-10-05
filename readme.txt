学习记录：

1. 数据库连接池的使用
2. spring boot 整合freemarker 使用
2.1 freemarker的基础配置
2.2 freemarker模板使用demo
3. springboot-ControllerAdvice全局捕获异常
4. Springboot 之 静态资源路径配置
5. 静态资源路径是指系统可以直接访问的路径，且路径下的所有文件均可被用户直接读取。



# spring的Aspect，Filter、Interceptor、ControllerAdvice区别:
a. filter，这是java的过滤器，和框架无关的，是所有过滤组件中最外层的，从粒度来说是最大的。
配置方式，有直接实现Filter+@component，@Bean+@configuration（第三方的filter）
b. interceptor，spring框架的拦截器配置方式，@configuration+继承WebMvcConfigurationSupport类添加过滤器。
c. aspect，可以自定义要切入的类甚至再细的方法，粒度最小。加个注解用效果更佳。
d. controllerAdvice，是controller的增强，和ExceptionHandler一起用来做全局异常。
总结：filter：和框架无关，可以控制最初的http请求，但是更细一点的类和方法控制不了。
interceptor：可以控制请求的控制器和方法，但控制不了请求方法里的参数。 
aspect : 可以自定义切入的点，有方法的参数，但是拿不到http请求，可以通过其他方式如RequestContextHolder获得。

