### 异常重要性,不用说了
### 这里主要做以下场景的封装
   - 接口定义了一个返回类型，实现类需要多个返回类型时.  
   ```
                 可以使用RuntimeException,包装一个返回值。上层捕获异常获得。
                 当然也可以使用TreadLocal，但是这样容易内存泄漏。
          @see com.wlh.exception.ReturnException       
   ```              
   - 实现接口需要做异常适配的时候，
   ```
      本身不做多余操作，
      try {
			throw new FileNotFoundException();
		} catch (FileNotFoundException e) {
			throw new ConvertRunException(e);
		}
      @see com.wlh.exception.ConvertRunException
      @see com.wlh.exception.ConvertCompileException
      @see com.wlh.exception.ConvertErrorException
   ```
   - 代理printStackTrace()方法，输出到日志文件中。
   ```
   	有些人，不知道log.debug("==",e);  log.debug(e); e.printStackTrace() 的区别
   	甚至更过分的是，不知道e.printStackTrace()只能输出到控制台。
   	解决办法
   	1. 用个全局的Exception，重写他的printStackTrace()
   	2. System.setOut(printOut);  
   	@see com.wlh.exception.SysErrDemo
   	详见http://www.cnblogs.com/iwinson/p/6075287.html
   ```

### 无依赖