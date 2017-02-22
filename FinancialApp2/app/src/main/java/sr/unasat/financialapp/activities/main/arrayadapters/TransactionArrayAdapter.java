package sr.unasat.financialapp.activities.main.arrayadapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import sr.unasat.financialapp.R;
import sr.unasat.financialapp.dto.Transaction;

/**
 * Created by Jair on 2/22/2017.
 */

public class TransactionArrayAdapter extends ArrayAdapter<String> {

    public TransactionArrayAdapter(Context context, String[] transactions) {

        super(context, R.layout.transaction_card, transactions);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.transaction_card,parent,false);

        TextView tranName = (TextView)customView.findViewById(R.id.transaction_name);
        TextView tranDescr = (TextView) customView.findViewById(R.id.transaction_descr);
        TextView tranVal = (TextView) customView.findViewById(R.id.transaction_value);



        return super.getView(position, convertView, parent);
    }
}
