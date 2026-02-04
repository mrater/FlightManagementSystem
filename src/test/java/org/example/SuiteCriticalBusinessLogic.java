package org.example;

import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Suite: Critical business logic tests")
@SelectPackages({"org.example.Model", "org.example.Controller"})
@IncludeTags("critical")
@ExcludeTags("slow")
public class SuiteCriticalBusinessLogic {
}
