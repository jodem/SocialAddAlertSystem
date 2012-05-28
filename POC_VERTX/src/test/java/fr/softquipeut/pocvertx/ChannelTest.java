package fr.softquipeut.pocvertx;

import fr.softquipeut.pocvertx.channel.business.Channel;
import java.math.BigDecimal;
import static org.fest.assertions.Assertions.*;
import org.junit.Test;

/**
 *
 * @author Joce
 */
public class ChannelTest {

    @Test
    public void TestEqualsOk() {

        Channel channelOne = new Channel("KEY", "a sample name");
        Channel channelOneWithOtherName = new Channel("KEY", "another sample name");

        assertThat(channelOne).isEqualTo(channelOneWithOtherName);
    }
}
