package ru.nkz.ivcgzo.ldsclient;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import ru.nkz.ivcgzo.configuration;
import ru.nkz.ivcgzo.clientManager.common.Client;
import ru.nkz.ivcgzo.clientManager.common.ConnectionManager;
import ru.nkz.ivcgzo.ldsThrift.LDSThrift;
import ru.nkz.ivcgzo.thriftCommon.kmiacServer.UserAuthInfo;

public class MainForm extends Client{

	public MainForm(ConnectionManager conMan, UserAuthInfo authInfo, int lncPrm) {
		super(conMan, authInfo, lncPrm);

		initialize();
		if (conMan != null) {
			try {
				conMan.add(LDSThrift.Client.class, configuration.thrPort);
				conMan.setLocalForm(frame);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
			try {
				TTransport transport = new TFramedTransport(new TSocket("localhost", configuration.thrPort));
				transport.open();
				onConnect(new LDSThrift.Client(new TBinaryProtocol(transport)));
			} catch (TTransportException e) {
				e.printStackTrace();
				System.exit(1);
			}
		frame.setVisible(true);
	}

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm(null, null, 0);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onConnect(
			ru.nkz.ivcgzo.thriftCommon.kmiacServer.KmiacServer.Client conn) {
		// TODO Auto-generated method stub
		
	}

}
