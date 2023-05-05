package ru.maruspim.components;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class AldebaranPageComponent {

    // Selenide elements / locator / etc
    SelenideElement pageHeaderText = $(".logo").sibling(0),
            authorizationContainer = $(".login-link"),
            registrationMode = $(".registr"),
            newUserLoginInput = $("#new_login"),
            newUserEmailInput = $("#new_email"),
            newUserPasswordInput = $("#pwd_reg_inp"),
            loginErrorMessageBox = $("[for=new_login][class=error]"),
            emailErrorMessageBox = $("[for=new_email][class=error]"),
            searchField = $("#search");

    // Actions
    public AldebaranPageComponent openMainPage(String pageUrl) {
        open(pageUrl);
        pageHeaderText.shouldHave(text("Электронная библиотека книг"));

        return this;
    }

    public AldebaranPageComponent footerRemoving () {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public AldebaranPageComponent openRegistrationForm () {
        authorizationContainer.shouldHave(text("Вход / Регистрация")).click();;
        registrationMode.shouldHave(text("Регистрация")).click();
        return this;
    }

    public AldebaranPageComponent setNewUserLogin (String newUserLogin) {
        newUserLoginInput.setValue(newUserLogin).pressEnter();

        return this;
    }
    public AldebaranPageComponent setNewUserEmail (String newUserEmail) {
        newUserEmailInput.setValue(newUserEmail).pressEnter();

        return this;
    }

    public AldebaranPageComponent setNewUserPassword (String newUserPassword) {
        newUserPasswordInput.setValue(newUserPassword).pressEnter();

        return this;
    }

    public void errorLoginMessageBoxPresenceChecking(String expectedErrorMessage) {
        loginErrorMessageBox.shouldBe(visible).shouldHave(text(expectedErrorMessage));
    }
    public void errorLoginMessageBoxAbsenceChecking() {
        loginErrorMessageBox.shouldNotBe(visible);

    }

    public void errorEmailMessageBoxPresenceChecking(String expectedErrorMessage) {
        emailErrorMessageBox.shouldBe(visible).shouldHave(text(expectedErrorMessage));

    }

    public void errorEmailMessageBoxAbsenceChecking() {
        emailErrorMessageBox.shouldNotBe(visible);

    }

    public AldebaranPageComponent searchInput (String searchInput) {
        searchField.setValue(searchInput).pressEnter();

        return this;
    }

    public AldebaranPageComponent foundedElementsChecking (List<String> foundedElements) {
        $$(".wooklist p[class=booktitle]").shouldHave(CollectionCondition.texts(foundedElements));

        return this;
    }
}
