package com.example.user.twitter_url_dashboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    ArrayList<MyUserData> mlatlnglist;
    final int j=0;
    private Context context;
    public MainAdapter(ArrayList<MyUserData> latlnglist) {
            mlatlnglist = latlnglist;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MainAdapter.ViewHolder holder, int position) {
        if(position<mlatlnglist.size()) {
            holder.uid.setText(mlatlnglist.get(position).getUsername());
            holder.time.setText(mlatlnglist.get(position).getTime());
            holder.url.setText((mlatlnglist.get(position).getUrls().toString().replace("[", "").replace(","," ").replace("]", "")));

            String []splitterString=(mlatlnglist.get(position).getUrls().toString().replace("[", "").replace(","," ").replace("]", "")).split(" ");
            for (String a : splitterString){
                if(a.contains("https:")){


                    Linkify.addLinks(holder.url,Linkify.WEB_URLS);

                }
            }

           /*
            final MyUserData str = mlatlnglist.get(position+2);

            holder.url.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.url.setLinkTextColor(Color.BLUE);
                    final Intent browserIntent = new Intent(Intent.ACTION_WEB_SEARCH);
                    browserIntent.setData(Uri.parse("+str+"));
                    context.startActivity(browserIntent);


                }
            });

            position = position+2;
*/

        }
    }


    @Override
    public int getItemCount() {
        return mlatlnglist.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView time,uid,url;

        public ViewHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.tvTime);
            uid = itemView.findViewById(R.id.tvUID);
            url = itemView.findViewById(R.id.tvURL);
            context = itemView.getContext();
        }
    }
}
