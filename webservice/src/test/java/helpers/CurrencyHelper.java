package helpers;

import dto.CurrencyRequest;
import repository.entities.Currency;

public class CurrencyHelper {
    private static final String WRONG_CURRENCY_CODE_1 = "aB";
    private static final String WRONG_CURRENCY_CODE_2 = "Wrong";
    private static final String WRONG_CURRENCY_CODE_3 = "123";
    private static final String WRONG_CURRENCY_CODE_4 = "";

    private static final String CORRECT_CURRENCY_CODE_1 = "EUR";
    private static final String CORRECT_CURRENCY_CODE_2 = "rub";
    private static final String CORRECT_CURRENCY_CODE_3 = "UsD";

    private static Currency testCurrency;

    private static final String[] WRONG_REQUESTS;
    private static final String[] CORRECT_REQUESTS;

    static {
        WRONG_REQUESTS = new String[] { WRONG_CURRENCY_CODE_1, WRONG_CURRENCY_CODE_2, WRONG_CURRENCY_CODE_3,
                        WRONG_CURRENCY_CODE_4 };
        CORRECT_REQUESTS = new String[] { CORRECT_CURRENCY_CODE_1, CORRECT_CURRENCY_CODE_2, CORRECT_CURRENCY_CODE_3 };
    }

    static {
        testCurrency = new Currency();
        testCurrency.setCode(CORRECT_CURRENCY_CODE_1);
        testCurrency.setNum("123");
        testCurrency.setE("0");
        testCurrency.setCurrency("Test currency");
    }

    public static Object[] getWrongRequests() {
        return getRequests(WRONG_REQUESTS);
    }

    public static Object[] getCorrectRequests() {
        return getRequests(CORRECT_REQUESTS);
    }

    public static Currency getTestCurrency() {
        return testCurrency;
    }

    private static Object[] getRequests(final String[] requestArray) {
        Object[] result = new Object[requestArray.length];
        for (int i = 0; i < requestArray.length; i++) {
            CurrencyRequest wrongRequest = new CurrencyRequest();
            wrongRequest.setCurrencyCode(requestArray[i]);
            result[i] = wrongRequest;
        }
        return result;
    }
}
