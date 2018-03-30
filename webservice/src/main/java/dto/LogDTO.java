package dto;

import java.io.Serializable;

public class LogDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String currencyCode;
    private String requestDate;
    private String requestTime;
    private String clientIP;
    private String errorDescription;

    public String getCurrencyCode() {
        return currencyCode;
    }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getRequestDate() {
        return requestDate;
    }
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestTime() {
        return requestTime;
    }
    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getClientIP() {
        return clientIP;
    }
    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("LogDTO = [");
        builder.append(" currencyCode = ");
        builder.append(currencyCode);
        builder.append(",  requestDate = ");
        builder.append(requestDate);
        builder.append(",  requestTime = ");
        builder.append(requestTime);
        builder.append(",  clientIP = ");
        builder.append(clientIP);
        builder.append(",  errorDescription = ");
        builder.append(errorDescription);
        builder.append(']');
        builder.append(super.toString());
        return builder.toString();
    }
}
