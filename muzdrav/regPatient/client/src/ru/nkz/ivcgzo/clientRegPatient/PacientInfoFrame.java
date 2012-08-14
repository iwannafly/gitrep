package ru.nkz.ivcgzo.clientRegPatient;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.thrift.TException;

import ru.nkz.ivcgzo.clientManager.common.IClient;
import ru.nkz.ivcgzo.clientManager.common.swing.CustomTimeEditor;
import ru.nkz.ivcgzo.clientManager.common.swing.CustomDateEditor;
import ru.nkz.ivcgzo.clientManager.common.swing.CustomTable;
import ru.nkz.ivcgzo.clientManager.common.swing.CustomTableItemChangeEvent;
import ru.nkz.ivcgzo.clientManager.common.swing.CustomTableItemChangeEventListener;
import ru.nkz.ivcgzo.clientManager.common.swing.ThriftIntegerClassifierCombobox;
import ru.nkz.ivcgzo.clientManager.common.swing.ThriftStringClassifierCombobox;
import ru.nkz.ivcgzo.thriftCommon.classifier.IntegerClassifier;
import ru.nkz.ivcgzo.thriftCommon.classifier.StringClassifier;
import ru.nkz.ivcgzo.thriftRegPatient.Address;
import ru.nkz.ivcgzo.thriftRegPatient.Agent;
import ru.nkz.ivcgzo.thriftRegPatient.AgentNotFoundException;
import ru.nkz.ivcgzo.thriftRegPatient.AllGosp;
import ru.nkz.ivcgzo.thriftRegPatient.Gosp;
import ru.nkz.ivcgzo.thriftRegPatient.GospNotFoundException;
import ru.nkz.ivcgzo.thriftRegPatient.Info;
import ru.nkz.ivcgzo.thriftRegPatient.Kontingent;
import ru.nkz.ivcgzo.thriftRegPatient.KontingentAlreadyExistException;
import ru.nkz.ivcgzo.thriftRegPatient.KontingentNotFoundException;
import ru.nkz.ivcgzo.thriftRegPatient.Lgota;
import ru.nkz.ivcgzo.thriftRegPatient.LgotaAlreadyExistException;
import ru.nkz.ivcgzo.thriftRegPatient.LgotaNotFoundException;
import ru.nkz.ivcgzo.thriftRegPatient.Nambk;
import ru.nkz.ivcgzo.thriftRegPatient.OgrnNotFoundException;
import ru.nkz.ivcgzo.thriftRegPatient.PatientAlreadyExistException;
import ru.nkz.ivcgzo.thriftRegPatient.PatientBrief;
import ru.nkz.ivcgzo.thriftRegPatient.PatientFullInfo;
import ru.nkz.ivcgzo.thriftRegPatient.PatientNotFoundException;
import ru.nkz.ivcgzo.thriftRegPatient.Polis;
import ru.nkz.ivcgzo.thriftRegPatient.Sign;
import ru.nkz.ivcgzo.thriftRegPatient.SignNotFoundException;
import ru.nkz.ivcgzo.thriftRegPatient.SmorfNotFoundException;
import ru.nkz.ivcgzo.thriftRegPatient.TerLiveNotFoundException;
import ru.nkz.ivcgzo.thriftRegPatient.RegionLiveNotFoundException;

public class PacientInfoFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTabbedPane tbMain;
    private JTabbedPane tpPersonal;
    private JTabbedPane tpLgota;
    private JTabbedPane tpKateg;
    private JTabbedPane tpAgent;
    private JTabbedPane tpSign;
    private JTabbedPane tpPriem;
    private int curPatientId = 0;
    private int curNgosp = 0;
    private int curId = 0;
    private final ButtonGroup btnGroup_pol = new ButtonGroup();
    private final ButtonGroup btnGroup_gk = new ButtonGroup();
    private final ButtonGroup btnGroup_rf = new ButtonGroup();
    private final ButtonGroup btnGroup_pol_pr = new ButtonGroup();
    private final ButtonGroup btnGroup_plextr = new ButtonGroup();
    private JTextField tfFam;
    private JTextField tfIm;
    private JTextField tfOt;
    private JTextField tf_Adp_obl;
    private JTextField tf_Adp_gorod;
    private JTextField tf_Adp_ul;
    private JTextField tf_Adp_dom;
    private JTextField tf_Adp_kv;
    private JTextField tf_Adm_obl;
    private JTextField tf_Adm_gorod;
    private JTextField tf_Adm_ul;
    private JTextField tf_Adm_dom;
    private JTextField tf_Adm_kv;
    private JTextField tf_oms_ser;
    private JTextField tf_dms_ser;
    private JTextField tf_oms_nom;
    private JTextField tf_dms_nom;
    private JTextField tf_oms_smo;
    private JTextField tf_dms_smo;
    private JTextField tfMr;
    private JTextField tfMrname;
    private JTextField tfDolj;
    private JTextField tfTel;
    private JTextField tf_Cpol;
    private JTextField tf_Nuch;
    private JTextField tf_Nambk;
    private JTextField tf_serdoc;
    private JTextField tf_nomdoc;
    private JTextField tf_Odoc;
    private JTextField tf_Snils;
    private JTextField tf_Fam_pr;
    private JTextField tf_Im_pr;
    private JTextField tf_Ot_pr;
    private JTextField tf_Mr_pr;
    private JTextField tf_Polis_ser_pr;
    private JTextField tf_Polis_nom_pr;
    private JTextField tf_Name_sk_pr;
    private JTextField tf_Ser_doc_pr;
    private JTextField tf_Nomdoc_pr;
    private JTextField tf_ntalon;
    private JTextField tf_diag_n;
    private JTextField tf_diag_p;
    private JTextField tf_toc;
    private JTextField tf_ad;
    private JTextField tf_smpn;
    private JTextField tf_nist;
    private JRadioButton rbtn_pol_m;
    private JRadioButton rbtn_pol_j;
    private JRadioButton rbtn_pol_pr_m;
    private JRadioButton rbtn_pol_pr_j;
    private JRadioButton rbtn_vp1;
    private JRadioButton rbtn_vp2;
    private JRadioButton rbtn_vp3;
    private JRadioButton rbtn_rf1;
    private JRadioButton rbtn_rf2;
    private JRadioButton rbtn_gk1;
    private JRadioButton rbtn_gk2;
    private JRadioButton rbtn_gk3;
    private JRadioButton rbtn_gk4;
    private JRadioButton rbtn_plan;
    private JRadioButton rbtn_extr;
    private JTextArea ta_allerg;
    private JTextArea ta_farm;
    private JTextArea ta_vitae;
    private JTextArea ta_jal_pr;
    private JTextArea ta_diag_p;
    private JTextArea ta_diag_n;
    private JCheckBox cbx_gosp;
    private JCheckBox cbx_smp;
    private JCheckBox cbx_nalz;
    private JCheckBox cbx_nalp;
    private JCheckBox cbx_messr;
    private CustomDateEditor tfDr;
    private CustomDateEditor tf_datapr;
    private CustomDateEditor tf_dataot;
    private CustomDateEditor tf_datadoc;
    private CustomDateEditor tf_dr_pr;
    private CustomDateEditor tf_datap;
    private CustomDateEditor tf_dataosm;
    private CustomDateEditor tf_datasmp;
    private CustomDateEditor tf_datagosp;
    private CustomTimeEditor tf_timep;
    private CustomTimeEditor tf_timeosm;
    private CustomTimeEditor tf_timesmp;
    private CustomTimeEditor tf_timegosp;

    private JSpinner sp_sv_time;
    private JSpinner sp_sv_day;
    public List<PatientBrief> pat;
    private PatientFullInfo PersonalInfo;
    private Agent AgentInfo;
    private List<Lgota> LgotaInfo;
    private List<Kontingent> KontingentInfo;
    private Sign SignInfo;
    private Gosp Id_gosp;
    private List<AllGosp> AllGospInfo;
//    private CustomTable<PatientBrief, PatientBrief._Fields> tbl_patient;
    private CustomTable<Lgota, Lgota._Fields> tbl_lgota;
    private CustomTable<Kontingent, Kontingent._Fields> tbl_kateg;
    private CustomTable<AllGosp, AllGosp._Fields> tbl_priem;

    private ThriftIntegerClassifierCombobox <IntegerClassifier> cmb_status;
    private ThriftIntegerClassifierCombobox <IntegerClassifier> cmb_ishod;
    private ThriftIntegerClassifierCombobox <IntegerClassifier> cmb_tdoc;
    private ThriftIntegerClassifierCombobox <IntegerClassifier> cmb_oms_doc;
    private ThriftIntegerClassifierCombobox <IntegerClassifier> cmb_Tdoc_pr;
    private ThriftIntegerClassifierCombobox <IntegerClassifier> cmb_Polis_doc_pr;
    private ThriftIntegerClassifierCombobox <IntegerClassifier> cmb_cotd;
    private ThriftIntegerClassifierCombobox <IntegerClassifier> cmb_travm;
    private ThriftIntegerClassifierCombobox <IntegerClassifier> cmb_trans;
    private ThriftIntegerClassifierCombobox <IntegerClassifier> cmb_otkaz;
    private ThriftIntegerClassifierCombobox <IntegerClassifier> cmb_alk;
    private ThriftStringClassifierCombobox <StringClassifier> cmb_naprav;
    private ThriftIntegerClassifierCombobox <IntegerClassifier> cmb_org;
    private ThriftStringClassifierCombobox <StringClassifier> cmb_ogrn;
//    private PatientBrief newPatBr;
    private AllGosp newPriem;
//	private Info pInfo;

//    public void refresh(List<PatientBrief> pat) {
//        tbl_patient.requestFocus();
//        tbl_patient.setData(pat);
//    }
    /**
     * Create the frame.
     */
    public PacientInfoFrame(List<PatientBrief> pat) {
//		addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowOpened(WindowEvent arg0) {
//				setExtendedState(JFrame.MAXIMIZED_BOTH);
//			}
//		});

    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
		try {
			cmb_ishod = new ThriftIntegerClassifierCombobox<>(true);
			cmb_status = new ThriftIntegerClassifierCombobox<>(true);
			cmb_tdoc = new ThriftIntegerClassifierCombobox<>(true);
			cmb_oms_doc = new ThriftIntegerClassifierCombobox<>(true);
			cmb_Tdoc_pr = new ThriftIntegerClassifierCombobox<>(true);
			cmb_cotd = new ThriftIntegerClassifierCombobox<>(true);
			cmb_travm = new ThriftIntegerClassifierCombobox<>(true);
			cmb_trans = new ThriftIntegerClassifierCombobox<>(true);
			cmb_otkaz = new ThriftIntegerClassifierCombobox<>(true);
			cmb_alk = new ThriftIntegerClassifierCombobox<>(true);
			cmb_org = new ThriftIntegerClassifierCombobox<>(true);
			cmb_ogrn = new ThriftStringClassifierCombobox<>(true);
			cmb_ogrn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						if (!tf_oms_smo.getText().isEmpty()) {
							cmb_ogrn.setData(MainForm.tcl.getSmorf(Integer.valueOf(tf_oms_smo.getText())));
						}
					} catch (SmorfNotFoundException snfe) {
						snfe.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					} 
				}
			});
			cmb_naprav = new ThriftStringClassifierCombobox<>(true);
			cmb_naprav.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						if (cmb_naprav.getSelectedItem() != null) {
//							if (cmb_naprav.getSelectedPcod().equals("Г"))cmb_org.setData(MainForm.tcl.getAL0());
							switch (cmb_naprav.getSelectedPcod()) {
							case "Г":
								cmb_org.setData(MainForm.tcl.getAL0());
								break;
							case "К":
								cmb_org.setData(MainForm.tcl.getN00());
								break;
							case "Л":
								cmb_org.setData(MainForm.tcl.getM00());
								break;
							case "Р":
								cmb_org.setData(MainForm.tcl.getW04());
								break;
							case "Т":
								cmb_org.setData(MainForm.tcl.getO00());
								break;
							default:
								cmb_org.setSelectedItem(null);
								break;
							}
						}
					} catch (TException e) {
						e.printStackTrace();
					} 
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		} 

		setBounds(1, 1, 1002, 748); //ширина, высота
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel pl_print = new JPanel();
		pl_print.setBorder(new TitledBorder(null, "\u041F\u0435\u0447\u0430\u0442\u044C \u0442\u0438\u0442\u0443\u043B\u044C\u043D\u043E\u0433\u043E \u043B\u0438\u0441\u0442\u0430", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		final JTabbedPane tbMain = new JTabbedPane(JTabbedPane.TOP);
		tbMain.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (tbMain.getSelectedIndex() == 1) {
					changePatientLgotaInfo(curPatientId);
				}
				if (tbMain.getSelectedIndex() == 2) {
					changePatientKategInfo(curPatientId);
				}
				if (tbMain.getSelectedIndex() == 3) {
					changePatientAgentInfo(curPatientId);
				}
				if (tbMain.getSelectedIndex() == 4) {
					changePatientSignInfo(curPatientId);
				}
				if (tbMain.getSelectedIndex() == 5) {
					selectAllPatientPriemInfo(curPatientId);
				}
			}
		});
		
		JPanel pl_patient = new JPanel();
		pl_patient.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		tbl_patient = new CustomTable<>(true, true, PatientBrief.class, 1,"Фамилия",2,"Имя",3,"Отчество",0,"ВН");
//      tbl_patient.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                if (!e.getValueIsAdjusting()) {
//                    if (tbl_patient.getSelectedItem() != null) {
//                    curPatientId = tbl_patient.getSelectedItem().npasp;
//                    if (tbMain.getSelectedIndex() == 0) {
//                        changePatientPersonalInfo(curPatientId);
//                    }
//                    if (tbMain.getSelectedIndex() == 1) {
//                        changePatientLgotaInfo(curPatientId);
//                    }
//                    if (tbMain.getSelectedIndex() == 2) {
//                        changePatientKategInfo(curPatientId);
//                    }
//                    if (tbMain.getSelectedIndex() == 3) {
//                        changePatientAgentInfo(curPatientId);
//                    }
//                    if (tbMain.getSelectedIndex() == 4) {
//                        changePatientSignInfo(curPatientId);
//                    }
//                    if (tbMain.getSelectedIndex() == 5) {
//                        selectAllPatientPriemInfo(curPatientId);
//                    }
//                    }
//                }
//            }
//        });
//        tbl_patient.registerDeleteSelectedRowListener(new CustomTableItemChangeEventListener<PatientBrief>() {
//
//            @Override
//            public boolean doAction(CustomTableItemChangeEvent<PatientBrief> event) {
//                try {
//                    MainForm.tcl.deletePatient(curPatientId);
//                } catch (TException e) {
//                    e.printStackTrace();
//                    return false;
//                }
//                return true;
//            }
//        });
//
//        tbl_patient.setFillsViewportHeight(true);
//        tbl_patient.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//
//        scrollPane.setViewportView(tbl_patient);
////        refresh(pat);
//
//        //удалить пациента
//        btnDel.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent arg0) {
//              try{
//                curPatientId = tbl_patient.getSelectedItem().npasp;
//                tbl_patient.requestFocus();
//                tbl_patient.deleteSelectedRow();
//              } catch (Exception e) {
//                e.printStackTrace();
//              }
//            }
//        });

        JButton btnNew = new JButton("Новый");
        btnNew.setToolTipText("Создать карту нового пациента");
        btnNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
//                    newPatBr = tbl_patient.addExternalItem();
                    tfFam.requestFocus();
                    curPatientId = 0;
                    NewPatient();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        JButton btnPoisk = new JButton("Поиск");
        btnPoisk.setToolTipText("Поиск пациента");
        btnPoisk.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
             	int[] res = MainForm.conMan.showPatientSearchForm("Поиск", false, true);
            	
            	if (res != null) {
            		curPatientId = res[0];
                    changePatientPersonalInfo(curPatientId);
            	}
//              tbMain.setSelectedIndex(0);
//              PacientMainFrame.getInstance().setVisible(true);
          }
        });
                
        JButton btnSave = new JButton("Сохранить");
        btnSave.setToolTipText("Сохранить изменения карты пациента");
        btnSave.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		try {
        			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        			PersonalInfo = new PatientFullInfo();
        			PersonalInfo.admAddress = new Address();
				    PersonalInfo.adpAddress = new Address();
				    PersonalInfo.polis_oms = new Polis();
				    PersonalInfo.polis_dms = new Polis();
				    PersonalInfo.nambk = new Nambk();
				    PersonalInfo.setDataz(new Date().getTime());
				    PersonalInfo.setNpasp(curPatientId);
				    PersonalInfo.setFam(tfFam.getText().toUpperCase().trim());
				    PersonalInfo.setIm(tfIm.getText().toUpperCase().trim());
				    PersonalInfo.setOt(tfOt.getText().toUpperCase().trim());
				    PersonalInfo.admAddress.setRegion(tf_Adm_obl.getText().toUpperCase().trim());
				    PersonalInfo.admAddress.setCity(tf_Adm_gorod.getText().toUpperCase().trim());
				    PersonalInfo.admAddress.setStreet(tf_Adm_ul.getText().toUpperCase().trim());
				    PersonalInfo.admAddress.setHouse(tf_Adm_dom.getText().toUpperCase().trim());
				    PersonalInfo.admAddress.setFlat(tf_Adm_kv.getText().toUpperCase().trim());
				    PersonalInfo.adpAddress.setRegion(tf_Adp_obl.getText().toUpperCase().trim());
				    PersonalInfo.adpAddress.setCity(tf_Adp_gorod.getText().toUpperCase().trim());
				    PersonalInfo.adpAddress.setStreet(tf_Adp_ul.getText().toUpperCase().trim());
				    PersonalInfo.adpAddress.setHouse(tf_Adp_dom.getText().toUpperCase().trim());
				    PersonalInfo.adpAddress.setFlat(tf_Adp_kv.getText().toUpperCase().trim());
				    PersonalInfo.setNamemr(tfMrname.getText().toUpperCase().trim());
				    PersonalInfo.setMrab(tfMr.getText().toUpperCase().trim());
				    PersonalInfo.setProf(tfDolj.getText().trim());
				    PersonalInfo.setTel(tfTel.getText().trim());
				    PersonalInfo.setSnils(tf_Snils.getText().toUpperCase().trim());
				    PersonalInfo.setOdoc(tf_Odoc.getText().trim());
				    PersonalInfo.setDocser(tf_serdoc.getText().trim());
				    PersonalInfo.setDocnum(tf_nomdoc.getText().trim());
				    PersonalInfo.polis_dms.setSer(tf_dms_ser.getText().toUpperCase().trim());
				    PersonalInfo.polis_dms.setNom(tf_dms_nom.getText().toUpperCase().trim());
				    PersonalInfo.polis_oms.setSer(tf_oms_ser.getText().toUpperCase().trim());
				    PersonalInfo.polis_oms.setNom(tf_oms_nom.getText().toUpperCase().trim());
				    PersonalInfo.nambk.setNambk(tf_Nambk.getText());
				    if (!tf_oms_smo.getText().isEmpty()) PersonalInfo.polis_oms.setStrg(Integer.valueOf(tf_oms_smo.getText()));
				    if (!tf_dms_smo.getText().isEmpty()) PersonalInfo.polis_dms.setStrg(Integer.valueOf(tf_dms_smo.getText()));
				    if (!tf_Nuch.getText().isEmpty()) PersonalInfo.nambk.setNuch(Integer.valueOf(tf_Nuch.getText()));
				    if (!tf_Cpol.getText().isEmpty()) PersonalInfo.setCpol_pr(Integer.valueOf(tf_Cpol.getText()));

				    if (tfDr.getDate() != null) PersonalInfo.setDatar(tfDr.getDate().getTime());
				    if (tf_datadoc.getDate() != null) PersonalInfo.setDatadoc(tf_datadoc.getDate().getTime());
				    if (tf_datapr.getDate() != null) PersonalInfo.nambk.setDatapr(tf_datapr.getDate().getTime());
				    if (tf_dataot.getDate() != null) PersonalInfo.nambk.setDataot(tf_dataot.getDate().getTime());
//					if (!tfDr.getText().isEmpty()) PersonalInfo.setDatar(sdf.parse(tfDr.getText()).getTime());
//					if (!tf_datadoc.getText().isEmpty()) PersonalInfo.setDatadoc(sdf.parse(tf_datadoc.getText()).getTime());
//					if (!tf_datapr.getText().isEmpty()) PersonalInfo.nambk.setDatapr(sdf.parse(tf_datapr.getText()).getTime());
//					if (!tf_dataot.getText().isEmpty()) PersonalInfo.nambk.setDataot(sdf.parse(tf_dataot.getText()).getTime());
				    if (rbtn_pol_m.isSelected()) PersonalInfo.setPol(1);
				    if (rbtn_pol_j.isSelected()) PersonalInfo.setPol(2);
				    if (cmb_status.getSelectedItem() != null) PersonalInfo.setSgrp(cmb_status.getSelectedPcod());
				    if (cmb_oms_doc.getSelectedItem() != null) PersonalInfo.polis_oms.setTdoc(cmb_oms_doc.getSelectedPcod());
				    if (cmb_ishod.getSelectedItem() != null) PersonalInfo.nambk.setIshod(cmb_ishod.getSelectedPcod());
				    if (cmb_tdoc.getSelectedItem() != null) PersonalInfo.setTdoc(cmb_tdoc.getSelectedPcod());
				
//				    try{
//					pcod из классификатора
//				    	PersonalInfo.setTer_liv(MainForm.tcl.getTerLive(pcod)); 
//					} catch (TerLiveNotFoundException tlnfe) {
//					    System.out.println("Пациент не найден.");
//					} catch (Exception e) {
//				    	e.printStackTrace();
//					}
//				    try{
//					    PersonalInfo.setRegion_liv(MainForm.tcl.getRegionLive(pcod));
//					} catch (RegionLiveNotFoundException rlnfe) {
//					    System.out.println("Пациент не найден.");
//					} catch (Exception e) {
//				    	e.printStackTrace();
//					}
				//FIXME   jitel, terp
				    if (curPatientId == 0){
					curPatientId = MainForm.tcl.addPatient(PersonalInfo);
					//newPatBr.setFam(PersonalInfo.getFam().toUpperCase());
					//newPatBr.setOt(PersonalInfo.getOt().toUpperCase());
					//newPatBr.setIm(PersonalInfo.getIm().toUpperCase());
					//newPatBr.setNpasp(curPatientId);
					//tbl_patient.updateSelectedItem();
					//						RefreshTablePatient();
				    }
					else{
						MainForm.tcl.updatePatient(PersonalInfo);
					    }
				} catch (PatientAlreadyExistException paee) {
					    System.out.println("Пациент не найден.");
				} catch (Exception e) {
					    e.printStackTrace();
				}
			 }
			});
				
				        JButton btnDel = new JButton("Удалить");
				        btnDel.setToolTipText("Удалить карту пациента");
				        btnDel.addActionListener(new ActionListener() {
				        	public void actionPerformed(ActionEvent arg0) {
				              try {
				            	  MainForm.tcl.deletePatient(curPatientId);
				              } catch (TException e) {
				            	  e.printStackTrace();
				              }
				        	}
				        });
				
				        JButton btnShowTalonSelectModule = new JButton("Запись на приём");
				        btnShowTalonSelectModule.setToolTipText("Записать пациента на приём");
				        btnShowTalonSelectModule.addActionListener(new ActionListener() {
				            @Override
				            public void actionPerformed(ActionEvent arg0) {
				                try {
				                    int patientId = curPatientId;
				                    String patientSurname = tfFam.getText();
				                    String patientName = tfIm.getText();
				                    String patientMiddlename = tfOt.getText();
				                    long patientBirthdate = tfDr.getDate().getTime();
				                    IClient client = MainForm.conMan.getPluginLoader().loadPluginByAppId(10);
				                    client.showModal(MainForm.instance, patientId, patientSurname, patientName, patientMiddlename, patientBirthdate);
				                } catch (Exception e) {
				                    e.printStackTrace();
				                }
				            }
				        });

				GroupLayout gl_pl_patient = new GroupLayout(pl_patient);
				gl_pl_patient.setHorizontalGroup(
					gl_pl_patient.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pl_patient.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnDel)
							.addGap(77)
							.addComponent(btnNew)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnPoisk)
							.addGap(8)
							.addComponent(btnSave)
							.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
							.addComponent(btnShowTalonSelectModule)
							.addContainerGap())
				);
				gl_pl_patient.setVerticalGroup(
					gl_pl_patient.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_pl_patient.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_pl_patient.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnDel)
								.addComponent(btnNew)
								.addComponent(btnPoisk)
								.addComponent(btnSave)
								.addComponent(btnShowTalonSelectModule)))
				);
        pl_patient.setLayout(gl_pl_patient);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tbMain, GroupLayout.PREFERRED_SIZE, 974, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addComponent(pl_patient, GroupLayout.PREFERRED_SIZE, 567, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
					.addComponent(pl_print, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(pl_print, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(pl_patient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addComponent(tbMain, GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE))
		);
		
		JPanel tpPersonal = new JPanel();
		tpPersonal.setBorder(new EmptyBorder(0, 0, 0, 0));
		tbMain.addTab("Пациент", null, tpPersonal, null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Общая информация", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "\u0410\u0434\u0440\u0435\u0441", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "\u041C\u0435\u0434\u0438\u0446\u0438\u043D\u0441\u043A\u0438\u0439 \u043F\u043E\u043B\u0438\u0441", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "\u041C\u0435\u0441\u0442\u043E \u0440\u0430\u0431\u043E\u0442\u044B", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "\u041F\u0440\u0438\u043A\u0440\u0435\u043F\u043B\u0435\u043D\u0438\u0435", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "\u0414\u043E\u043A\u0443\u043C\u0435\u043D\u0442, \u0443\u0434\u043E\u0441\u0442\u043E\u0432\u0435\u0440\u044F\u044E\u0449\u0438\u0439 \u043B\u0438\u0447\u043D\u043E\u0441\u0442\u044C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_tpPersonal = new GroupLayout(tpPersonal);
		gl_tpPersonal.setHorizontalGroup(
			gl_tpPersonal.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_tpPersonal.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_tpPersonal.createParallelGroup(Alignment.LEADING)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 679, Short.MAX_VALUE)
					.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 679, Short.MAX_VALUE)
					.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
					.addComponent(panel_7, GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
					.addGroup(gl_tpPersonal.createSequentialGroup()
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(4)))
					.addGap(43))
		);
		gl_tpPersonal.setVerticalGroup(
			gl_tpPersonal.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tpPersonal.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_27 = new JLabel("Документ");
		JLabel lblNewLabel_28 = new JLabel("Серия");
		JLabel lblNewLabel_29 = new JLabel("Номер");
		JLabel lblNewLabel_30 = new JLabel("Дата");
		JLabel lblNewLabel_31 = new JLabel("Кем выдан");
		JLabel lblNewLabel_32 = new JLabel("СНИЛС");
		
		tf_serdoc = new JTextField();
		tf_serdoc.setColumns(10);

		tf_nomdoc = new JTextField();
		tf_nomdoc.setColumns(10);
		
		tf_Odoc = new JTextField();
		tf_Odoc.setColumns(10);
		
		tf_Snils = new JTextField();
		tf_Snils.setColumns(10);
		
		tf_datadoc = new CustomDateEditor();
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_7.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_7.createSequentialGroup()
							.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
								.addComponent(tf_serdoc, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_28))
							.addGap(12))
						.addGroup(gl_panel_7.createSequentialGroup()
							.addComponent(lblNewLabel_31)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_7.createSequentialGroup()
							.addComponent(tf_Odoc, 295, 295, 295)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_30)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tf_datadoc, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_32)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(tf_Snils, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_7.createSequentialGroup()
							.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_29)
								.addComponent(tf_nomdoc, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_7.createSequentialGroup()
									.addGap(7)
									.addComponent(lblNewLabel_27)
									.addGap(36))
								.addGroup(gl_panel_7.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cmb_tdoc, GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)))))
					.addGap(36))
		);
		gl_panel_7.setVerticalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_28)
						.addComponent(lblNewLabel_29)
						.addComponent(lblNewLabel_27))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
							.addComponent(tf_serdoc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(tf_nomdoc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(cmb_tdoc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_7.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
							.addComponent(tf_Odoc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_30)
							.addComponent(lblNewLabel_32)
							.addComponent(tf_Snils, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(tf_datadoc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_31))
					.addContainerGap())
		);
		panel_7.setLayout(gl_panel_7);
		
		tf_Cpol = new JTextField();
		tf_Cpol.setColumns(10);
		
		JLabel lblNewLabel_21 = new JLabel("Поликлиника");
		JLabel lblNewLabel_22 = new JLabel("Дата прикрепления");
		JLabel lblNewLabel_23 = new JLabel("№ участка");
		JLabel lblNewLabel_24 = new JLabel("№ амб. карты");
		JLabel lblNewLabel_25 = new JLabel("Дата открепления");
		JLabel lblNewLabel_26 = new JLabel("Причина");
		
		tf_Nuch = new JTextField();
		tf_Nuch.setText("");
		tf_Nuch.setColumns(10);
		
		tf_Nambk = new JTextField();
		tf_Nambk.setColumns(10);
		
		tf_datapr = new CustomDateEditor();
		tf_dataot = new CustomDateEditor();
		
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addComponent(lblNewLabel_25)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tf_dataot, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_26)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cmb_ishod, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE))
						.addGroup(gl_panel_6.createSequentialGroup()
							.addComponent(lblNewLabel_21)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(tf_Cpol, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_23)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tf_Nuch, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_24)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tf_Nambk, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_22)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(tf_datapr, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(39, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_21)
						.addComponent(tf_Cpol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_23)
						.addComponent(tf_Nuch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_24)
						.addComponent(tf_Nambk, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_22)
						.addComponent(tf_datapr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_25)
						.addComponent(lblNewLabel_26)
						.addComponent(cmb_ishod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tf_dataot, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_6.setLayout(gl_panel_6);
		
		JLabel lblNewLabel_18 = new JLabel("Место работы");
		JLabel lblNewLabel_19 = new JLabel("Должность");
		JLabel lblNewLabel_20 = new JLabel("Телефон");
		
		tfMrname = new JTextField();
		tfMrname.setColumns(10);

		tfMr = new JTextField();
		tfMr.setColumns(10);
		
		tfDolj = new JTextField();
		tfDolj.setColumns(10);
		
		tfTel = new JTextField();
		tfTel.setColumns(10);
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_19)
						.addComponent(lblNewLabel_18))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(tfDolj, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_20)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfTel, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(tfMr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfMrname, GroupLayout.PREFERRED_SIZE, 456, GroupLayout.PREFERRED_SIZE)))
					.addGap(50))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_18)
						.addComponent(tfMr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfMrname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfDolj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_19)
						.addComponent(lblNewLabel_20)
						.addComponent(tfTel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(0, 0, Short.MAX_VALUE))
		);
		panel_5.setLayout(gl_panel_5);
		
		JLabel lblNewLabel_12 = new JLabel("Серия");
		JLabel lblNewLabel_13 = new JLabel("ОМС");
		JLabel lblNewLabel_14 = new JLabel("ДМС");
		JLabel lblNewLabel_15 = new JLabel("Номер");
		JLabel lblNewLabel_16 = new JLabel("СМО");
		JLabel lblNewLabel_17 = new JLabel("Документ");
		
		tf_oms_ser = new JTextField();
		tf_oms_ser.setColumns(10);
		
		tf_dms_ser = new JTextField();
		tf_dms_ser.setColumns(10);
		
		tf_oms_nom = new JTextField();
		tf_oms_nom.setColumns(10);
		
		tf_dms_nom = new JTextField();
		tf_dms_nom.setColumns(10);
		
		tf_oms_smo = new JTextField();
		tf_oms_smo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		tf_oms_smo.setColumns(10);
		
		tf_dms_smo = new JTextField();
		tf_dms_smo.setColumns(10);
		
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_14)
						.addComponent(lblNewLabel_13))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(tf_dms_ser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tf_dms_nom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tf_dms_smo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addComponent(tf_oms_ser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_12))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addComponent(tf_oms_nom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_15))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addComponent(tf_oms_smo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_16))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_17)
								.addComponent(cmb_oms_doc, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))))
					.addGap(92))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_12)
						.addComponent(lblNewLabel_15)
						.addComponent(lblNewLabel_16)
						.addComponent(lblNewLabel_17))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf_oms_ser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tf_oms_nom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tf_oms_smo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmb_oms_doc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_13))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf_dms_ser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tf_dms_nom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tf_dms_smo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_14))
					.addContainerGap(30, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Прописка");
		JLabel lblNewLabel_6 = new JLabel("Проживает");
		JLabel lblNewLabel_7 = new JLabel("Область(край, республика)");
		JLabel lblNewLabel_8 = new JLabel("Город (село)");
		JLabel lblNewLabel_9 = new JLabel("Улица");
		JLabel lblNewLabel_10 = new JLabel("Дом");
		JLabel lblNewLabel_11 = new JLabel("Кв.");
		
		tf_Adp_obl = new JTextField();
		tf_Adp_obl.setColumns(10);
		
		tf_Adp_gorod = new JTextField();
		tf_Adp_gorod.setColumns(10);
		
		tf_Adp_ul = new JTextField();
		tf_Adp_ul.setColumns(10);
		
		tf_Adp_dom = new JTextField();
		tf_Adp_dom.setColumns(10);
		
		tf_Adp_kv = new JTextField();
		tf_Adp_kv.setColumns(10);
		
		tf_Adm_obl = new JTextField();
		tf_Adm_obl.setColumns(10);
		
		tf_Adm_gorod = new JTextField();
		tf_Adm_gorod.setColumns(10);
		
		tf_Adm_ul = new JTextField();
		tf_Adm_ul.setColumns(10);
		
		tf_Adm_dom = new JTextField();
		tf_Adm_dom.setColumns(10);
		
		tf_Adm_kv = new JTextField();
		tf_Adm_kv.setColumns(10);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_6)
							.addGap(6)
							.addComponent(tf_Adm_obl, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(tf_Adm_gorod, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(tf_Adm_ul, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(tf_Adm_dom, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tf_Adm_kv, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_3.createSequentialGroup()
									.addGap(82)
									.addComponent(lblNewLabel_7)
									.addGap(18)
									.addComponent(lblNewLabel_8)
									.addGap(105)
									.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_3.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(tf_Adp_obl, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tf_Adp_gorod, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tf_Adp_ul, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)))
							.addGap(10)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(tf_Adp_dom, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_11, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(tf_Adp_kv, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))))
					.addGap(16))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_7)
						.addComponent(lblNewLabel_8)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_9)
							.addComponent(lblNewLabel_10)
							.addComponent(lblNewLabel_11)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_5)
							.addComponent(tf_Adp_obl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(tf_Adp_gorod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(tf_Adp_ul, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
							.addComponent(tf_Adp_dom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(tf_Adp_kv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
							.addComponent(tf_Adm_obl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(tf_Adm_gorod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(tf_Adm_ul, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(tf_Adm_kv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(tf_Adm_dom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblNewLabel_6))
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		
		tfFam = new JTextField();
		tfFam.setColumns(10);
		
		tfIm = new JTextField();
		tfIm.setColumns(10);
		
		tfOt = new JTextField();
		tfOt.setColumns(10);
		
		tfDr = new CustomDateEditor();

		rbtn_pol_m = new JRadioButton("мужской");
		btnGroup_pol.add(rbtn_pol_m);
		
		rbtn_pol_j = new JRadioButton("женский");
		btnGroup_pol.add(rbtn_pol_j);
		
		JLabel lblNewLabel = new JLabel("Дата рождения");
		JLabel lblNewLabel_1 = new JLabel("Фамилия");
		JLabel lblNewLabel_2 = new JLabel("Имя");
		JLabel lblNewLabel_3 = new JLabel("Отчество");
		JLabel lblNewLabel_4 = new JLabel("Социальный статус");
		JLabel lblPol = new JLabel("Пол");
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
						.addComponent(tfDr)
						.addComponent(tfOt)
						.addComponent(tfIm)
						.addComponent(tfFam, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
					.addGap(46)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmb_status, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblPol)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rbtn_pol_m)
							.addGap(18)
							.addComponent(rbtn_pol_j)))
					.addGap(152))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfFam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_4))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfIm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(cmb_status, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfOt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3)
						.addComponent(lblPol)
						.addComponent(rbtn_pol_m)
						.addComponent(rbtn_pol_j))
					.addGap(6)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(tfDr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(2, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		tpPersonal.setLayout(gl_tpPersonal);
		
		JPanel tpLgota = new JPanel();
		tbMain.addTab("Льгота", null, tpLgota, null);
		
		JPanel panel_9 = new JPanel();
		
		JPanel panel_10 = new JPanel();
		GroupLayout gl_tpLgota = new GroupLayout(tpLgota);
		gl_tpLgota.setHorizontalGroup(
			gl_tpLgota.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_tpLgota.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_tpLgota.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_10, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
						.addComponent(panel_9, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_tpLgota.setVerticalGroup(
			gl_tpLgota.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_tpLgota.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_9, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_10, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(387, Short.MAX_VALUE))
		);

	
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel_10 = new GroupLayout(panel_10);
		gl_panel_10.setHorizontalGroup(
			gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 648, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		gl_panel_10.setVerticalGroup(
			gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
					.addContainerGap())
		);

		tbl_lgota =new CustomTable<>(true, true, Lgota.class, 3,"Дата",2,"Льгота",4,"Наименование");
		tbl_lgota.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tbl_lgota.setDateField(0);
		tbl_lgota.setFillsViewportHeight(true);
		tbl_lgota.setPreferredWidths(75,75,490);
		//tbl_lgota.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane_1.setViewportView(tbl_lgota);
		panel_10.setLayout(gl_panel_10);
		
//		tbl_lgota.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//			@Override
//			public void valueChanged(ListSelectionEvent e) {
//				if (!e.getValueIsAdjusting()) {
//					  changePatientLgotaInfo(curPatientId);
//				}
//			}
//		});
        //удалить
        tbl_lgota.registerDeleteSelectedRowListener(new CustomTableItemChangeEventListener<Lgota>() {

            @Override
            public boolean doAction(CustomTableItemChangeEvent<Lgota> event) {
                try {
                    curId = tbl_lgota.getSelectedItem().id;
                    MainForm.tcl.deleteLgota(curId);
                } catch (TException e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        });
        //добавить
        tbl_lgota.registerAddRowListener(new CustomTableItemChangeEventListener<Lgota>() {

            @Override
            public boolean doAction(CustomTableItemChangeEvent<Lgota> event) {
                try {
                    Lgota item = event.getItem();
                    item.setNpasp(curPatientId);
                    item.setLgota(tbl_lgota.getSelectedItem().lgota);
                    item.setDatau(tbl_lgota.getSelectedItem().datau);
                    //item.setName(tbl_lgota.getSelectedItem().name);
                    Info pInfo = MainForm.tcl.addLgota(event.getItem());
                    tbl_lgota.getSelectedItem().setName(pInfo.getName());
                    curId = pInfo.getId();
                } catch (LgotaAlreadyExistException laee) {
                    laee.printStackTrace();
                    return false;
                } catch (TException e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        });
        //изменить
        tbl_lgota.registerUpdateSelectedRowListener(new CustomTableItemChangeEventListener<Lgota>() {
            @Override
            public boolean doAction(CustomTableItemChangeEvent<Lgota> event) {
                try {
                    MainForm.tcl.updateLgota(event.getItem());
                } catch (TException e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        });

        JButton btnDel_lgt = new JButton("Удалить");
        btnDel_lgt.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnDel_lgt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    tbl_lgota.requestFocus();
                    tbl_lgota.deleteSelectedRow();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        JButton btnAdd_lgt = new JButton("Добавить");
        btnAdd_lgt.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnAdd_lgt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    tbl_lgota.requestFocus();
                    tbl_lgota.addItem();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        JButton btnSave_lgt = new JButton("Сохранить");
        btnSave_lgt.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnSave_lgt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    tbl_lgota.updateSelectedItem();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        GroupLayout gl_panel_9 = new GroupLayout(panel_9);
        gl_panel_9.setHorizontalGroup(
            gl_panel_9.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_9.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnDel_lgt)
                    .addGap(18)
                    .addComponent(btnAdd_lgt)
                    .addGap(18)
                    .addComponent(btnSave_lgt)
                    .addContainerGap(384, Short.MAX_VALUE))
        );
        gl_panel_9.setVerticalGroup(
            gl_panel_9.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_9.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel_9.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnDel_lgt)
                        .addComponent(btnAdd_lgt)
                        .addComponent(btnSave_lgt))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_9.setLayout(gl_panel_9);
        tpLgota.setLayout(gl_tpLgota);

        JPanel tpKateg = new JPanel();
        tbMain.addTab("Категория", null, tpKateg, null);

        JPanel panel_11 = new JPanel();

        JPanel panel_12 = new JPanel();
        GroupLayout gl_tpKateg = new GroupLayout(tpKateg);
        gl_tpKateg.setHorizontalGroup(
            gl_tpKateg.createParallelGroup(Alignment.TRAILING)
                .addGroup(Alignment.LEADING, gl_tpKateg.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_tpKateg.createParallelGroup(Alignment.LEADING)
                        .addComponent(panel_11, GroupLayout.PREFERRED_SIZE, 668, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_12, GroupLayout.PREFERRED_SIZE, 668, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(19, Short.MAX_VALUE))
        );
        gl_tpKateg.setVerticalGroup(
            gl_tpKateg.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_tpKateg.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel_11, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(panel_12, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(389, Short.MAX_VALUE))
        );

        JScrollPane scrollPane_2 = new JScrollPane();
        GroupLayout gl_panel_12 = new GroupLayout(panel_12);
        gl_panel_12.setHorizontalGroup(
            gl_panel_12.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_12.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 648, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gl_panel_12.setVerticalGroup(
            gl_panel_12.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panel_12.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addContainerGap())
        );

        tbl_kateg =new CustomTable<>(true, true, Kontingent.class, 3,"Дата",2,"Категория",4,"Наименование");
        tbl_kateg.setFont(new Font("Tahoma", Font.PLAIN, 11));
        tbl_kateg.setDateField(0);
        tbl_kateg.setPreferredWidths(75,75,490);
        tbl_kateg.setFillsViewportHeight(true);
        scrollPane_2.setViewportView(tbl_kateg);
        panel_12.setLayout(gl_panel_12);

//		tbl_kateg.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//			@Override
//			public void valueChanged(ListSelectionEvent e) {
//				if (!e.getValueIsAdjusting()) {
//					curPatientId = tbl_patient.getSelectedItem().npasp;
//					changePatientKategInfo(curPatientId);
//				}
//			}
//		});
        //удалить
        tbl_kateg.registerDeleteSelectedRowListener(new CustomTableItemChangeEventListener<Kontingent>() {
            @Override
            public boolean doAction(CustomTableItemChangeEvent<Kontingent> event) {
                try {
                    curId = tbl_kateg.getSelectedItem().id;
                    MainForm.tcl.deleteKont(curId);
                } catch (TException e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        });
        //добавить
        tbl_kateg.registerAddRowListener(new CustomTableItemChangeEventListener<Kontingent>() {

            @Override
            public boolean doAction(CustomTableItemChangeEvent<Kontingent> event) {
                try {
                    Kontingent item = event.getItem();
                    item.setNpasp(curPatientId);
                    item.setKateg(tbl_kateg.getSelectedItem().kateg);
                    item.setDatau(tbl_kateg.getSelectedItem().datau);
//				    item.setName(tbl_kateg.getSelectedItem().name);
                    Info pInfo = MainForm.tcl.addKont(event.getItem());
                    tbl_kateg.getSelectedItem().setName(pInfo.getName());
                    curId = pInfo.getId();
                } catch (KontingentAlreadyExistException kaee) {
                    kaee.printStackTrace();
                    return false;
                } catch (TException e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        });
        //изменить
        tbl_kateg.registerUpdateSelectedRowListener(new CustomTableItemChangeEventListener<Kontingent>() {
            @Override
            public boolean doAction(CustomTableItemChangeEvent<Kontingent> event) {
                try {
                    MainForm.tcl.updateKont(event.getItem());
                } catch (TException e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        });

        JButton btnDel_kat = new JButton("Удалить");
        btnDel_kat.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnDel_kat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    tbl_kateg.requestFocus();
                    tbl_kateg.deleteSelectedRow();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        JButton btnAdd_kat = new JButton("Добавить");
        btnAdd_kat.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnAdd_kat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    tbl_kateg.requestFocus();
                    tbl_kateg.addItem();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        JButton btnSave_kat = new JButton("Сохранить");
        btnSave_kat.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnSave_kat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    tbl_kateg.updateSelectedItem();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        GroupLayout gl_panel_11 = new GroupLayout(panel_11);
        gl_panel_11.setHorizontalGroup(
            gl_panel_11.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_11.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnDel_kat)
                    .addGap(18)
                    .addComponent(btnAdd_kat)
                    .addGap(18)
                    .addComponent(btnSave_kat)
                    .addContainerGap(375, Short.MAX_VALUE))
        );
        gl_panel_11.setVerticalGroup(
            gl_panel_11.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_11.createSequentialGroup()
                    .addContainerGap(12, Short.MAX_VALUE)
                    .addGroup(gl_panel_11.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnDel_kat)
                        .addComponent(btnAdd_kat)
                        .addComponent(btnSave_kat))
                    .addContainerGap())
        );
        panel_11.setLayout(gl_panel_11);
        tpKateg.setLayout(gl_tpKateg);

        JPanel tpAgent = new JPanel();
        tbMain.addTab("Доп. сведения", null, tpAgent, null);

        JPanel panel_8 = new JPanel();
        panel_8.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u0421\u0432\u0435\u0434\u0435\u043D\u0438\u044F \u043E \u043F\u0440\u0435\u0434\u0441\u0442\u0430\u0432\u0438\u0442\u0435\u043B\u0435 \u0440\u0435\u0431\u0435\u043D\u043A\u0430 :", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel panel_20 = new JPanel();
        panel_20.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u0414\u043B\u044F \u0438\u043D\u043E\u043E\u0431\u043B\u0430\u0441\u0442\u043D\u044B\u0445 \u0433\u0440\u0430\u0436\u0434\u0430\u043D :", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel panel_21 = new JPanel();
        panel_21.setBorder(new TitledBorder(null, "Документ, удостоверяющий личность", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_21.setVisible(false);
        
        JPanel panel_22 = new JPanel();
        
        JLabel lblNewLabel_39 = new JLabel("Дополнительные сведения для инообластных граждан и детей до 3-х месяцев, не имеющих полиса ОМС");
        lblNewLabel_39.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GroupLayout gl_tpAgent = new GroupLayout(tpAgent);
        gl_tpAgent.setHorizontalGroup(
        	gl_tpAgent.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_tpAgent.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_tpAgent.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_tpAgent.createSequentialGroup()
        					.addComponent(panel_8, GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE)
        					.addContainerGap())
        				.addGroup(gl_tpAgent.createSequentialGroup()
        					.addComponent(lblNewLabel_39)
        					.addContainerGap(913, Short.MAX_VALUE))
        				.addGroup(gl_tpAgent.createSequentialGroup()
        					.addComponent(panel_21, GroupLayout.PREFERRED_SIZE, 677, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap(282, Short.MAX_VALUE))
        				.addGroup(gl_tpAgent.createSequentialGroup()
        					.addGroup(gl_tpAgent.createParallelGroup(Alignment.LEADING)
        						.addComponent(panel_20, GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE)
        						.addGroup(gl_tpAgent.createSequentialGroup()
        							.addComponent(panel_22, GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE)
        							.addGap(45)))
        					.addContainerGap())))
        );
        gl_tpAgent.setVerticalGroup(
        	gl_tpAgent.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_tpAgent.createSequentialGroup()
        			.addGap(12)
        			.addComponent(lblNewLabel_39)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(panel_8, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(panel_20, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(panel_22, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
        			.addGap(59)
        			.addComponent(panel_21, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(101, Short.MAX_VALUE))
        );

        JButton btnSave_agent = new JButton("Сохранить");
        btnSave_agent.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnSave_agent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    AgentInfo = new Agent();
                    AgentInfo.setNpasp(curPatientId);
                    AgentInfo.setFam(tf_Fam_pr.getText().toUpperCase().trim());
                    AgentInfo.setIm(tf_Im_pr.getText().toUpperCase().trim());
                    AgentInfo.setOt(tf_Ot_pr.getText().toUpperCase().trim());
                    if (tf_dr_pr.getDate() != null)  AgentInfo.setDatar(tf_dr_pr.getDate().getTime());
                    AgentInfo.setBirthplace(tf_Mr_pr.getText().trim());
                    AgentInfo.setSpolis(tf_Polis_ser_pr.getText().toUpperCase().trim());
                    AgentInfo.setNpolis(tf_Polis_nom_pr.getText().toUpperCase().trim());
                    AgentInfo.setName_str(tf_Name_sk_pr.getText().trim());
                    AgentInfo.setDocser(tf_Ser_doc_pr.getText().toUpperCase().trim());
                    AgentInfo.setDocnum(tf_Nomdoc_pr.getText().toUpperCase().trim());
                    if (rbtn_pol_pr_m.isSelected()) AgentInfo.setPol(1);
                    if (rbtn_pol_pr_j.isSelected()) AgentInfo.setPol(2);
                    if (cmb_Tdoc_pr.getSelectedItem() != null) AgentInfo.setVpolis(cmb_Tdoc_pr.getSelectedPcod());
                    if (cmb_Polis_doc_pr.getSelectedItem() != null) AgentInfo.setTdoc(cmb_Polis_doc_pr.getSelectedPcod());
                    try{
                    	if (cmb_ogrn.getSelectedItem() != null) AgentInfo.setOgrn_str(MainForm.tcl.getOgrn(cmb_ogrn.getSelectedPcod()));
                	} catch (OgrnNotFoundException onfe) {
                		onfe.printStackTrace();
                	} catch (Exception e) {
                		e.printStackTrace();
                	}
                    MainForm.tcl.addOrUpdateAgent(AgentInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        JButton btnDel_agent = new JButton("Удалить");
        btnDel_agent.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnDel_agent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    MainForm.tcl.deleteAgent(curPatientId);
                    NewAgent();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        GroupLayout gl_panel_22 = new GroupLayout(panel_22);
        gl_panel_22.setHorizontalGroup(
            gl_panel_22.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panel_22.createSequentialGroup()
                    .addGap(455)
                    .addComponent(btnSave_agent)
                    .addGap(34)
                    .addComponent(btnDel_agent)
                    .addContainerGap(24, Short.MAX_VALUE))
        );
        gl_panel_22.setVerticalGroup(
            gl_panel_22.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_22.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel_22.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnDel_agent)
                        .addComponent(btnSave_agent))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_22.setLayout(gl_panel_22);

        JLabel lblNewLabel_45 = new JLabel("Документ");
        JLabel lblNewLabel_46 = new JLabel("Серия");
        JLabel lblNewLabel_47 = new JLabel("Номер");
        tf_Ser_doc_pr = new JTextField();
        tf_Ser_doc_pr.setColumns(10);
        tf_Nomdoc_pr = new JTextField();
        tf_Nomdoc_pr.setColumns(10);
        lblNewLabel_45.setVisible(false);
        lblNewLabel_46.setVisible(false);
        lblNewLabel_47.setVisible(false);
        tf_Ser_doc_pr.setVisible(false);
        tf_Nomdoc_pr.setVisible(false);
        cmb_Tdoc_pr.setVisible(false);
        
        GroupLayout gl_panel_21 = new GroupLayout(panel_21);
        gl_panel_21.setHorizontalGroup(
            gl_panel_21.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_21.createSequentialGroup()
                    .addComponent(tf_Ser_doc_pr, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(tf_Nomdoc_pr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(cmb_Tdoc_pr, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addGap(295))
                .addGroup(gl_panel_21.createSequentialGroup()
                    .addComponent(lblNewLabel_46)
                    .addGap(40)
                    .addComponent(lblNewLabel_47)
                    .addGap(70)
                    .addComponent(lblNewLabel_45)
                    .addGap(439))
        );
        gl_panel_21.setVerticalGroup(
            gl_panel_21.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panel_21.createSequentialGroup()
                    .addGroup(gl_panel_21.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewLabel_46)
                        .addComponent(lblNewLabel_47)
                        .addComponent(lblNewLabel_45))
                    .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(gl_panel_21.createParallelGroup(Alignment.BASELINE)
                        .addComponent(tf_Ser_doc_pr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(tf_Nomdoc_pr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmb_Tdoc_pr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        panel_21.setLayout(gl_panel_21);
        JLabel lblNewLabel_43 = new JLabel("СМО ФФ ОМС");
        JLabel lblNewLabel_44 = new JLabel("Наименование СМО");

        tf_Name_sk_pr = new JTextField();
        tf_Name_sk_pr.setColumns(10);
        JLabel lblNewLabel_37 = new JLabel("Место рождения");
        
                tf_Mr_pr = new JTextField();
                tf_Mr_pr.setColumns(10);
        
        GroupLayout gl_panel_20 = new GroupLayout(panel_20);
        gl_panel_20.setHorizontalGroup(
        	gl_panel_20.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_20.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_panel_20.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblNewLabel_44)
        				.addComponent(lblNewLabel_43)
        				.addComponent(lblNewLabel_37))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_panel_20.createParallelGroup(Alignment.LEADING)
        				.addComponent(tf_Name_sk_pr, GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
        				.addComponent(cmb_ogrn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(tf_Mr_pr, GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE))
        			.addGap(215))
        );
        gl_panel_20.setVerticalGroup(
        	gl_panel_20.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_20.createSequentialGroup()
        			.addGroup(gl_panel_20.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel_20.createSequentialGroup()
        					.addGap(4)
        					.addComponent(lblNewLabel_37))
        				.addGroup(gl_panel_20.createSequentialGroup()
        					.addGap(1)
        					.addComponent(tf_Mr_pr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_panel_20.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNewLabel_44)
        				.addComponent(tf_Name_sk_pr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(8)
        			.addGroup(gl_panel_20.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNewLabel_43)
        				.addComponent(cmb_ogrn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_20.setLayout(gl_panel_20);

        JLabel lblNewLabel_33 = new JLabel("Фамилия");
        JLabel lblNewLabel_34 = new JLabel("Имя");
        JLabel lblNewLabel_35 = new JLabel("Отчество");
        JLabel lblNewLabel_36 = new JLabel("Дата рождения");
        JLabel lblNewLabel_38 = new JLabel("Пол");

        tf_Fam_pr = new JTextField();
        tf_Fam_pr.setColumns(10);

        tf_Im_pr = new JTextField();
        tf_Im_pr.setColumns(10);

        tf_Ot_pr = new JTextField();
        tf_Ot_pr.setColumns(10);

        rbtn_pol_pr_m = new JRadioButton("мужской");
        btnGroup_pol_pr.add(rbtn_pol_pr_m);

        rbtn_pol_pr_j = new JRadioButton("женский");
        btnGroup_pol_pr.add(rbtn_pol_pr_j);

        tf_dr_pr = new CustomDateEditor();
        
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "\u041C\u0435\u0434\u0438\u0446\u0438\u043D\u0441\u043A\u0438\u0439 \u043F\u043E\u043B\u0438\u0441 \u041E\u041C\u0421", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GroupLayout gl_panel_8 = new GroupLayout(panel_8);
        gl_panel_8.setHorizontalGroup(
        	gl_panel_8.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, gl_panel_8.createSequentialGroup()
        			.addGap(47)
        			.addGroup(gl_panel_8.createParallelGroup(Alignment.TRAILING)
        				.addComponent(lblNewLabel_33)
        				.addComponent(lblNewLabel_34)
        				.addComponent(lblNewLabel_35)
        				.addComponent(lblNewLabel_36))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_panel_8.createParallelGroup(Alignment.TRAILING, false)
        				.addComponent(tf_dr_pr)
        				.addComponent(tf_Ot_pr, Alignment.LEADING)
        				.addComponent(tf_Im_pr, Alignment.LEADING)
        				.addComponent(tf_Fam_pr, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
        			.addGap(68)
        			.addComponent(lblNewLabel_38)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(rbtn_pol_pr_m)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(rbtn_pol_pr_j)
        			.addContainerGap(453, Short.MAX_VALUE))
        		.addGroup(gl_panel_8.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(panel, GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
        			.addGap(204))
        );
        gl_panel_8.setVerticalGroup(
        	gl_panel_8.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_8.createSequentialGroup()
        			.addGroup(gl_panel_8.createParallelGroup(Alignment.BASELINE)
        				.addComponent(tf_Fam_pr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNewLabel_33))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_panel_8.createParallelGroup(Alignment.BASELINE)
        				.addComponent(tf_Im_pr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNewLabel_34))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_panel_8.createParallelGroup(Alignment.BASELINE)
        				.addComponent(tf_Ot_pr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNewLabel_35))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_panel_8.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNewLabel_36)
        				.addComponent(lblNewLabel_38)
        				.addComponent(rbtn_pol_pr_m)
        				.addComponent(rbtn_pol_pr_j)
        				.addComponent(tf_dr_pr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(panel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
        			.addGap(21))
        );
        
                JLabel lblNewLabel_40 = new JLabel("Серия");
        JLabel lblNewLabel_41 = new JLabel("Номер");
        JLabel lblNewLabel_42 = new JLabel("Документ");
        
        tf_Polis_ser_pr = new JTextField();
        tf_Polis_ser_pr.setColumns(10);
        
        tf_Polis_nom_pr = new JTextField();
        tf_Polis_nom_pr.setColumns(10);
        cmb_Polis_doc_pr = new ThriftIntegerClassifierCombobox<>(true);
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(lblNewLabel_40)
        					.addGap(45)
        					.addComponent(lblNewLabel_41)
        					.addGap(56)
        					.addComponent(lblNewLabel_42))
        				.addGroup(gl_panel.createSequentialGroup()
        					.addComponent(tf_Polis_ser_pr, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
        					.addGap(10)
        					.addComponent(tf_Polis_nom_pr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addGap(6)
        					.addComponent(cmb_Polis_doc_pr, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)))
        			.addGap(145))
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        					.addComponent(lblNewLabel_41)
        					.addComponent(lblNewLabel_40))
        				.addComponent(lblNewLabel_42))
        			.addGap(6)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addComponent(tf_Polis_ser_pr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(tf_Polis_nom_pr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(cmb_Polis_doc_pr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(12, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);
        panel_8.setLayout(gl_panel_8);
        tpAgent.setLayout(gl_tpAgent);

        JPanel tpSign = new JPanel();
        tbMain.addTab("Сигнальные отметки", null, tpSign, null);

        JPanel panel_13 = new JPanel();
        panel_13.setBorder(new TitledBorder(null, "\u0413\u0440\u0443\u043F\u043F\u0430 \u043A\u0440\u043E\u0432\u0438", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel panel_14 = new JPanel();
        panel_14.setBorder(new TitledBorder(null, "\u0420\u0435\u0437\u0443\u0441 \u0444\u0430\u043A\u0442\u043E\u0440", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel panel_15 = new JPanel();
        panel_15.setBorder(new TitledBorder(null, "\u0410\u043B\u043B\u0435\u0440\u0433\u043E-\u0430\u043D\u0430\u043C\u043D\u0435\u0437", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel panel_16 = new JPanel();
        panel_16.setBorder(new TitledBorder(null, "\u0424\u0430\u0440\u043C\u0430\u043A\u043E\u043B\u043E\u0433\u0438\u0447\u0435\u0441\u043A\u0438\u0439 \u0430\u043D\u0430\u043C\u043D\u0435\u0437", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel panel_17 = new JPanel();
        panel_17.setBorder(new TitledBorder(null, "\u0410\u043D\u0430\u043C\u043D\u0435\u0437 \u0436\u0438\u0437\u043D\u0438", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel panel_18 = new JPanel();
        panel_18.setBorder(new TitledBorder(null, "\u0412\u0440\u0435\u0434\u043D\u044B\u0435 \u043F\u0440\u0438\u0432\u044B\u0447\u043A\u0438", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel panel_19 = new JPanel();
        GroupLayout gl_tpSign = new GroupLayout(tpSign);
        gl_tpSign.setHorizontalGroup(
            gl_tpSign.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_tpSign.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_tpSign.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_tpSign.createSequentialGroup()
                            .addComponent(panel_19, GroupLayout.PREFERRED_SIZE, 677, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(gl_tpSign.createParallelGroup(Alignment.LEADING)
                            .addGroup(gl_tpSign.createSequentialGroup()
                                .addGroup(gl_tpSign.createParallelGroup(Alignment.LEADING)
                                    .addComponent(panel_15, GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                                    .addComponent(panel_16, GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                                    .addComponent(panel_17, GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE))
                                .addGap(158))
                            .addGroup(gl_tpSign.createSequentialGroup()
                                .addComponent(panel_13, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(panel_14, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(panel_18, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()))))
        );
        gl_tpSign.setVerticalGroup(
            gl_tpSign.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_tpSign.createSequentialGroup()
                    .addGap(23)
                    .addGroup(gl_tpSign.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(panel_14, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
                        .addComponent(panel_13, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel_18, 0, 0, Short.MAX_VALUE))
                    .addGap(18)
                    .addComponent(panel_15, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(panel_16, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(panel_17, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(panel_19, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(185))
        );

        JButton btnSave_Sign = new JButton("Сохранить");
        btnSave_Sign.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnSave_Sign.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    SignInfo = new Sign();
                    SignInfo.setNpasp(curPatientId);
                    SignInfo.setVred("");
                    SignInfo.setGrup("");
                    SignInfo.setPh("");
                    if (rbtn_gk1.isSelected()) {
                        SignInfo.setGrup("1");
                    }
                    if (rbtn_gk2.isSelected()) {
                        SignInfo.setGrup("2");
                    }
                    if (rbtn_gk3.isSelected()) {
                        SignInfo.setGrup("3");
                    }
                    if (rbtn_gk4.isSelected()) {
                        SignInfo.setGrup("4");
                    }
                    if (rbtn_rf1.isSelected()) {
                        SignInfo.setPh("+");
                    }
                    if (rbtn_rf2.isSelected()) {
                        SignInfo.setPh("-");
                    }
                    if (rbtn_vp1.isSelected()) {
                        SignInfo.vred += "1";
                    }else {
                        SignInfo.vred += "0";
                    }
                    if (rbtn_vp2.isSelected()) {
                        SignInfo.vred += "1";
                    }else {
                        SignInfo.vred += "0";
                    }
                    if (rbtn_vp3.isSelected()) {
                        SignInfo.vred += "1";
                    }else {
                        SignInfo.vred += "0";
                    }
                    SignInfo.setAllerg(ta_allerg.getText().trim());
                    SignInfo.setFarmkol(ta_farm.getText().trim());
                    SignInfo.setVitae(ta_vitae.getText().trim());
                    MainForm.tcl.addOrUpdateSign(SignInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        JButton btnDel_Sign = new JButton("Удалить");
        btnDel_Sign.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnDel_Sign.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    MainForm.tcl.deleteSign(curPatientId);
                    NewSign();
                } catch (Exception e) {
                    System.out.println("Ошибка при удалении данных.");
                    e.printStackTrace();
                }
            }
        });
        GroupLayout gl_panel_19 = new GroupLayout(panel_19);
        gl_panel_19.setHorizontalGroup(
            gl_panel_19.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panel_19.createSequentialGroup()
                    .addContainerGap(474, Short.MAX_VALUE)
                    .addComponent(btnSave_Sign)
                    .addGap(18)
                    .addComponent(btnDel_Sign)
                    .addGap(21))
        );
        gl_panel_19.setVerticalGroup(
            gl_panel_19.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_19.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel_19.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnDel_Sign)
                        .addComponent(btnSave_Sign))
                    .addContainerGap(14, Short.MAX_VALUE))
        );
        panel_19.setLayout(gl_panel_19);

        rbtn_vp1= new JRadioButton("курение");

        rbtn_vp2 = new JRadioButton("алкоголь");

        rbtn_vp3 = new JRadioButton("наркотики");
        GroupLayout gl_panel_18 = new GroupLayout(panel_18);
        gl_panel_18.setHorizontalGroup(
            gl_panel_18.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_18.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(rbtn_vp1)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(rbtn_vp2)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(rbtn_vp3)
                    .addContainerGap(30, Short.MAX_VALUE))
        );
        gl_panel_18.setVerticalGroup(
            gl_panel_18.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_18.createSequentialGroup()
                    .addGroup(gl_panel_18.createParallelGroup(Alignment.BASELINE)
                        .addComponent(rbtn_vp1)
                        .addComponent(rbtn_vp2)
                        .addComponent(rbtn_vp3))
                    .addGap(49))
        );
        panel_18.setLayout(gl_panel_18);

        ta_allerg = new JTextArea();
        ta_allerg.setFont(new Font("Tahoma", Font.PLAIN, 11));
        GroupLayout gl_panel_15 = new GroupLayout(panel_15);
        gl_panel_15.setHorizontalGroup(
            gl_panel_15.createParallelGroup(Alignment.LEADING)
                .addComponent(ta_allerg, GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
        );
        gl_panel_15.setVerticalGroup(
            gl_panel_15.createParallelGroup(Alignment.LEADING)
                .addComponent(ta_allerg, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
        );
        panel_15.setLayout(gl_panel_15);

        ta_farm = new JTextArea();
        ta_farm.setFont(new Font("Tahoma", Font.PLAIN, 11));
        GroupLayout gl_panel_16 = new GroupLayout(panel_16);
        gl_panel_16.setHorizontalGroup(
            gl_panel_16.createParallelGroup(Alignment.LEADING)
                .addComponent(ta_farm, GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
        );
        gl_panel_16.setVerticalGroup(
            gl_panel_16.createParallelGroup(Alignment.LEADING)
                .addComponent(ta_farm, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
        );
        panel_16.setLayout(gl_panel_16);

        ta_vitae = new JTextArea();
        ta_vitae.setFont(new Font("Tahoma", Font.PLAIN, 11));
        GroupLayout gl_panel_17 = new GroupLayout(panel_17);
        gl_panel_17.setHorizontalGroup(
            gl_panel_17.createParallelGroup(Alignment.LEADING)
                .addComponent(ta_vitae, GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
        );
        gl_panel_17.setVerticalGroup(
            gl_panel_17.createParallelGroup(Alignment.LEADING)
                .addComponent(ta_vitae, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );
        panel_17.setLayout(gl_panel_17);

        rbtn_rf1 = new JRadioButton("+");
        btnGroup_rf.add(rbtn_rf1);

        rbtn_rf2 = new JRadioButton("-");
        btnGroup_rf.add(rbtn_rf2);
        GroupLayout gl_panel_14 = new GroupLayout(panel_14);
        gl_panel_14.setHorizontalGroup(
            gl_panel_14.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_14.createSequentialGroup()
                    .addComponent(rbtn_rf1)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(rbtn_rf2)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gl_panel_14.setVerticalGroup(
            gl_panel_14.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_14.createSequentialGroup()
                    .addGroup(gl_panel_14.createParallelGroup(Alignment.BASELINE)
                        .addComponent(rbtn_rf1)
                        .addComponent(rbtn_rf2))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_14.setLayout(gl_panel_14);

        rbtn_gk1 = new JRadioButton("I");
        btnGroup_gk.add(rbtn_gk1);

        rbtn_gk2 = new JRadioButton("II");
        btnGroup_gk.add(rbtn_gk2);

        rbtn_gk3 = new JRadioButton("III");
        btnGroup_gk.add(rbtn_gk3);

        rbtn_gk4 = new JRadioButton("IV");
        btnGroup_gk.add(rbtn_gk4);

        GroupLayout gl_panel_13 = new GroupLayout(panel_13);
        gl_panel_13.setHorizontalGroup(
            gl_panel_13.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_13.createSequentialGroup()
                    .addComponent(rbtn_gk1)
                    .addGap(10)
                    .addComponent(rbtn_gk2)
                    .addGap(4)
                    .addComponent(rbtn_gk3)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(rbtn_gk4)
                    .addContainerGap(37, Short.MAX_VALUE))
        );
        gl_panel_13.setVerticalGroup(
            gl_panel_13.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_13.createSequentialGroup()
                    .addGroup(gl_panel_13.createParallelGroup(Alignment.BASELINE)
                        .addComponent(rbtn_gk1)
                        .addComponent(rbtn_gk2)
                        .addComponent(rbtn_gk3)
                        .addComponent(rbtn_gk4))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_13.setLayout(gl_panel_13);
        tpSign.setLayout(gl_tpSign);

        JPanel tpPriem = new JPanel();
        tbMain.addTab("Приемное отделение", null, tpPriem, null);

        JPanel panel_23 = new JPanel();
        panel_23.setBorder(new TitledBorder(null, "Обращения", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel panel_24 = new JPanel();

        JPanel panel_25 = new JPanel();
        panel_25.setBorder(new TitledBorder(null, "Обращение в приемное отделение", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel panel_34 = new JPanel();
        panel_34.setBorder(new TitledBorder(null, "Госпитализация", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GroupLayout gl_tpPriem = new GroupLayout(tpPriem);
        gl_tpPriem.setHorizontalGroup(
            gl_tpPriem.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_tpPriem.createSequentialGroup()
                    .addGroup(gl_tpPriem.createParallelGroup(Alignment.LEADING)
                        .addComponent(panel_34, GroupLayout.PREFERRED_SIZE, 732, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_25, GroupLayout.PREFERRED_SIZE, 732, GroupLayout.PREFERRED_SIZE)
                        .addGroup(gl_tpPriem.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(panel_23, GroupLayout.PREFERRED_SIZE, 693, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_tpPriem.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(panel_24, GroupLayout.PREFERRED_SIZE, 676, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gl_tpPriem.setVerticalGroup(
            gl_tpPriem.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_tpPriem.createSequentialGroup()
                    .addGap(6)
                    .addComponent(panel_23, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_24, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                    .addGap(12)
                    .addComponent(panel_25, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_34, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(16, Short.MAX_VALUE))
        );

        JPanel panel_35 = new JPanel();
        panel_35.setBorder(new TitledBorder(null, "Дата и время", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel panel_36 = new JPanel();
        panel_36.setBorder(new TitledBorder(null, "Своевременность", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JLabel lblNewLabel_62 = new JLabel("Отделение");
        lblNewLabel_62.setFont(new Font("Tahoma", Font.PLAIN, 11));

        cbx_messr = new JCheckBox("сообщение родственникам");
        cbx_messr.setFont(new Font("Tahoma", Font.PLAIN, 11));
        GroupLayout gl_panel_34 = new GroupLayout(panel_34);
        gl_panel_34.setHorizontalGroup(
            gl_panel_34.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_34.createSequentialGroup()
                    .addGroup(gl_panel_34.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_34.createSequentialGroup()
                            .addComponent(lblNewLabel_62)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(cmb_cotd, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                            .addGap(104))
                        .addGroup(gl_panel_34.createSequentialGroup()
                            .addComponent(panel_35, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
                            .addGap(18)
                            .addComponent(panel_36, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
                            .addGap(18)
                            .addComponent(cbx_messr)))
                    .addGap(39))
        );
        gl_panel_34.setVerticalGroup(
            gl_panel_34.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_34.createSequentialGroup()
                    .addGroup(gl_panel_34.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_34.createSequentialGroup()
                            .addGroup(gl_panel_34.createParallelGroup(Alignment.BASELINE)
                                .addComponent(panel_35, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addComponent(panel_36, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                            .addGap(9)
                            .addGroup(gl_panel_34.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblNewLabel_62)
                                .addComponent(cmb_cotd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(gl_panel_34.createSequentialGroup()
                            .addGap(17)
                            .addComponent(cbx_messr)))
                    .addContainerGap(3, Short.MAX_VALUE))
        );

        JLabel lblNewLabel_59 = new JLabel("от начала заболевания");
        lblNewLabel_59.setFont(new Font("Tahoma", Font.PLAIN, 11));

        sp_sv_time = new JSpinner();
        sp_sv_time.setFont(new Font("Tahoma", Font.PLAIN, 11));
        sp_sv_time.setModel(new SpinnerNumberModel(0, 0, 23, 1));

        JLabel lblNewLabel_60 = new JLabel("часов");
        lblNewLabel_60.setFont(new Font("Tahoma", Font.PLAIN, 11));

        JLabel lblNewLabel_61 = new JLabel("суток");
        lblNewLabel_61.setFont(new Font("Tahoma", Font.PLAIN, 11));

        sp_sv_day = new JSpinner();
        sp_sv_day.setFont(new Font("Tahoma", Font.PLAIN, 11));
        sp_sv_day.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
        GroupLayout gl_panel_36 = new GroupLayout(panel_36);
        gl_panel_36.setHorizontalGroup(
            gl_panel_36.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_36.createSequentialGroup()
                    .addComponent(lblNewLabel_59)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(lblNewLabel_60)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(sp_sv_time, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(lblNewLabel_61)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(sp_sv_day, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                    .addGap(19))
        );
        gl_panel_36.setVerticalGroup(
            gl_panel_36.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_36.createParallelGroup(Alignment.BASELINE)
                    .addComponent(lblNewLabel_59)
                    .addComponent(lblNewLabel_60)
                    .addComponent(sp_sv_time, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNewLabel_61)
                    .addComponent(sp_sv_day, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        panel_36.setLayout(gl_panel_36);

        cbx_gosp = new JCheckBox("");
        cbx_gosp.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                ChangeStateCheckbox();
            }
        });

        tf_datagosp = new CustomDateEditor();
        tf_timegosp = new CustomTimeEditor();
        GroupLayout gl_panel_35 = new GroupLayout(panel_35);
        gl_panel_35.setHorizontalGroup(
            gl_panel_35.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panel_35.createSequentialGroup()
                    .addComponent(tf_datagosp, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(tf_timegosp, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbx_gosp))
        );
        gl_panel_35.setVerticalGroup(
            gl_panel_35.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_35.createSequentialGroup()
                    .addGroup(gl_panel_35.createParallelGroup(Alignment.LEADING)
                        .addComponent(cbx_gosp)
                        .addGroup(gl_panel_35.createParallelGroup(Alignment.BASELINE)
                            .addComponent(tf_datagosp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_timegosp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_35.setLayout(gl_panel_35);
        panel_34.setLayout(gl_panel_34);

        JPanel panel_26 = new JPanel();

        tf_ntalon = new JTextField();
        tf_ntalon.setColumns(10);

        JPanel panel_27 = new JPanel();
        panel_27.setBorder(new TitledBorder(null, "Дата и время", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel panel_28 = new JPanel();
        panel_28.setBorder(new TitledBorder(null, "Кем направлен", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel panel_29 = new JPanel();
        panel_29.setBorder(new TitledBorder(null, "Диагноз направившего учреждения", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel panel_30 = new JPanel();
        panel_30.setBorder(new TitledBorder(null, "\u0414\u0438\u0430\u0433\u043D\u043E\u0437 \u043F\u0440\u0438\u0435\u043C\u043D\u043E\u0433\u043E \u043E\u0442\u0434\u0435\u043B\u0435\u043D\u0438\u044F", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel panel_31 = new JPanel();
        panel_31.setBorder(new TitledBorder(null, "Состояние пациента", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel panel_32 = new JPanel();
        panel_32.setBorder(new TitledBorder(null, "Вызов скорой помощи", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel panel_33 = new JPanel();
        panel_33.setBorder(new TitledBorder(null, "\u0416\u0430\u043B\u043E\u0431\u044B", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JLabel lblNewLabel_48 = new JLabel("№ талона");
        JLabel lblNewLabel_56 = new JLabel("Вид травмы");
        JLabel lblNewLabel_57 = new JLabel("Вид транспортировки");
        JLabel lblNewLabel_58 = new JLabel("Причина отказа в госпитализации");
        JLabel lblNist = new JLabel("№ ист. бол.");

        tf_nist = new JTextField();
        tf_nist.setColumns(10);
        GroupLayout gl_panel_25 = new GroupLayout(panel_25);
        gl_panel_25.setHorizontalGroup(
            gl_panel_25.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_25.createSequentialGroup()
                    .addGroup(gl_panel_25.createParallelGroup(Alignment.LEADING, false)
                        .addGroup(gl_panel_25.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(gl_panel_25.createParallelGroup(Alignment.LEADING, false)
                                .addGroup(gl_panel_25.createSequentialGroup()
                                    .addComponent(lblNist)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(tf_nist, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
                                .addComponent(panel_26, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
                                .addGroup(gl_panel_25.createSequentialGroup()
                                    .addComponent(lblNewLabel_48)
                                    .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_ntalon, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)))
                            .addGap(32)
                            .addComponent(panel_27, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18)
                            .addComponent(panel_28, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_panel_25.createSequentialGroup()
                            .addComponent(panel_29, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)
                            .addGap(18)
                            .addComponent(panel_30, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_panel_25.createSequentialGroup()
                            .addGroup(gl_panel_25.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(panel_33, 0, 0, Short.MAX_VALUE)
                                .addGroup(gl_panel_25.createSequentialGroup()
                                    .addComponent(panel_31, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(ComponentPlacement.UNRELATED)
                                    .addComponent(panel_32, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addGroup(gl_panel_25.createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_panel_25.createSequentialGroup()
                                    .addGap(60)
                                    .addComponent(lblNewLabel_58))
                                .addComponent(cmb_otkaz, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblNewLabel_56)
                                .addComponent(lblNewLabel_57)
                                .addComponent(cmb_trans, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmb_travm, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap())
        );
        gl_panel_25.setVerticalGroup(
            gl_panel_25.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_25.createSequentialGroup()
                    .addGroup(gl_panel_25.createParallelGroup(Alignment.LEADING, false)
                        .addGroup(gl_panel_25.createSequentialGroup()
                            .addComponent(panel_26, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addGroup(gl_panel_25.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblNewLabel_48)
                                .addComponent(tf_ntalon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(gl_panel_25.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblNist)
                                .addComponent(tf_nist, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(Alignment.TRAILING, gl_panel_25.createParallelGroup(Alignment.BASELINE)
                            .addComponent(panel_27, GroupLayout.PREFERRED_SIZE, 67, Short.MAX_VALUE)
                            .addComponent(panel_28, 0, 0, Short.MAX_VALUE)))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panel_25.createParallelGroup(Alignment.BASELINE)
                        .addComponent(panel_29, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                        .addComponent(panel_30, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panel_25.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_25.createSequentialGroup()
                            .addGroup(gl_panel_25.createParallelGroup(Alignment.BASELINE)
                                .addComponent(panel_31, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(panel_32, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(panel_33, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_panel_25.createSequentialGroup()
                            .addComponent(lblNewLabel_56)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(cmb_travm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(4)
                            .addComponent(lblNewLabel_57)
                            .addGap(5)
                            .addComponent(cmb_trans, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                            .addComponent(lblNewLabel_58)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(cmb_otkaz, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())))
        );

        ta_jal_pr = new JTextArea();
        ta_jal_pr.setFont(new Font("Tahoma", Font.PLAIN, 11));
        ta_jal_pr.setLineWrap(true);
        ta_jal_pr.setWrapStyleWord(true);
        GroupLayout gl_panel_33 = new GroupLayout(panel_33);
        gl_panel_33.setHorizontalGroup(
            gl_panel_33.createParallelGroup(Alignment.LEADING)
                .addComponent(ta_jal_pr, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
        );
        gl_panel_33.setVerticalGroup(
            gl_panel_33.createParallelGroup(Alignment.LEADING)
                .addComponent(ta_jal_pr, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );
        panel_33.setLayout(gl_panel_33);

        JLabel lblNewLabel_54 = new JLabel("Дата и время");
        JLabel lblNewLabel_55 = new JLabel("Номер");

        tf_smpn = new JTextField();
        tf_smpn.setColumns(10);

        cbx_smp = new JCheckBox("");
        cbx_smp.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                ChangeStateCheckbox();
            }
        });

        tf_datasmp = new CustomDateEditor();
        tf_timesmp = new CustomTimeEditor();
        GroupLayout gl_panel_32 = new GroupLayout(panel_32);
        gl_panel_32.setHorizontalGroup(
            gl_panel_32.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_32.createSequentialGroup()
                    .addGroup(gl_panel_32.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_32.createSequentialGroup()
                            .addComponent(lblNewLabel_55)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(tf_smpn, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_panel_32.createSequentialGroup()
                            .addGroup(gl_panel_32.createParallelGroup(Alignment.TRAILING, false)
                                .addComponent(tf_datasmp)
                                .addComponent(lblNewLabel_54, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(4)
                            .addComponent(tf_timesmp, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(cbx_smp)))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gl_panel_32.setVerticalGroup(
            gl_panel_32.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_32.createSequentialGroup()
                    .addGroup(gl_panel_32.createParallelGroup(Alignment.TRAILING)
                        .addComponent(cbx_smp)
                        .addGroup(gl_panel_32.createSequentialGroup()
                            .addComponent(lblNewLabel_54)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addGroup(gl_panel_32.createParallelGroup(Alignment.BASELINE)
                                .addComponent(tf_datasmp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(tf_timesmp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_panel_32.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewLabel_55)
                        .addComponent(tf_smpn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_32.setLayout(gl_panel_32);

        cbx_nalz = new JCheckBox("чесотка");
        cbx_nalp = new JCheckBox("педикулез");

        JLabel lblNewLabel_51 = new JLabel("Опьянение");
        JLabel lblNewLabel_52 = new JLabel("Температура");
        JLabel lblNewLabel_53 = new JLabel("Давление");

        tf_toc = new JTextField();
        tf_toc.setColumns(10);

        tf_ad = new JTextField();
        tf_ad.setColumns(10);
        GroupLayout gl_panel_31 = new GroupLayout(panel_31);
        gl_panel_31.setHorizontalGroup(
            gl_panel_31.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_31.createSequentialGroup()
                    .addComponent(cbx_nalp)
                    .addGap(20)
                    .addComponent(lblNewLabel_53)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(tf_ad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGroup(gl_panel_31.createParallelGroup(Alignment.TRAILING, false)
                    .addGroup(gl_panel_31.createSequentialGroup()
                        .addGap(5)
                        .addComponent(lblNewLabel_51)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(cmb_alk, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(Alignment.LEADING, gl_panel_31.createSequentialGroup()
                        .addComponent(cbx_nalz)
                        .addGap(18)
                        .addComponent(lblNewLabel_52)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(tf_toc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        );
        gl_panel_31.setVerticalGroup(
            gl_panel_31.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_31.createSequentialGroup()
                    .addGroup(gl_panel_31.createParallelGroup(Alignment.BASELINE)
                        .addComponent(cmb_alk, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNewLabel_51))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panel_31.createParallelGroup(Alignment.BASELINE)
                        .addComponent(cbx_nalz)
                        .addComponent(lblNewLabel_52)
                        .addComponent(tf_toc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panel_31.createParallelGroup(Alignment.BASELINE)
                        .addComponent(cbx_nalp)
                        .addComponent(tf_ad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNewLabel_53))
                    .addGap(0, 0, Short.MAX_VALUE))
        );
        panel_31.setLayout(gl_panel_31);

        tf_diag_p = new JTextField();
        tf_diag_p.setColumns(10);

        ta_diag_p = new JTextArea();
        ta_diag_p.setFont(new Font("Monospaced", Font.PLAIN, 12));
        ta_diag_p.setWrapStyleWord(true);
        ta_diag_p.setLineWrap(true);
        GroupLayout gl_panel_30 = new GroupLayout(panel_30);
        gl_panel_30.setHorizontalGroup(
            gl_panel_30.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_30.createSequentialGroup()
                    .addComponent(tf_diag_p, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(ta_diag_p, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE))
        );
        gl_panel_30.setVerticalGroup(
            gl_panel_30.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_30.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(tf_diag_p, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(17, Short.MAX_VALUE))
                .addComponent(ta_diag_p, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );
        panel_30.setLayout(gl_panel_30);

        tf_diag_n = new JTextField();
        tf_diag_n.setColumns(10);

        ta_diag_n = new JTextArea();
        ta_diag_n.setFont(new Font("Monospaced", Font.PLAIN, 12));
        ta_diag_n.setWrapStyleWord(true);
        ta_diag_n.setLineWrap(true);
        GroupLayout gl_panel_29 = new GroupLayout(panel_29);
        gl_panel_29.setHorizontalGroup(
            gl_panel_29.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_29.createSequentialGroup()
                    .addComponent(tf_diag_n, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(ta_diag_n, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        gl_panel_29.setVerticalGroup(
            gl_panel_29.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_29.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(tf_diag_n, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(17, Short.MAX_VALUE))
                .addComponent(ta_diag_n, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );
        panel_29.setLayout(gl_panel_29);

        GroupLayout gl_panel_28 = new GroupLayout(panel_28);
        gl_panel_28.setHorizontalGroup(
            gl_panel_28.createParallelGroup(Alignment.LEADING)
                .addComponent(cmb_naprav, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
                .addComponent(cmb_org, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
        );
        gl_panel_28.setVerticalGroup(
            gl_panel_28.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_28.createSequentialGroup()
                    .addComponent(cmb_naprav, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                    .addComponent(cmb_org, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        panel_28.setLayout(gl_panel_28);

        JLabel lblNewLabel_49 = new JLabel("Поступления");
        JLabel lblNewLabel_50 = new JLabel("Осмотра");

        tf_datap = new CustomDateEditor();
        tf_timep = new CustomTimeEditor();
        tf_dataosm = new CustomDateEditor();
        tf_timeosm = new CustomTimeEditor();
        GroupLayout gl_panel_27 = new GroupLayout(panel_27);
        gl_panel_27.setHorizontalGroup(
            gl_panel_27.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_27.createSequentialGroup()
                    .addGroup(gl_panel_27.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblNewLabel_49)
                        .addComponent(lblNewLabel_50))
                    .addGroup(gl_panel_27.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_27.createSequentialGroup()
                            .addGap(10)
                            .addComponent(tf_datap, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_panel_27.createSequentialGroup()
                            .addGap(11)
                            .addComponent(tf_dataosm, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panel_27.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(tf_timeosm, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tf_timep, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                    .addGap(4))
        );
        gl_panel_27.setVerticalGroup(
            gl_panel_27.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_27.createSequentialGroup()
                    .addGroup(gl_panel_27.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblNewLabel_49)
                        .addGroup(gl_panel_27.createSequentialGroup()
                            .addComponent(tf_timep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(tf_timeosm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_panel_27.createSequentialGroup()
                            .addComponent(tf_datap, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addGroup(gl_panel_27.createParallelGroup(Alignment.BASELINE)
                                .addComponent(tf_dataosm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblNewLabel_50))))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_27.setLayout(gl_panel_27);

        rbtn_plan = new JRadioButton("плановое");
        btnGroup_plextr.add(rbtn_plan);

        rbtn_extr = new JRadioButton("экстренное");
        btnGroup_plextr.add(rbtn_extr);
        GroupLayout gl_panel_26 = new GroupLayout(panel_26);
        gl_panel_26.setHorizontalGroup(
            gl_panel_26.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_26.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(rbtn_plan)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(rbtn_extr)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gl_panel_26.setVerticalGroup(
            gl_panel_26.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_26.createSequentialGroup()
                    .addGroup(gl_panel_26.createParallelGroup(Alignment.BASELINE)
                        .addComponent(rbtn_plan, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
                        .addComponent(rbtn_extr))
                    .addContainerGap(6, Short.MAX_VALUE))
        );
        panel_26.setLayout(gl_panel_26);
        panel_25.setLayout(gl_panel_25);

        JButton btnNew_priem = new JButton("Новое обращение");
        btnNew_priem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newPriem = tbl_priem.addExternalItem();
                rbtn_plan.requestFocus();
                curId = 0;
                curNgosp = 0;
                NewPriemInfo();
            }
        });

        JButton btnSave_priem = new JButton("Сохранить");
        btnSave_priem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                SavePriemInfo();
            }
        });

        JButton btnDel_priem = new JButton("Удалить");
        btnDel_priem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
              try{
                curId = tbl_priem.getSelectedItem().id;
                tbl_priem.requestFocus();
                tbl_priem.deleteSelectedRow();
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
        });
        GroupLayout gl_panel_24 = new GroupLayout(panel_24);
        gl_panel_24.setHorizontalGroup(
            gl_panel_24.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_24.createSequentialGroup()
                    .addGap(40)
                    .addComponent(btnNew_priem)
                    .addGap(239)
                    .addComponent(btnSave_priem)
                    .addGap(88)
                    .addComponent(btnDel_priem, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gl_panel_24.setVerticalGroup(
            gl_panel_24.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_24.createSequentialGroup()
                    .addGroup(gl_panel_24.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnSave_priem)
                        .addComponent(btnDel_priem)
                        .addComponent(btnNew_priem))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_24.setLayout(gl_panel_24);

        JScrollPane scrollPane_3 = new JScrollPane();
        GroupLayout gl_panel_23 = new GroupLayout(panel_23);
        gl_panel_23.setHorizontalGroup(
            gl_panel_23.createParallelGroup(Alignment.LEADING)
                .addComponent(scrollPane_3, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 663, GroupLayout.PREFERRED_SIZE)
        );
        gl_panel_23.setVerticalGroup(
            gl_panel_23.createParallelGroup(Alignment.LEADING)
                .addComponent(scrollPane_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
        );

        tbl_priem = new CustomTable<>(true, false, AllGosp.class, 3,"N ист. бол.",4,"Дата",5,"Отделение",6,"DS прием", 7, "Наименование");
        tbl_priem.setDateField(1);
        tbl_priem.setPreferredWidths(70,70,65,60,395);
        tbl_priem.setFillsViewportHeight(true);
        tbl_priem.setShowVerticalLines(true);
        tbl_priem.setShowHorizontalLines(true);
        scrollPane_3.setViewportView(tbl_priem);
        panel_23.setLayout(gl_panel_23);
        tpPriem.setLayout(gl_tpPriem);


        tbl_priem.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                if (tbl_priem.getSelectedItem() !=  null) {
                changePatientPriemInfo(curPatientId);
                }
            }
        }
        });

        //удалить
        tbl_priem.registerDeleteSelectedRowListener(new CustomTableItemChangeEventListener<AllGosp>() {
        @Override
        public boolean doAction(CustomTableItemChangeEvent<AllGosp> event) {
            try {
                MainForm.tcl.deleteGosp(curId);
            } catch (TException e) {
                e.printStackTrace();
                return false;
            }
            return true;
            }
        });
        
        JButton btnPrint_ambk = new JButton("Амбул.карты");
        btnPrint_ambk.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	}
        });
        btnPrint_ambk.setToolTipText("Печать титульного листа амбулаторной карты");
        
        JButton btnPrint_istb = new JButton("Ист.болезни");
        btnPrint_istb.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	}
        });
        btnPrint_istb.setToolTipText("Печать титульного листа истории болезни");

        GroupLayout gl_pl_print = new GroupLayout(pl_print);
        gl_pl_print.setHorizontalGroup(
        	gl_pl_print.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_pl_print.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(btnPrint_ambk)
        			.addGap(26)
        			.addComponent(btnPrint_istb)
        			.addContainerGap(27, Short.MAX_VALUE))
        );
        gl_pl_print.setVerticalGroup(
        	gl_pl_print.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, gl_pl_print.createSequentialGroup()
        			.addGroup(gl_pl_print.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnPrint_ambk)
        				.addComponent(btnPrint_istb))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        if (MainForm.authInfo.getCslu() != 1) {
        	tbMain.remove(tpSign);
        	tbMain.remove(tpPriem);
        	btnPrint_istb.setVisible(false);
        }
        
        pl_print.setLayout(gl_pl_print);
        contentPane.setLayout(gl_contentPane);
    }

	public void onConnect() {
		try {
			cmb_status.setData(MainForm.tcl.getSgrp());
			cmb_Polis_doc_pr.setData(MainForm.tcl.getPomsTdoc());
			cmb_oms_doc.setData(MainForm.tcl.getPomsTdoc());
			cmb_tdoc.setData(MainForm.tcl.getTdoc());
			cmb_ishod.setData(MainForm.tcl.getABB());
			cmb_cotd.setData(MainForm.tcl.getOtdForCurrentLpu(MainForm.authInfo.clpu));
			cmb_travm.setData(MainForm.tcl.getAI0());
			cmb_trans.setData(MainForm.tcl.getVTR());
			cmb_otkaz.setData(MainForm.tcl.getAF0());
			cmb_alk.setData(MainForm.tcl.getALK());
			cmb_naprav.setData(MainForm.tcl.getNaprav());
//			cmb_Tdoc_pr.setData(MainForm.tcl.getTdoc());
			cmb_Tdoc_pr.setVisible(false);
			cmb_ogrn.setSelectedItem(null);
			cmb_org.setSelectedItem(null); 
		} catch (TException e) {
			e.printStackTrace();
			MainForm.conMan.reconnect(e);
		}
	}
    //слушатель таб контрола персональной информации о пациенте
    final ChangeListener  tpPersonalChangeListener= new ChangeListener() {
        public void stateChanged(ChangeEvent changeEvent) {
//            curPatientId = tbl_patient.getSelectedItem().npasp;
            changePatientPersonalInfo(curPatientId);
        }
    };

    //слушатель таб контрола о льготе
    final ChangeListener  tpLgotaChangeListener= new ChangeListener() {
        public void stateChanged(ChangeEvent changeEvent) {
            changePatientLgotaInfo(curPatientId);
        }
    };

    //слушатель таб контрола о категории
    final ChangeListener  tpKategChangeListener= new ChangeListener() {
        public void stateChanged(ChangeEvent changeEvent) {
            changePatientKategInfo(curPatientId);
        }
    };

    //слушатель таб контрола о представителе
    final ChangeListener  tpAgentChangeListener= new ChangeListener() {
        public void stateChanged(ChangeEvent changeEvent) {
            changePatientAgentInfo(curPatientId);
        }
    };

    //слушатель таб контрола о сигнальн отм
    final ChangeListener  tpSignChangeListener= new ChangeListener() {
        public void stateChanged(ChangeEvent changeEvent) {
            changePatientSignInfo(curPatientId);
        }
    };

    //слушатель таб контрола о приемн отд
    final ChangeListener  tpPriemChangeListener= new ChangeListener() {
        public void stateChanged(ChangeEvent changeEvent) {
            selectAllPatientPriemInfo(curPatientId);
        }
    };

    // слушатель главного таб контрола
    final ChangeListener  mainChangeListener= new ChangeListener() {

        public void stateChanged(ChangeEvent changeEvent) {
                if (tbMain.getSelectedIndex() == 0){
                  tpPersonalChangeListener.stateChanged(new ChangeEvent(tpPersonal));
                }
                if (tbMain.getSelectedIndex() == 1){
                  tpLgotaChangeListener.stateChanged(new ChangeEvent(tpLgota));
                }
                if (tbMain.getSelectedIndex() == 2){
                  tpKategChangeListener.stateChanged(new ChangeEvent(tpKateg));
                }
                if (tbMain.getSelectedIndex() == 3){
                  tpAgentChangeListener.stateChanged(new ChangeEvent(tpAgent));
                }
                if (tbMain.getSelectedIndex() == 4){
                  tpSignChangeListener.stateChanged(new ChangeEvent(tpSign));
                }
                if (tbMain.getSelectedIndex() == 5){
                  tpPriemChangeListener.stateChanged(new ChangeEvent(tpPriem));
                }
          }
    };

    // просмотр  информации о пациенте
    private void changePatientPersonalInfo(int PatId){
        try {
            if (PatId == 0)
                return;
            NewPatient();
            PersonalInfo = MainForm.tcl.getPatientFullInfo(PatId);
            if (PersonalInfo.getFam() != null){
                tfFam.setText(PersonalInfo.fam.trim());
            }
            if (PersonalInfo.getIm() != null){
                tfIm.setText(PersonalInfo.im.trim());
            }
            if (PersonalInfo.getOt() != null){
                tfOt.setText(PersonalInfo.ot.trim());
            }
            if (PersonalInfo.isSetDatar()){
                tfDr.setDate(PersonalInfo.datar);
                //tfDr.setText(sdf.format(new Date(PersonalInfo.datar)));
            }
            if (PersonalInfo.getAdmAddress().region != null){
                tf_Adm_obl.setText(PersonalInfo.admAddress.region.trim());
            }
            if (PersonalInfo.getAdmAddress().city != null){
                tf_Adm_gorod.setText(PersonalInfo.admAddress.city.trim());
            }
            if (PersonalInfo.getAdmAddress().street != null){
                tf_Adm_ul.setText(PersonalInfo.admAddress.street.trim());
            }
            if (PersonalInfo.getAdmAddress().house != null){
                tf_Adm_dom.setText(PersonalInfo.admAddress.house.trim());
            }
            if (PersonalInfo.getAdmAddress().flat != null){
                tf_Adm_kv.setText(PersonalInfo.admAddress.flat.trim());
            }
            if (PersonalInfo.getAdpAddress().region != null){
                tf_Adp_obl.setText(PersonalInfo.adpAddress.region.trim());
            }
            if (PersonalInfo.getAdpAddress().city != null){
                tf_Adp_gorod.setText(PersonalInfo.adpAddress.city.trim());
            }
            if (PersonalInfo.getAdpAddress().street != null){
                tf_Adp_ul.setText(PersonalInfo.adpAddress.street.trim());
            }
            if (PersonalInfo.getAdpAddress().house != null){
                tf_Adp_dom.setText(PersonalInfo.adpAddress.house.trim());
            }
            if (PersonalInfo.getAdpAddress().flat != null){
                tf_Adp_kv.setText(PersonalInfo.adpAddress.flat.trim());
            }
            if (PersonalInfo.getNamemr() != null){
                tfMrname.setText(PersonalInfo.namemr.trim());
            }
            if (PersonalInfo.getMrab() != null){
                tfMr.setText(PersonalInfo.mrab.trim());
            }
            if (PersonalInfo.getProf() != null){
                tfDolj.setText(PersonalInfo.prof.trim());
            }
            if (PersonalInfo.getTel() != null){
                tfTel.setText(PersonalInfo.tel.trim());
            }
            if (PersonalInfo.getSnils() != null){
                tf_Snils.setText(PersonalInfo.snils.trim());
            }
            if (PersonalInfo.odoc != null){
                tf_Odoc.setText(PersonalInfo.odoc.trim());
            }
            if (PersonalInfo.getDocser() != null){
                tf_serdoc.setText(PersonalInfo.docser.trim());
            }
            if (PersonalInfo.getDocnum() != null){
                tf_nomdoc.setText(PersonalInfo.docnum.trim());
            }
            if (PersonalInfo.getPolis_dms().ser != null){
                tf_dms_ser.setText(PersonalInfo.polis_dms.ser.trim());
            }
            if (PersonalInfo.getPolis_dms().nom != null){
                tf_dms_nom.setText(PersonalInfo.polis_dms.nom.trim());
            }
            if (PersonalInfo.getPolis_oms().ser != null){
                tf_oms_ser.setText(PersonalInfo.polis_oms.ser.trim());
            }
            if (PersonalInfo.getPolis_dms().nom != null){
                tf_oms_nom.setText(PersonalInfo.polis_oms.nom.trim());
            }
            if (PersonalInfo.getPolis_oms().strg != 0){
                tf_oms_smo.setText(Integer.toString(PersonalInfo.polis_oms.strg));
            }
            if (PersonalInfo.getPolis_dms().strg != 0){
                tf_dms_smo.setText(Integer.toString(PersonalInfo.polis_dms.strg));
            }
            if (PersonalInfo.isSetCpol_pr()){
                tf_Cpol.setText(Integer.toString(PersonalInfo.cpol_pr));
            }
            //if (PersonalInfo.isSetNambk()){
            if (PersonalInfo.getNambk().nambk != null){
                tf_Nambk.setText(PersonalInfo.nambk.nambk.trim());
            }
            if (PersonalInfo.getNambk().nuch != 0){
                tf_Nuch.setText(Integer.toString(PersonalInfo.nambk.nuch));
            }
            if (PersonalInfo.getNambk().datapr != 0){
                tf_datapr.setDate(PersonalInfo.nambk.datapr);
                //tf_datapr.setText(sdf.format(new Date(PersonalInfo.nambk.datapr)));
            }
            if (PersonalInfo.getNambk().dataot != 0){
                tf_dataot.setDate(PersonalInfo.nambk.dataot);
//				tf_dataot.setText(sdf.format(new Date(PersonalInfo.nambk.dataot)));
            }
//			if (PersonalInfo.getDatadoc() != 0){
            if (PersonalInfo.isSetDatadoc()){
                tf_datadoc.setDate(PersonalInfo.datadoc);
//				tf_datadoc.setText(sdf.format(new Date(PersonalInfo.datadoc)));
            }
            if (PersonalInfo.isSetPol()){
                rbtn_pol_m.setSelected(PersonalInfo.pol == 1);
                rbtn_pol_j.setSelected(PersonalInfo.pol == 2);
            }
            if (PersonalInfo.getSgrp() != 0) {
                cmb_status.setSelectedPcod(PersonalInfo.sgrp);
            }
            if (PersonalInfo.getPolis_oms().tdoc != 0){
                cmb_oms_doc.setSelectedPcod(PersonalInfo.polis_oms.tdoc);
            }
            if (PersonalInfo.getNambk().ishod != 0) {
                cmb_ishod.setSelectedPcod(PersonalInfo.nambk.ishod);
            }
            if (PersonalInfo.getTdoc() != 0) {
                cmb_tdoc.setSelectedPcod(PersonalInfo.tdoc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void NewPatient(){
        try {
            tfFam.setText(null);
            tfIm.setText(null);
            tfOt.setText(null);
            tfDr.setValue(null);
            tf_Adm_obl.setText(null);
            tf_Adm_gorod.setText(null);
            tf_Adm_ul.setText(null);
            tf_Adm_dom.setText(null);
            tf_Adm_kv.setText(null);
            tf_Adp_obl.setText(null);
            tf_Adp_gorod.setText(null);
            tf_Adp_ul.setText(null);
            tf_Adp_dom.setText(null);
            tf_Adp_kv.setText(null);
            tfMrname.setText(null);
            tfMr.setText(null);
            tfDolj.setText(null);
            tfTel.setText(null);
            tf_Cpol.setText(null);
            tf_Nuch.setText(null);
            tf_Nambk.setText(null);
            tf_datapr.setValue(null);
            tf_dataot.setValue(null);
            tf_datadoc.setValue(null);
            tf_Snils.setText(null);
            tf_Odoc.setText(null);
            tf_serdoc.setText(null);
            tf_nomdoc.setText(null);
            tf_dms_ser.setText(null);
            tf_dms_nom.setText(null);
            tf_oms_ser.setText(null);
            tf_oms_nom.setText(null);
            tf_oms_smo.setText(null);
            tf_dms_smo.setText(null);
            btnGroup_pol.clearSelection();
            cmb_status.setSelectedIndex(-1);
            cmb_oms_doc.setSelectedIndex(-1);
            cmb_ishod.setSelectedIndex(-1);
            cmb_tdoc.setSelectedIndex(-1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // обновление информации о льготе
    private void changePatientLgotaInfo(int PatId){
        try {
            tbl_lgota.setData(new ArrayList<Lgota>());
            LgotaInfo = MainForm.tcl.getLgota(PatId);
            tbl_lgota.setData(LgotaInfo);
        } catch (LgotaNotFoundException lnfe) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // обновление информации о категории
    private void changePatientKategInfo(int PatId){
        try {
            tbl_kateg.setData(new ArrayList<Kontingent>());
            KontingentInfo = MainForm.tcl.getKontingent(PatId);
            tbl_kateg.setData(KontingentInfo);
        } catch (KontingentNotFoundException knfe) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // обновление информации о представителе
    private void changePatientAgentInfo(int PatId){
        try {
            NewAgent();
            AgentInfo = MainForm.tcl.getAgent(PatId);
            if (AgentInfo.getFam() != null)	tf_Fam_pr.setText(AgentInfo.fam.trim());
            if (AgentInfo.getIm() != null) tf_Im_pr.setText(AgentInfo.im.trim());
            if (AgentInfo.getOt() != null) tf_Ot_pr.setText(AgentInfo.ot.trim());
            if (AgentInfo.isSetDatar())	tf_dr_pr.setDate(AgentInfo.datar);
            if (AgentInfo.getBirthplace() != null) tf_Mr_pr.setText(AgentInfo.birthplace.trim());
            if (AgentInfo.getSpolis() != null) tf_Polis_ser_pr.setText(AgentInfo.spolis.trim());
            if (AgentInfo.getNpolis() != null) tf_Polis_nom_pr.setText(AgentInfo.npolis.trim());
            if (AgentInfo.getName_str() != null) tf_Name_sk_pr.setText(AgentInfo.name_str.trim());
            if (AgentInfo.getDocser() != null) tf_Ser_doc_pr.setText(AgentInfo.docser.trim());
            if (AgentInfo.getDocnum() != null) tf_Nomdoc_pr.setText(AgentInfo.docnum.trim());
            if (AgentInfo.isSetPol()){
                rbtn_pol_pr_m.setSelected(AgentInfo.pol == 1);
                rbtn_pol_pr_j.setSelected(AgentInfo.pol == 2);
            }
//FIXME            if (AgentInfo.getOgrn_str() != null) cmb_ogrn.setSelectedPcod(AgentInfo.getOgrn_str());
            if (AgentInfo.getVpolis() != 0) cmb_Polis_doc_pr.setSelectedPcod(AgentInfo.getVpolis());
            if (AgentInfo.getTdoc() != 0) cmb_Tdoc_pr.setSelectedPcod(AgentInfo.getTdoc());
        } catch (AgentNotFoundException anfe) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void NewAgent(){
        try {
            tf_Fam_pr.setText(null);
            tf_Im_pr.setText(null);
            tf_Ot_pr.setText(null);
            tf_dr_pr.setValue(null);
            tf_Mr_pr.setText(null);
            tf_Polis_ser_pr.setText(null);
            tf_Polis_nom_pr.setText(null);
            tf_Name_sk_pr.setText(null);
            tf_Ser_doc_pr.setText(null);
            tf_Nomdoc_pr.setText(null);
            tf_Fam_pr.setText(null);
            tf_Fam_pr.setText(null);
            tf_Fam_pr.setText(null);
            btnGroup_pol_pr.clearSelection();
            cmb_Tdoc_pr.setSelectedItem(null);
            cmb_Polis_doc_pr.setSelectedItem(null);
            cmb_ogrn.setSelectedItem(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // обновление информации сигн отм
    private void changePatientSignInfo(int PatId){
        try {
            NewSign();
            SignInfo = MainForm.tcl.getSign(PatId);
            if (SignInfo.getGrup().trim() != null){
                rbtn_gk1.setSelected(SignInfo.grup.charAt(0) == '1');
                rbtn_gk2.setSelected(SignInfo.grup.charAt(0) == '2');
                rbtn_gk3.setSelected(SignInfo.grup.charAt(0) == '3');
                rbtn_gk4.setSelected(SignInfo.grup.charAt(0) == '4');
            }
            if (SignInfo.getPh() != null){
                rbtn_rf1.setSelected(SignInfo.ph.charAt(0) == '+');
                rbtn_rf2.setSelected(SignInfo.ph.charAt(0) == '-');
            }
            if (SignInfo.getVred() != null){
                rbtn_vp1.setSelected(SignInfo.vred.charAt(0) == '1');
                rbtn_vp2.setSelected(SignInfo.vred.charAt(1) == '1');
                rbtn_vp3.setSelected(SignInfo.vred.charAt(2) == '1');
            }
            if (SignInfo.getAllerg() != null){
                ta_allerg.setText(SignInfo.allerg.trim());
            }
            if (SignInfo.getFarmkol() != null){
                ta_farm.setText(SignInfo.farmkol.trim());
            }
            if (SignInfo.getVitae() != null){
                ta_vitae.setText(SignInfo.vitae.trim());
            }
        } catch (SignNotFoundException snfe) {
            System.out.println("Информации нет.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void NewSign(){
        try {
            btnGroup_gk.clearSelection();
            btnGroup_rf.clearSelection();
            rbtn_vp1.setSelected(false);
            rbtn_vp2.setSelected(false);
            rbtn_vp3.setSelected(false);
            ta_allerg.setText(null);
            ta_farm.setText(null);
            ta_vitae.setText(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // просмотр всех обращений
    private void selectAllPatientPriemInfo(int PatId){
      try{
        tbl_priem.setData(new ArrayList<AllGosp>());
        AllGospInfo = MainForm.tcl.getAllGosp(PatId);
        tbl_priem.setData(AllGospInfo);
      } catch (GospNotFoundException gnfe) {
            System.out.println("Обращений нет.");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    // просмотр информации о госпитализациях
    private void changePatientPriemInfo(int PatId){
        if (tbl_priem.getSelectedItem() == null)
            return;
        try {
            curId = tbl_priem.getSelectedItem().id;
            curNgosp = tbl_priem.getSelectedItem().ngosp;
            Id_gosp = MainForm.tcl.getGosp(curId);
//			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm");
            NewPriemInfo();
            if (Id_gosp.getJalob() != null){
                ta_jal_pr.setText(Id_gosp.jalob.trim());
            }
            if (Id_gosp.isSetPl_extr()){
                rbtn_plan.setSelected(Id_gosp.pl_extr == 1);
                rbtn_extr.setSelected(Id_gosp.pl_extr == 2);
            }
            if (Id_gosp.isSetNal_z()){
                cbx_nalz.setSelected(Id_gosp.nal_z);
            }
            if (Id_gosp.isSetNal_p()){
                cbx_nalp.setSelected(Id_gosp.nal_p);
            }
            if (Id_gosp.isSetMessr()){
                cbx_messr.setSelected(Id_gosp.messr);
            }
            if (Id_gosp.getDiag_n() != null){
                tf_diag_n.setText(Id_gosp.diag_n.trim());
            }
            if (Id_gosp.getDiag_p() != null){
                tf_diag_p.setText(Id_gosp.diag_p.trim());
            }
            if (Id_gosp.getNamed_n() != null){
                ta_diag_n.setText(Id_gosp.named_n.trim());
            }
            if (Id_gosp.getNamed_p() != null){
                ta_diag_p.setText(Id_gosp.named_p.trim());
            }
            if (Id_gosp.getToc() != null){
                tf_toc.setText(Id_gosp.toc.trim());
            }
            if (Id_gosp.getAd() != null){
                tf_ad.setText(Id_gosp.ad.trim());
            }

            if (Id_gosp.getSmp_num() != 0) {
                tf_smpn.setText(Integer.toString(Id_gosp.smp_num));
                tf_smpn.setEnabled(true);
            }
            if (Id_gosp.getSmp_data() != 0) {
                tf_datasmp.setDate(Id_gosp.smp_data);
                tf_timesmp.setTime(Id_gosp.smp_time);
                tf_datasmp.setEnabled(true);
                tf_timesmp.setEnabled(true);
                cbx_smp.setSelected(true);
            }

            if (Id_gosp.getDatap() != 0) {
//				sp_datap.setValue(sdf.format(new Date(Id_gosp.datap)));
                tf_datap.setDate(Id_gosp.datap);
                tf_timep.setTime(Id_gosp.vremp);
            }
            if (Id_gosp.getDataosm() != 0) {
                tf_dataosm.setDate(Id_gosp.dataosm);
                tf_timeosm.setTime(Id_gosp.vremosm);
            }
            if (Id_gosp.getDatagos()!= 0) {
                tf_datagosp.setDate(Id_gosp.datagos);
                tf_timegosp.setTime(Id_gosp.vremgos);
                tf_datagosp.setEnabled(true);
                tf_timegosp.setEnabled(true);
                cbx_gosp.setSelected(true);
            }
            if (Id_gosp.isSetNtalon()) {
                tf_ntalon.setText(Integer.toString(Id_gosp.ntalon));
            }
            if (Id_gosp.isSetNist()) {
                tf_nist.setText(Integer.toString(Id_gosp.nist));
            }
            if (Id_gosp.isSetSv_time()) {
                sp_sv_time.setValue(Id_gosp.sv_time);
            }
            if (Id_gosp.isSetSv_day()) {
                sp_sv_day.setValue(Id_gosp.sv_day);
            }

            if (Id_gosp.getCotd() != 0) {
                cmb_cotd.setSelectedPcod(Id_gosp.cotd);
            }
            if (Id_gosp.getAlkg() != 0) {
                cmb_alk.setSelectedPcod(Id_gosp.alkg);
            }
            if (Id_gosp.getVidtr() != 0) {
                cmb_travm.setSelectedPcod(Id_gosp.vidtr);
            }
            if (Id_gosp.getVid_trans() != 0) {
                cmb_trans.setSelectedPcod(Id_gosp.vid_trans);
            }
            if (Id_gosp.getPr_out() != 0) {
                cmb_otkaz.setSelectedPcod(Id_gosp.pr_out);
            }

            if (Id_gosp.getNaprav() != null) {
                cmb_naprav.setSelectedPcod(Id_gosp.naprav);
                if (Id_gosp.getN_org() != 0) {
                    cmb_org.setSelectedPcod(Id_gosp.n_org);
                }
            }
        } catch (GospNotFoundException gnfe) {
            System.out.println("Информации нет.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void ChangeStateCheckbox(){
        try {
            tf_datasmp.setEnabled(cbx_smp.isSelected());
            tf_timesmp.setEnabled(cbx_smp.isSelected());
            tf_smpn.setEnabled(cbx_smp.isSelected());
            tf_datagosp.setEnabled(cbx_gosp.isSelected());
            tf_timegosp.setEnabled(cbx_gosp.isSelected());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // новое обращение
    private void NewPriemInfo(){
        try {
            btnGroup_plextr.clearSelection();
            cbx_nalz.setSelected(false);
            cbx_nalp.setSelected(false);
            cbx_messr.setSelected(false);
            cbx_smp.setSelected(false);
            cbx_gosp.setSelected(false);
            tf_smpn.setEnabled(false);
            tf_datap.setEnabled(true);
            tf_dataosm.setEnabled(true);
            tf_datagosp.setEnabled(false);
            tf_datasmp.setEnabled(false);
            tf_timep.setEnabled(true);
            tf_timeosm.setEnabled(true);
            tf_timegosp.setEnabled(false);
            tf_timesmp.setEnabled(false);

//			tf_datap.setValue(null);
//			tf_dataosm.setValue(null);
            tf_datap.setDate(System.currentTimeMillis());
            tf_dataosm.setDate(System.currentTimeMillis());
            tf_timep.setTime(System.currentTimeMillis());
            tf_timeosm.setTime(System.currentTimeMillis());

//			tf_timep.setValue(null);
//			tf_timeosm.setValue(null);
            tf_datagosp.setValue(null);
            tf_datasmp.setValue(null);
            tf_timegosp.setValue(null);
            tf_timesmp.setValue(null);
            tf_ntalon.setText(null);
            tf_nist.setText(null);
            tf_diag_n.setText(null);
            tf_diag_p.setText(null);
            ta_diag_n.setText(null);
            ta_diag_p.setText(null);
            ta_jal_pr.setText(null);
            tf_toc.setText(null);
            tf_ad.setText(null);
            tf_smpn.setText(null);
            sp_sv_time.setValue(0);
            sp_sv_day.setValue(0);

            cmb_org.setSelectedItem(null);
            cmb_cotd.setSelectedItem(null);
            cmb_alk.setSelectedItem(null);
            cmb_naprav.setSelectedItem(null);
            cmb_travm.setSelectedItem(null);
            cmb_trans.setSelectedItem(null);
            cmb_otkaz.setSelectedItem(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void SavePriemInfo(){
        try {
//			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
//			SimpleDateFormat stf = new SimpleDateFormat("HH:mm");
            Id_gosp = new Gosp();
            Id_gosp.setNpasp(curPatientId);
            Id_gosp.setNgosp(curNgosp);
            Id_gosp.setId(curId);
            Id_gosp.setDataz(new Date().getTime());

            Id_gosp.setCotd_p(MainForm.authInfo.cpodr);
            Id_gosp.setCuser(MainForm.authInfo.pcod);
            Id_gosp.setNist(1); //подумать

//		    System.out.println(((Date) sp_dataosm.getValue()).getTime());
//		    System.out.println(stf.format((Date) sp_dataosm.getValue()));
            if (tf_datap.getDate() != null) Id_gosp.setDatap(tf_datap.getDate().getTime());
            if (tf_dataosm.getDate() != null) Id_gosp.setDataosm(tf_dataosm.getDate().getTime());

            Id_gosp.setVremosm(tf_timeosm.getTime().getTime());
            Id_gosp.setVremp(tf_timep.getTime().getTime());
            if (cbx_smp.isSelected()){
            	if (tf_datasmp.getDate() != null) Id_gosp.setSmp_data(tf_datasmp.getDate().getTime());
                Id_gosp.setSmp_time(tf_timesmp.getTime().getTime());
            }
            if (cbx_gosp.isSelected()){
            	if (tf_datagosp.getDate() != null) Id_gosp.setDatagos(tf_datagosp.getDate().getTime());
                Id_gosp.setVremgos(tf_timegosp.getTime().getTime());
            }

            if (sp_sv_time.getValue() != null) Id_gosp.setSv_time(Integer.valueOf(sp_sv_time.getValue().toString()));
            if (sp_sv_day.getValue() != null)Id_gosp.setSv_day(Integer.valueOf(sp_sv_day.getValue().toString()));
            if (tf_diag_n.getText().trim() != null) Id_gosp.setDiag_n(tf_diag_n.getText().trim());
            if (tf_diag_p.getText().trim() != null) Id_gosp.setDiag_p(tf_diag_p.getText().trim());
            if (ta_diag_n.getText().trim() != null) Id_gosp.setNamed_n(ta_diag_n.getText().trim());
            if (ta_diag_p.getText().trim() != null) Id_gosp.setNamed_p(ta_diag_p.getText().trim());
            if (tf_toc.getText().trim() != null) Id_gosp.setToc(tf_toc.getText().trim());
            if (tf_ad.getText().trim() != null) Id_gosp.setAd(tf_ad.getText().trim());
            if (ta_jal_pr.getText().trim() != null) Id_gosp.setJalob(ta_jal_pr.getText().trim());
            if (!tf_smpn.getText().isEmpty()) Id_gosp.setSmp_num(Integer.valueOf(tf_smpn.getText()));
            if (!tf_ntalon.getText().isEmpty()) Id_gosp.setNtalon(Integer.valueOf(tf_ntalon.getText()));

            if (rbtn_plan.isSelected()) Id_gosp.setPl_extr(1);
            if (rbtn_extr.isSelected()) Id_gosp.setPl_extr(2);

            Id_gosp.setMessr(cbx_messr.isSelected());
            Id_gosp.setNal_z(cbx_nalz.isSelected());
            Id_gosp.setNal_p(cbx_nalp.isSelected());

            if (cmb_travm.getSelectedItem() != null) Id_gosp.setVidtr(cmb_travm.getSelectedPcod());
            if (cmb_otkaz.getSelectedItem() != null) Id_gosp.setPr_out(cmb_otkaz.getSelectedPcod());
            if (cmb_alk.getSelectedItem() != null) Id_gosp.setAlkg(cmb_alk.getSelectedPcod());
            if (cmb_trans.getSelectedItem() != null) Id_gosp.setVid_trans(cmb_trans.getSelectedPcod());
            if (cmb_naprav.getSelectedItem() != null) Id_gosp.setNaprav(cmb_naprav.getSelectedPcod());
            if (cmb_org.getSelectedItem() != null) Id_gosp.setN_org(cmb_org.getSelectedPcod());
            if (cmb_cotd.getSelectedItem() != null) Id_gosp.setCotd(cmb_cotd.getSelectedPcod());

            //System.out.println(Id_gosp.getDiag_p()+","+Id_gosp.getNamed_p());
            CheckNotNullTableCgosp();
            if (curId == 0){
                curId = MainForm.tcl.addGosp(Id_gosp);
                newPriem.setId(curId);
                newPriem.setNist(Id_gosp.getNist());
                newPriem.setDatap(Id_gosp.getDatap());
                newPriem.setCotd(Id_gosp.getCotd());
                newPriem.setDiag_p(Id_gosp.getDiag_p());
                newPriem.setNamed_p(Id_gosp.getNamed_p());
                tbl_priem.updateSelectedItem();
            }
            else{
                MainForm.tcl.updateGosp(Id_gosp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    @SuppressWarnings("unused")
//    private void RefreshTablePatient(){
//        try {
//            PatientBrief patBr = new PatientBrief();
//            if (!tfFam.getText().isEmpty()) patBr.setFam(tfFam.getText().toUpperCase().trim());
//            if (!tfIm.getText().isEmpty()) patBr.setIm(tfIm.getText().toUpperCase().trim());
//            if (!tfOt.getText().isEmpty()) patBr.setOt(tfOt.getText().toUpperCase().trim());
//            if (!tf_oms_ser.getText().isEmpty()) patBr.setSpolis(tf_oms_ser.getText().toUpperCase().trim());
//            if (!tf_oms_nom.getText().isEmpty()) patBr.setNpolis(tf_oms_nom.getText().toUpperCase().trim());
//            try {
//                pat = MainForm.tcl.getAllPatientBrief(patBr);
//                refresh(pat);
//            }
//            catch (PatientNotFoundException e) {
//                JOptionPane.showMessageDialog(tfFam, "По заданным критериям сведения о пациенте отсутствуют.");
//                System.out.println(patBr.setFam(tfFam.getText().trim())+", "+patBr.setIm(tfIm.getText().trim())+", "+patBr.setOt(tfOt.getText().trim()));
//                System.out.println("По заданным критериям сведения о пациенте отсутствуют.");
//            }
//        } catch (Exception e) {
//            System.out.println("эксэпшн");
//            e.printStackTrace();
//        }
//    }
    private void CheckNotNullTableCgosp(){
        try {
            String strerr = "";
            if (Id_gosp.getPl_extr() == 0)
                strerr += "плановое/экстренное; \n\r";
            if (Id_gosp.getNaprav() == null)
                strerr += "кем направлен; \n\r";
            if ((Id_gosp.getDiag_p().isEmpty()) || (Id_gosp.getNamed_p().isEmpty()))
                strerr += "диагноз приемного отделения; \n\r";
            if ((Id_gosp.getCuser() == 0) || (Id_gosp.getCotd_p() == 0))
                strerr += "нет информации о пользователе; \n\r";
            if ((Id_gosp.getDataosm() == 0) || (Id_gosp.getVremosm() == 0))
                strerr += "дата и время осмотра; \n\r";
            if  (!strerr.isEmpty()){
                JOptionPane.showMessageDialog(tbl_priem, "Данные поля обязательно надо заполнить: \n\r"+ strerr);
                System.out.println("Данные поля обязательно надо заполнить: \n\r"+ strerr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
