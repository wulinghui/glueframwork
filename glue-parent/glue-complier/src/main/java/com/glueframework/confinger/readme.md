# 配置级别:
1. System级别的配置,必须一些配置。
2. 之后封装到了DataBaseConfiguration，就所有配置都用DataBaseConfiguration级别的配置了。
3. 在之后各个子系统，就做各个系统的配置。

# 在实现对DataBaseConfiguration进行封装的过程中:
想到了2个方案，
1. 通过反射把真正的操作下移给子类，交给子类扩展。
我们的主表只记录他们的主键。
优势，所有数据统一一张表管理。
这样设计很明显，主表的数据可能过大，导致慢。

2. 我们通过把子类扩展的分开，来实现分表的操作。
且各司其职，唯一不好的地方是表的分散管理，可能出现问题。所以我们通过统一命名来管理。


#  系统默认配置如何实现?
使用这个 configuration.get(cls, key, defaultValue)
这个模式，对于我们整个系统来说就有些慢了。 每次都有一个默认的配置的new必须执行。
配置文件需要写在哪里，我们不能直接

采用spi机制，每个子系统提供各自的配置文件的实现类。我们在调用所有的实现类配置方法,实现配置的注册服务。
        ServiceLoader<IOperation> operations = ServiceLoader.load(IOperation.class);
        Iterator<IOperation> operationIterator = operations.iterator();
		while (operationIterator.hasNext()) {
            IOperation operation = operationIterator.next();
            System.out.println(operation.operation(6, 3));
        }