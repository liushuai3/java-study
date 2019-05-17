# java-rmi
【定义】

       Java RMI：Java远程方法调用，即Java RMI（Java Remote Method Invocation）是Java编程语言里，一种用于实现远程过程调用的应用程序编程接口。它使客户机上运行的程序可以调用远程服务器上的对象。远程方法调用特性使Java编程人员能够在网络环境中分布操作。RMI全部的宗旨就是尽可能简化远程接口对象的使用。

      我们知道远程过程调用（Remote Procedure Call, RPC）可以用于一个进程调用另一个进程（很可能在另一个远程主机上）中的过程，从而提供了过程的分布能力。Java 的 RMI 则在 RPC 的基础上向前又迈进了一步，即提供分布式对象间的通讯。

      RMI（Remote Method Invocation）为远程方法调用，是允许运行在一个Java虚拟机的对象调用运行在另一个Java虚拟机上的对象的方法。

      这两个虚拟机可以是运行在相同计算机上的不同进程中，也可以是运行在网络上的不同计算机中。

一、工作原理

      RMI能让一个Java程序去调用网络中另一台计算机的Java对象的方法，那么调用的效果就像是在本机上调用一样。通俗的讲：A机器上面有一个class，通过远程调用，B机器调用这个class 中的方法。

      RMI，远程方法调用（Remote Method Invocation）是Enterprise JavaBeans的支柱，是建立分布式Java应用程序的方便途径。RMI是非常容易使用的，但是它非常的强大。

      RMI的基础是接口，RMI构架基于一个重要的原理：定义接口和定义接口的具体实现是分开的。下面我们通过具体的例子，建立一个简单的远程计算服务和使用它的客户程序

二、RMI包含部分：
1.远程服务的接口定义
2.远程服务接口的具体实现
3.桩（Stub）和框架（Skeleton）文件
4.一个运行远程服务的服务器
5.一个RMI命名服务，它允许客户端去发现这个远程服务
6.类文件的提供者（一个HTTP或者FTP服务器）
7.一个需要这个远程服务的客户端程序

三、RMI的用途?

     RMI的用途是为分布式Java应用程序之间的远程通信提供服务，提供分布式服务。

     目前主要应用时封装在各个J2EE项目框架中，例如Spring，EJB（Spring和EJB均封装了RMI技术）

     在Spring中实现RMI：

     ①在服务器端定义服务的接口，定义特定的类实现这些接口；

     ②在服务器端使用org.springframework.remoting.rmi.RmiServiceExporter类来注册服务；

     ③在客户端使用org.springframework.remoting.rmi.RmiProxyFactoryBean来实现远程服务的代理功能；

     ④在客户端定义访问与服务器端服务接口相同的类

四、RMI的局限？                                                                     

      RMI目前使用Java远程消息交换协议JRMP（Java Remote Messaging Protocol）进行通信。JRMP是专为Java的远程对象制定的协议，由于JRMP是专为Java对象制定的，因此，RMI对于用非Java语言开发的应用系统的支持不足。不能与用非Java语言书写的对象进行通信（意思是只支持客户端和服务器端都是Java程序的代码的远程调用）。

五、RMI的使用局限?

      由于客户机和服务器都是使用Java编写的，二者平台兼容性的要求仅仅是双方都运行在版本兼容的Java虚拟机上。

六、RMI调用远程方法的参数和返回值

      当调用远程对象上的方法时，客户机除了可以将原始类型的数据作为参数一外，还可以将对象作为参数来传递，与之相对应的是返回值，可以返回原始类型或对象，这些都是通过Java的对象序列化（serialization）技术来实现的。（换而言之：参数或者返回值如果是对象的话必须实现Serializable接口）

七、RMI类和接口（完成一个简单RMI需要用到的类）。
(一) Remote接口：是一个不定义方法的标记接口

      Public interface Remote{}

      在RMI中，远程接口声明了可以从远程Java虚拟机中调用的方法集。远程接口满足下列要求：

      1、远程接口必须直接或间接扩展Java.rmi.Remote接口，且必须声明为public，除非客户端于远程接口在同一包中

      2、在远程接口中的方法在声明时，除了要抛出与应用程序有关的一场之外，还必须包括RemoteException（或它的超类，IOExcepion或Exception）异常

      3、在远程方法声明中，作为参数或返回值声明的远程对象必须声明为远程接口，而非该接口的实现类。

(二) RemoteObject抽象类实现了Remote接口和序列化Serializable接口，它和它的子类提供RMI服务器函数。

(三) LocateRegistry final()类用于获得特定主机的引导远程对象注册服务器程序的引用（即创建stub），或者创建能在特定端口接收调用的远程对象注册服务程序。

服务器端：向其他客户机提供远程对象服务

      SomeService servcie=……；//远程对象服务
1.Registry registry=LocateRegisty.getRegistry()；//Registry是个接口，他继承了Remote，此方法返回本地主机在默认注册表端口 1099 上对远程对象 Registry 的引用。
2.getRegistry(int port) 返回本地主机在指定 port 上对远程对象 Registry 的引用;
3.getRegistry(String host)  返回指定 host 在默认注册表端口 1099 上对远程对象 Registry 的引用;
4.getRegistry(String host, int port) 返回指定的 host 和 port 上对远程对象 Registry 的引用
5.registry.bind(“I serve”,service);// bind(String name,Remote obj) 绑定对此注册表中指定 name 的远程引用。name ： 与该远程引用相关的名称 obj ： 对远程对象（通常是一个 stub）的引用
6.unbind（String name）移除注册表中指定name的绑定。
7.rebind（String name,Remote obj）重新绑定，如果name已存在，但是Remote不一样则替换，如果Remote一样则丢弃现有的绑定
8.lookup(String name) 返回注册表中绑定到指定 name 的远程引用，返回Remote
9.String[] list()   返回在此注册表中绑定的名称的数组。该数组将包含一个此注册表中调用此方法时绑定的名称快照。

客户机端：向服务器提供相应的服务请求。
Registry registry=LocateRegisty.getRegistry()；
SomeService servcie=(SomeService)registry.lookup(“I serve”);
Servcie.requestService();

(四) Naming类和Registry类类似。

客户端：

      Naming.lookup(String url)

      url 格式如下"rmi://localhost/"+远程对象引用
服务器端：
     Registry registry=LocateRegistry.createRegistry(int port);
      Naming.rebind(“service”,service);

(五) RMISecurityManager类

     在RMI引用程序中，如果没有设置安全管理器，则只能从本地类路径加载stub和类，这可以确保应用程序不受由远程方法调用所下载的代码侵害

     在从远程主机下载代码之前必须执行以下代码来安装RMISecurityManager:

     System.setSecurityManager（new RMISecurityManager（））；
