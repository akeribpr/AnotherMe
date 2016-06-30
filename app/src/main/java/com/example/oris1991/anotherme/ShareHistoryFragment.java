package com.example.oris1991.anotherme;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.oris1991.anotherme.ExternalCalendar.Utility;
import com.example.oris1991.anotherme.Model.Entities.SharePictureOrText;
import com.example.oris1991.anotherme.Model.Entities.Task;
import com.example.oris1991.anotherme.Model.Model;
import com.example.oris1991.anotherme.Model.ModelMain;
import com.example.oris1991.anotherme.PopUpAndSMS.PopupTemplates;
import com.example.oris1991.anotherme.PopUpAndSMS.SmsTemplates;

import java.util.List;

/**
 * Created by Itzik on 02/06/2016.
 */
public class ShareHistoryFragment extends Fragment{

    ProgressBar progressBar;
    ListView list;
    List<SharePictureOrText> data;
    int idFromList;
    MyAddapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.share_history_list,
                container, false);
        setHasOptionsMenu(true);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBarList);
        list = (ListView) view.findViewById(R.id.shareHistoryListview);
        progressBar.setVisibility(View.VISIBLE);
        data = Model.instance().getPicture();
        progressBar.setVisibility(View.GONE);
        adapter = new MyAddapter();
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                idFromList = data.get(position).getId();
            }
        });


        return view;

    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_share, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_history) {
            Intent intent = new Intent(getActivity(),
                    HistoryActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_sms_templates)
        {
            Intent intent = new Intent(getActivity(),
                    SmsTemplates.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_popup_templates)
        {
            Intent intent = new Intent(getActivity(),
                    PopupTemplates.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class MyAddapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView,
                            ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                convertView = inflater.inflate(R.layout.share_history, null);
                Log.d("TAG", "create view:" + position);

            } else {
                Log.d("TAG", "use convert view:" + position);
            }

            TextView withPerson = (TextView) convertView.findViewById(R.id.from);
            TextView message = (TextView) convertView.findViewById(R.id.massegeTextHistory);
            final ImageView image = (ImageView) convertView.findViewById(R.id.pic);
            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.ifGet);

            SharePictureOrText st = data.get(position);

            withPerson.setText(st.getShardWtith());
            message.setText(st.getText());
            checkBox.setChecked(st.getSend());
            if(st.getPicName() != null){
                Log.d("TAG","list gets image " + st.getPicName());
                final ProgressBar progress = (ProgressBar) convertView.findViewById(R.id.rowImageProgressBar);
                progress.setVisibility(View.VISIBLE);
                ModelMain.getInstance().loadImage(st.getPicName(),new ModelMain.LoadImageListener() {
                    @Override
                    public void onResult(Bitmap imageBmp) {
                        if (imageBmp != null) {
                            image.setImageBitmap(imageBmp);
                            progress.setVisibility(View.GONE);
                        }
                        else{
                            String uri = "@drawable/a_m_icon";  // where myresource (without the extension) is the file

                            int imageResource = getResources().getIdentifier(uri, null, "drawable");
                            Drawable res = getResources().getDrawable(imageResource);
                            image.setImageDrawable(res);
                        }
                    }
                });
            }

            return convertView;
        }
    }
}
