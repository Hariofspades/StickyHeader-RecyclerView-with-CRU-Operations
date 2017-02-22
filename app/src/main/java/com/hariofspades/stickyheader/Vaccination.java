package com.hariofspades.stickyheader;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

/**
 * Created by Hari on 22/02/17.
 */

public class Vaccination extends AbstractItem<Vaccination, Vaccination.ViewHolder> {

    String title, date, info, header;

    public Vaccination(){

    }

    public String getDate() {
        return date;
    }

    public String getInfo() {
        return info;
    }

    public String getTitle() {
        return title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return title+" "+date+" "+info+" "+header;
    }

    @Override
    public int getType() {
        return R.id.fastadapter_sample_item_id;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.list_item;
    }

    @Override
    public void bindView(ViewHolder holder, List<Object> payloads) {
        super.bindView(holder, payloads);
        holder.title.setText(title);
        //set the text for the description or hide
        holder.date.setText(date);
        holder.info.setText(info);
    }

    @Override
    public void unbindView(ViewHolder holder) {
        super.unbindView(holder);
        holder.title.setText(null);
        //set the text for the description or hide
        holder.date.setText(null);
        holder.info.setText(null);
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView title;
        protected TextView date;
        protected TextView info;

        public ViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.title);
            this.date = (TextView) view.findViewById(R.id.date);
            this.info = (TextView) view.findViewById(R.id.info);
        }
    }
}
