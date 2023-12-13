package com.ouharri.aftas.events;

import org.springframework.context.ApplicationEvent;

/**
 * Event class representing the creation of a hunting activity.
 */
public class HuntingCreatedEvent extends ApplicationEvent {

    /**
     * Constructs a new HuntingCreatedEvent.
     *
     * @param source The source of the event.
     */
    public HuntingCreatedEvent(Object source) {
        super(source);
    }

}