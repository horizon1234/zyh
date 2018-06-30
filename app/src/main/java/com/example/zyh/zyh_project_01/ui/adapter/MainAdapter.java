package com.example.zyh.zyh_project_01.ui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.zyh.zyh_project_01.R;
import com.example.zyh.zyh_project_01.data.db.Comic;
import com.example.zyh.zyh_project_01.data.entity.FullHomeItem;
import com.example.zyh.zyh_project_01.data.entity.HomeTitle;
import com.example.zyh.zyh_project_01.data.entity.LargeHomeItem;
import com.example.zyh.zyh_project_01.data.entity.SmallHomeItem;
import com.example.zyh.zyh_project_01.ui.adapter.base.BaseRecyclerAdapter;
import com.example.zyh.zyh_project_01.ui.adapter.base.BaseRecyclerHolder;
import com.example.zyh.zyh_project_01.ui.view.custom.NoScrollGridLayoutManager;

import java.util.List;

/**
 * Created by zyh on 2018/6/28.
 */

public class MainAdapter extends BaseRecyclerAdapter<Comic> {

    public static final int ITEM_TITLE = 0;
    public static final int ITEM_LARGE = 1;
    public static final int ITEM_SMALL = 2;
    public static final int ITEM_FULL = 3;
    private int itemTitleLayoutId;
    private int itemFullLayoutId;
    private int itemFullLayoutTreeId;
    private OnItemClickListener mItemClickListener;

    public MainAdapter(Context context, int itemLayoutId) {
        super(context, itemLayoutId);
    }

    public MainAdapter(Context context, int itemTitleLayoutId,int itemFullLayoutTreeId,int itemLayoutId,int itemFullLayoutId) {
        super(context, itemLayoutId);
        this.itemLayoutId = itemLayoutId;
        this.itemTitleLayoutId = itemTitleLayoutId;
        this.itemFullLayoutId = itemFullLayoutId;
        this.itemFullLayoutTreeId = itemFullLayoutTreeId;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof NoScrollGridLayoutManager){
            final NoScrollGridLayoutManager gridLayoutManager = (NoScrollGridLayoutManager) manager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int size = 0;
                    switch(getItemViewType(position)){
                        case ITEM_FULL:
                            size = 6;
                            break;
                        case ITEM_LARGE:
                            size = 3;
                            break;
                        case ITEM_SMALL:
                            size = 2;
                            break;
                        case ITEM_TITLE:
                            size = 6;
                            break;
                        default:
                            size = 2;
                            break;
                    }
                    return size;
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        Comic comic = list.get(position);
        if(comic instanceof HomeTitle){
            return ITEM_TITLE;
        }else if(comic instanceof SmallHomeItem){
            return ITEM_SMALL;
        }else if(comic instanceof LargeHomeItem){
            return ITEM_LARGE;
        }else if(comic instanceof FullHomeItem){
            return ITEM_FULL;
        }
        else {
            return ITEM_SMALL;
        }
    }

    @Override
    public BaseRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case ITEM_TITLE:
                view = inflater.inflate(itemTitleLayoutId,parent,false);
                break;
            case ITEM_LARGE:
                view = inflater.inflate(itemLayoutId,parent,false);
                break;
            case ITEM_SMALL:
                view = inflater.inflate(itemFullLayoutTreeId,parent,false);
                break;
            case ITEM_FULL:
                view = inflater.inflate(itemFullLayoutId,parent,false);
                break;
            default:
                view = inflater.inflate(itemLayoutId,parent,false);
                break;
        }
        return BaseRecyclerHolder.getRecyclerHolder(context,view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mItemClickListener != null && v != null && recyclerView != null){
                    int position = recyclerView.getChildAdapterPosition(v);
                    if(list.get(position) instanceof HomeTitle){
                        mItemClickListener.onTitleClick(recyclerView,v,
                                ((HomeTitle) list.get(position)).getTitleType());
                    }else {
                        mItemClickListener.onItemClick(recyclerView,v,position);
                    }
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(longClickListener != null && v != null && recyclerView != null){
                    int position = recyclerView.getChildAdapterPosition(v);
                    longClickListener.onItemLongClick(recyclerView,v,position);
                    return true;
                }
                return false;
            }
        });

        convert(holder,list.get(position),position);
    }

    public MainAdapter(Context context, List<Comic> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void convert(BaseRecyclerHolder holder, Comic item, int position) {
        switch (getItemViewType(position)){
            case ITEM_TITLE:
                if(position == 0){
                    holder.setVisibility(R.id.v_padding,View.GONE);
                }
                holder.setText(R.id.tv_hometitle,((HomeTitle)item).getItemTitle());
                break;
            case ITEM_FULL:
                holder.setText(R.id.tv_title,item.getTitle());
                holder.setText(R.id.tv_describe,item.getDescribe());
                holder.setText(R.id.tv_author,item.getAuthor());
                holder.setImageByUrl(R.id.iv_image,item.getCover());
                break;
            default:
                holder.setText(R.id.tv_title,item.getTitle());
                holder.setText(R.id.tv_describe,item.getDescribe());
                holder.setImageByUrl(R.id.iv_image,item.getCover());
        }
    }

    public void setOnItemClickListener(OnItemClickListener mItemClickListener){
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(RecyclerView parent, View view, int position);
        void onTitleClick(RecyclerView parent, View view, int type);
    }
}
