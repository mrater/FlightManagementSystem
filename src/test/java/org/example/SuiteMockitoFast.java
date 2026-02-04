package org.example;

import org.example.Controller.ControllerPassengerMockitoTest;
import org.example.Model.ModelMockitoTest;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Suite: Fast Mockito-based tests")
@SelectClasses({ModelMockitoTest.class, ControllerPassengerMockitoTest.class})
@IncludeTags("mockito")
@ExcludeTags("integration")
public class SuiteMockitoFast {
}
