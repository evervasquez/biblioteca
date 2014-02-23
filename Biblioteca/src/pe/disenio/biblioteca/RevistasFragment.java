package pe.disenio.biblioteca;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import pe.disenio.biblioteca.model.Revistas;
import pe.disenio.biblioteca.utils.ConnectUtils;
import pe.disenio.biblioteca.utils.ConstantsUtils;
import pe.disenio.biblioteca.utils.ProgressFragment;
import pe.disenio.biblioteca.utils.dialogos;
import pe.disenio.biblioteca.utils.verifica_internet;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.dina.ui.widget.UITableView;
import br.com.dina.ui.widget.UITableView.ClickListener;

public class RevistasFragment extends ProgressFragment {

	private static final String TAG = "RevistasFragment";
	private View mContentView;
	public ArrayList<Revistas> datos_revistas;
	public String cadena_buscada, tipo_libro, url;
	ConnectUtils objetoBD;
	private String content;
	Intent intent;

	ProgressDialog pd;
	String datos;
	private static final String METHOD_NAME = "buscarLibrosAndroid";
	private asyncBusqueda tarea;

	public static RevistasFragment newInstance() {
		RevistasFragment fragment = new RevistasFragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Bundle args = getArguments();
		if (args != null) {
			cadena_buscada = args.getString("cadena_buscada");
			tipo_libro = args.getString("tipo_libro");
			url = args.getString("url");

		}
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContentView = inflater.inflate(R.layout.lista_busqueda, null);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setContentView(mContentView);
		Log.v("URL", url);
		datos_revistas = new ArrayList<Revistas>();
		obtainData();

	}

	@SuppressWarnings("unchecked")
	private void obtainData() {
		if (verifica_internet.checkConex(getSherlockActivity())) {
			tarea = new asyncBusqueda();
			tarea.execute();
		} else {

			dialogos.Dialogo_Alerta(getSherlockActivity(),
					"nesecita estar conectado a internet");
			return;
		}
	}

	@SuppressWarnings("rawtypes")
	private class asyncBusqueda extends AsyncTask {
		private Revistas datos_t;
		UITableView tableView;

		protected void onPreExecute() {
			setContentShown(false);
			try {
				String param = URLEncoder.encode("cadena_buscada", "UTF-8")
						+ "=" + URLEncoder.encode(cadena_buscada, "UTF-8");
				param += "&" + URLEncoder.encode("tipo_libro", "UTF-8") + "="
						+ URLEncoder.encode(tipo_libro, "UTF-8");
				datos = param;

				Log.v(TAG, datos);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		@Override
		protected Object doInBackground(Object... params) {
			objetoBD = new ConnectUtils(getSherlockActivity()
					.getApplicationContext(), url + ""
					+ ConstantsUtils.CONTROLLER + METHOD_NAME, datos);
			content = objetoBD.getResponse();
			return 1;
		}

		protected void onPostExecute(Object res) {
			setContentShown(true);
			tableView = (UITableView) mContentView.findViewById(R.id.tableView);
			CustomClickListener listener = new CustomClickListener();
			tableView.setClickListener(listener);
			try {

				JSONObject jsonResponse;

				setContentShown(true);
				jsonResponse = new JSONObject(content);
				Log.e(TAG, content);
				// recuperamos el array
				JSONArray jsonMainNode = jsonResponse.optJSONArray("Android");

				int lengthJsonArr = jsonMainNode.length();

				if (lengthJsonArr > 0) {
					
					for (int i = 0; i < lengthJsonArr; i++) {
						JSONObject jsonChildNode = jsonMainNode
								.getJSONObject(i);
						datos_t = new Revistas();
						
						String idrevista = jsonChildNode.getString("Revista_ID").toString();
						String idarticulo = jsonChildNode.getString("Articulo_ID").toString();
						String titulo = jsonChildNode.getString("Titulo").toString();
						String idcategoria = jsonChildNode.getString("Categoria_ID").toString();
						String tituloabrev =  jsonChildNode.getString("Titulo_abreviado").toString();
						String areatematica = jsonChildNode.getString("Area_tematica").toString();
						String editor =  jsonChildNode.getString("Editor").toString();
						String ciudad = jsonChildNode.getString("Ciudad").toString();
						String email = jsonChildNode.getString("Direccion_web").toString();
						String lugarEdicion = jsonChildNode.getString("Lugar_edicion").toString();
						
						tableView.addBasicItem("Titulo : "
								+ titulo,
								"Área Temática : "
										+ areatematica);
						
						datos_t.setIdRevista(idrevista);
						datos_t.setIdArticulo(idarticulo);
						datos_t.setTitulo(titulo);
						datos_t.setIdCategoria(idcategoria);
						datos_t.setTituloabrev(tituloabrev);
						datos_t.setArea_tematica(areatematica);
						datos_t.setEditor(editor);
						datos_t.setCiudad(ciudad);
						datos_t.setEmail(email);
						datos_t.setLugarEdicion(lugarEdicion);
						
						datos_revistas.add(datos_t);
					}
				}

				tableView.commit();

			} catch (Exception e) {
				/*
				 * Toast.makeText(getSherlockActivity(),
				 * "Hubo un error al descargar datos", Toast.LENGTH_SHORT)
				 * .show();
				 */

			}
		}

		private class CustomClickListener implements ClickListener {
			@Override
			public void onClick(int index) {
				
				 intent = new Intent(getSherlockActivity(),
						 
				 ActivityProgress_detalle.class); 
				 
				 String[] data2 = new String[] { 
						 datos_revistas.get(index).getIdRevista(),//0
						 datos_revistas.get(index).getIdArticulo(),//1
						 datos_revistas.get(index).getTitulo(),//2
						 datos_revistas.get(index).getIdCategoria(),//3
						 datos_revistas.get(index).getTituloabrev(),//4
						 datos_revistas.get(index).getArea_tematica(),//5
						 datos_revistas.get(index).getEditor(),//6
						 datos_revistas.get(index).getCiudad(),//7
						 datos_revistas.get(index).getEmail(),//8
						 datos_revistas.get(index).getLugarEdicion(),//9
						 };
				 
				 intent.putExtra("url", url);
				 intent.putExtra("tipo_libro", tipo_libro);
				 intent.putExtra("detalle_libro", data2);
				 startActivity(intent);
				 
			}

		}
	}
}