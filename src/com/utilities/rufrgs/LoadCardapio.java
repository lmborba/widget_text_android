package com.utilities.rufrgs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.util.Log;

public class LoadCardapio {
	
	String filed = "cardapio.json";
	String total[] = {"Não foi possível exibir o cardápio","Não foi possível exibir o cardápio","Não foi possível exibir o cardápio","Não foi possível exibir o cardápio","Não foi possível exibir o cardápio"};
	Context ct;
	
	LoadCardapio (Context ctx) {
		ct = ctx;
		if (!load_and_save_data()) {
			load_from_file();
		}
	}
	
	private boolean json_read(String data) {
		try {
			JSONArray json = new JSONArray(data);
			if (json.length() < 1) {
				return false;
			}
			JSONObject jsonObject = json.getJSONObject(0);
			for (int i = 0; i < 5; i++) {
				StringBuilder sb = new StringBuilder();
				sb.append(i);
				if (jsonObject.has(sb.toString())) {
					total[i] = jsonObject.getString(sb.toString());
				}
			}
			return true;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	private void load_from_file() {
		
		StringBuilder data = new StringBuilder();
		if (read_json_file(data)) {
			
				json_read(data.toString());
			
		}
		
	}

	private boolean read_json_file(StringBuilder response_s) {
		// TODO Auto-generated method stub		
		FileInputStream stream = null;
		
		try {
			
			//File path = new File(Environment.getExternalStorageDirectory(),"json");
			//File toLoad = new File(path, "cardapio.json");
		
			stream = ct.openFileInput(filed);
			ObjectInputStream ois = new ObjectInputStream(stream);
			
			response_s.append((String) ois.readObject());
 
			stream.close();
		
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (stream != null) {
					stream.close();
				}
			} catch (final IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	private boolean load_and_save_data() {
				
		StringBuilder builder = new StringBuilder();
	    HttpClient client = new DefaultHttpClient();
	    HttpGet httpGet = new HttpGet("http://rufrgs.heroku.com/cardapio/all");
	    try {
	      HttpResponse response = client.execute(httpGet);
	      StatusLine statusLine = response.getStatusLine();
	      int statusCode = statusLine.getStatusCode();
	      if (statusCode == 200) {
	        HttpEntity entity = response.getEntity();
	        InputStream content = entity.getContent();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
	        String line;
	        while ((line = reader.readLine()) != null) {
	          builder.append(line);
	        }
	        boolean v = json_read(builder.toString());
	        if (v) {
	        	save_json_to_file(builder.toString());
	        }
	        return v;
	      } else {
	        Log.e(LoadCardapio.class.toString(), "Failed to download file");
	        return false;
	      }
	    } catch (ClientProtocolException e) {
	      e.printStackTrace();
	      return false;
	    } catch (IOException e) {
	      e.printStackTrace();
	      return false;
	    }
	}

	private void save_json_to_file(String content) {
		// TODO Auto-generated method stub
		
		FileOutputStream stream = null;
			
		try {
			stream = ct.openFileOutput(filed,Context.MODE_PRIVATE);
			if (stream == null) {
				return;
			}
			ObjectOutputStream oos = new ObjectOutputStream(stream);
			oos.writeObject(content);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			try {
				if (stream != null) {
					stream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	//String days[] = {"Seg","Ter","Qua","Qui","Sex"};
	public String getDay(int position) {
		return total[position];
	}

}
