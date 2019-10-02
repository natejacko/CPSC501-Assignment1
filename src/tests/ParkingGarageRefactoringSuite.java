package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses ({ ParkingLevelExceptionTest.class, BusRefactorPropertyTests.class, BusRefactorParkingTests.class })
public class ParkingGarageRefactoringSuite { }
