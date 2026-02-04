package org.example;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Suite: Controller layer tests")
@SelectPackages("org.example.Controller")
public class SuiteControllerLayer {
}
