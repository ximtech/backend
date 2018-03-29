package dto;

import java.io.Serializable;

public class CurrencyRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String currencyCode;
    private String clientIP;

    public String getCurrencyCode() {
        return currencyCode;
    }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getClientIP() {
        return clientIP;
    }
    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }
}
