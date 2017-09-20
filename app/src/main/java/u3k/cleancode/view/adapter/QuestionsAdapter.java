
package u3k.cleancode.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import u3k.cleancode.R;
import u3k.cleancode.model.Question;


public class QuestionsAdapter extends BaseRecyclerAdapter<Question, QuestionsAdapter.ViewHolder> {


    private Context context;

    public QuestionsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_questions, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Question item = getItemByPosition(position);
        holder.titleView.setText(item.getTitle());
        Glide.with(context).load(item.getOwnerImage()).placeholder(R.color.colorAccent).error(R.color.colorAccent).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getClickSubject().onNext(item);
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public long getItemId(int position) {
        return getItemByPosition(position).hashCode();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        public ImageView imageView;

        @BindView(R.id.title)
        public TextView titleView;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}