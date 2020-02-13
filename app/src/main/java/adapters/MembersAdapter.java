package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.biblestudy.R;

import java.util.List;

import models.GroupMember;


public class MembersAdapter  extends RecyclerView.Adapter<MembersAdapter.MyViewHolder> {

    List<GroupMember> list;
    Context context;

    public MembersAdapter(List<GroupMember> members, Context context){
        this.list = members;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.members_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GroupMember item =  list.get(position);

        holder.name.setText(item.getFirstName());
        holder.phone.setText(item.getMsisdn());
        holder.county.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, phone, county;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            phone = (TextView) view.findViewById(R.id.phone);
            county = (TextView) view.findViewById(R.id.county);
        }
    }
}
