package com.glueframework.boilerplate.common;

import java.nio.file.Path;
import java.nio.file.WatchEvent.Kind;

public interface IJdtConvert {
	IJdtConvert DEFAULT = null;
	String doHandle(Kind<?> kind, Path fileName);
}
