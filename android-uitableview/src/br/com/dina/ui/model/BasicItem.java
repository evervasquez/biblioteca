package br.com.dina.ui.model;

public class BasicItem implements IListItem {
	
	private boolean mClickable = true;
	private int mDrawable = -1;
	private String mTitle;
	private String mSubtitle1;
	private String mSubtitle2;
	private String mSubtitle3;
	private String mSubtitle4;
	private int mColor = -1;
	

	public BasicItem(String _title) {
		this.mTitle = _title;
	}
	
	public BasicItem(String _title, String _subtitle) {
		this.mTitle = _title;
		this.mSubtitle1 = _subtitle;
	}
	
	public BasicItem(String _title, String _subtitle, int _color) {
		this.mTitle = _title;
		this.mSubtitle1 = _subtitle;
		this.mColor = _color;
	}
	
	public BasicItem(String _title, String _subtitle, boolean _clickable) {
		this.mTitle = _title;
		this.mSubtitle1 = _subtitle;
		this.mClickable = _clickable;
	}	
	
	public BasicItem(int _drawable, String _title, String _subtitle) {
		this.mDrawable = _drawable;
		this.mTitle = _title;
		this.mSubtitle1 = _subtitle;
	}
	
	public BasicItem(int _drawable, String _title, String _subtitle, int _color) {
		this.mDrawable = _drawable;
		this.mTitle = _title;
		this.mSubtitle1 = _subtitle;
		this.mColor = _color;
	}

	public int getDrawable() {
		return mDrawable;
	}

	public void setDrawable(int drawable) {
		this.mDrawable = drawable;
	}
	

	/**************************TITLE****************************/
	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		this.mTitle = title;
	}
	
	/*****************************METODO QUE USARE*******************************/
	
	//CON IMAGEN
	public BasicItem(int _drawable, String _title, String _subtitle1, String _subtitle2,String _subtitle3,String _subtitle4){
		this.mDrawable = _drawable;
		this.mTitle = _title;
		this.mSubtitle1 = _subtitle1;
		this.mSubtitle2 = _subtitle2;
		this.mSubtitle3 = _subtitle3;
		this.mSubtitle4 = _subtitle4;
	}
	
	public BasicItem(String _title, String _subtitle1, String _subtitle2,String _subtitle3){
		this.mTitle = _title;
		this.mSubtitle1 = _subtitle1;
		this.mSubtitle2 = _subtitle2;
		this.mSubtitle3 = _subtitle3;
	}
	
	//SIN IMAGEN
	public BasicItem(String _title, String _subtitle1, String _subtitle2,String _subtitle3,String _subtitle4){
		this.mTitle = _title;
		this.mSubtitle1 = _subtitle1;
		this.mSubtitle2 = _subtitle2;
		this.mSubtitle3 = _subtitle3;
		this.mSubtitle4 = _subtitle4;
	}
	//CON IMAGEN Y COLOR
	public BasicItem(int _drawable,String _title, String _subtitle1, String _subtitle2,String _subtitle3,String _subtitle4, int _color){
		this.mTitle = _title;
		this.mDrawable = _drawable;
		this.mSubtitle1 = _subtitle1;
		this.mSubtitle2 = _subtitle2;
		this.mSubtitle3 = _subtitle3;
		this.mSubtitle4 = _subtitle4;
		this.mColor = _color;
	}
	//SIN IMAGEN Y COLOR
	public BasicItem(String _title, String _subtitle1, String _subtitle2,String _subtitle3,String _subtitle4, int _color){
		this.mTitle = _title;
		this.mSubtitle1 = _subtitle1;
		this.mSubtitle2 = _subtitle2;
		this.mSubtitle3 = _subtitle3;
		this.mSubtitle4 = _subtitle4;
		this.mColor = _color;
	}
	
	/********************************END METODO***********************************/


	/****************EDITADO POR eveR********************/
	public String getSubtitle1() {
		return mSubtitle1;
	}

	public void setSubtitle1(String summary) {
		this.mSubtitle1 = summary;
	}
	
	public String getSubtitle2() {
		return mSubtitle2;
	}

	public void setSubtitle2(String summary) {
		this.mSubtitle2 = summary;
	}

	public String getSubtitle3() {
		return mSubtitle3;
	}

	public void setSubtitle3(String summary) {
		this.mSubtitle3 = summary;
	}

	public String getSubtitle4() {
		return mSubtitle4;
	}

	public void setSubtitle4(String summary) {
		this.mSubtitle4 = summary;
	}
	/*******************END EDITADO*******************/
	public int getColor() {
		return mColor;
	}

	public void setColor(int mColor) {
		this.mColor = mColor;
	}

	@Override
	public boolean isClickable() {
		return mClickable;
	}

	@Override
	public void setClickable(boolean clickable) {
		mClickable = clickable;			
	}
	
}
