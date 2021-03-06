package de.noCredentials.service

import de.noCredentials.model.EchoModel
import groovy.util.logging.Log4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service


/**
 * Created by Naik on 23.02.17.
 */
@Log4j
@Service
class SocketService {

    @Autowired
    private SimpMessagingTemplate simpTemplate;

    def echoMessage(String message) {
        log.debug("Start convertAndSend ${new Date()}")
        simpTemplate.convertAndSend('/topic/greetings', new EchoModel(message))
        log.debug("End convertAndSend ${new Date()}")
    }
}
