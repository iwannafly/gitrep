package ru.nkz.ivcgzo.clientHospital;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;

import ru.nkz.ivcgzo.clientManager.common.swing.CustomDateEditor;
import ru.nkz.ivcgzo.clientManager.common.swing.CustomTable;
import ru.nkz.ivcgzo.clientManager.common.swing.CustomTextField;
import ru.nkz.ivcgzo.clientManager.common.swing.CustomTimeEditor;
import ru.nkz.ivcgzo.clientManager.common.swing.ThriftIntegerClassifierCombobox;
import ru.nkz.ivcgzo.clientManager.common.swing.ThriftIntegerClassifierList;
import ru.nkz.ivcgzo.thriftCommon.classifier.IntegerClassifier;
import ru.nkz.ivcgzo.thriftCommon.classifier.IntegerClassifiers;
import ru.nkz.ivcgzo.thriftCommon.classifier.StringClassifier;
import ru.nkz.ivcgzo.thriftCommon.kmiacServer.KmiacServerException;
import ru.nkz.ivcgzo.thriftCommon.kmiacServer.UserAuthInfo;
import ru.nkz.ivcgzo.thriftHospital.DiagnosisNotFoundException;
import ru.nkz.ivcgzo.thriftHospital.LifeHistoryNotFoundException;
import ru.nkz.ivcgzo.thriftHospital.MedicalHistoryNotFoundException;
import ru.nkz.ivcgzo.thriftHospital.MesNotFoundException;
import ru.nkz.ivcgzo.thriftHospital.PatientNotFoundException;
import ru.nkz.ivcgzo.thriftHospital.PriemInfoNotFoundException;
import ru.nkz.ivcgzo.thriftHospital.Shablon;
import ru.nkz.ivcgzo.thriftHospital.ShablonText;
import ru.nkz.ivcgzo.thriftHospital.TDiagnosis;
import ru.nkz.ivcgzo.thriftHospital.TLifeHistory;
import ru.nkz.ivcgzo.thriftHospital.TMedicalHistory;
import ru.nkz.ivcgzo.thriftHospital.TPatient;
import ru.nkz.ivcgzo.thriftHospital.TPriemInfo;
import ru.nkz.ivcgzo.thriftHospital.TStage;
import ru.nkz.ivcgzo.thriftHospital.Zakl;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.Timer;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.UIManager;

import org.apache.thrift.TException;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JToolBar;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 3513837719265529744L;
    private static final String WINDOW_HEADER = "Врач стационара";
    private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("dd.MM.yy");
    private JMenuBar mbMain;
    private JMenu mnPatientOperation;
    private JMenuItem mntmSelectPatient;
    private JMenuItem mntmReception;
    private JTabbedPane tabbedPane;
    private JSplitPane spPatientInfo;
    private JPanel pPersonalInfo;
    private JPanel pReceptionInfo;
    private JLabel lblNumberDesiaseHistory;
    private JLabel lblGender;
    private JLabel lblChamber;
    private JLabel lblRegistrationAddress;
    private JTextField tfNumberOfDesiaseHistory;
    private JTextField tfGender;
    private JTextField tfChamber;
    private JLabel lblSurname;
    private JLabel lblBirthdate;
    private JLabel lblStatus;
    private JTextField tfSurname;
    private JTextField tfBirthdate;
    private JTextField tfStatus;
    private JLabel lblName;
    private JLabel lblOms;
    private JLabel lblWork;
    private JTextField tfName;
    private JTextField tfOms;
    private JTextField tfWork;
    private JLabel lblMiddlename;
    private JLabel lblDms;
    private JTextField tfMiddlename;
    private JTextField tfDms;
    private JTextField tfRegistrationAddress;
    private JLabel lblRealAddress;
    private JTextField tfRealAddress;
    private JTextPane textPane;
    private JButton btnShowPatientInfo;
    private UserAuthInfo doctorAuth;
    private TPatient patient;
    private TPriemInfo priemInfo;
    private JPanel pMedicalHistory;
    private JTabbedPane tbpMedicalHistory;
    private CustomTextField tfMedHShablonFilter;
    private JPanel pnStatusLocalis;
    private JPanel pnStatusPraence;
    private JPanel pnJalob;
    private JPanel pnDesiaseHistory;
    private JPanel pnFisicalObs;
    private JTextArea taJalob;
    private JTextArea taDesiaseHistory;
    private JTextArea taStatusPraence;
    private JTextArea taFisicalObs;
    private JTextArea taStatusLocalis;
    private JScrollPane spMedicalHistoryShablonNames;
    private ThriftIntegerClassifierList lMedicalHistoryShablonNames;
    private JPanel pLifeHistory;
    private JLabel lblLifeHistory;
    private JScrollPane spLifeHistory;
    private JLabel lblAllergo;
    private JScrollPane spAllergo;
    private JLabel lblFarmo;
    private JScrollPane spFarmo;
    private CustomTextField tfLifeHShablonFilter;
    private JScrollPane spLifeHShablonNames;
    private JButton btnSaveLifeHistory;
    private JTextArea taLifeHistory;
    private JTextArea taAllergo;
    private JTextArea taFarmo;
    private ThriftIntegerClassifierList lLifeHistoryShabloNames;
    private JButton btnUpdateChamber;
    private TLifeHistory lifeHistory;
    private PatientSelectFrame frmPatientSelect;
    private CurationFrame frmCuration;
    private JPanel pDiagnosis;
    private JButton btnSaveDiag;
    private JRadioButton rdbtnMain;
    private JRadioButton rdbtnSoput;
    private JRadioButton rdbtnOsl;
    private CustomTextField tfDiagShablonFilter;
    private ThriftIntegerClassifierList lDiagShablonNames;
    private CustomTable<TDiagnosis, TDiagnosis._Fields> tbDiag;
    private JScrollPane spDiag;
    private JButton btnAddDiag;
    private JButton btnDelDiag;
    private JLabel lblDiagMedOp;
    private JTextArea taDiagMedOp;
    private JPanel pDiagTypes;
    private JScrollPane spDiagShablonNames;
    private Component hzstMainDiagSopDiag;
    private Component hzstSopDiagOslDiag;
    private JScrollPane spDiagnosisMedOp;
    private ButtonGroup btgDiag;
    private JPanel pZakl;
    private CustomTextField tfZaklShablonNames;
    private JButton btnSaveZakl;
    private JLabel lblRecomend;
    private JLabel lblZakluch;
    private ThriftIntegerClassifierCombobox<IntegerClassifier> cbxIshod;
    private JScrollPane spZaklShablonNames;
    private ThriftIntegerClassifierList lZaklShablonNames;
    private JScrollPane spRecomend;
    private JTextArea taRecomend;
    private JScrollPane spZakluch;
    private JTextArea taZakluch;
    private ShablonSearchListener lifeHiSearchListener;
    private ShablonSearchListener medHiSearchListener;
    private ShablonSearchListener diagSearchListener;
    private ShablonSearchListener zaklSearchListener;
    private CustomTable<TMedicalHistory, TMedicalHistory._Fields> tbMedHist;
    private JScrollPane spMedHist;
    private JButton btnMedHistUpd;
    private JButton btnMedHistDel;
    private JButton btnMedHistAdd;
    private JLabel lblResult;
    private JLabel lblIshod;
    private ThriftIntegerClassifierCombobox<IntegerClassifier> cbxResult;
    private CustomDateEditor cdeZaklDate;
    private CustomTimeEditor cdeZaklTime;
    private JLabel lblZaklDate;
    private JLabel lblZaklTime;
    private JButton btnIssled;
    private Color defCol = UIManager.getColor("TabbedPane.foreground");
    private Color selCol = Color.red;
    private JButton btnMedication;
    private JPanel pStage;
    private JScrollPane spStageTable;
    private JPanel pStageButtons;
    private CustomTable<TStage, TStage._Fields> tbStages;
    private JButton btnAddStage;
    private JButton btnUpdateStage;
    private JButton btnDeleteStage;
    private JLabel lblVidPom;
    private ThriftIntegerClassifierCombobox<IntegerClassifier> cbxVidPom;
    private ThriftIntegerClassifierCombobox<IntegerClassifier> cbxDefect;
    private JLabel lblDefect;
    private JLabel lblUkl;
    private JTextField tfUkl;
    private JLabel lblLifeHistioryShablonHeader;
    private Box vbLifeHistoryShablonComponents;
    private Box vbLifeHistoryTextFields;
    private Component hsLifeHistorySecond;
    private Box hbLifeHistoryShablonFind;
    private JButton btnLifeHistoryShablonFind;
    private Component hsLifeHistoryThird;
    private Component hsLifeHistoryFirst;
    private Box vbMedicalHistoryTextFields;
    private Box vbMedicalHistoryShablonComponents;
    private JLabel lblMedicalHistioryShablonHeader;
    private Box hbMedicalHistoryShablonFind;
    private JButton btnMedicalHistoryShablonFind;
    private Box hbMedicalHistoryTableControls;
    private Box vbMedicalHistoryTableButtons;
    private Component hsMedicalHistorySecond;
    private Component hsMedicalHistoryFirst;
    private Component hsMedicalHistoryThird;
    private Component vsMedicalHistoryControlsDelim;
    private Box vbDiagnosisTextFields;
    private Box vbDiagnosisShablonComponents;
    private Box hbDiagnosisTableControls;
    private Box vbDiagnosisTableButtons;
    private JLabel lblDiagnosisShablonHeader;
    private Box hbDiagnosisShablonFind;
    private JButton btnDiagnosisShablonFind;
    private Component hsDiagnosisSecond;
    private Component hsDiagnosisThird;
    private Component hsDiagnosisFirst;
    private Component vsDiagnosisControlsDelimFirst;
    private Component verticalStrut;

    public MainFrame(final UserAuthInfo authInfo) {
        setMinimumSize(new Dimension(800, 600));
        doctorAuth = authInfo;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(WINDOW_HEADER);
        setMainMenu();
        setTabbedPane();
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
        pack();
    }

//////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////// Общие методы //////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////

    private void setTabbedPane() {
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        getContentPane().add(tabbedPane);
        setPatientInfoPanel();
        setLifeHistoryPanel();
        setMedicalHistoryPanel();
        setStagePanel();
        setDiagnosisPanel();
        setZaklPanel();
    }

    public final void onConnect() {
        createModalFrames();
        try {
            lMedicalHistoryShablonNames.setData(ClientHospital.tcl.getShablonNames(
                doctorAuth.getCspec(), doctorAuth.getCslu(),  null));
            lLifeHistoryShabloNames.setData(ClientHospital.tcl.getShablonNames(
                doctorAuth.getCspec(), doctorAuth.getCslu(),  null));
            lDiagShablonNames.setData(ClientHospital.tcl.getShablonNames(
                doctorAuth.getCspec(), doctorAuth.getCslu(),  null));
            lZaklShablonNames.setData(ClientHospital.tcl.getShablonNames(
                doctorAuth.getCspec(), doctorAuth.getCslu(),  null));
            cbxIshod.setData(ClientHospital.tcl.getAp0());
            cbxResult.setData(ClientHospital.tcl.getAq0());
        } catch (KmiacServerException e) {
            e.printStackTrace();
        } catch (TException e) {
            ClientHospital.conMan.reconnect(e);
        }
    }

    private void clearAllComponentsAndObjects() {
        patient = null;
        clearPatientText();
        priemInfo = null;
        clearPriemInfoText();
        lifeHistory = null;
        clearLifeHistoryText();
        clearMedicalHistory();
        clearDiagnosisText();
        clearZaklText();
    }

    private class ShablonSearchListener implements DocumentListener {
        private CustomTextField ctf;
        private ThriftIntegerClassifierList ticl;

        public ShablonSearchListener(final CustomTextField inCtf,
                final ThriftIntegerClassifierList inTicl) {
            ctf = inCtf;
            ticl = inTicl;
        }

        private Timer timer = new Timer(500, new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                updateNow();
            }
        });

        @Override
        public void removeUpdate(final DocumentEvent e) {
            timer.restart();
        }

        @Override
        public void insertUpdate(final DocumentEvent e) {
            timer.restart();
        }

        @Override
        public void changedUpdate(final DocumentEvent e) {
            timer.restart();
        }

        public void updateNow() {
            timer.stop();
            loadShablonList(ctf, ticl);
        }
    }

    private void loadShablonList(final CustomTextField inCtf,
            final ThriftIntegerClassifierList inTicl) {
        try {
            List<IntegerClassifier> intClassif = ClientHospital.tcl.getShablonNames(
                doctorAuth.getCspec(), doctorAuth.getCslu(),
                (inCtf.getText().length() < 3)
                ? null : '%' + inCtf.getText() + '%');
            inTicl.setData(intClassif);
        } catch (KmiacServerException e1) {
            JOptionPane.showMessageDialog(MainFrame.this,
                "Ошибка загрузки результатов поиска", "Ошибка", JOptionPane.ERROR_MESSAGE);
        } catch (TException e1) {
            ClientHospital.conMan.reconnect(e1);
        }
    }

//////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////// Модульные фреймы ///////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////

    private void createModalFrames() {
        frmPatientSelect = new PatientSelectFrame(doctorAuth);
        frmPatientSelect.pack();
        frmPatientSelect.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent arg0) {
                if (frmPatientSelect.getCurrentPatient() != null) {
                    clearAllComponentsAndObjects();
                    fillPersonalInfoTextFields();
                    fillReceptionPanel();
                    fillLifeHistoryPanel();
                    fillMedHistoryTable();
                    fillDiagnosisTable();
                    fillStageTable();
                }
            }
        });
        frmCuration = new CurationFrame(doctorAuth);
        frmCuration.pack();
    }

//////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////   Главное меню   ///////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////

    private void setMainMenu() {
        mbMain = new JMenuBar();
        setJMenuBar(mbMain);

        mnPatientOperation = new JMenu("Управление пациентами");
        mbMain.add(mnPatientOperation);

        mntmSelectPatient = new JMenuItem("Выбор пациента");
        mntmSelectPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                frmPatientSelect.refreshModel(doctorAuth);
                frmPatientSelect.setVisible(true);
            }
        });
        mnPatientOperation.add(mntmSelectPatient);

        mntmReception = new JMenuItem("Приём в курацию");
        mntmReception.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                frmCuration.refreshModel(doctorAuth);
                frmCuration.setVisible(true);
            }
        });
        mnPatientOperation.add(mntmReception);
    }

//////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////   Информация о пациенте   //////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////

    private void setPatientInfoPanel() {
        spPatientInfo = new JSplitPane();
        spPatientInfo.setOrientation(JSplitPane.VERTICAL_SPLIT);
        tabbedPane.addTab("Информация о пациенте", null, spPatientInfo, null);
        setPersonalInfoPanel();
        setReceptionPanel();
    }

    private void setPersonalInfoPanel() {
        pPersonalInfo = new JPanel();
        spPatientInfo.setLeftComponent(pPersonalInfo);

        lblNumberDesiaseHistory = new JLabel("Номер истории болезни");
        tfNumberOfDesiaseHistory = new JTextField();
        tfNumberOfDesiaseHistory.setEditable(false);
        tfNumberOfDesiaseHistory.setColumns(15);

        lblSurname = new JLabel("Фамилия");
        tfSurname = new JTextField();
        tfSurname.setEditable(false);
        tfSurname.setColumns(15);

        lblName = new JLabel("Имя");
        tfName = new JTextField();
        tfName.setEditable(false);
        tfName.setColumns(15);

        lblMiddlename = new JLabel("Отчество");
        tfMiddlename = new JTextField();
        tfMiddlename.setEditable(false);
        tfMiddlename.setColumns(15);

        lblGender = new JLabel("Пол");
        tfGender = new JTextField();
        tfGender.setEditable(false);
        tfGender.setColumns(15);

        lblBirthdate = new JLabel("Дата рождения");
        tfBirthdate = new JTextField();
        tfBirthdate.setEditable(false);
        tfBirthdate.setColumns(15);

        lblOms = new JLabel("Полис ОМС");
        tfOms = new JTextField();
        tfOms.setEditable(false);
        tfOms.setColumns(20);

        lblDms = new JLabel("Полис ДМС");
        tfDms = new JTextField();
        tfDms.setEditable(false);
        tfDms.setColumns(20);

        lblChamber = new JLabel("Номер палаты");
        tfChamber = new JTextField();
        tfChamber.setColumns(15);

        lblStatus = new JLabel("Профиль отделения");
        tfStatus = new JTextField();
        tfStatus.setEditable(false);
        tfStatus.setColumns(15);

        lblWork = new JLabel("Место работы");
        tfWork = new JTextField();
        tfWork.setEditable(false);
        tfWork.setColumns(20);

        lblRegistrationAddress = new JLabel("Адрес прописки");
        tfRegistrationAddress = new JTextField();
        tfRegistrationAddress.setEditable(false);
        tfRegistrationAddress.setColumns(85);

        lblRealAddress = new JLabel("Адрес проживания");
        tfRealAddress = new JTextField();
        tfRealAddress.setEditable(false);
        tfRealAddress.setColumns(85);

        btnUpdateChamber = new JButton("Сохранить");
        btnUpdateChamber.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if ((patient != null) || (!tfChamber.getText().isEmpty())) {
                    try {
                        ClientHospital.tcl.updatePatientChamberNumber(
                                patient.gospitalCod, Integer.parseInt(tfChamber.getText()));
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(frmPatientSelect,
                                "Формат номера палаты неверный",
                                "Ошибка!", JOptionPane.ERROR_MESSAGE);
                    } catch (KmiacServerException e1) {
                        JOptionPane.showMessageDialog(frmPatientSelect,
                                "Ошибка сохранения номера палаты",
                                "Ошибка!", JOptionPane.ERROR_MESSAGE);
                    } catch (TException e1) {
                        ClientHospital.conMan.reconnect(e1);
                    }
                }
            }
        });

        btnShowPatientInfo = new JButton("Информация о пациенте");
        btnShowPatientInfo.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (patient != null) {
                    ClientHospital.conMan.showPatientInfoForm("Информация о пациенте",
                        patient.getPatientId());
                }
            }
        });

        btnMedication = new JButton("Медицинские назначения");
        btnMedication.setVisible(false);
        btnMedication.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (patient != null) {
                    ClientHospital.conMan.showMedicationForm(patient.getPatientId(),
                        patient.getSurname(), patient.getName(), patient.getMiddlename(),
                        patient.getGospitalCod());
                }
            }
        });

        btnIssled = new JButton("Запись на исследование");
        btnIssled.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (patient != null) {
                    ClientHospital.conMan.showLabRecordForm(patient.getPatientId(),
                        patient.getSurname(), patient.getName(), patient.getMiddlename(),
                        patient.getGospitalCod());
                }
            }
        });
        setPatientInfoPanelGroupLayout();
    }

    private void clearPatientText() {
        tfNumberOfDesiaseHistory.setText("");
        tfSurname.setText("");
        tfName.setText("");
        tfMiddlename.setText("");
        tfGender.setText("");
        tfBirthdate.setText("");
        tfOms.setText("");
        tfDms.setText("");
        tfChamber.setText("");
        tfStatus.setText("");
        tfWork.setText("");
        tfRegistrationAddress.setText("");
        tfRealAddress.setText("");
    }

    private void fillPersonalInfoTextFields() {
        try {
            patient = ClientHospital.tcl.getPatientPersonalInfo(
                frmPatientSelect.getCurrentPatient().getPatientId(),
                frmPatientSelect.getCurrentPatient().getIdGosp());
            setTitle(String.format("%s %s %s",
                patient.getSurname(), patient.getName(),
                patient.getMiddlename()));
        } catch (PatientNotFoundException e) {
            patient = null;
        } catch (KmiacServerException e) {
            e.printStackTrace();
        } catch (TException e) {
            ClientHospital.conMan.reconnect(e);
        }

        if (patient != null) {
            tfNumberOfDesiaseHistory.setText(String.valueOf(patient.getNist()));
            tfSurname.setText(patient.getSurname());
            tfName.setText(patient.getName());
            tfMiddlename.setText(patient.getMiddlename());
            tfGender.setText(patient.getGender());
            tfBirthdate.setText(DEFAULT_DATE_FORMAT.format(patient.getBirthDate()));
            tfOms.setText(patient.getOms());
            tfDms.setText(patient.getDms());
            tfChamber.setText(String.valueOf(patient.getChamber()));
            if (patient.getStatus() != null) {
                tfStatus.setText(patient.getStatus());
            }
            tfWork.setText(patient.getJob());
            tfRegistrationAddress.setText(patient.getRegistrationAddress());
            tfRealAddress.setText(patient.getRealAddress());
        }
    }

    private void setReceptionPanel() {
        pReceptionInfo = new JPanel();
        spPatientInfo.setRightComponent(pReceptionInfo);
        pReceptionInfo.setLayout(new BoxLayout(pReceptionInfo, BoxLayout.X_AXIS));

        textPane = new JTextPane();
        textPane.setEditable(false);
        pReceptionInfo.add(textPane);
    }

    private void clearPriemInfoText() {
        textPane.setText("");
    }

    private void fillReceptionPanel() {
        try {
            priemInfo = ClientHospital.tcl.getPriemInfo(
                    patient.getGospitalCod());
        } catch (PriemInfoNotFoundException e) {
            priemInfo = null;
        } catch (KmiacServerException e) {
            e.printStackTrace();
        } catch (TException e) {
            ClientHospital.conMan.reconnect(e);
        }

        String priemInfoText =
            "ДОСТУПНАЯ ИНФОРМАЦИЯ О ПАЦИЕНТЕ ИЗ ПРИЁМНОГО ОТДЕЛЕНИЯ:" + "\n\n";
        if (priemInfo != null) {
            if (priemInfo.getPl_extr() != null) {
                priemInfoText += "   " + "Форма обращения в приёмное отделение: "
                    + priemInfo.getPl_extr() + "\n";
            }
            if (priemInfo.getDatap() != 0) {
                priemInfoText += "   " + "Дата поступления: " + DEFAULT_DATE_FORMAT.format(
                    priemInfo.getDatap()) + "\n";
            }
            if (priemInfo.getDataosm() != 0) {
                priemInfoText += "   " + "Дата осмотра: " + DEFAULT_DATE_FORMAT.format(
                    priemInfo.getDataosm()) + "\n";
            }
            if ((priemInfo.getNaprav() != null) && (priemInfo.getNOrg() != null)) {
                priemInfoText += "   " + "Кем направлен: " + priemInfo.getNaprav() + "  "
                    + priemInfo.getNOrg() + "\n";
            }
            if ((priemInfo.getDiagN() != null) && (priemInfo.getDiagNtext() != null)) {
                priemInfoText += "   " + "Диагноз напр. учреждения: " + priemInfo.getDiagN() + " "
                    + priemInfo.getDiagNtext() + "\n";
            }
            if ((priemInfo.getDiagP() != null) && (priemInfo.getDiagPtext() != null)) {
                priemInfoText += "   " + "Диагноз приёмного отделения: "
                    + priemInfo.getDiagP() + " "
                    + priemInfo.getDiagPtext() + "\n";
            }
            if (priemInfo.getT0c() != null) {
                priemInfoText += "   " + "Температура: " + priemInfo.getT0c() + "\n";
            }
            if (priemInfo.getAd() != null) {
                priemInfoText += "   " + "Давление: " + priemInfo.getAd() + "\n";
            }
            if (priemInfo.getJalob() != null) {
                priemInfoText += "   " + "Жалобы при поступлении: :" + priemInfo.getJalob();
            }
        }
        textPane.setText(priemInfoText);
    }

//////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////   История жизни  ///////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////

    private void setLifeHistoryPanel() {
        pLifeHistory = new JPanel();
        pLifeHistory.setLayout(new BoxLayout(pLifeHistory, BoxLayout.X_AXIS));
        tabbedPane.addTab("История жизни", null, pLifeHistory, null);

        hsLifeHistoryFirst = Box.createHorizontalStrut(5);
        pLifeHistory.add(hsLifeHistoryFirst);

        setLifeHistoryVerticalTextPanels();

        hsLifeHistorySecond = Box.createHorizontalStrut(5);
        pLifeHistory.add(hsLifeHistorySecond);

        setLifeHistoryVerticalShablonPanel();

        hsLifeHistoryThird = Box.createHorizontalStrut(5);
        pLifeHistory.add(hsLifeHistoryThird);
    }

    private void setLifeHistoryVerticalTextPanels() {
        vbLifeHistoryTextFields = Box.createVerticalBox();
        vbLifeHistoryTextFields.setPreferredSize(new Dimension(500, 0));
        vbLifeHistoryTextFields.setAlignmentX(Component.LEFT_ALIGNMENT);
        vbLifeHistoryTextFields.setBorder(
                new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.GRAY));
        pLifeHistory.add(vbLifeHistoryTextFields);

        setLifeHistoryTextAreas();
        setLifeHistoryButtons();
    }

    private void setLifeHistoryTextAreas() {
        setLifeHistoryScrollPane();
        setAllergoScrollPane();
        setFarmoScrollPane();
    }

    private void setLifeHistoryScrollPane() {
        lblLifeHistory = new JLabel("История жизни");
        lblLifeHistory.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblLifeHistory.setFont(new Font("Tahoma", Font.BOLD, 13));
        vbLifeHistoryTextFields.add(lblLifeHistory);
        spLifeHistory = new JScrollPane();
        vbLifeHistoryTextFields.add(spLifeHistory);
        taLifeHistory = new JTextArea();
        spLifeHistory.setViewportView(taLifeHistory);
        taLifeHistory.setLineWrap(true);
        taLifeHistory.setWrapStyleWord(true);
        taLifeHistory.setFont(new Font("Tahoma", Font.PLAIN, 11));
    }

    private void setAllergoScrollPane() {
        lblAllergo = new JLabel("Аллергоанамнез");
        lblAllergo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblAllergo.setFont(new Font("Tahoma", Font.BOLD, 13));
        vbLifeHistoryTextFields.add(lblAllergo);
        spAllergo = new JScrollPane();
        vbLifeHistoryTextFields.add(spAllergo);
        taAllergo = new JTextArea();
        taAllergo.setWrapStyleWord(true);
        taAllergo.setLineWrap(true);
        taAllergo.setFont(new Font("Tahoma", Font.PLAIN, 11));
        spAllergo.setViewportView(taAllergo);
    }

    private void setFarmoScrollPane() {
        lblFarmo = new JLabel("Фармоанамнез");
        lblFarmo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblFarmo.setFont(new Font("Tahoma", Font.BOLD, 13));
        vbLifeHistoryTextFields.add(lblFarmo);
        spFarmo = new JScrollPane();
        vbLifeHistoryTextFields.add(spFarmo);
        taFarmo = new JTextArea();
        taFarmo.setLineWrap(true);
        taFarmo.setWrapStyleWord(true);
        taFarmo.setFont(new Font("Tahoma", Font.PLAIN, 11));
        spFarmo.setViewportView(taFarmo);
    }

    private void setLifeHistoryButtons() {
        btnSaveLifeHistory = new JButton("Сохранить");
        btnSaveLifeHistory.setMaximumSize(new Dimension(350, 40));
        btnSaveLifeHistory.setPreferredSize(new Dimension(350, 40));
        btnSaveLifeHistory.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSaveLifeHistory.setFont(new Font("Tahoma", Font.BOLD, 11));
        vbLifeHistoryTextFields.add(btnSaveLifeHistory);
        btnSaveLifeHistory.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (patient != null) {
                    try {
                        updateLifeHistoryFromTextAreas();
                        ClientHospital.tcl.updateLifeHistory(lifeHistory);
                        JOptionPane.showMessageDialog(MainFrame.this,
                            "История жизни сохранена", "Операция успешно завершена",
                            JOptionPane.INFORMATION_MESSAGE);
                    } catch (TException e1) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Ошибка при "
                            + "изменении истории жизни. Информация не будет сохранена!",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                        ClientHospital.conMan.reconnect(e1);
                    }
                } else {
                    JOptionPane.showMessageDialog(MainFrame.this, "Ошибка при "
                            + "изменении истории жизни. Информация не будет сохранена!",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void clearLifeHistoryText() {
        taLifeHistory.setText("");
        taAllergo.setText("");
        taFarmo.setText("");
    }

    private void setLifeHistoryVerticalShablonPanel() {
        vbLifeHistoryShablonComponents = Box.createVerticalBox();
        vbLifeHistoryShablonComponents.setBorder(
            new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.GRAY));
        vbLifeHistoryShablonComponents.setPreferredSize(new Dimension(300, 0));
        pLifeHistory.add(vbLifeHistoryShablonComponents);

        setLifeHistoryShablonLabel();
        setLifeHistoryShablonHorizontalBox();
        setLifeHistoryShablonScrollPane();
    }

    private void setLifeHistoryShablonLabel() {
        lblLifeHistioryShablonHeader = new JLabel("Строка поиска шаблона");
        lblLifeHistioryShablonHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblLifeHistioryShablonHeader.setFont(new Font("Tahoma", Font.BOLD, 13));
        vbLifeHistoryShablonComponents.add(lblLifeHistioryShablonHeader);
        lblLifeHistioryShablonHeader.setHorizontalTextPosition(SwingConstants.LEFT);
        lblLifeHistioryShablonHeader.setHorizontalAlignment(SwingConstants.LEFT);
    }

    private void setLifeHistoryShablonHorizontalBox() {
        hbLifeHistoryShablonFind = Box.createHorizontalBox();
        hbLifeHistoryShablonFind.setAlignmentY(Component.CENTER_ALIGNMENT);
        vbLifeHistoryShablonComponents.add(hbLifeHistoryShablonFind);

        setLifeHistoryShablonTextField();
        setLifeHistoryShablonButton();
    }

    private void setLifeHistoryShablonTextField() {
        tfLifeHShablonFilter = new CustomTextField(true, true, false);
        tfLifeHShablonFilter.setMaximumSize(new Dimension(450, 50));
        hbLifeHistoryShablonFind.add(tfLifeHShablonFilter);
        tfLifeHShablonFilter.getDocument().addDocumentListener(lifeHiSearchListener);
        tfLifeHShablonFilter.setColumns(10);
        lifeHiSearchListener =
            new ShablonSearchListener(tfLifeHShablonFilter, lLifeHistoryShabloNames);
    }

    private void setLifeHistoryShablonButton() {
        btnLifeHistoryShablonFind = new JButton("Найти");
        hbLifeHistoryShablonFind.add(btnLifeHistoryShablonFind);
    }

    private void setLifeHistoryShablonScrollPane() {
        spLifeHShablonNames = new JScrollPane();
        vbLifeHistoryShablonComponents.add(spLifeHShablonNames);

        setLifeHistoryShablonList();
    }

    private void setLifeHistoryShablonList() {
        lLifeHistoryShabloNames = new ThriftIntegerClassifierList();
        spLifeHShablonNames.setViewportView(lLifeHistoryShabloNames);
        lLifeHistoryShabloNames.setBorder(new LineBorder(new Color(0, 0, 0)));
        lLifeHistoryShabloNames.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (lMedicalHistoryShablonNames.getSelectedValue() != null) {
                        try {
                            pasteSelectedLifeHShablon(ClientHospital.tcl.getShablon(
                                lMedicalHistoryShablonNames.getSelectedValue().pcod));
                        } catch (KmiacServerException e1) {
                            JOptionPane.showMessageDialog(MainFrame.this,
                                "Ошибка загрузки шаблона", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        } catch (TException e1) {
                            ClientHospital.conMan.reconnect(e1);
                        }
                    }
                }
            }
        });
    }

    private void fillLifeHistoryPanel() {
        try {
            lifeHistory =
                    ClientHospital.tcl.getLifeHistory(patient.getPatientId());
        } catch (LifeHistoryNotFoundException e) {
            lifeHistory = null;
        } catch (KmiacServerException e) {
            lifeHistory = null;
        } catch (TException e) {
            ClientHospital.conMan.reconnect(e);
        }
        if (lifeHistory != null) {
            taAllergo.setText(lifeHistory.getAllerg());
            taFarmo.setText(lifeHistory.getFarmkol());
            taLifeHistory.setText(lifeHistory.getVitae());
        }
    }

    private void pasteSelectedLifeHShablon(final Shablon shablon) {
        if (shablon == null) {
            return;
        }

        for (ShablonText shText : shablon.textList) {
            switch (shText.grupId) {
                case 4:
                    taLifeHistory.setText(shText.getText());
                    break;
                case 3:
                    taAllergo.setText(shText.getText());
                    break;
                case 5:
                    taFarmo.setText(shText.getText());
                    break;
                default:
                    break;
            }
        }
    }

    private void updateLifeHistoryFromTextAreas() {
        if (lifeHistory == null) {
            lifeHistory = new TLifeHistory();
        }
        lifeHistory.setId(patient.getPatientId());
        lifeHistory.setAllerg(taAllergo.getText());
        lifeHistory.setFarmkol(taFarmo.getText());
        lifeHistory.setVitae(taLifeHistory.getText());
    };

//////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////   Медицинская история   ////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////

    private void setMedicalHistoryPanel() {
        pMedicalHistory = new JPanel();
        tabbedPane.addTab("Дневник", null, pMedicalHistory, null);
        pMedicalHistory.setLayout(new BoxLayout(pMedicalHistory, BoxLayout.X_AXIS));

        hsMedicalHistoryFirst = Box.createHorizontalStrut(5);
        pMedicalHistory.add(hsMedicalHistoryFirst);

        setMedicalHistoryVerticalTextComponents();

        hsMedicalHistorySecond = Box.createHorizontalStrut(5);
        pMedicalHistory.add(hsMedicalHistorySecond);

        setMedicalHistoryVerticalShablonPanel();

        hsMedicalHistoryThird = Box.createHorizontalStrut(5);
        pMedicalHistory.add(hsMedicalHistoryThird);
    }

    private void setMedicalHistoryVerticalTextComponents() {
        vbMedicalHistoryTextFields = Box.createVerticalBox();
        vbMedicalHistoryTextFields.setBorder(
            new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.GRAY));
        vbMedicalHistoryTextFields.setPreferredSize(new Dimension(500, 0));
        pMedicalHistory.add(vbMedicalHistoryTextFields);

        setMedicalHistoryHorizontalTableComponents();
        setMedicalHistoryTabPaneComponents();
    }

    private void setMedicalHistoryHorizontalTableComponents() {
        hbMedicalHistoryTableControls = Box.createHorizontalBox();
        hbMedicalHistoryTableControls.setBorder(
            new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
        vbMedicalHistoryTextFields.add(hbMedicalHistoryTableControls);

        setMedicalHistoryTableScrollPane();
        setMedicalHistoryTableButtonsPanel();
    }

    private void setMedicalHistoryTableScrollPane() {
        spMedHist = new JScrollPane();
        spMedHist.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
        spMedHist.setPreferredSize(new Dimension(300, 400));
        hbMedicalHistoryTableControls.add(spMedHist);

        addMedicalHistoryTable();
    }

    private void addMedicalHistoryTable() {
        tbMedHist = new CustomTable<TMedicalHistory, TMedicalHistory._Fields>(
                true, true, TMedicalHistory.class, 8, "Дата", 9, "Время");
        spMedHist.setViewportView(tbMedHist);
        tbMedHist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (tbMedHist.getSelectedItem() != null) {
                    setMedicalHistoryText();
                }
            }
        });
        tbMedHist.setDateField(0);
        tbMedHist.setTimeField(1);
    }

    private void setMedicalHistoryTableButtonsPanel() {
        vbMedicalHistoryTableButtons = Box.createVerticalBox();
        hbMedicalHistoryTableControls.add(vbMedicalHistoryTableButtons);

        addMedicalHistoryButtons();
    }

    private void addMedicalHistoryButtons() {
        addMedicalHistoryAddButton();
        addMedicalHistoryDeleteButton();
        addMedicalHistoryUpdateButton();
    }

    private void addMedicalHistoryAddButton() {
        btnMedHistAdd = new JButton();
        btnMedHistAdd.setPreferredSize(new Dimension(50, 50));
        btnMedHistAdd.setMaximumSize(new Dimension(50, 50));
        vbMedicalHistoryTableButtons.add(btnMedHistAdd);
        btnMedHistAdd.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                addMedHistoryToTable();
            }
        });
        btnMedHistAdd.setIcon(new ImageIcon(MainFrame.class.getResource(
                "/ru/nkz/ivcgzo/clientHospital/resources/1331789242_Add.png")));
    }

    private void addMedicalHistoryDeleteButton() {
        btnMedHistDel = new JButton();
        btnMedHistDel.setMaximumSize(new Dimension(50, 50));
        btnMedHistDel.setPreferredSize(new Dimension(50, 50));
        vbMedicalHistoryTableButtons.add(btnMedHistDel);
        btnMedHistDel.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                deleteMedHistoryFormTable();
            }
        });
        btnMedHistDel.setIcon(new ImageIcon(MainFrame.class.getResource(
                "/ru/nkz/ivcgzo/clientHospital/resources/1331789259_Delete.png")));
    }

    private void addMedicalHistoryUpdateButton() {
        btnMedHistUpd = new JButton();
        btnMedHistUpd.setPreferredSize(new Dimension(50, 50));
        btnMedHistUpd.setMaximumSize(new Dimension(50, 50));
        vbMedicalHistoryTableButtons.add(btnMedHistUpd);
        btnMedHistUpd.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                updateMedHistoryToTable();
            }
        });
        btnMedHistUpd.setIcon(new ImageIcon(MainFrame.class.getResource(
                "/ru/nkz/ivcgzo/clientHospital/resources/1341981970_Accept.png")));
    }


    private void setMedicalHistoryTabPaneComponents() {
        vsMedicalHistoryControlsDelim = Box.createVerticalStrut(20);
        vbMedicalHistoryTextFields.add(vsMedicalHistoryControlsDelim);

        addMedicalHistoryTabbedPane();
    }

    private void addMedicalHistoryTabbedPane() {
        tbpMedicalHistory = new JTabbedPane(JTabbedPane.LEFT);
        tbpMedicalHistory.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
        tbpMedicalHistory.setPreferredSize(new Dimension(500, 400));
        vbMedicalHistoryTextFields.add(tbpMedicalHistory);
        tbpMedicalHistory.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        setMedicalHistoryTabs();
        tbpMedicalHistory.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent e) {
                for (int i = 0; i < tbpMedicalHistory.getTabCount(); i++) {
                    JLabel lbl = (JLabel) tbpMedicalHistory.getTabComponentAt(i);
                    if (lbl != null) {
                        if (i == tbpMedicalHistory.getSelectedIndex()) {
                            lbl.setForeground(selCol);
                        } else {
                            lbl.setForeground(defCol);
                        }
                    }
                }
            }
        });
    }

    private void setMedicalHistoryTabs() {
        addJalonPanel();
        addDesiaseHistoryPanel();
        addStatusPraencePanel();
        addFisicalObsPanel();
        addStausLocalisPanel();
    }

    private void deleteMedHistoryFormTable() {
        try {
            if (tbMedHist.getSelectedItem() != null) {
                int opResult = JOptionPane.showConfirmDialog(
                    btnMedHistDel, "Удалить запись?",
                    "Удаление записи", JOptionPane.YES_NO_OPTION);
                if (opResult == JOptionPane.YES_OPTION) {
                    ClientHospital.tcl.deleteMedicalHistory(
                        tbMedHist.getSelectedItem().getId());
                    tbMedHist.setData(
                        ClientHospital.tcl.getMedicalHistory((patient.getGospitalCod())));
                }
                if (tbMedHist.getRowCount() > 0) {
                    tbMedHist.setRowSelectionInterval(tbMedHist.getRowCount() - 1,
                            tbMedHist.getRowCount() - 1);
                }
                clearMedicalHistoryTextAreas();
            }
        } catch (KmiacServerException e1) {
            e1.printStackTrace();
        } catch (MedicalHistoryNotFoundException e1) {
            tbMedHist.setData(new ArrayList<TMedicalHistory>());
        } catch (TException e1) {
            ClientHospital.conMan.reconnect(e1);
        }

    }

    private void updateMedHistoryToTable() {
        try {
            if (tbMedHist.getSelectedItem() != null) {
                int opResult = JOptionPane.showConfirmDialog(
                    btnMedHistUpd, "Обновить информацию о диагнозе?",
                    "Изменение диагноза", JOptionPane.YES_NO_OPTION);
                if (opResult == JOptionPane.YES_OPTION) {
                    tbMedHist.getSelectedItem().setFisicalObs(taFisicalObs.getText());
                    tbMedHist.getSelectedItem().setJalob(taJalob.getText());
                    tbMedHist.getSelectedItem().setMorbi(taDesiaseHistory.getText());
                    tbMedHist.getSelectedItem().setStatusLocalis(taStatusLocalis.getText());
                    tbMedHist.getSelectedItem().setStatusPraesense(taStatusPraence.getText());
                    ClientHospital.tcl.updateMedicalHistory(tbMedHist.getSelectedItem());
                }
            }
        } catch (KmiacServerException e1) {
            e1.printStackTrace();
        } catch (TException e1) {
            ClientHospital.conMan.reconnect(e1);
        }
    }

    private void addMedHistoryToTable() {
        try {
            if (patient != null) {
                TMedicalHistory medHist = new TMedicalHistory();
                medHist.setDataz(System.currentTimeMillis());
                medHist.setTimez(System.currentTimeMillis());
                medHist.setPcodVrach(doctorAuth.getPcod());
                medHist.setIdGosp(patient.getGospitalCod());
                medHist.setId(ClientHospital.tcl.addMedicalHistory(medHist));
                tbMedHist.addItem(medHist);
                tbMedHist.setData(
                        ClientHospital.tcl.getMedicalHistory(patient.getGospitalCod()));
            }
        } catch (KmiacServerException e1) {
            e1.printStackTrace();
        } catch (MedicalHistoryNotFoundException e) {
            tbMedHist.setData(new ArrayList<TMedicalHistory>());
        } catch (TException e1) {
            ClientHospital.conMan.reconnect(e1);
        }
    }

    private void setMedicalHistoryText() {
        taJalob.setText(tbMedHist.getSelectedItem().getJalob());
        taDesiaseHistory.setText(tbMedHist.getSelectedItem().getMorbi());
        taFisicalObs.setText(tbMedHist.getSelectedItem().getFisicalObs());
        taStatusLocalis.setText(tbMedHist.getSelectedItem().getStatusLocalis());
        taStatusPraence.setText(tbMedHist.getSelectedItem().getStatusPraesense());
    }

    private void addJalonPanel() {
        pnJalob = new JPanel();
        pnJalob.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnJalob.setLayout(new BoxLayout(pnJalob, BoxLayout.Y_AXIS));

        taJalob = new JTextArea();
        taJalob.setFont(new Font("Tahoma", Font.PLAIN, 11));
        taJalob.setLineWrap(true);
        taJalob.setWrapStyleWord(true);
        pnJalob.add(taJalob);

        tbpMedicalHistory.addTab("Жалобы", null, pnJalob, null);
        tbpMedicalHistory.setTabComponentAt(0, new JLabel("<html><br>Жалобы<br><br></html>"));
        ((JLabel) tbpMedicalHistory.getTabComponentAt(0)).setForeground(selCol);
    }

    private void addDesiaseHistoryPanel() {
        pnDesiaseHistory = new JPanel();
        pnDesiaseHistory.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnDesiaseHistory.setLayout(new BoxLayout(pnDesiaseHistory, BoxLayout.X_AXIS));

        taDesiaseHistory = new JTextArea();
        taDesiaseHistory.setWrapStyleWord(true);
        taDesiaseHistory.setLineWrap(true);
        taDesiaseHistory.setFont(new Font("Tahoma", Font.PLAIN, 11));
        pnDesiaseHistory.add(taDesiaseHistory);

        tbpMedicalHistory.addTab("История болезни", null, pnDesiaseHistory, null);
        tbpMedicalHistory.setTabComponentAt(
            1, new JLabel("<html><br>История болезни<br><br></html>"));

    }

    private void addStatusPraencePanel() {
        pnStatusPraence = new JPanel();
        pnStatusPraence.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnStatusPraence.setLayout(new BoxLayout(pnStatusPraence, BoxLayout.X_AXIS));

        taStatusPraence = new JTextArea();
        taStatusPraence.setLineWrap(true);
        taStatusPraence.setWrapStyleWord(true);
        taStatusPraence.setFont(new Font("Tahoma", Font.PLAIN, 11));
        pnStatusPraence.add(taStatusPraence);

        tbpMedicalHistory.addTab("Объективный статус (Status praense)",
            null, pnStatusPraence, null);
        tbpMedicalHistory.setTabComponentAt(
            2, new JLabel("<html><br>Объективный статус (Status praense)<br><br></html>"));
    }

    private void addFisicalObsPanel() {
        pnFisicalObs = new JPanel();
        pnFisicalObs.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnFisicalObs.setLayout(new BoxLayout(pnFisicalObs, BoxLayout.X_AXIS));

        taFisicalObs = new JTextArea();
        taFisicalObs.setLineWrap(true);
        taFisicalObs.setWrapStyleWord(true);
        taFisicalObs.setFont(new Font("Tahoma", Font.PLAIN, 11));
        pnFisicalObs.add(taFisicalObs);

        tbpMedicalHistory.addTab("Физикальное обследование", null, pnFisicalObs, null);
        tbpMedicalHistory.setTabComponentAt(
            3, new JLabel("<html><br>Физикальное обследование<br><br></html>"));
    }

    private void addStausLocalisPanel() {
        pnStatusLocalis = new JPanel();
        pnStatusLocalis.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnStatusLocalis.setLayout(new BoxLayout(pnStatusLocalis, BoxLayout.X_AXIS));
        taStatusLocalis = new JTextArea();
        taStatusLocalis.setLineWrap(true);
        taStatusLocalis.setWrapStyleWord(true);
        taStatusLocalis.setFont(new Font("Tahoma", Font.PLAIN, 11));
        pnStatusLocalis.add(taStatusLocalis);

        tbpMedicalHistory.addTab("Локальный статус (Status localis)",
            null, pnStatusLocalis, null);
        tbpMedicalHistory.setTabComponentAt(
            4, new JLabel("<html><br>Локальный статус (Status localis)<br><br></html>"));
    }

    private void clearMedicalHistory() {
        tbMedHist.setData(Collections.<TMedicalHistory>emptyList());
        taJalob.setText("");
        taDesiaseHistory.setText("");
        taFisicalObs.setText("");
        taStatusLocalis.setText("");
        taStatusPraence.setText("");
    }

    private void clearMedicalHistoryTextAreas() {
        taJalob.setText("");
        taDesiaseHistory.setText("");
        taFisicalObs.setText("");
        taStatusLocalis.setText("");
        taStatusPraence.setText("");
    }

    private void setMedicalHistoryVerticalShablonPanel() {
        vbMedicalHistoryShablonComponents = Box.createVerticalBox();
        vbMedicalHistoryShablonComponents.setBorder(
            new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.GRAY));
        vbMedicalHistoryShablonComponents.setPreferredSize(new Dimension(300, 0));
        pMedicalHistory.add(vbMedicalHistoryShablonComponents);

        setMedicalHistoryShablonLabel();
        setMedicalHistoryShablonHorizontalBox();
        setMedicalHistoryShablonScrollPane();
    }

    private void setMedicalHistoryShablonLabel() {
        lblMedicalHistioryShablonHeader = new JLabel("Строка поиска шаблона");
        lblMedicalHistioryShablonHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblMedicalHistioryShablonHeader.setFont(new Font("Tahoma", Font.BOLD, 13));
        vbMedicalHistoryShablonComponents.add(lblMedicalHistioryShablonHeader);
        lblMedicalHistioryShablonHeader.setHorizontalTextPosition(SwingConstants.LEFT);
        lblMedicalHistioryShablonHeader.setHorizontalAlignment(SwingConstants.LEFT);
    }

    private void setMedicalHistoryShablonHorizontalBox() {
        hbMedicalHistoryShablonFind = Box.createHorizontalBox();
        vbMedicalHistoryShablonComponents.add(hbMedicalHistoryShablonFind);

        setMedicalHistoryShablonTextField();
        setMedicalHistoryShablonButton();
    }

    private void setMedicalHistoryShablonTextField() {
        tfMedHShablonFilter = new CustomTextField(true, true, false);
        tfMedHShablonFilter.setMaximumSize(new Dimension(450, 50));
        hbMedicalHistoryShablonFind.add(tfMedHShablonFilter);
        tfMedHShablonFilter.getDocument().addDocumentListener(medHiSearchListener);
        tfMedHShablonFilter.setColumns(10);
        medHiSearchListener =
            new ShablonSearchListener(tfMedHShablonFilter, lMedicalHistoryShablonNames);
    }

    private void setMedicalHistoryShablonButton() {
        btnMedicalHistoryShablonFind = new JButton("Найти");
        btnMedicalHistoryShablonFind.setMinimumSize(new Dimension(63, 23));
        btnMedicalHistoryShablonFind.setMaximumSize(new Dimension(63, 23));
        btnMedicalHistoryShablonFind.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnMedicalHistoryShablonFind.setPreferredSize(new Dimension(63, 23));
        hbMedicalHistoryShablonFind.add(btnMedicalHistoryShablonFind);
    }

    private void setMedicalHistoryShablonScrollPane() {
        spMedicalHistoryShablonNames = new JScrollPane();
        vbMedicalHistoryShablonComponents.add(spMedicalHistoryShablonNames);

        setMedicalHistoryShablonList();
    }

    private void setMedicalHistoryShablonList() {
        lMedicalHistoryShablonNames = new ThriftIntegerClassifierList();
        spMedicalHistoryShablonNames.setViewportView(lMedicalHistoryShablonNames);
        lMedicalHistoryShablonNames.setBorder(new LineBorder(new Color(0, 0, 0)));
        lMedicalHistoryShablonNames.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (lMedicalHistoryShablonNames.getSelectedValue() != null) {
                        try {
                            pasteSelectedShablon(ClientHospital.tcl.getShablon(
                                lMedicalHistoryShablonNames.getSelectedValue().pcod));
                        } catch (KmiacServerException e1) {
                            JOptionPane.showMessageDialog(MainFrame.this,
                                "Ошибка загрузки шаблона", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        } catch (TException e1) {
                            ClientHospital.conMan.reconnect(e1);
                        }
                    }
                }
            }
        });
    }

    private void pasteSelectedShablon(final Shablon shablon) {
        if (shablon == null) {
            return;
        }

        clearMedicalHistoryTextAreas();

        for (ShablonText shText : shablon.textList) {
            switch (shText.grupId) {
                case 1:
                    taJalob.setText(shText.getText());
                    break;
                case 2:
                    taDesiaseHistory.setText(shText.getText());
                    break;
                case 6:
                    taStatusPraence.setText(shText.getText());
                    break;
                case 7:
                    taFisicalObs.setText(shText.getText());
                    break;
                case 8:
                    taStatusLocalis.setText(shText.getText());
                    break;
                default:
                    break;
            }
        }
    }

    private void fillMedHistoryTable() {
        if (patient != null) {
            try {
                tbMedHist.setData(
                        ClientHospital.tcl.getMedicalHistory(patient.getGospitalCod()));
            } catch (MedicalHistoryNotFoundException e) {
                tbMedHist.setData(Collections.<TMedicalHistory>emptyList());
            } catch (KmiacServerException e) {
                e.printStackTrace();
            } catch (TException e) {
                ClientHospital.conMan.reconnect(e);
            }
        }
    }

//////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////// Диагнозы //////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////

    private void setDiagnosisPanel() {
        pDiagnosis = new JPanel();
        tabbedPane.addTab("Диагнозы", null, pDiagnosis, null);
        pDiagnosis.setLayout(new BoxLayout(pDiagnosis, BoxLayout.X_AXIS));

        hsDiagnosisFirst = Box.createHorizontalStrut(5);
        pDiagnosis.add(hsDiagnosisFirst);

        setDiagnosisVerticalTextComponents();

        hsDiagnosisSecond = Box.createHorizontalStrut(5);
        pDiagnosis.add(hsDiagnosisSecond);

        setDiagnosisVerticalShablonPanel();

        hsDiagnosisThird = Box.createHorizontalStrut(5);
        pDiagnosis.add(hsDiagnosisThird);
    }

    private void setDiagnosisVerticalTextComponents() {
        vbDiagnosisTextFields = Box.createVerticalBox();
        vbDiagnosisTextFields.setBorder(new EtchedBorder(
            EtchedBorder.LOWERED, Color.BLACK, Color.GRAY));
        vbDiagnosisTextFields.setPreferredSize(new Dimension(500, 0));
        pDiagnosis.add(vbDiagnosisTextFields);

        setDiagnosisHorizontalTableComponents();
        setDiagnosisTextComponents();
    }

    private void setDiagnosisHorizontalTableComponents() {
        hbDiagnosisTableControls = Box.createHorizontalBox();
        hbDiagnosisTableControls.setBorder(
            new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
        vbDiagnosisTextFields.add(hbDiagnosisTableControls);

        setDiagnosisTableScrollPane();
        setDiagnosisTableButtonsPanel();
    }

    private void setDiagnosisTableScrollPane() {
        spDiag = new JScrollPane();
        spDiag.setPreferredSize(new Dimension(300, 400));
        spDiag.setBorder(
            new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
        hbDiagnosisTableControls.add(spDiag);

        addDiagnosisTable();
    }

    private void addDiagnosisTable() {
        tbDiag = new CustomTable<TDiagnosis, TDiagnosis._Fields>(
                false, true, TDiagnosis.class, 4, "Дата", 2, "Код МКБ", 7, "Наименование диагноза");
        tbDiag.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (tbDiag.getSelectedItem() != null) {
                    setDiagPriznRdbtn();
                    taDiagMedOp.setText(tbDiag.getSelectedItem().getMedOp());
                }
            }
        });
        tbDiag.setDateField(0);
        spDiag.setViewportView(tbDiag);
    }

    private void setDiagnosisTableButtonsPanel() {
        vbDiagnosisTableButtons = Box.createVerticalBox();
        hbDiagnosisTableControls.add(vbDiagnosisTableButtons);

        addDiagnosisButtons();
    }

    private void addDiagnosisButtons() {
        addDiagnosisAddButton();
        addDiagnosisDeleteButton();
        addDiagnosisUpdateButton();
    }

    private void addDiagnosisAddButton() {
        btnAddDiag = new JButton();
        btnAddDiag.setMaximumSize(new Dimension(50, 50));
        btnAddDiag.setPreferredSize(new Dimension(50, 50));
        vbDiagnosisTableButtons.add(btnAddDiag);
        btnAddDiag.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                addDiagnosisToTable(ClientHospital.conMan.showMkbTreeForm("Диагноз", ""));
            }
        });
        btnAddDiag.setIcon(new ImageIcon(MainFrame.class.getResource(
                "/ru/nkz/ivcgzo/clientHospital/resources/1331789242_Add.png")));
    }

    private void addDiagnosisDeleteButton() {
        btnDelDiag = new JButton();
        btnDelDiag.setPreferredSize(new Dimension(50, 50));
        btnDelDiag.setMaximumSize(new Dimension(50, 50));
        vbDiagnosisTableButtons.add(btnDelDiag);
        btnDelDiag.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                delDiagnosisFromTable();
            }
        });
        btnDelDiag.setIcon(new ImageIcon(MainFrame.class.getResource(
                "/ru/nkz/ivcgzo/clientHospital/resources/1331789259_Delete.png")));
    }

    private void addDiagnosisUpdateButton() {
        btnSaveDiag = new JButton();
        btnSaveDiag.setPreferredSize(new Dimension(50, 50));
        btnSaveDiag.setMaximumSize(new Dimension(50, 50));
        vbDiagnosisTableButtons.add(btnSaveDiag);
        btnSaveDiag.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                saveDiagnosisToTable();
            }
        });
        btnSaveDiag.setIcon(new ImageIcon(MainFrame.class.getResource(
                "/ru/nkz/ivcgzo/clientHospital/resources/1341981970_Accept.png")));
    }

    private void setDiagnosisTextComponents() {
        vsDiagnosisControlsDelimFirst = Box.createVerticalStrut(10);
        vbDiagnosisTextFields.add(vsDiagnosisControlsDelimFirst);

        addDiagnosisRadioButtonsGroup();

        verticalStrut = Box.createVerticalStrut(10);
        vbDiagnosisTextFields.add(verticalStrut);

        addDiagnosisMedOpHeader();
        addDiagnosisMedOpScrollPane();
    }

    private void addDiagnosisMedOpHeader() {
        lblDiagMedOp = new JLabel("Медицинское описание диагноза");
        lblDiagMedOp.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblDiagMedOp.setFont(new Font("Tahoma", Font.BOLD, 13));
        vbDiagnosisTextFields.add(lblDiagMedOp);
    }

    private void addDiagnosisMedOpScrollPane() {
        spDiagnosisMedOp = new JScrollPane();
        spDiagnosisMedOp.setPreferredSize(new Dimension(500, 250));
        vbDiagnosisTextFields.add(spDiagnosisMedOp);

        addDiagnosisMedOpTextArea();
    }

    private void addDiagnosisMedOpTextArea() {
        taDiagMedOp = new JTextArea();
        taDiagMedOp.setBorder(new LineBorder(new Color(0, 0, 0)));
        taDiagMedOp.setLineWrap(true);
        taDiagMedOp.setWrapStyleWord(true);
        spDiagnosisMedOp.setViewportView(taDiagMedOp);
        taDiagMedOp.setFont(new Font("Tahoma", Font.PLAIN, 11));
    }

    private void addDiagnosisRadioButtonsGroup() {
        addDiagnosisRadioGroupPanel();
        addDiagnosisRadioGroupButtons();
    }

    private void addDiagnosisRadioGroupPanel() {
        pDiagTypes = new JPanel();
        vbDiagnosisTextFields.add(pDiagTypes);
        pDiagTypes.setBorder(new LineBorder(new Color(0, 0, 0)));
        pDiagTypes.setLayout(new BoxLayout(pDiagTypes, BoxLayout.X_AXIS));
    }

    private void addDiagnosisRadioGroupButtons() {
        rdbtnMain = new JRadioButton("Основной");
        pDiagTypes.add(rdbtnMain);

        hzstMainDiagSopDiag = Box.createHorizontalStrut(150);
        hzstMainDiagSopDiag.setMinimumSize(new Dimension(5, 0));
        pDiagTypes.add(hzstMainDiagSopDiag);

        rdbtnSoput = new JRadioButton("Сопутствующий");
        pDiagTypes.add(rdbtnSoput);

        hzstSopDiagOslDiag = Box.createHorizontalStrut(150);
        hzstSopDiagOslDiag.setMinimumSize(new Dimension(5, 0));
        pDiagTypes.add(hzstSopDiagOslDiag);

        rdbtnOsl = new JRadioButton("Осложнение основного");
        pDiagTypes.add(rdbtnOsl);

        btgDiag = new ButtonGroup();
        btgDiag.add(rdbtnMain);
        btgDiag.add(rdbtnSoput);
        btgDiag.add(rdbtnOsl);
    }

    private void setDiagnosisVerticalShablonPanel() {
        vbDiagnosisShablonComponents = Box.createVerticalBox();
        vbDiagnosisShablonComponents.setBorder(
            new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.GRAY));
        vbDiagnosisShablonComponents.setPreferredSize(new Dimension(300, 0));
        pDiagnosis.add(vbDiagnosisShablonComponents);

        setDiagnosisShablonLabel();
        setDiagnosisShablonHorizontalBox();
        setDiagnosisShablonScrollPane();
    }

    private void setDiagnosisShablonLabel() {
        lblDiagnosisShablonHeader = new JLabel("Строка поиска шаблона");
        lblDiagnosisShablonHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblDiagnosisShablonHeader.setFont(new Font("Tahoma", Font.BOLD, 13));
        vbDiagnosisShablonComponents.add(lblDiagnosisShablonHeader);
        lblDiagnosisShablonHeader.setHorizontalTextPosition(SwingConstants.LEFT);
        lblDiagnosisShablonHeader.setHorizontalAlignment(SwingConstants.LEFT);
        lblDiagnosisShablonHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void setDiagnosisShablonHorizontalBox() {
        hbDiagnosisShablonFind = Box.createHorizontalBox();
        vbDiagnosisShablonComponents.add(hbDiagnosisShablonFind);

        setDiagnosisShablonTextField();
        setDiagnosisShablonButton();
    }

    private void setDiagnosisShablonTextField() {
        tfDiagShablonFilter = new CustomTextField(true, true, false);
        tfDiagShablonFilter.setMaximumSize(new Dimension(450, 50));
        hbDiagnosisShablonFind.add(tfDiagShablonFilter);
        tfDiagShablonFilter.getDocument().addDocumentListener(diagSearchListener);
        tfDiagShablonFilter.setColumns(10);
        diagSearchListener = new ShablonSearchListener(tfDiagShablonFilter, lDiagShablonNames);
    }

    private void setDiagnosisShablonButton() {
        btnDiagnosisShablonFind = new JButton("Найти");
        btnDiagnosisShablonFind.setMinimumSize(new Dimension(63, 23));
        btnDiagnosisShablonFind.setMaximumSize(new Dimension(63, 23));
        btnDiagnosisShablonFind.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDiagnosisShablonFind.setPreferredSize(new Dimension(63, 23));
        hbDiagnosisShablonFind.add(btnDiagnosisShablonFind);
    }

    private void setDiagnosisShablonScrollPane() {
        spDiagShablonNames = new JScrollPane();
        vbDiagnosisShablonComponents.add(spDiagShablonNames);

        setDiagnosisShablonList();
    }

    private void setDiagnosisShablonList() {
        lDiagShablonNames = new ThriftIntegerClassifierList();
        lDiagShablonNames.setBorder(new LineBorder(new Color(0, 0, 0)));
        spDiagShablonNames.setViewportView(lDiagShablonNames);
    }

    private void clearDiagnosisText() {
        tbDiag.setData(Collections.<TDiagnosis>emptyList());
        taDiagMedOp.setText(null);
        rdbtnMain.setSelected(false);
        rdbtnSoput.setSelected(false);
        rdbtnOsl.setSelected(false);
    }

    private void fillDiagnosisTable() {
        if (patient != null) {
            try {
                tbDiag.setData(
                        ClientHospital.tcl.getDiagnosis(patient.getGospitalCod()));
                setDiagPriznRdbtn();
            } catch (DiagnosisNotFoundException e) {
                Collections.<TDiagnosis>emptyList();
            } catch (KmiacServerException e) {
                e.printStackTrace();
            } catch (TException e) {
                ClientHospital.conMan.reconnect(e);
            }
        }
    }

    private void setDiagPriznRdbtn() {
        if (tbDiag.getSelectedItem() != null) {
            if (tbDiag.getSelectedItem().getPrizn() == 1) {
                rdbtnMain.setSelected(true);
            } else if (tbDiag.getSelectedItem().getPrizn() == 3) {
                rdbtnSoput.setSelected(true);
            } else if (tbDiag.getSelectedItem().getPrizn() == 2) {
                rdbtnOsl.setSelected(true);
            } else {
                rdbtnMain.setSelected(false);
                rdbtnSoput.setSelected(false);
                rdbtnOsl.setSelected(false);
            }
        }

    }

    private void delDiagnosisFromTable() {
        try {
            if (tbDiag.getSelectedItem() != null) {
                int opResult = JOptionPane.showConfirmDialog(
                    btnDelDiag, "Удалить диагноз?",
                    "Удаление диагноза", JOptionPane.YES_NO_OPTION);
                if (opResult == JOptionPane.YES_OPTION) {
                    ClientHospital.tcl.deleteDiagnosis(tbDiag.getSelectedItem().getId());
                    tbDiag.setData(
                        ClientHospital.tcl.getDiagnosis(patient.getGospitalCod()));
                }
                if (tbDiag.getRowCount() > 0) {
                    tbDiag.setRowSelectionInterval(tbDiag.getRowCount() - 1,
                        tbDiag.getRowCount() - 1);
                }
                taDiagMedOp.setText("");
            }
        } catch (KmiacServerException e1) {
            e1.printStackTrace();
        } catch (DiagnosisNotFoundException e1) {
            tbDiag.setData(new ArrayList<TDiagnosis>());
            //e1.printStackTrace();
        } catch (TException e1) {
            ClientHospital.conMan.reconnect(e1);
        }
    }

    private void saveDiagnosisToTable() {
        try {
            if (tbDiag.getSelectedItem() != null) {
                int opResult = JOptionPane.showConfirmDialog(
                    btnDelDiag, "Добавить информацию о диагнозе?",
                    "Изменение диагноза", JOptionPane.YES_NO_OPTION);
                if (opResult == JOptionPane.YES_OPTION) {
                    tbDiag.getSelectedItem().setMedOp(taDiagMedOp.getText());
                    if (rdbtnMain.isSelected()) {
                        tbDiag.getSelectedItem().setPrizn(1);
                    }
                    if (rdbtnSoput.isSelected()) {
                        tbDiag.getSelectedItem().setPrizn(3);
                    }
                    if (rdbtnOsl.isSelected()) {
                        tbDiag.getSelectedItem().setPrizn(2);
                    }
                    tbDiag.getSelectedItem().setIdGosp(patient.getGospitalCod());
                    ClientHospital.tcl.updateDiagnosis(tbDiag.getSelectedItem());
                }
            }
        } catch (KmiacServerException e1) {
            e1.printStackTrace();
        } catch (TException e1) {
            ClientHospital.conMan.reconnect(e1);
        }
    }

    private void addDiagnosisToTable(final StringClassifier curDiagnosis) {
        try {
            if ((curDiagnosis != null) && (patient != null)) {
                TDiagnosis diag = new TDiagnosis();
                diag.setIdGosp(patient.getGospitalCod());
                diag.setCod(curDiagnosis.getPcod());
                diag.setDateUstan(System.currentTimeMillis());
                diag.setVrach(doctorAuth.getPcod());
                diag.setDiagName(curDiagnosis.getName());
                diag.setId(ClientHospital.tcl.addDiagnosis(diag));
                tbDiag.addItem(diag);
                tbDiag.setData(
                        ClientHospital.tcl.getDiagnosis(patient.getGospitalCod()));
            }
        } catch (KmiacServerException e1) {
            e1.printStackTrace();
        } catch (DiagnosisNotFoundException e) {
            tbDiag.setData(new ArrayList<TDiagnosis>());
        } catch (TException e1) {
            ClientHospital.conMan.reconnect(e1);
        }
    }

///////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////            Этапы         ///////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////

    private void setStagePanel() {
        pStage = new JPanel();
        tabbedPane.addTab("Этапы лечения", null, pStage, null);
        pStage.setLayout(new BoxLayout(pStage, BoxLayout.X_AXIS));

        addStageTablePanel();
        addStageButtonPanel();
    }

    private void addStageTablePanel() {
        spStageTable = new JScrollPane();
        spStageTable.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.GRAY));
        pStage.add(spStageTable);

        tbStages = new CustomTable<TStage, TStage._Fields>(
            true, true, TStage.class, 4, "Дата начала", 5, "Дата окончания", 2,
            "Этап", 3, "МЭС");
        tbStages.setDateField(0);
        tbStages.setDateField(1);
        tbStages.setIntegerClassifierSelector(2, IntegerClassifiers.n_etp);
        spStageTable.setViewportView(tbStages);
    }

    private void addStageButtonPanel() {
        pStageButtons = new JPanel();
        pStageButtons.setMaximumSize(new Dimension(50, 32767));
        pStage.add(pStageButtons);
        pStageButtons.setLayout(new BoxLayout(pStageButtons, BoxLayout.Y_AXIS));

        addStageAddButton();
        addStageDeleteButton();
        addStageUpdateButton();
    }

    private void addStageAddButton() {
        btnAddStage = new JButton();
        btnAddStage.setMaximumSize(new Dimension(50, 50));
        btnAddStage.setIcon(new ImageIcon(MainFrame.class.getResource(
                "/ru/nkz/ivcgzo/clientHospital/resources/1331789242_Add.png")));
        btnAddStage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                addStageToTable();
            }
        });
        pStageButtons.add(btnAddStage);
    }

    private void addStageToTable() {
        try {
            if (patient != null) {
                TStage stage = new TStage();
                stage.setDateStart(System.currentTimeMillis());
                stage.setIdGosp(patient.getGospitalCod());
                stage.setId(ClientHospital.tcl.addStage(stage));
                tbStages.addItem(stage);
                tbStages.setData(
                    ClientHospital.tcl.getStage(patient.getGospitalCod()));
            }
        } catch (KmiacServerException e1) {
            e1.printStackTrace();
        } catch (TException e1) {
            ClientHospital.conMan.reconnect(e1);
        }
    }

    private void addStageDeleteButton() {
        btnDeleteStage = new JButton();
        btnDeleteStage.setMaximumSize(new Dimension(50, 50));
        btnDeleteStage.setIcon(new ImageIcon(MainFrame.class.getResource(
                "/ru/nkz/ivcgzo/clientHospital/resources/1331789259_Delete.png")));
        btnDeleteStage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                deleteStageFromTable();
            }
        });
        pStageButtons.add(btnDeleteStage);
    }

    private void deleteStageFromTable() {
        try {
            if (tbStages.getSelectedItem() != null) {
                int opResult = JOptionPane.showConfirmDialog(
                    MainFrame.this, "Удалить этап лечения?",
                    "Удаление этапа лечения", JOptionPane.YES_NO_OPTION);
                if (opResult == JOptionPane.YES_OPTION) {
                    ClientHospital.tcl.deleteStage(tbStages.getSelectedItem().getId());
                    tbStages.setData(
                        ClientHospital.tcl.getStage(patient.getGospitalCod()));
                }
                if (tbStages.getRowCount() > 0) {
                    tbStages.setRowSelectionInterval(tbStages.getRowCount() - 1,
                        tbStages.getRowCount() - 1);
                }
            }
        } catch (KmiacServerException e1) {
            e1.printStackTrace();
        } catch (TException e1) {
            ClientHospital.conMan.reconnect(e1);
        }
    }

    private void addStageUpdateButton() {
        btnUpdateStage = new JButton();
        btnUpdateStage.setMaximumSize(new Dimension(50, 50));
        btnUpdateStage.setIcon(new ImageIcon(MainFrame.class.getResource(
                "/ru/nkz/ivcgzo/clientHospital/resources/1341981970_Accept.png")));
        btnUpdateStage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                saveStageToTable();
            }
        });
        pStageButtons.add(btnUpdateStage);
    }

    private void saveStageToTable() {
        try {
            if (tbStages.getSelectedItem() != null) {
                int opResult = JOptionPane.showConfirmDialog(
                        MainFrame.this, "Добавить информацию об этапе лечения?",
                    "Изменение этапа лечения", JOptionPane.YES_NO_OPTION);
                if (opResult == JOptionPane.YES_OPTION) {
                    ClientHospital.tcl.updateStage(tbStages.getSelectedItem());
                }
            }
        } catch (MesNotFoundException e) {
            JOptionPane.showMessageDialog(MainFrame.this,
                "Данного кода МЭС не существует!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
        } catch (KmiacServerException e) {
            e.printStackTrace();
        } catch (TException e) {
            ClientHospital.conMan.reconnect(e);
        }
    }

    private void fillStageTable() {
        if (patient != null) {
            try {
                List<TStage> tmpStages =
                    ClientHospital.tcl.getStage(patient.getGospitalCod());
                if (tmpStages.size() > 0) {
                    tbStages.setData(tmpStages);
                } else {
                    tbStages.setData(Collections.<TStage>emptyList());
                }
            } catch (KmiacServerException e) {
                e.printStackTrace();
            } catch (TException e) {
                ClientHospital.conMan.reconnect(e);
            }
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////// Заключение ///////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////

    private void setZaklPanel() {
        pZakl = new JPanel();
        tabbedPane.addTab("Заключение", null, pZakl, null);
        setZaklShablonComponents();
        setZaklTextAreas();
        setZaklComboboxes();
        setZaklButtons();
        setZaklPanelGroupLayout();
    }

    private void setZaklShablonComponents() {
        spZaklShablonNames = new JScrollPane();
        lZaklShablonNames = new ThriftIntegerClassifierList();
        lZaklShablonNames.setBorder(new LineBorder(new Color(0, 0, 0)));
        lZaklShablonNames.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (lZaklShablonNames.getSelectedValue() != null) {
                        try {
                            pasteZaklSelectedShablon(ClientHospital.tcl.getShablon(
                                    lZaklShablonNames.getSelectedValue().pcod));
                        } catch (KmiacServerException e1) {
                            JOptionPane.showMessageDialog(MainFrame.this,
                                "Ошибка загрузки шаблона", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        } catch (TException e1) {
                            ClientHospital.conMan.reconnect(e1);
                        }
                    }
                }
            }
        });
        spZaklShablonNames.setViewportView(lZaklShablonNames);

        tfZaklShablonNames = new CustomTextField(true, true, false);
        tfZaklShablonNames.setColumns(10);
        zaklSearchListener = new ShablonSearchListener(tfZaklShablonNames, lZaklShablonNames);
        tfZaklShablonNames.getDocument().addDocumentListener(zaklSearchListener);
    }

    private void pasteZaklSelectedShablon(final Shablon shablon) {
        if (shablon == null) {
            return;
        }

        clearZaklText();

        for (ShablonText shText : shablon.textList) {
            switch (shText.grupId) {
                case 12:
                    taRecomend.setText(shText.getText());
                    break;
                case 13:
                    taZakluch.setText(shText.getText());
                    break;
                default:
                    break;
            }
        }
    }

    private void clearZaklText() {
        taRecomend.setText("");
        taZakluch.setText("");
        cdeZaklDate.setDate(new Date());
        cdeZaklTime.setTime(new Date());
    }

    private void setZaklTextAreas() {
        spRecomend = new JScrollPane();
        lblRecomend = new JLabel("Рекомендации");
        taRecomend = new JTextArea();
        taRecomend.setWrapStyleWord(true);
        taRecomend.setLineWrap(true);
        taRecomend.setFont(new Font("Tahoma", Font.PLAIN, 11));
        spRecomend.setViewportView(taRecomend);

        spZakluch = new JScrollPane();
        lblZakluch = new JLabel("Состояние при выписке");
        taZakluch = new JTextArea();
        taZakluch.setLineWrap(true);
        taZakluch.setWrapStyleWord(true);
        taZakluch.setFont(new Font("Tahoma", Font.PLAIN, 11));
        spZakluch.setViewportView(taZakluch);


        lblIshod = new JLabel("Исход заболевания");
        lblResult = new JLabel("Результат лечения");


        lblZaklDate = new JLabel("Дата выписки");
        cdeZaklDate = new CustomDateEditor();
        cdeZaklDate.setColumns(10);

        lblZaklTime = new JLabel("Время выписки");
        cdeZaklTime = new CustomTimeEditor();
        cdeZaklTime.setColumns(10);
    }

    private void setZaklComboboxes() {
        lblVidPom = new JLabel("Вид помощи");

        cbxVidPom = new ThriftIntegerClassifierCombobox<IntegerClassifier>(
                IntegerClassifiers.n_vp1);

        cbxDefect = new ThriftIntegerClassifierCombobox<IntegerClassifier>(
                IntegerClassifiers.n_def);

        lblDefect = new JLabel("Дефекты доп. этапа");

        lblUkl = new JLabel("УКЛ");

        tfUkl = new JTextField();
        tfUkl.setColumns(10);
        cbxIshod = new ThriftIntegerClassifierCombobox<IntegerClassifier>(true);
        cbxResult = new ThriftIntegerClassifierCombobox<IntegerClassifier>(true);
    }

    private void setZaklButtons() {
        btnSaveZakl = new JButton("Выписать");
        btnSaveZakl.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    if ((patient != null)
                            && (((cbxIshod.getSelectedItem() != null)
                                    && (cbxResult.getSelectedItem() != null)
                                    && (cbxIshod.getSelectedPcod() != 2))
                                ||  ((cbxIshod.getSelectedPcod() == 2)
                                    && (cbxResult.getSelectedItem() == null)))) {
                        Zakl tmpZakl = new Zakl();
                        tmpZakl.setRecom(taRecomend.getText());
                        tmpZakl.setSostv(taZakluch.getText());
                        tmpZakl.setIshod(cbxIshod.getSelectedPcod());
                        if (cbxResult.getSelectedItem() != null) {
                            tmpZakl.setResult(cbxResult.getSelectedPcod());
                        }
                        tmpZakl.setDatav(cdeZaklDate.getDate().getTime());
                        tmpZakl.setVremv(cdeZaklTime.getTime().getTime());
                        tmpZakl.setIdGosp(patient.getGospitalCod());
                        ClientHospital.tcl.addZakl(tmpZakl);
                        JOptionPane.showMessageDialog(MainFrame.this,
                            "Пациент успешно выписан", "Выписка пациента",
                            JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(MainFrame.this,
                            "Не выбран пациент, либо не заполнены поля \"Результат лечения\" "
                            + "или \"Исход заболевания\"", "Ошибка!",
                            JOptionPane.ERROR_MESSAGE);
                    }
                } catch (KmiacServerException e1) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка при выписке пациента. Информация не сохранена", "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                } catch (TException e1) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка при выписке пациента. Информация не сохранена", "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                    ClientHospital.conMan.reconnect(e1);
                }
            }
        });
    }

////////////////////////////////////////// CAUTION! ///////////////////////////////////////////////
/////////////////// Автогенерируемое нечитаемое говно. Спасибо ВиндоуБилдеру за это. //////////////
///////////////////////////////////////////////////////////////////////////////////////////////////

    private void setPatientInfoPanelGroupLayout() {
        GroupLayout glPersonalInfo = new GroupLayout(pPersonalInfo);
        glPersonalInfo.setHorizontalGroup(
            glPersonalInfo.createParallelGroup(Alignment.LEADING)
                .addGroup(glPersonalInfo.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(glPersonalInfo.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblNumberDesiaseHistory)
                        .addComponent(lblGender)
                        .addComponent(lblChamber)
                        .addComponent(lblRegistrationAddress)
                        .addComponent(lblRealAddress))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(glPersonalInfo.createParallelGroup(Alignment.LEADING)
                        .addGroup(glPersonalInfo.createSequentialGroup()
                            .addGroup(glPersonalInfo.createParallelGroup(Alignment.LEADING)
                                .addComponent(tfChamber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfGender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfNumberOfDesiaseHistory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(31)
                            .addGroup(glPersonalInfo.createParallelGroup(Alignment.LEADING)
                                .addComponent(lblSurname)
                                .addComponent(lblBirthdate)
                                .addComponent(lblStatus))
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addGroup(glPersonalInfo.createParallelGroup(Alignment.LEADING)
                                .addComponent(tfBirthdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(34)
                            .addGroup(glPersonalInfo.createParallelGroup(Alignment.LEADING)
                                .addComponent(lblName)
                                .addComponent(lblOms)
                                .addComponent(lblWork))
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addGroup(glPersonalInfo.createParallelGroup(Alignment.LEADING)
                                .addGroup(glPersonalInfo.createSequentialGroup()
                                    .addGroup(glPersonalInfo.createParallelGroup(Alignment.LEADING)
                                        .addComponent(tfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tfOms, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(43)
                                    .addGroup(glPersonalInfo.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblMiddlename)
                                        .addComponent(lblDms)))
                                .addComponent(tfWork, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addComponent(tfRealAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfRegistrationAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(glPersonalInfo.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(tfMiddlename, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfDms, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
                        .addGroup(glPersonalInfo.createSequentialGroup()
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(btnUpdateChamber, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(btnShowPatientInfo))
                        .addComponent(btnMedication, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnIssled, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(5))
        );
        glPersonalInfo.setVerticalGroup(
            glPersonalInfo.createParallelGroup(Alignment.LEADING)
                .addGroup(glPersonalInfo.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(glPersonalInfo.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNumberDesiaseHistory)
                        .addComponent(tfNumberOfDesiaseHistory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSurname)
                        .addComponent(tfSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblName)
                        .addComponent(tfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMiddlename)
                        .addComponent(tfMiddlename, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(glPersonalInfo.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblGender)
                        .addComponent(tfGender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblBirthdate)
                        .addComponent(tfBirthdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblOms)
                        .addComponent(tfOms, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDms)
                        .addComponent(tfDms, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(glPersonalInfo.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblChamber)
                        .addComponent(tfChamber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblStatus)
                        .addComponent(tfStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblWork)
                        .addComponent(tfWork, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnShowPatientInfo)
                        .addComponent(btnUpdateChamber))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(glPersonalInfo.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblRegistrationAddress)
                        .addComponent(tfRegistrationAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnMedication))
                    .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(glPersonalInfo.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblRealAddress)
                        .addComponent(tfRealAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnIssled)))
        );
        pPersonalInfo.setLayout(glPersonalInfo);
    }

    private void setZaklPanelGroupLayout() {
        GroupLayout glPZakl = new GroupLayout(pZakl);
        glPZakl.setHorizontalGroup(
            glPZakl.createParallelGroup(Alignment.LEADING)
                .addGroup(glPZakl.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(glPZakl.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(lblRecomend)
                        .addComponent(btnSaveZakl, GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
                        .addComponent(spRecomend, GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
                        .addComponent(lblZakluch)
                        .addComponent(spZakluch, GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
                        .addComponent(lblVidPom)
                        .addComponent(lblDefect)
                        .addComponent(cbxVidPom, 0, 701, Short.MAX_VALUE)
                        .addComponent(cbxDefect, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addComponent(cbxIshod, GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
                        .addComponent(lblResult)
                        .addComponent(cbxResult, GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
                        .addGroup(glPZakl.createSequentialGroup()
                            .addComponent(lblZaklDate)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(cdeZaklDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(lblZaklTime)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(cdeZaklTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addComponent(lblIshod)
                        .addComponent(lblUkl)
                        .addComponent(tfUkl))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(glPZakl.createParallelGroup(Alignment.LEADING)
                        .addGroup(glPZakl.createSequentialGroup()
                            .addComponent(spZaklShablonNames, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                            .addGap(5))
                        .addGroup(glPZakl.createSequentialGroup()
                            .addComponent(tfZaklShablonNames, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                            .addContainerGap())))
        );
        glPZakl.setVerticalGroup(
            glPZakl.createParallelGroup(Alignment.LEADING)
                .addGroup(glPZakl.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(glPZakl.createParallelGroup(Alignment.LEADING)
                        .addGroup(glPZakl.createSequentialGroup()
                            .addGap(1)
                            .addComponent(tfZaklShablonNames, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(8)
                            .addComponent(spZaklShablonNames, GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
                            .addGap(1))
                        .addGroup(glPZakl.createSequentialGroup()
                            .addComponent(lblRecomend)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(spRecomend, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblZakluch)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(spZakluch, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
                            .addGap(1)
                            .addComponent(lblVidPom)
                            .addGap(1)
                            .addComponent(cbxVidPom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(4)
                            .addComponent(lblDefect)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(cbxDefect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblUkl)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(tfUkl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                            .addComponent(lblIshod)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(cbxIshod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblResult)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(cbxResult, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addGroup(glPZakl.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblZaklDate)
                                .addComponent(cdeZaklDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblZaklTime)
                                .addComponent(cdeZaklTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(btnSaveZakl)
                            .addGap(9)))
                    .addGap(0))
        );
        pZakl.setLayout(glPZakl);
    }
}
