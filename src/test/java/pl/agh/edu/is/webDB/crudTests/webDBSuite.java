package pl.agh.edu.is.webDB.crudTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@SuiteClasses(value = {
	ApplicationManagementTest.class,
	DiscountsManagementTest.class,
	FormFieldsManagementTest.class,
	GroupsManagementTest.class,
	RightsManagementTest.class,
	SellersManagementTest.class
})


public class webDBSuite {

}
