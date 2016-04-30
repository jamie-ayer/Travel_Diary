package example.michaelmuccio.com.travel_diary.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

    private static AdapterView.OnItemClickListener listener;
    Context context;
    LinkedList linkedList;

    public UsersListAdapter() {
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
        return null;
    }

    @Override
    public void onBindViewHolder(UsersListAdapter.UsersListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
