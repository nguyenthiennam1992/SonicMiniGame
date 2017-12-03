package a4;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

public class Sound {
private AudioClip myClip ;
private String slash =File.separator;
	private  String userDir = System.getProperty("user.dir");

	public Sound(String fileName) {
		//System.out.println(userDir);
		String soundDir =userDir+slash+"Sounds"+slash +  fileName;
        System.out.println(soundDir);
		try {
			File file = new File(soundDir);
			if (file.exists()) {
			myClip = Applet.newAudioClip(file.toURI().toURL());
			} else {
			throw new RuntimeException("Sound: file not found: " + fileName);
			}
			} catch (MalformedURLException e) {
			throw new RuntimeException("Sound: malformed URL: " + e);
			
			}
		
	
	}
			

	public void play() 
	{
		myClip.play();
	}
	public void stop()
	{
		myClip.stop();
	}
	public void loop()
	{
		myClip.loop();
	}
}