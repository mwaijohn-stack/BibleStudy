package adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.biblestudy.R;

import java.util.HashMap;
import java.util.List;

import models.AttendanceRequest;
import models.GroupMember;

public class AttendanceAdapter  extends RecyclerView.Adapter<AttendanceAdapter.MyViewHolder> {

    public static HashMap<String,AttendanceRequest> attendanceRequests = new HashMap<>();
    List<GroupMember> list;
    Context context;
    //ArrayList<AttendanceRequest> attendanceRequests = new ArrayList<>();

    public AttendanceAdapter(List<GroupMember> members, Context context){
            this.list = members;
            this.context = context;
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

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.attendance, parent, false);

            return new MyViewHolder(itemView);
    }


    @Override
    public void onViewRecycled(MyViewHolder holder) {
        holder.checkBox.setChecked(false); // - this line do the trick
        super.onViewRecycled(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        GroupMember item =  list.get(position);

        holder.name.setText(item.getFirstName() +" " + item.getLastName());
        holder.position.setText(1 + position +".");
        //holder.checkBox.setChecked(false);

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("campuu",item.getCampusId());
            }
        });


        Log.d("campusid",item.getCampusId());

        AttendanceRequest ATDR = new AttendanceRequest(Integer.parseInt(item.getId()),
                Integer.parseInt(item.getGroupingProcessorId()),
                Integer.parseInt(item.getGroupName()), 1,
                7,
                0,Integer.parseInt(item.getCampusId()));

            attendanceRequests.put(item.getId(),ATDR);

            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                   @Override
                   public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                       if (isChecked){

                           AttendanceRequest ATDR = new AttendanceRequest(Integer.parseInt(item.getId()),
                                   Integer.parseInt(item.getGroupName()),
                                   Integer.parseInt(item.getGroupingProcessorId()),
                                   1,
                                   7,
                                   1,
                                   Integer.parseInt(item.getCampusId()));
                           holder.checkBox.setSelected(true);
                           attendanceRequests.put(item.getId(),ATDR);
                       }else {

                           AttendanceRequest ATDR = new AttendanceRequest(Integer.parseInt(item.getId()),
                                  Integer.parseInt(item.getGroupName()),
                                   Integer.parseInt(item.getGroupingProcessorId()),
                                   1,
                                   7,
                                   0,
                                   Integer.parseInt(item.getCampusId())
                                   );

                           attendanceRequests.put(item.getId(),ATDR);
                           holder.checkBox.setSelected(false);
                       }
                   }
               }
            );
    }

    @Override
    public int getItemCount() {
            return list.size();
    }

}

