package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.biblestudy.R;

import java.util.List;

import models.GroupMember;
public class AttendanceAdapter  extends RecyclerView.Adapter<AttendanceAdapter.MyViewHolder> {

        List<GroupMember> list;
        Context context;

        public AttendanceAdapter(List<GroupMember> members, Context context){
                this.list = members;
                this.context = context;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.attendance, parent, false);

                return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                GroupMember item =  list.get(position);

                holder.name.setText(item.getFirstName() +" " + item.getLastName());
                holder.position.setText(1 + position +".");
        }
        @Override
        public int getItemCount() {
                return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView position, name;
            public CheckBox checkBox;

            public MyViewHolder(View view) {
                super(view);
                name =  view.findViewById(R.id.name);
                position =  view.findViewById(R.id.position);
                checkBox = view.findViewById(R.id.checkbox);
            }
        }
}

