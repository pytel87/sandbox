package u3k.cleancode.manager.rest;

import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.subscribers.DisposableSubscriber;
import u3k.cleancode.model.Resource;

public abstract class NetworkBoundSource<LocalType, RemoteType> {


    public NetworkBoundSource(final FlowableEmitter<Resource<LocalType>> emitter) {

        final Disposable firstLocalDisposable = getLocal().take(1).subscribe(new Consumer<LocalType>() {
            @Override
            public void accept(@NonNull LocalType localType) throws Exception {
                emitter.onNext(Resource.loading(localType));
            }
        });

        getRemote().map(mapper()).subscribeWith(new DisposableSubscriber<LocalType>() {


            @Override
            public void onNext(LocalType localType) {
                firstLocalDisposable.dispose();
                saveRequestResult(localType);
                getLocal().take(1).subscribe(new Consumer<LocalType>() {
                    @Override
                    public void accept(@NonNull LocalType localType) throws Exception {
                        emitter.onNext(Resource.success(localType));
                    }
                });
            }

            @Override
            public void onError(Throwable t) {
                emitter.onError(t);
            }

            @Override
            public void onComplete() {

            }
        });


    }

    public abstract Flowable<RemoteType> getRemote();

    public abstract Flowable<LocalType> getLocal();

    public abstract void saveRequestResult(LocalType data);

    public abstract Function<RemoteType, LocalType> mapper();

}
