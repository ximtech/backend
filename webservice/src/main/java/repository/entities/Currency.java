package repository.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "ExtCurrency")
public class Currency implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Pattern(regexp = "^[A-Z]{3}$")
    @Column(name = "Code")
    private String code;

    @NotBlank
    @Pattern(regexp = "^\\d{3}$")
    @Column(name = "Num")
    private String num;

    @NotNull
    @Column(name = "E")
    private String e;

    @NotBlank
    @Column(name = "Currency")
    private String currency;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getNum() {
        return num;
    }
    public void setNum(String num) {
        this.num = num;
    }

    public String getE() {
        return e;
    }
    public void setE(String e) {
        this.e = e;
    }

    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Currency = [");
        builder.append("id = ");
        builder.append(id);
        builder.append(",  code = ");
        builder.append(code);
        builder.append(",  num = ");
        builder.append(num);
        builder.append(",  e = ");
        builder.append(e);
        builder.append(",  currency = ");
        builder.append(currency);
        builder.append(']');
        builder.append(super.toString());
        return builder.toString();
    }
}
