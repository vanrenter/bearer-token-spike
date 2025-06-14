# bearer-token-spike
a test project for an issue in spring-boot:3.5.0

## branches
* main
	* spring-boot:3.5.0 with error
* spring-boot:3.4.6
	* the latest working version
* workaround
	* spring-boot:3.5.0 + workaround (added dependency)

## error

```
Caused by: java.lang.NoClassDefFoundError: org/springframework/security/oauth2/jwt/JwtDecoderFactory
	at org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.DPoPAuthenticationConfigurer.configure(DPoPAuthenticationConfigurer.java:79) ~[spring-security-config-6.5.0.jar:6.5.0]
	at org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer.configure(OAuth2ResourceServerConfigurer.java:288) ~[spring-security-config-6.5.0.jar:6.5.0]
	at org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer.configure(OAuth2ResourceServerConfigurer.java:147) ~[spring-security-config-6.5.0.jar:6.5.0]
	at org.springframework.security.config.annotation.AbstractConfiguredSecurityBuilder.configure(AbstractConfiguredSecurityBuilder.java:398) ~[spring-security-config-6.5.0.jar:6.5.0]
	at org.springframework.security.config.annotation.AbstractConfiguredSecurityBuilder.doBuild(AbstractConfiguredSecurityBuilder.java:352) ~[spring-security-config-6.5.0.jar:6.5.0]
	at org.springframework.security.config.annotation.AbstractSecurityBuilder.build(AbstractSecurityBuilder.java:38) ~[spring-security-config-6.5.0.jar:6.5.0]
	at spike.config.BeansConfig.filterChain(BeansConfig.java:35) ~[classes/:na]
	at spike.config.BeansConfig$$SpringCGLIB$$0.CGLIB$filterChain$0(<generated>) ~[classes/:na]
	at spike.config.BeansConfig$$SpringCGLIB$$FastClass$$1.invoke(<generated>) ~[classes/:na]
	at org.springframework.cglib.proxy.MethodProxy.invokeSuper(MethodProxy.java:258) ~[spring-core-6.2.7.jar:6.2.7]
	at org.springframework.context.annotation.ConfigurationClassEnhancer$BeanMethodInterceptor.intercept(ConfigurationClassEnhancer.java:393) ~[spring-context-6.2.7.jar:6.2.7]
	at spike.config.BeansConfig$$SpringCGLIB$$0.filterChain(<generated>) ~[classes/:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:568) ~[na:na]
	at org.springframework.beans.factory.support.SimpleInstantiationStrategy.lambda$instantiate$0(SimpleInstantiationStrategy.java:171) ~[spring-beans-6.2.7.jar:6.2.7]
	... 42 common frames omitted
Caused by: java.lang.ClassNotFoundException: org.springframework.security.oauth2.jwt.JwtDecoderFactory
	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:641) ~[na:na]
	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(ClassLoaders.java:188) ~[na:na]
	at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:525) ~[na:na]
	... 59 common frames omitted
```

## root cause
* `JwtDecoderFactory` is in `spring-security-oauth2-jose:6.5.0`
* which is not added by default
* maybe it should be included as a dependency from `spring-security-oauth2-client` if it is now required as a dependency

