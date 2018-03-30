package services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.CurrencyResponse;
import dto.LogDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.interfaces.ClientDAO;
import repository.entities.Log;
import services.interfaces.ClientService;

@Service
public class ClientServiceImpl implements ClientService {
    private static final Logger LOGGER = Logger.getLogger(ClientServiceImpl.class);

    private ClientDAO clientDAO;

    @Override
    public void performCleanUp() {
        LOGGER.info("Performing Log Table cleanup");
        clientDAO.clear();
    }

    @Override
    public void persistClientData(CurrencyResponse response) {
        Log log = new Log();
        log.setCurrencyCode(response.getCurrencyCode());
        log.setRequestDate(new Date());
        log.setRequestTime(new Date());
        log.setClientIP(response.getClientIP());
        log.setErrorDescription(response.getErrorMessage());

        try {
            clientDAO.save(log);
        } catch (Exception e) {
            LOGGER.info("Exception trying to save client log to DB " + e.getMessage());
            response.setErrorMessage(e.getMessage());
        }
    }

    @Override
    public List<LogDTO> getAllClientDataFromDB() {
        List<Log> listOfLogs = clientDAO.getAllLogEntries();
        return convertLogList(listOfLogs);
    }

    private List<LogDTO> convertLogList(List<Log> logList) {
        List<LogDTO> result = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        for (Log log : logList) {
            LogDTO dto = new LogDTO();
            dto.setCurrencyCode(log.getCurrencyCode());
            dto.setClientIP(log.getClientIP());
            dto.setErrorDescription(log.getErrorDescription());
            dto.setRequestDate(dateFormat.format(log.getRequestDate()));
            dto.setRequestTime(timeFormat.format(log.getRequestTime()));
            result.add(dto);
        }
        return result;
    }

    @Autowired
    public void setClientDAO(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }
}
