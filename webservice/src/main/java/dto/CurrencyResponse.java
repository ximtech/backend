package dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String currencyCode;
    private String currencyNum;
    private String currencyE;
    private String currencyFullName;
    private List<LogDTO> logList;
    private String clientIP;
    private String errorMessage;

    public String getCurrencyCode() {
        return currencyCode;
    }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyNum() {
        return currencyNum;
    }
    public void setCurrencyNum(String currencyNum) {
        this.currencyNum = currencyNum;
    }

    public String getCurrencyE() {
        return currencyE;
    }
    public void setCurrencyE(String currencyE) {
        this.currencyE = currencyE;
    }

    public String getCurrencyFullName() {
        return currencyFullName;
    }
    public void setCurrencyFullName(String currencyFullName) {
        this.currencyFullName = currencyFullName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<LogDTO> getLogList() {
        return logList;
    }
    public void setLogList(List<LogDTO> logList) {
        this.logList = logList;
    }

    public String getClientIP() {
        return clientIP;
    }
    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CurrencyResponse = [");
        builder.append(" currencyCode = ");
        builder.append(currencyCode);
        builder.append(",  currencyNum = ");
        builder.append(currencyNum);
        builder.append(",  currencyE = ");
        builder.append(currencyE);
        builder.append(",  currencyFullName = ");
        builder.append(currencyFullName);
        builder.append(",  logList = ");
        builder.append(logList);
        builder.append(",  clientIP = ");
        builder.append(clientIP);
        builder.append(",  errorMessage = ");
        builder.append(errorMessage);
        builder.append(']');
        builder.append(super.toString());
        return builder.toString();
    }
}
