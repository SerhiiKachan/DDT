package com.epam.lab.specific_elements;

import com.epam.lab.specific_elements.interfaces.IElement;
import org.openqa.selenium.WebElement;

public class CheckBox extends IElement.Element {

    public CheckBox(WebElement webElement) {
        super(webElement);
    }

    private boolean isChecked() {
        return webElement.isSelected();
    }

    public void setChecked() {
        if (!this.isChecked())
            this.click();
    }
}
