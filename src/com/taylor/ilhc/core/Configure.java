package com.taylor.ilhc.core;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.awt.Desktop;
import java.net.URI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Configure {

	protected Shell shlConfigurationUtility;
	private Text jsonPath;
	private Text oauthKey;
	private Text channelName;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Configure window = new Configure();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlConfigurationUtility.open();
		shlConfigurationUtility.layout();
		while (!shlConfigurationUtility.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlConfigurationUtility = new Shell();
		shlConfigurationUtility.setSize(450, 173);
		shlConfigurationUtility.setText("Configuration Utility");
		
		jsonPath = new Text(shlConfigurationUtility, SWT.BORDER);
		jsonPath.setText("C:\\Temp\\songlistjson.json");
		jsonPath.setBounds(146, 10, 278, 21);
		
		Label lblPathToSonglistjsonjson = new Label(shlConfigurationUtility, SWT.NONE);
		lblPathToSonglistjsonjson.setBounds(10, 13, 130, 15);
		lblPathToSonglistjsonjson.setText("Path to songlistjson.json");
		
		oauthKey = new Text(shlConfigurationUtility, SWT.BORDER);
		oauthKey.setBounds(146, 37, 278, 21);
		if (ConfigEdit.oauthKey != null) {
			oauthKey.setText(ConfigEdit.oauthKey);
		}
		
		Label lblOauthKey = new Label(shlConfigurationUtility, SWT.NONE);
		lblOauthKey.setBounds(85, 40, 55, 15);
		lblOauthKey.setText("Oauth key");
		
		channelName = new Text(shlConfigurationUtility, SWT.BORDER);
		channelName.setBounds(146, 64, 278, 21);
		if (ConfigEdit.channelName != null) {
			channelName.setText(ConfigEdit.channelName);
		}
		
		Label lblChannelName = new Label(shlConfigurationUtility, SWT.NONE);
		lblChannelName.setBounds(61, 67, 79, 15);
		lblChannelName.setText("Channel Name");
		
		Button btnOk = new Button(shlConfigurationUtility, SWT.NONE);
		btnOk.setBounds(349, 99, 75, 25);
		btnOk.setText("Ok");
		btnOk.addListener(SWT.Selection, event-> {
			ConfigFile.oauthKey = oauthKey.getText();
			ConfigFile.jsonPath = jsonPath.getText();
			ConfigFile.channelName = channelName.getText();
			ConfigFile.wait = false;
			
			ConfigEdit.oauthKey = oauthKey.getText();
			ConfigEdit.jsonPath = jsonPath.getText();
			ConfigEdit.channelName = channelName.getText();
			ConfigEdit.wait = false;
			ConfigEdit.changesMade = true;
			
			shlConfigurationUtility.close();
		});
		
		Button btnCancel = new Button(shlConfigurationUtility, SWT.NONE);
		btnCancel.setBounds(268, 99, 75, 25);
		btnCancel.setText("Cancel");
		
		Button btnHelp = new Button(shlConfigurationUtility, SWT.NONE);
		btnHelp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					  Desktop desktop = java.awt.Desktop.getDesktop();
					  URI oURL = new URI("https://twitchapps.com/tmi/");
					  desktop.browse(oURL);
					} catch (Exception e1) {
					  e1.printStackTrace();
					}
			}
		});
		btnHelp.setBounds(20, 35, 55, 25);
		btnHelp.setText("Help");
		btnCancel.addListener(SWT.Selection, event-> {
			shlConfigurationUtility.close();
		});

	}
}
