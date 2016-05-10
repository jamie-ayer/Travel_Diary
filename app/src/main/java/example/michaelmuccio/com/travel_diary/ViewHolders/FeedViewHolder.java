package example.michaelmuccio.com.travel_diary.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import example.michaelmuccio.com.travel_diary.R;

/**
 * Created by michaelmuccio on 5/9/16.
 */
public class FeedViewHolder extends RecyclerView.ViewHolder{
    private TextView tripTitle;
    private TextView timeStamp;
    private TextView tripSummary;
    private ImageView tripIcon;
    //private static OnItemClickListener listener;

    public FeedViewHolder(final View itemView){
        super(itemView);

        tripTitle = (TextView) itemView.findViewById(R.id.user_title);
        timeStamp = (TextView) itemView.findViewById(R.id.time_stamp);
        tripSummary = (TextView) itemView.findViewById(R.id.trip_info_cardview);
        tripIcon = (ImageView) itemView.findViewById(R.id.users_latest_travel_pic);
        
    }

    public void setTripTitle(String title) {
        tripTitle.setText(title);
    }

    public void setTimeStamp(String time) {
        timeStamp.setText(time);
    }

    public void setTripSummary(String summary) {
        tripSummary.setText(summary);
    }

    public void setTripIcon(int icon) {
        tripIcon.setImageResource(icon);
    }
}
