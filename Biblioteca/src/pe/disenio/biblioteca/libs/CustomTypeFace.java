package pe.disenio.biblioteca.libs;

import android.content.Context;
import android.graphics.Typeface;

public class CustomTypeFace {
	private Context context;
	private static CustomTypeFace instance;

	public CustomTypeFace(Context context) {
		this.context = context;
	}

	public static CustomTypeFace getInstance(Context context) {
		synchronized (CustomTypeFace.class) {
			if (instance == null)
				instance = new CustomTypeFace(context);
			return instance;
		}
	}

	public Typeface getTypeFace() {
		return Typeface.createFromAsset(context.getResources().getAssets(),
				"fuentes/SEGOEUI.ttf");
	}
}
