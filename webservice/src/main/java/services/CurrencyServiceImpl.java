package services;

import static constants.Constants.SUCCESS;
import static constants.Constants.WIKI_PAGE_URL;

import java.util.Date;

import dto.CurrencyRequest;
import dto.CurrencyResponse;
import errors.ValidationException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ClientDAO;
import repository.CurrencyDAO;
import repository.entities.Currency;
import repository.entities.Log;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private static final Logger LOGGER = Logger.getLogger(CurrencyServiceImpl.class);

    private CurrencyDAO currencyDAO;
    private ClientDAO clientDAO;
    private WikiPageService pageParser;

    @Override
    public void performCleanUp() {
        LOGGER.info("Performing DB cleanup");
        currencyDAO.clear();
        clientDAO.clear();
    }

    @Override
    public void getDataFromWikiPage() {
        LOGGER.info("Getting currency data from Wiki page");
        try {
            pageParser.parseCurrencyDataFromWikiPage(WIKI_PAGE_URL);
        } catch (Exception e) {
            LOGGER.error("Exception trying to get currency data from wikipedia page " + e.getMessage());
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
                response.setRequestDateAndTime(new Date());
                response.setErrorMessage(e.getMessage());
            }

        return response;
    }

    @Override
    public void persistClientData(CurrencyResponse response) {
        Log log = new Log();
        log.setCurrencyCode(response.getCurrencyCode());
        log.setRequestDateTime(response.getRequestDateAndTime());
        log.setClientIP(response.getClientIP());
        log.setErrorrDescription(response.getErrorMessage());

        try {
            clientDAO.save(log);
        } catch (Exception e) {
            LOGGER.info("Exception trying to save client log to DB " + e.getMessage());
            response.setErrorMessage(e.getMessage());
        }
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
        response.setRequestDateAndTime(new Date());
        response.setErrorMessage(SUCCESS);
    }

    @Autowired
    public void setCurrencyDAO(CurrencyDAO currencyDAO) {
        this.currencyDAO = currencyDAO;
    }

    @Autowired
    public void setClientDAO(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Autowired
    public void setPageParser(WikiPageService pageParser) {
        this.pageParser = pageParser;
    }
}
