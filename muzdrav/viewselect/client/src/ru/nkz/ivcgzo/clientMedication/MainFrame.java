package ru.nkz.ivcgzo.clientMedication;

import javax.swing.JFrame;

import ru.nkz.ivcgzo.thriftCommon.kmiacServer.UserAuthInfo;
import ru.nkz.ivcgzo.thriftMedication.Patient;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = -8573682902821548961L;
//    private UserAuthInfo doctorInfo;
    private Patient patient;
    private JTable tbMedication;
    private JScrollPane spMedicationTable;
    private JPanel pButtons;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private MedicationCatalogFrame frmMedicationCatalog;

    public MainFrame(final UserAuthInfo authInfo) {
//        doctorInfo = authInfo;
        initialization();
    }

    private void initialization() {
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        addTableScrollPane();
        addButtonPanel();
    }

    private void addTableScrollPane() {
        spMedicationTable = new JScrollPane();
        getContentPane().add(spMedicationTable);

        addMedicationTable();
    }

    private void addMedicationTable() {
        tbMedication = new JTable();
        spMedicationTable.setViewportView(tbMedication);
    }

    private void addButtonPanel() {
        pButtons = new JPanel();
        getContentPane().add(pButtons);

        addAddButton();
        addUpdateButton();
        addDeleteButton();
    }

    private void addAddButton() {
        pButtons.setLayout(new BoxLayout(pButtons, BoxLayout.Y_AXIS));
        btnAdd = new JButton("Добавить");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmMedicationCatalog.setVisible(true);
            }
        });
        pButtons.add(btnAdd);
    }

    private void addUpdateButton() {
        btnUpdate = new JButton("Изменить");
        pButtons.add(btnUpdate);
    }

    private void addDeleteButton() {
        btnDelete = new JButton("Удалить");
        pButtons.add(btnDelete);
    }

    public final void fillPatient(final int id, final String surname,
            final String name, final String middlename, final int idGosp) {
        patient = new Patient();
        patient.setId(id);
        patient.setSurname(surname);
        patient.setName(name);
        patient.setMiddlename(middlename);
        patient.setIdGosp(idGosp);
    }

    private void createModalFrames() {
        frmMedicationCatalog = new MedicationCatalogFrame();
        frmMedicationCatalog.pack();
    }

    public final void onConnect() {
        createModalFrames();
    }
}
