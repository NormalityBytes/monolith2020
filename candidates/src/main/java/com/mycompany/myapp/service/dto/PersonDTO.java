package com.mycompany.myapp.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Person} entity.
 */
public class PersonDTO implements Serializable {
    
    private Long id;

    @Size(max = 20)
    private String prefix;

    @NotNull
    @Size(max = 30)
    private String vorname;

    @NotNull
    @Size(max = 30)
    private String nachname;

    @Size(max = 20)
    private String suffix;

    private Instant geburtstag;


    private Long unternehmenId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Instant getGeburtstag() {
        return geburtstag;
    }

    public void setGeburtstag(Instant geburtstag) {
        this.geburtstag = geburtstag;
    }

    public Long getUnternehmenId() {
        return unternehmenId;
    }

    public void setUnternehmenId(Long unternehmenId) {
        this.unternehmenId = unternehmenId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonDTO)) {
            return false;
        }

        return id != null && id.equals(((PersonDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PersonDTO{" +
            "id=" + getId() +
            ", prefix='" + getPrefix() + "'" +
            ", vorname='" + getVorname() + "'" +
            ", nachname='" + getNachname() + "'" +
            ", suffix='" + getSuffix() + "'" +
            ", geburtstag='" + getGeburtstag() + "'" +
            ", unternehmenId=" + getUnternehmenId() +
            "}";
    }
}
