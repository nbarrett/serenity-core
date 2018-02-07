### Running in Sauce Labs ###
mvn surefire:test -Dtest=WordPressAppTest -Dsaucelabs.url=http://**sauceId**:**sauceKey**@ondemand.saucelabs.com:80/wd/hub -Dsaucelabs.access.key=**sauceKey** -Dsaucelabs.user.id=**sauceId**


### Before Run ###

1. Start appium server ( command line / appium desktop )
2. Create simulator with necessary iOS version and mention same under __appium.platformVersion__  and __appium.deviceName__
3. under **resources** folder, unzip **WordPress.zip** to get **WordPress.app** file needed for automation
<br>


### How To Run From Command Line ###

#### iOS Test Cases ####
1. From command line run below from **/serenity-core/serenity-appium**, make sure to pass complete path where**WordPress.app**is located

   **mvn clean verify -Dappium.app=FULL_PATH_REQUIRED/serenity-core/serenity-appium/src/test/java/integration/resources/WordPress.app**

<br>