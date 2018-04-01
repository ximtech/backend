package services;

import static constants.Constants.DATE_FORMAT;
import static constants.Constants.TIME_FORMAT;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.CurrencyResponse;
import dto.LogDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.entities.Log;
import repository.interfaces.ClientDAO;
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
        Log log = populateLogParams(response);
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
        for (Log log : logList) {
            LogDTO dto = populateLogDtoParams(log);
            result.add(dto);
        }
        return result;
    }

    private Log populateLogParams(CurrencyResponse response) {
        Log log = new Log();
        log.setCurrencyCode(response.getCurrencyCode());
        log.setRequestDate(new Date());
        log.setRequestTime(new Date());
        log.setClientIP(response.getClientIP());
        log.setErrorDescription(response.getErrorMessage());
        return log;
    }

    private LogDTO populateLogDtoParams(Log log) {
        LogDTO dto = new LogDTO();
        dto.setCurrencyCode(log.getCurrencyCode());
        dto.setClientIP(log.getClientIP());
        dto.setErrorDescription(log.getErrorDescription());
        dto.setRequestDate(DATE_FORMAT.format(log.getRequestDate()));
        dto.setRequestTime(TIME_FORMAT.format(log.getRequestTime()));
        return dto;
    }

    @Autowired
    public void setClientDAO(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }
}
