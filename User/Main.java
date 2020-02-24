/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package korisnik;
/**
 *
 * @author Anja
 */
import entiteti.Documentrequest;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;

public class Main extends JFrame {
private JTextField txtJmbg;
	private JTextField txtIme, txtTermin;
	private JTextField txtPrezime;
	private JTextField txtImeMajke;
	private JTextField txtPrezimeMajke;
	private JTextField txtImeOca;
	private JTextField txtPrezimeOca;
	private JTextField txtNacionalnost;
	private JTextField txtProfesija;
	private JTextField txtBracnoStanje;
	private JTextField txtOpstinaPrebivalista;
	private JTextField txtUlicaPrebivalista;
	private JTextField txtBrojPrebivalista;
	private JTextField txtDdmmgggg;
	private JTextField textField;
	private JButton btnProveraTermina;
	private JLabel lblStatusTermina;
        private JTextArea txtrPodaci;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
        private JRadioButton rdbtnNewRadioButton, rdbtnNewRadioButton_1;
        private final int IdRegionalnogCentra = 17420;
        private long brojZahteva = 174200000000L;
	private Documentrequest dr; 
        private HttpURL http = new HttpURL();
        private EntityManagerFactory emf = Persistence.createEntityManagerFactory("KorisnikPU");
        private EntityManager em = emf.createEntityManager();
        
        @Resource(lookup="jms/__defaultConnectionFactory")
        private static ConnectionFactory conn;
        
        @Resource(lookup = "QueueServisToKorisnik")
        private static Queue queueServisToKorisnik;
        //na ovaj Queue servis salje odgovore korisniku
    
        @Resource(lookup = "QueueKorisnikToServis")
        private static Queue queueKorisnikToServis;
        //na ovaj Queue salje korisnik poruke koje servis obradjuje
        
//        @Resource(lookup = "Topic")
//        static Topic topic;
        //topic za tajmer
        
        @Resource(lookup = "testIS1")
        private static Queue queue;
        //queue za tajmer
        
        private static JMSContext context;
        private static JMSConsumer consumer;
        private static JMSProducer producer;
        private static JMSConsumer consumerTimer;
	        
	public Main() {
		setVisible(true);
		setSize(700, 400);
		setLocationRelativeTo(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Novi zahtev", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		txtJmbg = new JTextField();
		txtJmbg.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtJmbg.getText().equals("JMBG")) {
					txtJmbg.setText("");
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("Pol:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 5;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Datum rodjenja:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 7;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		txtJmbg.setText("JMBG");
		GridBagConstraints gbc_txtJmbg = new GridBagConstraints();
		gbc_txtJmbg.insets = new Insets(0, 0, 5, 5);
		gbc_txtJmbg.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtJmbg.gridx = 1;
		gbc_txtJmbg.gridy = 2;
		panel.add(txtJmbg, gbc_txtJmbg);
		txtJmbg.setColumns(10);
		
		txtNacionalnost = new JTextField();
		txtNacionalnost.setText("Nacionalnost");
		GridBagConstraints gbc_txtNacionalnost = new GridBagConstraints();
		gbc_txtNacionalnost.insets = new Insets(0, 0, 5, 5);
		gbc_txtNacionalnost.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNacionalnost.gridx = 3;
		gbc_txtNacionalnost.gridy = 2;
		panel.add(txtNacionalnost, gbc_txtNacionalnost);
		txtNacionalnost.setColumns(10);
		txtNacionalnost.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtNacionalnost.getText().equals("Nacionalnost")) {
					txtNacionalnost.setText("");
				}
			}
		});
		
		rdbtnNewRadioButton = new JRadioButton("Muski");
		rdbtnNewRadioButton.setSelected(true);
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBackground(Color.WHITE);
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton.gridx = 5;
		gbc_rdbtnNewRadioButton.gridy = 2;
		panel.add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);
		
		txtDdmmgggg = new JTextField();
		txtDdmmgggg.setText("gggg-mm-dd");
		GridBagConstraints gbc_txtDdmmgggg = new GridBagConstraints();
		gbc_txtDdmmgggg.insets = new Insets(0, 0, 5, 5);
		gbc_txtDdmmgggg.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDdmmgggg.gridx = 7;
		gbc_txtDdmmgggg.gridy = 2;
		panel.add(txtDdmmgggg, gbc_txtDdmmgggg);
		txtDdmmgggg.setColumns(10);
		txtDdmmgggg.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtDdmmgggg.getText().equals("gggg-mm-dd")) {
					txtDdmmgggg.setText("");
				}
			}
		});
		
		txtIme = new JTextField();
		txtIme.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtIme.getText().equals("Ime")) {
					txtIme.setText("");
				}
			}
		});
		txtIme.setText("Ime");
		GridBagConstraints gbc_txtIme = new GridBagConstraints();
		gbc_txtIme.insets = new Insets(0, 0, 5, 5);
		gbc_txtIme.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtIme.gridx = 1;
		gbc_txtIme.gridy = 3;
		panel.add(txtIme, gbc_txtIme);
		txtIme.setColumns(10);
		
		txtProfesija = new JTextField();
		txtProfesija.setText("Profesija");
		GridBagConstraints gbc_txtProfesija = new GridBagConstraints();
		gbc_txtProfesija.insets = new Insets(0, 0, 5, 5);
		gbc_txtProfesija.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProfesija.gridx = 3;
		gbc_txtProfesija.gridy = 3;
		panel.add(txtProfesija, gbc_txtProfesija);
		txtProfesija.setColumns(10);
		txtProfesija.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtProfesija.getText().equals("Profesija")) {
					txtProfesija.setText("");
				}
			}
		});
		
		rdbtnNewRadioButton_1 = new JRadioButton("Zenski");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton_1.gridx = 5;
		gbc_rdbtnNewRadioButton_1.gridy = 3;
		panel.add(rdbtnNewRadioButton_1, gbc_rdbtnNewRadioButton_1);
		
		txtPrezime = new JTextField();
		txtPrezime.setText("Prezime");
		GridBagConstraints gbc_txtPrezime = new GridBagConstraints();
		gbc_txtPrezime.insets = new Insets(0, 0, 5, 5);
		gbc_txtPrezime.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPrezime.gridx = 1;
		gbc_txtPrezime.gridy = 4;
		panel.add(txtPrezime, gbc_txtPrezime);
		txtPrezime.setColumns(10);
		txtPrezime.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtPrezime.getText().equals("Prezime")) {
					txtPrezime.setText("");
				}
			}
		});
		
		txtBracnoStanje = new JTextField();
		txtBracnoStanje.setText("Bracno stanje");
		GridBagConstraints gbc_txtBracnoStanje = new GridBagConstraints();
		gbc_txtBracnoStanje.insets = new Insets(0, 0, 5, 5);
		gbc_txtBracnoStanje.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBracnoStanje.gridx = 3;
		gbc_txtBracnoStanje.gridy = 4;
		panel.add(txtBracnoStanje, gbc_txtBracnoStanje);
		txtBracnoStanje.setColumns(10);
		txtBracnoStanje.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtBracnoStanje.getText().equals("Bracno stanje")) {
					txtBracnoStanje.setText("");
				}
			}
		});
		
		txtImeMajke = new JTextField();
		txtImeMajke.setText("Ime majke");
		GridBagConstraints gbc_txtImeMajke = new GridBagConstraints();
		gbc_txtImeMajke.insets = new Insets(0, 0, 5, 5);
		gbc_txtImeMajke.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtImeMajke.gridx = 1;
		gbc_txtImeMajke.gridy = 5;
		panel.add(txtImeMajke, gbc_txtImeMajke);
		txtImeMajke.setColumns(10);
		txtImeMajke.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtImeMajke.getText().equals("Ime majke")) {
					txtImeMajke.setText("");
				}
			}
		});
		
		txtOpstinaPrebivalista = new JTextField();
		txtOpstinaPrebivalista.setText("Opstina prebivalista");
		GridBagConstraints gbc_txtOpstinaPrebivalista = new GridBagConstraints();
		gbc_txtOpstinaPrebivalista.insets = new Insets(0, 0, 5, 5);
		gbc_txtOpstinaPrebivalista.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtOpstinaPrebivalista.gridx = 3;
		gbc_txtOpstinaPrebivalista.gridy = 5;
		panel.add(txtOpstinaPrebivalista, gbc_txtOpstinaPrebivalista);
		txtOpstinaPrebivalista.setColumns(10);
		txtOpstinaPrebivalista.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtOpstinaPrebivalista.getText().equals("Opstina prebivalista")) {
					txtOpstinaPrebivalista.setText("");
				}
			}
		});
		
		txtPrezimeMajke = new JTextField();
		txtPrezimeMajke.setText("Prezime majke");
		GridBagConstraints gbc_txtPrezimeMajke = new GridBagConstraints();
		gbc_txtPrezimeMajke.insets = new Insets(0, 0, 5, 5);
		gbc_txtPrezimeMajke.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPrezimeMajke.gridx = 1;
		gbc_txtPrezimeMajke.gridy = 6;
		panel.add(txtPrezimeMajke, gbc_txtPrezimeMajke);
		txtPrezimeMajke.setColumns(10);
		txtPrezimeMajke.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtPrezimeMajke.getText().equals("Prezime majke")) {
					txtPrezimeMajke.setText("");
				}
			}
		});
		
		txtUlicaPrebivalista = new JTextField();
		txtUlicaPrebivalista.setText("Ulica prebivalista");
		GridBagConstraints gbc_txtUlicaPrebivalista = new GridBagConstraints();
		gbc_txtUlicaPrebivalista.insets = new Insets(0, 0, 5, 5);
		gbc_txtUlicaPrebivalista.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUlicaPrebivalista.gridx = 3;
		gbc_txtUlicaPrebivalista.gridy = 6;
		panel.add(txtUlicaPrebivalista, gbc_txtUlicaPrebivalista);
		txtUlicaPrebivalista.setColumns(10);
		txtUlicaPrebivalista.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtUlicaPrebivalista.getText().equals("Ulica prebivalista")) {
					txtUlicaPrebivalista.setText("");
				}
			}
		});
		
		txtImeOca = new JTextField();
		txtImeOca.setText("Ime oca");
		GridBagConstraints gbc_txtImeOca = new GridBagConstraints();
		gbc_txtImeOca.insets = new Insets(0, 0, 5, 5);
		gbc_txtImeOca.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtImeOca.gridx = 1;
		gbc_txtImeOca.gridy = 7;
		panel.add(txtImeOca, gbc_txtImeOca);
		txtImeOca.setColumns(10);
		txtImeOca.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtImeOca.getText().equals("Ime oca")) {
					txtImeOca.setText("");
				}
			}
		});
		
		txtBrojPrebivalista = new JTextField();
		txtBrojPrebivalista.setText("Broj prebivalista");
		GridBagConstraints gbc_txtBrojPrebivalista = new GridBagConstraints();
		gbc_txtBrojPrebivalista.insets = new Insets(0, 0, 5, 5);
		gbc_txtBrojPrebivalista.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBrojPrebivalista.gridx = 3;
		gbc_txtBrojPrebivalista.gridy = 7;
		panel.add(txtBrojPrebivalista, gbc_txtBrojPrebivalista);
		txtBrojPrebivalista.setColumns(10);
		txtBrojPrebivalista.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtBrojPrebivalista.getText().equals("Broj prebivalista")) {
					txtBrojPrebivalista.setText("");
				}
			}
		});
		
		txtPrezimeOca = new JTextField();
		txtPrezimeOca.setText("Prezime oca");
		GridBagConstraints gbc_txtPrezimeOca = new GridBagConstraints();
		gbc_txtPrezimeOca.insets = new Insets(0, 0, 5, 5);
		gbc_txtPrezimeOca.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPrezimeOca.gridx = 1;
		gbc_txtPrezimeOca.gridy = 8;
		panel.add(txtPrezimeOca, gbc_txtPrezimeOca);
		txtPrezimeOca.setColumns(10);
		txtPrezimeOca.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtPrezimeOca.getText().equals("Prezime oca")) {
					txtPrezimeOca.setText("");
				}
			}
		});
		
                txtTermin = new JTextField();
		txtTermin.setText("gggg-mm-ddTss:mm:ss");
		GridBagConstraints gbc_txtTermin = new GridBagConstraints();
		gbc_txtTermin.insets = new Insets(0, 0, 5, 0);
		gbc_txtTermin.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTermin.gridx = 9;
		gbc_txtTermin.gridy = 8;
		panel.add(txtTermin, gbc_txtTermin);
		txtTermin.setColumns(10);
                txtTermin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtTermin.getText().equals("gggg-mm-ddTss:mm:ss")) {
					txtTermin.setText("");
				}
			}
		});
                
		btnProveraTermina = new JButton("Provera termina");
		GridBagConstraints gbc_btnProveraTermina = new GridBagConstraints();
		gbc_btnProveraTermina.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnProveraTermina.insets = new Insets(0, 0, 5, 0);
		gbc_btnProveraTermina.gridx = 9;
		gbc_btnProveraTermina.gridy = 9;
		panel.add(btnProveraTermina, gbc_btnProveraTermina);
		
		JButton btnNewButton = new JButton("Unesi novog korisnika");
		btnNewButton.setEnabled(false);
		
		lblStatusTermina = new JLabel("dostupnost termina");
		lblStatusTermina.setEnabled(false);
		GridBagConstraints gbc_lblStatusTermina = new GridBagConstraints();
		gbc_lblStatusTermina.insets = new Insets(0, 0, 5, 0);
		gbc_lblStatusTermina.gridx = 9;
		gbc_lblStatusTermina.gridy = 10;
		panel.add(lblStatusTermina, gbc_lblStatusTermina);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 9;
		gbc_btnNewButton.gridy = 11;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Status dokumenta", null, panel_1, null);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Broj zahteva:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 1;
		panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		panel_1.add(textField, gbc_textField);
		textField.setColumns(10);
		
		btnNewButton_1 = new JButton("Prikazi zahtev");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 2;
		panel_1.add(btnNewButton_1, gbc_btnNewButton_1);
		
		btnNewButton_2 = new JButton("Osvezi");
		btnNewButton_2.setEnabled(false);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 3;
		gbc_btnNewButton_2.gridy = 2;
		panel_1.add(btnNewButton_2, gbc_btnNewButton_2);
		
		btnNewButton_3 = new JButton("Uruci");
		btnNewButton_3.setEnabled(false);
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 4;
		gbc_btnNewButton_3.gridy = 2;
		panel_1.add(btnNewButton_3, gbc_btnNewButton_3);
		
		txtrPodaci = new JTextArea();
		txtrPodaci.setText("Podaci...");
		GridBagConstraints gbc_txtrPodaci = new GridBagConstraints();
		gbc_txtrPodaci.gridwidth = 2;
		gbc_txtrPodaci.gridheight = 2;
		gbc_txtrPodaci.insets = new Insets(0, 0, 5, 5);
		gbc_txtrPodaci.fill = GridBagConstraints.BOTH;
		gbc_txtrPodaci.gridx = 1;
		gbc_txtrPodaci.gridy = 3;
		panel_1.add(txtrPodaci, gbc_txtrPodaci);
                
                //provera termina
                btnProveraTermina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
                            boolean dostupan;
                            try {
                                dostupan = http.proveraDostupnostiTermina(txtTermin.getText());
                            }catch(Exception e) {
                                dostupan = false;
                            }                            
                            if (!dostupan) {
                                lblStatusTermina.setText("Termin nije dostupan");
                            }
                            else {
                                lblStatusTermina.setText("Termin je dostupan");
                                btnNewButton.setEnabled(true);
                            }                            
                            lblStatusTermina.setEnabled(true);
                        }
		});
                
                //kreiraj zahtev
                btnNewButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        dr = new Documentrequest();

                        dr.setJmbg(txtJmbg.getText());
                        dr.setIme(txtIme.getText());
                        dr.setPrezime(txtPrezime.getText());
                        dr.setImeMajke(txtImeMajke.getText());
                        dr.setPrezimeMajke(txtPrezimeMajke.getText());
                        dr.setImeOca(txtImeOca.getText());
                        dr.setPrezimeOca(txtPrezimeOca.getText());
                        dr.setNacionalnost(txtNacionalnost.getText());
                        dr.setProfesija(txtProfesija.getText());
                        dr.setBracnoStanje(txtBracnoStanje.getText());
                        dr.setOpstinaPrebivalista(txtOpstinaPrebivalista.getText());
                        dr.setUlicaPrebivalista(txtUlicaPrebivalista.getText());
                        dr.setBrojPrebivalista(txtBrojPrebivalista.getText());
                        dr.setDatumRodjenja(txtDdmmgggg.getText());
                        if (rdbtnNewRadioButton.isSelected()) dr.setPol("M");
                        else dr.setPol("Z");   
                        dr.setStatus("Kreiran");
                        long brojZahteva = em.createQuery("SELECT MAX(d.brojZahteva) FROM Documentrequest d", Long.class).getSingleResult();
                        if (brojZahteva < 174200000000L) brojZahteva = 174200000000L;
                        dr.setBrojZahteva(++brojZahteva);

                        em.getTransaction().begin();
                        em.persist(dr);
                        //em.flush();
                        em.getTransaction().commit();

                        lblStatusTermina.setEnabled(false);
                        btnNewButton.setEnabled(false);

                        ObjectMessage objMsg = context.createObjectMessage();
                        try {
                            objMsg.setObject(dr);
                            objMsg.setStringProperty("poruka", "PosaljiNaPersoServis");
                            producer.send(queueKorisnikToServis, objMsg);

                            consumer.setMessageListener((msg) -> {
                                try {
                                    ObjectMessage obj = (ObjectMessage)msg;
                                    if (obj.getStringProperty("poruka").equals("PoslatNaPersoServis")) {
                                        int response = obj.getIntProperty("responseCode");
                                        if (response == 400)
                                            System.out.println("Los zahtev");
                                        else {
                                            System.out.println("Zahtev poslat na obradu");
                                            dr.setStatus("U produkciji");
                                            em.getTransaction().begin();
                                            em.persist(dr);
                                            //em.flush();
                                            em.getTransaction().commit();
                                        }
                                    }   
                                } catch (JMSException ex) {
                                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });
                            
                        } catch (JMSException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                   }
		});
                                
                //prikazi zahtev
		btnNewButton_1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        long brojZahteva;
                        try {
                            brojZahteva = Long.parseLong(textField.getText());
                        }catch(NumberFormatException n) {
                            brojZahteva = 0;
                        }        
                        btnNewButton_2.setEnabled(false);
                        btnNewButton_3.setEnabled(false);
                        Documentrequest zahtev = em.find(Documentrequest.class, brojZahteva);
                        if (zahtev == null) {
                            txtrPodaci.setText("Zahtev nije pronadjen");
                            return;
                        }                                
                        ispisiPodatke(zahtev);
                        if (zahtev.getStatus()!= null && zahtev.getStatus().equals("U produkciji")) {
                            btnNewButton_2.setEnabled(true);
                        }    
                        if (zahtev.getStatus()!= null && zahtev.getStatus().equals("Ceka na urucenje")) {
                            btnNewButton_3.setEnabled(true);
                        }  
                        
                        consumerTimer.setMessageListener((msg) -> {
                            try {
                                System.out.println("USAO"+((TextMessage)msg).getText());
                            } catch (JMSException ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            long brZahteva;
                            try {
                                brZahteva = Long.parseLong(textField.getText());
                            }catch(NumberFormatException n) {
                                brZahteva = 0;
                            } 
                            Documentrequest dr = em.find(Documentrequest.class, brZahteva);
                            if (dr == null) {
                                txtrPodaci.setText("Zahtev nije pronadjen");
                                return;
                            }
                        
                            ObjectMessage objMsg = context.createObjectMessage();
                            try {
                                objMsg.setObject(dr);
                                objMsg.setStringProperty("poruka", "DohvatiStatus");
                                producer.send(queueKorisnikToServis, objMsg);
                                
                               consumer.setMessageListener((mssg) -> {
                                    try {
                                        ObjectMessage objMssg = (ObjectMessage)mssg;
                                        if (objMssg.getStringProperty("poruka").equals("StatusDokumenta")) {
                                            int response = objMssg.getIntProperty("responseCode");
                                            if (response == 400)
                                                System.out.println("Nevalidan id dokumenta");
                                            else if (response == 404) {
                                                System.out.println("Dokument nije nadjen");
                                            }
                                            else {
                                                System.out.println("Zahtev uspesno izvrsen");
                                                if (response != 200) return;
                                                btnNewButton_3.setEnabled(true);
                                                dr.setStatus("Ceka na urucenje");
                                                em.getTransaction().begin();
                                                em.persist(dr);
                                                //em.flush();
                                                em.getTransaction().commit();
                                                ispisiPodatke(dr);
                                            }
                                        }
                                    } catch (JMSException ex) {
                                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                               });
                            } catch (JMSException ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    }
		});
                
                //osvezi
                btnNewButton_2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        long brojZahteva;
                        try {
                            brojZahteva = Long.parseLong(textField.getText());
                        }catch(NumberFormatException n) {
                            brojZahteva = 0;
                        } 
                        Documentrequest dr = em.find(Documentrequest.class, brojZahteva);
                        if (dr == null) {
                            txtrPodaci.setText("Zahtev nije pronadjen");
                            return;
                        }
                        
                        ObjectMessage objMsg = context.createObjectMessage();
                        try {
                            objMsg.setObject(dr);
                            objMsg.setStringProperty("poruka", "DohvatiStatus");
                            producer.send(queueKorisnikToServis, objMsg);
                            
                            consumer.setMessageListener((msg) -> {
                                try {
                                    ObjectMessage obj = (ObjectMessage)msg;
                                    if (obj.getStringProperty("poruka").equals("StatusDokumenta")) {
                                        int response = obj.getIntProperty("responseCode");
                                        if (response == 400)
                                            System.out.println("Nevalidan id dokumenta");
                                        else if (response == 404) {
                                            System.out.println("Dokument nije nadjen");
                                        }
                                        else {
                                            System.out.println("Zahtev uspesno izvrsen u osvezi");
                                            if (response != 200) return;
                                            btnNewButton_3.setEnabled(true);
                                            dr.setStatus("Ceka na urucenje");
                                            em.getTransaction().begin();
                                            em.persist(dr);
                                            //em.flush();
                                            em.getTransaction().commit();
                                            ispisiPodatke(dr);
                                        }
                                    }
                                } catch (JMSException ex) {
                                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });
                        } catch (JMSException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
		});
                
                //uruci
               btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            long brojZahteva;
                            try {
                                brojZahteva = Long.parseLong(textField.getText());
                            }catch(NumberFormatException n) {
                                brojZahteva = 0;
                            } 
                            Documentrequest dr = em.find(Documentrequest.class, brojZahteva);
                            if (dr == null) {
                                System.out.println("Dokument nije pronadjen");
                                return;
                            }
                            dr.setStatus("Urucen");
                            em.getTransaction().begin();
                            em.persist(dr);
                            //em.flush();
                            em.getTransaction().commit();
                            ispisiPodatke(dr);
                            btnNewButton_3.setEnabled(false);
			}
		});                 
	}
        
        public void ispisiPodatke(Documentrequest dr) {
            txtrPodaci.setText("Status: "+dr.getStatus()+"\n");
            txtrPodaci.append("JMBG: "+dr.getJmbg()+"\n");
            txtrPodaci.append("Ime: "+dr.getIme()+"\n");
            txtrPodaci.append("Prezime: "+dr.getPrezime()+"\n");
            txtrPodaci.append("Ime majke: "+dr.getImeMajke()+"\n");
            txtrPodaci.append("Prezime majke: "+dr.getPrezimeMajke()+"\n");
            txtrPodaci.append("Ime oca: "+dr.getImeOca()+"\n");
            txtrPodaci.append("Prezime oca: "+dr.getPrezimeOca()+"\n");
            txtrPodaci.append("Pol: "+dr.getPol()+"\n");
            txtrPodaci.append("Datum rodjenja: "+dr.getDatumRodjenja()+"\n");
            txtrPodaci.append("Nacionalnost: "+dr.getNacionalnost()+"\n");
            txtrPodaci.append("Profesija: "+dr.getProfesija()+"\n");
            txtrPodaci.append("Bracno stanje: "+dr.getBracnoStanje()+"\n");
            txtrPodaci.append("Opstina prebivalista: "+dr.getOpstinaPrebivalista()+"\n");
            txtrPodaci.append("Ulica prebivalista: "+dr.getUlicaPrebivalista()+"\n");
            txtrPodaci.append("Broj prebivalista: "+dr.getBrojPrebivalista()+"\n");
        }
	
	public static void main(String[] args) {
                context = conn.createContext();
                consumer = context.createConsumer(queueServisToKorisnik);
                producer = context.createProducer();
                consumerTimer = context.createConsumer(queue);       
		Main p = new Main();
	}
}