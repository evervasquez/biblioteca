package pe.disenio.biblioteca;

import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

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
import br.com.dina.ui.model.BasicItem;
import br.com.dina.ui.widget.UIButton;
import br.com.dina.ui.widget.UITableView;

public class DetalleMonografiaFragment extends ProgressFragment {
	View mContentView;
	ConnectUtils objetoBD;
	Intent intent;
	ProgressDialog pd;
	public static final String TAG = "DetalleFragment";
	String categoria, esta, datos;
	dialogos dialog;
	public String content, url,tipo_libro;
	String METHOD_NAME = "detalleBusqueda";
	private String[] detalle_monografia;

	public static DetalleMonografiaFragment newInstance() {
		DetalleMonografiaFragment fragment = new DetalleMonografiaFragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Bundle args = getArguments();
		if (args != null) {
			detalle_monografia = args.getStringArray("detalle_libro");
			url = args.getString("url");
			tipo_libro = args.getString("tipo_libro");
		}
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContentView = inflater.inflate(R.layout.activity_detalle, null);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setContentView(mContentView);

		UIButton.setTitle(detalle_monografia[3]);
		UIButton.setSubTitle1("Curso : " + detalle_monografia[1]);
		UIButton.setSubTitle2("");
		UIButton.setSubTitle3("");
		UIButton.setSubTitle4("");
		
		obtainData();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@SuppressWarnings("unchecked")
	private void obtainData() {
		if (verifica_internet.checkConex(getSherlockActivity())) {
			asyncTramites tarea = new asyncTramites();
			tarea.execute();
		} else {
			dialogos.Dialogo_Alerta(getSherlockActivity(),
					"nesecita estar conectado a internet");
			return;
		}
	}

	@SuppressWarnings("rawtypes")
	private class asyncTramites extends AsyncTask {
		UITableView tableView;
		
		@Override
		protected void onPreExecute() {
			try {
				setContentShown(false);
				String param = URLEncoder.encode("Libro_ID", "UTF-8") + "="
						+ URLEncoder.encode(detalle_monografia[0], "UTF-8");
				param += "&" + URLEncoder.encode("tipo_libro", "UTF-8") + "="
						+ URLEncoder.encode(tipo_libro, "UTF-8");
				datos = param;

				Log.v(TAG, datos);
			} catch (Exception e) {
				getSherlockActivity().finish();
			}
		}
		
		@Override
		protected Object doInBackground(Object... params) {
			// TODO Auto-generated method stub
			objetoBD = new ConnectUtils(getSherlockActivity().getApplicationContext(),
					url + ConstantsUtils.CONTROLLER + METHOD_NAME, datos);

			// comprobamos si tenemos conexion a internet
			content = objetoBD.getResponse();
			return 1;
		}

		

		protected void onPostExecute(Object res) {
			try {
				Log.v(TAG, content);
				tableView = (UITableView) getView()
						.findViewById(R.id.tableView);
				setContentShown(true);

				JSONObject jsonResponse;
				jsonResponse = new JSONObject(content);
				JSONArray jsonMainNode = jsonResponse.optJSONArray("Android");
				
				int lengthJsonArr = jsonMainNode.length();
				if (lengthJsonArr > 0) {
					
					for (int i = 0; i < lengthJsonArr; i++) {
						JSONObject jsonChildNode = jsonMainNode
								.getJSONObject(i);

						String cantidad = jsonChildNode.getString("cantidad").toString();
						String prestados = jsonChildNode.getString("prestados").toString();
						
						tableView.addBasicItem(new BasicItem("Asesor", detalle_monografia[2],false));
						tableView.addBasicItem(new BasicItem("Total de Ejemplares", cantidad,false));
						tableView.addBasicItem(new BasicItem("Total, Disponibles", (Integer.parseInt(cantidad) - Integer.parseInt(prestados))+"",false));
					}
				
				}else{
					tableView.addBasicItem(new BasicItem("Observación", "No existe ningún ejemplar",false));
				}
				tableView.commit();

			} catch (Exception e) {
				// Toast.makeText(getSherlockActivity(),
				// "La acci�n ha sido cancelada", Toast.LENGTH_SHORT).show();
			}

		}

	}

}