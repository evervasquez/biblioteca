package pe.disenio.biblioteca;


import pe.disenio.biblioteca.libs.CustomTypeFace;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	private static final int RESULT_SETTING = 1;
	private static final String metodo = "selectLibros";
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
		
		//inicializamos los elementos
		tipolibros = (Spinner) findViewById(R.id.sp_tipos);
		txt_buscar = (EditText)  findViewById(R.id.txt_busqueda);
			
		spinner_adapter = ArrayAdapter.createFromResource( this, R.array.array_busqueda , android.R.layout.simple_spinner_item);
		spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		tipolibros.setAdapter(spinner_adapter);
		
	}
	
	public void iniciar(View view){
		String cadena_buscada = txt_buscar.getText().toString().trim();
		if(cadena_buscada.length() > 0){
			//Intent intent = new Intent(MainActivity.this,
			//		ListFragmentActivity.class);
			//intent.putExtra("cadena_buscada", cadena_buscada);
			//startActivity(intent);
		}else{
			Toast.makeText(getApplicationContext(), "Ingrese correctamente el nombre del libro", Toast.LENGTH_SHORT).show();
		}
		Log.v(TAG, txt_buscar.getText().toString().trim()+"-"+tipolibros.getSelectedItemPosition());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	private String getUrl() {
		SharedPreferences pref = getSharedPreferences("AgrovetPreferences",
				MODE_PRIVATE);
		return pref.getString("url", "nada");
	}
}
