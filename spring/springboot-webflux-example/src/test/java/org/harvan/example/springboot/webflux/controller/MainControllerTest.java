package org.harvan.example.springboot.webflux.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.harvan.example.springboot.webflux.Application;
import org.harvan.example.springboot.webflux.entity.MainEntity;
import org.harvan.example.springboot.webflux.entity.MainEntityBuilder;
import org.harvan.example.springboot.webflux.repository.impl.MainEntityRepositoryImpl;
import org.harvan.example.springboot.webflux.services.impl.MainEntityServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class MainControllerTest {
    private TestRestTemplate restTemplate;
    @Autowired
    private MainEntityServiceImpl mainEntityService;
    @MockBean
    private MainEntityRepositoryImpl mainEntityRepository;

    @Autowired
    public void setRestTemplate(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;

        setSingleConverter(restTemplate);
    }

    private void setSingleConverter(TestRestTemplate restTemplate) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new ObjectMapper();
        List<HttpMessageConverter<?>> converters = new ArrayList<>();

        converter.setObjectMapper(mapper);
        converters.add(converter);

        restTemplate.getRestTemplate().setMessageConverters(converters);
    }

    private MainEntity buildMainEntity(int id) {
        return new MainEntityBuilder().withId(id).withName("name").withDescription("description").build();
    }

    public Flux<MainEntity> findAll() {
        List<MainEntity> list = new ArrayList<>(4);

        list.add(buildMainEntity(901));
        list.add(buildMainEntity(902));
        list.add(buildMainEntity(903));
        list.add(buildMainEntity(904));

        return Flux.just(list.toArray(new MainEntity[4]));
    }

    @Test
    public void testHome() {
        when(mainEntityRepository.findAll()).thenReturn(findAll());
        List<MainEntity> list = Arrays.asList(restTemplate.getForEntity("/webflux/", MainEntity[].class).getBody());

        assertNotNull(list);
        assertEquals(4, list.size());
        assertTrue(list.get(0).getId().equals(901));
        assertTrue(list.get(1).getId().equals(902));
        assertTrue(list.get(2).getId().equals(903));
        assertTrue(list.get(3).getId().equals(904));
    }

    @Test
    public void findById() {
        Mono<MainEntity> mainEntity = Mono.just(buildMainEntity(99));
        when(mainEntityRepository.findById(anyInt())).thenReturn(mainEntity);

        Mono<MainEntity> response = Mono.just(restTemplate.getForEntity("/webflux/99", MainEntity.class).getBody());

        assertNotNull(response);
        assertEquals(mainEntity.block(), response.block());
    }
}