package com.formationspring.demo.restcontroller;

import com.formationspring.demo.dto.ArticleDto;
import com.formationspring.demo.mapper.ArticleMapper;
import com.formationspring.demo.services.Interface.ArticleInterface;
import org.springframework.web.bind.annotation.*;

import static com.formationspring.demo.mapper.UserDataAccessMapper.fromInput;

@RestController
@RequestMapping("/Post")
public class ArticleRestController {

    private final ArticleInterface postService;

    public ArticleRestController(ArticleInterface postService) {
        this.postService = postService;
    }

    @PostMapping("/")
    public ArticleDto.Output createPost(@RequestBody ArticleDto.Input postInput) {
        // Convertir Input en Output pour envoyer au service
        ArticleDto.Output output = ArticleMapper.fromInputToOutput(postInput);
        return postService.createPost(output);
    }



    @GetMapping("/{id}")
    public ArticleDto.Output getPost(@PathVariable int id) {
        return postService.findPostById(id);
    }
}
