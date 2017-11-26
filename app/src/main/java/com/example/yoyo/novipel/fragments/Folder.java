package com.example.yoyo.novipel.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Movie;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
//import android.widget.ProgressBar;
import android.widget.ProgressBar;
import android.widget.Toast;
//import com.github.silvestrpredko.dotprogressbar.DotProgressBar;

import com.example.yoyo.novipel.R;
import com.example.yoyo.novipel.Website;
import com.example.yoyo.novipel.utility.Data;
import com.example.yoyo.novipel.utility.DataAdapter;
import com.example.yoyo.novipel.utility.DividerItemDecoration;
import com.example.yoyo.novipel.utility.HTTPDataHandler;
import com.example.yoyo.novipel.utility.RecyclerTouchListener;
import com.example.yoyo.novipel.utility.Web;
import com.github.silvestrpredko.dotprogressbar.DotProgressBarBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.github.silvestrpredko.dotprogressbar.DotProgressBar;
import com.github.silvestrpredko.dotprogressbar.DotProgressBarBuilder;
import java.util.concurrent.Future;

/**
 * Created by yoyo on 7/5/2017.
 */

public class Folder extends Fragment {

        //Progress Bar
         private DotProgressBar dotProgressBar;
    private ProgressBar pbDefaultM;
    private com.github.silvestrpredko.dotprogressbar.DotProgressBarBuilder  progress;
    private RecyclerView recyclerView;
    private List<Data> dataList = new ArrayList<>();
    private ArrayList<String> stringUrls = new ArrayList<>();
    private DataAdapter dataAdapter;
    private Typeface typefaceMed;
    private Typeface typefaceThin;
    private static String urlString;
    AssetManager am ;
    private static final ExecutorService threadpool = Executors.newFixedThreadPool(1);

    private boolean isNetworkAvailable()
    {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        

        
        am = getActivity().getAssets();
        typefaceMed = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Roboto-Medium.ttf"));

        typefaceThin = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Roboto-Thin.ttf"));

        View v = inflater.inflate(R.layout.folder, container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
        //int height = v.getMeasuredHeight();
        //Log.d("Folder"," the height is "+height);
      //  dataAdapter = new DataAdapter(dataList,typefaceThin,typefaceMed);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
//        recyclerView.setAdapter(dataAdapter);

        //the prgress bar
        //dotProgressBar = (DotProgressBar) v.findViewById(R.id.pbDefault);

        //url where the node app is
        urlString = "http://46.101.251.32:4000/stories";


        try {

            if(isNetworkAvailable())
            {
               prepareData();
            }else
            {
                Toast.makeText(getActivity(), "Connect your device to internet!!!", Toast.LENGTH_SHORT).show();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position)
            {
                   Log.d( "POSITIONClick" , position + " " );
                   String uri =  dataList.get(position).getLinkStory();
                   Intent i = new Intent(getActivity(),Web.class);
                   i.putExtra("website",uri);
                   startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position)
            {

            }
    }));

        recyclerView.scrollToPosition(0);

        return v;
    }



    private void prepareData() throws ExecutionException, InterruptedException, JSONException
    {
        new ProcessJSON().execute(urlString);
       //  new ProcessJSON(dotProgressBar).execute(urlString);
    }


    public static Folder newInstance()
    {
        Folder fragment = new Folder();
        return fragment;
    }

    // THE PRIVATE CLASS ACCESS
    public class ProcessJSON extends AsyncTask<String, Void, String> {

        //private DotProgressBar pbM;
        public ProcessJSON()
        {

        }
        protected String doInBackground(String... strings)
        {
            String stream = null;
            String urlString = strings[0];
            HTTPDataHandler hh = new HTTPDataHandler();
            stream = hh.GetHTTPData(urlString);
            // Log.d("THE JSON IS ", stream);
            // Return the data from specified url
            return stream;
        }

        private void revertList(List<Data> revertlist)
        {
            Collections.reverse(revertlist);
        }


        protected void onPostExecute(String stream)
        {
            ImageView image = new ImageView(getActivity().getApplicationContext());
            if (stream != null)
            {
                try
                {
                        Log.d("i have json array ","sbdfj");
                        JSONArray jsonarray = new JSONArray(stream);
                        for (int i = 0; i < jsonarray.length(); i++)
                        {
                            JSONObject jsonobject = jsonarray.getJSONObject(i);
                            String title = jsonobject.getString("title");
                            String description = jsonobject.getString("description");
                            String date_entry = jsonobject.getString("date_entry");
                            String  datinka =  date_entry.substring(0,10);
                            String  imgLink = jsonobject.getString("ImgLink");
                            String  linkStory = jsonobject.getString("Link");
//                            stringUrls.add(imgLink);
//                            Data data1 = new Data(title,datinka,description,image);
                             Data data1 = new Data(title,datinka,description,image,imgLink,linkStory);
                            //checkfor image link
                          //  Data data1 = new Data(title,datinka,description,imgLink);
                            dataList.add(data1);

//                            pbM.setVisibility(View.INVISIBLE);
                        }
                            revertList(dataList);
                            dataAdapter = new DataAdapter(getContext(),dataList,typefaceThin,typefaceMed);
                            recyclerView.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();

                        Log.d("string urls ",Integer.toString(stringUrls.size()));
                        //Toast.makeText(getActivity().getApplicationContext(), stringUrls.size(), Toast.LENGTH_LONG).show();

                  //call execute on the second AsyncTask

                } catch (JSONException e)
                {
                        e.printStackTrace();
                }
            } // if statement end
//                   AsyncTask task = new ProcessPictures(pbM).execute(stringUrls);
//                   Log.d("the status task is ",task.getStatus().toString());
        } // onPostExecute() end


    }//END OF ProcessJSON private class


//    // THE SECOND PRIVATE CLASS ACCESS
//    public class ProcessPictures extends AsyncTask<ArrayList<String>, Integer, List<Bitmap>> {
//
//                private DotProgressBar pbM;
//                public ProcessPictures(DotProgressBar pb)
//                {
//                    pbM = pb;
//                }
//                @Override
//                protected List<Bitmap> doInBackground(ArrayList<String>... urls)
//                {
//                        //get passed arrayList
//                         int  hundredProcent = urls.length;
//                         Log.d("LEGTH", "Progress Update: "+hundredProcent);
//                         ArrayList<String> cloudLinks = urls[0];
//                         List<Bitmap> bitmaps = new ArrayList<>();
//                         for(int i = 0; i<cloudLinks.size();i++)
//                         {
//                             try
//                             {
//                                 InputStream in = new java.net.URL(cloudLinks.get(i)).openStream();
//                                 Bitmap  bitmap = BitmapFactory.decodeStream(in);
//                                 bitmaps.add(bitmap);
//                                 //publishProgress(progress);
//                                // Log.d("PROLOG", "Progress Update: " +(int) (i /cloudLinks.size() )*100);
//                                 Log.d("PROLOG", "Progress Update: "+i);
//                                 publishProgress(i);
//
//                             } catch (Exception e)
//                             {
//                                 e.printStackTrace();
//                             }
//                         }
//
//                        return bitmaps;
//                }
//
//
//                @Override
//                protected void onPreExecute()
//                {
//                    Log.d("LOG_PREEXE", "Pre-Execute");
//
//                    super.onPreExecute();
//                    pbM.setVisibility( View.VISIBLE);
////                    teSecondsProgressedM.setVisibility( View.VISIBLE);
//                }
//
//                @Override
//                protected void onProgressUpdate(Integer... progress)
//                {
//                        Log.d("LOG_TAG", "Progress Update: " + progress[0].toString());
//                        super.onProgressUpdate(progress[0]);
////                        pbM.setProgress( progress[0]);
//                }
//
//        protected void onPostExecute(List<Bitmap> result)
//                {
//                            if (result != null && !result.isEmpty())
//                            {
//                                  for(int i = 0; i < result.size(); i++)
//                                  {
//                                          ImageView image = new ImageView(getActivity().getApplicationContext());
//                                          Bitmap bitmap = Bitmap.createScaledBitmap(result.get(i),200,200,true);
//                                          image.setImageBitmap(bitmap);
//                                          dataList.get(i).setImage(image);
//                                          Log.d("images ", dataList.get(i).getImage().toString());
//
//                                  }
//
//                            }
//
//                            dataAdapter = new DataAdapter(dataList,typefaceThin,typefaceMed);
//                            recyclerView.setAdapter(dataAdapter);
//                            dataAdapter.notifyDataSetChanged();
//                            pbM.setVisibility(View.INVISIBLE);
//
//                }
//    } // onPostExecute() end



//TWO CLASSES STARTED DOING THE THING WITH FUTURES INSTEAD OF ASYNC CALLS MAYBE CONTINUED IF ASYNC TASK TOO SLOW
    private static class ProcessJsonFuture implements Callable
    {

        @Override
        public String call() throws Exception
        {
            String stream = null;
            HTTPDataHandler hh = new HTTPDataHandler();
            stream = hh.GetHTTPData(urlString);
            // Log.d("THE JSON IS ", stream);
            // Return the data from specified url
            return stream;
        }
    }

    private static class ProcessPicturesFuture implements Callable
    {
        private final ArrayList<String> urlsPictures;

        public ProcessPicturesFuture(ArrayList<String> urlsPictures)
        {
            this.urlsPictures = urlsPictures;
        }

        @Override
        public List<Bitmap> call() throws Exception
        {
            ArrayList<String> cloudLinks =  urlsPictures;
            List<Bitmap> bitmaps = new ArrayList<>();
            for(int i = 0; i<cloudLinks.size();i++)
            {
                try
                {
                    InputStream in = new java.net.URL(cloudLinks.get(i)).openStream();
                    Bitmap  bitmap = BitmapFactory.decodeStream(in);
                    bitmaps.add(bitmap);

                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }


}//END OF BIG CLASS



//        ProcessJsonFuture task = new ProcessJsonFuture();
//        Future future = threadpool.submit(task);
//        String jsonarrayString = (String) future.get();
//        JSONArray jsonarray = new JSONArray(jsonarrayString);
//        for (int i = 0; i < jsonarray.length(); i++)
//        {
//            JSONObject jsonobject = jsonarray.getJSONObject(i);
//            String title = jsonobject.getString("title");
//            String description = jsonobject.getString("description");
//            String date_entry = jsonobject.getString("date_entry");
//            String  datinka =  date_entry.substring(0,10);
//            String  imgLink = jsonobject.getString("ImgLink");
//
//            stringUrls.add(imgLink);
//            Data data1 = new Data(title,datinka,description);
//            dataList.add(data1);
//        }
//        dataAdapter.notifyDataSetChanged();
