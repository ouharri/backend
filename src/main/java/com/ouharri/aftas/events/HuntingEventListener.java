package com.ouharri.aftas.events;

import com.ouharri.aftas.model.entities.Hunting;
import com.ouharri.aftas.services.spec.RankingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Event listener for handling events related to hunting activities.
 */
@Slf4j
@Component
@AllArgsConstructor
public class HuntingEventListener {

    private final RankingService rankingService;

    /**
     * Handles the event when a hunting activity is created.
     *
     * @param event The HuntingCreatedEvent.
     */
    @EventListener
    public void handleHuntingCreatedEvent(HuntingCreatedEvent event) {
        if (event.getSource() instanceof Hunting hunting) {
            log.info("Hunting created event received for hunting with id: {}", hunting.getId());
            rankingService.calculateAndSetRanking(hunting);
        }
    }
}
