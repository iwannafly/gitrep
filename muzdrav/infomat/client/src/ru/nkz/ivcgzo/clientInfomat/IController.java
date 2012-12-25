package ru.nkz.ivcgzo.clientInfomat;

import javax.swing.JFrame;

import ru.nkz.ivcgzo.thriftCommon.classifier.IntegerClassifier;
import ru.nkz.ivcgzo.thriftCommon.classifier.StringClassifier;
import ru.nkz.ivcgzo.thriftInfomat.TPatient;
import ru.nkz.ivcgzo.thriftInfomat.TTalon;


public interface IController {
    void setPoliclinics();

    void setCurrentPoliclinic(IntegerClassifier currentPoliclinic);

    void setCurrentSpeciality(StringClassifier currentSpeciality);

    void setCurrentDoctor(IntegerClassifier currentDoctor);

    void setSpecialities(int cpol);

    void setDoctors(int cpol, String cdol);

    void setTalons(int cpol, String cdol, int pcod);

    void setPatient(String oms);

    void setReservedTalon(int patientId);

    void setShedule(int pcod, int cpol, String cdol);

    void reserveTalon(TPatient pat, TTalon talon);

    void releaseTalon(TTalon talon);

    void setSelectedTalon(TTalon talon);

    void setCurrentReservedTalon(TTalon talon);

    //FIXME костыль, избавиться от него!
    JFrame getMainFrame();
}