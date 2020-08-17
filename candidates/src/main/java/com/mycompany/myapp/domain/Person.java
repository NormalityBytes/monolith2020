package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Person.
 */
@Entity
@Table(name = "person")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Size(max = 20)
    @Column(name = "prefix", length = 20)
    private String prefix;

    @NotNull
    @Size(max = 30)
    @Column(name = "vorname", length = 30, nullable = false)
    private String vorname;

    @NotNull
    @Size(max = 30)
    @Column(name = "nachname", length = 30, nullable = false)
    private String nachname;

    @Size(max = 20)
    @Column(name = "suffix", length = 20)
    private String suffix;

    @Column(name = "geburtstag")
    private Instant geburtstag;

    @OneToOne

    @MapsId
    @JoinColumn(name = "id")
    private Unternehmen unternehmen;

    @OneToMany(mappedBy = "person")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Kontaktinfo> kontaktinfos = new HashSet<>();

    @OneToMany(mappedBy = "person")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Adresse> adresses = new HashSet<>();

    @OneToOne(mappedBy = "person")
    @JsonIgnore
    private Berater berater;

    @OneToOne(mappedBy = "person")
    @JsonIgnore
    private Kandidat kandidat;

    @OneToOne(mappedBy = "person")
    @JsonIgnore
    private Kontakt kontakt;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public Person prefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getVorname() {
        return vorname;
    }

    public Person vorname(String vorname) {
        this.vorname = vorname;
        return this;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public Person nachname(String nachname) {
        this.nachname = nachname;
        return this;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getSuffix() {
        return suffix;
    }

    public Person suffix(String suffix) {
        this.suffix = suffix;
        return this;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Instant getGeburtstag() {
        return geburtstag;
    }

    public Person geburtstag(Instant geburtstag) {
        this.geburtstag = geburtstag;
        return this;
    }

    public void setGeburtstag(Instant geburtstag) {
        this.geburtstag = geburtstag;
    }

    public Unternehmen getUnternehmen() {
        return unternehmen;
    }

    public Person unternehmen(Unternehmen unternehmen) {
        this.unternehmen = unternehmen;
        return this;
    }

    public void setUnternehmen(Unternehmen unternehmen) {
        this.unternehmen = unternehmen;
    }

    public Set<Kontaktinfo> getKontaktinfos() {
        return kontaktinfos;
    }

    public Person kontaktinfos(Set<Kontaktinfo> kontaktinfos) {
        this.kontaktinfos = kontaktinfos;
        return this;
    }

    public Person addKontaktinfo(Kontaktinfo kontaktinfo) {
        this.kontaktinfos.add(kontaktinfo);
        kontaktinfo.setPerson(this);
        return this;
    }

    public Person removeKontaktinfo(Kontaktinfo kontaktinfo) {
        this.kontaktinfos.remove(kontaktinfo);
        kontaktinfo.setPerson(null);
        return this;
    }

    public void setKontaktinfos(Set<Kontaktinfo> kontaktinfos) {
        this.kontaktinfos = kontaktinfos;
    }

    public Set<Adresse> getAdresses() {
        return adresses;
    }

    public Person adresses(Set<Adresse> adresses) {
        this.adresses = adresses;
        return this;
    }

    public Person addAdresse(Adresse adresse) {
        this.adresses.add(adresse);
        adresse.setPerson(this);
        return this;
    }

    public Person removeAdresse(Adresse adresse) {
        this.adresses.remove(adresse);
        adresse.setPerson(null);
        return this;
    }

    public void setAdresses(Set<Adresse> adresses) {
        this.adresses = adresses;
    }

    public Berater getBerater() {
        return berater;
    }

    public Person berater(Berater berater) {
        this.berater = berater;
        return this;
    }

    public void setBerater(Berater berater) {
        this.berater = berater;
    }

    public Kandidat getKandidat() {
        return kandidat;
    }

    public Person kandidat(Kandidat kandidat) {
        this.kandidat = kandidat;
        return this;
    }

    public void setKandidat(Kandidat kandidat) {
        this.kandidat = kandidat;
    }

    public Kontakt getKontakt() {
        return kontakt;
    }

    public Person kontakt(Kontakt kontakt) {
        this.kontakt = kontakt;
        return this;
    }

    public void setKontakt(Kontakt kontakt) {
        this.kontakt = kontakt;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }
        return id != null && id.equals(((Person) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Person{" +
            "id=" + getId() +
            ", prefix='" + getPrefix() + "'" +
            ", vorname='" + getVorname() + "'" +
            ", nachname='" + getNachname() + "'" +
            ", suffix='" + getSuffix() + "'" +
            ", geburtstag='" + getGeburtstag() + "'" +
            "}";
    }
}
