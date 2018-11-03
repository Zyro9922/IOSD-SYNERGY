package com.example.mohitkumar.originalsynergygo.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mohitkumar.originalsynergygo.Activities.Business;
import com.example.mohitkumar.originalsynergygo.Activities.Office;
import com.example.mohitkumar.originalsynergygo.Models.CardDetails;
import com.example.mohitkumar.originalsynergygo.R;
import com.example.mohitkumar.originalsynergygo.Activities.Residence;

import java.util.ArrayList;

/**
 * Created by mohitkumar on 11/04/17.
 */
public class RecyclerCardAdapter extends RecyclerView.Adapter<RecyclerCardAdapter.RecycViewHolder>{

    Context context;
    ArrayList<CardDetails> arrayList = new ArrayList<CardDetails>();

    public RecyclerCardAdapter(Context context, ArrayList<CardDetails> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public RecycViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.agent_card,parent,false);
        RecycViewHolder recycViewHolder = new RecycViewHolder(view,context,arrayList);
        return recycViewHolder;
    }

    @Override
    public void onBindViewHolder(RecycViewHolder holder, int position) {
        CardDetails cardDetails = arrayList.get(position);

//        Typeface tf = Typeface.createFromAsset(context.getAssets(),"fonts/OpenSans-Semibold.ttf");

//        holder.textView1.setTypeface(tf);
//        holder.textView2.setTypeface(tf);
//        holder.textView3.setTypeface(tf);
//        holder.textView4.setTypeface(tf);
//        holder.textView5.setTypeface(tf);
//        holder.textView6.setTypeface(tf);
//        holder.textView7.setTypeface(tf);
//        holder.textView8.setTypeface(tf);
//        holder.textView9.setTypeface(tf);

        String applcoappl = cardDetails.getApplorco();

        holder.textView1.setText(cardDetails.getName());
        holder.textView2.setText(cardDetails.getApplorco());
        holder.textView3.setText(cardDetails.getType());
        holder.textView4.setText(cardDetails.getAddress());
       // holder.textView5.setText(cardDetails.getApplorco());
        holder.textView6.setText(cardDetails.getMobile());
       // holder.textView7.setText(cardDetails.);
       // holder.textView8.setText(cardDetails.getFos());
        holder.textView9.setText(cardDetails.getRefno());
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecycViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9;
        Context context;
        ArrayList<CardDetails> data = new ArrayList<CardDetails>();
        String s;

        public RecycViewHolder(View itemView, Context context, ArrayList<CardDetails> data) {
            super(itemView);
            this.data = data;
            this.context = context;

            textView1 = (TextView) itemView.findViewById(R.id.name);
            textView2 = (TextView) itemView.findViewById(R.id.appl_coappl);
            textView3 = (TextView) itemView.findViewById(R.id.add_type);
            textView4 = (TextView) itemView.findViewById(R.id.address);
           // textView5 = (TextView) itemView.findViewById(R.id.land_mark);
            textView6 = (TextView) itemView.findViewById(R.id.p_contact);
          //  textView7 = (TextView) itemView.findViewById(R.id.s_contact);
          //  textView8 = (TextView)itemView.findViewById(R.id.age);
            textView9 = (TextView)itemView.findViewById(R.id.refno);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        if(textView3.getText().toString().equals("RESIDENCE")) {
                Intent intent = new Intent(this.context, Residence.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("name", textView1.getText().toString());
                intent.putExtra("appl_coappl", textView2.getText().toString());
                intent.putExtra("addtype", textView3.getText().toString());
                intent.putExtra("address", textView4.getText().toString());
              //  intent.putExtra("landmark", textView5.getText().toString());
                intent.putExtra("pcontact", textView6.getText().toString());

              //  intent.putExtra("scontact", textView7.getText().toString());
              //  intent.putExtra("agent", textView8.getText().toString());
                intent.putExtra("uniid", textView9.getText().toString().trim());
//                Log.d("AgentAdap", textView8.getText().toString());
//                Log.d("UniqueAdap", textView9.getText().toString());
                context.startActivity(intent);
            } else if(textView3.getText().toString().equals("SERVICE")) {
                Intent intent = new Intent(this.context, Office.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("name", textView1.getText().toString());
            intent.putExtra("appl_coappl", textView2.getText().toString());
                intent.putExtra("addtype", textView3.getText().toString());
                intent.putExtra("address", textView4.getText().toString());
             //   intent.putExtra("landmark", textView5.getText().toString());
                intent.putExtra("pcontact", textView6.getText().toString());
             //   intent.putExtra("scontact", textView7.getText().toString());
             //   intent.putExtra("agent", textView8.getText().toString());
                intent.putExtra("uniid", textView9.getText().toString().trim());
//                Log.d("AgentAdap", textView8.getText().toString());
//                Log.d("UniqueAdap", textView9.getText().toString());
                context.startActivity(intent);
            } else if(textView3.getText().toString().equals("BUSINESS")) {
                Intent intent = new Intent(this.context, Business.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("name", textView1.getText().toString());
            intent.putExtra("appl_coappl", textView2.getText().toString());
                intent.putExtra("addtype", textView3.getText().toString());
                intent.putExtra("address", textView4.getText().toString());
               // intent.putExtra("landmark", textView5.getText().toString());
                intent.putExtra("pcontact", textView6.getText().toString());
               // intent.putExtra("scontact", textView7.getText().toString());
             //   intent.putExtra("agent", textView8.getText().toString());
                intent.putExtra("uniid", textView9.getText().toString().trim());
//                Log.d("AgentAdap", textView8.getText().toString());
//                Log.d("UniqueAdap", textView9.getText().toString());
                context.startActivity(intent);
            }

        }
    }
}
