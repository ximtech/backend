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

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("CurrencyRequest = [");
        builder.append(" currencyCode = ");
        builder.append(currencyCode);
        builder.append(",  clientIP = ");
        builder.append(clientIP);
        builder.append(']');
        builder.append(super.toString());
        return builder.toString();
    }
}
