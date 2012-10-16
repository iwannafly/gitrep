package ru.nkz.ivcgzo.clientGenReestr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import org.apache.thrift.TException;

import ru.nkz.ivcgzo.clientManager.common.swing.CustomDateEditor;
import ru.nkz.ivcgzo.clientManager.common.swing.ThriftIntegerClassifierCombobox;
import ru.nkz.ivcgzo.thriftCommon.classifier.IntegerClassifier;
import ru.nkz.ivcgzo.thriftCommon.fileTransfer.OpenFileException;
import ru.nkz.ivcgzo.thriftCommon.kmiacServer.KmiacServerException;
import ru.nkz.ivcgzo.thriftGenReestr.ReestrNotFoundException;


public class SettingsForm extends JDialog {
	private static final long serialVersionUID = -48586555281952961L;
	private CustomDateEditor tfDn;
	private CustomDateEditor tfDk;
	private final ButtonGroup bgrp = new ButtonGroup();
	private JRadioButton rbtn1;
	private JRadioButton rbtn2;
	private JRadioButton rbtn3;
	private JRadioButton rbtn4;
	private int vidrstr;
    private ThriftIntegerClassifierCombobox <IntegerClassifier> cmb_podr;
	
	public SettingsForm() {
		setModalityType(ModalityType.TOOLKIT_MODAL);
		setBounds(100, 100, 344, 382); //ширина, высота
		setTitle("Реестры пациентов");
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Период :", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Выгрузить :", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Подразделение ЛПУ :", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		cmb_podr = new ThriftIntegerClassifierCombobox<>(true);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addComponent(cmb_podr, 0, 300, Short.MAX_VALUE)
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(cmb_podr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
		rbtn1 = new JRadioButton("новые посещения");
		rbtn2 = new JRadioButton("новые посещения + ошибки контроля");
		rbtn3 = new JRadioButton("ошибки контроля");
		rbtn4 = new JRadioButton("все случаи лечения");
		bgrp.add(rbtn1);
		bgrp.add(rbtn2);
		bgrp.add(rbtn3);
		bgrp.add(rbtn4);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(rbtn1)
						.addComponent(rbtn2)
						.addComponent(rbtn3)
						.addComponent(rbtn4))
					.addContainerGap(169, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(rbtn1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rbtn2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rbtn3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rbtn4)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblNewLabel = new JLabel("с");
		JLabel lblNewLabel_1 = new JLabel("по");
		
		tfDk = new CustomDateEditor();
		tfDn = new CustomDateEditor();
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addGap(13)
					.addComponent(tfDn, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tfDk, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(60, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(tfDn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(tfDk, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JButton btnRun = new JButton("Выполнить");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
				String servPath = null;
				String cliPath = null;
				if (rbtn1.isSelected()) vidrstr = 1;
				if (rbtn2.isSelected()) vidrstr = 2;
				if (rbtn3.isSelected()) vidrstr = 3;
				if (rbtn4.isSelected()) vidrstr = 4;
				try {
					if(cmb_podr.getSelectedPcod() != 0 || (tfDn.getDate().getTime() <= tfDk.getDate().getTime() || vidrstr != 0)){
				        if (MainForm.authInfo.getCslu() == 1)
				        	servPath = MainForm.tcl.getReestrInfoOtd(cmb_podr.getSelectedPcod(), tfDn.getDate().getTime(), tfDk.getDate().getTime(), vidrstr, 2, MainForm.authInfo.getClpu(), MainForm.authInfo.getKdate(), System.currentTimeMillis());
				        if (MainForm.authInfo.getCslu() == 2)
				        	servPath = MainForm.tcl.getReestrInfoPol(cmb_podr.getSelectedPcod(), tfDn.getDate().getTime(), tfDk.getDate().getTime(), vidrstr, 2, MainForm.authInfo.getClpu(), MainForm.authInfo.getKdate(), System.currentTimeMillis());
				        if (MainForm.authInfo.getCslu() == 3)
				        	servPath = MainForm.tcl.getReestrInfoLDS(cmb_podr.getSelectedPcod(), tfDn.getDate().getTime(), tfDk.getDate().getTime(), vidrstr, 2, MainForm.authInfo.getClpu(), MainForm.authInfo.getKdate(), System.currentTimeMillis());
						if (servPath.endsWith("zip")){
							cliPath = "C:\\L_"+sdf.format(new Date())+"_"+MainForm.authInfo.getKdate()+cmb_podr.getSelectedPcod()+".rar";
	   						MainForm.conMan.transferFileFromServer(servPath, cliPath);
							JOptionPane.showMessageDialog(null, "Файл : "+cliPath, null, JOptionPane.INFORMATION_MESSAGE); 
						}
						else{
							cliPath = File.createTempFile("reestrInfo", ".htm").getAbsolutePath();
	   						MainForm.conMan.transferFileFromServer(servPath, cliPath);
	   						MainForm.conMan.openFileInEditor(cliPath, false);
						}
						dispose();
					}else
						JOptionPane.showMessageDialog(null, "Укажите все параметры формирования реестра.", null, JOptionPane.INFORMATION_MESSAGE); 
				} catch (ReestrNotFoundException | TException e) {
					e.printStackTrace();
				} catch (KmiacServerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ru.nkz.ivcgzo.thriftCommon.fileTransfer.FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OpenFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		JButton btnClose = new JButton("Выход");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(28)
					.addComponent(btnRun)
					.addGap(33)
					.addComponent(btnClose)
					.addContainerGap(77, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRun)
						.addComponent(btnClose))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}

	public void showSettingsForm() {
        if (tfDn.getDate() == null) tfDn.setDate(System.currentTimeMillis());
        if (tfDk.getDate() == null) tfDk.setDate(System.currentTimeMillis());
        if (!rbtn1.isSelected() && !rbtn2.isSelected() && !rbtn3.isSelected() && !rbtn4.isSelected()) rbtn1.setSelected(true);
			try{
		        if (MainForm.authInfo.getCslu() == 1){
		        	if (Integer.toString(MainForm.authInfo.getCpodr()).length() == 4) cmb_podr.setData(MainForm.tcl.getOtdForCurrentLpu(MainForm.authInfo.getCpodr()));
		        	if (Integer.toString(MainForm.authInfo.getCpodr()).length() == 2) cmb_podr.setData(MainForm.tcl.getAllOtdForCurrentLpu(MainForm.authInfo.getCpodr()));
				}
		        if (MainForm.authInfo.getCslu() == 2){
		        	if (Integer.toString(MainForm.authInfo.getCpodr()).length() == 3) cmb_podr.setData(MainForm.tcl.getPolForCurrentLpu(MainForm.authInfo.getCpodr()));
		        	if (Integer.toString(MainForm.authInfo.getCpodr()).length() == 2) cmb_podr.setData(MainForm.tcl.getAllPolForCurrentLpu(MainForm.authInfo.getCpodr()));
				}
		        if (MainForm.authInfo.getCslu() == 3){
		        	if (Integer.toString(MainForm.authInfo.getCpodr()).length() == 7) cmb_podr.setData(MainForm.tcl.getLDSForCurrentLpu(MainForm.authInfo.getCpodr()));
		        	if (Integer.toString(MainForm.authInfo.getCpodr()).length() == 2) cmb_podr.setData(MainForm.tcl.getAllLDSForCurrentLpu(MainForm.authInfo.getCpodr()));
				}
	        	cmb_podr.setSelectedPcod(MainForm.authInfo.getCpodr());
			} catch (TException e) {
				e.printStackTrace();
				MainForm.conMan.reconnect(e);
			} catch (KmiacServerException e) {
				e.printStackTrace();
		}
		setVisible(true);
	}
}
