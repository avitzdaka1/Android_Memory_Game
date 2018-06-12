package afeka.com.hw1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Score> mDataSource;

    public ScoreAdapter(Context context, ArrayList<Score> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = mInflater.inflate(R.layout.list_row_view, parent, false);

        TextView titleTextView =
                (TextView) rowView.findViewById(R.id.recipe_list_title);
        TextView detailTextView =
                (TextView) rowView.findViewById(R.id.recipe_list_detail);
        Score score = (Score) getItem(position);
        titleTextView.setText(score.getName());
        detailTextView.setText(String.valueOf(score.getScore()));




        return rowView;
    }
}
