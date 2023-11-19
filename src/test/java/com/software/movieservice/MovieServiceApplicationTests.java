package com.software.movieservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.software.movieservice.model.Movie;
import lombok.var;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class MovieServiceApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void given_movie_whenCreateMovie_thenReturnSavedMovie() throws Exception {

		//Given
		Movie movie=new Movie();
		movie.setName("AAa");
		movie.setDirector("Trivikram");
		movie.setActor("nithin");
	  //when create movie
      var response=   mockMvc.perform(post("/movies")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(movie)));

	  //then verify saved movie
	  response.andDo(print())
			  .andExpect(status().isOk())
			  .andExpect(jsonPath("$.id",is(notNullValue())))
			  .andExpect(jsonPath("$.actor",is(movie.getActor())))
			  .andExpect(jsonPath("$.name",is(movie.getName())))
			  .andExpect(jsonPath("$.director",is(movie.getDirector())));
	}

}
