/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anja
 */
@Entity
@Table(name = "documentrequest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documentrequest.findAll", query = "SELECT d FROM Documentrequest d"),
    @NamedQuery(name = "Documentrequest.findByBrojZahteva", query = "SELECT d FROM Documentrequest d WHERE d.brojZahteva = :brojZahteva"),
    @NamedQuery(name = "Documentrequest.findByStatus", query = "SELECT d FROM Documentrequest d WHERE d.status = :status"),
    @NamedQuery(name = "Documentrequest.findByIme", query = "SELECT d FROM Documentrequest d WHERE d.ime = :ime"),
    @NamedQuery(name = "Documentrequest.findByPrezime", query = "SELECT d FROM Documentrequest d WHERE d.prezime = :prezime"),
    @NamedQuery(name = "Documentrequest.findByImeMajke", query = "SELECT d FROM Documentrequest d WHERE d.imeMajke = :imeMajke"),
    @NamedQuery(name = "Documentrequest.findByPrezimeMajke", query = "SELECT d FROM Documentrequest d WHERE d.prezimeMajke = :prezimeMajke"),
    @NamedQuery(name = "Documentrequest.findByImeOca", query = "SELECT d FROM Documentrequest d WHERE d.imeOca = :imeOca"),
    @NamedQuery(name = "Documentrequest.findByPrezimeOca", query = "SELECT d FROM Documentrequest d WHERE d.prezimeOca = :prezimeOca"),
    @NamedQuery(name = "Documentrequest.findByPol", query = "SELECT d FROM Documentrequest d WHERE d.pol = :pol"),
    @NamedQuery(name = "Documentrequest.findByDatumRodjenja", query = "SELECT d FROM Documentrequest d WHERE d.datumRodjenja = :datumRodjenja"),
    @NamedQuery(name = "Documentrequest.findByNacionalnost", query = "SELECT d FROM Documentrequest d WHERE d.nacionalnost = :nacionalnost"),
    @NamedQuery(name = "Documentrequest.findByProfesija", query = "SELECT d FROM Documentrequest d WHERE d.profesija = :profesija"),
    @NamedQuery(name = "Documentrequest.findByBracnoStanje", query = "SELECT d FROM Documentrequest d WHERE d.bracnoStanje = :bracnoStanje"),
    @NamedQuery(name = "Documentrequest.findByOpstinaPrebivalista", query = "SELECT d FROM Documentrequest d WHERE d.opstinaPrebivalista = :opstinaPrebivalista"),
    @NamedQuery(name = "Documentrequest.findByUlicaPrebivalista", query = "SELECT d FROM Documentrequest d WHERE d.ulicaPrebivalista = :ulicaPrebivalista"),
    @NamedQuery(name = "Documentrequest.findByBrojPrebivalista", query = "SELECT d FROM Documentrequest d WHERE d.brojPrebivalista = :brojPrebivalista"),
    @NamedQuery(name = "Documentrequest.findByJmbg", query = "SELECT d FROM Documentrequest d WHERE d.jmbg = :jmbg")})
public class Documentrequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "BrojZahteva")
    private Long brojZahteva;
    @Size(max = 45)
    @Column(name = "Status")
    private String status;
    @Size(max = 45)
    @Column(name = "Ime")
    private String ime;
    @Size(max = 45)
    @Column(name = "Prezime")
    private String prezime;
    @Size(max = 45)
    @Column(name = "ImeMajke")
    private String imeMajke;
    @Size(max = 45)
    @Column(name = "PrezimeMajke")
    private String prezimeMajke;
    @Size(max = 45)
    @Column(name = "ImeOca")
    private String imeOca;
    @Size(max = 45)
    @Column(name = "PrezimeOca")
    private String prezimeOca;
    @Size(max = 45)
    @Column(name = "Pol")
    private String pol;
    @Size(max = 40)
    @Column(name = "DatumRodjenja")
    private String datumRodjenja;
    @Size(max = 45)
    @Column(name = "Nacionalnost")
    private String nacionalnost;
    @Size(max = 45)
    @Column(name = "Profesija")
    private String profesija;
    @Size(max = 45)
    @Column(name = "BracnoStanje")
    private String bracnoStanje;
    @Size(max = 45)
    @Column(name = "OpstinaPrebivalista")
    private String opstinaPrebivalista;
    @Size(max = 45)
    @Column(name = "UlicaPrebivalista")
    private String ulicaPrebivalista;
    @Size(max = 10)
    @Column(name = "BrojPrebivalista")
    private String brojPrebivalista;
    @Size(max = 13)
    @Column(name = "JMBG")
    private String jmbg;

    public Documentrequest() {
    }

    public Documentrequest(Long brojZahteva) {
        this.brojZahteva = brojZahteva;
    }

    public Long getBrojZahteva() {
        return brojZahteva;
    }

    public void setBrojZahteva(Long brojZahteva) {
        this.brojZahteva = brojZahteva;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getImeMajke() {
        return imeMajke;
    }

    public void setImeMajke(String imeMajke) {
        this.imeMajke = imeMajke;
    }

    public String getPrezimeMajke() {
        return prezimeMajke;
    }

    public void setPrezimeMajke(String prezimeMajke) {
        this.prezimeMajke = prezimeMajke;
    }

    public String getImeOca() {
        return imeOca;
    }

    public void setImeOca(String imeOca) {
        this.imeOca = imeOca;
    }

    public String getPrezimeOca() {
        return prezimeOca;
    }

    public void setPrezimeOca(String prezimeOca) {
        this.prezimeOca = prezimeOca;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(String datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getNacionalnost() {
        return nacionalnost;
    }

    public void setNacionalnost(String nacionalnost) {
        this.nacionalnost = nacionalnost;
    }

    public String getProfesija() {
        return profesija;
    }

    public void setProfesija(String profesija) {
        this.profesija = profesija;
    }

    public String getBracnoStanje() {
        return bracnoStanje;
    }

    public void setBracnoStanje(String bracnoStanje) {
        this.bracnoStanje = bracnoStanje;
    }

    public String getOpstinaPrebivalista() {
        return opstinaPrebivalista;
    }

    public void setOpstinaPrebivalista(String opstinaPrebivalista) {
        this.opstinaPrebivalista = opstinaPrebivalista;
    }

    public String getUlicaPrebivalista() {
        return ulicaPrebivalista;
    }

    public void setUlicaPrebivalista(String ulicaPrebivalista) {
        this.ulicaPrebivalista = ulicaPrebivalista;
    }

    public String getBrojPrebivalista() {
        return brojPrebivalista;
    }

    public void setBrojPrebivalista(String brojPrebivalista) {
        this.brojPrebivalista = brojPrebivalista;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (brojZahteva != null ? brojZahteva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documentrequest)) {
            return false;
        }
        Documentrequest other = (Documentrequest) object;
        if ((this.brojZahteva == null && other.brojZahteva != null) || (this.brojZahteva != null && !this.brojZahteva.equals(other.brojZahteva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Documentrequest[ brojZahteva=" + brojZahteva + " ]";
    }
    
}
