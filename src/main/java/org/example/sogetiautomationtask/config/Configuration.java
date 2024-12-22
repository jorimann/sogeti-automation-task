package org.example.sogetiautomationtask.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties","classpath:config-default.properties", "classpath:allure.properties"})
public interface Configuration extends Config {

    @Key("ui.baseurl")
    String uiBaseUrl();

    @Key("ui.browsertype")
    String browserType();

    @Key("ui.headless")
    Boolean headless();

    @Key("ui.viewport.width")
    Integer viewportWidth();

    @Key("ui.viewport.height")
    Integer viewportHeight();

    @Key("ui.device.scale.factor")
    @DefaultValue("1")
    Integer deviceScaleFactor();

    @Key("ui.slow.motion")
    Integer slowMotion();

    @Key("ui.timeout")
    Integer timeout();

    @Key("ui.timeout.page.load")
    Integer timeoutPageLoad();

    @Key("ui.wait.element")
    Integer waitElement();

    @Key("ui.wait.for.new.page")
    Integer waitForNewPage();

    @Key("ui.tracing.enabled")
    Boolean tracingEnabled();

    @Key("api.baseUrl")
    String apiBaseUrl();

    @Key("allure.results.directory")
    String allureResultsDirectory();
}
