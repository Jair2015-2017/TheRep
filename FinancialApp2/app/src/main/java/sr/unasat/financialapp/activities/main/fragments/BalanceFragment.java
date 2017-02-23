package sr.unasat.financialapp.activities.main.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sr.unasat.financialapp.R;
import sr.unasat.financialapp.activities.main.arrayadapters.TransactionArrayAdapter;
import sr.unasat.financialapp.db.dao.Dao;
import sr.unasat.financialapp.dto.Transaction;

public class BalanceFragment extends Fragment {

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //TODO
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_balance, container, false);

        TextView closingView = (TextView)view.findViewById(R.id.balance_closing_value);
        ListView listView = (ListView)view.findViewById(R.id.transaction_listView);

        Dao dao = new Dao(getActivity());
        List<Transaction> transactionList = dao.getTransactions();
        List<String> transactionNames=new ArrayList<>();

        for (Transaction transaction: transactionList){
            transactionNames.add(transaction.getTran_name());
        }

        TransactionArrayAdapter adapter = new TransactionArrayAdapter(getActivity(),transactionNames);
        listView.setAdapter(adapter);

        closingView.setText(String.valueOf(dao.getUserById(1).getClosing()));


        return view;
    }


}
