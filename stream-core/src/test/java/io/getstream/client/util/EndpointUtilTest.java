package io.getstream.client.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.URI;

import org.junit.Test;

import io.getstream.client.config.ClientConfiguration;
import io.getstream.client.config.StreamRegion;

public class EndpointUtilTest {

    @Test
    public void shouldGetPersonalizedEndpoint() {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setPersonalizedFeedEndpoint("http://yourcompany.getstream.io/yourcompany");

        URI personalizedEndpoint = EndpointUtil.getPersonalizedEndpoint(clientConfiguration);
        assertThat(personalizedEndpoint.toString(), is("http://yourcompany.getstream.io/yourcompany/"));
    }

    @Test
    public void shouldGetPersonalizedEndpointWithoutDoubleSlash() {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setPersonalizedFeedEndpoint("http://yourcompany.getstream.io/yourcompany/");

        URI personalizedEndpoint = EndpointUtil.getPersonalizedEndpoint(clientConfiguration);
        assertThat(personalizedEndpoint.toString(), is("http://yourcompany.getstream.io/yourcompany/"));
    }

    @Test
    public void shouldGetCustomEndpoint() {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setDefaultEndpoint("http://www.example.com/v1");

        URI personalizedEndpoint = EndpointUtil.getBaseEndpoint(clientConfiguration);
        assertThat(personalizedEndpoint.toString(), is("http://www.example.com/v1"));
    }

    @Test
    public void shouldGetRegionDefaultEndpoint() {
        ClientConfiguration clientConfiguration = new ClientConfiguration(StreamRegion.US_EAST);

        URI personalizedEndpoint = EndpointUtil.getBaseEndpoint(clientConfiguration);
        assertThat(personalizedEndpoint.toString(), is(StreamRegion.US_EAST.getEndpoint().toString()));
    }

    @Test(expected = NullPointerException.class)
    public void shouldFail() {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setPersonalizedFeedEndpoint(null);

        EndpointUtil.getPersonalizedEndpoint(clientConfiguration);
    }
}