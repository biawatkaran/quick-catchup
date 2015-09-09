#Spring AOP

**Aspect** Some functionality we need to apply to our application at global level. e.g. logging, trx management etc.

**Advice** Method to be implemented. Interceptor are nothing but child of advice interface.

`<!-- A simple MethodInterceptor style advice object (InterceptorA implements MethodInterceptor) -->    
<bean name="interceptorA" class="com.javalobby.tnt.spring.aop.InterceptorA" /><!-- A simple MethodBeforeAdvice style advice object (BeforeAdviceA implements MethodBeforeAdvice) -->
<bean name="beforeAdviceA" class="com.javalobby.tnt.spring.aop.BeforeAdviceA"/>`

**Joinpoint** Location in code where advice needs to be applied (field level, method level, constructor level). Spring supports only
method level.

**Pointcut** Set of join points where advice needs to be applied. In spring case it's set of methods. you can specify certain conditions etc

`<bean name="controller.handle.pointcut" class="org.springframework.aop.support.NameMatchMethodPointcut">
	<property name="mappedName" value="handleRequestInternal"/>
</bean>`

**Advisor** Combination of pointcut and advice

`<!-- A pointcut advisor that combines the controller pointcut with interceptor A -->
<bean name="pointcut.advisor1" class="org.springframework.aop.support.DefaultPointcutAdvisor">
 <property name="advice" ref="interceptorA"/>
 <property name="pointcut" ref="controller.handle.pointcut"/>
</bean>`


###Let's Simplify
In above we have defined advisor where needs to have separate pointcut bean. We can eliminate pointcut bean by using  *NameMatchMethodPointcutAdvisor*

`<!-- 
 Use the NameMatchMethod pointcut advisor to make things a little simpler.
 -->
 <bean name="pointcut.advisor1" class="org.springframework.aop.support.NameMatchMethodPointcutAdvisor">
  <property name="advice" ref="interceptorA"/>
  <property name="mappedName" value="handleRequestInternal"/>
 </bean>`


Till now we have aspect things in place. We have not done anything with our object yet. We need to have some sort of wrap over our object. this is done
by using FactoryBean/ProxyFactoryBean

Create our controller bean

`<bean name="myRawController" class="com.javalobby.tnt.spring.aop.ExampleController" />`

Link this with above aspects created

`<bean name="myController" class="org.springframework.aop.framework.ProxyFactoryBean">
 	<property name="target" ref="myRawController"/>
 	<property name="interceptorNames">
 		<list>
 		    <value>pointcut.advisor1</value>
 			<value>beforeAdviceA</value>
 			<value>interceptorA</value>
 		</list>
 	</property>
 </bean>`
 
In above list of values,
 
* if any particular condition is required as stated above i.e. only methods with 'handleRequestInternal' name should be advised upon, then use pointcutadvisor pointcut.advisor1
 
* If no condition needed then only advice can be used in value tags.

 
Order of these list items is important and on particular call of that object
the orderly listed advice will be applied upon.


The use cases are when we have some logging or database related stuff to do. Then on particular execution, the set of advice can be applied.
e.g. store account numbers, since this is transactional behaviour so we can have something like when insertAccount (pointcut) is called then 
start transaction (before advice) and once job finished, commit transaction (after advice) can be called.


##Reference

 [http://www.javalobby.org/java/forums/t44746.html](http://www.javalobby.org/java/forums/t44746.html)