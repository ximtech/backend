package helpers;

import static constants.Constants.SUCCESS;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import dto.CurrencyResponse;
import repository.entities.Log;

public class ClientHelper {

    private static final String TEST_CURRENCY_CODE = "Test Code";
    private static final String TEST_IP = "Test IP";

    public static CurrencyResponse getCurrencyResponse() {
        CurrencyResponse response = new CurrencyResponse();
        response.setCurrencyCode(TEST_CURRENCY_CODE);
        response.setClientIP(TEST_IP);
        response.setErrorMessage(SUCCESS);
        return response;
    }

    public static List<Log> getLogList() {
        Log log = new Log();
        log.setCurrencyCode(TEST_CURRENCY_CODE);
        log.setRequestDate(new Date());
        log.setRequestTime(new Date());
        log.setClientIP(TEST_IP);
        log.setErrorDescription(SUCCESS);
        return Collections.singletonList(log);
    }
}
