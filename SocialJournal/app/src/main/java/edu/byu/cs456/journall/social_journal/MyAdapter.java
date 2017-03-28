package edu.byu.cs456.journall.social_journal;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * Created by Michael on 3/27/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private String[] mDataset;
    private Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public WebView mWebView;

        public ViewHolder(View v) {
            super(v);
            mWebView = (WebView) v.findViewById(R.id.web_view);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(String[] myDataset, Context context) {

        mDataset = myDataset;
        mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        //LinearLayout ll = (LinearLayout) parent;
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post, parent, false);
        // set the view's size, margins, paddings and layout parameters
        //...
        //v.setTextSize(20);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        //holder.mWebView.setText(mDataset[position]);

        //TODO not sure what to do with this yet...
        String post = mDataset[position];

//        holder.mWebView.getSettings().setLoadWithOverviewMode(true);
//        holder.mWebView.getSettings().setUseWideViewPort(true);
        holder.mWebView.setInitialScale(getScale());
        holder.mWebView.loadDataWithBaseURL("https://facebook.com", post, "text/html", "utf-8", null);
    }

    private int getScale(){
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        Double val = new Double(width)/new Double(500);
        val = val * 100d;
        return val.intValue();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
