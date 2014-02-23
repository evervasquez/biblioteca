package pe.disenio.biblioteca;

import pe.disenio.biblioteca.libs.CustomTypeFace;
import pe.disenio.biblioteca.utils.verifica_internet;
import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

/**
 * @author eveR
 */

public class ActivityProgress_detalle extends SherlockFragmentActivity {

	private String[] detalle_libro;
	private String url, tipo_libro;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Detalle del libro");

		int titleId = Resources.getSystem().getIdentifier("action_bar_title",
				"id", "android");
		if (titleId == 0)
			titleId = com.actionbarsherlock.R.id.abs__action_bar_title;

		TextView custom = (TextView) findViewById(titleId);
		custom.setTypeface(CustomTypeFace.getInstance(this).getTypeFace());

		Bundle bundle = getIntent().getExtras();
		detalle_libro = bundle.getStringArray("detalle_libro");
		url = bundle.getString("url");
		tipo_libro = bundle.getString("tipo_libro");

		// verifica si tienes conección a internet
		if (verifica_internet.checkConex(getApplicationContext())) {
			Fragment fragment = getSupportFragmentManager().findFragmentById(
					android.R.id.content);
			
			if (fragment == null) {

				switch (Integer.parseInt(tipo_libro)) {
				case 0:
					fragment = DetalleLibroFragment.newInstance();
					break;
				case 1:
					fragment = DetalleRevistaFragment.newInstance();
					break;
				case 2:
					fragment = DetalleLaminaFragment.newInstance();
					break;
				case 3:
					fragment = DetalleMonografiaFragment.newInstance();
					break;
				default:
					break;
				}

				Bundle args = new Bundle();
				args.putStringArray("detalle_libro", detalle_libro);
				args.putString("url", url);
				args.putString("tipo_libro", tipo_libro);
				
				fragment.setArguments(args);
				getSupportFragmentManager().beginTransaction()
						.add(android.R.id.content, fragment).commit();
			}

		} else {
			Toast.makeText(getApplicationContext(),
					"Verifique estar conectado a INTERNET", Toast.LENGTH_LONG)
					.show();
			finish();
		}

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	// implementar fin de la actividad de la imagen
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private class ActionBarHelper {
	}

}
