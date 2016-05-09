package example.michaelmuccio.com.travel_diary.ViewHolders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import example.michaelmuccio.com.travel_diary.R;

/**
 * Created by michaelmuccio on 5/4/16.
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsListViewHolder> {
    private static OnItemClickListener listener;
    Context context;
    ArrayList list;

    public ContactsAdapter() {

    }

    public interface OnItemClickListener{
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class ContactsListViewHolder extends RecyclerView.ViewHolder{
        TextView userName;
        ImageView profilePic;
        ImageButton addContactButton;

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

    @Override
    public ContactsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.trips_by_users, parent, false);
        ContactsListViewHolder vh = new ContactsAdapter.ContactsListViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(ContactsListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
