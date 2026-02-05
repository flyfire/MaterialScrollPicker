package com.eddystudio.scrollpicker;


import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

/*
 * Creadted by houruhou on 2026/02/05 11:29
 */
public class SolarexScrollPickerView extends RelativeLayout {
    private RecyclerView recyclerView;
    public SolarexScrollPickerView(Context context) {
        super(context);
        init(context);
    }

    public SolarexScrollPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SolarexScrollPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.layout_solarex_scroll_picker_view,
                this,
                true
        );
        recyclerView = view.findViewById(R.id.picker_recyclerview);
        setBackgroundColor(Color.BLACK);
    }

    public void setup(ScrollPickerAdapter adapter, OnItemSelectedListener onItemSelectedListener, OnItemUnselectedListener onItemUnselectedListener) {
        adapter.setOnItemClickListener(new ScrollPickerAdapter.OnItemClickListener() {
            @Override
            public void onClicked(@NonNull View view, int position) {
                recyclerView.smoothScrollToPosition(position);
            }
        });
        ScrollPickerLayoutManager layoutManager = new ScrollPickerLayoutManager(getContext(), RecyclerView.HORIZONTAL);
        layoutManager.setOnItemSelectedListener(onItemSelectedListener);
        layoutManager.setOnItemUnselectedListener(onItemUnselectedListener);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setLayoutManager(layoutManager);
        LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
        linearSnapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int width = 0;
        View view = recyclerView.getChildAt(0);
        if (view != null) {
            width = view.getWidth() / 2;
        }
        int padding = w/2 - width;
        recyclerView.setPadding(padding, 0, padding, 0);
    }
}
