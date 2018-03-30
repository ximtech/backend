package repository.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "ExtLog")
public class Log implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "Code")
    private String currencyCode;

    @Column(name = "RequestDate")
    @Temporal(TemporalType.DATE)
    private Date requestDate;

    @Column(name = "RequestTime")
    @Temporal(TemporalType.TIME)
    private Date requestTime;

    @NotNull
    @Column(name = "IP")
    private String clientIP;

    @NotBlank
    @Column(name = "ErrorDescription")
    private String errorDescription;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Date getRequestDate() {
        return requestDate;
    }
    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getRequestTime() {
        return requestTime;
    }
    public void setRequestTime(Date requestTime) {
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
        builder.append("Log = [");
        builder.append("id = ");
        builder.append(id);
        builder.append(",  currencyCode = ");
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
