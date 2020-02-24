/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servis;
import entiteti.Documentrequest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.ConnectionFactory;
import javax.annotation.Resource;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Anja
 */
public class Main {
    @Resource(lookup = "jms/__defaultConnectionFactory")
    public static ConnectionFactory conn;
    
    @Resource(lookup = "QueueServisToKorisnik")
    private static Queue queueServisToKorisnik;
    //na ovaj Queue servis salje odgovore korisniku
    
    @Resource(lookup = "QueueKorisnikToServis")
    private static Queue queueKorisnikToServis;
    //na ovaj Queue salje korisnik poruke koje servis obradjuje
    
    public static void main(String[] args) {
        JMSContext context = conn.createContext();
        JMSConsumer consumer = context.createConsumer(queueKorisnikToServis);
        JMSProducer producer = context.createProducer();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ServisPU");
        EntityManager em = emf.createEntityManager();
        HttpURL http = new HttpURL();
        
        while(true) {
            try {
                ObjectMessage objMsg = (ObjectMessage)consumer.receive();
                Documentrequest dr = (Documentrequest)objMsg.getObject();
                String poruka = objMsg.getStringProperty("poruka");
                
                if (poruka.equals("PosaljiNaPersoServis")) {
                    int response = 0;
                    while(true) {
                        try {
                            response = http.slanjeZahtevaZaKreiranje(dr);
                        } catch(Exception e) {
                            System.out.println("Neuspesno slanje zahteva za kreiranje novog dokumenta");
                            continue;
                        }
                        break;
                    }
                    ObjectMessage responseObj = context.createObjectMessage();
                    responseObj.setObject(dr);
                    responseObj.setStringProperty("poruka", "PoslatNaPersoServis");
                    responseObj.setIntProperty("responseCode", response);
                    
                    producer.send(queueServisToKorisnik, responseObj);
                }
                else if (poruka.equals("DohvatiStatus")) {
                    int response = 0;
                    while(true) {
                        try {
                            response = http.dohvatanjeDokumenta(""+dr.getBrojZahteva());
                        } catch(Exception e) {
                            System.out.println("Neuspesno slanje zahteva za kreiranje novog dokumenta");
                            continue;
                        }
                        break;
                    }
                    ObjectMessage responseObj = context.createObjectMessage();
                    responseObj.setObject(dr);
                    responseObj.setStringProperty("poruka", "StatusDokumenta");
                    responseObj.setIntProperty("responseCode", response);
                    
                    producer.send(queueServisToKorisnik, responseObj);
                }
            } catch (JMSException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
