package com.eba.aislatech;

/**
 * Created by Chaitanya on 9/17/2015.
 *
 * This RecyclerView Adapter is used for Faqs, it displays the alerts in cardview which is
 * inflated from support_faq_row.xml. This adapter class also holds the onClick event for the
 * Faqs.
 *
 */

import android.animation.ValueAnimator;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterFaq extends RecyclerView.Adapter < AdapterFaq.DataObjectHolder > {

    private static MyClickListener myClickListener;
    private ArrayList < ModelFaq > mDataset;

    public AdapterFaq(ArrayList < ModelFaq > myDataset) {
        mDataset = myDataset;
    }


    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.support_faq_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.faqQuestion.setText(mDataset.get(position).getFaqQuestion());
        holder.faqAnswer.setText(mDataset.get(position).getFaqAnswer());

    }

    public void addItem(ModelFaq dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        if (mDataset == null) {
            return 0;
        };
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView faqQuestion;
        TextView faqAnswer;


        public DataObjectHolder(View itemView) {
            super(itemView);
            faqQuestion = (TextView) itemView.findViewById(R.id.faq_question);
            faqAnswer = (TextView) itemView.findViewById(R.id.faq_answer);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {


            toggleProductDescriptionHeight(v);

            //myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    private static void toggleProductDescriptionHeight(View v) {
        final CardView descriptionCardView = (CardView) v.findViewById(R.id.faq_cardview);
        TextView faqAnswer = (TextView) v.findViewById(R.id.faq_answer);
        int stringLength = faqAnswer.getText().length();
        Log.i("tag", String.valueOf(stringLength));
        int descriptionViewMinHeight = 300;
        if (descriptionCardView.getHeight() == descriptionViewMinHeight) {
            // expand
            ValueAnimator anim = ValueAnimator.ofInt(descriptionCardView.getMeasuredHeightAndState(), 500 + stringLength);
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {@Override
                                                                               public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = descriptionCardView.getLayoutParams();
                layoutParams.height = val;
                descriptionCardView.setLayoutParams(layoutParams);
            }
            });
            anim.start();
        } else {
            // collapse
            ValueAnimator anim = ValueAnimator.ofInt(descriptionCardView.getMeasuredHeightAndState(),
                    descriptionViewMinHeight);
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {@Override
                                                                               public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = descriptionCardView.getLayoutParams();
                layoutParams.height = val;
                descriptionCardView.setLayoutParams(layoutParams);
            }
            });
            anim.start();
        }
    }
}