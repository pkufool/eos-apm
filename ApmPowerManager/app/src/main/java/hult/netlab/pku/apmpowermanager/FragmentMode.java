package hult.netlab.pku.apmpowermanager;


import android.content.Context;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentMode.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentMode#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMode extends Fragment {

    private ArrayList<Mode> modeList;
    private ListView listView;
    private ImageView addView;

    public FragmentMode() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_fragment_mode,container,false);
        listView = (ListView)rootview.findViewById(R.id.mode_list);
        modeList = getData();
        final modeAdapter adapter =new modeAdapter(container.getContext());
        listView.setAdapter(adapter);

        addView = (ImageView)rootview.findViewById(R.id.add_mode);
        addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modeList = addData(modeList);
                adapter.notifyDataSetChanged();
            }
        });

        return rootview;
    }

    private ArrayList<Mode> getData(){
        ArrayList<Mode> list = new ArrayList<Mode>();
    //    for(int i=1;i<4;i++) {
            Mode mode = new Mode();
            mode.check = Math.random()>0.5 ? true : false;
            mode.name = "mode"+1;
        //    mode.description = "mode"+1+" description!";
            list.add(mode);
     //   }
        return list;
    }

    private ArrayList<Mode> addData(ArrayList<Mode> list){
        int length = list.size()+1;
        Mode mode = new Mode();
        mode.check = Math.random()>0.5 ? true : false;
        mode.name = "mode"+length;
     //   mode.description = "mode"+length+" description!";
        list.add(mode);
        return list;
    }

    public class modeAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;

        public modeAdapter(Context context){
            this.layoutInflater=LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return modeList.size();
        }

        @Override
        public Object getItem(int position) {
            return modeList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHloder holder = null;
            if(convertView == null){
                holder = new ViewHloder();
                convertView = layoutInflater.inflate(R.layout.mode_list_detail,null);
                holder.checkbutton = (RadioButton)convertView.findViewById(R.id.modecheckbutton);
                holder.name = (TextView)convertView.findViewById(R.id.modename);
           //     holder.description = (TextView)convertView.findViewById(R.id.modedescription);
                convertView.setTag(holder);
            }else{
                holder = (ViewHloder)convertView.getTag();
            }
            holder.checkbutton.setChecked(modeList.get(position).check);
            holder.name.setText(modeList.get(position).name);
    //        holder.description.setText(modeList.get(position).description);

            return convertView;
        }
    }

    class Mode {
        boolean check;
        String name;
   //     String description;
    }

    public final class ViewHloder{
        public RadioButton checkbutton;
        public TextView name;
 //       public TextView description;
    }


}
