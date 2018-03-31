package repository;

import static helpers.CurrencyHelper.*;
import static org.junit.Assert.*;

import errors.CurrencyNotFoundException;
import helpers.CurrencyHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import repository.entities.Currency;
import repository.interfaces.CurrencyDAO;
import springbootweb.rest.RestApplication;

@ContextConfiguration(classes = RestApplication.class)
@ActiveProfiles("test")
@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
public class CurrencyDAOImplTest {

    @Autowired
    private CurrencyDAO currencyDAO;

    @Before
    public void setUp() throws Exception {
        currencyDAO.save(getTestCurrency());
    }

    @Test
    public void update() throws Exception {
        Currency currency = findCurrency(TEST_CURRENCY_CODE_1);
        currency.setCode(TEST_CURRENCY_CODE_2);
        currencyDAO.update(currency);
        currency = findCurrency(TEST_CURRENCY_CODE_2);
        validateCurrency(currency, TEST_CURRENCY_CODE_2);
    }

    @Test
    public void findByCode() throws Exception {
        Currency currency = findCurrency(TEST_CURRENCY_CODE_1);
        validateCurrency(currency, TEST_CURRENCY_CODE_1);
    }

    @Test(expected = CurrencyNotFoundException.class)
    public void delete() throws Exception {
        Currency currency = findCurrency(TEST_CURRENCY_CODE_1);
        currencyDAO.delete(currency);
        currencyDAO.findByCode(TEST_CURRENCY_CODE_1);
    }

    private Currency findCurrency(String code) {
        return currencyDAO.findByCode(code);
    }

    private void validateCurrency(Currency currency, String code) {
        assertNotNull("Test currency can't be found", currency);
        assertEquals("Currency codes must be equals", code, currency.getCode());
    }
}