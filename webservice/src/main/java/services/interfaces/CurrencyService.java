package services.interfaces;

import dto.CurrencyRequest;
import dto.CurrencyResponse;
import org.springframework.stereotype.Component;

@Component
public interface CurrencyService {

    void performCleanUp();

    void getDataFromWikiPage();

    CurrencyResponse getCurrencyDataByCode(CurrencyRequest request);
}
