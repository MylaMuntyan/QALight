package pages;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

abstract public class ParentPage extends CommonActionWithElements {
    protected String base_url;

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
        base_url = configProperties.base_url().replace("[env]", System.getProperty("env", "aqa"));
    }

    protected void waitChatToBeHide() {
        webDriverWait10
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }

    abstract String getRelativeURL();

    /**
     * Example google.com == google.com -> true
     */
    protected void checkURL() {
        Assert.assertEquals("Invalid page.", base_url + getRelativeURL(), webDriver.getCurrentUrl());
    }

    /**
     * Example google.com == google ->
     */
    protected void checkURLContains() {
        Assert.assertThat("", webDriver.getCurrentUrl(), CoreMatchers.containsString(base_url + getRelativeURL()));
    }

    //https://qa-complexapp.onrender.com/post/63f2c83452a9eb0035c6558d/edit
    //https://qa-complexapp.onrender.com/post/.*/edit
    protected void checkURLWithPattern() {
        String actualURL = webDriver.getCurrentUrl();
        Assert.assertTrue("\nActual URL "+actualURL +" \n"+
                 " Expected URL " + base_url+getRelativeURL() +" \n", actualURL.matches(base_url+getRelativeURL()));
    }

}
