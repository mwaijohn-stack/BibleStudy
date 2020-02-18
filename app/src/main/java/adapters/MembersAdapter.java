package adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.biblestudy.R;
import com.biblestudy.StudentAttendanceStatsActivity;

import java.util.List;

import models.GroupMember;
import utilities.SharedPref;

import static com.biblestudy.GroupMembersActivity.create_attendance;


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

        if (position == 1){
            SharedPref.write(SharedPref.GROUP_NAME,item.getGroupName());
            Log.d("group_name",item.getGroupName());
        }


        String user_id = SharedPref.read(SharedPref.STUDENT_ID,"0");
        Log.d("user_id",user_id);
        Log.d("user_id_",item.getId());

        if (user_id.equals(item.getId())){
            if (item.getRole().equals("1")){
                create_attendance.setVisibility(View.VISIBLE);
            }
        }else {
            //create_attendance.setVisibility(View.GONE);
        }

        if (Integer.parseInt(item.getRole())== 1){

            holder.name.setText(item.getFirstName() +" " + item.getLastName());
            holder.name.setTextColor(context.getResources().getColor(R.color.colorAccent));
            holder.phone.setText("+"+item.getMsisdn());
            holder.phone.setTextColor(context.getResources().getColor(R.color.colorAccent));
            holder.position.setText(1 + position +".");
            holder.position.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }else {

            holder.name.setText(item.getFirstName() +" " + item.getLastName());
            holder.phone.setText("+"+item.getMsisdn());
            holder.position.setText(1 + position +".");
        }

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context, StudentAttendanceStatsActivity.class);

                intent.putExtra("student_id", item.getId());



                intent.putExtra("campus_id", item.getCampusId());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
            }
        });

        holder.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context, StudentAttendanceStatsActivity.class);

                intent.putExtra("student_id", item.getId());



                intent.putExtra("campus_id", item.getCampusId());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

//    @Override
//    public void onClick(View v) {
//        Intent intent  = new Intent(context, StudentAttendanceStatsActivity.class);
//
//
//        Log.d("vclick","view clicked");
//
//        GroupMember groupMember = list.get(position);
//        intent.putExtra("campus_id", groupMember.getCampusId());
//        intent.putExtra("student_id", groupMember.getId());
//
//        context.startActivity(intent);
//    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView position, phone, name;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            phone = (TextView) view.findViewById(R.id.phone);
            position = (TextView) view.findViewById(R.id.position);


        }
    }
}
