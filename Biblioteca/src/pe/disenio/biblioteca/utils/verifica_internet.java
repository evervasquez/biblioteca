package pe.disenio.biblioteca.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
public class verifica_internet {
	
	public static boolean checkConex(Context contexto) {
		boolean hasConnectedWifi = false;
		boolean hasConnectedMobile = false;

		ConnectivityManager cm = (ConnectivityManager) contexto
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] netInfo = cm.getAllNetworkInfo();
		for (NetworkInfo ni : netInfo) {
			if (ni.getTypeName().equalsIgnoreCase("wifi"))
				if (ni.isConnected())
					hasConnectedWifi = true;
			if (ni.getTypeName().equalsIgnoreCase("mobile"))
				if (ni.isConnected())
					hasConnectedMobile = true;
			if (ni.getState() == NetworkInfo.State.CONNECTED) 
				return true;
		} 
		return hasConnectedWifi || hasConnectedMobile;
		//return true;
	}
	
}

