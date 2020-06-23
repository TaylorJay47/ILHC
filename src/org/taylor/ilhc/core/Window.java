package org.taylor.ilhc.core;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import java.io.IOException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import com.cavariux.twitchirc.Chat.Channel;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class Window extends ILHC{
	
	protected static Shell shlSongRequestBot;
	public static Boolean takingReqs = true;
	public String requestSong;
	public String requestArtist;
	private static Text txtRequests;
	static boolean hasRequest;
	static String[] stringValues;
	static String message;
	public static Boolean wait = true;
	public static String channelName;
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
		ConfigFile configFile = new ConfigFile();
		configFile.open();
		ILHC bot = new ILHC();
		Runnable r = new Runnable() {
			@Override
			public void run() {
				bot.start();
			}
		};
		bot.connect();
	    Channel channel = bot.joinChannel(channelName);
	    Thread t = new Thread(r);
	    t.start();
	    bot.sendMessage("I am now taking requests. Please use !sr to request a song or artist.", channel);
		shlSongRequestBot = new Shell();
		shlSongRequestBot.setMinimumSize(new Point(350, 500));
		shlSongRequestBot.setSize(450, 700);
		shlSongRequestBot.setText("Song Request Bot");
		shlSongRequestBot.setLayout(new GridLayout(1, false));
		
		txtRequests = new Text(shlSongRequestBot, SWT.BORDER | SWT.MULTI);
		txtRequests.setEditable(false);
		txtRequests.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

	
		Composite compButtons = new Composite(shlSongRequestBot, SWT.NONE);
		GridData gd_compButtons = new GridData(SWT.FILL, SWT.BOTTOM, true, false, 1, 1);
		gd_compButtons.heightHint = 121;
		compButtons.setLayoutData(gd_compButtons);
		compButtons.setLayout(new GridLayout(8, false));
		new Label(compButtons, SWT.NONE);
		new Label(compButtons, SWT.NONE);
		new Label(compButtons, SWT.NONE);
		new Label(compButtons, SWT.NONE);
		new Label(compButtons, SWT.NONE);
		new Label(compButtons, SWT.NONE);
		new Label(compButtons, SWT.NONE);
		new Label(compButtons, SWT.NONE);
		new Label(compButtons, SWT.NONE);
		new Label(compButtons, SWT.NONE);
		new Label(compButtons, SWT.NONE);
		new Label(compButtons, SWT.NONE);
		new Label(compButtons, SWT.NONE);
		new Label(compButtons, SWT.NONE);
		new Label(compButtons, SWT.NONE);
		new Label(compButtons, SWT.NONE);
			
		Button btnSendHelp = new Button(compButtons, SWT.NONE);
		btnSendHelp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				bot.sendMessage("Use !sr to request a song or artist. Punctuation and case do not matter.", channel);
			}
		});
		GridData gd_btnSendHelp = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_btnSendHelp.widthHint = 87;
		btnSendHelp.setLayoutData(gd_btnSendHelp);
		btnSendHelp.setText("Send Help");
		new Label(compButtons, SWT.NONE);
		new Label(compButtons, SWT.NONE);
		new Label(compButtons, SWT.NONE);
		new Label(compButtons, SWT.NONE);
		new Label(compButtons, SWT.NONE);
		new Label(compButtons, SWT.NONE);
					
		Button btnNextSong = new Button(compButtons, SWT.CENTER);
		btnNextSong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String txtRequestsNew = txtRequests.getText().substring(txtRequests.getText().indexOf("\n")+1);
				txtRequests.setText(txtRequestsNew);
				txtRequestsNew = txtRequests.getText().substring(txtRequests.getText().indexOf("\n")+1);
				txtRequests.setText(txtRequestsNew);
			}
		});
		GridData gd_btnNextSong = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2);
		gd_btnNextSong.heightHint = 54;
		gd_btnNextSong.widthHint = 111;
		btnNextSong.setLayoutData(gd_btnNextSong);
		btnNextSong.setText("Next Song");
				
		Button btnStartBot = new Button(compButtons, SWT.NONE);
		btnStartBot.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			String label = btnStartBot.getText();
			if (label == "Start Bot") {
				btnStartBot.setText("Stop Bot");
				takingReqs = true;
				bot.sendMessage("I am now taking requests. Please use !sr to request a song or artist.", channel);
			} else if (label == "Stop Bot"){
				btnStartBot.setText("Start Bot");
				takingReqs = false;
				bot.sendMessage("I am no longer taking requests.", channel);        
			}
		}
		});
					
	
		GridData gd_btnStartBot = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_btnStartBot.widthHint = 87;
		btnStartBot.setLayoutData(gd_btnStartBot);
		btnStartBot.setText("Stop Bot");
		new Label(compButtons, SWT.NONE);
		new Label(compButtons, SWT.NONE);
		new Label(compButtons, SWT.NONE);
		new Label(compButtons, SWT.NONE);
		new Label(compButtons, SWT.NONE);
		new Label(compButtons, SWT.NONE);
		
		Menu menu = new Menu(shlSongRequestBot, SWT.BAR);
		shlSongRequestBot.setMenuBar(menu);
		
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("&File");
		Menu fileMenu = new Menu(shlSongRequestBot, SWT.DROP_DOWN);
		mntmFile.setMenu(fileMenu);
		
		MenuItem mntmConfigureSettings = new MenuItem(fileMenu, SWT.NONE);
		mntmConfigureSettings.setText("Configure Settings");
		mntmConfigureSettings.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				ConfigEdit configEdit = new ConfigEdit();
				configEdit.open();
			}
		});
		
		new MenuItem(fileMenu, SWT.SEPARATOR);
		
		MenuItem exitItem = new MenuItem(fileMenu, SWT.PUSH);
		exitItem.setText("&Exit");
		exitItem.addListener(SWT.Selection, event-> {
			shlSongRequestBot.getDisplay().dispose();
			System.exit(0);
			t.stop();
		});
		
		Display display = Display.getDefault();
		shlSongRequestBot.open();
		shlSongRequestBot.layout();
		shlSongRequestBot.addListener(SWT.Close, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				t.stop();
			}
		});
		while (!shlSongRequestBot.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	public static void addText(String string){
		txtRequests.append(string + "\n");
	}
	
	static Runnable runAddText = new Runnable() {
		@Override
		public void run() {
			Window.addText(ILHC.runRequest + "\n");
		}
	};
}
