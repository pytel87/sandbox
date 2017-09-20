package u3k.cleancode.base;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

public final class RxBus {

    private static PublishSubject<Object> publishSubject = PublishSubject.create();

    public static Disposable subscribe(Consumer<Object> action) {
        return publishSubject.subscribe(action);
    }

    public static void publish(Object message) {
        publishSubject.onNext(message);
    }
}