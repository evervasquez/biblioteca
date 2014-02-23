/*
 * Copyright (C) 2013 Evgeny Shishkin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
public class ProgressActivity extends SherlockFragmentActivity {
	private String cadena_buscada, tipolibro, url;
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 Bundle bundle = getIntent().getExtras();
		 cadena_buscada = bundle.getString("cadena_buscada");
		 tipolibro = bundle.getString("tipo_libro");
		 url = bundle.getString("url");
		 
		 	getSupportActionBar().setTitle("resultado de busqueda");
		 	
			int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
			if(titleId == 0)
			    titleId = com.actionbarsherlock.R.id.abs__action_bar_title;
			
			TextView custom = (TextView) findViewById(titleId);
			custom.setTypeface(CustomTypeFace.getInstance(this).getTypeFace());
		
		
	     //verifica si tienes conección a internet
		if (verifica_internet.checkConex(getApplicationContext())) {
			Fragment fragment = getSupportFragmentManager().findFragmentById(
					android.R.id.content);
			if (fragment == null) {
				
				switch (Integer.parseInt(tipolibro)) {
				case 0:
					fragment = LibrosFragment.newInstance();
					break;
				case 1:
					fragment = RevistasFragment.newInstance();
					break;
				case 2:
					fragment = LaminasFragment.newInstance();
					break;
				case 3:
					fragment = MonografiasFragment.newInstance();
					break;
					
				default:
					break;
				}

				Bundle args = new Bundle();
				args.putString("cadena_buscada", cadena_buscada);
				args.putString("tipo_libro", tipolibro);
				args.putString("url", url);
				
				fragment.setArguments(args);
				getSupportFragmentManager().beginTransaction()
						.add(android.R.id.content, fragment).commit();
			}
			//getSupportActionBar().setTitle("Lista de Tr�mites");
			
		}else{
			Toast.makeText(getApplicationContext(), "Verifique estar conectado a INTERNET", Toast.LENGTH_LONG).show();
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

	/**
	 * Helper for fix issue VerifyError on Android 1.6. On Android 1.6 virtual
	 * machine tries to resolve (verify) getActionBar function, and since there
	 * is no such function, Dalvik throws VerifyError.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private class ActionBarHelper {

		/**
		 * Set whether home should be displayed as an "up" affordance. Set this
		 * to true if selecting "home" returns up by a single level in your UI
		 * rather than back to the top level or front page.
		 * 
		 * @param showHomeAsUp
		 *            true to show the user that selecting home will return one
		 *            level up rather than to the top level of the app.
		 */

	}

}
