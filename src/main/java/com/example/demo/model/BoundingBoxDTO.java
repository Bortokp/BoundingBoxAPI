package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoundingBoxDTO {
    private Map<Integer,BoundingBox> boundingBoxes;
}
