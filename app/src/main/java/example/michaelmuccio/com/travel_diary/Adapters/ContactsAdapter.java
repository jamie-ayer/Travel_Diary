package example.michaelmuccio.com.travel_diary.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import example.michaelmuccio.com.travel_diary.R;

/**
 * Created by michaelmuccio on 5/4/16.
 */
public class ContactsAdapter extends RecyclerView.Adapter<> {

    public ContactsAdapter() {

    }

    public interface onitemClickListener{
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        this.listener = listener;
    }

    public class ContactsListViewHolder extends RecyclerView.ViewHolder{
        private TextView userName;
        private ImageView profilePic;
        private ImageButton addContactButton;

        public ContactsListViewHolder(View itemView) {
            super(itemView);

            userName = (TextView) itemView.findViewById(R.id.contact_name);
            profilePic = (ImageView) itemView.findViewById(R.id.prof_pic);
            addContactButton = (ImageButton) itemView.findViewById(R.id.add_contact_button);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onItemClick(addContactButton, getLayoutPosition());
                }
            });
        }
    }

}
