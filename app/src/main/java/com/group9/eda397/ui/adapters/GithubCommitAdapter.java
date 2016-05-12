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
import com.group9.eda397.model.GitHubCommitItem;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GithubCommitAdapter extends BaseRecyclerAdapter<GitHubCommitItem>{
    public static final String STATE_FAILED = "failed";
    private final Context context;
    private final Picasso picasso;
    private final ItemClickListener itemClickListener;

    public GithubCommitAdapter(final Context context, final Picasso picasso, final ItemClickListener itemClickListener) {
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
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_github_commits, parent, false));
    }



    public interface ItemClickListener {
        void onClickItem(final View view, final int position, final GitHubCommitItem item);
    }

    public class ItemViewHolder extends BaseRecyclerAdapter.ItemViewHolder {

        protected View itemView;

        @Bind(R.id.tv_user) TextView userTextView;
        @Bind(R.id.tv_message) TextView messageTextView;
        @Bind(R.id.tv_date) TextView dateTextView;

        public ItemViewHolder(final View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void bindTo() {
            GitHubCommitItem ghItem = getList().get(getAdapterPosition());
            if(ghItem.getAuthor() != null && ghItem.getAuthor().getUsername() != null) {
                userTextView.setText(ghItem.getAuthor().getUsername());
            }
            if(ghItem.getCommit()!= null && ghItem.getCommit().getAuthor() != null && ghItem.getCommit().getAuthor().getDate() != null) {
                dateTextView.setText(DateUtils.formatDateTime(context, ghItem.getCommit().getAuthor().getDate().getTime(), DateUtils.FORMAT_ABBREV_MONTH) +
                        " " + DateUtils.formatDateTime(context, ghItem.getCommit().getAuthor().getDate().getTime(), DateUtils.FORMAT_SHOW_TIME));
            }
            if(ghItem.getCommit() != null && ghItem.getCommit().getMessage() != null) {
                messageTextView.setText(ghItem.getCommit().getMessage());
            }
        }
    }

}
