package com.epam.lab.specific_elements;

import com.epam.lab.specific_elements.interfaces.IElement;
import org.openqa.selenium.WebElement;

public class Label extends IElement.Element {

    public Label(WebElement webElement) {
        super(webElement);
    }

    public String getText() {
        return webElement.getText();
    }
}
