package repository.entities;

import java.io.Serializable;
import java.sql.Timestamp;
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

    @NotNull
    @Column(name = "RequestDateTime")
    @Temporal(TemporalType.DATE)
    private Date requestDateTime;

    @NotNull
    @Column(name = "IP")
    private String clientIP;

    @Column(name = "ErrorDescription")
    private String errorrDescription;

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

    public Date getRequestDateTime() {
        return requestDateTime;
    }
    public void setRequestDateTime(Date requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public String getClientIP() {
        return clientIP;
    }
    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public String getErrorrDescription() {
        return errorrDescription;
    }
    public void setErrorrDescription(String errorrDescription) {
        this.errorrDescription = errorrDescription;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Log = [");
        builder.append("id = ");
        builder.append(id);
        builder.append(",  currencyCode = ");
        builder.append(currencyCode);
        builder.append(",  requestDateTime = ");
        builder.append(requestDateTime);
        builder.append(",  clientIP = ");
        builder.append(clientIP);
        builder.append(",  errorrDescription = ");
        builder.append(errorrDescription);
        builder.append(']');
        builder.append(super.toString());
        return builder.toString();
    }
}
