package co.com.sofka.generic.events;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DomainEvent<T> {
    private final String type;
    private final String aggregateId;
    private final T source;

    public String getType() {
        return this.type;
    }

    public T getSource() {
        return source;
    }

    public String getAggregateId() {
        return aggregateId;
    }
}

