package dpi.ks19.participantapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import dpi.ks19.participantapp.R;


public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.NumberViewHolder> {

    private static final String TAG = GreenAdapter.class.getSimpleName();
    private static Integer noOfMembers ;
    private ViewGroup viewGroup;
    private Context context;
    private String title;
    private static final String[] android={"Aswin N",
            "Kavin Raju",
            "Tanveer Ahmad",
            "Kathir T",
            "Sibi N",
            "Arun Balaji",
            "Karthikeyan",
            };
    private static final String[] web={"Jaswanth",
            "Vamsi Krishna",
            "Jaikanth",
            "Lakshminarayanan"};
    private static final String[] graphic={"Aniruth R",
            "Sruthi Baradwaj",
            "Haretha",
            "Naveen",
            "Sanjay Vasanth",
            "Manickavel",
            "Adithya Narayanan",
            "Yagaa Gowtham",
            "Gowtham K"};
    private String[] all_rules;

    GreenAdapter.NumberViewHolder viewHolder;
    View view;

    public GreenAdapter(Context context, String title) {
        this.context=context;
        this.title=title;

        if(title.equals("android"))
            noOfMembers=android.length;
        else if(title.equals("web"))
            noOfMembers=web.length;
        else
            noOfMembers=graphic.length;

    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        context = viewGroup.getContext();
        boolean shouldAttachToParentImmediately = false;
        LayoutInflater inflater = LayoutInflater.from(context);
        int layoutIdForListItem;
        layoutIdForListItem =R.layout.aboutus_recyl;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        viewHolder = new GreenAdapter.NumberViewHolder(view);
        return viewHolder;
    }

    public NumberViewHolder returnViewHolder(View view)
    {
        return new GreenAdapter.NumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder numberViewHolder, int position) {
        numberViewHolder.bind(position,numberViewHolder);
    }

    @Override
    public int getItemCount() {
        return noOfMembers;
    }

    class NumberViewHolder extends RecyclerView.ViewHolder{

        private ImageView icon_img;
        private TextView name,posi;

        public NumberViewHolder(View itemView) {
            super(itemView);
            icon_img = itemView.findViewById(R.id.imageView_profilePic_recyl);
            name=itemView.findViewById(R.id.textView_name_recyl);
            posi=itemView.findViewById(R.id.textView_posi_recyl);
        }

        @SuppressLint("ResourceAsColor")
        public void bind(int position, NumberViewHolder holder) {

            if(title.equals("android")) {
                name.setText(android[position]);
                posi.setText("APP DEVELOPER");
            }
            else if(title.equals("web")) {
                name.setText(web[position]);
                posi.setText("WEB DEVELOPER");
            }
            else {
                name.setText(graphic[position]);
                posi.setText("GRAPHIC DESIGNER");
            }
        }

    }


}



