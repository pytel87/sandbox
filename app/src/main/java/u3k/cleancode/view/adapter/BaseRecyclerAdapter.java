package u3k.cleancode.view.adapter;


import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import u3k.cleancode.utils.Utils;

/**
 * Created by Vladimir Skoupy.
 */

public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {


    private List<T> items;

    private final PublishSubject<T> clickSubject = PublishSubject.create();


    public BaseRecyclerAdapter() {
        this.items = new ArrayList<>();
    }

    public void addItem(T item) {
        if (item != null) {
            this.items.add(item);
            notifyDataSetChanged();
        }
    }

    public void addItems(List<T> items) {
        if (Utils.isNotEmpty(items)) {
            this.items.addAll(items);
        }
        notifyDataSetChanged();
    }

    public void setItems(List<T> items) {
        this.items.clear();
        addItems(items);
    }

    public T removeItem(int position) {
        T remove = this.items.remove(position);
        notifyItemRemoved(position);
        return remove;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<T> getItems() {
        return items;
    }

    public T getItemByPosition(int position) {
        return items.get(position);
    }

    public void clear() {
        this.items.clear();
        notifyDataSetChanged();
    }


    public Observable<T> getClickSubjectAsObservable() {
        return clickSubject;
    }

    public PublishSubject<T> getClickSubject() {
        return clickSubject;
    }
}