package Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;

public class SocketLib {
	public static void  runSocket() {
		try {
            @SuppressWarnings("resource")
			final ServerSocket serverSocket = new ServerSocket(13000);
            new Thread("Device Listener") {
                public void run() {
                    try {
                        System.out.println("Listener Running . . .");
                        java.net.Socket socket = null;
                        while ((socket = serverSocket.accept()) != null) {
                            System.out.println("| Incoming : "+ socket.toString());
                            while(true) {
                            BufferedReader in = new BufferedReader (new InputStreamReader (socket.getInputStream ()));
                            try {
                            	float vol = Float.parseFloat(in.readLine());
                            	System.out.println(vol);
                    			Audio.setMasterOutputVolume(vol);
                    		} catch (NumberFormatException e) {
                    			// TODO Auto-generated catch block
                    			e.printStackTrace();
                    		} catch (IOException e) {
                    			// TODO Auto-generated catch block
                    			e.printStackTrace();
                    		}
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                };
            }.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
}
