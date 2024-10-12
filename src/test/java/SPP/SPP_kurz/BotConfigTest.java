package SPP.SPP_kurz;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {BotConfig.class})
@ExtendWith(SpringExtension.class)
@Import(value = {
        BotConfig.class
})
class BotConfigTest
{

    @Autowired
    BotConfig config;

    @Test
    void getBotName()
    {
        assertNotNull(config.getBotName());
    }

    @Test
    void getToken()
    {
        assertNotNull(config.getToken());
    }
}