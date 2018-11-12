package net.thucydides.core;

import net.thucydides.core.util.EnvironmentVariables;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Properties that can be passed to a web driver test to customize its behaviour.
 * The properties can be passed as system properties or placed in the 'thucydides.properties' file using a lower-case,
 * period-separated format. For example, WEBDRIVER_DRIVER is passed as -Dwebdriver.driver=firefox
 *
 * @author johnsmart
 *
 */
public enum ThucydidesSystemProperty {

    /**
     * The WebDriver driver - firefox, chrome, iexplorer, htmlunit, safari.
     */
    WEBDRIVER_DRIVER,

    /** A shortcut for 'webdriver.driver'. */
    DRIVER,

    /**
     * If using a provided driver, what type is it.
     * The implementation class needs to be defined in the webdriver.provided.{type} system property.
    */
    WEBDRIVER_PROVIDED_TYPE,

    /**
     * The default starting URL for the application, and base URL for relative paths.
     */
    WEBDRIVER_BASE_URL,

    /**
     * The URL to be used for remote drivers (including a selenium grid hub)
     */
    WEBDRIVER_REMOTE_URL,

    /**
     * What port to run PhantomJS on (used in conjunction with webdriver.remote.url to
     * register with a Selenium hub, e.g. -Dphantomjs.webdriver=5555 -Dwebdriver.remote.url=http://localhost:4444
     */
    PHANTOMJS_WEBDRIVER_PORT,

    /**
     * The driver to be used for remote drivers
     */
    WEBDRIVER_REMOTE_DRIVER,

    WEBDRIVER_REMOTE_BROWSER_VERSION,

    WEBDRIVER_REMOTE_OS,

    /**
     * Path to the Internet Explorer driver, if it is not on the system path.
     */
    WEBDRIVER_IE_DRIVER,

    /**
     * Path to the Edge driver, if it is not on the system path.
     */
    WEBDRIVER_EDGE_DRIVER,

    /**
     * Path to the Chrome driver, if it is not on the system path.
     */
    WEBDRIVER_CHROME_DRIVER,

    /**
     * Path to the Chrome binary, if it is not on the system path.
     */
    WEBDRIVER_CHROME_BINARY,

    @Deprecated
    THUCYDIDES_PROJECT_KEY,

    /**
     * A unique identifier for the project under test, used to record test statistics.
     */
    SERENITY_PROJECT_KEY,

    @Deprecated
    THUCYDIDES_PROJECT_NAME,

    /**
     * What name should appear on the reports
     */
    SERENITY_PROJECT_NAME,


    @Deprecated
    THUCYDIDES_HOME,

    /**
     * The home directory for Thucydides output and data files - by default, $USER_HOME/.thucydides
     */
    SERENITY_HOME,

    @Deprecated
    THUCYDIDES_REPORT_RESOURCES,

    /**
     * Indicates a directory from which the resources for the HTML reports should be copied.
     * This directory currently needs to be provided in a JAR file.
     */
    SERENITY_REPORT_RESOURCES,

    /**
     * Encoding for reports output
     */
    @Deprecated
    THUCYDIDES_REPORT_ENCODING,

    /**
     * Encoding for reports output
     */
    SERENITY_REPORT_ENCODING,

    @Deprecated
    THUCYDIDES_OUTPUT_DIRECTORY("thucydides.outputDirectory"),

    /**
     * Where should reports be generated (use the system property 'serenity.outputDirectory').
     */
    SERENITY_OUTPUT_DIRECTORY("serenity.outputDirectory"),

    /**
     * Default name of report with configurations. It will contains some values that was used during generation of reports
     */
    @Deprecated
    THUCYDIDES_CONFIGURATION_REPORT("thucydides.configuration.json"),

    /**
     * Default name of report with configurations. It will contains some values that was used during generation of reports
     */
    SERENITY_CONFIGURATION_REPORT("serenity.configuration.json"),

    @Deprecated
    THUCYDIDES_FLOW_REPORTS_DIR("flow"),

    /**
     * Default name of folder, with reports about test flow and aggregation report generation
     */
    SERENITY_FLOW_REPORTS_DIR("flow"),

    /**
     * Should Thucydides only store screenshots for failing steps?
     * This can save disk space and speed up the tests somewhat. Useful for data-driven testing.
     * @deprecated This property is still supported, but thucydides.take.screenshots provides more fine-grained control.
     */
    @Deprecated
    THUCYDIDES_ONLY_SAVE_FAILING_SCREENSHOTS,

    @Deprecated
    THUCYDIDES_DRIVER_CAPABILITIES,

    /**
     * A set of user-defined capabilities to be used to configure the WebDriver driver.
     * Capabilities should be passed in as a space or semi-colon-separated list of key:value pairs, e.g.
     * "build:build-1234; max-duration:300; single-window:true; tags:[tag1,tag2,tag3]"
     */
    SERENITY_DRIVER_CAPABILITIES,

    /**
     * Should Thucydides take screenshots for every clicked button and every selected link?
     * By default, a screenshot will be stored at the start and end of each step.
     * If this option is set to true, Thucydides will record screenshots for any action performed
     * on a WebElementFacade, i.e. any time you use an expression like element(...).click(),
     * findBy(...).click() and so on.
     * This will be overridden if the THUCYDIDES_ONLY_SAVE_FAILING_SCREENSHOTS option is set to true.
     * @deprecated This property is still supported, but thucydides.take.screenshots provides more fine-grained control.
     */
    @Deprecated
    THUCYDIDES_VERBOSE_SCREENSHOTS,

    @Deprecated
    THUCYDIDES_VERBOSE_STEPS,

    /**
     * If set to true, WebElementFacade events and other step actions will be logged to the console.
     */
    SERENITY_VERBOSE_STEPS,

    /**
     * Words that will be recognised as pronouns by Serenity Screenplay in Cucumber and used to refer to the
     * actor in the spotlight, rather than as an actor name. Defaults to "he" and "she"
     */
    SCREENPLAY_PRONOUNS,


    @Deprecated
    THUCYDIDES_TAKE_SCREENSHOTS,

    /**
     *  Fine-grained control over when screenshots are to be taken.
     *  This property accepts the following values:
     *  <ul>
     *      <li>FOR_EACH_ACTION</li>
     *      <li>BEFORE_AND_AFTER_EACH_STEP</li>
     *      <li>AFTER_EACH_STEP</li>
     *      <li>FOR_FAILURES</li>
     *  </ul>
     */
    SERENITY_TAKE_SCREENSHOTS,

    @Deprecated
    THUCYDIDES_REPORTS_SHOW_STEP_DETAILS,

    /**
     * Should Thucydides display detailed information in the test result tables.
     * If this is set to true, test result tables will display a breakdown of the steps by result.
     * This is false by default.
     */
    SERENITY_REPORTS_SHOW_STEP_DETAILS,

    @Deprecated
    THUCYDIDES_REPORT_SHOW_MANUAL_TESTS,

    /**
     * Show statistics for manual tests in the test reports.
     */
    SERENITY_REPORT_SHOW_MANUAL_TESTS,

    @Deprecated
    THUCYDIDES_REPORT_SHOW_RELEASES,

    /**
     * Report on releases
     */
    SERENITY_REPORT_SHOW_RELEASES,

    @Deprecated
    THUCYDIDES_REPORT_SHOW_PROGRESS,

    SERENITY_REPORT_SHOW_PROGRESS,

    @Deprecated
    THUCYDIDES_REPORT_SHOW_HISTORY,

    SERENITY_REPORT_SHOW_HISTORY,

    @Deprecated
    THUCYDIDES_REPORT_SHOW_TAG_MENUS,

    SERENITY_REPORT_SHOW_TAG_MENUS,

    @Deprecated
    THUCYDIDES_REPORT_TAG_MENUS,

    SERENITY_REPORT_TAG_MENUS,

    @Deprecated
    THUCYDIDES_EXCLUDE_UNRELATED_REQUIREMENTS_OF_TYPE,

    SERENITY_EXCLUDE_UNRELATED_REQUIREMENTS_OF_TYPE,

    @Deprecated
    THUCYDIDES_RESTART_BROWSER_FREQUENCY,

    /**
     * Restart the browser every so often during data-driven tests.
     */
    SERENITY_RESTART_BROWSER_FREQUENCY,

    @Deprecated
    THUCYDIDES_RESTART_BROWSER_FOR_EACH,

    /**
     * Indicate when a browser should be restarted during a test run.
     * Can be one of: example, scenario, story, feature, never
     *
     */
    SERENITY_RESTART_BROWSER_FOR_EACH,

    @Deprecated
    THUCYDIDES_DIFFERENT_BROWSER_FOR_EACH_ACTOR,

    /**
     * When multiple actors are used with the Screenplay pattern, a separate browser is configured for each actor.
     * Set this property to false if you want actors use a common browser.
     * This can be useful if actors are used to illustrate the intent of a test, but no tests use more than one actor simultaneously
     */
    SERENITY_DIFFERENT_BROWSER_FOR_EACH_ACTOR,

    @Deprecated
    THUCYDIDES_STEP_DELAY,

    /**
     * Pause (in ms) between each test step.
     */
    SERENITY_STEP_DELAY,

    @Deprecated
    THUCYDIDES_TIMEOUT,

    /**
     * How long should the driver wait for elements not immediately visible, in seconds.
     */
    SERENITY_TIMEOUT,

    /**
     * Don't accept sites using untrusted certificates.
     * By default, Thucydides accepts untrusted certificates - use this to change this behaviour.
     */
    REFUSE_UNTRUSTED_CERTIFICATES,

    /**
     * Use the same browser for all tests (the "Highlander" rule)
     * Deprecated: Use THUCYDIDES_RESTART_BROWSER_FOR_EACH instead.
     */
    @Deprecated
    THUCYDIDES_USE_UNIQUE_BROWSER,

    @Deprecated
    THUCYDIDES_ESTIMATED_AVERAGE_STEP_COUNT,

    /**
     * The estimated number of steps in a pending scenario.
     * This is used for stories where no scenarios have been defined.
     */
    SERENITY_ESTIMATED_AVERAGE_STEP_COUNT,

    @Deprecated
    THUCYDIDES_ESTIMATED_TESTS_PER_REQUIREMENT,

    /**
     * The estimated number of tests in a typical story.
     * Used to estimate functional coverage in the requirements reports.
     */
    SERENITY_ESTIMATED_TESTS_PER_REQUIREMENT,

    @Deprecated
    THUCYDIDES_ISSUE_TRACKER_URL,

    /**
     *  Base URL for the issue tracking system to be referred to in the reports.
     *  If defined, any issues quoted in the form #1234 will be linked to the relevant
     *  issue in the issue tracking system. Works with JIRA, Trac etc.
     */
    SERENITY_ISSUE_TRACKER_URL,

    @Deprecated
    THUCYDIDES_NATIVE_EVENTS,

    /**
     * Activate native events in Firefox.
     * This is true by default, but can cause issues with some versions of linux.
     */
    SERENITY_NATIVE_EVENTS,

    /**
     * If the base JIRA URL is defined, Thucydides will build the issue tracker url using the standard JIRA form.
     */
    JIRA_URL,

    /**
     *  If defined, the JIRA project id will be prepended to issue numbers.
     */
    JIRA_PROJECT,

    /**
     *  If defined, the JIRA username required to connect to JIRA.
     */
    JIRA_USERNAME,

    /**
     *  If defined, the JIRA password required to connect to JIRA.
     */
    JIRA_PASSWORD,

    /**
     *  The JIRA workflow is defined in this file.
     */
    SERENITY_JIRA_WORKFLOW,

    /**
     *  If set to true, JIRA Workflow is active.
     */
    SERENITY_JIRA_WORKFLOW_ACTIVE,

    @Deprecated
    THUCYDIDES_HISTORY,

    /**
     * Base directory in which history files are stored.
     */
    SERENITY_HISTORY,

    @Deprecated
    THUCYDIDES_BROWSER_HEIGHT,

    /**
     *  Redimension the browser to enable larger screenshots.
     */
    SERENITY_BROWSER_HEIGHT,

    @Deprecated
    THUCYDIDES_BROWSER_WIDTH,

    /**
     *  Redimension the browser to enable larger screenshots.
     */
    SERENITY_BROWSER_WIDTH,

    @Deprecated
    THUCYDIDES_BROWSER_MAXIMIZED,

    /**
     * Set to true to get WebDriver to maximise the Browser window before the tests are executed.
     */
    SERENITY_BROWSER_MAXIMIZED,

    @Deprecated
    THUCYDIDES_RESIZED_IMAGE_WIDTH,

    /**
     * Set to false if you don't want Serenity to resize the browser page at the start of a test
     * (Can be useful for custom Appium drivers)
     */
    SERENITY_BROWSER_RESIZING,

    /**
     * If set, resize screenshots to this size to save space.
     */
    SERENITY_RESIZED_IMAGE_WIDTH,

    @Deprecated
    THUCYDIDES_PUBLIC_URL,

    /**
     * Public URL where the Thucydides reports will be displayed.
     * This is mainly for use by plugins.
     */
    SERENITY_PUBLIC_URL,

    @Deprecated
    THUCYDIDES_ACTIVATE_FIREBUGS,

    /**
     * Activate the Firebugs plugin for firefox.
     * Useful for debugging, but not very when running the tests on a build server.
     * It is not activated by default.
     */
    SERENITY_ACTIVATE_FIREBUGS,

    /**
     * Enable applets in Firefox.
     * Use the system property 'security.enable_java'.
     * Applets slow down webdriver, so are disabled by default.
     */
    SECURITY_ENABLE_JAVA("security.enable_java"),

    @Deprecated
    THUCYDIDES_ACTIVATE_HIGHLIGHTING,

    SERENITY_ACTIVATE_HIGHLIGHTING,

    @Deprecated
    THUCYDIDES_BATCH_STRATEGY,

    /**
     * Batch strategy to use for parallel batches.
     * Allowed values - DIVIDE_EQUALLY (default) and DIVIDE_BY_TEST_COUNT
     */
    SERENITY_BATCH_STRATEGY,

    @Deprecated
    THUCYDIDES_BATCH_COUNT,

    /**
     *  A deprecated property that is synonymous with thucydides.batch.size
     */
    SERENITY_BATCH_COUNT,

    @Deprecated
    THUCYDIDES_BATCH_SIZE,

    /**
     *  If batch testing is being used, this is the size of the batches being executed.
     */
    SERENITY_BATCH_SIZE,

    @Deprecated
    THUCYDIDES_BATCH_NUMBER,

    /**
     * If batch testing is being used, this is the number of the batch being run on this machine.
     */
    SERENITY_BATCH_NUMBER,

    @Deprecated
    THUCYDIDES_PROXY_HTTP,

    @Deprecated
    THUCYDIDES_PROXY_HTTP_PORT("thucydides.proxy.http_port"),

    /**
     * HTTP Proxy port configuration for Firefox and PhantomJS
     * Use 'thucydides.proxy.http_port'
     */
    SERENITY_PROXY_HTTP_PORT("serenity.proxy.http_port"),

    @Deprecated
    THUCYDIDES_PROXY_TYPE,

    /**
     * HTTP Proxy type configuration for Firefox and PhantomJS
     */
    SERENITY_PROXY_TYPE,

    @Deprecated
    THUCYDIDES_PROXY_USER,

    /**
     * HTTP Proxy URL configuration
     */
    SERENITY_PROXY_HTTP,

    /**
     * HTTP Proxy username configuration for Firefox and PhantomJS
     */
    SERENITY_PROXY_USER,

    @Deprecated
    THUCYDIDES_PROXY_PASSWORD,

    /**
     * HTTP Proxy password configuration for Firefox and PhantomJS
     */
    SERENITY_PROXY_PASSWORD,

    /**
     * SSL Proxy port configuration for Firefox and PhantomJS - serenity.proxy.sslProxyPort
     */
    SERENITY_PROXY_SSL_PORT("serenity.proxy.sslProxyPort"),

    /**
     * SSL Proxy port configuration for Firefox and PhantomJS - serenity.proxy.sslProxy
     */
    SERENITY_PROXY_SSL("serenity.proxy.sslProxy"),

    /**
     * How long webdriver waits for elements to appear by default, in milliseconds.
     */
    WEBDRIVER_TIMEOUTS_IMPLICITLYWAIT,

    /**
     * Synonym for webdriver.wait.for.timeout
     */
    WEBDRIVER_TIMEOUTS_FLUENTWAIT,

    /**
     * How long webdriver waits by default when you use a fluent waiting method, in milliseconds.
     */
    WEBDRIVER_WAIT_FOR_TIMEOUT,

    @Deprecated
    THUCYDIDES_EXT_PACKAGES,

    /**
     * Extension packages. This is a list of packages that will be scanned for custom TagProvider implementations.
     * To add a custom tag provider, just implement the TagProvider interface and specify the root package for this
     * provider in this parameter.
     */
    SERENITY_EXT_PACKAGES,

    /**
     * Arguments to be passed to the Chrome driver, separated by commas.
     */
    CHROME_SWITCHES,

    /**
     * Path to a Chrome-driver specific extensions file
     */
    CHROME_EXTENSION,

    /**
     * Preferences to be passed to the Firefox driver, separated by semi-colons (commas often appear in the preference
     * values.
     */
    FIREFOX_PREFERENCES,

    /**
     * Used to specify either chrome options or firefox preferences, depending on which driver is being used.
     */
    DRIVER_OPTIONS,

    /**
     * Full path to the Firefox profile to be used with Firefox.
     * You can include Java system properties ${user.dir}, ${user.home} and the Windows environment variables %APPDIR%
     * and %USERPROFILE (assuming these are correctly set in the environment)
     */
    WEBDRIVER_FIREFOX_PROFILE,

    @Deprecated
    THUCYDIDES_JQUERY_INTEGRATION,

    /**
     * Enable JQuery integration.
     * If set to true, JQuery will be injected into any page that does not already have it.
     * This option is deactivated by default, as it can slow down page loading.
     */
    SERENITY_JQUERY_INTEGRATION,

    SAUCELABS_BROWSERNAME("saucelabs.browserName"),

    SAUCELABS_TARGET_PLATFORM,

    SAUCELABS_DRIVER_VERSION,

    SAUCELABS_TEST_NAME,
    /**
     * SauceLabs URL if running the web tests on SauceLabs
     */
    SAUCELABS_URL,

    /**
     * SauceLabs access key - if provided, Thucydides can generate links to the SauceLabs reports that don't require a login.
     */
    SAUCELABS_ACCESS_KEY,

    /**
     * SauceLabs user id - if provided with the access key,
     * Thucydides can generate links to the SauceLabs reports that don't require a login.
     */
    SAUCELABS_USER_ID,

    /**
     * Override the default implicit timeout value for the Saucelabs driver.
     */
    SAUCELABS_IMPLICIT_TIMEOUT,

    /**
     * Saucelabs records screenshots as well as videos by default. Since Thucydides also records screenshots,
     * this feature is disabled by default. It can be reactivated using this system property.
     */
    SAUCELABS_RECORD_SCREENSHOTS,

    /**
     * BrowserStack Hub URL if running the tests on BrowserStack Cloud
     */
    BROWSERSTACK_URL,

    BROWSERSTACK_OS,

    BROWSERSTACK_OS_VERSION("browserstack.os_version"),

    /**
     * Browserstack uses this property for desktop browsers, like firefox, chrome and IE.
     */
    BROWSERSTACK_BROWSER,

    /**
     * Browserstack uses this one for android and iphone.
     */
    BROWSERSTACK_BROWSERNAME("browserstack.browserName"),

    BROWSERSTACK_BROWSER_VERSION,

    /**
     * BrowserStack mobile device name on which tests should be run
     */
    BROWSERSTACK_DEVICE,

    /**
     * Set the screen orientation of BrowserStack mobile device
     */
    BROWSERSTACK_DEVICE_ORIENTATION,

    /**
     * Specify a name for a logical group of builds on BrowserStack
     */
    BROWSERSTACK_PROJECT,

    /**
     * Specify a name for a logical group of tests on BrowserStack
     */
    BROWSERSTACK_BUILD,

    /**
     * Specify an identifier for the test run on BrowserStack
     */
    BROWSERSTACK_SESSION_NAME,

    /**
     * For Testing against internal/local servers on BrowserStack
     */
    BROWSERSTACK_LOCAL,

    /**
     * Generates screenshots at various steps in tests on BrowserStack
     */
    BROWSERSTACK_DEBUG,

    /**
     * Sets resolution of VM on BrowserStack
     */
    BROWSERSTACK_RESOLUTION,

    BROWSERSTACK_SELENIUM_VERSION,

    /**
     * Disable flash on Internet Explorer on BrowserStack
     */
    BROWSERSTACK_IE_NO_FLASH,

    /**
     * Specify the Internet Explorer webdriver version on BrowserStack
     */
    BROWSERSTACK_IE_DRIVER,

    /**
     *  Enable the popup blocker in Internet Explorer on BrowserStack
     */
    BROWSERSTACK_IE_ENABLE_POPUPS,

    @Deprecated
    THUCYDIDES_FILE_IO_RETRY_TIMEOUT,

    /**
     * Timeout (in seconds) for retrying file I/O.
     * Used in net.thucydides.core.resources.FileResources.copyResourceTo().
     * Sometimes, file I/O fails on Windows machine due to the way Windows handles memory-mapped
     * files (http://stackoverflow.com/questions/3602783/file-access-synchronized-on-java-object).
     * This property, if set, will retry copying the resource till timeout. A default value is used
     * if the property is not set.
     */
    SERENITY_FILE_IO_RETRY_TIMEOUT,

    @Deprecated
    THUCYDIDES_LOGGING,

    /**
     * Three levels are supported: QUIET, NORMAL and VERBOSE
     */
    SERENITY_LOGGING,

    @Deprecated
    THUCYDIDES_TEST_ROOT,

    /**
     * The root package for the tests in a given project.
     * If provided, Thucydides will log information about the total number of tests to be executed,
     * and keep a tally of the executed tests. It will also use this as the root package when determining the
     * capabilities associated with a test.
     * If you are using the File System Requirements provider, Thucydides will expect this directory structure to exist
     * at the top of the requirements tree. If you want to exclude packages in a requirements definition and start at a
     * lower level in the hierarchy, use the thucydides.requirement.exclusions property.
     * This is also used by the PackageAnnotationBasedTagProvider to know where to look for annotated requirements.
     */
    SERENITY_TEST_ROOT,

    @Deprecated
    THUCYDIDES_REQUIREMENTS_DIR,

    /**
     * Use this property if you need to completely override the location of requirements for the File System Provider.
     */
    SERENITY_REQUIREMENTS_DIR,

    /**
     * Use this property if you need to enforce that SERENITY_REQUIREMENTS_DIR is configured on your project.
     */
    SERENITY_REQUIREMENTS_DIR_MANDATORY,

    /**
     * if specified, should point to a folder that contains one or more Serenity CSV output files
     */
    SERENITY_TEST_STATISTICS_DIR,

    @Deprecated
    THUCYDIDES_USE_REQUIREMENTS_DIRECTORIES,

    /**
     * Override the directory name for the directory containing feature files. This is designed to work for both
     * simple and multi-module projects. Give the name of the directory inside src/test/resources (e.g. you
     * would put serenity.features.directory = myFeatures for src/test/resources/myFeatures
     */
    SERENITY_FEATURES_DIRECTORY,

    /**
     * Same as serenity.features.directory but for src/test/stories
     */
    SERENITY_STORIES_DIRECTORY,

    /**
     * Normally, REST calls are disabled when a failure occurs. Set this to false to never disable REST calls
     */
    SERENITY_DISABLE_REST_CALLS_AFTER_FAILURES,

    /**
     * By default, Thucydides will read requirements from the directory structure that contains the stories.
     * When other tag and requirements plugins are used, such as the JIRA plugin, this can cause conflicting
     * tags. Set this property to false to deactivate this feature (it is true by default).
     */
    SERENITY_USE_REQUIREMENTS_DIRECTORIES,

    @Deprecated
    THUCYDIDES_ANNOTATED_REQUIREMENTS_DIR,

    /**
     * Use this property if you need to completely override the location of requirements for the Annotated Provider.
     * This is recommended if you use File System and Annotated provider simultaneously.
     * The default value is stories.
     */
    SERENITY_ANNOTATED_REQUIREMENTS_DIR,

    @Deprecated
    THUCYDIDES_LOWEST_REQUIREMENT_TYPE,

    /**
     * Determine what the lowest level requirement (test cases, feature files, story files, should be
     * called. 'Story' is used by default. 'feature' is a popular alternative.
     */
    SERENITY_LOWEST_REQUIREMENT_TYPE,

    @Deprecated
    THUCYDIDES_REQUIREMENT_TYPES,

    /**
     * The hierarchy of requirement types.
     * This is the list of requirement types to be used when reading requirements from the file system
     * and when organizing the reports. It is a comma-separated list of tags.The default value is: capability, feature
     */
    SERENITY_REQUIREMENT_TYPES,

    @Deprecated
    THUCYDIDES_REQUIREMENT_EXCLUSIONS,

    /**
     * When deriving requirement types from a path, exclude any values from this comma-separated list.
     */
    SERENITY_REQUIREMENT_EXCLUSIONS,

    @Deprecated
    THUCYDIDES_RELEASE_TYPES,

    /**
     * What tag names identify the release types (e.g. Release, Iteration, Sprint).
     * A comma-separated list. By default, "Release, Iteration"
     */
    SERENITY_RELEASE_TYPES,

    @Deprecated
    THUCYDIDES_LOCATOR_FACTORY,

    /**
     * Normally, Serenity uses SmartElementLocatorFactory, an extension of the AjaxElementLocatorFactory
     * when instantiating page objects. This is to ensure that web elements are available and usable before they are used.
     * For alternative behaviour, you can set this value to DisplayedElementLocatorFactory, AjaxElementLocatorFactory or DefaultElementLocatorFactory.
     */
    SERENITY_LOCATOR_FACTORY,

    @Deprecated
    THUCYDIDES_DATA_DIR,

    /**
     * Where Serenity stores local data.
     */
    SERENITY_DATA_DIR,

    /**
     * Allows you to override the default serenity.properties location for properties file.
     */
    PROPERTIES,

    @Deprecated
    THUCYDIDES_TEST_REQUIREMENTS_BASEDIR,

    /**
     *  The base directory in which requirements are kept. It is assumed that this directory contains sub folders
     *  src/test/resources. If this property is set, the requirements are read from src/test/resources under this folder
     *  instead of the classpath or working directory. If you need to set an independent requirements directory that
     *  does not follow the src/test/resources convention, use thucydides.requirements.dir instead
     *
     *  This property is used to support situations where your working directory
     *  is different from the requirements base dir (for example when building a multi-module project from parent pom with
     *  requirements stored inside a sub-module : See Jira #Thucydides-100)
     */
    SERENITY_TEST_REQUIREMENTS_BASEDIR,


    /**
     * Set to true if you want the HTML source code to be recorded as well as the screenshots.
     * This is not currently used in the reports.
     */
    //    THUCYDIDES_STORE_HTML_SOURCE,

    @Deprecated
    THUCYDIDES_KEEP_UNSCALED_SCREENSHOTS,

    /**
     * If set to true, a copy of the original screenshot will be kept when screenshots are scaled for the reports.
     * False by default to conserve disk space.
     */
    SERENITY_KEEP_UNSCALED_SCREENSHOTS,

    /**
     * If provided, only classes and/or methods with tags in this list will be executed. The parameter expects
     * a tag or comma-separated list of tags in the shortened form.
     * For example, -Dtags="iteration:I1" or -Dtags="color:red,flavor:strawberry"
     */
    TAGS,

    /**
     * If provided, each test in a test run will have these tags added.
     */
    INJECTED_TAGS,

    @Deprecated
    THUCYDIDES_CSV_EXTRA_COLUMNS,

    /**
     * If set to true, historical flags will be displayed in test lists.
     * This must be set in conjunction with the serenity.historyDirectory property
     */
    SHOW_HISTORY_FLAGS,

    /**
     * Serenity will look in this directory for the previous build results, to use as a basis for the
     * historical flags shown in the test results. By default, the 'history' folder in the working directory will be used.
     */
    SERENITY_HISTORY_DIRECTORY("serenity.historyDirectory"),

    /**
     * Delete the history directory before a new set of results is recorded
     */
    DELETE_HISTORY_DIRECTORY,

    /**
     * Add extra columns to the CSV output, obtained from tag values.
     */
    SERENITY_CSV_EXTRA_COLUMNS,

    @Deprecated
    THUCYDIDES_CONSOLE_HEADINGS,

    /**
     * Write the console headings using ascii-art ("ascii", default value) or in normal text ("normal")
     */
    SERENITY_CONSOLE_HEADINGS,

    @Deprecated
    THUCYDIDES_CONSOLE_COLORS,
    SERENITY_CONSOLE_COLORS,

    /**
     * If set to true, Asciidoc formatting will be supported in the narrative texts.
     */
    NARRATIVE_FORMAT,

    /**
     * What format should test results be generated in.
     * By default, this is "json,xml".
     */
    OUTPUT_FORMATS,

    /**
     * If set to true (the default), allow markdown formatting in test outcome titles and descriptions.
     * This is a comma-separated lists of values from the following: story, narrative, step
     * By default, Markdown is enabled for story titles and narrative texts, but not for steps.
     */
    ENABLE_MARKDOWN,

    /**
     * Path to PhantomJS executable
     */
    PHANTOMJS_BINARY_PATH,

    /**
     * Path to the Gecko driver binary
     */
    WEBDRIVER_GECKO_DRIVER,

    /**
     * If set to true, don't format embedded tables in JBehave or Gherkin steps.
     * False by default.
     */
    IGNORE_EMBEDDED_TABLES,

    /**
     * If set, this will display the related tag statistics on the home page.
     * If you are using external requirements, you may not want to display these tags on the dashboard.
     */
    SHOW_RELATED_TAGS,

    /**
     * If set to true (the default value), a story tag will be extracted from the test case or feature file
     * containing the test.
     */
    USE_TEST_CASE_FOR_STORY_TAG,

    /**
     * Display the pie charts on the dashboard by default.
     * If this is set to false, the pie charts will be initially hidden on the dashboard.
     */
    SHOW_PIE_CHARTS,

    /**
     * If set, this will define the list of tag types to appear on the dashboard screens
     */
    DASHBOARD_TAG_LIST,

    /**
     * If set to false, render report names in a readable form as opposed to a hash format.
     * Note: this can cause problems on operating systems that limit path lengths such as Windows.
     */
    SERENITY_COMPRESS_FILENAMES,

    /**
     * If set, Serenity will scale down the screenshots taken during execution to the size of the carousel in the
     * screenshot page. This results in a loss of quality but a gain in disk space.
     */
    SERENITY_COMPRESS_SCREENSHOTS,

    /**
     * If set, this will define the list of tag types to be excluded from the dashboard screens
     */
    DASHBOARD_EXCLUDED_TAG_LIST,

    /**
     * Format the JSON test outcomes nicely.
     * "true" or "false", turned off by default.
     */
    JSON_PRETTY_PRINTING,

    /**
     * What charset to use for JSON processing.
     * Defaults to UTF-8
     */
    JSON_CHARSET,

    /**
     * What charset to use for report generation.
     * Defaults to UTF-8
     */
    REPORT_CHARSET,

    /**
     * Stack traces are by default decluttered for readability.
     * For example, calls to instrumented code or internal test libraries is removed.
     * This behaviour can be deactivated by setting this property to false.
     */
    SIMPLIFIED_STACK_TRACES,

    @Deprecated
    THUCYDIDES_DRY_RUN,

    /**
     * Run through the steps without actually executing them.
     */
    SERENITY_DRY_RUN,

    /**
     * What (human) language are the Cucumber feature files written in?
     * Defaults to "en"
     */
    FEATURE_FILE_LANGUAGE,

    /**
     * Display the context in the test title.
     * Set to false by default.
     * If the context is a browser type (chrome, ie, firefox, safari, opera), the browser icon will be displayed
     * If the context is an OS (linux, mac, windows, android, iphone), an icon will be displayed
     * Otherwise, the context name will be displayed at the start of the test title.
     */
    THUCYDIDES_DISPLAY_CONTEXT,

    /**
     * Include a context tag with a test if one is provided.
     * Set to 'true' by default
     */
    THUCYDIDES_ADD_CONTEXT_TAG,

    /**
     * What encoding to use for reading Cucumber feature files?
     * Defaults to system default encoding
     */
    FEATURE_FILE_ENCODING,

    /**
     * Fine-tune the number of threads Serenity uses for report generation.
     */
    REPORT_THREADS,
    REPORT_MAX_THREADS,
    REPORT_KEEP_ALIVE_TIME,

    /**
     * Set this to true if you want Serenity to report nested step structures for subsequent steps
     * after a step failure.
     */
    DEEP_STEP_EXECUTION_AFTER_FAILURES,


    /**
     * What test result (success,ignored, or pending) should be shown for manual annotated tests in the reports?
     */
    MANUAL_TEST_REPORT_RESULT,

    @Deprecated
    THUCYDIDES_MAINTAIN_SESSION,

    /**
     * Keep the Thucydides session data between tests.
     * Normally, the session data is cleared between tests.
     */
    SERENITY_MAINTAIN_SESSION,

    /**
     * Path to PhantomJS SSL support
     */
    PHANTOMJS_SSL_PROTOCOL,

    /**
     * Comma-separated list of exception classes that should produce a compromised test in the reports.
     */
    SERENITY_COMPROMISED_ON,

    /**
     * Comma-separated list of exception classes that should produce a skipped test in the reports.
     */
    SERENITY_SKIPPED_ON,

    /**
     * Comma-separated list of exception classes that should produce an error in the reports.
     */
    SERENITY_ERROR_ON,

    /**
     * Comma-separated list of exception classes that should produce a failure in the reports.
     */
    SERENITY_FAIL_ON,

    /**
     *  If batch testing is being used in a multithreaded environment, this is the number of forks that will be created.
     */
    SERENITY_FORK_COUNT,

    /**
     *  If batch testing is being used in a multithreaded environment, this is the executing fork number.
     */
    SERENITY_FORK_NUMBER,

    /**
     * Comma-separated list of exception classes that should produce a pending test in the reports.
     */
    SERENITY_PENDING_ON,

    /**
     * If set to true, add a tag for test failures, based on the error type and message
     */
    SERENITY_TAG_FAILURES,

    /**
     * A comma-separated list of tag types for which human-readable report names will be generated.
     */
    SERENITY_LINKED_TAGS,

    /**
     * Should we assume that collections of webdriver elements are already on the page, or if we should wait for them to be available.
     * This property takes two values: Optimistic or Pessimistic. Optimistic means that the elements are assumed to be on the screen, and will be
     * loaded as-is immediately. This is the normal WebDriver behavior.
     * For applications with lots of ASynchronous activity, it is often better to wait until the elements are visible before using them. The Pessimistic
     * mode waits for at least one element to be visible before proceeding.
     * For legacy reasons, the default strategy is Pessimistic.
     */
    SERENITY_WEBDRIVER_COLLECTION_LOADING_STRATEGY("serenity.webdriver.collection_loading_strategy"),

    /**
     * Serenity will try to download drivers not present on the system.
     * If you don't want this behaviour, set this property to false
     */
    AUTOMATIC_DRIVER_DOWNLOAD,

    /**
     * If the Gecko Driver is on the system path, it will be used (with Marionnette) by default.
     * If you want to use the old-style Firefox driver, but have gecko on the system path,
     * then set this property to false.
     */
    USE_GECKO_DRIVER,

    /**
     * Use this property to pass options to Marionette using the 'moz:firefoxOptions' capability option.
     */
    GECKO_FIREFOX_OPTIONS,

    /**
     * Use this property to specify the maximum number of times to rerun the failing tests.
     */
    TEST_RETRY_COUNT,

    /**
     * Use this property to specify the maximum number of times to rerun the failing tests for cucumber tests.
     */
    TEST_RETRY_COUNT_CUCUMBER,

    /**
     * Record failures to a file specified by property rerun.failures.file or rerun.xml in current directory
     */
    RECORD_FAILURES,

    /**
     * Replay failures from a file specified by property rerun.failures.file or rerun.xml in current directory
     */
    REPLAY_FAILURES,

    /**
     * Location of the directory where the failure files are recorded.
     */
    RERUN_FAILURES_DIRECTORY,

    /**
     * Provide a text that distinguishes tests run in a particular environment or context from the same test
     * run in a different environment or context.
     */
    CONTEXT,

    /**
     * By default, new @Steps libraries are made as new instances, unless declared `shared`, in which case they are
     * cached by type. Use this property to make Serenity use the older strategy, which was to default to 'shared' and
     * only create new instances for step libraries with the uniqueInstance attribute.
     * Possible values are: default (the default value), and legacy.
     */
    STEP_CREATION_STRATEGY,

    /**
     * Instruct the SafariDriver to delete all existing session data when starting a new session (true by default)
     */
    SAFARI_USE_CLEAN_SESSION,

    /**
     * Activate headless mode for chrome or firefox
     */
    HEADLESS_MODE,

    /**
     * Where to find images used in the reports (defaults to src/test/resourcesFC/assets)
     */
    REPORT_ASSETS_DIRECTORY,

    FIREFOX_LOG_LEVEL,

    /**
     * Unique device identifier of the connected physical device used for appium tests
     */
    APPIUM_UDID("appium.udid"),

    /**
     * The device name used for Appium tests
     */
    APPIUM_DEVICE_NAME("appium.deviceName"),

    /**
     * (Experimental) Specifies a list of devices to be used for parallel testing.
     * Will only be used if manage.appium.servers is set to true
     */
    APPIUM_DEVICE_NAMES("appium.deviceNames"),

    /**
     * Should Serenity Manage your appium servers for you
     */
    MANAGE_APPIUM_SERVERS,

    /**
     * Set to true to activate the AcceptInsecureCertificates options for Chrome and Firefox.
     */
    ACCEPT_INSECURE_CERTIFICATES,

    /**
     * The character to be used to separate firefox preference key-value pairs when using the "firefox.preferences" property.
     * By default this is a semicolon, but sometimes semicolon appears in the preference values.
     */
    FIREFOX_PREFERENCE_SEPARATOR,

    /**
     * Disable Webdriver integration. Turn this off to avoid Serenity loading WebDriver classes unnecessarily.
     */
    SERENITY_WEBDRIVER_INTEGRATION,

    /**
     * When creating steps that contain references to other steps serenity does a recursion check to prevent cyclic references.
     * This property determines how many levels deep the step classes can be nested before it triggers a recursion exception.
     * By default it is set to 32, but can be increased if you start getting RecursiveOrCyclicStepLibraryReferenceException
     * due to step nesting rather than actual infinite recursion.
     */
    SERENITY_MAXIMUM_STEP_NESTING_DEPTH,

    /**
     * The maximum number of entries to appear on report scoreboards (default 5)
     */
    REPORT_SCOREBOARD_SIZE,

    /**
     * What tag types should be listed in the report summary
     */
    REPORT_TAGTYPES,

    MAX_FREQUENT_FAILURES,

    CUCUMBER_PRETTY_FORMAT_TABLES,

    /**
     * How many days before a manually configured test result expires and goes back to pending.
     */
    MANUAL_RESULT_EXPIRY_LIMIT,

    /**
     * Set this property to true if you don't want Serenity to try to instrument tasks for you.
     */
    MANUAL_TASK_INSTRUMENTATION,

    /**
     * The title to appear in the tag type table in the email reports
     */
    REPORT_TAGTYPE_TITLE
    ;

    private String propertyName;
    public static final int DEFAULT_HEIGHT = 700;
    public static final int DEFAULT_WIDTH = 960;

    public static final String DEFAULT_HISTORY_DIRECTORY = "history";


    private final Logger logger = LoggerFactory.getLogger(ThucydidesSystemProperty.class);

    ThucydidesSystemProperty(final String propertyName) {
        this.propertyName = propertyName;
    }

    ThucydidesSystemProperty() {
        this.propertyName = name().replaceAll("_",".").toLowerCase();
    }

    public String getPropertyName() {
        return propertyName;
    }

    @Override
    public String toString() {
        return propertyName;
    }

    public String from(EnvironmentVariables environmentVariables) {
        return from(environmentVariables, null);
    }

    private Optional<String> legacyPropertyValueIfPresentIn(EnvironmentVariables environmentVariables) {
        String legacyValue = environmentVariables.getProperty(withLegacyPrefix(getPropertyName()));
        if (StringUtils.isNotEmpty(legacyValue)) {
            logger.warn("Legacy property format detected for {}, please use the serenity.* format instead.",getPropertyName());
        }
        return Optional.ofNullable(legacyValue);
    }

    private String withLegacyPrefix(String propertyName) {
        return propertyName.replaceAll("serenity.", "thucydides.");
    }

    private String withSerenityPrefix(String propertyName) {
        return propertyName.replaceAll("thucydides.", "serenity.");
    }

    public String preferredName(){
        return withSerenityPrefix(getPropertyName());
    }

    public List<String> legacyNames(){
        List<String> names = new ArrayList<>(1);
        names.add(withLegacyPrefix(getPropertyName()));
        return names;
    }

    public String from(EnvironmentVariables environmentVariables, String defaultValue) {
        Optional<String> newPropertyValue
                = Optional.ofNullable(environmentVariables.getProperty(withSerenityPrefix(getPropertyName())));

        if (isDefined(newPropertyValue)) {
            return newPropertyValue.get();
        } else {
            Optional<String> legacyValue = legacyPropertyValueIfPresentIn(environmentVariables);
            return (isDefined(legacyValue)) ? legacyValue.get() : defaultValue;
        }
    }

    private boolean isDefined(Optional<String> newPropertyValue) {
        return newPropertyValue.isPresent() && StringUtils.isNotEmpty(newPropertyValue.get());
    }

    public int integerFrom(EnvironmentVariables environmentVariables) {
        return integerFrom(environmentVariables,0);
    }

    public int integerFrom(EnvironmentVariables environmentVariables, int defaultValue) {
        Optional<String> newPropertyValue
                = Optional.ofNullable(environmentVariables.getProperty(withSerenityPrefix(getPropertyName())));

        if (isDefined(newPropertyValue)) {
            return Integer.parseInt(newPropertyValue.get().trim());
        } else {
            Optional<String> legacyValue = legacyPropertyValueIfPresentIn(environmentVariables);
            return (isDefined(legacyValue)) ? Integer.parseInt(legacyValue.get().trim()) : defaultValue;
        }
    }

    public Boolean booleanFrom(EnvironmentVariables environmentVariables) {
        return booleanFrom(environmentVariables, false);
    }

    public Boolean booleanFrom(EnvironmentVariables environmentVariables, Boolean defaultValue) {
        if (environmentVariables == null) { return defaultValue; }

        Optional<String> newPropertyValue
                = Optional.ofNullable(environmentVariables.getProperty(withSerenityPrefix(getPropertyName())));

        if (isDefined(newPropertyValue)) {
            return Boolean.valueOf(newPropertyValue.get().trim());
        } else {
            Optional<String> legacyValue = legacyPropertyValueIfPresentIn(environmentVariables);
            return (isDefined(legacyValue)) ? Boolean.valueOf(legacyValue.get().trim()) : defaultValue;
        }
    }

    public boolean isDefinedIn(EnvironmentVariables environmentVariables) {
        return StringUtils.isNotEmpty(from(environmentVariables));
    }

}
