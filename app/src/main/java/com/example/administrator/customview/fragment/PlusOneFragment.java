package com.example.administrator.customview.fragment;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.customview.Bean;
import com.example.administrator.customview.R;
import com.example.administrator.customview.recyclerview.RecycleViewActivity;
import com.example.administrator.customview.recyclerview.enclosure.MyItemTouchHelper;
import com.example.administrator.customview.recyclerview.enclosure.RecycleViewClickListenering;
import com.example.administrator.customview.touch_pull.TouchPullActivity;

import java.util.ArrayList;
import java.util.List;


public class PlusOneFragment extends Fragment {

    private List<Bean> beanList = new ArrayList<>();
    private RecyclerView recyclerView;
    private OnFragmentInteractionListener mListener;
    private Context mContext;


    public PlusOneFragment() {
        // Required empty public constructor
    }

    public static PlusOneFragment newInstance() {
        PlusOneFragment fragment = new PlusOneFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beanList.add(new Bean(R.mipmap.ic_launcher_round,"Pull To Refresh"));
        beanList.add(new Bean(R.mipmap.ic_launcher_round,"Drag RecycleView"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plus_one, container, false);
        recyclerView = view.findViewById(R.id.recycle_view);
        FrgRecyclerAdapter frgRecyclerAdapter = new FrgRecyclerAdapter(getActivity(),beanList);
        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new MyItemTouchHelper<>(frgRecyclerAdapter));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(frgRecyclerAdapter);
        recyclerView.addOnItemTouchListener(new RecycleViewClickListenering(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getActivity().getWindow().setExitTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.trans));
                    startActivity(new Intent(getActivity(),TouchPullActivity.class), ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                }

            }

            @Override
            public void onLongClick(RecyclerView.ViewHolder viewHolder) {
                itemTouchHelper.startDrag(viewHolder);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
