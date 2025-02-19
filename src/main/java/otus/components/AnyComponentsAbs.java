package components;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import actions.CommonActions;
import annotations.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public abstract class AnyComponentsAbs<T> extends CommonActions<T> {

  protected Actions actions;

  public AnyComponentsAbs(WebDriver driver) {
    super(driver);
    actions = new Actions(driver);
  }

  public WebElement getComponentEntity() {
    return $(getComponentLocator());
  }

  private By getComponentLocator() {
    Component component = getClass().getAnnotation(Component.class);

    if (component != null) {
      String value = component.value();

      String searchStrategy = "";

      Pattern pattern = Pattern.compile("^(\\w+):.*?");
      Matcher matcher = pattern.matcher(value);
      if (matcher.find()) {
        searchStrategy = matcher.group(1).toLowerCase(Locale.ROOT);
      }

      switch (searchStrategy) {
        case "xpath":
          return By.xpath(value.replace(String.format("%s:", searchStrategy), ""));
        case "id":
          return By.id(value.replace(String.format("%s:", searchStrategy), ""));
      }
    }

    return null;
  }
}
