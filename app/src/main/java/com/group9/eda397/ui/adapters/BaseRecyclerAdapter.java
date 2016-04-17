package com.group9.eda397.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.group9.eda397.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author palmithor
 * @since 17/04/16.
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_FOOTER = 2;
    protected boolean hasFooter = false;
    protected boolean hasHeader = false;
    private List<T> list = new ArrayList<>();

    public BaseRecyclerAdapter() {
    }

    public BaseRecyclerAdapter(final boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_ITEM) {
            return getItemView(inflater, parent);
        } else if (viewType == TYPE_HEADER) {
            return getHeaderView(inflater, parent);
        } else if (viewType == TYPE_FOOTER) {
            return getFooterView(inflater, parent);
        }
        throw new RuntimeException("Invalid view type!");
    }

    @Override
    public int getItemCount() {
        int itemCount = list.size();
        itemCount = hasFooter ? ++itemCount : itemCount;
        itemCount = hasHeader ? ++itemCount : itemCount;
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (hasHeader && isPositionHeader(position)) {
            return TYPE_HEADER;
        } else if (hasFooter && isPositionFooter(position)) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    public void setHasFooter(final boolean hasFooter) {
        if (this.hasFooter() && !hasFooter) {
            notifyItemRemoved(getItemCount());
        } else if (!this.hasFooter && hasFooter) {
            notifyItemInserted(getItemCount());
        }
        this.hasFooter = hasFooter;

    }

    public T getItem(int position) {
        return hasHeader ? list.get(position - 1) : list.get(position);
    }

    public List<T> getList() {
        return list;
    }

    public boolean hasItems() {
        return list != null && !list.isEmpty();
    }

    public boolean hasFooter() {
        return hasFooter;
    }

    public boolean hasHeader() {
        return hasHeader;
    }

    public void add(final T item) {
        list.add(item);
        notifyItemInserted(list.size() - 1);
    }

    public void add(final int index, final T item) {
        list.add(index, item);
        notifyItemInserted(list.size() - 1);
    }

    public void push(final T item) {
        list.add(0, item);
        notifyItemInserted(0);
    }

    public void remove(final T item) {
        final int indexOfItem = list.indexOf(item);
        list.remove(indexOfItem);
        notifyItemRemoved(indexOfItem);
    }

    public void update(final T updated) {
        if (getList().contains(updated)) {
            final int position = getList().indexOf(updated);
            getList().remove(position);
            getList().add(position, updated);
            notifyItemChanged(position);
        }
    }

    public void addAll(final List<T> data, final boolean animated) {
        final int count = getItemCount();
        list.addAll(data);
        if (animated) {
            notifyItemRangeInserted(count - 1, data.size());
        } else {
            notifyDataSetChanged();
        }
    }

    public void pushAll(final List<T> data, final boolean animated) {
        list.addAll(0, data);
        if (animated) {
            notifyItemRangeInserted(0, data.size());
        } else {
            notifyDataSetChanged();
        }
    }

    public void clear() {
        list.clear();
    }

    public void setData(final List<T> data, final boolean animated) {
        clear();
        list.addAll(data);
        if (animated) {
            int positionStart = hasHeader ? 1 : 0;
            notifyItemRangeInserted(positionStart, data.size());
        } else {
            notifyDataSetChanged();
        }
    }

    public T remove(final int position) {
        final T model = list.remove(position);
        notifyItemRemoved(position);
        return model;
    }


    protected abstract RecyclerView.ViewHolder getItemView(LayoutInflater inflater, ViewGroup parent);

    protected RecyclerView.ViewHolder getHeaderView(LayoutInflater inflater, ViewGroup parent) {
        return null;
    }


    /**
     * If footer should be anything else than loading view this should be overridden
     *
     * @param inflater
     * @param parent
     * @return
     */
    protected RecyclerView.ViewHolder getFooterView(LayoutInflater inflater, ViewGroup parent) {
        return new DefaultLoadingViewHolder(inflater.inflate(R.layout.item_loading, parent, false));
    }

    protected boolean isPositionHeader(int position) {
        return position == 0;
    }

    protected boolean isPositionFooter(int position) {
        return position == getItemCount() - 1;
    }


    public abstract class ItemViewHolder extends RecyclerView.ViewHolder {
        public ItemViewHolder(final View itemView) {
            super(itemView);
        }

        protected abstract void bindTo();
    }

    protected class DefaultLoadingViewHolder extends RecyclerView.ViewHolder {
        public DefaultLoadingViewHolder(final View itemView) {
            super(itemView);
        }
    }
}