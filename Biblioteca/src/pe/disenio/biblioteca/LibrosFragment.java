package pe.disenio.biblioteca;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import pe.disenio.biblioteca.model.Libros;
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

public class LibrosFragment extends ProgressFragment {

	private static final String TAG = "ListaFragment";
	private View mContentView;
	public ArrayList<Libros> datos_libros;
	public String cadena_buscada, tipo_libro, url;
	ConnectUtils objetoBD;
	private String content;
	Intent intent;

	ProgressDialog pd;
	String datos;
	private static final String METHOD_NAME = "buscarLibrosAndroid";
	private asyncBusqueda tarea;

	public static LibrosFragment newInstance() {
		LibrosFragment fragment = new LibrosFragment();
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
		datos_libros = new ArrayList<Libros>();
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
		private Libros datos_t;
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

				// recuperamos el array
				JSONArray jsonMainNode = jsonResponse.optJSONArray("Android");

				int lengthJsonArr = jsonMainNode.length();

				if (lengthJsonArr > 0) {
					
					for (int i = 0; i < lengthJsonArr; i++) {
						JSONObject jsonChildNode = jsonMainNode
								.getJSONObject(i);
						datos_t = new Libros();
						
						String libroID = jsonChildNode.getString("Libro_ID").toString();
						String tipolibroID = jsonChildNode.getString("Tipo_Libro_ID").toString();
						String resumen = jsonChildNode.getString("Resumen").toString();
						String titulo = jsonChildNode.getString("Titulo").toString();
						String editorial =  jsonChildNode.getString("Editorial").toString();
						String autor = jsonChildNode.getString("Autor").toString();
						String pais =  jsonChildNode.getString("Pais").toString();
						String ciudad = jsonChildNode.getString("Ciudad").toString();
						
						tableView.addBasicItem("Titulo : "
								+ titulo,
								"Autor : "
										+ autor);
						
						datos_t.setIdlibro(libroID);
						datos_t.setIdtipolibro(tipolibroID);
						datos_t.setResumen(resumen);
						datos_t.setAutor(autor);
						datos_t.setEditorial(editorial);
						datos_t.setTitulo(titulo);
						datos_t.setPais(pais);
						datos_t.setCiudad(ciudad);
						
						datos_libros.add(datos_t);
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
						 datos_libros.get(index).getIdlibro(),//0
						 datos_libros.get(index).getIdtipolibro(),//1
						 datos_libros.get(index).getResumen(),//2
						 datos_libros.get(index).getTitulo(),//3
						 datos_libros.get(index).getEditorial(),//4
						 datos_libros.get(index).getPais(),//5
						 datos_libros.get(index).getCiudad(),//6
						 datos_libros.get(index).getAutor()//7
						 };
				 
				 intent.putExtra("url", url);
				 intent.putExtra("tipo_libro", tipo_libro);
				 intent.putExtra("detalle_libro", data2);
				 startActivity(intent);
				 
			}

		}
	}
}