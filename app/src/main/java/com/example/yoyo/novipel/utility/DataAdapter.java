package com.example.yoyo.novipel.utility;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import com.example.yoyo.novipel.R;

import java.util.List;
import java.util.Locale;

/**
 * Created by yoyo on 7/20/2017.
 */
//Adapter for the RecycleView
public class DataAdapter  extends RecyclerView.Adapter<DataAdapter.MyViewHolder>
{
    private List<Data> dataList;
    private Typeface typeThin;
    private Typeface typeMed;
    private Context context;


    public DataAdapter(Context context , List<Data> dataList, Typeface typeThin, Typeface typeMed)
    {
        this.dataList = dataList;
        this.typeThin = typeThin;
        this.typeMed = typeMed;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        //Log.d("THE PARENT IS ",parent.toString());
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row_new,parent,false);
           CardView card = (CardView) itemView.findViewById(R.id.card);

     //Touch here for scalability
      int width =  parent.getWidth();
      int height = parent.getHeight();
      int cardheight = height/3;
      int cardwidth = width - 1;

        Log.d("DataAdapter"," the height is "+height);
        card.setLayoutParams(new CardView.LayoutParams(
                cardwidth, (int) (cardheight - 30))); //the minus
        // CardView.LayoutParams.WRAP_CONTENT
//        card.setMinimumHeight(height/6);//this way does not work
//        int minH = height/5;
//        itemView.setMinimumHeight(minH);

      // itemView.setLayoutParams();
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        //Data data = new Data();
        Data data = dataList.get(position);
        Log.d( "POSITION" , position + " " );
        Log.d( "DATA" , data.getImgLink() + "  ha" );

        holder.title.setText(data.getTitle());
        holder.time.setText(data.getTime());
        holder.explanation.setText(data.getExplanation());
        if(data.getImgLink() != null  && !data.getImgLink().isEmpty())
        {
         Picasso.with(context).load(data.getImgLink()).error(R.drawable.imagenotfound).placeholder(R.drawable.progress_animation).resize(200, 200).into(holder.image);
        }else
        {
            String uri = "@drawable/imagenotfound";
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            Drawable res = context.getResources().getDrawable(imageResource);
            Log.d( "DRAWABLE" , res.isVisible() + " " );
          //  data.setImageFromDrawable(res);
            Picasso.with(context).load(R.drawable.imagenotfound).resize(200, 200).into(holder.image);
          //  holder.image.setImageDrawable(res);
        }

//        holder.image.setImageDrawable(data.getImage().getDrawable());
//        if(position % 2 == 0 )
//        {
//            holder.title.setTextColor(Color.BLUE);
//        }else
//        {
//            holder.title.setTextColor(Color.rgb(255,105,180));
//        }


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position, List<Object> payloads)
    {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount()
    {
        return dataList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
            public TextView title;
            public TextView time;
            public TextView explanation;
            public ImageView image;


            public MyViewHolder(View view)
            {
                super(view);
                title = (TextView) view.findViewById(R.id.title);
                title.setTypeface(typeMed);
                time = (TextView) view.findViewById(R.id.time);
                explanation = (TextView) view.findViewById(R.id.explanation);
                time.setTypeface(typeThin); explanation.setTypeface(typeThin);
                image = (ImageView) view.findViewById(R.id.imageHa);
                //time = (TextView) view.findViewById(R.id.)
            }
   }


}
