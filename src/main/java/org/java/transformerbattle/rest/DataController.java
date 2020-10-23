package org.java.transformerbattle.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.java.transformerbattle.model.StatusResponse;
import org.java.transformerbattle.model.TransformerInData;
import org.java.transformerbattle.model.TransformerOutData;
import org.java.transformerbattle.model.TransformerResponse;
import org.java.transformerbattle.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transformer")
@Api(tags = "Transformers",description = "Transformer Data Operations")
public class DataController {

    private static final Logger LOG = LoggerFactory.getLogger(DataController.class);

    @Autowired
    private DataService dataService;

    @PostMapping("/create")
    @ApiOperation(value = "Add Transformer")
    public ResponseEntity createTransformer(@RequestBody TransformerInData transformer){
        StatusResponse response = new StatusResponse();
        try{
            dataService.create(transformer);
            response.setMessage("Transformer added successfully");
            return ResponseEntity.ok(response);
        }catch (Exception e){
            LOG.error("Exception while creating a transformer cause: {}",e.getMessage());
            response.setMessage("Exception while creating a transformer");
        }
        return ResponseEntity.badRequest().body(response);
    }

    @GetMapping("/list")
    @ApiOperation(value = "List all Transformers")
    public ResponseEntity listTransformers(){
        TransformerResponse response = new TransformerResponse();
        try{
            List<TransformerOutData> transformersList = new ArrayList<>();
            transformersList = dataService.listAllTransformers();
            response.setTransformers(transformersList);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            StatusResponse status = new StatusResponse();
            status.setMessage("Exception occurred while retrieving transformer data Cause is: "+e.getMessage());
            LOG.error("Exception while retrieving transformers data. Cause:{}",e.getMessage());
            return ResponseEntity.badRequest().body(status);
        }
    }

    @PutMapping("/{id}/save")
    @ApiOperation(value = "Update Transformer")
    public ResponseEntity updateTransformer(@PathVariable("id") int id, @RequestBody TransformerInData transformer){
        StatusResponse response = new StatusResponse();
        try{
            dataService.updateTransformer(id,transformer);
            response.setMessage("Updated successfully");
            return ResponseEntity.ok(response);
        }catch (Exception e){
            response.setMessage("Exception occurred while updating transformer data Cause is: "+e.getMessage());
            LOG.error("Exception while updating transformers data with ID: {}. Cause:{}",id,e.getMessage());
        }
        return ResponseEntity.badRequest().body(response);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Transformer")
    public ResponseEntity deleteTransformerById(@PathVariable("id") int id){
        StatusResponse response = new StatusResponse();
        try{
            dataService.deleteById(id);
            response.setMessage("Deleted successfully");
            return ResponseEntity.ok(response);
        }catch (Exception e){
            response.setMessage("Exception occurred while deleting transformer. Cause: "+e.getMessage());
            LOG.error("Exception while deleting transformers with ID: {}. Cause:{}",id,e.getMessage());
        }
        return ResponseEntity.badRequest().body(response);
    }

}
