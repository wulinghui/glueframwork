# ���ü���:
1. System���������,����һЩ���á�
2. ֮���װ����DataBaseConfiguration�����������ö���DataBaseConfiguration����������ˡ�
3. ��֮�������ϵͳ����������ϵͳ�����á�

# ��ʵ�ֶ�DataBaseConfiguration���з�װ�Ĺ�����:
�뵽��2��������
1. ͨ������������Ĳ������Ƹ����࣬����������չ��
���ǵ�����ֻ��¼���ǵ�������
���ƣ���������ͳһһ�ű����
������ƺ����ԣ���������ݿ��ܹ��󣬵�������

2. ����ͨ����������չ�ķֿ�����ʵ�ֱַ�Ĳ�����
�Ҹ�˾��ְ��Ψһ���õĵط��Ǳ�ķ�ɢ�������ܳ������⡣��������ͨ��ͳһ����������


#  ϵͳĬ���������ʵ��?
ʹ����� configuration.get(cls, key, defaultValue)
���ģʽ��������������ϵͳ��˵����Щ���ˡ� ÿ�ζ���һ��Ĭ�ϵ����õ�new����ִ�С�
�����ļ���Ҫд��������ǲ���ֱ��

����spi���ƣ�ÿ����ϵͳ�ṩ���Ե������ļ���ʵ���ࡣ�����ڵ������е�ʵ�������÷���,ʵ�����õ�ע�����
        ServiceLoader<IOperation> operations = ServiceLoader.load(IOperation.class);
        Iterator<IOperation> operationIterator = operations.iterator();
		while (operationIterator.hasNext()) {
            IOperation operation = operationIterator.next();
            System.out.println(operation.operation(6, 3));
        }