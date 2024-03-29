package ru.nkz.ivcgzo.clientInfomat.model.tableModels;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.apache.thrift.TException;

import ru.nkz.ivcgzo.clientInfomat.ClientInfomat;
import ru.nkz.ivcgzo.clientInfomat.model.TalonList;

public final class TalonTableModel implements TableModel {
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
    private static final SimpleDateFormat DEFAULT_TIME_FORMAT = new SimpleDateFormat("HH:mm");
    private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("dd-MM-yy");
    private static final String[] DAY_NAMES = {
        "Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вск"
    };
    private TalonList talonList;

    public TalonTableModel(final int cpol, final String cdol, final int pcod) throws TException {
        setTalonList(cpol, cdol, pcod);
    }

    private void setTalonList(final int cpol, final String cdol, final int pcod) throws TException {
        talonList = new TalonList(ClientInfomat.tcl.getTalons(cpol, cdol, pcod));
    }

    public TalonList getTalonList() {
        return talonList;
    }

    @Override
    public int getRowCount() {
        return talonList.getMaximumListSize();
    }

    @Override
    public int getColumnCount() {
        return talonList.getWeekDays().length;
    }

    @Override
    public Object getValueAt(final int rowIndex, final int columnIndex) {
        String displayedTime = "";
        if ((talonList.getTimeOfAppointmentByDay(rowIndex, columnIndex)) != null) {
            displayedTime = DEFAULT_TIME_FORMAT.format(
                talonList.getTimeOfAppointmentByDay(rowIndex, columnIndex));
        }
        return displayedTime; //talonList.getTimeOfAppointmentByDay(rowIndex, columnIndex);
    }

    @Override
    public String getColumnName(final int columnIndex) {
        return String.format("%s %s", DAY_NAMES[columnIndex],
            DEFAULT_DATE_FORMAT.format(talonList.getWeekDays()[columnIndex]));
    }

    @Override
    public Class<?> getColumnClass(final int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(final int rowIndex, final int columnIndex) {
        return false;
    }

    @Override
    public void setValueAt(final Object aValue, final int rowIndex, final int columnIndex) {
    }

    public void setPrevWeek() {
        talonList.setPrevWeek();
    }

    public void setNextWeek() {
        talonList.setNextWeek();
    }

    @Override
    public void addTableModelListener(final TableModelListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeTableModelListener(final TableModelListener listener) {
        listeners.remove(listener);
    }
}
