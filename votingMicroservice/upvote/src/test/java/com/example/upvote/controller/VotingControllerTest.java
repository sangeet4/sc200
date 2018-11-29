package com.example.upvote.controller;

import com.example.upvote.domain.Voting;
import com.example.upvote.service.VotingService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class VotingControllerTest {


    @Autowired
    private MockMvc mockMvc;
    //private Movie movie;
    @Autowired
    private Voting voting;
    @MockBean
    private VotingService votingService;
    @InjectMocks
    private VotingController votingController;
    private List<Voting> list =null;

    public VotingControllerTest() {
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        List<String> tester=new ArrayList<String>();
        tester.add("ybb");
        mockMvc = MockMvcBuilders.standaloneSetup(votingController).build();
        voting = new Voting("challengeId","challengeName",34,44,"userId",1,tester);
        list = new ArrayList();
        list.add(voting);
    }
    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void testUpdateMovieByIdSuccess() throws Exception {
        //movieService.saveMovie(movie);
        // Movie m2=new Movie(movie.getImdbId(),"DDLJ2","http://ddlj2poster/hd/view",9.2,2018);
        when(votingService.updateMovieById((String)any(),(Voting)any())).thenReturn(voting);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/movie/{id}",voting.getChallengeId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(voting)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }


}
