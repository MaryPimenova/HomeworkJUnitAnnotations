package ru.maruspim.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import ru.maruspim.components.AldebaranPageComponent;

public class TestBase {
    AldebaranPageComponent aldebaranPageComponent = new AldebaranPageComponent();
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy="eager";
    }
}
