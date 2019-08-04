package com.glueframework.boilerplate;

import java.sql.SQLException;

public class TestH2 {
	public static void main(String[] args) throws SQLException {
		// java -jar h2*.jar org.h2.tools.Server -web -webPort 8082 -browser
		org.h2.tools.Server.main("-web", "-webPort", "8082", "-browser");
	}
}
