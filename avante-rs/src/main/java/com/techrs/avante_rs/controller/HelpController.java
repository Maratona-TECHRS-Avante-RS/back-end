package com.techrs.avante_rs.controller;

import com.techrs.avante_rs.dto.IdResponse;
import com.techrs.avante_rs.dto.help.CompleteHelpResponse;
import com.techrs.avante_rs.dto.help.CreateHelpRequest;
import com.techrs.avante_rs.dto.help.ResumeHelpCreatorResponse;
import com.techrs.avante_rs.dto.help.ResumeHelpResponse;
import com.techrs.avante_rs.service.HelpService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/helps")
public class HelpController {
    @Autowired
    private HelpService helpService;


    @GetMapping()
    public Page<ResumeHelpResponse> getAll(
            @RequestParam(value = "urgencyLevelId", required = false) Long urgencyLevelId,
            @RequestParam(value = "distance", required = false) Long distance,
            @RequestParam(value = "helpTypeId", required = false) Long helpTypeId,
                                           Pageable pageable) {
        return helpService.getAll(urgencyLevelId, helpTypeId, distance, pageable);
    }


    @GetMapping("me")
    public Page<ResumeHelpCreatorResponse> getAll(Pageable pageable) {
        return helpService.getAllByUser(pageable);
    }

    @GetMapping("/{id}")
    public CompleteHelpResponse getById(@PathVariable Long id) {
        return helpService.getById(id);
    }

    @PatchMapping("/{id}")
    public IdResponse getInHelp(@PathVariable Long id){
        return helpService.getInHelp(id);
    }


    @PostMapping
    public IdResponse createHelp(@Valid @RequestBody CreateHelpRequest request){
        return helpService.create(request);
    }

    @PutMapping("/{id}")
    public IdResponse createHelp(@PathVariable Long id, @Valid @RequestBody CreateHelpRequest request){
        return helpService.edit(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteHelp(@PathVariable Long id){
        helpService.delete(id);
    }
}
