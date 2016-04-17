package com.group9.eda397.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.group9.eda397.R;
import com.group9.eda397.model.TravisBuild;
import com.group9.eda397.utils.StringUtils;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Recycler Adapter for Displaying Travis Builds
 *
 * @author palmithor
 * @since 17/04/16.
 */
public class TravisBuildsAdapter extends BaseRecyclerAdapter<TravisBuild> {

    public static final String STATE_FAILED = "failed";
    private final Context context;
    private final Picasso picasso;
    private final ItemClickListener itemClickListener;

    public TravisBuildsAdapter(final Context context, final Picasso picasso, final ItemClickListener itemClickListener) {
        this.context = context;
        this.picasso = picasso;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int pos) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.bindTo();
            itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    itemClickListener.onClickItem(v, pos, getList().get(pos));
                }
            });
        }
    }

    @Override
    protected RecyclerView.ViewHolder getItemView(final LayoutInflater inflater, final ViewGroup parent) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_travis_build, parent, false));
    }

    public interface ItemClickListener {
        void onClickItem(final View view, final int position, final TravisBuild item);
    }

    public class ItemViewHolder extends BaseRecyclerAdapter.ItemViewHolder {

        protected View itemView;

        @Bind(R.id.tv_build_no) TextView buildTextView;
        @Bind(R.id.tv_branch) TextView branchTextView;
        @Bind(R.id.tv_date) TextView finishedAtTextView;
        @Bind(R.id.iv_result) ImageView stateImageView;

        public ItemViewHolder(final View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void bindTo() {
            TravisBuild build = getList().get(getAdapterPosition());
            buildTextView.setText(String.format("%s%s", StringUtils.HASHTAG, build.getBuildNumber()));
            branchTextView.setText(build.getBranch());
            if (build.getFinishedAt() == null) {
                finishedAtTextView.setText(R.string.title_in_progress);
                stateImageView.setImageResource(R.drawable.ic_loop_black_36dp);
            } else {
                finishedAtTextView.setText(String.format("%s at %s",
                        DateUtils.formatDateTime(context, build.getFinishedAt().getTime(), DateUtils.FORMAT_ABBREV_MONTH),
                        DateUtils.formatDateTime(context, build.getFinishedAt().getTime(), DateUtils.FORMAT_SHOW_TIME)));
                stateImageView.setImageResource(build.getResult() != 0 ? R.drawable.ic_thumb_down_black_24dp : R.drawable.ic_thumb_up_black_24dp);
            }
        }
    }

}
