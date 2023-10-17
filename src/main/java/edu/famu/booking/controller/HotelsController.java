package edu.famu.booking.controller;
import edu.famu.booking.service.HotelsService;
import edu.famu.booking.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hotel")
//hello
public class HotelsController {
    private HotelsService hotelsService;

    public HotelsController(HotelsService hotelsService) {
        this.hotelsService = hotelsService;
    }
    @GetMapping
    public ResponseEntity<ApiResponse> getAllHotels()
    {
        try {
            return ResponseEntity.ok(new ApiResponse(true,"Success", hotelsService.getAllHotels(), null));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred.", null, e.getMessage()));
        }
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<ApiResponse> getHotelsById(@PathVariable String hotelId){
        try {
            return ResponseEntity.ok(new ApiResponse(true,"Success", hotelsService.getHotelsById(hotelId), null));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred.", null, e.getMessage()));
        }
    }
}
