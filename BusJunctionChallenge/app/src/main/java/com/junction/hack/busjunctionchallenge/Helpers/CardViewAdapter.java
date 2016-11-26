package com.junction.hack.busjunctionchallenge.Helpers;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.junction.hack.busjunctionchallenge.R;
import com.junction.hack.busjunctionchallenge.StoryActivity;
import com.junction.hack.busjunctionchallenge.models.Story;

import java.util.List;

/**
 * Created by Buka on 11/26/2016.
 */
public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.StoryViewHolder>{

    List<Story> stories;
    StoryActivity storyActivity;
    public CardViewAdapter(List<Story> stories, StoryActivity activity){
        this.stories = stories;
        this.storyActivity = activity;
    }

    @Override
    public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_story_display, parent, false);
        v.setMinimumWidth(parent.getMeasuredWidth());
        StoryViewHolder pvh = new StoryViewHolder(v, storyActivity);
        return pvh;
    }

    @Override
    public void onBindViewHolder(StoryViewHolder holder, int i) {
        holder.description.setText(stories.get(i).getDescription());
        holder.header.setText(stories.get(i).getTitle());
        holder.personPhoto.setBackgroundResource(stories.get(i).getImage());

        holder.surprise.setText(stories.get(i).getSuprise());
        holder.hart.setText(stories.get(i).getHart());
        holder.like.setText(stories.get(i).getLikes());
    }

    @Override
    public int getItemCount() {
        return this.stories.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class StoryViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView header;
        TextView description;
        ImageView coverPhoto;
        ImageView personPhoto;

        TextView like;
        TextView surprise;
        TextView hart;

        StoryViewHolder(View itemView, StoryActivity storyActivity) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.story_recyclerview);
            description = (TextView)itemView.findViewById(R.id.description);
            header = (TextView)itemView.findViewById(R.id.person_name);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
            coverPhoto = (ImageView)itemView.findViewById(R.id.cover_photo);

            like = (TextView) storyActivity.findViewById(R.id.number_of_likes);
            surprise = (TextView) storyActivity.findViewById(R.id.number_of_surprises);
            hart = (TextView) storyActivity.findViewById(R.id.number_of_harts);
        }
    }

}
