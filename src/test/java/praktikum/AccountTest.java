package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AccountTest {

    private final String personName;
    private final boolean isNameValidation;

    public AccountTest(String personName, boolean isNameValidation) {
        this.personName = personName;
        this.isNameValidation = isNameValidation;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {"Р Ж", true}, // кириллица, 3 символа true
                {"Робинзон Крузотесто", true}, // кириллица, 19 символов true
                {"Робинзон Крузо", true}, // кириллица, 14 символов true
                {"Jon Sina", true}, // латиница, 13 символов true
                {"Робизонтестов Sina", true}, // латиница+кириллица, 18 символов
                {null, false}, // null false
                {"", false}, // пустая строка false
                {" ", false}, // строка из пробела false
                {"РобинзонКрузо ", false}, // пробел в конце строки false
                {" РобинзонКрузо", false}, // пробел в начале строки false
                {"Робинзон  Крузо", false}, // 2 пробела подряд внутри строки false
                {"Ким Чен Ын", false}, // более 2 слов false
                {"РобинзонКрузо", false}, // отсутствие пробела false
                {"Сан-Мартин Виолетта-Виктория", false}, // дефис false
                {"А1 П2", false}, // цифры+кириллица false
                {"1Z 2J", false}, // цифры+латиница false
                {"11 22", false}, // цифры false
                {"&!'#$% *()^@", false}, // спецсимволы false
                {"Робинзон? Крузо№", false}, // кириллица+спецсимволы false
                {"Noize #MC", false}, // латиница+спецсимволы false
                {"Ян", false}, // 2 символа false
                {"Робинзонте Крузотест", false} // 20 символов false
        };
    }

    @Test
    @DisplayName("Проверка валидации имени и фамилии")
    public void checkNameToEmbossTest() {
        Account account = new Account(personName);
        boolean actual = account.checkNameToEmboss();
        assertEquals(isNameValidation, actual);
    }
}
