package au.com.taeho.app.mygolfscorecard;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by taeho on 18/07/2014.
 *
 * Tutorial
 * http://www.learn-android-easily.com/2013/09/android-custom-gridview-example.html
 *
 */
public class GridViewCustomAdapter extends ArrayAdapter {
    Context context;

    public GridViewCustomAdapter(Context context) {
        super(context, 0);
        this.context=context;

    }

    public int getCount() {
        return 24;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;

        if (row == null)
        {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.grid_row, parent, false);


            TextView textViewTitle = (TextView) row.findViewById(R.id.textView);
            ImageView imageViewIte = (ImageView) row.findViewById(R.id.imageView);

            if(position%2==0)
            {
                textViewTitle.setText("Facebook");
                imageViewIte.setImageResource(R.drawable.ic_launcher);
            }
            else
            {
                textViewTitle.setText("Twitter");
                imageViewIte.setImageResource(R.drawable.ic_launcher);
            }
        }



        return row;

    }

}
