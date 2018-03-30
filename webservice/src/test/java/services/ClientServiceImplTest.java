package services;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import dto.LogDTO;
import helpers.ClientHelper;
import org.junit.Before;
import org.junit.Test;
import repository.ClientDAOImpl;
import repository.interfaces.ClientDAO;

public class ClientServiceImplTest {

    private ClientDAO clientDAO;
    private ClientServiceImpl clientService;

    @Before
    public void setUp() throws Exception {
        clientDAO = mock(ClientDAOImpl.class);
        clientService = new ClientServiceImpl();
        clientService.setClientDAO(clientDAO);
    }

    @Test
    public void performCleanUp() throws Exception {
        clientService.performCleanUp();
        verify(clientDAO, times(1)).clear();
    }

    @Test
    public void persistClientData() throws Exception {
        clientService.persistClientData(ClientHelper.getCurrencyResponse());
        verify(clientDAO, times(1)).save(anyObject());
    }

    @Test
    public void getAllClientDataFromDB() throws Exception {
        when(clientDAO.getAllLogEntries()).thenReturn(ClientHelper.getLogList());
        List<LogDTO> list = clientService.getAllClientDataFromDB();
        validateLogResponse(list);
    }

    private void validateLogResponse(List<LogDTO> list) {
        LogDTO dto = list.get(0);
        assertTrue("Date should match the pattern", dto.getRequestDate().matches("^\\d{2}-\\d{2}-\\d{4}$"));
        assertTrue("Time should match the pattern", dto.getRequestTime().matches("^\\d{2}:\\d{2}:\\d{2}$"));
    }
}