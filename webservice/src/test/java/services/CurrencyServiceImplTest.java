package services;

import static constants.Constants.SUCCESS;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import dto.CurrencyRequest;
import dto.CurrencyResponse;
import helpers.CurrencyHelper;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import repository.interfaces.CurrencyDAO;
import repository.CurrencyDAOImpl;
import services.interfaces.WikiPageService;

@RunWith(JUnitParamsRunner.class)
public class CurrencyServiceImplTest {

    private CurrencyDAO currencyDAO;
    private WikiPageService pageParser;

    private CurrencyServiceImpl currencyService;

    @Before
    public void setUp() throws Exception {
        currencyService = new CurrencyServiceImpl();
        currencyDAO = mock(CurrencyDAOImpl.class);
        pageParser = mock(WikiPageServiceImpl.class);

        currencyService.setCurrencyDAO(currencyDAO);
        currencyService.setPageParser(pageParser);
    }

    @Test
    public void performCleanUpTest() throws Exception {
        currencyService.performCleanUp();
        verify(currencyDAO, times(1)).clear();
    }

    @Test
    public void getDataFromWikiPageTest() throws Exception {
        currencyService.getDataFromWikiPage();
        verify(pageParser, times(1)).parseCurrencyDataFromWikiPage(anyString());
    }

    @Test
    @Parameters(method = "getIncorrectRequests")
    public void getCurrencyDataByCodeTest1(Object request) throws Exception {
        CurrencyResponse response = currencyService.getCurrencyDataByCode((CurrencyRequest) request);
        assertNotEquals("Error message can't be successful", response.getErrorMessage().equals(SUCCESS));
    }

    @Test
    @Parameters(method = "getCorrectRequests")
    public void getCurrencyDataByCodeTest2(Object request) throws Exception {
        when(currencyDAO.findByCode(anyString())).thenReturn(CurrencyHelper.getTestCurrency());
        CurrencyResponse response = currencyService.getCurrencyDataByCode((CurrencyRequest) request);
        assertEquals("Error message status should be successful", SUCCESS, response.getErrorMessage());
    }

    private Object[] getIncorrectRequests() {
        return CurrencyHelper.getWrongRequests();
    }

    private Object[] getCorrectRequests() {
        return CurrencyHelper.getCorrectRequests();
    }

}