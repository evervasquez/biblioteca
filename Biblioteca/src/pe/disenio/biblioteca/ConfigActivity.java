package pe.disenio.biblioteca;

import pe.disenio.biblioteca.libs.CustomTypeFace;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.TextView;


public class ConfigActivity extends PreferenceActivity{
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.config);
		int titleId = Resources.getSystem().getIdentifier("action_bar_title",
				"id", "android");
		TextView custom = (TextView) findViewById(titleId);
		custom.setTextAppearance(getApplicationContext(), R.style.CustomText);
		getActionBar().setTitle(getResources().getString(R.string.title_config));
		custom.setTypeface(CustomTypeFace.getInstance(this).getTypeFace());
	}
	
}
