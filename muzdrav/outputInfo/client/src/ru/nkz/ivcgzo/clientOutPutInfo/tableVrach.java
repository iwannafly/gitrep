package ru.nkz.ivcgzo.clientOutPutInfo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ru.nkz.ivcgzo.clientManager.common.swing.CustomTable;
import ru.nkz.ivcgzo.thriftOutputInfo.VrachInfo;
import ru.nkz.ivcgzo.thriftOutputInfo.VrachTabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.FlowLayout;

public class tableVrach extends JPanel {
	
	private static CustomTable<VrachInfo, VrachInfo._Fields> tableVrachInfo;
	private static CustomTable<VrachTabel, VrachTabel._Fields> tableVrachTabel;

/**public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				//tableVrach window = new tableVrach();
				//window.frameVr.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}*/

public tableVrach(){
	JScrollPane scrollPane = new JScrollPane();
	initialize();

}

public void initialize() {

	JScrollPane vrPane = new JScrollPane();

	tableVrachInfo = new CustomTable<>(false, true, VrachInfo.class, 0, "Код врача", 1, "Фамилия", 2, "Имя", 3, "Отчество", 4, "Должность");
	tableVrachInfo.setAutoCreateRowSorter(true);
	tableVrachInfo.getRowSorter().toggleSortOrder(0);
	tableVrachInfo.setFillsViewportHeight(true);
	tableVrachInfo.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	tableVrachInfo.getColumnModel().getColumn(0).setMaxWidth(70);
	vrPane.setViewportView(tableVrachInfo);
	
	JScrollPane timePane = new JScrollPane();
	
	tableVrachTabel = new CustomTable<>(false, true, VrachTabel.class, 0, "Код врача", 1, "Должность", 2, "Дата приема", 3, "В поликлинике", 4, "На дому", 5, "На дому актив", 6, "Проф.осмотр", 7, "Прочие", 8, "№1 участка", 9, "№2 участка", 10, "№3 участка");
	tableVrachTabel.setAutoCreateRowSorter(true);
	tableVrachTabel.getRowSorter().toggleSortOrder(0);
	tableVrachTabel.setFillsViewportHeight(true);
	tableVrachTabel.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	tableVrachTabel.getColumnModel().getColumn(0).setMaxWidth(70);
	timePane.setViewportView(tableVrachTabel);
	
	JPanel butPanel = new JPanel();
	
		butPanel.setLayout(new GridLayout(1, 3, 5, 0) );
		
		JButton butCreate = new JButton("Создать");
		butCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "Создать");
			}
		});
		butPanel.add(butCreate);
		JButton butDelete = new JButton("Удалить");
		butDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "Удалить");
			}
		});
		butPanel.add(butDelete);
		JButton butForward = new JButton("Вперед");
		butForward.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "Вперед");
			}
		});
		butPanel.add(butForward);
		JButton butBack = new JButton("Назад");
		butBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "Назад");
			}
		});
		butPanel.add(butBack);
		JButton butSave = new JButton("Сохранить");
		butSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "Сохранить");
			}
		});
		butPanel.add(butSave);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(butPanel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(vrPane, Alignment.LEADING))
					.addGap(39))
				.addComponent(timePane, GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(vrPane, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(butPanel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(timePane, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	
}
}