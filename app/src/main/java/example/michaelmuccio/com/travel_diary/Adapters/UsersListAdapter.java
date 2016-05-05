package example.michaelmuccio.com.travel_diary.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

import example.michaelmuccio.com.travel_diary.R;

/**
 * Created by michaelmuccio on 4/29/16.
 */
public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.UsersListViewHolder> {

    private static OnItemClickListener listener;
    Context context;
    LinkedList linkedList;

    public UsersListAdapter() {
        //empty constructor
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class UsersListViewHolder extends RecyclerView.ViewHolder{
        TextView userName;
        TextView timeStamp;
        TextView tripBlurp;
        ImageView tripIcon;

        public UsersListViewHolder(final View itemView){
            super(itemView);

            userName = (TextView) itemView.findViewById(R.id.user_name);
            timeStamp = (TextView) itemView.findViewById(R.id.time_stamp);
            tripBlurp = (TextView) itemView.findViewById(R.id.trip_info_cardview);
            tripIcon = (ImageView) itemView.findViewById(R.id.users_latest_travel_pic);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onItemClick(itemView, getLayoutPosition());
                }
            });
        }
    }

    @Override
    public UsersListAdapter.UsersListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.users_info_view_for_adapter, parent, false);
        UsersListViewHolder vh = new UsersListViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(UsersListAdapter.UsersListViewHolder holder, int position) {
        //TODO time stamp and add pic
//        long timeStamp = System.currentTimeMillis();
//        holder.headline.setText(data.get(position).getHeadline().getMain());
//        holder.articleAbstract.setText(data.get(position).getLead_paragraph());
//        String agoText = NewsRecyclerAdapter.getBiggestUnitTimeElapsed(data.get(position).getPub_date(), timeStamp);
//        if(agoText.isEmpty()) {
//            holder.ago.setText("published today");
//        } else {
//            holder.ago.setText("published " + NewsRecyclerAdapter.getBiggestUnitTimeElapsed(data.get(position).getPub_date(), timeStamp) + " ago");
//        }
//
//        String imageURI = null;
//        Multimedia[] multiMedia = data.get(position).getMultimedia();
//        if(multiMedia != null && multiMedia.length > 0) {
//            imageURI = multiMedia[0].getUrl();
//        }
//        if (imageURI == null) {
//            imageURI = "R.drawable.nyt_icon";
//        }
//
//        Picasso.with(context)
//                .load("http://nytimes.com/" + imageURI)
//                .placeholder(R.drawable.nyt_icon)
//                .resize(100, 100)
//                .centerCrop()
//                .into(holder.imageIcon);
    }

    @Override
    public int getItemCount() {
        return linkedList.size();
    }


}
