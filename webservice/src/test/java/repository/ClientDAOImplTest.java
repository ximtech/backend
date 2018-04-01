package repository;

import static helpers.CurrencyHelper.TEST_CURRENCY_CODE_1;
import static helpers.CurrencyHelper.TEST_CURRENCY_CODE_2;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import errors.LogEntryNotFoundException;
import helpers.ClientHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import repository.entities.Log;
import repository.interfaces.ClientDAO;
import springbootweb.rest.RestApplication;

@ContextConfiguration(classes = RestApplication.class)
@ActiveProfiles("test")
@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
public class ClientDAOImplTest {

    @Autowired
    private ClientDAO clientDAO;

    @Before
    public void setUp() throws Exception {
        clientDAO.save(ClientHelper.getTestLog());
    }

    @Test
    public void update() throws Exception {
        Log log = findLog(TEST_CURRENCY_CODE_1);
        log.setCurrencyCode(TEST_CURRENCY_CODE_2);
        clientDAO.update(log);
        log = clientDAO.findByCode(TEST_CURRENCY_CODE_2);
        validateLog(log, TEST_CURRENCY_CODE_2);
    }

    @Test
    public void findByCode() throws Exception {
        Log log = findLog(TEST_CURRENCY_CODE_1);
        validateLog(log, TEST_CURRENCY_CODE_1);
    }

    @Test
    public void getAllLogEntries() throws Exception {
        List<Log> list = clientDAO.getAllLogEntries();
        assertTrue("List shouldn't be empty", list.size() > 0);
    }

    @Test(expected = LogEntryNotFoundException.class)
    public void delete() throws Exception {
        Log log = findLog(TEST_CURRENCY_CODE_1);
        clientDAO.delete(log);
        clientDAO.findByCode(TEST_CURRENCY_CODE_1);
    }

    private Log findLog(String code) {
        return clientDAO.findByCode(code);
    }

    private void validateLog(Log log, String code) {
        assertNotNull("Test log can't be found", log);
        assertEquals("Currency codes must be equals", code, log.getCurrencyCode());
    }
}