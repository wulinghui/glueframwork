package com.glueframework.ioc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration2.beanutils.BeanDeclaration;
import org.apache.commons.configuration2.beanutils.BeanHelper;
import org.apache.commons.configuration2.beanutils.ConstructorArg;
import org.apache.commons.lang3.ClassUtils;

import com.glueframework.common.exception.ConvertRunException;
import com.glueframework.confinger.ConfigerBeanSuper;
import com.glueframework.log.ILogger;
import com.glueframework.log.LogMSG;

/**
 * @author Administrator
 * 
 */
public class BeanDeclarationImp extends ConfigerBeanSuper implements BeanDeclaration {
	String Bean_Properties;
	String Nested_Bean;
	String Constructor_Args;
	protected static final ILogger logger = LogMSG.getLogger();
	private String class_name;
	protected BeanHelper beanHelper;
	
    @Override
    public Map<String, Object> getNestedBeanDeclarations()
    {
        // no nested beans
        return Collections.emptyMap();
    }

    @Override
    public Collection<ConstructorArg> getConstructorArgs()
    {
        // no constructor arguments
    	List<ConstructorArg> list = new ArrayList<>();
    	ConstructorArg arg = null;
    	// table 
    	
		list.add(arg);
        return list;
    }

    @Override
    // 普通属性。
    public Map<String, Object> getBeanProperties()
    {
        // the properties are equivalent to the parameters
    	try {
			Class<?> class1 = ClassUtils.getClass( getBeanFactoryName() );
		} catch (ClassNotFoundException e) {
			throw new ConvertRunException(e);
		}
        return params;
    }

    @Override
    // 这个在apache里面没有使用。 
    public Object getBeanFactoryParameter()
    {
        return null;
    }
    
    @Override
    public String getBeanFactoryName()
    {
        return super.get_value_();
    }

    @Override
    public String getBeanClassName()
    {
        return class_name;
    }

}
