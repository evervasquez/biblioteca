package br.com.dina.ui.widget;

import br.com.dina.ui.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UIButton extends LinearLayout {

	private LayoutInflater mInflater;
	private static LinearLayout mButtonContainer;
	private ClickListener mClickListener;
	private CharSequence mTitle;
	private CharSequence mSubtitle;
	private int mImage;

	public UIButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setClickable(true);
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mButtonContainer = (LinearLayout) mInflater.inflate(
				R.layout.list_button, null);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.FILL_PARENT);

		/*
		 * TypedArray a = context.obtainStyledAttributes(attrs,
		 * R.styleable.UIButton, 0, 0); mTitle =
		 * a.getString(R.styleable.UIButton_title); mSubtitle =
		 * a.getString(R.styleable.UIButton_subtitle); mImage =
		 * a.getResourceId(R.styleable.UIButton_image, -1);
		 * 
		 * if(mTitle != null) { ((TextView)
		 * mButtonContainer.findViewById(R.id.title
		 * )).setText(mTitle.toString()); } else { ((TextView)
		 * mButtonContainer.findViewById(R.id.title)).setText("subtitle"); }
		 * 
		 * if(mSubtitle != null) { ((TextView)
		 * mButtonContainer.findViewById(R.id
		 * .subtitle)).setText(mSubtitle.toString()); } else { ((TextView)
		 * mButtonContainer
		 * .findViewById(R.id.subtitle)).setVisibility(View.GONE); }
		 * 
		 * if(mImage > -1) { ((ImageView)
		 * mButtonContainer.findViewById(R.id.image)).setImageResource(mImage);
		 * }
		 */
		mButtonContainer.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				if (mClickListener != null)
					mClickListener.onClick(UIButton.this);
			}

		});

		addView(mButtonContainer, params);
	}

	// como no pude enviar por xml, agrege estos tres metodos que alimentan
	// directamente

	public static void setTitle(String title) {
		((TextView) mButtonContainer.findViewById(R.id.titleui)).setText(title);
	}

	public static void setSubTitle1(String subtitle1) {
		((TextView) mButtonContainer.findViewById(R.id.subtitle1))
				.setText(subtitle1);
	}

	public static void setSubTitle2(String subtitle2) {
		((TextView) mButtonContainer.findViewById(R.id.subtitle2)).setText(subtitle2);
	}

	public static void setSubTitle3(String subtitle3) {
		((TextView) mButtonContainer.findViewById(R.id.subtitle3))
				.setText(subtitle3);
	}
	
	public static void setSubTitle4(String subtitle4) {
		((TextView) mButtonContainer.findViewById(R.id.subtitle4))
				.setText(subtitle4);
	}
	
	public static void setImage(int image) {
		if (image > -1) {
			((ImageView) mButtonContainer.findViewById(R.id.image))
					.setImageResource(image);
		}
	}

	public interface ClickListener {
		void onClick(View view);
	}

	/**
	 * 
	 * @param listener
	 */
	public void addClickListener(ClickListener listener) {
		this.mClickListener = listener;
	}

	/**
	 * 
	 */
	public void removeClickListener() {
		this.mClickListener = null;
	}

}
