package com.example.demo.repository;

import com.example.demo.model.BoundingBox;
import com.example.demo.model.BoundingBoxDTO;
import org.springframework.stereotype.Component;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

@Component
public class BoundingBoxRepository {
    private Map<Integer, BoundingBox> bbdatabase;

    public BoundingBoxRepository() {
        this.bbdatabase = new HashMap<>();
    }

    public void addBoundingBox(BoundingBox bB) {
        bbdatabase.put(bB.getID(), bB);

    }

    public BoundingBoxDTO getAllBoundingBoxes() {
        return new BoundingBoxDTO(Map.copyOf(this.bbdatabase));
    }

    public BoundingBox getBoundingBoxByID(int iD) {
        for (int i = 0; i < bbdatabase.size(); i++) {
            if (bbdatabase.get(i).getID() == iD) {
                return bbdatabase.get(i);
            }
        }
        return null;
    }
    public String errorMsg(){return "error";}
    public void editBoundingBox(int iD, Point2D.Double p1, Point2D.Double p2) {
            bbdatabase.get(iD).setPoint1(p1);
            bbdatabase.get(iD).setPoint2(p2);
    }
    public Integer calculateID() {
        boolean bool = true;
        int iD = 0;
        while (bool) {
            iD = (int) ((Math.random() * (10000)));
            if (!bbdatabase.containsKey(iD)) bool = false;
        }
        return iD;
    }
    public void removeBoundingBox(int iD){
        bbdatabase.remove(iD);
    }
}

