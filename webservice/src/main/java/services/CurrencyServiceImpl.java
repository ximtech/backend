package services;

import static constants.Constants.NO_VALUE;
import static constants.Constants.SUCCESS;
import static constants.Constants.WIKI_PAGE_URL;

import dto.CurrencyRequest;
import dto.CurrencyResponse;
import errors.ValidationException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.entities.Currency;
import repository.interfaces.CurrencyDAO;
import services.interfaces.CurrencyService;
import services.interfaces.WikiPageService;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private static final Logger LOGGER = Logger.getLogger(CurrencyServiceImpl.class);

    private CurrencyDAO currencyDAO;
    private WikiPageService pageParser;

    @Override
    public void performCleanUp() {
        LOGGER.info("Performing Currency Table cleanup");
        currencyDAO.clear();
    }

    @Override
    public void getDataFromWikiPage() {
        LOGGER.info("Getting currency data from Wiki page");
        try {
            pageParser.parseCurrencyDataFromWikiPage(WIKI_PAGE_URL);
        } catch (Exception e) {
            LOGGER.error("Exception trying to get currency data from wikipedia page " + e.getMessage(), e);
        }
        LOGGER.info("All data has been successfully persisted");
    }

    @Override
    public CurrencyResponse getCurrencyDataByCode(CurrencyRequest request) {
        CurrencyResponse response = new CurrencyResponse();
        response.setClientIP(request.getClientIP());

        String currencyCode = request.getCurrencyCode();
            try {
                validateCurrencyCode(currencyCode);
                Currency currency = currencyDAO.findByCode(currencyCode);
                fillResponseFromEntity(response, currency);

            } catch (Exception e) {
                LOGGER.info("Exception trying to get currency " + e.getMessage());
                response.setCurrencyCode(request.getCurrencyCode());
                response.setErrorMessage(e.getMessage());
                setEmptyValuesToResponse(response);
            }

        return response;
    }

    private void validateCurrencyCode(String code) {
        if (code == null || code.isEmpty()) {
            throw new ValidationException("Currency Code can't be empty");
        }
        if (!code.toUpperCase().matches("^[A-Z]{3}$")) {
            throw new ValidationException("Currency Code is not valid");
        }
    }

    private void fillResponseFromEntity(CurrencyResponse response, Currency currency) {
        response.setCurrencyCode(currency.getCode());
        response.setCurrencyNum(currency.getNum());
        response.setCurrencyE(currency.getE());
        response.setCurrencyFullName(currency.getCurrency());
        response.setErrorMessage(SUCCESS);
    }

    private void setEmptyValuesToResponse(CurrencyResponse response) {
        response.setCurrencyNum(NO_VALUE);
        response.setCurrencyE(NO_VALUE);
        response.setCurrencyFullName(NO_VALUE);
    }

    @Autowired
    public void setCurrencyDAO(CurrencyDAO currencyDAO) {
        this.currencyDAO = currencyDAO;
    }

    @Autowired
    public void setPageParser(WikiPageService pageParser) {
        this.pageParser = pageParser;
    }
}
