package ru.nkz.ivcgzo.clientInfomat;

import javax.swing.JFrame;

import ru.nkz.ivcgzo.clientInfomat.model.IModel;
import ru.nkz.ivcgzo.clientInfomat.ui.InfomatView;
import ru.nkz.ivcgzo.thriftInfomat.TPatient;
import ru.nkz.ivcgzo.thriftInfomat.TTalon;

public class Controller implements IController {
    private IModel model;
    private InfomatView view;

    public Controller(IModel curModel) {
        this.model = curModel;
        this.view = new InfomatView(this, model);
        view.createFrames();
        view.createControls();
    }

    //FIXME костыль
    public JFrame getMainFrame() {
        return view.getMainFrame();
    }

    @Override
    public void setPoliclinics() {
        model.setPoliclinics();
    }

    @Override
    public void setSpecialities(int cpol) {
        model.setSpecialities(cpol);
    }

    @Override
    public void setDoctors(int cpol, String cdol) {
        model.setDoctors(cpol, cdol);
    }

    @Override
    public void setTalons(int cpol, String cdol, int pcod) {
        model.setTalons(cpol, cdol, pcod);
    }

    @Override
    public void setPatient(String oms) {
        model.setPatient(oms);
    }

    @Override
    public void setReservedTalon(int patientId) {
        model.setReservedTalon(patientId);
    }

    @Override
    public void setShedule(int pcod, int cpol, String cdol) {
        model.setShedule(pcod, cpol, cdol);
    }

    @Override
    public void reserveTalon(TPatient pat, TTalon talon) {
        model.releaseTalon(talon);
    }

    @Override
    public void releaseTalon(TTalon talon) {
        model.releaseTalon(talon);
    }

}
