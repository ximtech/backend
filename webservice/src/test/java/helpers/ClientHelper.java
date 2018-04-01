package helpers;

import static constants.Constants.SUCCESS;
import static helpers.CurrencyHelper.TEST_CURRENCY_CODE_1;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import dto.CurrencyResponse;
import repository.entities.Log;

public class ClientHelper {
    private static final String TEST_IP = "Test_IP";

    public static CurrencyResponse getCurrencyResponse() {
        CurrencyResponse response = new CurrencyResponse();
        response.setCurrencyCode(TEST_CURRENCY_CODE_1);
        response.setClientIP(TEST_IP);
        response.setErrorMessage(SUCCESS);
        return response;
    }

    public static Log getTestLog() {
        Log log = new Log();
        log.setCurrencyCode(TEST_CURRENCY_CODE_1);
        log.setClientIP(TEST_IP);
        log.setErrorDescription(SUCCESS);
        return log;
    }

    public static List<Log> getLogList() {
        Log log = new Log();
        log.setCurrencyCode(TEST_CURRENCY_CODE_1);
        log.setRequestDate(new Date());
        log.setRequestTime(new Date());
        log.setClientIP(TEST_IP);
        log.setErrorDescription(SUCCESS);
        return Collections.singletonList(log);
    }
}
