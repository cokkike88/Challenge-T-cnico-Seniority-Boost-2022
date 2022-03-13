package rooftop.challenge.omunoz.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rooftop.challenge.omunoz.models.payloads.PostPayload;
import rooftop.challenge.omunoz.models.responses.DeleteResponse;
import rooftop.challenge.omunoz.models.responses.ErrorResponse;
import rooftop.challenge.omunoz.services.TextService;

@RestController
@RequestMapping("/text")
@AllArgsConstructor
public class TextController {

    private final TextService textService;


    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> hashRequest(@RequestBody PostPayload payload){
        try {
            var result = textService.hashText(payload);
            if (result == null){
                return ResponseEntity.badRequest().body(new ErrorResponse(true, "The text field isn't present", 400));
            }
            return ResponseEntity.ok(result);
        }
        catch (Exception ex){
            return ResponseEntity.internalServerError().body(new ErrorResponse(true, "An error occurred when processing the text", 422));
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getHashData(@PathVariable() Long id){
        try{
            var result = textService.getHashData(id);
            if(result != null){
                return ResponseEntity.ok(result);
            }
            return ResponseEntity.status(404).body(new ErrorResponse(true, "Text not found", 404));
        }
        catch (Exception ex){
            return ResponseEntity.internalServerError().body(new ErrorResponse(true, "An error occurred when processing the text", 422));
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllHashData(){
        try{
            var result = textService.getAllHashData();
                return ResponseEntity.ok(result);
        }
        catch (Exception ex){
            return ResponseEntity.internalServerError().body(new ErrorResponse(true, "An error occurred when processing the text", 422));
        }
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> deleteHashData(@PathVariable Long id){
        try {
            var isDeleted = textService.deleteHashData(id);
            if(isDeleted){
                return ResponseEntity.ok(new DeleteResponse());
            }
            return ResponseEntity.status(404).body(new ErrorResponse(true, "Text not found", 404));
        }
        catch(Exception ex) {
            return ResponseEntity.internalServerError().body(new ErrorResponse(true, "An error occurred when processing the text", 422));
        }
    }

}
