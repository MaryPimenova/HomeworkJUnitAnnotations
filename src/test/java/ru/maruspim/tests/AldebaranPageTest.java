package ru.maruspim.tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import java.util.List;
import java.util.stream.Stream;


@DisplayName("Testing the Aldebaran e-library Website Page")
public class AldebaranPageTest extends TestBase {
    @BeforeEach
    void openMainPage() {
        aldebaranPageComponent.openMainPage("https://aldebaran.ru/")
                .footerRemoving();
    }

    static Stream<Arguments> searchTestDataProvider() {
        return Stream.of(
                Arguments.of(
                        "Элегантность еж", List.of("Элегантность ёжика", "Элегантность ёжика")
                ),
                Arguments.of(
                        "Чернильное сердце", List.of("Чернильное сердце", "Чернильное сердце", "Чернильная кровь", "Чернильная смерть")
                )
        );
    }
    @MethodSource("searchTestDataProvider")
    @ParameterizedTest(name = "For the entered search query: {0}, books with titles {1} are displayed")
    @DisplayName("Checking for searching books by different word fragments")
    void bookSearchTest(String wordPartInput, List<String> bookTitles) {
        aldebaranPageComponent.searchInput(wordPartInput);
        aldebaranPageComponent.foundedElementsChecking(bookTitles);

    }


    @CsvFileSource(resources = "/wrongLoginValidation.csv", delimiter = '|')
    @ParameterizedTest(name = "For the entered login: {0} an error message is displayed: {1}")
    @DisplayName("Checking for an error message presence when entering an invalid login")
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL")
    })
    void loginErrorMessagePresenceTest(String newUserLogin, String expectedLoginErrorMessage) {
        aldebaranPageComponent.openRegistrationForm()
                .setNewUserLogin(newUserLogin)
                .errorLoginMessageBoxPresenceChecking(expectedLoginErrorMessage);

    }

    @ValueSource(strings = {
            "Hurray18745",
            "babochkaButterfly",
            "LaLaLanding"
    })
    @ParameterizedTest(name = "For the entered login: {0} there is no error message")
    @DisplayName("Checking for an error message absence when entering a valid login")
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL")
    })
    void loginErrorMessageAbsenceTest(String newUserLogin) {
        aldebaranPageComponent.openRegistrationForm()
                .setNewUserLogin(newUserLogin)
                .errorLoginMessageBoxAbsenceChecking();

    }

    @CsvSource(value = {
            " | Укажите свой e-mail",
            "глафира | Вы ввели некорректный e-mail",
            "abcd | Вы ввели некорректный e-mail",
            "abcd@ | Вы ввели некорректный e-mail",
            "abcd@mail | Вы ввели некорректный e-mail",
            "abcd@mail. | Вы ввели некорректный e-mail",
            "abcd@mail.d24 | Вы ввели некорректный e-mail",
            "abcd@mail.363 | Вы ввели некорректный e-mail"
    },
            delimiter = '|')
    @ParameterizedTest(name = "For the entered e-mail: {0} an error message is displayed: {1}")
    @DisplayName("Checking for an error message presence when entering an invalid e-mail")
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL")
    })
    void emailErrorMessagePresenceTest(String newUserEmail, String expectedEmailErrorMessage) {
        aldebaranPageComponent.openRegistrationForm()
                .setNewUserEmail(newUserEmail)
                .errorEmailMessageBoxPresenceChecking(expectedEmailErrorMessage);

    }

    @ValueSource(strings = {
            "Hurray18745@mail.ru",
            "babochkaButterfly@gmail.com",
            "LaLaLanding@mail.d"
    })
    @ParameterizedTest(name = "For the entered e-mail: {0} there is no error message")
    @DisplayName("Checking for an error message absence when entering a valid e-mail")
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL")
    })
    void emailErrorMessageAbsenceTest(String newUserEmail) {
        aldebaranPageComponent.openRegistrationForm()
                .setNewUserEmail(newUserEmail)
                .errorEmailMessageBoxAbsenceChecking();

    }

    @ValueSource(strings = {
            "gdrfh623",
            "12^74hfa",
            "---!!!.."
    })
    @ParameterizedTest(name = "For the entered password: {0} there is no error message")
    @DisplayName("Checking if there is no error message when entering a password")
    @Tags({
            @Tag("WEB"),
            @Tag("NORMAL")
    })

    void passwordErrorMessageTest(String newUserPassword) {
        aldebaranPageComponent.openRegistrationForm()
                .setNewUserPassword(newUserPassword);

    }

}
