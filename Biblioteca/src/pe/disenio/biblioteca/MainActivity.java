package pe.disenio.biblioteca;

import pe.disenio.biblioteca.libs.CustomTypeFace;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class MainActivity extends SherlockActivity {
	private static final int RESULT_SETTING = 1;
	private EditText txt_buscar;

	private Spinner tipolibros;
	private ArrayAdapter<?> spinner_adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		int titleId = Resources.getSystem().getIdentifier("action_bar_title",
				"id", "android");
		TextView custom = (TextView) findViewById(titleId);
		custom.setTextAppearance(getApplicationContext(), R.style.CustomText);
		custom.setTypeface(CustomTypeFace.getInstance(this).getTypeFace());

		// inicializamos los elementos
		tipolibros = (Spinner) findViewById(R.id.sp_tipos);
		txt_buscar = (EditText) findViewById(R.id.txt_busqueda);

		spinner_adapter = ArrayAdapter.createFromResource(this,
				R.array.array_busqueda, android.R.layout.simple_spinner_item);
		spinner_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		tipolibros.setAdapter(spinner_adapter);

	}

	public void iniciar(View view) {
		String cadena_buscada = txt_buscar.getText().toString().trim();
		String tipoLibro = tipolibros.getSelectedItemPosition() + "";
		if (cadena_buscada.length() > 0) {
			Intent intent = new Intent(MainActivity.this,
					ProgressActivity.class);
			intent.putExtra("cadena_buscada", cadena_buscada);
			intent.putExtra("tipo_libro", tipoLibro);
			intent.putExtra("url", getUrl());
			startActivity(intent);
		} else {
			Toast.makeText(getApplicationContext(),
					"Ingrese correctamente el nombre del libro",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getSupportMenuInflater();
	    inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
			startActivityForResult(intent, RESULT_SETTING);
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	// metodo para recuperar la url de preferences
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case RESULT_SETTING:
			showSettings();
			break;

		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void showSettings() {
		SharedPreferences mSharePreferences = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		String url = mSharePreferences.getString("prefUrl", "NULL");
		String web = mSharePreferences.getString("prefWeb", "NULL");

		mSharePreferences = getSharedPreferences("AgrovetPreferences",
				MODE_PRIVATE);

		// guardamos las preferencias en un xml
		SharedPreferences.Editor editor = mSharePreferences.edit();
		editor.putString("url", url);
		editor.putString("web", web);
		editor.commit();
	}
	
	public String getUrl() {
		SharedPreferences pref = getSharedPreferences("AgrovetPreferences",
				MODE_PRIVATE);
		return pref.getString("url", "nada");
	}
	
}
